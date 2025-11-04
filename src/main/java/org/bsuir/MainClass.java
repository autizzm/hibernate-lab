package org.bsuir;

import jakarta.persistence.NoResultException;
import org.bsuir.dao.UserDAO;
import org.bsuir.model.User;

import java.util.Scanner;

public class MainClass {
    static UserDAO userDAO = new UserDAO();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Menu menu = null;
        while (true){
            System.out.print("Username:");
            String username = in.nextLine();
            System.out.print("Password:");
            String password = in.nextLine();
            User user;
            try
            {
                user = userDAO.AuthUser(username, password);
            }
            catch(NoResultException ex)
            {
                System.out.println("Bad credentials");
                continue;
            }
            switch(user.getUserRole())
            {
                case ROLE_ADMIN:
                    menu = new AdminMenu();
                    break;
                case ROLE_USER:
                    menu = new UserMenu();
                    break;
            }
            menu.menu(user);
            break;
        }
    }

}
