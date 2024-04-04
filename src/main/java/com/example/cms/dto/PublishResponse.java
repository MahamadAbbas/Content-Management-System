package com.example.cms.dto;

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
public class PublishResponse {

	private int publishId;

	private String seoTitle;

	private String seoDescription;

	private String[] seoTags;

}
