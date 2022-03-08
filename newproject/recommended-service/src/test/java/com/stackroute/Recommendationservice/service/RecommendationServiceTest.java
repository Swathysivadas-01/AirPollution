package com.stackroute.Recommendationservice.service;

import static org.junit.jupiter.api.Assertions.*;

import com.stackroute.Recommendationservice.model.Recommendation;
        import com.stackroute.Recommendationservice.repository.RecommendationRepository;
        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
        import java.util.Optional;

        import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceTest{
    @Mock
    private RecommendationRepository recommendationRepository;

    @InjectMocks
    private RecommendationServiceImpl recommendationService;
    private Recommendation recommendation,recommendation1;
    private List<Recommendation> recommendationList;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        recommendation = new Recommendation(1, "Kochi", 4);
        recommendation1 = new Recommendation(2, "Kozhikode", 3);
        optional = Optional.of(recommendation);
    }

    @AfterEach
    public void tearDown() {
        recommendation = null;
    }
    @Test
    public void givenGetAllBlogsThenShouldReturnListOfAllBlogs() {
        recommendationRepository.save( recommendation );
        //stubbing the mock to return specific data
        when(recommendationRepository.findAll()).thenReturn(recommendationList);
        List<Recommendation> recommendationList1 = recommendationService.getAllRecommendation();
        Assertions.assertEquals( recommendationList,recommendationList1 );
        verify(recommendationRepository, times(1)).save(recommendation);
        verify(recommendationRepository, times(1)).findAll();
    }


}