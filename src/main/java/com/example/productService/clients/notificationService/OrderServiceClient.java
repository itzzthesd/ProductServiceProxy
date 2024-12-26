package com.example.productService.clients.notificationService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order-service", url = "http://localhost:8081/orders/product")
@Service
public interface OrderServiceClient {
    @PostMapping
    ResponseEntity<ProductRequestDto> getProduct(@RequestBody ProductRequestDto productRequestDto);

}
