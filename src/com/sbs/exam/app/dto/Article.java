package com.sbs.exam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article implements Comparable<Article> {
	private int id;
	private String regDate;
	private String updateDate;
	private int boardId;
	private int memberId;
	private String title;
	private String body;
	private int hit;
	private int like;
	private int disLike;
	private String keyword;

	@Override
	public int compareTo(Article a1) {
		return (a1.id - this.id);
	}
}
