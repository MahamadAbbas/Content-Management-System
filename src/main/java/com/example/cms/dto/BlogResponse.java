package com.example.cms.dto;

import com.example.cms.model.ContributionPanel;
import com.example.cms.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {

	private int blogId;

	private String title;

	private String[] topics;

	private String about;

}
