package me.spring.restaurant.application;

import me.spring.restaurant.domain.Review;
import me.spring.restaurant.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void getReviews() {
        List<Review> mockReviews = new ArrayList<>();
        mockReviews.add(Review.builder().description("Cool!").build());

        given(reviewRepository.findAll()).willReturn(mockReviews);

        List<Review> reviews = reviewService.getReviews();

        Review review = reviews.get(0);

        assertThat(review.getDescription(), is("Cool!"));
    }
}
