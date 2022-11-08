package com.gdu.app10.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class BoardDTO {

	private int boardNo;
	private String title, content, writer, createDate, modifyDate; 

	
	
}
