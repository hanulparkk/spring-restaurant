package me.spring.restaurant.application;

import me.spring.restaurant.domain.MenuItem;
import me.spring.restaurant.domain.MenuItemRepository;
import me.spring.restaurant.domain.Restaurant;
import me.spring.restaurant.domain.RestaurantNotFoundException;
import me.spring.restaurant.domain.RestaurantRepository;
import me.spring.restaurant.domain.Review;
import me.spring.restaurant.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private MenuItemRepository menuItemRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuItemRepository menuItemRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants;
    }

    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(restaurant.getId());
        restaurant.setMenuItems(menuItems);

        List<Review> reviews = reviewRepository.findAllByRestaurantId(restaurant.getId());
        restaurant.setReviews(reviews);

        return restaurant;
    }
}
