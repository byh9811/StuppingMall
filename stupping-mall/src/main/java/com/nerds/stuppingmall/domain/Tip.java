package com.nerds.stuppingmall.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="tips")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Tip {
	@Id
	private String _id;
	private String purpose;
	private String explanation1;
	private String explanation2;
	private String explanation3;
}
