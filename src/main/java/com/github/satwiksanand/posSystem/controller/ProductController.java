package com.github.satwiksanand.posSystem.controller;

import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.ProductDto;
import com.github.satwiksanand.posSystem.service.ProductService;
import com.github.satwiksanand.posSystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    public ProductController(ProductService productService, UserService userService){
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(productService.createProduct(productDto, user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(productService.updateProduct(id, productDto, user));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);
        productService.deleteProduct(id, user);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<ProductDto>> getProductsByStoreId(@PathVariable Long id, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok(productService.getProductsByStoreId(id));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<List<ProductDto>> searchByKeyword(@PathVariable Long id, @RequestParam String keyword, @RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok(productService.searchByKeyword(id, keyword));
    }
}
