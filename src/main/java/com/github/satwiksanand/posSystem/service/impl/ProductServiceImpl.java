package com.github.satwiksanand.posSystem.service.impl;

import com.github.satwiksanand.posSystem.exception.CategoryException;
import com.github.satwiksanand.posSystem.exception.ProductException;
import com.github.satwiksanand.posSystem.mapper.ProductMapper;
import com.github.satwiksanand.posSystem.models.Category;
import com.github.satwiksanand.posSystem.models.Product;
import com.github.satwiksanand.posSystem.models.Store;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.ProductDto;
import com.github.satwiksanand.posSystem.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              StoreRepository storeRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws ProductException, CategoryException {
        Store store = storeRepository.findById(productDto.getStoreId()).orElseThrow(
                ()->new ProductException("store not found!")
        );
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                ()->new CategoryException("category not found!")
        );
        Product product = ProductMapper.toEntity(productDto, store, category);
        return ProductMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws ProductException, CategoryException {
        Product product = productRepository.findById(id).orElseThrow(
                ()->new ProductException("Product not found!")
        );
        if(productDto.getCategoryId()!=null){
            Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                    ()->new CategoryException("category not found!")
            );
            if(category!=null){
                product.setCategory(category);
            }
        }
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
