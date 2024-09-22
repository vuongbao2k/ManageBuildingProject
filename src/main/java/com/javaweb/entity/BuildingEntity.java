package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "district")
    private String district;

    @Column(name = "structure")
    private String structure;

    @Column(name = "numberofbasement")
    private Integer numberofbasement;

    @Column(name = "floorarea")
    private Integer floorarea;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private Integer level;

    @Column(name = "rentprice")
    private Integer rentprice;

    @Column(name = "rentpricedescription")
    private String rentpricedescription;

    @Column(name = "servicefee")
    private Integer servicefee;

    @Column(name = "carfee")
    private Integer carfee;

    @Column(name = "motofee")
    private Integer motofee;

    @Column(name = "overtimefee")
    private Integer overtimefee;

    @Column(name = "waterfee")
    private Integer waterfee;

    @Column(name = "electricityfee")
    private Integer electricityfee;

    @Column(name = "deposit")
    private Integer deposit;

    @Column(name = "payment")
    private Integer payment;

    @Column(name = "renttime")
    private Integer renttime;

    @Column(name = "decorationtime")
    private Integer decorationtime;

    @Column(name = "brokeragefee")
    private Integer brokeragefee;

    @Column(name = "type")
    private String type;

    @Column(name = "note")
    private String note;

    @Column(name = "linkofbuilding")
    private String linkofbuilding;

    @Column(name = "map")
    private String map;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "managername")
    private String managername;

    @Column(name = "managerphone")
    private String managerphone;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buildingEntity")
//    List<AssignBuildingEntity> assignBuildingEntities = new ArrayList<AssignBuildingEntity>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> userEntities = new ArrayList<>();

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<RentAreaEntity> rentarea = new ArrayList<RentAreaEntity>();

    public List<RentAreaEntity> getRentarea() {
        return rentarea;
    }

    public void setRentarea(List<RentAreaEntity> rentarea) {
        this.rentarea = rentarea;
    }

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

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

    public String getRentpricedescription() {
        return rentpricedescription;
    }

    public void setRentpricedescription(String rentpricedescription) {
        this.rentpricedescription = rentpricedescription;
    }

    public Integer getServicefee() {
        return servicefee;
    }

    public void setServicefee(Integer servicefee) {
        this.servicefee = servicefee;
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

    public Integer getOvertimefee() {
        return overtimefee;
    }

    public void setOvertimefee(Integer overtimefee) {
        this.overtimefee = overtimefee;
    }

    public Integer getWaterfee() {
        return waterfee;
    }

    public void setWaterfee(Integer waterfee) {
        this.waterfee = waterfee;
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

    public Integer getBrokeragefee() {
        return brokeragefee;
    }

    public void setBrokeragefee(Integer brokeragefee) {
        this.brokeragefee = brokeragefee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLinkofbuilding() {
        return linkofbuilding;
    }

    public void setLinkofbuilding(String linkofbuilding) {
        this.linkofbuilding = linkofbuilding;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

//    public List<AssignBuildingEntity> getAssignBuildingEntities() {
//        return assignBuildingEntities;
//    }
//
//    public void setAssignBuildingEntities(List<AssignBuildingEntity> assignBuildingEntities) {
//        this.assignBuildingEntities = assignBuildingEntities;
//    }
}
