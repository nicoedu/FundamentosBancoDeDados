package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.DesenvolvedorDao;
import dominio.Desenvolvedor;

public class DesenvolvedorController {
	
	public void salvar(String codigo, String nome, String custo, String idProjeto, Date de, Date ate) 
			throws SQLException, ParseException, NumberFormatException 
		{
	        Desenvolvedor desenvolvedor = new Desenvolvedor();
	        desenvolvedor.setNome(nome);
	        desenvolvedor.setCodigo(Integer.valueOf(codigo));
	        desenvolvedor.setIdProjeto(Integer.valueOf(idProjeto));
	        desenvolvedor.setCusto(Float.valueOf(custo)); //TODO transformar em float de valor real
	        desenvolvedor.setDataDe(de);
	        desenvolvedor.setDataAte(ate);
	        
	        new DesenvolvedorDao().salvar(desenvolvedor);
	        
	    }

	    public void alterar(int codigoAntigo, String codigo, String nome, String custo, String idProjeto, Date de, Date ate) 
			throws SQLException, ParseException, NumberFormatException 
		{
	        
			Desenvolvedor desenvolvedor = new Desenvolvedor();
			desenvolvedor.setNome(nome);
	        desenvolvedor.setCodigo(Integer.valueOf(codigo));
	        desenvolvedor.setIdProjeto(Integer.valueOf(idProjeto));
	        desenvolvedor.setCusto(Float.valueOf(custo)); //TODO transformar em float de valor real
	        desenvolvedor.setDataDe(de);
	        desenvolvedor.setDataAte(ate);

	        new DesenvolvedorDao().alterar(desenvolvedor, codigoAntigo);
	    }

	    public List<Desenvolvedor> listaDesenvolvedors() throws SQLException{
	        DesenvolvedorDao dao = new DesenvolvedorDao();
	        try {
	            return dao.findDesenvolvedors();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, 
					"Problemas ao localizar desenvolvedor\n" + 
					e.getLocalizedMessage()
				);
	        }
	        return null;
	    }

	    public void excluir(int id) throws SQLException {
	        new DesenvolvedorDao().excluir(id);
	    }

	    public List<Desenvolvedor> buscaDesenvolvedorPorNome(String nome) throws SQLException {
	        DesenvolvedorDao dao = new DesenvolvedorDao();
	        return dao.findByName(nome);
	    }	
}
