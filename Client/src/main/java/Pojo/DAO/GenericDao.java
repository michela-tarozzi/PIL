package Pojo.DAO;

import Utility.HibernateUtil;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.lang.reflect.Field;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericDao<T> implements GenericDaoInterface<T> {
    private final SessionFactory session;

    public GenericDao() {
        this.session = HibernateUtil.getSessionFactory();
        if (this.session == null) {
            throw new RuntimeException("SessionFactory is null!!!");
        }
    }

    @Override
    public T get(Class<T> cl, String id) {
        if (id == null) {
            return null;
        }
        T element = null;
        try {
            Session session = this.session.getCurrentSession();
            session.beginTransaction();
            element = session.get(cl, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            handleException(e);
        }

        return element;
    }


    public T get(Class<T> cl, int id) {
        T element = null;
        try {
            Session session = this.session.getCurrentSession();
            session.beginTransaction();
            element = session.get(cl, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            handleException(e);
        }

        return element;
    }


    @Override
    public T save(T object) {
        try {
            Session session = this.session.getCurrentSession();
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            handleException(e);
        }
        return object;
    }


    @Override
    public void update(T object) {
        try {
            Session session = this.session.getCurrentSession();
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        } catch (Exception e) {

            handleException(e);
        }
    }

    @Override
    public void delete(T object) {
        try {
            Session session = this.session.getCurrentSession();
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        } catch (Exception e) {

            handleException(e);
        }
    }


    public void saveOrUpdate(T object) {
        try {
            Session session = this.session.getCurrentSession();
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void persist(T object) {
        try {
            Session session = this.session.getCurrentSession();
            session.beginTransaction();
            session.persist(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            handleException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> query(String hsql, Map<String, Object> params) {
        List<T> result = null;
        try {
            if ((!hsql.toUpperCase().contains("DELETE"))
                    && (!hsql.toUpperCase().contains("UPDATE"))
                    && (!hsql.toUpperCase().contains("INSERT"))) {
                Session session = this.session.getCurrentSession();
                session.beginTransaction();
                Query query = session.createQuery(hsql);
                if (params != null) {
                    params.keySet().stream().forEach((i) -> {
                        query.setParameter(i, params.get(i));
                    });
                }
                result = query.list();
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            handleException(e);
        }
        return result;
    }


    @SuppressWarnings("unchecked")
    @Override
    public ObservableList<T> filtrableQuery(String hsql, Map<String, Object> params) {
        ObservableList<T> result = FXCollections.observableArrayList();
        try {
            if ((!hsql.toUpperCase().contains("DELETE"))
                    && (!hsql.toUpperCase().contains("UPDATE"))
                    && (!hsql.toUpperCase().contains("INSERT"))) {
                Session session = this.session.getCurrentSession();
                session.beginTransaction();
                Query query = session.createQuery(hsql);
                if (params != null) {
                    params.keySet().stream().forEach((i) -> {
                        query.setParameter(i, params.get(i));
                    });
                }
                result.setAll(query.list());
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            handleException(e);
        }
        return result;
    }

    @Deprecated
    @Override
    public int countAll(T ob) {
        return 1;
    }

    @Override
    public void printEntity(T obj) {
        /**Questo metodo è più che altro un esercizio di stile/funzione di debug per non stare a ciclare ogni
         * volta su tutte le proprietà di un'entita. La reflection può essere lenta/devastante**/
        {
            for (Field field : obj.getClass().getDeclaredFields()) {
                try {
                    //field.setAccessible(true); // if you want to modify private fields
                    field.setAccessible(true);
                    System.out.println(field.getName() + " - " + field.get(obj));
                } catch (Exception e) {
                    handleException(e);
                    Logger.getLogger(Pojo.DAO.GenericDao.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    /**
     * Questo metodo è stato creato per pulire i POJO dalle proprietà FX, ma per il momento non ci serve
     **/

    public HashMap MaptEntity(T obj) {
        /*Questo metodo è più che altro un esercizio di stile/funzione di debug per non stare a ciclare ogni volta su tutte
        le proprietà di un'entita. La reflection può essere lenta/devastante*/
        HashMap<String, Object> retMap = new HashMap<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                //field.setAccessible(true); // if you want to modify private fields
                field.setAccessible(true);
                //devo pulire anche esame
                if (!field.getType().getCanonicalName().contains("Property") &&
                        field.getName() != "esami" && field.getName() != "lavoratore")
                    retMap.put(field.getName(), field.get(obj));

            } catch (Exception e) {
                handleException(e);
                Logger.getLogger(Pojo.DAO.GenericDao.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return retMap;
    }

    @Override
    public List<T> findAll(Class<T> object) {
        List<T> result = null;
        try {
            Session session = this.session.getCurrentSession();
            session.beginTransaction();
            result = session.createQuery("from " + object.getName()).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            handleException(e);
        }
        return result;
    }

    @Override
    public ObservableList<T> findAllObservableList(Class<T> object) {
        ObservableList<T> result = FXCollections.observableArrayList();
        Session session = this.session.getCurrentSession();
        session.beginTransaction();
        result.setAll(session.createQuery("from " + object.getName()).list());
        session.flush();
        session.getTransaction().commit();
        return result;
    }

    public List<T> execQuery(String queryName, Map<String, Object> params) {

        List<T> list = null;
        try {
            Session session = this.session.getCurrentSession();
            session.beginTransaction();
            Query query = session.getNamedQuery((queryName));
            if (params != null) {
                params.keySet().stream().forEach((i) -> {
                    query.setParameter(i, params.get(i));
                });
            }
            list = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {

            handleException(e);
        }
        return list;

    }

    @Override
    public void commit() {
        this.session.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void rollback() {
        this.session.getCurrentSession().getTransaction().rollback();
    }

    @Override
    public void closeSession() {
        this.session.close();
    }

    private void handleException(Exception e) {
        rollback();

        if (e.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException ce = (ConstraintViolationException) e.getCause();

            if (ce.getCause() instanceof SQLIntegrityConstraintViolationException) {
                SQLIntegrityConstraintViolationException se = (SQLIntegrityConstraintViolationException) ce.getCause();


                throw new SystemExceptionRefactor().wrap(se, ExceptionCode.getDAOErrorCode());

            }
        } else {
            throw new SystemExceptionRefactor().wrap(e, ExceptionCode.getDAOErrorCode());
        }


    }
}


