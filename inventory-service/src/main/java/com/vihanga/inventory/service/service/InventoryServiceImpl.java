package com.vihanga.inventory.service.service;

import com.vihanga.inventory.service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
       boolean isPresent = inventoryRepository.findBySkuCode(skuCode).isPresent();
        return isPresent;
    }
}
