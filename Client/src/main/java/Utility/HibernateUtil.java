package Utility;

import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;


public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ServiceRegBuilder = new StandardServiceRegistryBuilder();
            ServiceRegBuilder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = ServiceRegBuilder.configure().build();
            System.out.println("Sessione Factory Created");
            return configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        System.out.println("Get Session Factory");
        return sessionFactory;
    }

    public static void shutdownDatabase() {
        Connection connection;
        SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) sessionFactory;
        try {
            connection = sessionFactoryImplementation.getJdbcServices().getBootstrapJdbcConnectionAccess().obtainConnection();
            if (connection != null)
                connection.close();
            else
                throw new SystemExceptionRefactor("La connection della factory di Hibernate e' nulla ", ExceptionCode.getUtilCode());
        } catch (Exception e) {
            throw new SystemExceptionRefactor().wrap(e, ExceptionCode.getUtilCode());
        }
    }
}
