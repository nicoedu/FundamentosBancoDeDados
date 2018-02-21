package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dao.HorarioDao;
import dominio.Horario;

public class HorarioController {
	public void salvar(String codigo, String idProjeto, String de, String ate) 
			throws SQLException, ParseException, NumberFormatException 
		{
			SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	        Horario horario = new Horario();
	        horario.setCodigoDesenvolvedor(Integer.valueOf(codigo));
	        horario.setIdProjeto(Integer.valueOf(idProjeto));
	        horario.setInicio(dt1.parse(de));
	        horario.setFim(dt1.parse(de));
	        
	        new HorarioDao().salvar(horario);
	        
	    }

	    public void alterar(int codigoAntigo, int idAntigo, Date dataAntiga, String codigo, String idProjeto, String de, String ate) 
			throws SQLException, ParseException, NumberFormatException 
		{
	    	SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	    	 Horario horario = new Horario();
		        horario.setCodigoDesenvolvedor(Integer.valueOf(codigo));
		        horario.setIdProjeto(Integer.valueOf(idProjeto));
		        horario.setInicio(dt1.parse(de));
		        horario.setFim(dt1.parse(de));
		        

	        new HorarioDao().alterar(horario, codigoAntigo, idAntigo, dataAntiga);
	    }

	    public List<Horario> listaHorarios() throws SQLException{
	        HorarioDao dao = new HorarioDao();
	        try {
	            return dao.findHorarios();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, 
					"Problemas ao localizar horario\n" + 
					e.getLocalizedMessage()
				);
	        }
	        return null;
	    }

	    public void excluir(int id, int codigo, Date de) throws SQLException {
	        new HorarioDao().excluir(id, codigo, de);
	    }

	    public List<Horario> buscaHorarioPorNome(String nome) throws SQLException {
	        HorarioDao dao = new HorarioDao();
	        return dao.findByName(nome);
	    }	
}