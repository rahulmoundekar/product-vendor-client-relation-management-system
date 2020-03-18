package com.vcr.app.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "clients")
public class Clients {

	@Id
	private String id;

	@Field(name = "client_id")
	private Integer clientId;

	@Field(name = "name")
	private String name;

	@Field(name = "contact")
	private String contact;

	@Field(name = "active")
	private Boolean active;

	@Field(name = "update_timestamp")
	private Long updateTimestamp;

	private List<Products> productList;

}
