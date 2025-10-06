package com.github.satwiksanand.posSystem.mapper;

import com.github.satwiksanand.posSystem.models.Store;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.StoreDto;

public class StoreMapper {
    public static StoreDto toDTO(Store store){
        return StoreDto.builder()
                .id(store.getId())
                .brand(store.getBrand())
                .storeAdmin(UserMapper.toDTO(store.getStoreAdmin()))
                .createdAt(store.getCreatedAt())
                .updatedAt(store.getUpdatedAt())
                .description(store.getDescription())
                .storeType(store.getStoreType())
                .status(store.getStatus())
                .contact(store.getContact())
                .build();
    }

    public static Store toEntity(StoreDto storeDto, User user){
        return Store.builder()
                .id(storeDto.getId())
                .brand(storeDto.getBrand())
                .storeAdmin(user)
                .createdAt(storeDto.getCreatedAt())
                .updatedAt(storeDto.getUpdatedAt())
                .description(storeDto.getDescription())
                .storeType(storeDto.getStoreType())
                .status(storeDto.getStatus())
                .contact(storeDto.getContact())
                .build();
    }
}
