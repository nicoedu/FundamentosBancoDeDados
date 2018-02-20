package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Desenvolvedor;

public class DesenvolvedorDao extends GenericDao {
	
	 public void salvar(Desenvolvedor desenvolvedor) throws SQLException {
	        String insert = "INSERT INTO desenvolvedor (CODIGO, NOME, CUSTO, ID, DE, ATE) VALUES(?,?,?,?,?,?)";
	        save(insert, desenvolvedor.getCodigo(), desenvolvedor.getNome(), desenvolvedor.getCusto(), desenvolvedor.getIdProjeto(), desenvolvedor.getDataDe(),  desenvolvedor.getDataAte());
	    }

	    public void alterar(Desenvolvedor desenvolvedor, int numero) throws SQLException {
	        String update = "UPDATE desenvolvedor " +
	                "CODIGO = ?, NOME = ?, CUSTO = ?, ID = ?, DE = ?, ATE = ?";
	        update(update, numero, desenvolvedor.getCodigo(), desenvolvedor.getNome(), desenvolvedor.getCusto(), desenvolvedor.getIdProjeto(), desenvolvedor.getDataDe(),  desenvolvedor.getDataAte());
	    }

	    public void excluir(int codigo) throws SQLException {
	        String delete = "DELETE FROM desenvolvedor WHERE CODIGO = ?";
	        delete(delete, codigo);
	    }

	    public List<Desenvolvedor> findDesenvolvedors() throws SQLException {
	        List<Desenvolvedor> desenvolvedors = new ArrayList<Desenvolvedor>();

	        String select = "SELECT * FROM PROJETO";

	        PreparedStatement stmt = 
				getConnection().prepareStatement(select);
				
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Desenvolvedor desenvolvedor = new Desenvolvedor();
	            desenvolvedor.setCodigo(rs.getInt("CODIGO"));
	            desenvolvedor.setNome(rs.getString("NOME"));
	            desenvolvedor.setIdProjeto(rs.getInt("ID"));
	            desenvolvedor.setCusto(rs.getFloat("CUSTO"));
	            desenvolvedor.setDataDe(rs.getDate("DE"));
	            desenvolvedor.setDataAte(rs.getDate("ATE"));
	            desenvolvedors.add(desenvolvedor);
	        }

	        rs.close();
	        stmt.close();
	        getConnection().close();

	        return desenvolvedors;
	    }

	    public List<Desenvolvedor> findByName(String nome) throws SQLException {
	        String select = "SELECT * FROM desenvolvedor WHERE nome LIKE ?";
	        List<Desenvolvedor> desenvolvedors = new ArrayList<Desenvolvedor>(); 
	        PreparedStatement stmt = 
				getConnection().prepareStatement(select);
				
	        stmt.setString(1, "%" + nome + "%");
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	        	Desenvolvedor desenvolvedor = new Desenvolvedor();
	        	desenvolvedor.setCodigo(rs.getInt("CODIGO"));
	            desenvolvedor.setNome(rs.getString("NOME"));
	            desenvolvedor.setIdProjeto(rs.getInt("ID"));
	            desenvolvedor.setCusto(rs.getFloat("CUSTO"));
	            desenvolvedor.setDataDe(rs.getDate("DE"));
	            desenvolvedor.setDataAte(rs.getDate("ATE"));
	            desenvolvedors.add(desenvolvedor);
	        }

	        rs.close();
	        stmt.close();
	        getConnection().close();

	        return desenvolvedors;
	    }

}
