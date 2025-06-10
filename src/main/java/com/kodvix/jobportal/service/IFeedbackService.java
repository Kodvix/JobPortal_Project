package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dto.FeedbackDTO;
import com.kodvix.jobportal.dto.FeedbackListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IFeedbackService {

    Float averageRating(String id);

    FeedbackDTO addFeedback(FeedbackDTO feedbackDto);

    List<FeedbackListDTO> findFeedbacksForFreelancerByRecruiter(String fId, String rId);
}
