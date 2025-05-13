package services;

import models.Dto.feedBack.CreateFeedBackDto;
import models.FeedBack;
import repository.FeedBackRepository;

import java.time.LocalDate;
import java.util.ArrayList;
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
            throw new Exception("Select a rating !");
        }
        if (feedbackDto.getKoment().length() > 500) {
            throw new Exception("Comments cannot be longer than 500 characters !");
        }
        FeedBack feedback = this.feedbackRepository.create(feedbackDto);
        if (feedback == null) {
            throw new Exception("Feedback wasn't created !");
        }
        return feedback;
    }
    public boolean delete(int id) throws Exception {
        this.getById(id); // E kontrollojm a ekziston
        return this.feedbackRepository.delete(id);
    }
    public List<FeedBack> getFeedbacksByDate(int instructorId, LocalDate date) {
        return feedbackRepository.getFeedbacksByStaffAndDate(instructorId, date);
    }
    public ArrayList<FeedBack>getAll(){
        return this.feedbackRepository.getAll();
    }
//PYTJE: A ka nevoj me ba validime kur te boj update te nje repository
}
