/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package iuh.fit.edu.daos;/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */

import iuh.fit.edu.entitites.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();

    Product getProductById(int id);
}
