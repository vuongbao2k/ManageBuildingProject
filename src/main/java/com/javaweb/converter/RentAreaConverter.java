package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentAreaConverter {
    public RentAreaEntity toRentAreaEntity(BuildingDTO buildingDTO, Long val) {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setId(buildingDTO.getId());
        RentAreaEntity rentAreaEntity = new RentAreaEntity();
        rentAreaEntity.setBuilding(buildingEntity);
        rentAreaEntity.setValue(val);
        return rentAreaEntity;
    }

    public List<RentAreaEntity> toRentAreaEntityList(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String[] rentAreas = buildingDTO.getRentarea().split(",");
        List<RentAreaEntity> rentAreaEntityList = new ArrayList<RentAreaEntity>();
        for(String rentArea : rentAreas) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntity.setValue(Long.valueOf(rentArea));
            rentAreaEntityList.add(rentAreaEntity);
        }
        return rentAreaEntityList;
    }
}
