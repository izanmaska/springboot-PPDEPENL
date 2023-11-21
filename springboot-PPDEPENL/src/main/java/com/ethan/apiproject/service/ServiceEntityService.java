package com.ethan.apiproject.service;

import com.ethan.apiproject.model.ServiceEntity;
import com.ethan.apiproject.repository.ServiceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEntityService {

    @Autowired
    private ServiceEntityRepository serviceRepository;

    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }


}
