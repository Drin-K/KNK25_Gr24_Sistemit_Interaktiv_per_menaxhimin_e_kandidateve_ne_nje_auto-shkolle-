package services;

import models.Dto.feedBack.CreateFeedBackDto;
import models.FeedBack;
import repository.FeedBackRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class FeedBackService {
    private FeedBackRepository feedbackRepository;

    public FeedBackService() {
        this.feedbackRepository = new FeedBackRepository();
    }
    public FeedBack getById(int id)throws Exception{
        if(id <= 0){
            throw new Exception("Id does not exist!");
        }
        FeedBack feedBack=this.feedbackRepository.getById(id);
        if (feedBack==null){
            throw new Exception("User with Id: " + id + " does not exist!");
        }
        return feedBack;
    }

    public FeedBack create(CreateFeedBackDto feedbackDto) throws Exception {
        if (feedbackDto.getVlersimi() < 1 || feedbackDto.getVlersimi() > 5) {
            throw new Exception("Vlerësimi duhet të jetë midis 1 dhe 5!");
        }
        if (feedbackDto.getKoment().length() > 500) {
            throw new Exception("Komentet nuk mund të jenë më të gjata se 500 karaktere!");
        }
        FeedBack feedback = this.feedbackRepository.create(feedbackDto);
        if (feedback == null) {
            throw new Exception("Feedback nuk u krijua!");
        }
        return feedback;
    }

    public List<FeedBack> getFeedbacksByDate(int instructorId, LocalDate date) {
        return feedbackRepository.getFeedbacksByStaffAndDate(instructorId, date);
    }
//PYTJE: A ka nevoj me ba validime kur te boj update te nje repository
}
