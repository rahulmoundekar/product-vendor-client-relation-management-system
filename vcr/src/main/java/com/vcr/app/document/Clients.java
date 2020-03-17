package com.vcr.app.document;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "clients")
public class Clients {

	private Integer client_id;
	private String name;
	private String contact;
	private Boolean active;
	private Long update_timestamp;

	// @DBRef(db = "products")
	private Set<Products> productList;

}
