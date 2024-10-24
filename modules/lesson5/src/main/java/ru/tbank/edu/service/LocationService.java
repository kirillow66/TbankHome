package ru.tbank.edu.service;

import org.springframework.stereotype.Service;
import ru.tbank.edu.aop.LogExecutionTime;
import ru.tbank.edu.model.Location;
import ru.tbank.edu.model.LocationDTO;
import ru.tbank.edu.repository.LocationRepository;

import java.util.Collection;
import java.util.Optional;
@LogExecutionTime
@Service
public class LocationService {
    private final LocationRepository repository;
    private Integer currentId = 1;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public Collection<LocationDTO> getAllLocations() {
        return repository.findAll();
    }

    public Optional<LocationDTO> getLocationById(Integer id) {
        return repository.findById(id);
    }

    public void createLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        LocationDTO locationDTO = new LocationDTO(currentId, location.getSlug(), location.getName());
        repository.save(currentId, locationDTO);
        currentId++;
    }

    public void updateLocation(Integer id, LocationDTO locationDTO) {
        repository.update(id, locationDTO);
    }

    public void deleteLocation(Integer id) {
        repository.deleteById(id);
    }
}