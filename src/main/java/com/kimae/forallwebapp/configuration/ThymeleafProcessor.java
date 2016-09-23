package com.kimae.forallwebapp.configuration;

import java.io.IOException;
import java.util.Map.Entry;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@RequestScoped
public class ThymeleafProcessor {
    //private static Logger logger = LoggerFactory.getLogger(ThymeleafConfiguration.class);
    @Context
    private HttpServletRequest httpRequest;
    @Context
    private HttpServletResponse httpResponse;
    
    private static TemplateEngine engine;
    public ThymeleafProcessor(){
        TemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setPrefix("layout/");
        resolver.setSuffix(".html");
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
