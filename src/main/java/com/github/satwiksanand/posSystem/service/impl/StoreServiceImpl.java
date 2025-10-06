package com.github.satwiksanand.posSystem.service.impl;

import com.github.satwiksanand.posSystem.domain.StoreStatus;
import com.github.satwiksanand.posSystem.exception.StoreException;
import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.mapper.StoreMapper;
import com.github.satwiksanand.posSystem.models.Store;
import com.github.satwiksanand.posSystem.models.StoreContact;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.StoreDto;
import com.github.satwiksanand.posSystem.repository.StoreRepository;
import com.github.satwiksanand.posSystem.service.StoreService;
import com.github.satwiksanand.posSystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final UserService userService;

    public StoreServiceImpl(StoreRepository storeRepository, UserService userService) {
        this.storeRepository = storeRepository;
        this.userService = userService;
    }

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store = StoreMapper.toEntity(storeDto, user);
        return StoreMapper.toDTO(storeRepository.save(store));
    }

    @Override
    public StoreDto getStoreById(Long id) throws StoreException {
        Store store = storeRepository.findById(id).orElseThrow(
                ()-> new StoreException("store not found!")
        );
        return StoreMapper.toDTO(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        List<Store> allStores = storeRepository.findAll();
        return allStores.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin = userService.getCurrentUser();
        return storeRepository.findByStoreAdminId(admin.getId());
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws UserException, StoreException {
        //i feel like there is something wrong here, because i am simply not checking if the existing store and id of the store are same or not.
        //in that case the error will be that an admin is trying to change the details of some other store.
        User currentUser = userService.getCurrentUser();
        Store existing = storeRepository.findByStoreAdminId(currentUser.getId());
        if(existing == null){
            throw new StoreException("store not found!");
        }
        if(storeDto.getBrand() != null){
            existing.setBrand(storeDto.getBrand());
        }
        if(storeDto.getDescription() != null){
            existing.setDescription(storeDto.getDescription());
        }
        if(storeDto.getStoreType() != null){
            existing.setStoreType(storeDto.getStoreType());
        }
        if(storeDto.getContact() != null){
            existing.setContact(StoreContact.builder()
                    .address(storeDto.getContact().getAddress())
                    .phone(storeDto.getContact().getPhone())
                    .email(storeDto.getContact().getEmail()).build());
        }
        return StoreMapper.toDTO(storeRepository.save(existing));
    }

    @Override
    public void deleteStore(Long id) throws UserException {
        //again what is the use of this id parameter, we are missing something.
        Store store = getStoreByAdmin();
        storeRepository.delete(store);
    }

    @Override
    public StoreDto getStoreByEmployee() throws UserException {//every user has to be related to at least one store.
        User currentUser = userService.getCurrentUser();
        if(currentUser == null){
            throw new UserException("you do not have permission to access this store!");
        }
        return StoreMapper.toDTO(currentUser.getStore());
    }

    @Override
    public StoreDto moderateStore(Long id, StoreStatus status) throws StoreException {
        Store store = storeRepository.findById(id).orElseThrow(
                ()-> new StoreException("store not found!")
        );
        store.setStatus(status);
        return StoreMapper.toDTO(storeRepository.save(store));
    }
}
