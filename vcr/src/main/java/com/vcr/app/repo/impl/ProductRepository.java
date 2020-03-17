package com.vcr.app.repo.impl;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.vcr.app.document.Products;

public interface ProductRepository extends MongoRepository<Products, String> {

}
