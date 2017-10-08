package Pojo.DAO;

import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;

/**
 * Created by acerioni on 06/09/2016.
 */
public interface GenericDaoInterface<T> {
public T get(Class<T> cl, String id);

public T save(T object);

public void update(T object);

public void delete(T object);

public int countAll(T object);

public void persist(T object);

public void saveOrUpdate(T object);

public List<T> query(String hsql, Map<String, Object> params);

public ObservableList<T> filtrableQuery(String hsql, Map<String, Object> params);

public void printEntity(T object);

public List<T> findAll(Class<T> object);

public ObservableList<T> findAllObservableList(Class<T> object);

public void commit();

public void rollback();

public void closeSession();

}