package me.spring.restaurant.application;

import me.spring.restaurant.domain.Region;
import me.spring.restaurant.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getRegions() {
        List<Region> regions = regionRepository.findAll();

        return regions;
    }

    public Region addRegion(Region region) {
        return regionRepository.save(region);
    }
}
