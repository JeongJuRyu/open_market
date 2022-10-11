package com.tmax.cm.superstore.ship.service;

import com.tmax.cm.superstore.ship.repository.ShipRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class ShipService {

    private final ShipRepository shipRepository;


}
