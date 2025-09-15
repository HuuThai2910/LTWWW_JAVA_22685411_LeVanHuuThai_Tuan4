/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package iuh.fit.edu.daos.impl;

import iuh.fit.edu.entitites.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
public class ProductDAOImpl implements iuh.fit.edu.daos.ProductDAO {
    private final DataSource dataSource;

    public ProductDAOImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll(){
        String sql = "Select * from product";
        List<Product> productList = new ArrayList<>();
        try(Connection con = this.dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                productList.add(new Product(id, name, price, image));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
    @Override
    public Product getProductById(int id){
        String sql = "Select * from product where id = ?";
        Product p = null;
        try(Connection con = this.dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    String image = rs.getString("image");
                    p = new Product(id, name, price, image);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
}
