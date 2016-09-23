package com.kimae.forallwebapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.kimae.forallwebapp.configuration.ModelAndView;
import com.kimae.forallwebapp.infrastructure.MapFactory;
import com.kimae.forallwebapp.infrastructure.WebService;
import com.kimae.forallwebapp.repository.ProductRepository;

@Path("/order")
public class OrderController implements Controller {
    
    @Inject
    private ProductRepository productRepository;
    
    @GET
    @Override
    public ModelAndView home(){
        Map<String, Object> model = new HashMap<>();
        model.put("test", "kim :)");
        return ModelAndView.getSimpleModelAndView("order", model);
    }
    
    @GET
    @Path("/products")
    public ModelAndView getProducts(){
        return ModelAndView.getSimpleModelAndView("", MapFactory.createOf("products", (Object)productRepository.findAll()));
    }
}
