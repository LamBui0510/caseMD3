package controller;


import config.dao.ManagerDao;

import config.dao.RoleDao;
import model.Manager;
import model.Role;
import service.ManagerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
@WebServlet(urlPatterns = "/admin")
public class ManagerServlet extends HttpServlet {
    ManagerDao managerDao = new ManagerDao();
    ManagerService managerService = new ManagerService();
    RoleDao roleDao = new RoleDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                List<Manager> managerList = managerDao.showListManager();
                request.setAttribute("manager",managerList);
                request.getRequestDispatcher("/view/manager/createManager.jsp").forward(request, response);
                break;
            case "edit":
                managerList = managerDao.showListManager();
                request.setAttribute("manager",managerList);
                request.getRequestDispatcher("/view/manager/editManager.jsp").forward(request, response);
                break;
            case "delete":
                try {
                    deleteManager(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showList(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    createManager(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    editManager(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "search":
                searchByName(request,response);
                break;
        }
    }
    public void searchByName (HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("search");

        RequestDispatcher dispatcher = request.getRequestDispatcher("showManager.jsp");
        List<Manager> managerList = managerDao.searchByName(name);;
        request.setAttribute("manager", managerList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createManager(HttpServletRequest request, HttpServletResponse response) {
        int id_role = Integer.parseInt(request.getParameter("id_role"));
        String user_name = request.getParameter("user_name");
        String passwords = request.getParameter("passwords");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String img = String.valueOf(Integer.parseInt(request.getParameter("img")));
        Double salary = Double.valueOf(request.getParameter("salary"));
        Double coefficients_salary = Double.valueOf(request.getParameter("coefficients_salary"));
        String status = request.getParameter("status");
        Date create_date = Date.valueOf(request.getParameter("create_date"));
        Date modify_date = Date.valueOf(request.getParameter("modify_date"));
        managerService.add(new Manager(id_role,user_name, passwords, email,phone,address,img,salary,coefficients_salary,status,create_date,modify_date));
        try {
            response.sendRedirect("/manager");
        } catch (IOException e) {
            e.printStackTrace();
        }}

    public void editManager(HttpServletRequest request, HttpServletResponse response)  {
        int id = Integer.parseInt(request.getParameter("id"));
        int id_role = Integer.parseInt(request.getParameter("id_role"));
        String user_name = request.getParameter("user_name");
        String passwords = request.getParameter("passwords");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String img = String.valueOf(Integer.parseInt(request.getParameter("img")));
        Double salary = Double.valueOf(request.getParameter("salary"));
        Double coefficients_salary = Double.valueOf(request.getParameter("coefficients_salary"));
        String status = request.getParameter("status");
        Date create_date = Date.valueOf(request.getParameter("create_date"));
        Date modify_date = Date.valueOf(request.getParameter("modify_date"));
        Manager manager = new Manager(id,id_role,user_name, passwords, email,phone,address,img,salary,coefficients_salary,status,create_date,modify_date);
        managerService.edit(id,manager);
        try {
            response.sendRedirect("/manager");
        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.sendRedirect("/customer");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }}

    public void deleteManager(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        managerService.delete(id);

        try {
            response.sendRedirect("/manager");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/manager/showManager.jsp");
        List<Manager> managerList = managerDao.showListManager();
        request.setAttribute("manager",managerList);
        dispatcher.forward(request, response);
    }

}
