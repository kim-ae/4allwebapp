package com.kimae.forallwebapp;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kimae.forallwebapp.configuration.ThymeleafConfiguration;
import com.kimae.forallwebapp.controller.OrderController;

@ApplicationPath("/")
public class Forallwebapp extends Application {
    
    private Set<Object> singletons = new HashSet<Object>();
    public Forallwebapp() {
        singletons.add(new ThymeleafConfiguration());
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> defaults = super.getSingletons();
        singletons.addAll(defaults);
        return singletons;
    }
    
    public Set<Class<?>> getClasses() {        
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(OrderController.class);
        
        return s;
    }
}
