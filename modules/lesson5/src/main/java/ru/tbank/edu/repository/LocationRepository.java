package ru.tbank.edu.repository;

import ru.tbank.edu.aop.LogExecutionTime;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@LogExecutionTime
@org.springframework.stereotype.Repository
public class LocationRepository<T, ID> {
    private final ConcurrentHashMap<ID, T> storage = new ConcurrentHashMap<>();


    public Collection<T> findAll() {
        return storage.values();
    }

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    public void save(ID id, T entity) {
        storage.put(id, entity);
    }

    public void update(ID id, T entity) {
        storage.replace(id, entity);
    }

    public void deleteById(ID id) {
        storage.remove(id);
    }
}
