package com.github.satwiksanand.posSystem.service;

import com.github.satwiksanand.posSystem.exception.ProductException;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto, User user) throws Exception;
    ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception;
    void deleteProduct(Long id, User user) throws ProductException;
    List<ProductDto> getProductsByStoreId(Long id);
    List<ProductDto> searchByKeyword(Long id, String keyword);
}
