package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection conexao;
    private static final String URL_CONEXAO = "jdbc:mysql://127.0.0.1/gxs";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
                //System.out.println("conectou");
            } catch (SQLException e) {
                System.out.println("deu merda1 " + e);
            } catch (ClassNotFoundException ex) {
                System.out.println("deu merda2 " + ex);
            }
        }
        return conexao;
    }

    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
            }
        }
    }
}
