package PPRegressionTests.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConectaBanco {
	
	

	public Connection conectarBancoTeste() throws IOException {
		Properties prop = new Properties();
		Util util = new Util();
		util.lerArquivo("/home/daiane/Desktop/test.properties", prop);

		String usuario = prop.getProperty("usuario");
		String senha = prop.getProperty("senha");
		String url = prop.getProperty("url");
		System.out.println(url);
		
		Connection conexao = null;

		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			System.out.println("Conexão obtida com sucesso");
			conexao = DriverManager.getConnection(url, usuario, senha);
			System.out.println(conexao);

		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return conexao;

	}

}
