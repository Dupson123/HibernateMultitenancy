package com.kk.multitenancy;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author kkluz
 */
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider
{

    @Autowired
    DataSource dataSource;

    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        try {
            connection.createStatement().execute( "SET schema \'" + tenantIdentifier+"\'" );
        }
        catch ( SQLException e ) {
            throw new HibernateException(
                    "Could not alter JDBC connection to specified schema [" +
                            tenantIdentifier + "]",
                    e
            );
        }
        return connection;
    }

    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try {
            connection.createStatement().execute( "SET schema public" );
        }
        catch ( SQLException e ) {
            // on error, throw an exception to make sure the connection is not returned to the pool.
            // your requirements may differ
            throw new HibernateException(
                    "Could not alter JDBC connection to specified schema [" +
                            tenantIdentifier + "]",
                    e
            );
        }
        connection.close();
    }

    public boolean supportsAggressiveRelease()
    {
        return false;
    }


    public boolean isUnwrappableAs( Class aClass )
    {
        return false;
    }

    public <T> T unwrap( Class<T> aClass )
    {
        return null;
    }

    public DataSource getDataSource()
    {
        return dataSource;
    }

    public void setDataSource( DataSource dataSource )
    {
        this.dataSource = dataSource;
    }
}
