package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            sql.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.buildingid ");
        }
    }

    public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
                        && !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(buildingSearchBuilder);
                    if (value != null && !"".equals(value)) {
                        if (item.getType().getName().equals("java.lang.Long")
                                || item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND b." + fieldName + " = " + value);
                        } else if (item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            where.append(" AND ab.staffid = " + staffId);
        }
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        if (rentAreaFrom != null || rentAreaTo != null) {
            where.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE ra.buildingid = b.id ");
            if (rentAreaFrom != null) {
                where.append(" AND ra.value >=" + rentAreaFrom);
            }
            if (rentAreaTo != null) {
                where.append(" AND ra.value <=" + rentAreaTo);
            }
            where.append(") ");
        }

        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        if (rentPriceFrom != null) {
            where.append(" AND b.rentprice >=" + rentPriceFrom);
        }
        if (rentPriceTo != null) {
            where.append(" AND b.rentprice <=" + rentPriceTo);
        }
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && typeCode.size() != 0) {
            where.append(" AND (");
            String sql = typeCode.stream().map(it -> "b.type LIKE '%" + it + "%' ")
                    .collect(Collectors.joining(" OR "));
            where.append(sql);
            where.append(") ");
        }

    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder(
                "SELECT b.* FROM building b ");
        joinTable(buildingSearchBuilder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        queryNormal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        where.append(" GROUP BY b.id ");
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }
}
