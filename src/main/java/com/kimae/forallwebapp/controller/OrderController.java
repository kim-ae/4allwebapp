package com.kimae.forallwebapp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.kimae.forallwebapp.configuration.ModelAndView;
import com.kimae.forallwebapp.configuration.ThymeleafProcessor;

@Path("/order")
public class OrderController extends Controller {
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String home(){
        Map<String, Object> model = new HashMap<>();
        try {
            return toHtml(ModelAndView.getSimpleModelAndView("order", model), true);
        } catch (WebApplicationException | IOException e) {
            return "vish";
        }
    }
    

}
