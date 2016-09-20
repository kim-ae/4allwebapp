package com.kimae.forallwebapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.kimae.forallwebapp.configuration.ModelAndView;

@Path("/order")
@RequestScoped
public class OrderController {

    @GET
    public ModelAndView home(){
        Map<String, Object> model = new HashMap<>();
        model.put("test", "kim :)");
        return ModelAndView.getHtmlResponse("order", model);
    }
}
