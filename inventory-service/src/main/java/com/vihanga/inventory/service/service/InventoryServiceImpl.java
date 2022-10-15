package com.vihanga.inventory.service.service;

import com.vihanga.inventory.service.dto.InventoryResponseDto;
import com.vihanga.inventory.service.model.Inventory;
import com.vihanga.inventory.service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponseDto> isInStock(List<String> skuCode) {
        List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(skuCode);

             List<InventoryResponseDto> inventoryResponseDtoList =   inventoryList.stream()
                     .map(inventory ->
                             InventoryResponseDto.builder()
                                     .skuCode(inventory.getSkuCode())
                                     .isInStock(inventory.getQuantity() > 0)
                                     .build()
                ).collect(Collectors.toList());

        return  inventoryResponseDtoList;

    }
}
