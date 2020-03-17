package com.vcr.app.document;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "clients")
public class Clients {

	private String client_id;
	private String name;
	private String contact;
	private Boolean active;
	private Long update_timestamp;

}
