package com.example.app.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class Member {
	private Integer id;
	@NotBlank
	@Size(max = 10)
	private String name;
	@Range(min = 0, max = 120)
	private Integer age;
	@Size(max = 255)
	private String address;
	private Integer typeId;
	private String typeName;
	private Date created;
}
