package controller;

import config.dao.CustomerDao;
import model.Customer;
import service.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    CustomerDao customerDao = new CustomerDao();
    CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                List<Customer> customerList = customerDao.showListCustomer();
                request.setAttribute("customer", customerList);
                request.getRequestDispatcher("createCustomer.jsp").forward(request, response);
                break;
            case "edit":
                customerList = customerDao.showListCustomer();
                request.setAttribute("customer", customerList);
                request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
                break;
            case "delete":
                try {
                    deleteCustomer(request, response);
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
                    createCustomer(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    editCustomer(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "search":
                searchByName(request, response);
                break;
        }
    }

    public void searchByName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("search");

        RequestDispatcher dispatcher = request.getRequestDispatcher("showCustomer.jsp");
        List<Customer> customerList = customerDao.searchByName(name);
        ;
        request.setAttribute("customer", customerList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void createCustomer(HttpServletRequest request, HttpServletResponse response) {
        String full_name = request.getParameter("full_name");
        String passwords = request.getParameter("passwords");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int id_role = Integer.parseInt(request.getParameter("id_role"));
        String img = request.getParameter("img");
        Date create_date = Date.valueOf(request.getParameter("create_date"));
        Date modify_date = Date.valueOf(request.getParameter("modify_date"));
        customerService.add(new Customer(full_name, passwords, email, phone, address, id_role, img, create_date, modify_date));
        try {
            response.sendRedirect("/customer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String full_name = request.getParameter("full_name");
        String passwords = request.getParameter("passwords");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int id_role = Integer.parseInt(request.getParameter("id_role"));
        String img = request.getParameter("img");
        Date create_date = Date.valueOf(request.getParameter("create_date"));
        Date modify_date = Date.valueOf(request.getParameter("modify_date"));
        customerService.add(new Customer(id, full_name, passwords, email, phone, address, id_role, img, create_date, modify_date));
        try {
            response.sendRedirect("/customer");
        } catch (IOException e) {
            e.printStackTrace();
            Customer customer = (new Customer(id, full_name, passwords, email, phone, address, id_role, img, create_date, modify_date));
            customerService.edit(id, customer);

            try {
                response.sendRedirect("/customer");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        customerService.delete(id);

        try {
            response.sendRedirect("/customer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/customer/showCustomer.jsp");
        List<Customer> customerList = customerDao.showListCustomer();
        request.setAttribute("customer", customerList);
        dispatcher.forward(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String passwords = request.getParameter("passwords");
        Customer acc = customerDao.loginCustomer(email, passwords);
        if (acc == null) {

            try {
                request.setAttribute("messenger", "Wrong email or password");
                request.getRequestDispatcher("/login").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            HttpSession session = request.getSession();
            try {
                response.sendRedirect("/showMenuCustomer");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
