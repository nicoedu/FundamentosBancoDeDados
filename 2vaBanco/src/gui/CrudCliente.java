package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import controller.ClienteController;
import dominio.Cliente;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class CrudCliente extends JFrame {

	private JPanel contentPane;
	private JTextField tfnumero;
	private JTextField tfnome;
	private JTextField tfcpf;
	private JTextField tfemail;
	DefaultTableModel model;
	private String[] columnNames = {"Num Coluna", "Nome", "CPF", "Email"};
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudCliente frame = new CrudCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	public CrudCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 565);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Selecionar");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuCliente = new JMenuItem("Cliente");
		mnNewMenu.add(menuCliente);
		
		JMenuItem menuProjeto = new JMenuItem("Projeto");
		menuProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrudProjeto cdProjeto = new CrudProjeto();
				CrudCliente.this.setVisible(false);
				cdProjeto.setVisible(true);
			}
		});
		mnNewMenu.add(menuProjeto);
		
		JMenuItem menuDesenvolvedor = new JMenuItem("Desenvolvedor");
		mnNewMenu.add(menuDesenvolvedor);
		
		JMenuItem menuHorario = new JMenuItem("Horário");
		mnNewMenu.add(menuHorario);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfnumero = new JTextField();
		tfnumero.setBounds(221, 22, 86, 20);
		contentPane.add(tfnumero);
		tfnumero.setColumns(10);
		
		tfnome = new JTextField();
		tfnome.setBounds(221, 53, 86, 20);
		contentPane.add(tfnome);
		tfnome.setColumns(10);
		
		tfcpf = new JTextField();
		tfcpf.setBounds(221, 84, 86, 20);
		contentPane.add(tfcpf);
		tfcpf.setColumns(10);
		
		tfemail = new JTextField();
		tfemail.setBounds(221, 115, 86, 20);
		contentPane.add(tfemail);
		tfemail.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 179, 558, 2);
		contentPane.add(separator);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(134, 56, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(134, 87, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(134, 118, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNmero = new JLabel("Numero");
		lblNmero.setBounds(134, 25, 46, 14);
		contentPane.add(lblNmero);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(335, 21, 89, 23);
		contentPane.add(btnInserir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(159, 470, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnBuscar = new JButton("Buscar por Nome");
		btnBuscar.setBounds(335, 83, 159, 23);
		contentPane.add(btnBuscar);
		
		table = new JTable();
		table.setBounds(10, 192, 538, 267);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		contentPane.add(table);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(316, 470, 89, 23);
		contentPane.add(btnDeletar);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(218, 145, 89, 23);
		contentPane.add(btnListar);
		
	
		btnInserir.addActionListener(
                new ActionListener() {
                	@Override
                    public void actionPerformed(ActionEvent e) {
                        onClickSalvar();
                    }

                }
        );
		
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickListar();
			}

		});

        btnAlterar.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        onClickAlterar();
                    }
                }
        );

        btnBuscar.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        onClickLocalizar();
                    }
                }
        );
        
        btnDeletar.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                onClickExcluir();
            }
        });
		
	}
	
	private void onClickListar() {
		ClienteController cc = new ClienteController();
        String numero= "";
        String nome= "";
        String cpf = "";
        String email = "";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Numero");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Email");
		try{
		List<Cliente> cs = cc.listaClientes();
        for(Cliente c: cs ) {
            numero = String.valueOf(c.getNumCliente());
            nome = c.getNome();
            cpf = c.getCpf();
            email = c.getEmail(); 
            modelo.addRow(new Object[]{numero, nome, cpf, email});
        }
        table.setModel(modelo);
		} catch(SQLException e) {
            JOptionPane.showMessageDialog(this, 
				"Ocorreu um erro, tente novamente!n" + 
				e.getLocalizedMessage()
			);
			
		}
		
	}
	
	 private void onClickAlterar() {
			if(table.getSelectedRow() < 0){
				JOptionPane.showMessageDialog(this, "Selecione uma linha para alterar!");
			}else if(table.getSelectedRowCount() > 1){
				JOptionPane.showMessageDialog(this, "Selecione somente uma linha para alterar!");
			}else{
				if(tfnumero.getText().isEmpty()){
			 		JOptionPane.showMessageDialog(this, "Um número válido deve ser escolhido!");
			 		return;
			 	}
				ClienteController cc = new ClienteController();
		        try {
		            cc.alterar(Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0)), Integer.valueOf(tfnumero.getText()), tfnome.getText(), tfcpf.getText(), tfemail.getText());
		            JOptionPane.showMessageDialog(this, "Cliente alterado com sucesso!");
		            clearFields();
		            onClickListar();
		        } catch (SQLException e) {
		            JOptionPane.showMessageDialog(this, "Nao foi possivel alterar contato!n" + e.getLocalizedMessage());
		        }
			}
	    }

	    private void onClickSalvar() {
	        ClienteController cc = new ClienteController();
	        try {
	            cc.salvar(tfnome.getText(), tfcpf.getText(), Integer.valueOf(tfnumero.getText()), tfemail.getText());
	            JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
	            clearFields();
	            onClickListar();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, 
					"Nao foi possivel salvar contato!\n" + 
					e.getLocalizedMessage()
				);
	        }
	    }

	    private void onClickExcluir() {
	    	if(table.getSelectedRow() < 0){
				JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir!");
			}else if(table.getSelectedRowCount() > 1){
				JOptionPane.showMessageDialog(this, "Selecione somente uma linha para excluir!");
			}else{
		        ClienteController cc = new ClienteController();
		        try {
		            cc.excluir(Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0)));
		            JOptionPane.showMessageDialog(this, "Cliente excluido com sucesso!");
		            clearFields();
		            onClickListar();
		        } catch (SQLException e) {
		            JOptionPane.showMessageDialog(this, 
						"Nao foi possivel excluir o contato!n" + 
						e.getLocalizedMessage()
					);
		        }
			}
	    }

	    private void onClickLocalizar() {
	        ClienteController cc = new ClienteController();
	        String numero= "";
	        String nome= "";
	        String cpf = "";
	        String email = "";
	        DefaultTableModel modelo = new DefaultTableModel();
	        modelo.addColumn("Numero");
	        modelo.addColumn("Nome");
	        modelo.addColumn("CPF");
	        modelo.addColumn("Email");
	        try {
	            List<Cliente> cs = cc.buscaClientePorNome(tfnome.getText());
	            for(Cliente c: cs ) {
		            numero = String.valueOf(c.getNumCliente());
		            nome = c.getNome();
		            cpf = c.getCpf();
		            email = c.getEmail(); 
		            modelo.addRow(new Object[]{numero, nome, cpf, email});
	            }
	            
	            table.setModel(modelo);
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, 
					"Ocorreu um erro, tente novamente!n" + 
					e.getLocalizedMessage()
				);
	        } catch (NullPointerException e){
	            JOptionPane.showMessageDialog(this, 
					"Cliente não localizdo ou não existe!n" + 
					e.getLocalizedMessage()
				);
	        }
	    }
	    
	    private void clearFields() {
	        tfnome.setText("");
	        tfcpf.setText("");
	        tfnumero.setText("");
	        tfemail.setText("");
	    }
}
