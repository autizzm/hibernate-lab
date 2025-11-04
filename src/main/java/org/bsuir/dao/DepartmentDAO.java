package org.bsuir.dao;

import org.bsuir.config.SessionConfig;
import org.bsuir.model.Department;
import org.bsuir.model.Developer;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepartmentDAO {
    private SessionConfig sc;

    public DepartmentDAO() {
        sc = SessionConfig.getInstanceOfSeccionFactory();
    }

    public List<Department> getDepartments()
    {
        Session session = sc.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Department> deps = session.createQuery("FROM Department", Department.class).getResultList();
        transaction.commit();
        session.close();
        return deps;
    }

    // этот кусок кода решает проблему n+1
    public Set<Department> getDepartmentWithWorkers()
    {
        Session session = sc.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Set<Department> departments = new HashSet<>(session.createQuery("FROM Department d LEFT JOIN FETCH d.developers", Department.class).getResultList());
        transaction.commit();
        session.close();
        return departments;
    }

    public List<Developer> getDevelopersByDepartment(Department department){
        Session session = sc.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Department dep = session.find(Department.class, department.getDepartmentId());
        Hibernate.initialize(dep.getDevelopers());
        List<Developer> devs = dep.getDevelopers();
        transaction.commit();
        session.close();
        return devs;
    }

    public Department findDepartmentByID(String departmentID) {
        Session session = sc.getSessionFactory().openSession();
        session.getTransaction().begin();
        Department dept = session.find(Department.class, departmentID);
        session.getTransaction().commit();
        session.close();
        return dept;
    }

    public Department addDepartament(Department dep)
    {
       Session session = sc.getSessionFactory().openSession();
       session.getTransaction().begin();

       session.persist(dep);

       session.getTransaction().commit();
       session.close();
       return dep;
    }

    public void deleteDepartment(String departmentID)
    {
        Session session = sc.getSessionFactory().openSession();
        session.getTransaction().begin();
        Department dept = session.find(Department.class, departmentID);
        session.remove(dept);
        session.getTransaction().commit();
        session.close();
    }


}
