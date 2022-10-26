package com.nerds.stuppingmall.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="reports")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Report {
	private String reporterID;
	private String reportedID;
	private String reason;
}
