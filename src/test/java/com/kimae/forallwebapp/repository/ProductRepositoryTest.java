package com.kimae.forallwebapp.repository;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.kimae.forallwebapp.entity.Product;
import com.kimae.forallwebapp.entity.Products;
import com.kimae.forallwebapp.infrastructure.ForAllWebService;
import com.kimae.forallwebapp.infrastructure.MapFactory;
import com.kimae.forallwebapp.mock.ProductMockProvider;

public class ProductRepositoryTest {
    @Mock
    private ForAllWebService webService;
    @InjectMocks
    private final ProductRepository productRepository = new ProductRepository();
    private static final String GET_ALL_METHOD = "getProdutos";
    private static final Map<String, String> AUTH_KEY = MapFactory.createOf("authkey", "hello123");
    private final ProductMockProvider testEntities = new ProductMockProvider(4);
    
    @Before
    public void setup() throws UnsupportedCharsetException, ClientProtocolException, IOException{
        MockitoAnnotations.initMocks(this);
        Mockito.when(webService.call(GET_ALL_METHOD, Products.class, AUTH_KEY)).thenReturn(testEntities.getProducts());
    }
    
    @Test
    public void testFindAll(){
        List<Product> products = productRepository.findAll();
        assertEquals(testEntities.getProducts().getProdutos().size(), products.size());
        for(Product product : products){
            Product mockedProduct = testEntities.productMock(product.getSku());
            assertEquals(mockedProduct, product);
        }
    }
    
    @Test
    public void testFindById(){
        Product product = productRepository.findById(1);
        Product mockProduct = testEntities.productMock(1);
        assertTrue(product != null);
        assertEquals(mockProduct, product);
    }
}
