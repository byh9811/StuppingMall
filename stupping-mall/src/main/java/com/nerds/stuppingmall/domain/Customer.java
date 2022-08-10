package com.nerds.stuppingmall.domain;


import com.mongodb.lang.NonNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="members")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Customer extends Member {
	private Address address;
	private List<String> myPicks;
	private List<String> recentFinds;

	@AllArgsConstructor
	@Getter
	public static class Address {
		private String base;
		private String detail;
	}
}