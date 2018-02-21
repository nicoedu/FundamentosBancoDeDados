package dominio;

import java.util.Date;

public class Horario {
	
	private int idProjeto;
	private int codigoDesenvolvedor;
	private Date inicio;
	private Date fim;
	
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
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	
	
}
