package com.vcr.app.document;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Vendors {

	@Id
	private String id;

	@Field(name = "vendorId")
	private Integer vendor_id;

	@Field(name = "vendor_name")
	private String vendorName;

	@Field(name = "update_timestamp")
	private Long updateTimestamp;

	@Field(name = "active")
	private Boolean active;

	private List<Products> productList;

}
