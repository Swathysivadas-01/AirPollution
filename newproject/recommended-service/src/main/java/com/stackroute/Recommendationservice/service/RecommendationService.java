package com.stackroute.Recommendationservice.service;

import com.stackroute.Recommendationservice.model.Recommendation;

import java.util.List;

public interface RecommendationService {
    //abstract method to get all recommendations
    public List<Recommendation> getAllRecommendation();
    //abstract method to save recommendations
    public boolean saveToRecommendation(Recommendation recommendation);
    //abstract method to delete recommendations
    public boolean deleteRecommendation(int id);
}