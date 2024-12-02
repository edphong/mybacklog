package com.backlog.moviedatabase.controllers;

import com.backlog.moviedatabase.models.Review;
import com.backlog.moviedatabase.config.ApiKeyProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiKeyProvider apiKeyProvider;

    @GetMapping("/{movieId}")
    public List<Review> getReviews(@PathVariable String movieId) {
        String apiKey = apiKeyProvider.getApiKey();
        String reviewsAPI = "https://api.themoviedb.org/3/movie/" + movieId + "/reviews?language=en-US&page=1&api_key=" + apiKey;

        // Fetch reviews from the API
        return restTemplate.exchange(
                reviewsAPI,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {}).getBody();
    }
}
