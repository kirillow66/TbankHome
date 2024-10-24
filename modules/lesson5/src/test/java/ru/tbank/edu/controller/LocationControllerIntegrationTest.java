package ru.tbank.edu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.tbank.edu.model.Location;
import ru.tbank.edu.model.LocationDTO;
import ru.tbank.edu.service.LocationService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Location location;
    private LocationDTO locationDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        location = new Location();
        location.setSlug("test-slug");
        location.setName("Test Location");

        locationDTO = new LocationDTO();
        locationDTO.setId(3);
        locationDTO.setSlug("test-slug2");
        locationDTO.setName("Test Location2");
    }

    @Test
    public void testCreateLocation() throws Exception {
        mockMvc.perform(post("/api/v1/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(location)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllLocations() throws Exception {
        mockMvc.perform(post("/api/v1/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(location)));
    }

    @Test
    public void testUpdateLocation() throws Exception {
        mockMvc.perform(put("/api/v1/locations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(locationDTO)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteLocation() throws Exception {
        mockMvc.perform(delete("/api/v1/locations/1"))
                .andExpect(status().isNoContent());
    }
}
