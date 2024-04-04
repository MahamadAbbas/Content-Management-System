package com.example.cms.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Publish {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int publishId;
	
	private String seoTitle;
	
	private String seoDescription;
	
	private String[] seoTags;
	
	private String publishURL;
	
	@OneToOne
	private BlogPost blogPost;

}
