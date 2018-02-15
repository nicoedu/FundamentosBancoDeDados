package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Projeto;

public class ProjetoDao extends GenericDao {
	
	 public void salvar(Projeto projeto) throws SQLException {
	        String insert = "INSERT INTO projeto (ID, NOME, NUMCLIENTE) VALUES(?,?,?)";
	        save(insert, projeto.getId(), projeto.getNome(), projeto.getNumCliente() );
	    }

	    public void alterar(Projeto projeto, int numero) throws SQLException {
	        String update = "UPDATE projeto " +
	                "SET ID = ?, NOME = ?, NUMCLIENTE = ? " +
	                "WHERE ID = ?";
	        update(update, numero, projeto.getId(), projeto.getNome(), projeto.getNumCliente());
	    }

	    public void excluir(int id) throws SQLException {
	        String delete = "DELETE FROM projeto WHERE ID = ?";
	        delete(delete, id);
	    }

	    public List<Projeto> findProjetos() throws SQLException {
	        List<Projeto> projetos = new ArrayList<Projeto>();

	        String select = "SELECT * FROM PROJETO";

	        PreparedStatement stmt = 
				getConnection().prepareStatement(select);
				
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Projeto projeto = new Projeto();
	            projeto.setId(rs.getInt("ID"));
	            projeto.setNome(rs.getString("NOME"));
	            projeto.setNumCliente(rs.getInt("NUMCLIENTE"));
	            projetos.add(projeto);
	        }

	        rs.close();
	        stmt.close();
	        getConnection().close();

	        return projetos;
	    }

	    public List<Projeto> findByName(String nome) throws SQLException {
	        String select = "SELECT * FROM projeto WHERE nome LIKE ?";
	        List<Projeto> projetos = new ArrayList<Projeto>(); 
	        PreparedStatement stmt = 
				getConnection().prepareStatement(select);
				
	        stmt.setString(1, "%" + nome + "%");
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	        	Projeto projeto = new Projeto();
	            projeto.setId(rs.getInt("ID"));
	            projeto.setNome(rs.getString("NOME"));
	            projeto.setNumCliente(rs.getInt("NUMCLIENTE"));
	            projetos.add(projeto);
	        }

	        rs.close();
	        stmt.close();
	        getConnection().close();

	        return projetos;
	    }

}
