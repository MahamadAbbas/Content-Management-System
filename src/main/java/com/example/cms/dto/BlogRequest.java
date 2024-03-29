package com.example.cms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequest {

	@NotNull(message = "Title is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Title should only contain alphabets")
	private String title;

//	@NotEmpty(message = "At least one topic must be specified")          // it will be only for the String type
	private String[] topics;

	private String about;

}
	