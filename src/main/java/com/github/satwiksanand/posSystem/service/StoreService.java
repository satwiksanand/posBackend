package com.github.satwiksanand.posSystem.service;

import com.github.satwiksanand.posSystem.domain.StoreStatus;
import com.github.satwiksanand.posSystem.exception.StoreException;
import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.models.Store;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.StoreDto;

import java.util.List;

public interface StoreService {
    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long id) throws StoreException;
    List<StoreDto> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDto updateStore(Long id, StoreDto storeDto) throws UserException, StoreException;
    void deleteStore(Long id) throws UserException;
    StoreDto getStoreByEmployee() throws UserException;
    StoreDto moderateStore(Long id, StoreStatus status) throws StoreException;
}