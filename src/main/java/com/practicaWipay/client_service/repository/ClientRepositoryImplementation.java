package com.practicaWipay.client_service.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.practicaWipay.client_service.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImplementation implements ClientRepository{

    private final DynamoDBMapper dynamoDBMapper;

    public void save(Object client) {
        dynamoDBMapper.save(client);
    }

    @Override
    public Optional<Client> findById(Object id) {
        return Optional.ofNullable(dynamoDBMapper.load(Client.class, id));
    }

    @Override
    public List<Client> findAll() {
        return dynamoDBMapper.scan(Client.class, new DynamoDBScanExpression());
    }

    @Override
    public Optional<Client> findByEmail(Object email) {
        return Optional.ofNullable(dynamoDBMapper.load(Client.class, email));
    }

}
