package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PrincipalView {

	private JFrame frame;

	public PrincipalView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setBounds(100, 100, 831, 494);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar);
		
		JMenuItem cadUsu = new JMenuItem("Cadastro de Usuario");
		menuBar.add(cadUsu);
		cadUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					new UsuarioView();
				
			}
		});
		
		
		
		JMenuItem cadObra = new JMenuItem("Cadastro Obra");
		menuBar.add(cadObra);
		cadObra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						new ObraView();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		
		JMenuItem cadExemplar = new JMenuItem("Cadastro de Exemplar");
		menuBar.add(cadExemplar);
		cadExemplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						new ExemplarView();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				
			}
		});
		
		
		JMenuItem cadAutor = new JMenuItem("Cadastro de Autor");
		menuBar.add(cadAutor);
		cadAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					new AutorView();
				
			}
		});
		
		
		JMenuItem empLivro = new JMenuItem("Emprestimo de Livros");
		menuBar.add(empLivro);
		empLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						new EmprestimoView();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		
		
		JMenuItem devLivro = new JMenuItem("Devolucao de Livros");
		menuBar.add(devLivro);
		devLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new DevolucaoView();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
	});
				frame.setVisible(true);
	}

}
