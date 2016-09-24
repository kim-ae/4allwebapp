package com.kimae.forallwebapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.kimae.forallwebapp.business.OrderStuff;
import com.kimae.forallwebapp.configuration.ModelAndView;
import com.kimae.forallwebapp.entity.OrderItem;

@Path("/order")
public class OrderController extends HtmlController {
    
    @EJB
    private  OrderStuff orderBusiness;
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String home(){
        Map<String, Object> model = new HashMap<>();
        try {
            return getHtmlEngine().toHtml(ModelAndView.templatedModelAndView("order", model, "default-template"), getRequest(), getResponse());
        } catch (WebApplicationException e) {
            return "vish";
        }
    }
    
    @POST
    @Path("/try-to-order")
    @Consumes(MediaType.APPLICATION_JSON)
    public void tryToOrder(List<OrderItem> itens){
        orderBusiness.tryToOrder(itens);
    }
}
