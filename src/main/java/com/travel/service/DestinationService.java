package com.travel.service;

import com.travel.model.Destination;
import com.travel.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public Destination getDestinationById(Long id) {
        Optional<Destination> destination = destinationRepository.findById(id);
        return destination.orElse(null);
    }

    public void saveDestination(Destination destination) {
        destinationRepository.save(destination);
    }
}