package com.nerds.stuppingmall.dto;

import java.util.List;

import com.nerds.stuppingmall.enumerate.Usage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotebookResponseBasicDto {
	private String id;
	private String name;
}