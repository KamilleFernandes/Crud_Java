package dao;

import factory.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import modelo.Cliente;

public class ClienteDAO {
    private Connection connection;
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public ClienteDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Cliente cliente) {
        String sql = "INSERT INTO cliente(nome,cpf,data_nascimento,telefone,endereco,bairro,cidade,estado,cep) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getBairro());
            stmt.setString(7, cliente.getCidade());
            stmt.setString(8, cliente.getEstado());
            stmt.setString(9, cliente.getCep());

            stmt.execute();
            stmt.close();

            clientes.add(cliente);
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public ArrayList<Cliente> buscar(String nome, String cpf) {
        clientes.clear(); 
        String sql = "SELECT * FROM cliente WHERE nome LIKE ? OR cpf = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            stmt.setString(2, cpf);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                c.setTelefone(rs.getString("telefone"));
                c.setEndereco(rs.getString("endereco"));
                c.setBairro(rs.getString("bairro"));
                c.setCidade(rs.getString("cidade"));
                c.setEstado(rs.getString("estado"));
                c.setCep(rs.getString("cep"));
                clientes.add(c); 
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public void alterar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome=?, cpf=?, data_nascimento=?, telefone=?, endereco=?, bairro=?, cidade=?, estado=?, cep=? WHERE id=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getBairro());
            stmt.setString(7, cliente.getCidade());
            stmt.setString(8, cliente.getEstado());
            stmt.setString(9, cliente.getCep());
            stmt.setInt(10, cliente.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM cliente WHERE id=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    
    public static void removerDoVetor(int indice) {
        clientes.remove(indice);
    }
}