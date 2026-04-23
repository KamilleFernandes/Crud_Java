/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import factory.ConnectionFactory;
import java.sql.*;
import java.sql.PreparedStatement;
import modelo.Cliente;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class ClienteDAO {
    private Connection connection;
    
    public ClienteDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Cliente cliente){
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
        }
        catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
    public List<Cliente> listarTodos() {
    List<Cliente> clientes = new ArrayList<>();
    String sql = "SELECT * FROM cliente";
    
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Cliente cliente = new Cliente();
            
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            
          
            java.sql.Date dataBanco = rs.getDate("data_nascimento");
            if (dataBanco != null) {
                cliente.setDataNascimento(dataBanco.toLocalDate());
            }
           
            
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setCidade(rs.getString("cidade"));
            cliente.setEstado(rs.getString("estado"));
            cliente.setCep(rs.getString("cep"));
            
            clientes.add(cliente);
        }
        
        rs.close();
        stmt.close();
        return clientes;
        
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
    public void alterar(Cliente cliente) {
    String sql = "UPDATE cliente SET nome=?, cpf=?, data_nascimento=?, telefone=?, endereco=?, bairro=?, cidade=?, estado=?, cep=? WHERE id=?";
    
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpf());
        
        stmt.setDate(3, java.sql.Date.valueOf(cliente.getDataNascimento())); 
        
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

    public List<Cliente> clientes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
