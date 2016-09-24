package com.kimae.forallwebapp.controller;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import com.kimae.forallwebapp.controller.templateengine.HtmlEngine;

@Local
public abstract class HtmlController {
    @Context
    private HttpServletRequest httpRequest;
    @Context
    private HttpServletResponse httpResponse;
    @EJB(beanName="ThymeleafEngine")
    private HtmlEngine htmlEngine;
    
    HttpServletRequest getRequest(){
        return httpRequest;
    }
    HttpServletResponse getResponse(){
        return httpResponse;
    }
    HtmlEngine getHtmlEngine(){
        return htmlEngine;
    }
}
