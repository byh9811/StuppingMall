package com.nerds.stuppingmall.domain;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="knowhows")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Knowhow {
	@Id
	private String _id;
	private String purpose;
	private String explanation1;
	private String explanation2;
	private String explanation3;
}
