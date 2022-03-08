package com.stackroute.Recommendationservice.service;
import com.stackroute.Recommendationservice.controller.RecommendationController;
import com.stackroute.Recommendationservice.model.Recommendation;
import com.stackroute.Recommendationservice.repository.RecommendationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//service implementation class
@Service
public class RecommendationServiceImpl implements RecommendationService{

    private RecommendationRepository repository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(RecommendationController.class);

    @Autowired
    public RecommendationServiceImpl(RecommendationRepository repository) {
        this.repository = repository;
    }
    //to get all recommendations
    @Override
    public List<Recommendation> getAllRecommendation() {
        List<Recommendation> recommendation_list = repository.findAll();
        recommendation_list.removeIf(e -> (e.getCounter() < 3));
        return recommendation_list;
    }
    //to add data to recommendation list
    @Override
    public boolean saveToRecommendation(Recommendation recommendation) {
        int id = recommendation.getCityId();
        // list to get all recommendations
        List<Recommendation> addRecommendationList = repository.findAll();
        for (Recommendation item : addRecommendationList) {
            if (item.getCityId()== id) {
                // incrementing the counter
                int counter = repository.findById(id).get().getCounter();
                repository.deleteById(id);
                recommendation.setCounter(counter + 1);
                repository.save(recommendation);
                return true;
            }
        }
        recommendation.setCounter(1);
        repository.save(recommendation);
        return true;
    }
    //delete data from the list
    @Override
    public boolean deleteRecommendation(int id) {
        //delete the item if it exist in the db and decrease the counter
        List<Recommendation> deleteList = repository.findAll();
        for (Recommendation item : deleteList) {
            if (item.getCityId() == id) {
                Recommendation recommendation = repository.findById(id).get();
                repository.deleteById(id);
                recommendation.setCounter(recommendation.getCounter() - 1);
                repository.save(recommendation);
            }
            if(item.getCounter()==0){
                //delete the item if counter is zero
                repository.deleteById(id);
            }
        }
        return true;
    }
}