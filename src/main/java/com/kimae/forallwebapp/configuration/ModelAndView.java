package com.kimae.forallwebapp.configuration;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView{

    private final String view;
    private final Map<String, Object> model;
    private final String template;
    
    private ModelAndView(String view, Map<String, Object> model, String template){
        this.model = new HashMap<>();
        this.model.putAll(model);
        this.view = view;
        this.template = template;
    }
    
    public static ModelAndView getSimpleModelAndView(String view, Map<String, Object> model){
        return new ModelAndView(view, model, null);
    }
    
    public static ModelAndView templatedModelAndView(String view, Map<String, Object> model, String template){
        return new ModelAndView(view, model, template);
    }
    
    public String getView(){
        return view;
    }
    
    public String getTemplate(){
        return template;
    }
    public Map<String, Object> getModel(){
        return new HashMap<>(model);
    }
    public boolean usesTemplate(){
        return template != null;
    }
}
