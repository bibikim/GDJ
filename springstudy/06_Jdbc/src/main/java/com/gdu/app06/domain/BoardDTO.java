package com.gdu.app06.domain;

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

	private int board_no;
	private String title, content, writer, create_date, modify_date; 

	
	
}
