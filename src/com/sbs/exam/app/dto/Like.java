package com.sbs.exam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Like {
	private int id;
	private String regDate;
	private String updateDate;
	private int articleId;
	private int memberId;
	private int like = 0;
	private int disLike = 0;
	
}
