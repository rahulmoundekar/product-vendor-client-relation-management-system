package com.vcr.app.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Document(collection = "products")
@ToString
public class Products {

	@Id
	private String id;
	private String description;
	private Boolean active;
	private Double pricing;
	private String short_description;
	private Long update_timestamp;

}
