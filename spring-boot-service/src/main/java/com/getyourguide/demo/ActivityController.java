package com.getyourguide.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;
import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ActivityController {
    @Autowired
    private ResourceLoader resourceLoader;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/search")
    public ResponseEntity<List<Activity>> search(@RequestParam(name = "search", required = false) String title) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            var fileInputStream = new ClassPathResource("static/activities.json").getInputStream();
            List<Activity> activities = objectMapper.readValue(fileInputStream, new TypeReference<List<Activity>>() {});
            List<Activity> filteredData = filterActivities(title, activities);
            return ResponseEntity.ok(filteredData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    public List<Activity> filterActivities(String searchString, List<Activity> activities) {
        if (searchString == null || searchString.trim().isEmpty()) {
            return activities;
        }

        String lowercaseSearch = searchString.toLowerCase().trim();
        return activities.stream()
                .filter(activity ->
                    activity.getTitle().toLowerCase().contains(lowercaseSearch)
                )
                .collect(Collectors.toList());
    }

    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> activities() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            var fileInputStream = new ClassPathResource("static/activities.json").getInputStream();
            List<Activity> activities = objectMapper.readValue(fileInputStream, new TypeReference<List<Activity>>() {
            });

            return ResponseEntity.ok(activities);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


}
