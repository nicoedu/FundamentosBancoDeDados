package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Cliente;

public class ClienteDao extends GenericDao {
	
	    public void salvar(Cliente cliente) throws SQLException {
	        String insert = "INSERT INTO cliente (NUMCLIENTE, NOME, CPF, EMAIL) VALUES(?,?,?,?)";
	        save(insert, cliente.getNumCliente(), cliente.getNome(), cliente.getCpf(), cliente.getEmail());
	    }

	    public void alterar(Cliente cliente, int numero) throws SQLException {
	        String update = "UPDATE cliente " +
	                "SET NUMCLIENTE = ?, NOME = ?, CPF = ?, EMAIL = ? " +
	                "WHERE NUMCLIENTE = ?";
	        update(update, numero, cliente.getNumCliente(), cliente.getNome(), cliente.getCpf(), cliente.getEmail());
	    }

	    public void excluir(int NUMCLIENTE) throws SQLException {
	        String delete = "DELETE FROM cliente WHERE NUMCLIENTE = ?";
	        delete(delete, NUMCLIENTE);
	    }

	    public List findClientes() throws SQLException {
	        List clientes = new ArrayList();

	        String select = "SELECT * FROM CLIENTE";

	        PreparedStatement stmt = 
				getConnection().prepareStatement(select);
				
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Cliente cliente = new Cliente();
	            cliente.setNumCliente(rs.getInt("NUMCLIENTE"));
	            cliente.setNome(rs.getString("nome"));
	            cliente.setCpf(rs.getString("cpf"));
	            cliente.setEmail(rs.getString("email"));
	            clientes.add(cliente);
	        }

	        rs.close();
	        stmt.close();
	        getConnection().close();

	        return clientes;
	    }

	    public List<Cliente> findByName(String nome) throws SQLException {
	        String select = "SELECT * FROM cliente WHERE nome LIKE ?";
	        List<Cliente> clientes = new ArrayList<Cliente>(); 
	        Cliente cliente = null;
	        PreparedStatement stmt = 
				getConnection().prepareStatement(select);
				
	        stmt.setString(1, "%" + nome + "%");
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	        	cliente = new Cliente();
	            cliente.setNumCliente(rs.getInt("NUMCLIENTE"));
	            cliente.setNome(rs.getString("nome"));
	            cliente.setCpf(rs.getString("cpf"));
	            cliente.setEmail(rs.getString("email"));
	            clientes.add(cliente);
	        }

	        rs.close();
	        stmt.close();
	        getConnection().close();

	        return clientes;
	    }
	
}
