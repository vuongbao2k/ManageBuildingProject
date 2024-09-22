package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuildingSearchResponseConverter;
import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IRentAreaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
    @Autowired
    private BuildingSearchResponseConverter buildingSearchResponseConverter;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IRentAreaService rentAreaService;
    @Autowired
    private RentAreaConverter rentAreaConverter;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();
        for(BuildingEntity buildingEntity : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingSearchResponseConverter.convertBuildingSearchResponse(buildingEntity);
            buildingSearchResponses.add(buildingSearchResponse);
        }
        return buildingSearchResponses;
    }

    @Override
    public BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setType(String.join(",",buildingDTO.getTypeCode()));
        buildingEntity.setRentarea(rentAreaConverter.toRentAreaEntityList(buildingDTO, buildingEntity));
        buildingRepository.save(buildingEntity);
//        buildingDTO.setId(buildingEntity.getId());
//        if(StringUtils.check(buildingDTO.getRentarea())) {
//            rentAreaService.addRentArea(buildingDTO);
//        }
        return buildingDTO;
    }

    @Override
    public void deleteBuildings(Long[] ids) {
//        rentAreaService.deleteByBuildings(ids);
//        assignmentBuildingService.deleteByBuildings(ids);
//        for(Long id : ids) buildingRepository.deleteById(id);
        buildingRepository.deleteByIdIn(ids);
    }


    @Override
    public ResponseDTO listStaff(Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        List<UserEntity> staff = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<UserEntity> staffAssignment = buildingEntity.getUserEntities();
//        List<AssignBuildingEntity> assignBuildingEntityList = buildingEntity.getAssignBuildingEntities();
//        List<UserEntity> staffAssignment = new ArrayList<>();
//        for(AssignBuildingEntity assignBuildingEntity : assignBuildingEntityList) {
//            staffAssignment.add(assignBuildingEntity.getUserEntity());
//        }
        List<StaffResponseDTO> staffResponseDTOList = new ArrayList<StaffResponseDTO>();
        ResponseDTO responseDTO = new ResponseDTO();
        for(UserEntity it : staff){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(it.getId());
            staffResponseDTO.setFullName(it.getFullName());
            if(staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOList.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOList);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public void addAssignmentBuildingEntity(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        List<UserEntity> userEntities = userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
        buildingEntity.setUserEntities(userEntities);
        buildingRepository.save(buildingEntity);
    }
}
