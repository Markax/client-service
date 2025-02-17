package com.practicaWipay.client_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainTable {
    private String PK;
    private String SK;
    private String id;
    private String status;
    private String gIndex2Pk;
    private LocalDateTime createdDate;


}
