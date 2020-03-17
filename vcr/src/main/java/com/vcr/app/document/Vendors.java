package com.vcr.app.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Vendors {

	private String id;
	private String vendor_name;
	private Long update_timestamp;
	private Boolean active;

}
