package com.ethan.apiproject.service;

import com.ethan.apiproject.model.offerings.Offering;
import com.ethan.apiproject.repository.OfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferingService {

    @Autowired
    private OfferingRepository offeringRepository;

    public List<Offering> getAllOfferings() {
        return offeringRepository.findAll();
    }

    public Optional<Offering> getOfferingById(UUID offeringId) {
        return offeringRepository.findById(offeringId);
    }

    public Offering createOffering(Offering offering) {
        return offeringRepository.save(offering);
    }

    public void deleteOffering(UUID offeringId) {
        offeringRepository.deleteById(offeringId);
    }

}
