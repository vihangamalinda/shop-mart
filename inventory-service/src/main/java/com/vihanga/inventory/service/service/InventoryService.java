package com.vihanga.inventory.service.service;


import com.vihanga.inventory.service.dto.InventoryResponseDto;

import java.util.List;

public interface InventoryService {
    List<InventoryResponseDto> isInStock(List<String> skuCode);

}
