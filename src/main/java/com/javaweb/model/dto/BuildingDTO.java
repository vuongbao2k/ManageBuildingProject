package com.javaweb.model.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingDTO extends AbstractDTO{
    private String name;
    private String street;
    private String ward;
    private String district;
    private Integer numberofbasement;
    private Integer floorarea;
    private Integer level;
    private Integer rentprice;
    private List<String> typeCode;
    private Integer overtimefee;
    private Integer electricityfee;
    private Integer deposit;
    private Integer payment;
    private Integer renttime;
    private Integer decorationtime;
    private String rentpricedescription;
    private Integer carfee;
    private Integer motofee;
    private String structure;
    private String direction;
    private String note;
    private String rentarea;
    private String managername;
    private String managerphone;
    private Integer servicefee;
    private Integer brokeragefee;
//    private String image;
//    private String imageBase64;
//    private String imageName;
//    private Map<String,String> buildingDTOs = new HashMap<>();
//
//    public Map<String, String> getBuildingDTOs() {
//        return buildingDTOs;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getNumberofbasement() {
        return numberofbasement;
    }

    public void setNumberofbasement(Integer numberofbasement) {
        this.numberofbasement = numberofbasement;
    }

    public Integer getFloorarea() {
        return floorarea;
    }

    public void setFloorarea(Integer floorarea) {
        this.floorarea = floorarea;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRentprice() {
        return rentprice;
    }

    public void setRentprice(Integer rentprice) {
        this.rentprice = rentprice;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(List<String> typeCode) {
        this.typeCode = typeCode;
    }

    public Integer getOvertimefee() {
        return overtimefee;
    }

    public void setOvertimefee(Integer overtimefee) {
        this.overtimefee = overtimefee;
    }

    public Integer getElectricityfee() {
        return electricityfee;
    }

    public void setElectricityfee(Integer electricityfee) {
        this.electricityfee = electricityfee;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getRenttime() {
        return renttime;
    }

    public void setRenttime(Integer renttime) {
        this.renttime = renttime;
    }

    public Integer getDecorationtime() {
        return decorationtime;
    }

    public void setDecorationtime(Integer decorationtime) {
        this.decorationtime = decorationtime;
    }

    public String getRentpricedescription() {
        return rentpricedescription;
    }

    public void setRentpricedescription(String rentpricedescription) {
        this.rentpricedescription = rentpricedescription;
    }

    public Integer getCarfee() {
        return carfee;
    }

    public void setCarfee(Integer carfee) {
        this.carfee = carfee;
    }

    public Integer getMotofee() {
        return motofee;
    }

    public void setMotofee(Integer motofee) {
        this.motofee = motofee;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRentarea() {
        return rentarea;
    }

    public void setRentarea(String rentarea) {
        this.rentarea = rentarea;
    }

    public String getManagername() {
        return managername;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }

    public String getManagerphone() {
        return managerphone;
    }

    public void setManagerphone(String managerphone) {
        this.managerphone = managerphone;
    }

    public Integer getServicefee() {
        return servicefee;
    }

    public void setServicefee(Integer servicefee) {
        this.servicefee = servicefee;
    }

    public Integer getBrokeragefee() {
        return brokeragefee;
    }

    public void setBrokeragefee(Integer brokeragefee) {
        this.brokeragefee = brokeragefee;
    }
}