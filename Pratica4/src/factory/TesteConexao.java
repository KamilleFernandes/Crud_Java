package factory;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {

   public static void main(String[] args) {
        try {
            Connection connection = new ConnectionFactory().getConnection();
            System.out.println("Conexao aberta!");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}