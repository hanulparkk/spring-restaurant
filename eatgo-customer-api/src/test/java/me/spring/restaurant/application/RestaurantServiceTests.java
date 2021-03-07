package me.spring.restaurant.application;

import me.spring.restaurant.domain.MenuItem;
import me.spring.restaurant.domain.MenuItemRepository;
import me.spring.restaurant.domain.Restaurant;
import me.spring.restaurant.domain.RestaurantNotFoundException;
import me.spring.restaurant.domain.RestaurantRepository;
import me.spring.restaurant.domain.Review;
import me.spring.restaurant.domain.ReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTests {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviewRepository();

        restaurantService = new RestaurantService(
                restaurantRepository, menuItemRepository, reviewRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();
        restaurants.add(restaurant);

        given(restaurantRepository.findAllByAddressContaining("Seoul"))
                .willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder()
                .name("Kimchi")
                .build());

        given(menuItemRepository.findAllByRestaurantId(1004L))
                .willReturn(menuItems);
    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
                .name("BeRyong")
                .score(1)
                .description("this is bad")
                .build());

        given(reviewRepository.findAllByRestaurantId(1004L))
                .willReturn(reviews);
    }

    @Test
    public void getRestaurants() {
        String region = "Seoul";

        List<Restaurant> restaurants = restaurantService.getRestaurants(region);

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        assertThat(restaurant.getId(), is(1004L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertThat(menuItem.getName(), is("Kimchi"));

        Review review = restaurant.getReviews().get(0);

        assertThat(review.getDescription(), is("this is bad"));
    }

    @Test
    public void getRestaurantWithNotExisted() {
        Assertions.assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.getRestaurant(404L);
        });
    }
}
