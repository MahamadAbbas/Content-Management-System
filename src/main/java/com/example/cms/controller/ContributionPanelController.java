package com.example.cms.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.ContributionPanelResponse;
import com.example.cms.service.ContributionPanelService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@AllArgsConstructor
public class ContributionPanelController {
	
	private ContributionPanelService contributionPanelService;
	
	@PutMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributors(@PathVariable int userId, @PathVariable int panelId) {
		return contributionPanelService.addContributors(userId,panelId);
	}
	
	@DeleteMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> removeUserFromContributionPanel(@PathVariable int userId, @PathVariable int panelId){
		return contributionPanelService.removeUserFromContributionPanel(userId,panelId);
	}
}
