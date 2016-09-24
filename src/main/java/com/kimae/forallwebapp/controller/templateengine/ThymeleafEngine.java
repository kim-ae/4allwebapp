package com.kimae.forallwebapp.controller.templateengine;

import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.kimae.forallwebapp.configuration.ModelAndView;

@Stateless
public class ThymeleafEngine implements HtmlEngine {
    private static Logger logger = LoggerFactory.getLogger(ThymeleafEngine.class);
    private TemplateEngine engine;
    
    @PostConstruct
    public void initializeEngine(){
        TemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setPrefix("/layout/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(false);
        engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        logger.info("Thymeleaf template engine initialized");
    }

    @Override
    public String toHtml(ModelAndView view, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        WebContext context = new WebContext(httpRequest, httpResponse, httpRequest.getServletContext());
        for(Entry<String, Object> b : view.getModel().entrySet()){
            context.setVariable(b.getKey(), b.getValue());
        }
        if(view.usesTemplate()){
            context.setVariable("view", view.getView());
        }
        return engine.process(view.usesTemplate() ? view.getTemplate(): view.getView(), context);
    }

}
