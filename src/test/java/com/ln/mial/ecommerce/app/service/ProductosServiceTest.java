package com.ln.mial.ecommerce.app.service;

import com.ln.mial.ecommerce.app.repository.ProductosRepository;
import com.ln.mial.ecommerce.infraestructure.entity.ProductosEntity;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductosServiceTest {

    @Mock
    private ProductosRepository productosRepository;

    @Mock
    private UploadFile uploadFile;

    @Mock
    private HttpSession session;

    private ProductosService productosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productosService = new ProductosService(productosRepository, uploadFile);
    }

    @Test
    void getProducts_ReturnList() {

        when(productosRepository.getProducts())
                .thenReturn(List.of(new ProductosEntity()));

        List<ProductosEntity> result =
                productosService.getProducts();

        assertEquals(1, result.size());
    }

    @Test
    void getProductById_ReturnProduct() {

        ProductosEntity product = new ProductosEntity();
        product.setId(1);

        when(productosRepository.getProductById(1))
                .thenReturn(product);

        ProductosEntity result =
                productosService.getProductById(1);

        assertEquals(1, result.getId());
    }

    @Test
    void saveProduct_NewProduct() throws IOException {

        ProductosEntity product = new ProductosEntity();

        MockMultipartFile file =
                new MockMultipartFile(
                        "file",
                        "image.jpg",
                        "image/jpeg",
                        "test".getBytes());

        when(session.getAttribute("iduser"))
                .thenReturn("1");

        when(uploadFile.upload(file))
                .thenReturn("image.jpg");

        when(productosRepository.saveProduct(any()))
                .thenAnswer(i -> i.getArgument(0));

        ProductosEntity result =
                productosService.saveProduct(
                        product,
                        file,
                        session
                );

        assertNotNull(result);
        assertEquals("image.jpg", result.getImage());
    }

    @Test
    void deleteProductById_ReturnTrue() {

        when(productosRepository.deleteProductById(1))
                .thenReturn(true);

        assertTrue(productosService.deleteProductById(1));
    }
}