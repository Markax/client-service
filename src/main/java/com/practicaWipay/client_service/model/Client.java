package com.practicaWipay.client_service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "MainTable")
public class Client extends MainTable {

    @Override
    @DynamoDBHashKey(attributeName = "PK")
    public String getPK(){
        return super.getPK();
    }

    @DynamoDBAttribute
    private String name;
    @DynamoDBAttribute
    private String surname;
    @DynamoDBAttribute
    private String cifNifNie;
    @DynamoDBAttribute
    private String phone;
    @DynamoDBAttribute
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid email format")
    private String email;
}
