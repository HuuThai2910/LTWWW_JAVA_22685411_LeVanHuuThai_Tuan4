/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package iuh.fit.edu.daos.impl;

import iuh.fit.edu.entities.DanhMuc;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@AllArgsConstructor
public class DanhMucDAOImpl implements iuh.fit.edu.daos.DanhMucDAO {
    private EntityManager entityManager;

    @Override
    public DanhMuc findById(Long id) {
        try {
            DanhMuc danhMuc = entityManager.find(DanhMuc.class, id);
            return danhMuc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<DanhMuc> findAll(){
        try {
            return entityManager.createQuery("Select d from DanhMuc d", DanhMuc.class)
                    .getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
