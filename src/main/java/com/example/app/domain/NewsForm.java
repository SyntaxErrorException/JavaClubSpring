package com.example.app.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NewsForm {
	//News
	@NotBlank
	private String title;
	private String author;
	private Date postDate =  new Date();
	
	//NewsDetail
	@NotBlank
	private String article;
	
	//お知らせの対象リスト（会員種別のIDリスト）
	private List<Integer> targetIdList;
	
	//画像のアップロード
	private MultipartFile upfile;
}
