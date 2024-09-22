package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.utils.DistrictCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingSearchResponseConverter {
    @Autowired
    ModelMapper modelMapper;

    public BuildingSearchResponse convertBuildingSearchResponse(BuildingEntity buildingEntity) {
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentarea();
        String rentArea = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        buildingSearchResponse.setRentArea(rentArea);
        Map<String, String> districts = DistrictCode.type();
        String districtName = "";
        if(buildingEntity.getDistrict()!=null && !"".equals(buildingEntity.getDistrict())) {
            districtName = districts.get(buildingEntity.getDistrict());
        }
        if(districtName!=null && !"".equals(districtName)) {
            buildingSearchResponse.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + districtName);
        }
        return buildingSearchResponse;
    }
}
