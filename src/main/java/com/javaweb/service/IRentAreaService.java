package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;

public interface IRentAreaService {
    public void deleteByBuildings(Long[] ids);
    public void addRentArea(BuildingDTO buildingDTO);
}
