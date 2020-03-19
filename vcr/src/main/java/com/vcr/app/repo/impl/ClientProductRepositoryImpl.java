package com.vcr.app.repo.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.vcr.app.document.Clients;
import com.vcr.app.document.Products;
import com.vcr.app.document.Vendors;
import com.vcr.app.dto.ClientProduct;
import com.vcr.app.dto.VendorProductDto;

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

	@Override
	public List<Clients> find_all_client_which_takes_more_than_more_product() {
		/*
		 * Query query = new Query();
		 * query.addCriteria(Criteria.where("this.productList.length >= 3"));
		 */
		Query query = Query.query(Criteria.where("productList").size(3));
		List<Clients> list = mongoTemplate.find(query, Clients.class);
		return list;
	}

	public List<Vendors> find_the_three_highest_amount_for_each_vendors() {
		Query query = Query.query(Criteria.where("productList").size(3));
		List<Vendors> list = mongoTemplate.find(query, Vendors.class);
		return list;
	}

	@Override
	public List<VendorProductDto> find_the_highest_amount_vendors_product() {
		Aggregation agg = newAggregation(unwind("productList"),
				project("vendorName", "productList.productName", "productList.pricing"),
				sort(Sort.Direction.ASC, "pricing"),
				group("vendorName").first("vendorName").as("vendorName").last("productName").as("productName").last("pricing").as("pricing"));

		// Convert the aggregation result into a List
		AggregationResults<VendorProductDto> groupResults = mongoTemplate.aggregate(agg, Vendors.class,
				VendorProductDto.class);
		List<VendorProductDto> result = groupResults.getMappedResults();
		System.out.println(result);
		return result;
	}

	public Integer find_the_highest_amount_product() {

		Query query = new Query();
		query.skip(-1);
		query.limit(1);
		SortOperation sortOperation = sort(Direction.DESC, "s");
		mongoTemplate.find(query, Products.class);
		return null;
	}

}
