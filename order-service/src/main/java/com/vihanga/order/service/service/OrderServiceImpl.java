package com.vihanga.order.service.service;

import com.vihanga.order.service.dto.InventoryResponseDto;
import com.vihanga.order.service.dto.OrderLineItemDto;
import com.vihanga.order.service.dto.OrderRequest;
import com.vihanga.order.service.model.Order;
import com.vihanga.order.service.model.OrderLineItem;
import com.vihanga.order.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

//    @Value("${microservice.inventory.base.url}")
//    private String inventoryBaseUrl;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItemList = orderRequest.getOrderLineItemDtoList()
               .stream()
               .map(orderLineItemDto -> dtoToModel(orderLineItemDto))
               .collect(Collectors.toList());

        order.setOrderLineItemList(orderLineItemList);
        System.out.println(order.getOrderLineItemList());

        List<String> skuCodes = order.getOrderLineItemList()
                .stream()
                .map(orderLineItem -> orderLineItem.getSkuCode())
                .collect(Collectors.toList());

        // Calling the inventory microservice to identify whether the item is in stock
//        System.out.println(inventoryBaseUrl);
        InventoryResponseDto[] inventoryResponseDtoArr =  webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponseDto[].class)
              .block();

       Boolean isPresent = Arrays.stream(inventoryResponseDtoArr).allMatch(inventoryResponseDto -> inventoryResponseDto.getIsInStock());

      if(isPresent){
          orderRepository.save(order);
      }else {
          throw new IllegalArgumentException("The requested items are out of stock. Please try again later.");
      }




    }

    private OrderLineItem dtoToModel(OrderLineItemDto orderLineItemDto) {
    OrderLineItem orderLineItem = new OrderLineItem();
    orderLineItem.setPrice(orderLineItemDto.getPrice());
    orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
    orderLineItem.setQuantity(orderLineItemDto.getQuantity());

        System.out.println(orderLineItem);
    return orderLineItem;
    }
}
