package com.example.clientsservice.services.data.db;

import com.example.clientsservice.repositories.AddressRepository;
import com.example.clientsservice.services.data.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceDb implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
}
