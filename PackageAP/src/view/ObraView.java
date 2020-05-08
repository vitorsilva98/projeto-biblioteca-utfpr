package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.AutorObraController;
import controller.ObraController;
import controller.UsuarioController;
import dao.AutorDAO;
import dao.ObraDAO;
import dao.TipoObraDAO;
import model.Autor;
import model.AutorObra;
import model.Obra;
import model.TipoObra;
import model.Usuario;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ObraView {

	private JFrame frame;
	private JTextField titulo;
	private JTable table;
	private JTextField anoPublicacao;
	private JTextField codigo;

	
	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public ObraView() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 790, 350);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
JLabel lblCodigo = new JLabel("Codigo");
		
		codigo = new JTextField();
		codigo.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Titulo");
		
		titulo = new JTextField();
		titulo.setColumns(10);
		
		JLabel lblAnoDePublicacao = new JLabel("Ano de Publicacao");
		
		JLabel lblNomeDoAutor = new JLabel("Nome do Autor");
		
		ArrayList<Autor> listAutor = new AutorDAO().findAutor();  
		JComboBox autor = new JComboBox();
		for(int i=0; i<listAutor.size();i++) {
			System.out.println(listAutor.get(i).getNomeAutor());
			autor.addItem(listAutor.get(i).getNomeAutor());
		}
		
		JButton btnLimpar = new JButton("Limpar");
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Tipo da Obra", "Titulo", "Autor", "Ano da Publicacao", "Data da Aquisicao", "Situacao", "Data Emprestimo", "Data Prevista Devolucao"},
			},
			new String[] {
				"Tipo da Obra", "Titulo", "Autor", "Ano da Publicacao", "Data da Aquisicao", "Situacao", "Data Emprestimo", "Data Prevista Devolucao"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Object.class, String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		anoPublicacao = new JTextField();
		anoPublicacao.setColumns(10);
		
		JLabel lblTipoDePublicacao = new JLabel("Tipo de Obra");
		
		   
		ArrayList<TipoObra> listTipoObra = new TipoObraDAO().findTipoObra();  
		JComboBox tipoObra = new JComboBox();
		for(int i=0; i<listTipoObra.size();i++) {
			tipoObra.addItem(listTipoObra.get(i).getDescricaoTipoObra());
		}

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			
	
			public void actionPerformed(ActionEvent e) {
			
				try {
					
					ObraController obraController = new ObraController(new Obra(
							Integer.parseInt(codigo.getText()),titulo.getText(), Integer.parseInt(anoPublicacao.getText()), tipoObra.getSelectedIndex()+1));
					
					AutorObraController autorObraController = new AutorObraController (new AutorObra((autor.getSelectedIndex()+1), Integer.parseInt(codigo.getText())));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 707, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(316)
							.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSalvar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTitulo)
								.addComponent(lblNomeDoAutor))
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(autor, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblTipoDePublicacao)
									.addGap(18)
									.addComponent(tipoObra, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(titulo, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblAnoDePublicacao, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(anoPublicacao, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCodigo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(codigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCodigo)
						.addComponent(codigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitulo)
						.addComponent(titulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAnoDePublicacao)
						.addComponent(anoPublicacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNomeDoAutor)
									.addGap(18))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTipoDePublicacao)
										.addComponent(tipoObra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLimpar)
								.addComponent(btnSalvar)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(autor, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
					.addGap(58)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setVisible(true);
	}
}
