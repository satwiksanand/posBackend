package com.github.satwiksanand.posSystem.service;

import com.github.satwiksanand.posSystem.exception.CategoryException;
import com.github.satwiksanand.posSystem.exception.StoreException;
import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto) throws UserException, StoreException;
    List<CategoryDto> getCategoriesByStore(Long storeId);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws StoreException, CategoryException, UserException;
    void deleteCategory(Long id) throws CategoryException, UserException;
}
