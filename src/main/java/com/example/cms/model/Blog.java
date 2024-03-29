package com.example.cms.model;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int blogId;
	
	private String title;
	
	private String[] topics;
	
	private String about;
	
	@ManyToOne
	private User user;
	
	@OneToOne
	private ContributionPanel contributionPanel;
	
}
