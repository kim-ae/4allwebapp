package com.kimae.forallwebapp;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kimae.forallwebapp.controller.OrderController;
import com.kimae.forallwebapp.controller.ProductController;
import com.kimae.forallwebapp.controller.StaticFileController;

@ApplicationPath("/")
public class Forallwebapp extends Application {
    
    public Set<Class<?>> getClasses() {        
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(OrderController.class);
        s.add(StaticFileController.class);
        s.add(ProductController.class);
        return s;
    }
}
