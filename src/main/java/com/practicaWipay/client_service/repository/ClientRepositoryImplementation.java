package com.practicaWipay.client_service.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.practicaWipay.client_service.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImplementation implements ClientRepository{

    private final DynamoDBMapper dynamoDBMapper;

    public void save(Client client) {
        dynamoDBMapper.save(client);
    }

    @Override
    public List<Client> findByValue(String id, String field) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val", new AttributeValue().withS(id));

        scanExpression.withFilterExpression(field + " = :val")
                .withExpressionAttributeValues(eav);

        return dynamoDBMapper.scan(Client.class, scanExpression);
    }

    @Override
    public List<Client> findAll() {
        return dynamoDBMapper.scan(Client.class, new DynamoDBScanExpression());
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return Optional.ofNullable(dynamoDBMapper.load(Client.class, email));
    }

}
