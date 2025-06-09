package com.org.kodvix.freelanceapp.service;

import com.org.kodvix.freelanceapp.dto.FeedbackDTO;
import com.org.kodvix.freelanceapp.dto.FeedbackListDTO;
import com.org.kodvix.freelanceapp.entities.Feedback;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IFeedbackService {

	Float averageRating(String id);

	FeedbackDTO addFeedback(FeedbackDTO feedbackDto);

	List<FeedbackListDTO> findFeedbacksForFreelancerByRecruiter(String fId, String rId);
}
