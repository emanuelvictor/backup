package br.edu.udc.sistemas.IAVI.emanuelvictor.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataBasePool {

	private static DataBasePool dataBasePool = null;
	private String userName;
	private String password;
	private String databaseName;
	private String driver;
	private String url;
	private String port;
	private String host;

    private Connection connectionList[];

	private int currentConnection;
	private int maxConnections;
	private boolean testOnBorrow;
	private String sqlTest;

	private DataBasePool() throws Exception {

		InputStream input = null;
		Properties properties = new Properties();
		try {
			input = new FileInputStream("config.properties");
			properties.load(input);

			this.userName = properties.getProperty("userName");
            this.password = properties.getProperty("password");
            this.databaseName = properties.getProperty("databaseName");
            this.driver = properties.getProperty("driver");
            this.port = properties.getProperty("port");
            this.host = properties.getProperty("host");
            this.url = properties.getProperty("url") + "://" + this.host + ":" + this.port	+ "/" + this.databaseName;

            this.maxConnections = Integer.parseInt(properties.getProperty("maxConnections"));
            this.testOnBorrow = properties.getProperty("testOnBorrow").equals("true");
            this.sqlTest = properties.getProperty("sqlTest");
            this.currentConnection = 0;


            this.initialize();
        } catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (input != null) {
				input.close();
			}
		}

	}

    private void initialize() throws Exception {
        this.connectionList = new Connection[this.maxConnections];
        Class.forName(this.driver);
        for (int i = 0; i < this.maxConnections; i++) {
            this.connect(i);
        }
    }


    private void connect(Integer connectionId) throws Exception {
        this.connectionList[connectionId] = DriverManager.getConnection(this.url, this.userName, this.password);
        this.connectionList[connectionId].setAutoCommit(false);
    }

    private void testConnection(Integer connectionId) throws Exception {
        Connection con = this.connectionList[connectionId];
        if (this.testOnBorrow) {
            if ((con == null) || (con.isClosed())) {
                this.connect(connectionId);
            } else {
                try {
                    Statement stmt = con.createStatement();
                    stmt.execute(this.sqlTest);
                    stmt.close();
                } catch (SQLException e) {
                    this.connect(connectionId);
                }
            }
        }
    }

    public Connection getConnection() throws Exception {
        this.testConnection(this.currentConnection);
        Connection con = this.connectionList[this.currentConnection];

        this.currentConnection++;
        if (this.currentConnection == this.maxConnections) {
            this.currentConnection = 0;
        }

        return con;
    }

	public static DataBasePool getInstance() throws Exception {
		if (dataBasePool == null) {
			dataBasePool = new DataBasePool();
		}
		return dataBasePool;
	}


}
