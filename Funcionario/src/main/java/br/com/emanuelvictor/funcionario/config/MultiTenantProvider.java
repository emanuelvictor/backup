package br.com.emanuelvictor.funcionario.config;



import org.hibernate.HibernateException;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by emanuelvictor on 31/03/16.
 */
public class MultiTenantProvider implements MultiTenantConnectionProvider, ServiceRegistryAwareService {

    private static final long serialVersionUID = 4368575201221677384L;

    private C3P0ConnectionProvider connectionProvider = null;

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public void injectServices(ServiceRegistryImplementor serviceRegistry) {
        Map lSettings = serviceRegistry.getService(ConfigurationService.class).getSettings();
        connectionProvider = new C3P0ConnectionProvider();
        connectionProvider.injectServices(serviceRegistry);
        connectionProvider.configure(lSettings);
    }

    @Override
    public boolean isUnwrappableAs(Class clazz) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        return null;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        final Connection connection = connectionProvider.getConnection();
        return connection;
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();

        String sql = "CREATE SCHEMA IF NOT EXISTS " +tenantIdentifier+ "\n" +
                "  AUTHORIZATION funcionario;" +
                "CREATE TABLE IF NOT EXISTS "+tenantIdentifier+".autonomo\n" +
                "(\n" +
                "  id bigint NOT NULL,\n" +
                "  empresa_id bigint NOT NULL,\n" +
                "  CONSTRAINT autonomo_pkey PRIMARY KEY (id),\n" +
                "  CONSTRAINT fk_33kpx21m6aumbx5kgvwt5v3qf FOREIGN KEY (id)\n" +
                "      REFERENCES funcionario (id) MATCH SIMPLE\n" +
                "      ON UPDATE NO ACTION ON DELETE NO ACTION,\n" +
                "  CONSTRAINT fk_djdrbsjdtgm2hl8gms2b419lo FOREIGN KEY (empresa_id)\n" +
                "      REFERENCES empresa (id) MATCH SIMPLE\n" +
                "      ON UPDATE NO ACTION ON DELETE NO ACTION\n" +
                ")\n" +
                "WITH (\n" +
                "  OIDS=FALSE\n" +
                ");\n" +
                "ALTER TABLE "+tenantIdentifier+".autonomo\n" +
                "  OWNER TO postgres;"+
                "CREATE TABLE IF NOT EXISTS "+tenantIdentifier+".empresa\n" +
                "(\n" +
                "  id bigserial NOT NULL,\n" +
                "  cnpj character varying(255) NOT NULL,\n" +
                "  CONSTRAINT empresa_pkey PRIMARY KEY (id),\n" +
                "  CONSTRAINT uk_kkt1mk7urprw5n4efkpa8b0ds UNIQUE (cnpj)\n" +
                ")\n" +
                "WITH (\n" +
                "  OIDS=FALSE\n" +
                ");\n" +
                "ALTER TABLE " +tenantIdentifier+ ".empresa\n" +
                "  OWNER TO postgres;" +
                "SET SCHEMA '" + tenantIdentifier + "'";

        try {
            connection.createStatement().execute(sql);
        }
        catch (SQLException e) {
            throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
        }
        return connection;
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        try {System.out.println("WTF is this?");
            connection.createStatement().execute("SET SCHEMA 'A'");
        }
        catch (SQLException e) {
            throw new HibernateException("Could not alter JDBC connection to specified schema [public]", e);
        }
        connectionProvider.closeConnection(connection);
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        releaseAnyConnection(connection);
    }
}