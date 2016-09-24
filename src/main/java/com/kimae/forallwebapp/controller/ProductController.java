package com.kimae.forallwebapp.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.kimae.forallwebapp.configuration.ModelAndView;
import com.kimae.forallwebapp.infrastructure.MapFactory;
import com.kimae.forallwebapp.repository.ProductRepository;

@Path("products")
public class ProductController extends HtmlController {

    @Inject
    private ProductRepository productRepository;
    
    @GET
    @Path("/get-all")
    @Produces(MediaType.TEXT_HTML)
    public String getProducts(){
        Map<String, Object> model = MapFactory.createOf("products", (Object)productRepository.findAll());
        try {
            return getHtmlEngine().toHtml(ModelAndView.getSimpleModelAndView("product-list", model), getRequest(), getResponse());
        } catch (WebApplicationException e) {
            return "vish";
        }
    }
}
