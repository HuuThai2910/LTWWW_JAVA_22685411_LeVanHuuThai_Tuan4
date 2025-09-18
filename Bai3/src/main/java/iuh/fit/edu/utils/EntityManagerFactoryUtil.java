/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package iuh.fit.edu.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
public class EntityManagerFactoryUtil {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("QUANLYDANHMUC");
        }catch (Exception e){
            throw new ExceptionInInitializerError(e);
        }
    }
    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
    public static void close(){
        if(entityManagerFactory.isOpen())
            entityManagerFactory.close();
    }

    public static void main(String[] args) {
        EntityManager entityManager = getEntityManager();
    }
}
