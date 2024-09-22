package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface IBuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO);
    void deleteBuildings(Long[] ids);
    ResponseDTO listStaff(Long buildingId);
    void addAssignmentBuildingEntity(AssignmentBuildingDTO assignmentBuildingDTO);
}
