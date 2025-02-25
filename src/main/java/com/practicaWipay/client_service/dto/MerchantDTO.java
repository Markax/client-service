package com.practicaWipay.client_service.dto;

import com.practicaWipay.client_service.model.MerchantType;
import lombok.Data;

@Data
public class MerchantDTO {
    private String id;
    private String name;
    private String address;
    private MerchantType merchantType;
}
