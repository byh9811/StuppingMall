package com.nerds.stuppingmall.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="members")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Supplier extends Member {
    private String companyRegistrationNumber;
    private String contactPerson;
}