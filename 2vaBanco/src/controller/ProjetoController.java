package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ProjetoDao;
import dominio.Projeto;



public class ProjetoController {
	
	public void salvar(String nome, String id, String numero) 
			throws SQLException, ParseException 
		{
	        Projeto projeto = new Projeto();
	        projeto.setNome(nome);
	        projeto.setId(Integer.valueOf(id));
	        projeto.setNumCliente(Integer.valueOf(numero));

	        new ProjetoDao().salvar(projeto);
	    }

	    public void alterar(String idAntigo, String nome, String id, String numero) 
			throws SQLException, ParseException 
		{
	        
			Projeto projeto = new Projeto();
			 projeto.setNome(nome);
			 projeto.setId(Integer.valueOf(id));
		     projeto.setNumCliente(Integer.valueOf(numero));

	        new ProjetoDao().alterar(projeto, Integer.valueOf(idAntigo));
	    }

	    public List listaProjetos() throws SQLException{
	        ProjetoDao dao = new ProjetoDao();
	        try {
	            return dao.findProjetos();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, 
					"Problemas ao localizar projeto\n" + 
					e.getLocalizedMessage()
				);
	        }
	        return null;
	    }

	    public void excluir(int id) throws SQLException {
	        new ProjetoDao().excluir(id);
	    }

	    public List<Projeto> buscaProjetoPorNome(String nome) throws SQLException {
	        ProjetoDao dao = new ProjetoDao();
	        return dao.findByName(nome);
	    }	
}
