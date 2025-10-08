package com.github.satwiksanand.posSystem.service.impl;

import com.github.satwiksanand.posSystem.exception.ProductException;
import com.github.satwiksanand.posSystem.mapper.ProductMapper;
import com.github.satwiksanand.posSystem.models.Product;
import com.github.satwiksanand.posSystem.models.Store;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.ProductDto;
import com.github.satwiksanand.posSystem.repository.ProductRepository;
import com.github.satwiksanand.posSystem.repository.StoreRepository;
import com.github.satwiksanand.posSystem.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws ProductException {
        Store store = storeRepository.findById(productDto.getStoreId()).orElseThrow(
                ()->new ProductException("store not found!")
        );
        Product product = ProductMapper.toEntity(productDto, store);
        return ProductMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws ProductException {
        Product product = productRepository.findById(id).orElseThrow(
                ()->new ProductException("Product not found!")
        );
        product.setBrand(productDto.getBrand());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setImageUrl(productDto.getImageUrl());
        product.setMrp(productDto.getMrp());
        product.setSellingPrice(productDto.getSellingPrice());
        product.setName(productDto.getName());
        return ProductMapper.toDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id, User user) throws ProductException {
        Product product = productRepository.findById(id).orElseThrow(
                ()->new ProductException("Product not found")
        );
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> getProductsByStoreId(Long id) {
        List<Product> productList = productRepository.findByStoreId(id);
        return productList.stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByKeyword(Long id, String keyword) {
        List<Product> allProducts = productRepository.searchByKeyword(id, keyword);
        return allProducts.stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }
}
