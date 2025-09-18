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

import iuh.fit.edu.entities.TinTuc;

import java.util.List;

public interface TinTucDao {
    TinTuc save(TinTuc tinTuc);

    boolean delete(Long maTinTuc);

    List<TinTuc> findTinTucByMaDanhMuc(Long maDanhMuc);

    List<TinTuc>findAll();
}
