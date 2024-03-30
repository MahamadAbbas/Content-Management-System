package com.example.cms.model;

import com.example.cms.enums.PostType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@Table(name= "BlogPost_Table")
@Entity
public class BlogPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int blogPostId;

	@NotNull(message = "Title cannot be null")
	private String title;

	private String subTitle;

//	@Length(min = 500, max = 2024)
	@Size(min = 500, message = "Summary should be at least 500 characters long")
	private String summary;

	@NotNull(message = "Post type cannot be null")
	private PostType postType;

	private String seoTitle;
	
	private String seoDescription;
	
	private String[] seoTopics;
	
}

