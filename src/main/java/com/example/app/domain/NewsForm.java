package com.example.app.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NewsForm {
	//News
	@NotBlank
	private String title;
	private String author;
	private Date postDate = Date.valueOf(LocalDate.now());
	
	//NewsDetail
	@NotBlank
	private String article;
	
	//お知らせの対象リスト（会員種別のIDリスト）
	private List<Integer> targetIdList;
}
