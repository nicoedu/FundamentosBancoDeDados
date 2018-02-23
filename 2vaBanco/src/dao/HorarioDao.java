package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dominio.Horario;

public class HorarioDao extends GenericDao {
	
	 public void salvar(Horario horario) throws SQLException {
	        String insert = "INSERT INTO horario (ID, CODIGO, DATA_HORA_INICIO, DATA_HORA_FIM) VALUES(?,?,?,?)";
	        save(insert, horario.getIdProjeto(), horario.getCodigoDesenvolvedor(), horario.getInicio(), horario.getFim() );
	    }

	    public void alterar(Horario horario, int numero, int codigo, Timestamp data) throws SQLException {
	        String update = "UPDATE horario " +
	                "SET ID = ?, CODIGO = ?, DATA_HORA_INICIO = ?, DATA_HORA_FIM = ? " +
	                "WHERE CODIGO = ? and DATA_HORA_INICIO = ? and ID = ?";
	        update(update, numero, horario.getIdProjeto(), horario.getCodigoDesenvolvedor(), horario.getInicio(), horario.getFim(), codigo, data);
	    }

	    public void excluir(int id, int codigo, Timestamp data) throws SQLException {
	        String delete = "DELETE FROM horario WHERE ID = ? and CODIGO = ? and DATA_HORA_INICIO = ?";
	        delete(delete, id, codigo, data);
	    }

	    public List<Horario> findHorarios() throws SQLException {
	        List<Horario> horarios = new ArrayList<Horario>();

	        String select = "SELECT * FROM HORARIO";

	        PreparedStatement stmt = 
				getConnection().prepareStatement(select);
				
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Horario horario = new Horario();
	            horario.setIdProjeto(rs.getInt("ID"));
	            horario.setCodigoDesenvolvedor(rs.getInt("CODIGO"));
	            horario.setInicio(rs.getTimestamp("DATA_HORA_INICIO"));
	            horario.setFim(rs.getTimestamp("DATA_HORA_FIM"));
	            horarios.add(horario);
	        }

	        rs.close();
	        stmt.close();
	        getConnection().close();

	        return horarios;
	    }

	    public List<Horario> findByName(String nome) throws SQLException {
	        String select = "SELECT * FROM horario WHERE nome LIKE ?";
	        List<Horario> horarios = new ArrayList<Horario>(); 
	        PreparedStatement stmt = 
				getConnection().prepareStatement(select);
				
	        stmt.setString(1, "%" + nome + "%");
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	        	Horario horario = new Horario();
	        	horario.setIdProjeto(rs.getInt("ID"));
	            horario.setCodigoDesenvolvedor(rs.getInt("CODIGO"));
	            horario.setInicio(rs.getTimestamp("DATA_HORA_INICIO"));
	            horario.setFim(rs.getTimestamp("DATA_HORA_FIM"));
	            horarios.add(horario);
	        }

	        rs.close();
	        stmt.close();
	        getConnection().close();

	        return horarios;
	    }

}
