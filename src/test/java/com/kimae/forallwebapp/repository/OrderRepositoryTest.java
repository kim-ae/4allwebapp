package com.kimae.forallwebapp.repository;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.kimae.forallwebapp.entity.Order;
import com.kimae.forallwebapp.entity.OrderSaveStatus;
import com.kimae.forallwebapp.infrastructure.ForAllWebService;
import com.kimae.forallwebapp.mock.OrderMockProvider;
import com.kimae.forallwebapp.mock.OrderSaveStatusMock;

public class OrderRepositoryTest {
    @Mock
    private ForAllWebService webService;
    @InjectMocks
    private final OrderRepository orderRepository = new OrderRepository();
    private static final String SAVE_METHOD = "setPedido";
    private final Order goodOrder = new OrderMockProvider().getOrder();
    private final Order badOrder = new OrderMockProvider().getOrder();
    
    @Before
    public void setup() throws UnsupportedCharsetException, ClientProtocolException, IOException{
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void saveGoodOrder() throws UnsupportedCharsetException, ClientProtocolException, IOException{
        Mockito.when(webService.call(SAVE_METHOD, OrderSaveStatus.class, goodOrder)).thenReturn(OrderSaveStatusMock.okStatus());
        assertTrue(orderRepository.save(goodOrder));
    }
    
    @Test
    public void saveBadOrder() throws UnsupportedCharsetException, ClientProtocolException, IOException{
        Mockito.when(webService.call(SAVE_METHOD, OrderSaveStatus.class, badOrder)).thenReturn(OrderSaveStatusMock.errorStatus());
        assertFalse(orderRepository.save(badOrder));
    }
    
    @Test
    public void findById() throws UnsupportedCharsetException, ClientProtocolException, IOException{
        Mockito.when(webService.call(SAVE_METHOD, OrderSaveStatus.class, goodOrder)).thenReturn(OrderSaveStatusMock.okStatus());
        orderRepository.save(goodOrder);
        assertTrue(orderRepository.findById(goodOrder.getId_pedido()) != null);
    }
    
    
    @Test
    @SuppressWarnings("unchecked")
    public void webserviceFailsIOException() throws ClientProtocolException, IOException{
        Mockito.when(webService.call(SAVE_METHOD, OrderSaveStatus.class, badOrder)).thenThrow(IOException.class);
        assertFalse(orderRepository.save(badOrder));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void webserviceFailsClientProtocolException() throws ClientProtocolException, IOException{
        Mockito.when(webService.call(SAVE_METHOD, OrderSaveStatus.class, badOrder)).thenThrow(ClientProtocolException.class);
        assertFalse(orderRepository.save(badOrder));
    }
}
