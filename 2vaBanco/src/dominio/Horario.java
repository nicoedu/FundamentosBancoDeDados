package dominio;

import java.sql.Timestamp;
import java.util.Date;

public class Horario {
	
	private int idProjeto;
	private int codigoDesenvolvedor;
	private Timestamp inicio;
	private Timestamp fim;
	
	public int getIdProjeto() {
		return idProjeto;
	}
	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}
	public int getCodigoDesenvolvedor() {
		return codigoDesenvolvedor;
	}
	public void setCodigoDesenvolvedor(int codigoDesenvolvedor) {
		this.codigoDesenvolvedor = codigoDesenvolvedor;
	}
	public Timestamp getInicio() {
		return inicio;
	}
	public void setInicio(Timestamp inicio) {
		this.inicio = inicio;
	}
	public Timestamp getFim() {
		return fim;
	}
	public void setFim(Timestamp fim) {
		this.fim = fim;
	}
	
	
}
