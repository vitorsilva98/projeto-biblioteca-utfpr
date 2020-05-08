package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.DevolucaoController;
import controller.UsuarioController;
import dao.EmprestimoDAO;
import dao.ExemplarDAO;
import dao.ExemplarEmprestimoDAO;
import dao.ObraDAO;
import model.Devolucao;
import model.Emprestimo;
import model.Exemplar;
import model.ExemplarEmprestimo;
import model.Obra;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class DevolucaoView {

	private JFrame frame;
	private JTextField matriculaUsuario;

	
	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public DevolucaoView() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 840, 542);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("RA/SIAPE");
		
		matriculaUsuario = new JTextField();
		matriculaUsuario.setColumns(10);
		JLabel lblObras = new JLabel("Obra");
		JComboBox obra = new JComboBox();
		JLabel lblExemplar = new JLabel("Exemplar");
		JComboBox exemplar = new JComboBox();
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		JLabel lblNomeBanco = new JLabel("");
		JButton btnPesquisar = new JButton("Pesquisar");
		ArrayList<ExemplarEmprestimo> exemplarEmprestimo = new ArrayList<ExemplarEmprestimo>();
		btnPesquisar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
						
				try {
					lblNomeBanco.setText(new UsuarioController().getUsuario(Integer.parseInt(matriculaUsuario.getText())));
					ArrayList<Emprestimo> emprestimoUsuario = new EmprestimoDAO().findEmprestimoAllByUsuario(Integer.parseInt(matriculaUsuario.getText()));
					for(int i=0; i<emprestimoUsuario.size(); i++) {
						exemplarEmprestimo.add(new ExemplarEmprestimoDAO().findExemplarByIdEmprestimo(emprestimoUsuario.get(i).getIdEmprestimo()));
					}
					ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
					
					for(int i=0; i<exemplarEmprestimo.size();i++) {
						exemplares.add(new ExemplarDAO().findObraByIdExemplar(exemplarEmprestimo.get(i).getIdExemplar(),1));
					}
					ArrayList<Obra> listObra = new ArrayList<Obra>();
					for(int i=0; i<exemplares.size();i++) {
						listObra.add(new ObraDAO().findObraById(exemplares.get(i).getCodigo()));
						
					}
					for(int i=0; i<listObra.size();i++) {
						obra.addItem(listObra.get(i).getCodigo()+"-"+listObra.get(i).getTitulo());
					}
					obra.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						for(int i=0; i<exemplares.size();i++) {
							exemplar.addItem(exemplares.get(i).getCodigo());
							
						}
						
						}
					});
					
					

				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		
		
				
		
		
		JLabel lblDataDevolucao = new JLabel("Data Devolucao");
		
		JComboBox comboDia = new JComboBox();
		comboDia.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		
		
		JComboBox comboMes = new JComboBox();
		comboMes.setModel(new DefaultComboBoxModel(new String[] {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		
		JComboBox comboAno = new JComboBox();
		comboAno.setModel(new DefaultComboBoxModel(new String[] {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		
		JButton btnSalvar = new JButton("Salvar");
			btnSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String dataDevolucao = comboDia.getSelectedItem().toString()+"/"+ (comboMes.getSelectedIndex()+1)+"/"+comboAno.getSelectedItem().toString();
				SimpleDateFormat formatEmp = new SimpleDateFormat("dd/MM/yyyy");
				DevolucaoController devolucaoController;
				ArrayList<Emprestimo> emprestimoUsuarios;
				try {
					emprestimoUsuarios = new EmprestimoDAO().findEmprestimoAllByUsuario(Integer.parseInt(matriculaUsuario.getText()));
					for(int i=0; i< emprestimoUsuarios.size();i++) {
						devolucaoController = new DevolucaoController(new Devolucao(new java.sql.Date(formatEmp.parse(dataDevolucao).getTime()),emprestimoUsuarios.get(i).getIdEmprestimo()));
				
						}
			
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

						}});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNomeBanco)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(matriculaUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnPesquisar)))
					.addGap(712))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblObras)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(obra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(313)
					.addComponent(lblExemplar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(exemplar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(349))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblDataDevolucao)
					.addGap(18)
					.addComponent(comboDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(comboMes, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(491))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(212)
					.addComponent(btnSalvar)
					.addContainerGap(513, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(matriculaUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(btnPesquisar))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNomeBanco))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblObras)
						.addComponent(obra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExemplar)
						.addComponent(exemplar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDevolucao)
						.addComponent(comboDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboMes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(108)
					.addComponent(btnSalvar)
					.addContainerGap(163, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setVisible(true);
	}
}
