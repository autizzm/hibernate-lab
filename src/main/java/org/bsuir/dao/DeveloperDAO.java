package org.bsuir.dao;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.bsuir.config.SessionConfig;
import org.bsuir.model.Department;
import org.bsuir.model.Developer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class DeveloperDAO {
    private final SessionFactory sessionFactory;
    private Session session;
    public DeveloperDAO() {
        sessionFactory = SessionConfig.getInstanceOfSeccionFactory().getSessionFactory();
    }
    public void addDeveloper(Developer developer) {
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.persist(developer);
        transaction.commit();
        session.close();
    }
    public Developer getDeveloperById(Integer id) {
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.find(Developer.class, id);
        transaction.commit();
        session.close();
        return developer;
    }

    public List<Developer> getDevelopers() {
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Developer> developers = session.createQuery("FROM Developer", Developer.class).list();
        transaction.commit();
        session.close();
        return developers;
    }
    public Developer updateDeveloper(Integer id, Integer experience) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.find(Developer.class, id);
        developer.setExperience(experience);
        transaction.commit();
        session.close();
        return developer;
    }

    public Developer updateDevelopersDepartment(Integer id, Department department){
        Session session = this.sessionFactory.openSession();
        session.getTransaction().begin();
        Developer dev = session.find(Developer.class, id);
        dev.setDepartment(department);
        session.merge(dev);
        session.getTransaction().commit();
        session.close();
        return dev;
    }

    public void removeDeveloper(Integer id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.find(Developer.class, id);
        session.remove(developer);
        transaction.commit();
        session.close();
    }


    // найдем всех сотрудников
    public List<Developer> findAllDevs() {
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Developer> criteria = criteriaBuilder.createQuery(Developer.class);
        Root<Developer> devCriteria = criteria.from(Developer.class);
        List <Developer> developers = session.createQuery(criteria).getResultList();
        transaction.commit();
        session.close();
        return developers;
    }

    public List<Developer> findByNameLike(String nameSubStr){
        session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Developer> criteria = criteriaBuilder.createQuery(Developer.class);
        Root<Developer> devCriteria = criteria.from(Developer.class);
        criteria.select(devCriteria).where(criteriaBuilder.like(devCriteria.get("name"), String.format("%%%s%%", nameSubStr)));
        List<Developer> developers = session.createQuery(criteria).getResultList();
        session.getTransaction().commit();
        session.close();
        return developers;
    }

    // найти сотрудников с опытом больше указанного значения, относящихся к специальностям
    public List<Developer> findByExperienceGreaterThanAndSpecialtyIn(Integer experience, ArrayList<String> specialties) {
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Developer> criteria = criteriaBuilder.createQuery(Developer.class);
        Root<Developer> devCriteria = criteria.from(Developer.class);

        criteria.where(criteriaBuilder.and(criteriaBuilder.
                        gt(devCriteria.get("experience"), experience),
                devCriteria.get("specialty").in(specialties)));
        List<Developer> developers = session.createQuery(criteria).getResultList();

        transaction.commit();
        session.close();
        return developers;
    }

    // удалить сотрудника по имени
    public void deleteDevByName(String name){
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<Developer> criteria = criteriaBuilder.createCriteriaDelete(Developer.class);
        Root<Developer> devCriteria = criteria.from(Developer.class);
        criteria.where(criteriaBuilder.equal(devCriteria.get("name"), name));
        session.createMutationQuery(criteria).executeUpdate();
        transaction.commit();
        session.close();
    }



}

