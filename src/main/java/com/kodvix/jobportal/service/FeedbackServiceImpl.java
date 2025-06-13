package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dao.IFeedbackDao;
import com.kodvix.jobportal.dao.IFreelancerDao;
import com.kodvix.jobportal.dao.IRecruiterDao;
import com.kodvix.jobportal.dto.FeedbackDTO;
import com.kodvix.jobportal.dto.FeedbackListDTO;
import com.kodvix.jobportal.entities.Feedback;
import com.kodvix.jobportal.entities.Freelancer;
import com.kodvix.jobportal.entities.Recruiter;
import com.kodvix.jobportal.exceptions.InvalidFeedbackException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements IFeedbackService {

    @Autowired
    IFeedbackDao feedbackDao;

    @Autowired
    IRecruiterDao recruiterDao;

    @Autowired
    IFreelancerDao freelancerDao;

    @Autowired
    ModelMapper model;

    @Override
    public Float averageRating(String id) {
        if (freelancerDao.existsByUserName(id)) {
            return feedbackDao.averageRatings(id);
        } else throw new InvalidFeedbackException();
    }

    @Override
    public FeedbackDTO addFeedback(FeedbackDTO feedbackDto) {
        System.out.println(feedbackDto.toString());
        if (recruiterDao.existsByUserName(feedbackDto.getRecruiterUName())
                && freelancerDao.existsByUserName(feedbackDto.getFreelancerUName())) {

            Recruiter recruiter = recruiterDao.findByUserName(feedbackDto.getRecruiterUName());
            Freelancer freelancer = freelancerDao.findByUserName(feedbackDto.getFreelancerUName());
            Feedback feedback = convertToEntity(feedbackDto);
            feedback.setCreatedBy(recruiter);
            feedback.setCreatedFor(freelancer);

            return convertToDto(feedbackDao.save(feedback));
        } else
            throw new InvalidFeedbackException();

    }

    @Override
    public List<FeedbackListDTO> findFeedbacksForFreelancerByRecruiter(String fId, String rId) {

        List<FeedbackListDTO> list1 = feedbackDao.findFeedbacksForFreelancerByRecruiterId(fId, rId);
        return list1;
    }

    private FeedbackDTO convertToDto(Feedback feedback) {
        return model.map(feedback, FeedbackDTO.class);
    }

    private Feedback convertToEntity(FeedbackDTO feedbackDTO) {
        return model.map(feedbackDTO, Feedback.class);
    }

    private List<FeedbackDTO> convertoDtoList(List<Feedback> feedbackList) {
        return feedbackList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
