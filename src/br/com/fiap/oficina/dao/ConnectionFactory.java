package br.com.fiap.oficina.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public Connection getConexao() {
		String jdbc = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(jdbc, " rm94267", "230304");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
