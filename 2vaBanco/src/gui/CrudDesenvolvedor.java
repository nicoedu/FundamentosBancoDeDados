package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.DesenvolvedorController;
import dominio.Desenvolvedor;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JEditorPane;

public class CrudDesenvolvedor extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfNome;
	private JTextField tfIdProjeto;
	private JTextField tfCusto;
	private JTextField tfDataDe;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField tfDataAte;
	private JLabel lblNewLabel_5;
	private JTable table;

	public CrudDesenvolvedor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 421);
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
				CrudDesenvolvedor.this.setVisible(false);
				cdCliente.setVisible(true);
			}
		});
		mnNewMenu.add(menuCliente);
		
		JMenuItem menuProjeto = new JMenuItem("Projeto");
		menuCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrudProjeto cdProjeto = new CrudProjeto();
				CrudDesenvolvedor.this.setVisible(false);
				cdProjeto.setVisible(true);
			}
		});
		mnNewMenu.add(menuProjeto);
		
		JMenuItem menuDesenvolvedor = new JMenuItem("Desenvolvedor");
		mnNewMenu.add(menuDesenvolvedor);
		
		JMenuItem menuHorario = new JMenuItem("Horário");
		mnNewMenu.add(menuHorario);
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(130, 13, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(130, 38, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Id. Projeto");
		lblNewLabel_2.setBounds(130, 63, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Custo");
		lblNewLabel_3.setBounds(130, 89, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Data Inicio");
		lblNewLabel_4.setBounds(130, 114, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Data Termino");
		lblNewLabel_5.setBounds(130, 139, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(204, 10, 86, 20);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		tfNome = new JTextField();
		tfNome.setBounds(204, 35, 86, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfIdProjeto = new JTextField();
		tfIdProjeto.setBounds(204, 61, 86, 20);
		contentPane.add(tfIdProjeto);
		tfIdProjeto.setColumns(10);
		
		tfCusto = new JTextField();
		tfCusto.setBounds(204, 86, 86, 20);
		contentPane.add(tfCusto);
		tfCusto.setColumns(10);
		
		tfDataDe = new JTextField();
		tfDataDe.setBounds(204, 111, 86, 20);
		contentPane.add(tfDataDe);
		tfDataDe.setColumns(10);
		
		tfDataAte = new JTextField();
		tfDataAte.setBounds(204, 135, 86, 20);
		contentPane.add(tfDataAte);
		tfDataAte.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 194, 538, 2);
		contentPane.add(separator);
		
		table = new JTable();
		table.setBounds(10, 207, 518, 127);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		contentPane.add(table);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(204, 166, 89, 23);
		contentPane.add(btnListar);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(300, 34, 89, 23);
		contentPane.add(btnInserir);
		
		JButton btnBuscar = new JButton("Buscar por Nome");
		btnBuscar.setBounds(300, 83, 113, 26);
		contentPane.add(btnBuscar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(163, 345, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(262, 345, 89, 23);
		contentPane.add(btnDeletar);
		
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

        btnEditar.addActionListener(
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

	protected void onClickExcluir() {
		if(table.getSelectedRow() < 0){
			JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir!");
		}else if(table.getSelectedRowCount() > 1){
			JOptionPane.showMessageDialog(this, "Selecione somente uma linha para excluir!");
		}else{
			DesenvolvedorController dv = new DesenvolvedorController();
	        try {
	        	dv.excluir(Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0)));
	            JOptionPane.showMessageDialog(this, "Desenvolvedor excluido com sucesso!");
	            clearFields();
	            onClickListar();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, 
					"Nao foi possivel excluir o Desenvolvedor!n" + 
					e.getLocalizedMessage()
				);
	        }
		}
		
	}

	protected void onClickLocalizar() {
		DesenvolvedorController dv = new DesenvolvedorController();
	     String codigo = "";
	        String nome= "";
	        String idProjeto = "";
	        String custo = "";
	        String dataDe = "";
	        String dataAte = "";
	        DefaultTableModel modelo = new DefaultTableModel();
	        modelo.addColumn("Codigo");
	        modelo.addColumn("Nome");
	        modelo.addColumn("ID projeto");
	        modelo.addColumn("Custo");
	        modelo.addColumn("Data de Inicio");
	        modelo.addColumn("Data de Término");
	        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            List<Desenvolvedor> ds = dv.buscaDesenvolvedorPorNome(tfNome.getText());
            for(Desenvolvedor d: ds ) {
                codigo = String.valueOf(d.getCodigo());
                nome = d.getNome();
                idProjeto = String.valueOf(d.getIdProjeto());
                custo = String.format("%.2f", d.getCusto());
                dataDe = dt1.format(d.getDataDe());
                dataAte = dt1.format(d.getDataAte());
                modelo.addRow(new Object[]{codigo, nome, idProjeto, custo, dataDe, dataAte});
            }
            
            table.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
				"Ocorreu um erro, tente novamente! \n" + 
				e.getLocalizedMessage()
			);
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(this, 
				"Desenvolvedor não localizdo ou não existe! \n" + 
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
			if(tfCodigo.getText().isEmpty() || tfIdProjeto.getText().isEmpty()){
		 		JOptionPane.showMessageDialog(this, "Um número válido deve ser escolhido!");
		 		return;
		 	}
			DesenvolvedorController dv = new DesenvolvedorController();
	        try {
	        	dv.alterar(Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0)), tfCodigo.getText(), tfNome.getText(), tfIdProjeto.getText(), tfCusto.getText(), tfDataDe.getText(), tfDataAte.getText());
	            JOptionPane.showMessageDialog(this, "Desenvolvedor alterado com sucesso!");
	            clearFields();
	            onClickListar();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, "Nao foi possivel alterar o Desenvolvedor!n" + e.getLocalizedMessage());
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
		DesenvolvedorController dv = new DesenvolvedorController();
        String codigo = "";
        String nome= "";
        String idProjeto = "";
        String custo = "";
        String dataDe = "";
        String dataAte = "";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome");
        modelo.addColumn("ID projeto");
        modelo.addColumn("Custo");
        modelo.addColumn("Data de Inicio");
        modelo.addColumn("Data de Término");
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
		try{
		List<Desenvolvedor> ds = dv.listaDesenvolvedors();
        for(Desenvolvedor d: ds ) {
            codigo = String.valueOf(d.getCodigo());
            nome = d.getNome();
            idProjeto = String.valueOf(d.getIdProjeto());
            custo = String.format("%.2f", d.getCusto());
            dataDe = dt1.format(d.getDataDe());
            dataAte = dt1.format(d.getDataAte());
            modelo.addRow(new Object[]{codigo, nome, idProjeto, custo, dataDe, dataAte});
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
		DesenvolvedorController dv = new DesenvolvedorController();
        try {
        	dv.salvar( tfCodigo.getText(), tfNome.getText(), tfIdProjeto.getText(), tfCusto.getText(), tfDataDe.getText(), tfDataAte.getText());
            JOptionPane.showMessageDialog(this, "Desenvolvedor salvo com sucesso!");
            clearFields();
            onClickListar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
				"Nao foi possivel salvar o Desenvolvedor!\n" + 
				e.getLocalizedMessage()
			);
        } catch (ParseException e) {
        	JOptionPane.showMessageDialog(this, "Formato de entrada inválido!");
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(this, "Formato de entrada inválido!");
		}
		
	}
	
	private void clearFields() {
		tfCodigo.setText("");
		tfNome.setText("");
		tfIdProjeto.setText("");
		tfCusto.setText("");
		tfDataDe.setText("");
		tfDataAte.setText("");
    }
}