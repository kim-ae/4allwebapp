package com.kimae.forallwebapp.controller;

import javax.ejb.Local;

import com.kimae.forallwebapp.configuration.ModelAndView;

@Local
public interface Controller {
    public ModelAndView home();
}
