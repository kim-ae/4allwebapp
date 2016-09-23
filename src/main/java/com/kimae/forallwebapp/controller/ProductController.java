package com.kimae.forallwebapp.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.kimae.forallwebapp.configuration.ModelAndView;
import com.kimae.forallwebapp.configuration.ThymeleafProcessor;
import com.kimae.forallwebapp.infrastructure.MapFactory;
import com.kimae.forallwebapp.repository.ProductRepository;

@Path("product")
public class ProductController extends Controller {

    @Inject
    private ProductRepository productRepository;
    
    @GET
    @Path("/get-all")
    @Produces(MediaType.TEXT_HTML)
    public String getProducts(){
        try {
            return toHtml(ModelAndView.getSimpleModelAndView("product-list", MapFactory.createOf("products", (Object)productRepository.findAll())), false);
        } catch (WebApplicationException | IOException e) {
            return "vish";
        }
    }
}
