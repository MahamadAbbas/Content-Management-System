package com.example.cms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublishRequest {
	
	@NotNull(message = "seoTitle is required")
	private String seoTitle;
	
	private String seoDescription;
	
	private String[] seoTags;
	
	private String publishURL;

}
