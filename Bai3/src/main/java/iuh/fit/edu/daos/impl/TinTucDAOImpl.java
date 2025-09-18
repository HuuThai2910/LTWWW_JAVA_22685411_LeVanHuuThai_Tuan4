/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package iuh.fit.edu.daos.impl;

import iuh.fit.edu.entities.TinTuc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;

import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@AllArgsConstructor
public class TinTucDAOImpl implements iuh.fit.edu.daos.TinTucDao {
    private EntityManager entityManager;

    @Override
    public TinTuc save(TinTuc tinTuc){
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(tinTuc);
            entityTransaction.commit();
            return tinTuc;
        }catch (Exception e){
            if(entityTransaction != null && entityTransaction.isActive())
                entityTransaction.rollback();
            e.printStackTrace(); // In stacktrace để biết lỗi thật
        }
        return null;
    }
    @Override
    public boolean delete(Long maTinTuc){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            TinTuc tinTuc = entityManager.find(TinTuc.class, maTinTuc);
            if(tinTuc != null){
                entityManager.remove(tinTuc);
                tr.commit();
                return true;
            }
        }catch (Exception e){
            if(tr != null && tr.isActive())
                tr.rollback();
        }
        return false;
    }

    @Override
    public List<TinTuc> findTinTucByMaDanhMuc(Long maDanhMuc){
        String query = "select t " +
                "from TinTuc t " +
                "where t.danhMuc.maDanhMuc = :maDanhMuc";
        return entityManager.createQuery(query, TinTuc.class)
                .setParameter("maDanhMuc", maDanhMuc)
                .getResultList();
    }

    @Override
    public List<TinTuc>findAll(){
        try {
            return entityManager.createQuery("Select t from TinTuc t", TinTuc.class)
                    .getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
