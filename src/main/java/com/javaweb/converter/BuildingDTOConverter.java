package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BuildingDTOConverter {
    @Autowired
    ModelMapper modelMapper;

    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity){
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentarea();
        String rentArea = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        buildingDTO.setRentarea(rentArea);
        List<String> typeCode = Stream.of(buildingEntity.getType().split(",", -1)).collect(Collectors.toList());
        buildingDTO.setTypeCode(typeCode);
        return buildingDTO;
    }
}
