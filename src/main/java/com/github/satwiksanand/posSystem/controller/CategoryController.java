package com.github.satwiksanand.posSystem.controller;

import com.github.satwiksanand.posSystem.exception.CategoryException;
import com.github.satwiksanand.posSystem.exception.StoreException;
import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.payload.dto.CategoryDto;
import com.github.satwiksanand.posSystem.payload.response.ApiResponse;
import com.github.satwiksanand.posSystem.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) throws UserException, StoreException {
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) throws UserException, StoreException, CategoryException {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) throws CategoryException, UserException {
        categoryService.deleteCategory(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("successfully deleted category!");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<CategoryDto>> getCategoryByStoreId(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoriesByStore(id));
    }
}
