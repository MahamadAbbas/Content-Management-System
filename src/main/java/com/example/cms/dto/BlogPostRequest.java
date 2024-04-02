package com.example.cms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogPostRequest {

	@NotNull
	private String title;

	private String subTitle;

	private String summary;

}
