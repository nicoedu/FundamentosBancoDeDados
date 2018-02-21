package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HorarioController;
import dominio.Horario;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class CrudHorario extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfCodigo;
	private JTextField tfDe;
	private JTextField tfAte;
	private JTable table;

	public CrudHorario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 443);
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
				CrudHorario.this.setVisible(false);
				cdCliente.setVisible(true);
			}
		});
		mnNewMenu.add(menuCliente);
		
		JMenuItem menuProjeto = new JMenuItem("Projeto");
		menuProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrudProjeto cdProjeto = new CrudProjeto();
				CrudHorario.this.setVisible(false);
				cdProjeto.setVisible(true);
			}
		});
		mnNewMenu.add(menuProjeto);
		
		JMenuItem menuDesenvolvedor = new JMenuItem("Desenvolvedor");
		menuDesenvolvedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrudDesenvolvedor cdDesenvolvedor = new CrudDesenvolvedor();
				CrudHorario.this.setVisible(false);
				cdDesenvolvedor.setVisible(true);
			}
		});
		mnNewMenu.add(menuDesenvolvedor);
		
		JMenuItem menuHorario = new JMenuItem("Horário");
		mnNewMenu.add(menuHorario);
		
		tfId = new JTextField();
		tfId.setBounds(242, 21, 86, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(242, 52, 86, 20);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		tfDe = new JTextField();
		tfDe.setBounds(242, 83, 86, 20);
		contentPane.add(tfDe);
		tfDe.setColumns(10);
		
		tfAte = new JTextField();
		tfAte.setBounds(242, 114, 86, 20);
		contentPane.add(tfAte);
		tfAte.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID do Projeto");
		lblNewLabel.setBounds(129, 24, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cód. Horario");
		lblNewLabel_1.setBounds(129, 55, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Data de Inicio");
		lblNewLabel_2.setBounds(129, 86, 86, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Data de Término");
		lblNewLabel_3.setBounds(129, 117, 80, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(242, 145, 89, 23);
		contentPane.add(btnListar);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(358, 55, 89, 23);
		contentPane.add(btnInserir);
		
		table = new JTable();
		table.setBounds(10, 179, 531, 165);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		contentPane.add(table);
		
		JScrollPane js=new JScrollPane(table);
        js.setVisible(true);
        js.setBounds(10, 179, 531, 165);
        add(js);
		
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(180, 355, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(303, 355, 89, 23);
		contentPane.add(btnExcluir);

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
        
        btnExcluir.addActionListener(new ActionListener(){
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
			HorarioController ho = new HorarioController();
	        try {
	        	SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	        	ho.excluir(Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0)), Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 1)), dt1.parse((String) table.getValueAt(table.getSelectedRow(), 2)));
	            JOptionPane.showMessageDialog(this, "Horario excluido com sucesso!");
	            clearFields();
	            onClickListar();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, 
					"Nao foi possivel excluir o Horario!n" + 
					e.getLocalizedMessage()
				);
	        } catch (ParseException e){
	        	JOptionPane.showMessageDialog(this, 
						"Erro ao computar dados! \n" + 
						e.getLocalizedMessage()
					);
	        }
		}
		
	}

	protected void onClickAlterar() {
		if(table.getSelectedRow() < 0){
			JOptionPane.showMessageDialog(this, "Selecione uma linha para alterar!");
		}else if(table.getSelectedRowCount() > 1){
			JOptionPane.showMessageDialog(this, "Selecione somente uma linha para alterar!");
		}else{
			if(tfCodigo.getText().isEmpty() || tfId.getText().isEmpty() || tfDe.getText().isEmpty()){
		 		JOptionPane.showMessageDialog(this, "Um v válido deve ser escolhido!");
		 		return;
		 	}
			HorarioController ho = new HorarioController();
	        try {
	        	SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	        	ho.alterar(Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0)), Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 1)), dt1.parse((String) table.getValueAt(table.getSelectedRow(), 2)), tfCodigo.getText(), tfId.getText(), tfDe.getText(), tfAte.getText());
	            JOptionPane.showMessageDialog(this, "Horario alterado com sucesso!");
	            clearFields();
	            onClickListar();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(this, "Nao foi possivel alterar o Horario!n" + e.getLocalizedMessage());
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
		HorarioController ho = new HorarioController();
        String id = "";
        String codigo = "";
        String dataDe = "";
        String dataAte = "";
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Projeto");
        modelo.addColumn("Cod. Desenvolvedor");
        modelo.addColumn("   Data de Inicio    ");
        modelo.addColumn("   Data de Término   ");
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		try{
		List<Horario> hs = ho.listaHorarios();
        for(Horario h: hs ) {
            codigo = String.valueOf(h.getCodigoDesenvolvedor());
            id = String.valueOf(h.getIdProjeto());
            dataDe = dt1.format(h.getInicio());
            dataAte = dt1.format(h.getFim());
            modelo.addRow(new Object[]{id, codigo, dataDe, dataAte});
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
		HorarioController ho = new HorarioController();
        try {
        	ho.salvar( tfCodigo.getText(), tfId.getText(), tfDe.getText(), tfAte.getText());
            JOptionPane.showMessageDialog(this, "Horario salvo com sucesso!");
            clearFields();
            onClickListar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
				"Nao foi possivel salvar o Horario!\n" + 
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
		tfId.setText("");
		tfDe.setText("");
		tfAte.setText("");
    }
}
