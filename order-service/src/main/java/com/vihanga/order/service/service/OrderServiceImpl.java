package com.vihanga.order.service.service;

import com.vihanga.order.service.dto.OrderLineItemDto;
import com.vihanga.order.service.dto.OrderRequest;
import com.vihanga.order.service.model.Order;
import com.vihanga.order.service.model.OrderLineItem;
import com.vihanga.order.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItemList = orderRequest.getOrderLineItemDtoList()
               .stream()
               .map(orderLineItemDto -> dtoToModel(orderLineItemDto))
               .collect(Collectors.toList());

        order.setOrderLineItemList(orderLineItemList);

        orderRepository.save(order);


    }

    private OrderLineItem dtoToModel(OrderLineItemDto orderLineItemDto) {
    OrderLineItem orderLineItem = new OrderLineItem();
    orderLineItem.setPrice(orderLineItemDto.getPrice());
    orderLineItem.setSkuCode(orderLineItem.getSkuCode());
    orderLineItem.setQuantity(orderLineItemDto.getQuantity());

    return orderLineItem;
    }
}
