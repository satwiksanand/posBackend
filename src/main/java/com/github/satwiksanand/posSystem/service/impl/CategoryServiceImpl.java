package com.github.satwiksanand.posSystem.service.impl;

import com.github.satwiksanand.posSystem.domain.UserRole;
import com.github.satwiksanand.posSystem.exception.CategoryException;
import com.github.satwiksanand.posSystem.exception.StoreException;
import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.mapper.CategoryMapper;
import com.github.satwiksanand.posSystem.models.Category;
import com.github.satwiksanand.posSystem.models.Store;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.CategoryDto;
import com.github.satwiksanand.posSystem.repository.CategoryRepository;
import com.github.satwiksanand.posSystem.repository.StoreRepository;
import com.github.satwiksanand.posSystem.service.CategoryService;
import com.github.satwiksanand.posSystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserService userService, StoreRepository storeRepository) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
        this.storeRepository = storeRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws UserException, StoreException {
        User user = userService.getCurrentUser();
        Store store = storeRepository.findById(user.getId()).orElseThrow(
                ()->new StoreException("store not found")
        );
        if(checkAuthority(user, store)){
            throw new UserException("You do not have the authority for this operation!");
        }
        Category category = Category.builder()
                .name(categoryDto.getName())
                .store(store)
                .build();
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getCategoriesByStore(Long storeId) {
        List<Category> allCategory = categoryRepository.findByStoreId(storeId);
        return allCategory.stream().map(CategoryMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws StoreException, CategoryException, UserException {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()->new CategoryException("category not found")
        );
        Store store = storeRepository.findById(categoryDto.getStoreId()).orElseThrow(
                ()->new StoreException("store not found!")
        );
        User user = userService.getCurrentUser();
        if(checkAuthority(user, category.getStore())){
            throw new UserException("You do not have the authority for this operation!");
        }
        category.setName(categoryDto.getName());
        category.setStore(store);
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws CategoryException, UserException {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()->new CategoryException("category not found")
        );
        User user = userService.getCurrentUser();
        if(checkAuthority(user, category.getStore())){
            throw new UserException("You do not have the authority for this operation!");
        }
        categoryRepository.delete(category);
    }

    private boolean checkAuthority(User user, Store store){
        boolean isAdmin = user.getRole().equals(UserRole.ADMIN);
        boolean isManager = user.getRole().equals(UserRole.STORE_MANAGER);
        boolean isSameStore = user.equals(store.getStoreAdmin());
        /*
        * if(!(isAdmin && isSameStore) && !isManager){
        * throw new Exception("you do not have the authority to do this stuff!");
        * }
        * */
        return (isAdmin && isSameStore) || isManager;
    }
}
