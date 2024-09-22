package com.javaweb.service.impl;

import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.IRentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentAreaService implements IRentAreaService {
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    RentAreaRepository rentAreaRepository;
    @Autowired
    RentAreaConverter rentAreaConverter;
    @Override
    public void deleteByBuildings(Long[] ids) {
//        for(Long id : ids) {
//            BuildingEntity buildingEntity = buildingRepository.findById(Long.valueOf(id)).get();
//            rentAreaRepository.deleteByBuilding(buildingEntity);
//        }
        rentAreaRepository.deleteByBuildingIdIn(ids);
    }

    @Override
    public void addRentArea(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingDTO.getId()).get();
        rentAreaRepository.deleteByBuilding(buildingEntity);
        String[] rentAreas = buildingDTO.getRentarea().split(",");
        for(String rentArea : rentAreas) {
            RentAreaEntity rentAreaEntity = rentAreaConverter.toRentAreaEntity(buildingDTO, Long.valueOf(rentArea));
            rentAreaRepository.save(rentAreaEntity);
        }
    }
}
