package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogResponse;
import com.example.cms.dto.ContributionPanelResponse;
import com.example.cms.exception.IllegalAccessRequestException;
import com.example.cms.exception.PanelNotFoundByIdException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.model.ContributionPanel;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContributionPanelRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.ContributionPanelService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContributionPanelServiceImpl implements ContributionPanelService {

	private UserRepository userRepository;
	private ContributionPanelRepository contributionPanelRepository;
	private BlogRepository blogRepository;
	private ResponseStructure<ContributionPanelResponse> structure;

	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributors(int userId, int panelId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(email).map(owner ->{
			return contributionPanelRepository.findById(panelId).map(panel ->{
				if(!blogRepository.existsByUserAndContributionPanel(owner, panel))
					throw new IllegalAccessRequestException("Failed to add Contribution");
				return userRepository.findById(userId).map(contributor -> {
//					if(!panel.getListUser().contains(contributor) && panel.getListUser().contains(owner))
					panel.getListUser().add(contributor);
					contributionPanelRepository.save(panel);
					return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value()).setMessage("Contribution added Successfully")
							.setData(mapToContributionPanelResponse(panel)));
				}).orElseThrow(() -> new UserNotFoundByIdException("User Not Found BY ID"));
			}).orElseThrow(() -> new PanelNotFoundByIdException("Panel Not Found By ID"));
		}).get();
	}

	private ContributionPanelResponse mapToContributionPanelResponse(ContributionPanel panel) {
		return ContributionPanelResponse.builder().panelId(panel.getPanelId())
				.build();
	}

	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> removeUserFromContributionPanel(int userId,
			int panelId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(email).map(owner ->{
			return contributionPanelRepository.findById(panelId).map(panel ->{
				if(!blogRepository.existsByUserAndContributionPanel(owner, panel))
					throw new IllegalAccessRequestException("Failed to add Contribution");
				return userRepository.findById(userId).map(contributor -> {
					panel.getListUser().remove(contributor);
				    contributionPanelRepository.save(panel);
					return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value()).setMessage(" Removed user from Contribution panel Successfully")
							.setData(mapToContributionPanelResponse(panel)));
				}).orElseThrow(() -> new UserNotFoundByIdException("User Not Found BY ID"));
			}).orElseThrow(() -> new PanelNotFoundByIdException("Panel Not Found By ID"));
		}).get();
	}
}
