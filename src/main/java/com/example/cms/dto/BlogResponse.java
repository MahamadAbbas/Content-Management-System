package com.example.cms.dto;

import java.util.ArrayList;
import java.util.List;

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
	
	private List<User> listUser = new ArrayList<User>();

}
