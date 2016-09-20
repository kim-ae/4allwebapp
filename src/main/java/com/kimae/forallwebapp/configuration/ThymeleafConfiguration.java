package com.kimae.forallwebapp.configuration;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map.Entry;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Provider
@Produces(MediaType.TEXT_HTML)
public class ThymeleafConfiguration implements MessageBodyWriter<ModelAndView> {
    private static Logger logger = LoggerFactory.getLogger(ThymeleafConfiguration.class);
    
    private static TemplateEngine engine;
    static{
        TemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("layout/");
        resolver.setSuffix(".html");
        resolver.setCacheable(false);
        engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
    }

    @Override
    public long getSize(ModelAndView arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] arg2, MediaType arg3) {
        return ModelAndView.class.isAssignableFrom(type);
    }

    @Override
    public void writeTo(ModelAndView response, Class<?> type, Type genericType, Annotation[] arg3, MediaType arg4,
            MultivaluedMap<String, Object> arg5, OutputStream entityStream) throws IOException, WebApplicationException {
        Context context = new Context();
        for(Entry<String, Object> b : response.model.entrySet()){
            context.setVariable(b.getKey(), b.getValue());
        }
        context.setVariable("view", response.view);
        Writer writer = new OutputStreamWriter(entityStream);
        engine.process("default-template", context, writer);
        writer.flush();
    }
}
