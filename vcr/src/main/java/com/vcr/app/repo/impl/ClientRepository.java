package com.vcr.app.repo.impl;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.vcr.app.document.Clients;

public interface ClientRepository extends MongoRepository<Clients, String> {

	@Query(value = "{'productList' : {$gt : ?0}}")
	public List<Clients> findAllWhereProudctListGraterThan(Integer more);

}
