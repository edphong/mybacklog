package com.backlog.moviedatabase.controllers;

import com.backlog.moviedatabase.config.ApiKeyProvider;
import com.backlog.moviedatabase.models.MovieReview;
import com.backlog.moviedatabase.models.MovieReviews; // This represents the wrapper for reviews

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieReviewController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiKeyProvider apiKeyProvider;

    @GetMapping("/reviews/{movieId}")
    public List<MovieReview> getReviews(@PathVariable String movieId) {
        String reviewsAPI = "https://api.themoviedb.org/3/movie/" + movieId + "/reviews";
        String apiKey = apiKeyProvider.getApiKey();

        // Fetch the reviews data from the API
        MovieReviews response = restTemplate.getForObject(reviewsAPI + "?api_key=" + apiKey, MovieReviews.class);

        // Return the list of reviews
        return response.getResults(); // Assuming `getResults()` returns a list of `MovieReview`
    }
}
