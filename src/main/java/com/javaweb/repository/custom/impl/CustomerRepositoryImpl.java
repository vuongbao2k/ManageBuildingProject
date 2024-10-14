package com.javaweb.repository.custom.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    public void multiTableJoin(CustomerSearchBuilder customerSearchBuilder, StringBuilder sql) {
        Long staffId = customerSearchBuilder.getStaffId();
        if(staffId != null) {
            sql.append(" inner join assignmentcustomer as ac on c.id = ac.customerid");
        }
    }

    private void simpleQuery(CustomerSearchBuilder customerSearchBuilder, StringBuilder where) {
        try {
            Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
            for(Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(customerSearchBuilder);
                if(!fieldName.equals("staffId")) {
                    if(value != null && value != "") {
                        where.append(" and c." + fieldName.toLowerCase() + " like '%" + value + "%'");
                    }
                }
                else {
                    if(value != null && value != "") {
                        where.append(" and ac." + fieldName.toLowerCase() + " = " + value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String existCustomer() {
        StringBuilder sql = new StringBuilder(" and c.is_active = 1 ");
        return sql.toString();
    }

    @Override
    public List<CustomerEntity> searchCustomer(CustomerSearchBuilder customerSearchBuilder) {
        StringBuilder sql = new StringBuilder("Select c.* from customer c ");
        multiTableJoin(customerSearchBuilder, sql);
        sql.append(SystemConstant.ONE_EQUAL_ONE);
        sql.append(existCustomer());
        simpleQuery(customerSearchBuilder, sql);
        sql.append(" \ngroup by c.id ");
        Query query = em.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public Page<CustomerEntity> searchCustomer(CustomerSearchBuilder customerSearchBuilder, Pageable pageable) {
        StringBuilder sql = new StringBuilder("Select c.* from customer c ");
        multiTableJoin(customerSearchBuilder, sql);
        sql.append(SystemConstant.ONE_EQUAL_ONE);
        sql.append(existCustomer());
        simpleQuery(customerSearchBuilder, sql);
        sql.append(" \ngroup by c.id ");
        Query query = em.createNativeQuery(sql.toString(), CustomerEntity.class);
        List<CustomerEntity> list = query.setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<CustomerEntity>(list, pageable, list.size());  // list.size() = countTotalItems()
    }

    @Override
    public int countTotalItems() {
        StringBuilder sql = new StringBuilder("select * from customer ");
        Query query = em.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList().size();
    }
}
