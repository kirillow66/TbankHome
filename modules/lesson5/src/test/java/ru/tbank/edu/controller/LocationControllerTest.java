package ru.tbank.edu.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import ru.tbank.edu.model.Location;
import ru.tbank.edu.model.LocationDTO;
import ru.tbank.edu.service.LocationService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LocationControllerTest {

    @InjectMocks
    private LocationController locationController;

    @Mock
    private LocationService locationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllLocations() {
        LocationDTO location1 = new LocationDTO(1, "Location 1", "Loc1");
        LocationDTO location2 = new LocationDTO(2, "Location 2", "Loc2");

        when(locationService.getAllLocations()).thenReturn(Arrays.asList(location1, location2));

        ResponseEntity<Collection<LocationDTO>> response = locationController.getAllLocations();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(locationService).getAllLocations();
    }

    @Test
    public void testGetLocationByIdFound() {
        LocationDTO location = new LocationDTO(1, "Location 1", "Loc1");

        when(locationService.getLocationById(1)).thenReturn(Optional.of(location));

        ResponseEntity<LocationDTO> response = locationController.getLocationById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(location, response.getBody());
        verify(locationService).getLocationById(1);
    }

    @Test
    public void testGetLocationByIdNotFound() {
        when(locationService.getLocationById(999)).thenReturn(Optional.empty());

        ResponseEntity<LocationDTO> response = locationController.getLocationById(999);

        assertEquals(404, response.getStatusCodeValue());
        verify(locationService).getLocationById(999);
    }

    @Test
    public void testCreateLocation() {
        Location location = new Location( "New Location", "NL");

        doNothing().when(locationService).createLocation(any(Location.class));

        ResponseEntity<Void> response = locationController.createLocation(location);

        assertEquals(201, response.getStatusCodeValue());
        verify(locationService).createLocation(eq(location));
    }

    @Test
    public void testUpdateLocation() {
        LocationDTO locationDTO = new LocationDTO(1,"Updated Location", "UL");

        doNothing().when(locationService).updateLocation(any(Integer.class), any(LocationDTO.class));

        ResponseEntity<Void> response = locationController.updateLocation(1, locationDTO);

        assertEquals(204, response.getStatusCodeValue());
        verify(locationService).updateLocation(eq(1), eq(locationDTO));
    }

    @Test
    public void testDeleteLocation() {
        doNothing().when(locationService).deleteLocation(1);

        ResponseEntity<Void> response = locationController.deleteLocation(1);

        assertEquals(204, response.getStatusCodeValue());
        verify(locationService).deleteLocation(1);
    }
}
