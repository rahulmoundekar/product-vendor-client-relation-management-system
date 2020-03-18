package com.vcr.app.repo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import com.vcr.app.document.Clients;
import com.vcr.app.dto.ClientProduct;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Repository
public class ClientProductRepositoryImpl implements ClientProductRepositoryCustom {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public ClientProductRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<ClientProduct> find_the_sum_of_amount_each_product_sold_to_the_client() {
		Aggregation agg = newAggregation(unwind("productList"),
				group("productList.productName").count().as("count").sum("productList.pricing").as("totalAmount"));

		// Convert the aggregation result into a List
		AggregationResults<ClientProduct> groupResults = mongoTemplate.aggregate(agg, Clients.class,
				ClientProduct.class);
		List<ClientProduct> result = groupResults.getMappedResults();

		return result;
	}

}
