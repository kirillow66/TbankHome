package ru.tbank.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.tbank.edu.aop.LogExecutionTime;
import ru.tbank.edu.model.Location;
import ru.tbank.edu.model.LocationDTO;
import ru.tbank.edu.service.LocationService;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @LogExecutionTime
    @GetMapping
    public ResponseEntity<Collection<LocationDTO>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @LogExecutionTime
    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Integer id) {
        return locationService.getLocationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @LogExecutionTime
    @PostMapping
    public ResponseEntity<Void> createLocation(@RequestBody Location location) {
        locationService.createLocation(location);
        return ResponseEntity.created(null).build();
    }

    @LogExecutionTime
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLocation(@PathVariable Integer id, @RequestBody LocationDTO locationDTO) {
        locationService.updateLocation(id, locationDTO);
        return ResponseEntity.noContent().build();
    }

    @LogExecutionTime
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Integer id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
