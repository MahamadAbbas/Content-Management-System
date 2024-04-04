package com.example.cms.model;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.cms.enums.PostType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@Table(name= "BlogPost_Table")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class BlogPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;

	@NotNull(message = "Title cannot be null")
	private String title;

	private String subTitle;

//	@Length(min = 500, max = 2024)
	//@Size(min = 500, message = "Summary should be at least 500 characters long")
	private String summary;

	@NotNull(message = "Post type cannot be null")
	private PostType postType;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	private LocalDateTime lastModifiedAt;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedBy
	private String lastModifiedBy;
	
	@ManyToOne
	private Blog blog;
	
	@OneToOne
	private BlogPost blogPost;
}

