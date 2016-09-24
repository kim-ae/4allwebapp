package com.kimae.forallwebapp.controller.templateengine;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kimae.forallwebapp.configuration.ModelAndView;

@Local
public interface HtmlEngine {
    public String toHtml(ModelAndView view, HttpServletRequest httpRequest, HttpServletResponse httpResponse);
}
