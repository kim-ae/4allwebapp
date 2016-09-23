package com.kimae.forallwebapp;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kimae.forallwebapp.configuration.ThymeleafProcessor;
import com.kimae.forallwebapp.controller.OrderController;
import com.kimae.forallwebapp.controller.StaticFileController;

@ApplicationPath("/")
public class Forallwebapp extends Application {
    
    private Set<Object> singletons = new HashSet<Object>();
    public Forallwebapp() {
        singletons.add(new ThymeleafProcessor());
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
        s.add(StaticFileController.class);
        return s;
    }
}
