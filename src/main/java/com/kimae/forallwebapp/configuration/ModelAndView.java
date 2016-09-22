package com.kimae.forallwebapp.configuration;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView{

    public final String view;
    public final Map<String, Object> model;
    
    private ModelAndView(String view, Map<String, Object> model){
        this.model = new HashMap<>();
        this.model.putAll(model);
        this.view = view;
    }
    
    public static ModelAndView getSimpleModelAndView(String view, Map<String, Object> model){
        return new ModelAndView(view, model);
    }
}
