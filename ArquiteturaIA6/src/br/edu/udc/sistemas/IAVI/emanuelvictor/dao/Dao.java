package br.edu.udc.sistemas.IAVI.emanuelvictor.dao;

import br.edu.udc.sistemas.IAVI.emanuelvictor.util.DataBasePool;
import br.edu.udc.sistemas.IAVI.emanuelvictor.util.Factory;
import br.edu.udc.sistemas.IAVI.emanuelvictor.util.Query;
import br.edu.udc.sistemas.IAVI.emanuelvictor.util.Reflection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by emanuelvictor on 26/08/14.
 */
public abstract class Dao {

    private Connection connection;

    public Dao() throws Exception {
        this.connection = DataBasePool.getInstance().getConnection();
    }

    public Dao(Connection connection) throws Exception {
        this.connection = connection;
    }

    public Connection getConnection() throws Exception {
        return connection;
    }

    public void setConnection(Connection connection) throws Exception {
        this.connection = connection;
    }

    public void commit() throws Exception {
        if (this.connection != null) {
            this.connection.commit();
        }
    }

    public void rollback() throws Exception {
        if (this.connection != null) {
            this.connection.rollback();
        }
        
    }


    public Object save(Object object) throws Exception {
        if (object == null) {
            return null;
        }
        Statement statement = null;
        try {
            statement = this.getConnection().createStatement();
            if (Reflection.getIdFieldValue(object) == null) {
                String sql = Query.getSQLInsert(object);
                statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    Reflection.setIdFieldValue(object, resultSet.getInt(1));
                    resultSet.close();
                }
            } else {
                String sql = Query.getSQLUpdate(object);
                statement.execute(sql);
            }
        } catch (Exception e) {
            this.rollback();
            throw e;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {

            }
        }

        return object;
    }

    public void delete(Object object) throws Exception {
        if (object == null) {
            return;
        }
        Statement statement = null;
        try {
            this.connection.createStatement();
            if (Reflection.getIdFieldValue(object) != null) {
                String sql = Query.getSQLDelete(object);
                statement.executeQuery(sql);
            }
        } catch (Exception e) {
            this.rollback();
            throw e;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {

            }
        }
    }


    public Object[] find(Object object) throws Exception {
        if (object == null) {
            return null;
        }
        Statement statement = null;
        Object[] objects = null;
        try {
            statement = this.connection.createStatement();
            if (Reflection.getIdFieldValue(object) != null) {
                String sql = Query.getSQLSelect(object);
                ResultSet resultSet = statement.executeQuery(sql);
                objects = Factory.create(resultSet, object.getClass());
            }
        } catch (Exception e) {
            this.rollback();
            throw e;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {

            }
        }

        return objects;
    }

    //TODO
    public Object findByPrimaryKey(Object object) throws Exception {
        if (object == null) {
            return null;
        }
        Object idValue = Reflection.getIdFieldValue(object);
        if (idValue != null) {
            Object objectAux = object.getClass().newInstance();
            Reflection.setIdFieldValue(objectAux, idValue);
            return this.find(objectAux)[0];
        } else return null;
    }


}
