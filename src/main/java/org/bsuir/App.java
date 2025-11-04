package org.bsuir;

import org.bsuir.dao.DeveloperDAO;
import org.bsuir.model.Developer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        loop:
        while(true){
            System.out.println("1 - populate DB with data\n2 - execute operations\n0 - quit");
            switch(scanner.nextInt()){
                case 1: populateDBWithData(); break;
                case 2: executeOperations(); break;
                case 0: break loop;
                default: System.out.println("Incorrect input. Try again.");
            }
        }
    }

    private static void populateDBWithData(){
        List<Developer> developers = new ArrayList<>(Arrays.asList(
                new Developer("Igor", "Java Developer", 2),
                new Developer("Alexander", "C++ Developer", 4),
                new Developer("Ivan", "DevOps", 3)
        ));

        DeveloperDAO developerDAO = new DeveloperDAO();

        // Check add operation
        developerDAO.addDeveloper(developers.get(0));
        developerDAO.addDeveloper(developers.get(1));
        developerDAO.addDeveloper(developers.get(2));

//        // Print all devs
//        developerDAO.getDevelopers().stream().forEach(System.out::println);
//
//        // check getDeveloper
//        System.out.println(developerDAO.getDeveloperById(2));
//
//        //update devs
//        developerDAO.updateDeveloper(1, 5);
//
//        developerDAO.removeDeveloper(2);
//        developerDAO.getDevelopers().stream().forEach(System.out::println);

    }

    private static void executeOperations(){
        DeveloperDAO developerDAO = new DeveloperDAO();
        developerDAO.findAllDevs().stream().forEach(System.out::println);
        System.out.println(developerDAO.findByNameLike("Ivan"));
        developerDAO.findByNameLike("ya").stream().forEach(System.out::println);
        developerDAO.findByExperienceGreaterThanAndSpecialtyIn(1, new ArrayList<>(List.of("C++ Developer", "Java Developer"))).
                stream().forEach(System.out::println);
        developerDAO.deleteDevByName("Ivan");
        developerDAO.findAllDevs().stream().forEach(System.out::println);
    }

}
