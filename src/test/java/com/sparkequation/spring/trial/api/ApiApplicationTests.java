package com.sparkequation.spring.trial.api;

import com.google.gson.Gson;
import com.sparkequation.spring.trial.api.controller.ProductController;
import com.sparkequation.spring.trial.api.model.Brand;
import com.sparkequation.spring.trial.api.model.Category;
import com.sparkequation.spring.trial.api.model.Product;
import com.sparkequation.spring.trial.api.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
class ApiApplicationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService service;

    @Test
    void contextLoads() {
    }

    @Test
    public void givenProducts_whenGetProducts_thenReturnJsonArray()
            throws Exception {

        Category category = new Category("newcategory");
        Brand brand = new Brand("newbrand", "Russia");
        Product product = new Product("newproduct", false, null, 0, null, 2.0, brand, Set.of(category));

        List<Product> allProducts = Arrays.asList(product);

        BDDMockito.given(service.GetProducts()).willReturn(allProducts);

        mvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(product.getName())));
    }

    @Test
    public void givenProducts_whenPostNewProduct_thenOk()
            throws Exception {
        Gson gson = new Gson();
        Category category = new Category("Drinks");
        Brand brand = new Brand("Water", "Russia");
        Product product = new Product("Crystal Water", false, null, 0, null, 2.0, brand, Set.of(category));
        String request = gson.toJson(product);

        mvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }

    @Test
    public void givenProducts_whenPostNewProductWrongValid_thenBadRequest()
            throws Exception {
        Gson gson = new Gson();
        Category category = new Category("Drinks");
        Brand brand = new Brand("Water", "Russia");
        Product product = new Product("Crystal Water", false, new SimpleDateFormat("dd/MM/yyyy").parse("12/01/2009"), 0, null, 2.0, brand, Set.of(category));
        String request = gson.toJson(product);

        mvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isBadRequest());
    }

}
