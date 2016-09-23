package com.kimae.forallwebapp.controller;

import java.io.IOException;
import java.util.Map.Entry;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.kimae.forallwebapp.configuration.ModelAndView;

@Local
public abstract class Controller {
    //private static Logger logger = LoggerFactory.getLogger(ThymeleafConfiguration.class);
    @Context
    private HttpServletRequest httpRequest;
    @Context
    private HttpServletResponse httpResponse;
    private TemplateEngine engine;
    public Controller(){
        TemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setPrefix("/layout/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(false);
        engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
    }

    public String toHtml(ModelAndView response, boolean usesTemplate) throws IOException, WebApplicationException {
        WebContext context = new WebContext(httpRequest, httpResponse, httpRequest.getServletContext());
        for(Entry<String, Object> b : response.model.entrySet()){
            context.setVariable(b.getKey(), b.getValue());
        }
        if(usesTemplate){
            context.setVariable("view", response.view);
        }
        return engine.process(usesTemplate ? "default-template" : response.view, context);
    }
}
