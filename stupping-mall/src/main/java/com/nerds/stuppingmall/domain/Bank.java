package com.nerds.stuppingmall.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="banks")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Bank {
	@Id
	private String _id;
	private String name;
}
