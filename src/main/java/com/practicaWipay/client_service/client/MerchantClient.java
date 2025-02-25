package com.practicaWipay.client_service.client;

import com.practicaWipay.client_service.dto.MerchantDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "merchant-service", url = "http://localhost:8081/api/merchant")
public interface MerchantClient {

    @GetMapping("/{id}")
    MerchantDTO getMerchantById(@PathVariable("id") String id);
}
