package org.bsuir.config;

import org.bsuir.model.Department;
import org.bsuir.model.Developer;
import org.bsuir.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionConfig {
    private static SessionConfig sc;
    final SessionFactory sessionFactory;

    private SessionConfig() {
        sessionFactory = new Configuration().
                addAnnotatedClass(User.class).
                addAnnotatedClass(Developer.class).
                addAnnotatedClass(Department.class).
                configure().buildSessionFactory();
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public static SessionConfig getInstanceOfSeccionFactory()
    {
        if (sc == null)
            sc = new SessionConfig();
        return sc;
    }
}
