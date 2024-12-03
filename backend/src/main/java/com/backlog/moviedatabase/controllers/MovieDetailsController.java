package com.backlog.moviedatabase.controllers;

import com.backlog.moviedatabase.models.TrendingMovies;
import com.backlog.moviedatabase.config.ApiKeyProvider;
import com.backlog.moviedatabase.models.TrendingMovie;
import com.backlog.moviedatabase.models.MovieReview;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PathVariable;  // For PathVariable
import org.springframework.http.HttpMethod;  // For HttpMethod
import org.springframework.core.ParameterizedTypeReference;  // For ParameterizedTypeReference
import org.springframework.web.client.RestTemplate;  // For RestTemplate

@RestController
@RequestMapping("/api/movies") // concerned with grabbing movie ID's for movie details and reviews
public class MovieDetailsController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiKeyProvider apiKeyProvider;

    @GetMapping("/details/{movieId}")
    public TrendingMovie getTrendingMovie(@PathVariable String movieId) {
        String apiKey = apiKeyProvider.getApiKey();
        String detailsAPI = "https://api.themoviedb.org/3/movie/" + movieId + "?language=en-US&api_key=" + apiKey;
        
        // Fetch movie details
        TrendingMovie movieDetails = restTemplate.getForObject(detailsAPI, TrendingMovie.class); 

        return movieDetails;
    }
}