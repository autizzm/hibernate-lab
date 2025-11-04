package org.bsuir;

import org.bsuir.dao.DepartmentDAO;
import org.bsuir.dao.DeveloperDAO;
import org.bsuir.dao.UserDAO;
import org.bsuir.model.User;

import java.util.Scanner;

public class Menu {
    User authUser;
    Scanner in = new Scanner(System.in);

    static DeveloperDAO developerDAO = new DeveloperDAO();
    static UserDAO userDAO = new UserDAO();
    static DepartmentDAO departmentDAO = new DepartmentDAO();

    public void menu(User authUser)
    {
        this.authUser = authUser;
        System.out.printf("\nHi, %s. Your role: %s\n", authUser.getDeveloper().getName(), authUser.getUserRole().getRole());
    }

}
