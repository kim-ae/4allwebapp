package com.kimae.forallwebapp.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

@Path("/static")
public class StaticFileController {
	
	@Context
	private ServletContext context;
	
    @Path("{filename}")
    @GET
    public InputStream getIndex(@PathParam("filename") String fileName){
	    File index = new File(context.getRealPath("/webapp/static/" + fileName));
	    try {
	        return new FileInputStream(index);
	    } catch (FileNotFoundException e) {
	        String s = "ERROR";
	        return new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
	    } 
    }
}
