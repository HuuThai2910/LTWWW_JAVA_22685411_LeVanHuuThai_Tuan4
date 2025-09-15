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
    private static final EntityManagerFactory entityManageFactory;

    static {
        try {
            entityManageFactory = Persistence.createEntityManagerFactory("user-management");
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        return entityManageFactory.createEntityManager();
    }

    public static void close() {
        if (entityManageFactory.isOpen()) {
            entityManageFactory.close();
        }
    }

    public static void main(String[] args) {
        EntityManager entityManager = getEntityManager();
    }
}