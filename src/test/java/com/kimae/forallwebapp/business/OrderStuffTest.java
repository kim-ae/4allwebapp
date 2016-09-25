package com.kimae.forallwebapp.business;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import java.util.Random;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.kimae.forallwebapp.entity.Order;
import com.kimae.forallwebapp.entity.OrderItem;
import com.kimae.forallwebapp.repository.OrderRepository;

public class OrderStuffTest {

    @Mock
    private OrderRepository repository;
    
    @Mock
    private Random randomGenerator;
    
    @InjectMocks
    private OrderStuff orderStuff = new OrderStuff();
    
    private final OrderItem orderItem = new OrderItem().setPreco(0f).setQuantidade(1).setSku(1);
    
    
    @Before
    public void setup() throws UnsupportedCharsetException, ClientProtocolException, IOException{
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void successFulSave() throws OrderBusinessException{
        Mockito.when(repository.save((Order)Mockito.isNotNull())).thenReturn(true);
        Mockito.when(randomGenerator.nextBoolean()).thenReturn(true);
        orderStuff.setRandomGenerator(randomGenerator);
        Integer id = orderStuff.tryToOrder(Arrays.asList(orderItem));
        assertTrue(id != null);
    }
    @Test(expected=OrderBusinessException.class)
    public void randomDecline() throws OrderBusinessException{
        Mockito.when(repository.save((Order)Mockito.isNotNull())).thenReturn(true);
        Mockito.when(randomGenerator.nextBoolean()).thenReturn(false);
        orderStuff.setRandomGenerator(randomGenerator);
        orderStuff.tryToOrder(Arrays.asList(orderItem));
    }
    @Test(expected=OrderBusinessException.class)
    public void couldNotSave() throws OrderBusinessException{
        Mockito.when(repository.save((Order)Mockito.isNotNull())).thenReturn(false);
        Mockito.when(randomGenerator.nextBoolean()).thenReturn(true);
        orderStuff.setRandomGenerator(randomGenerator);
        orderStuff.tryToOrder(Arrays.asList(orderItem));
    }
    @Test(expected=OrderBusinessException.class)
    public void couldNotSaveAdnRandomDecline() throws OrderBusinessException{
        Mockito.when(repository.save((Order)Mockito.isNotNull())).thenReturn(false);
        Mockito.when(randomGenerator.nextBoolean()).thenReturn(false);
        orderStuff.setRandomGenerator(randomGenerator);
        orderStuff.tryToOrder(Arrays.asList(orderItem));
    }
}
