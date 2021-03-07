package me.spring.restaurant.interfaces;

import me.spring.restaurant.application.CategoryService;
import me.spring.restaurant.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list() {
        List<Category> regions = categoryService.getCategories();

        return regions;
    }

    @PostMapping("/categories")
    public ResponseEntity<?> create(@RequestBody Category resource
    ) throws URISyntaxException {
        Category category = categoryService.addCategory(resource);

        URI location = new URI("/categories/" + category.getId());
        return ResponseEntity.created(location).body("{}");
    }
}
