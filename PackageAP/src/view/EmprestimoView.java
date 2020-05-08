package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JList;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.AlunoController;
import controller.EmprestimoController;
import controller.ExemplarController;
import controller.ExemplarEmprestimoController;
import controller.ServidorController;
import controller.UsuarioController;
import controller.UsuarioFoneController;
import dao.AutorDAO;
import dao.EmprestimoDAO;
import dao.ExemplarDAO;
import dao.ObraDAO;
import model.Aluno;
import model.Autor;
import model.Emprestimo;
import model.Exemplar;
import model.ExemplarEmprestimo;
import model.Obra;
import model.Servidor;
import model.Usuario;
import model.UsuarioFone;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class EmprestimoView {

	private JFrame frame;
	private JTextField matriculaUsuario;

	public EmprestimoView() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 706, 537);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JLabel lblTitulo = new JLabel("Emprestimo de Livros");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel lblObra = new JLabel("Obra:");
		
		JComboBox obra = new JComboBox();
		
		ArrayList<Obra> listObra = new ObraDAO().findObraAll();
				
		for(int i=0; i<listObra.size();i++) {
			obra.addItem(listObra.get(i).getCodigo()+"-"+listObra.get(i).getTitulo());
		}
		JLabel lblExemplaresDisponveis = new JLabel("Exemplares Dispon\u00EDveis");
		
		JComboBox exemplares = new JComboBox();
		obra.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				exemplares.setModel(new DefaultComboBoxModel(new String[] {}));
				String[] temp = obra.getSelectedItem().toString().split("-");
				int codObra = Integer.parseInt(temp[0].toString());
				ArrayList<Exemplar> listExemplar;
		try {
			
			listExemplar = new ExemplarDAO().findExemplarbySituacao(codObra,2);
			System.out.println(listExemplar.size());
			
			for(int i=0; i<listExemplar.size();i++) {
				exemplares.addItem(listExemplar.get(i).getCodigo());
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			}});
		JLabel lbldataEmprestimo = new JLabel("Data de Emprestimo:");
		
		JComboBox comboDia = new JComboBox();
		comboDia.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		
		JComboBox cmbMes = new JComboBox();
		cmbMes.setModel(new DefaultComboBoxModel(new String[] {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		
		JComboBox cmbAno = new JComboBox();
		cmbAno.setModel(new DefaultComboBoxModel(new String[] {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		
		JLabel lblDevolucao = new JLabel("Data de Devolucao:");
		
		JLabel lbldataDevolucao = new JLabel("");
		
		
		JLabel lblMatricula = new JLabel("Matricula:");
		
		matriculaUsuario = new JTextField();
		matriculaUsuario.setColumns(10);
		
	JLabel lblNomeBanco = new JLabel("");
		
		JComboBox diaDevolucao = new JComboBox();
		diaDevolucao.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		
		
		JComboBox mesDevolucao = new JComboBox();
		mesDevolucao.setModel(new DefaultComboBoxModel(new String[] {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		
		JComboBox anoDevolucao = new JComboBox();
		anoDevolucao.setModel(new DefaultComboBoxModel(new String[] {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
	

		
		
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
					
			try {
				lblNomeBanco.setText(new UsuarioController().getUsuario(Integer.parseInt(matriculaUsuario.getText())));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
		}
	});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String dataEmprestimo = comboDia.getSelectedItem().toString()+"/"+ (cmbMes.getSelectedIndex()+1)+"/"+cmbAno.getSelectedItem().toString();
				SimpleDateFormat formatEmp = new SimpleDateFormat("dd/MM/yyyy");
				String dataDevolucao = diaDevolucao.getSelectedItem().toString()+"/"+ (mesDevolucao.getSelectedIndex()+1)+"/"+anoDevolucao.getSelectedItem().toString();
				SimpleDateFormat formatDev = new SimpleDateFormat("dd/MM/yyyy");
					
				try {
					EmprestimoController emprestimoController = new EmprestimoController(new Emprestimo(new java.sql.Date(formatEmp.parse(dataEmprestimo).getTime()),new java.sql.Date(formatDev.parse(dataDevolucao).getTime()),Integer.parseInt(matriculaUsuario.getText())));
					Exemplar exemplar = new Exemplar();
					exemplar.setCodigo(Integer.parseInt(exemplares.getSelectedItem().toString()));
					exemplar.setFkSituacao(1);
					new ExemplarDAO().alterar(exemplar);
					int lastId = new EmprestimoDAO().getLastId();
					System.out.println("lastId"+lastId);
					ExemplarEmprestimoController exemplarEmprestimoController = new ExemplarEmprestimoController(new ExemplarEmprestimo(Integer.parseInt(exemplares.getSelectedItem().toString()),lastId));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
			
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblExemplaresDisponveis)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTitulo)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblMatricula)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(matriculaUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnPesquisar))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNome)
									.addGap(18)
									.addComponent(lblNomeBanco))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(btnSalvar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addComponent(lbldataEmprestimo)
													.addComponent(lblDevolucao))
												.addPreferredGap(ComponentPlacement.RELATED, 273, Short.MAX_VALUE)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addComponent(comboDia, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
													.addComponent(diaDevolucao, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
												.addGap(24)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addComponent(mesDevolucao, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
													.addComponent(cmbMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
											.addGroup(groupLayout.createSequentialGroup()
												.addGap(136)
												.addComponent(lbldataDevolucao, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
										.addGap(49)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(anoDevolucao, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
											.addComponent(cmbAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addGap(161)
											.addComponent(exemplares, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(lblObra)
											.addGap(116)
											.addComponent(obra, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)))))
							.addGap(6))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(lblTitulo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMatricula)
						.addComponent(matriculaUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(lblNomeBanco))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblObra)
						.addComponent(obra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbldataDevolucao)
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblExemplaresDisponveis)
								.addComponent(exemplares, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(69)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbldataEmprestimo)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(comboDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(cmbMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(cmbAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(57)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDevolucao)
								.addComponent(diaDevolucao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(14))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(mesDevolucao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(anoDevolucao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)))
					.addComponent(btnSalvar)
					.addGap(75))
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setVisible(true);
	}
	
}
