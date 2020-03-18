package com.vcr.app.repo.impl;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vcr.app.document.Vendors;

public interface VendorRepository extends MongoRepository<Vendors, String> {

}
