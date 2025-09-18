/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package iuh.fit.edu.controllers;

import iuh.fit.edu.daos.DanhMucDAO;
import iuh.fit.edu.daos.TinTucDao;
import iuh.fit.edu.daos.impl.DanhMucDAOImpl;
import iuh.fit.edu.daos.impl.TinTucDAOImpl;
import iuh.fit.edu.entities.DanhMuc;
import iuh.fit.edu.entities.TinTuc;
import iuh.fit.edu.utils.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@WebServlet(name = "tinTucController", urlPatterns = {"/tintucs", "/tintucs*"})
public class TinTucController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null)
            action = "";
        if(action.equals("list"))
            handleShowListTinTuc(req, resp);
        if(action.equals("add-form"))
            handleShowFormAdd(req, resp);
        else
            handleShowManage(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null)
            action = "";
        if(action.equals("add"))
            handleAddTinTuc(req, resp);
        else
            handleDeleteTinTuc(req, resp);
    }
//    Hàm hiện danh sách tin tức
    private void handleShowListTinTuc(HttpServletRequest req, HttpServletResponse resp){
        try(EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()){
            TinTucDao tinTucDao = new TinTucDAOImpl(entityManager);
            DanhMucDAO danhMucDAO = new DanhMucDAOImpl(entityManager);
            List<TinTuc> tinTucs = new ArrayList<>();
            List<DanhMuc> danhMucs = danhMucDAO.findAll();
            String maDanhMuc = req.getParameter("maDanhMuc");
            if(maDanhMuc == null || "-1".equals(maDanhMuc)){
                tinTucs = tinTucDao.findAll();
            }else {
                tinTucs  = tinTucDao.findTinTucByMaDanhMuc(Long.parseLong(maDanhMuc));
            }

            req.setAttribute("tinTucs", tinTucs);
            req.setAttribute("danhMucs", danhMucs);
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/tintuc/list.jsp");
            dispatcher.forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//    Hàm hiện danh sách tin tức trong quản lý
    private void handleShowManage(HttpServletRequest req, HttpServletResponse resp){
        try(EntityManager entityManager =  EntityManagerFactoryUtil.getEntityManager()) {
            TinTucDao tinTucDao = new TinTucDAOImpl(entityManager);
            List<TinTuc> tinTucs = tinTucDao.findAll();
            req.setAttribute("tinTucs", tinTucs);
            req.getRequestDispatcher("views/tintuc/manage.jsp").forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void handleShowFormAdd(HttpServletRequest req, HttpServletResponse resp){
        try(EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            DanhMucDAO danhMucDAO = new DanhMucDAOImpl(entityManager);
            List<DanhMuc> danhMucs = danhMucDAO.findAll();
            req.setAttribute("danhMucs", danhMucs);
            req.getRequestDispatcher("views/tintuc/add.jsp").forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    Hàm thêm tin tức mới
    private void handleAddTinTuc(HttpServletRequest req, HttpServletResponse resp){
        try(EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()){
            TinTucDao tinTucDao = new TinTucDAOImpl(entityManager);
            DanhMucDAO danhMucDAO = new DanhMucDAOImpl(entityManager);
            List<DanhMuc> danhMucs = danhMucDAO.findAll();
            String maTinTuc = req.getParameter("maTinTuc");
            String tieuDe = req.getParameter("tieuDe");
            String lienKet = req.getParameter("lienKet");
            String maDanhMuc = req.getParameter("maDanhMuc");
            String noiDung = req.getParameter("noiDung");
            DanhMuc danhMuc = danhMucDAO.findById(Long.parseLong(maDanhMuc));
            TinTuc tinTucNew = new TinTuc();
            if (maTinTuc != null && !maTinTuc.isBlank()) {
                tinTucNew.setMaTinTuc(Long.parseLong(maTinTuc));
            }
            tinTucNew.setLienKet(lienKet);
            tinTucNew.setTieuDe(tieuDe);
            tinTucNew.setNoiDungTinTuc(noiDung);
            tinTucNew.setDanhMuc(danhMuc);
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<TinTuc>> violations = validator.validate(tinTucNew);
            if(!violations.isEmpty()){
                req.setAttribute("errors", violations);
                req.setAttribute("tinTuc", tinTucNew);
                req.setAttribute("danhMucs", danhMucs);
                req.getRequestDispatcher("views/tintuc/add.jsp").forward(req, resp);
            }
            if(tinTucDao.save(tinTucNew) != null){
                resp.sendRedirect(req.getContextPath() + "/tintucs?action=list");
            }

        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

//    Hàm xóa tin tức
    private void handleDeleteTinTuc(HttpServletRequest req, HttpServletResponse resp){
        try(EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager()) {
            TinTucDao tinTucDao = new TinTucDAOImpl(entityManager);
            String maTinTuc = req.getParameter("id");
            System.out.println(maTinTuc);

            if(tinTucDao.delete(Long.parseLong(maTinTuc))){
                resp.sendRedirect( req.getContextPath() + "/tintucs?action=manage");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
