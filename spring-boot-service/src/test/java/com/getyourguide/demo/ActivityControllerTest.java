// package com.getyourguide.demo;
//
// import com.fasterxml.jackson.core.type.TypeReference;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.core.io.Resource;
// import org.springframework.core.io.ResourceLoader;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.core.io.ClassPathResource;
//
// import java.io.*;
// import java.io.ByteArrayInputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.util.Arrays;
// import java.util.List;
//
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;
//
// class ActivityControllerTest {
//
//     @InjectMocks
//     private ActivityController activityController;
//
//     @Mock
//     private ResourceLoader resourceLoader;
//
//     @Mock
//     private Resource mockResource;
//
//     private ObjectMapper objectMapper = new ObjectMapper();
//
//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }
//
//     @Test
//     void testSearch_WithValidInput() throws IOException {
//         // Arrange
//         String searchString = "tour";
//         List<Activity> mockActivities = Arrays.asList(
//             new Activity(1L, "City Tour", 50, "USD", 5.5, false),
//             new Activity(2L, "Museum Visit", 30, "EUR", 4.5, true)
//         );
//         setupMockResource(mockActivities);
//
//         System.out.println("mock " + mockActivities);
//         // Act
//         ResponseEntity<List<Activity>> response = activityController.search(searchString);
//
//         // Assert
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertNotNull(response.getBody());
//         assertEquals(1, response.getBody().size());
//         assertEquals("City Tour", response.getBody().get(0).getTitle());
//     }
//
//     @Test
//     void testSearch_WithEmptyInput() throws IOException {
//         // Arrange
//         String searchString = "";
//         List<Activity> mockActivities = Arrays.asList(
//             new Activity(1L, "City Tour", 50, "USD", 5.5, false),
//             new Activity(2L, "Museum Visit", 30, "EUR", 4.5, true)
//         );
//         setupMockResource(mockActivities);
//
//         // Act
//         ResponseEntity<List<Activity>> response = activityController.search(searchString);
//
//         // Assert
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertNotNull(response.getBody());
//         assertEquals(2, response.getBody().size());
//     }
//
//     @Test
//     void testSearch_WithException() throws IOException {
//         // Arrange
//         String searchString = "tour";
//         when(resourceLoader.getResource("static/activities.json")).thenReturn(mockResource);
//         when(mockResource.getInputStream()).thenThrow(new IOException("File not found"));
//
//         // Act
//         ResponseEntity<List<Activity>> response = activityController.search(searchString);
//
//         // Assert
//         assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//         assertNull(response.getBody());
//     }
//
//     @Test
//     void testFilterActivities() {
//         // Arrange
//         List<Activity> activities = Arrays.asList(
//             new Activity(1L, "City Tour", 50, "USD", 5.5, false),
//             new Activity(2L, "Museum Visit", 30, "EUR", 4.5, true),
//             new Activity(3L, "Mountain Hike", 75, "USD", 5.5, false)
//         );
//
//         // Act & Assert
//         assertEquals(3, activityController.filterActivities("German", activities).size());
//     }
//
//     @Test
//     void testActivities() throws IOException {
//         // Arrange
//         List<Activity> mockActivities = Arrays.asList(
//             new Activity(1L, "City Tour", 50, "USD", 5.5, false),
//             new Activity(2L, "Museum Visit", 30, "EUR", 4.5, true)
//         );
//         setupMockResource(mockActivities);
//
//         // Act
//         ResponseEntity<List<Activity>> response = activityController.activities();
//
//         // Assert
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertNotNull(response.getBody());
//         assertEquals(2, response.getBody().size());
//     }
//
//     @Test
//     void testActivities_WithException() throws IOException {
//         // Arrange
//         when(resourceLoader.getResource("static/activities.json")).thenReturn(mockResource);
//         when(mockResource.getInputStream()).thenThrow(new IOException("File not found"));
//
//         // Act & Assert
//         ResponseEntity<List<Activity>> response = activityController.activities();
//         assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//         assertNull(response.getBody());
//     }
//
//     private void setupMockResource(List<Activity> activities) throws IOException {
//         String jsonContent = objectMapper.writeValueAsString(activities);
//         InputStream inputStream = new ByteArrayInputStream(jsonContent.getBytes());
//         when(resourceLoader.getResource("static/activities.json")).thenReturn(mockResource);
//         when(mockResource.getInputStream()).thenReturn(inputStream);
//     }
// }
