package com.nerds.stuppingmall.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Usage {
	GAMING("GAMING", "게이밍"), DOCUMENT("DOCUMENT", "문서작업"), 
	HIGHEND("HIGHEND", "하이엔드"), PERFORMANCE("PERFORMANCE", "퍼포먼스");
	
	private String value;
	private String display;
}
