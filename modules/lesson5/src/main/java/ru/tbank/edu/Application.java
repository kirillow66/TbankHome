package ru.tbank.edu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import ru.tbank.edu.model.Category;
import ru.tbank.edu.model.Location;
import ru.tbank.edu.service.CategoryService;
import ru.tbank.edu.service.LocationService;

import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final CategoryService categoryService;
    private final LocationService locationService;

    public Application(CategoryService categoryService, LocationService locationService) {
        this.categoryService = categoryService;
        this.locationService = locationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();


        System.out.println("Initializing categories...");
        Category[] categories = restTemplate.getForObject("https://kudago.com/public-api/v1.4/place-categories", Category[].class);
        if (categories != null) {
            Arrays.stream(categories).forEach(category -> categoryService.createCategory(category.getId(), category));
            System.out.println("Categories successfully loaded.");
        } else {
            System.out.println("Failed to load categories.");
        }


        System.out.println("Initializing locations...");
        Location[] locations = restTemplate.getForObject("https://kudago.com/public-api/v1.4/locations", Location[].class);
        if (locations != null && locations.length > 0) {
            Arrays.stream(locations).forEach(location -> {

                locationService.createLocation(location);
            });
            System.out.println("Locations successfully loaded.");
        } else {
            System.out.println("Failed to load locations.");
        }

        System.out.println("Initialization completed.");
        System.out.println();
    }
}
