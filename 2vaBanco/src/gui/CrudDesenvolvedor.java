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

import controller.DesenvolvedorController;
import dominio.Desenvolvedor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JEditorPane;

public class CrudDesenvolvedor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField textField_3;
	private JLabel lblNewLabel_5;
	private JTable table;

	public CrudDesenvolvedor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(130, 13, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(130, 38, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(204, 10, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(204, 35, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(204, 61, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(204, 86, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(204, 111, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(130, 63, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(130, 89, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(130, 114, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(204, 135, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(130, 139, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 194, 538, 2);
		contentPane.add(separator);
		
		table = new JTable();
		table.setBounds(10, 207, 518, 127);
		contentPane.add(table);
		
		JButton btnListar = new JButton("New button");
		btnListar.setBounds(204, 166, 89, 23);
		contentPane.add(btnListar);
		
		JButton btnInserir = new JButton("New button");
		btnInserir.setBounds(300, 34, 89, 23);
		contentPane.add(btnInserir);
		
		JButton btnBuscar = new JButton("New button");
		btnBuscar.setBounds(300, 83, 113, 26);
		contentPane.add(btnBuscar);
		
		JButton btnEditar = new JButton("New button");
		btnEditar.setBounds(163, 345, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnDeletar = new JButton("New button");
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
			DesenvolvedorController pj = new DesenvolvedorController();
	        try {
	        	pj.excluir(Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0)));
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
		DesenvolvedorController pj = new DesenvolvedorController();
        String id = "";
        String nome= "";
        String numCliente = "";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("No. Cliente");
        try {
            List<Desenvolvedor> ps = pj.buscaDesenvolvedorPorNome(tfNomeDesenvolvedor.getText());
            for(Desenvolvedor p: ps ) {
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
			if(tfIdDesenvolvedor.getText().isEmpty()){
		 		JOptionPane.showMessageDialog(this, "Um número válido deve ser escolhido!");
		 		return;
		 	}
			DesenvolvedorController pj = new DesenvolvedorController();
	        try {
	        	pj.alterar(Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0)), tfIdDesenvolvedor.getText(), tfNomeDesenvolvedor.getText(), tfNumCliente.getText());
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
		DesenvolvedorController pj = new DesenvolvedorController();
        String id = "";
        String nome= "";
        String numCliente = "";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("No. Cliente");
		try{
		List<Desenvolvedor> ps = pj.listaDesenvolvedors();
        for(Desenvolvedor p: ps ) {
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
		DesenvolvedorController pj = new DesenvolvedorController();
        try {
        	pj.salvar( tfIdDesenvolvedor.getText(), tfNomeDesenvolvedor.getText(), tfNumCliente.getText());
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
		tfIdDesenvolvedor.setText("");
		tfNumCliente.setText("");
		tfNomeDesenvolvedor.setText("");
    }
}