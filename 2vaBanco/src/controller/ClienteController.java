package controller;

import javax.swing.*;

import dao.ClienteDao;
import dominio.Cliente;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ClienteController {

   public void salvar(String nome, String cpf, int numero, String email) 
		throws SQLException 
	{
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setNumCliente(numero);

        new ClienteDao().salvar(cliente);
    }

    public void alterar(int numeroAntigo, int numero, String nome, String cpf, String email) 
		throws SQLException 
	{
        
		Cliente cliente = new Cliente();
		 cliente.setNome(nome);
	     cliente.setCpf(cpf);
	     cliente.setEmail(email);
	     cliente.setNumCliente(numero);

        new ClienteDao().alterar(cliente, numeroAntigo);
    }

    public List listaClientes() throws SQLException{
        ClienteDao dao = new ClienteDao();
        try {
            return dao.findClientes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
				"Problemas ao localizar cliente\n" + 
				e.getLocalizedMessage()
			);
        }
        return null;
    }

    public void excluir(int id) throws SQLException {
        new ClienteDao().excluir(id);
    }

    public List<Cliente> buscaClientePorNome(String nome) throws SQLException {
        ClienteDao dao = new ClienteDao();
        return dao.findByName(nome);
    }
}
