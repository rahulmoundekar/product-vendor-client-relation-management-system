package com.vcr.app;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;

@SpringBootTest
class VcrApplicationTests {

	@Test
	void contextLoads() {

		RestAssured.baseURI = "http://localhost:8080/";
		given().contentType("application/json")
				.body("{\n" + "        \"description\": \"60 GB Unlimited\",\n" + "        \"active\": false,\n"
						+ "        \"pricing\": 150,\n" + "        \"short_description\": \"60 GB Plan\",\n"
						+ "        \"update_timestamp\": 1584429225246\n" + "}")
				.when().post("api/products").prettyPrint();

	}

	@Test
	public void doGet() {
		given().when().get("api/clients").prettyPeek();

	}

}
