package com.vihanga.inventory.service.controller;

import com.vihanga.inventory.service.dto.InventoryResponseDto;
import com.vihanga.inventory.service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDto> isInStock(@RequestParam List<String> skuCode){
        System.out.println("SkuCode");
        System.out.println(skuCode);
       return    inventoryService.isInStock(skuCode);

    }
}
