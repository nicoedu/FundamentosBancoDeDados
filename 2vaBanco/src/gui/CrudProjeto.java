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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ProjetoController;
import dominio.Projeto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class CrudProjeto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNomeProjeto;
	private JTextField tfNumCliente;
	private JTextField tfIdProjeto;
	private JTable table;

	public CrudProjeto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Selecionar");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuCliente = new JMenuItem("Cliente");
		menuCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrudCliente cdCliente = new CrudCliente();
				CrudProjeto.this.setVisible(false);
				cdCliente.setVisible(true);
			}
		});
		mnNewMenu.add(menuCliente);
		
		JMenuItem menuProjeto = new JMenuItem("Projeto");
		mnNewMenu.add(menuProjeto);
		
		JMenuItem menuDesenvolvedor = new JMenuItem("Desenvolvedor");
		mnNewMenu.add(menuDesenvolvedor);
		
		JMenuItem menuHorario = new JMenuItem("Horário");
		mnNewMenu.add(menuHorario);
		
		tfNomeProjeto = new JTextField();
		tfNomeProjeto.setBounds(209, 50, 86, 20);
		contentPane.add(tfNomeProjeto);
		tfNomeProjeto.setColumns(10);
		
		tfNumCliente = new JTextField();
		tfNumCliente.setBounds(209, 81, 86, 20);
		contentPane.add(tfNumCliente);
		tfNumCliente.setColumns(10);
		
		tfIdProjeto = new JTextField();
		tfIdProjeto.setBounds(209, 22, 86, 20);
		contentPane.add(tfIdProjeto);
		tfIdProjeto.setColumns(10);
		
		JLabel tvIdProjeto = new JLabel("ID");
		tvIdProjeto.setBounds(130, 25, 46, 14);
		contentPane.add(tvIdProjeto);
		
		JLabel tvNomeProjeto = new JLabel("Nome");
		tvNomeProjeto.setBounds(130, 53, 46, 14);
		contentPane.add(tvNomeProjeto);
		
		JLabel tvNumCliente = new JLabel("No. Cliente");
		tvNumCliente.setBounds(130, 84, 46, 14);
		contentPane.add(tvNumCliente);
		
		table = new JTable();
		table.setBounds(10, 151, 523, 172);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		contentPane.add(table);
		
		JButton btInserir = new JButton("Inserir");
		btInserir.setBounds(341, 21, 89, 23);
		contentPane.add(btInserir);
		
		JButton btBuscar = new JButton("Buscar por Nome");
		btBuscar.setBounds(341, 66, 89, 23);
		contentPane.add(btBuscar);
		
		JButton btListar = new JButton("Listar");
		btListar.setBounds(234, 121, 89, 23);
		contentPane.add(btListar);
		
		JButton btEditar = new JButton("Editar");
		btEditar.setBounds(151, 334, 89, 23);
		contentPane.add(btEditar);
		
		JButton btDeletar = new JButton("Deletar");
		btDeletar.setBounds(282, 334, 89, 23);
		contentPane.add(btDeletar);
		
		btInserir.addActionListener(
                new ActionListener() {
                	@Override
                    public void actionPerformed(ActionEvent e) {
                        onClickSalvar();
                    }

                }
        );
		
		btListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickListar();
			}

		});

        btEditar.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        onClickAlterar();
                    }
                }
        );

        btBuscar.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        onClickLocalizar();
                    }
                }
        );
        
        btDeletar.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                onClickExcluir();
            }
        });
	}

	protected void onClickExcluir() {
		if(table.getSelectedRow() < 0){
			JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir!");
		}else if(table.getSelectedRowCount() > 1){
			JOptionPane.showMessageDialog(this, "Selecione somente uma linha para excluir!");
		}else{
			ProjetoController pj = new ProjetoController();
	        try {
	        	pj.excluir(Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0)));
	            JOptionPane.showMessageDialog(this, "Projeto excluido com sucesso!");
	            clearFields();
	            onClickListar();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, 
					"Nao foi possivel excluir o Projeto!n" + 
					e.getLocalizedMessage()
				);
	        }
		}
		
	}

	protected void onClickLocalizar() {
		ProjetoController pj = new ProjetoController();
        String id = "";
        String nome= "";
        String numCliente = "";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("No. Cliente");
        try {
            List<Projeto> ps = pj.buscaProjetoPorNome(tfNomeProjeto.getText());
            for(Projeto p: ps ) {
                id = String.valueOf(p.getId());
                nome = p.getNome();
                numCliente = String.valueOf(p.getNumCliente());
                modelo.addRow(new Object[]{id, nome, numCliente});
            }
            
            table.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
				"Ocorreu um erro, tente novamente! \n" + 
				e.getLocalizedMessage()
			);
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(this, 
				"Projeto não localizdo ou não existe! \n" + 
				e.getLocalizedMessage()
			);
        }
		
	}

	protected void onClickAlterar() {
		if(table.getSelectedRow() < 0){
			JOptionPane.showMessageDialog(this, "Selecione uma linha para alterar!");
		}else if(table.getSelectedRowCount() > 1){
			JOptionPane.showMessageDialog(this, "Selecione somente uma linha para alterar!");
		}else{
			if(tfIdProjeto.getText().isEmpty()){
		 		JOptionPane.showMessageDialog(this, "Um número válido deve ser escolhido!");
		 		return;
		 	}
			ProjetoController pj = new ProjetoController();
	        try {
	        	pj.alterar(Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0)), tfIdProjeto.getText(), tfNomeProjeto.getText(), tfNumCliente.getText());
	            JOptionPane.showMessageDialog(this, "Projeto alterado com sucesso!");
	            clearFields();
	            onClickListar();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, "Nao foi possivel alterar o Projeto!n" + e.getLocalizedMessage());
	        } catch (NumberFormatException e) {
	        	JOptionPane.showMessageDialog(this, "Formato de Número Inválido!");
				e.printStackTrace();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(this, "Entrada inválida!");
				e.printStackTrace();
			}
		}
		
	}

	protected void onClickListar() {
		ProjetoController pj = new ProjetoController();
        String id = "";
        String nome= "";
        String numCliente = "";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("No. Cliente");
		try{
		List<Projeto> ps = pj.listaProjetos();
        for(Projeto p: ps ) {
            id = String.valueOf(p.getId());
            nome = p.getNome();
            numCliente = String.valueOf(p.getNumCliente());
            modelo.addRow(new Object[]{id, nome, numCliente});
        }
        table.setModel(modelo);
		} catch(SQLException e) {
            JOptionPane.showMessageDialog(this, 
				"Ocorreu um erro, tente novamente!n" + 
				e.getLocalizedMessage()
			);
			
		}
		
	}

	protected void onClickSalvar() {
		ProjetoController pj = new ProjetoController();
        try {
        	pj.salvar( tfIdProjeto.getText(), tfNomeProjeto.getText(), tfNumCliente.getText());
            JOptionPane.showMessageDialog(this, "Projeto salvo com sucesso!");
            clearFields();
            onClickListar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
				"Nao foi possivel salvar o Projeto!\n" + 
				e.getLocalizedMessage()
			);
        } catch (ParseException e) {
        	JOptionPane.showMessageDialog(this, "Formato de entrada inválido!");
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(this, "Formato de entrada inválido!");
		}
		
	}
	
	private void clearFields() {
		tfIdProjeto.setText("");
		tfNumCliente.setText("");
		tfNomeProjeto.setText("");
    }
}
