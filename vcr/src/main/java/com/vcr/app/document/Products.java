package com.vcr.app.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "products")
public class Products {

	@Id
	private String id;

	@Field(name = "product_id")
	private Integer productId;

	@Field(name = "product_name")
	private String productName;

	@Field(name = "description")
	private String description;

	@Field(name = "active")
	private Boolean active;

	@Field(name = "pricing")
	private Double pricing;

	@Field(name = "update_timestamp")
	private Long updateTimestamp;

}
