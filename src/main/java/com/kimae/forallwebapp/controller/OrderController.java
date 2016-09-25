package com.kimae.forallwebapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.kimae.forallwebapp.business.OrderBusinessException;
import com.kimae.forallwebapp.business.OrderStuff;
import com.kimae.forallwebapp.configuration.ModelAndView;
import com.kimae.forallwebapp.controller.model.ViewItem;
import com.kimae.forallwebapp.entity.Order;
import com.kimae.forallwebapp.entity.OrderItem;
import com.kimae.forallwebapp.repository.ProductRepository;
import com.kimae.forallwebapp.utils.DataFormatter;
import com.kimae.forallwebapp.utils.ViewFormatter;

@Path("/order")
public class OrderController extends HtmlController {
    
    @EJB
    private  OrderStuff orderBusiness;
    
    @Inject
    private ProductRepository productRepository;
    
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
    public Response tryToOrder(List<OrderItem> itens) {
        if(itens == null || itens.isEmpty()){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Por favor selecione pelo menos 1 item. E por favor não desligue o javascript, ele é seu amiguinho").build();
        }
        Integer orderId = null;
        try {
            orderId = orderBusiness.tryToOrder(itens);
        } catch (OrderBusinessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        return Response.ok().entity(orderId).build();
    }
    
    @GET
    @Path("/success/{order_id}")
    public String success(@PathParam("order_id") Integer orderId){
        Order order = orderBusiness.getOrder(orderId);
        Map<String, Object> model = new HashMap<>();
        DataFormatter formatter = new ViewFormatter();
        model.put("timeToDeliver", formatter.formatDate(order.getRawDeliveryDate()));
        List<ViewItem> itens = new ArrayList<>();
        for(OrderItem item : order.getItens()){
            itens.add(ViewItem.convertFrom(productRepository.findById(item.getSku()), item));
        }
        model.put("itens", itens);
        model.put("total", order.getTotal());
        return getHtmlEngine().toHtml(ModelAndView.templatedModelAndView("success", model, "default-template"), getRequest(), getResponse());
    }
}
