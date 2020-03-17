package com.vcr.app.repo.impl;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vcr.app.document.Clients;

public interface ClientRepository extends MongoRepository<Clients, String> {

}
