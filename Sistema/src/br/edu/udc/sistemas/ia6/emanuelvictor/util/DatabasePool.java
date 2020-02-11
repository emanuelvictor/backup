package br.edu.udc.sistemas.ia6.emanuelvictor.util;

import java.sql.Connection;
import java.sql.DriverManager;

//Esta classe � um singletown. Singletown � um padr�o de projeto (design pattner), para tanto a classe tem que ter tr�s coisas:
//Uma vari�vel est�tica do mesmo tipo da classe
//Um construtor privado
//e um m�todo est�tico para controlar a inst�ncia
public class DatabasePool {

	private static DatabasePool databasePool = null;

	private Connection connectionList[];
	private Integer initialConnections;
	private Integer openedConnections;
	private Integer maxConnections;
	private Integer currentConnection;
	private String host;
	private String port;
	private String databaseName;
	private String user;
	private String password;
	private String driver;
	private String url;

	private DatabasePool() {
		this.host = "localhost";
		this.port = "5432";
		this.databaseName = "postgres";
		this.user = "postgres";
		this.password = "postgres";
		this.driver = "org.postgresql.Driver";
		this.url = "jdbc:postgresql://" + this.host + ":" + this.port + "/"
				+ this.databaseName;
		this.initialConnections = 2;
		this.maxConnections = 10;
		this.currentConnection = 0;
		this.openedConnections = 0;
		this.initialize();
	}

	private void initialize() {
		this.connectionList = new Connection[this.maxConnections];
		for (int i = 0; i < initialConnections; i++) {
			this.connect(i);
		}
	}

	public static DatabasePool getInstance() {
		if (databasePool == null) {
			databasePool = new DatabasePool();
		}
		return databasePool;
	}

	private void connect(Integer connectionId) {
		// Entendendo como funciona o try e catch

		// Neste caso o Class.forName levanta uma excess�o, por isso �
		// necess�rio envolve-la por um try
		// Por�m tamb�m h� o setAutoComit(false) e o getConnection tamb�m
		// levantam excess�es.
		// OU seja, as duas excess�es dever�o ser envolvidas.
		// A excess�o do do class forname pode ser envolvida pelo catch
		// ClassNotFoundException, enquanto as outras demias ser�o envolvidas
		// pelo SQLexception.
		// Ambas classes exception herdam da classe m�e exception.
		try {
			Class.forName(this.driver);
			this.connectionList[connectionId] = DriverManager.getConnection(
					this.url, this.user, this.password);
			this.connectionList[connectionId].setAutoCommit(false);
			this.openedConnections++;
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao se conectar ao banco " + this.driver
					+ "N�o encontrado");
		} catch (Exception e) {
			System.out.println("Erro ao se conectar ao banco ");
		}
	}

	private boolean isConnected(Integer connectionId) throws Exception {
		return ((this.connectionList[connectionId] != null) && (!this.connectionList[connectionId]
				.isClosed()));
	}

	private void updateCurrentConnection() {
		this.currentConnection++;
		if (this.currentConnection == this.maxConnections) {
			this.currentConnection = 0;
		}
		if (this.currentConnection == this.openedConnections) {
			for (int i = this.currentConnection; i < (this.currentConnection + this.openedConnections); i++) {
				this.connect(i);
			}
		}
	}

	public Connection getConnection() throws Exception {
		if (!isConnected(this.currentConnection)) {
			this.openedConnections--;
			this.connect(this.currentConnection);
		}
		Connection con = this.connectionList[this.currentConnection];
		this.updateCurrentConnection();
		return con;
	}
}
