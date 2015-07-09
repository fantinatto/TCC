


import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;

import javax.swing.JLabel;

public class GUI {
	private JFrame frmGreentxGeneralizing;
	public Arquivo arq = new Arquivo();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmGreentxGeneralizing.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGreentxGeneralizing = new JFrame();
		frmGreentxGeneralizing.setTitle("GREEN-tx       Generalizing RulEs Using Taxonomy");
		frmGreentxGeneralizing.setBounds(100, 100, 1024, 700);
		frmGreentxGeneralizing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JTextArea textAreaTitulo = new JTextArea();
		

		// atribui valores
		textAreaTitulo.setTabSize(12);
		textAreaTitulo.setEditable(false);

		frmGreentxGeneralizing.getContentPane().setLayout(null);

		textAreaTitulo.setBounds(332, 13, 270, 25);
		frmGreentxGeneralizing.getContentPane().add(textAreaTitulo);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 128, 459, 586);
		frmGreentxGeneralizing.getContentPane().add(scrollPane_1);
		
		final JTextArea textAreaFiltro = new JTextArea();
		scrollPane_1.setViewportView(textAreaFiltro);
		textAreaFiltro.setTabSize(1000);
		textAreaFiltro.setEditable(false);

		JMenuBar menuBar = new JMenuBar();
		frmGreentxGeneralizing.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Grafo ar = new Grafo();
				ar.geradorArvores(arq.lista);				
				for (int i = 0; i < ar.regrasFiltradas.size(); i++) {
					textAreaFiltro.append(ar.regrasFiltradas.get(i) + "\n");
				}
				//ajustar regra dentro do texto
				//textAreaTitulo.append(str);
			}
		});

		btnFiltrar.setBounds(505, 128, 97, 25);
		frmGreentxGeneralizing.getContentPane().add(btnFiltrar);
		
		JLabel lblNewLabel = new JLabel("Tipo:");
		lblNewLabel.setBounds(23, 41, 56, 16);
		frmGreentxGeneralizing.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("N\u00FAmero Inicial:");
		lblNewLabel_1.setBounds(23, 70, 111, 16);
		frmGreentxGeneralizing.getContentPane().add(lblNewLabel_1);
		
		JLabel lblRegrasFiltradas = new JLabel("Regras Filtradas:");
		lblRegrasFiltradas.setBounds(24, 99, 136, 16);
		frmGreentxGeneralizing.getContentPane().add(lblRegrasFiltradas);
		
				
		JCheckBoxMenuItem chckbxmntmOpen = new JCheckBoxMenuItem("Open");
		chckbxmntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				// seleciona diretório
				//JFileChooser jf = new JFileChooser();
				//jf.setCurrentDirectory(new File(System.getProperty("user.home")));
				JFileChooser jf = new JFileChooser(
						"C:/Users/Vinicius/Desktop/fantinatto/academico/TCC/alg/dataminingSE");
				// seleciona um arquivo por vez
				jf.setMultiSelectionEnabled(false);
				// mostra janela para secelionar file
				int escolha = jf.showOpenDialog(null);
							
				// se escolheu algum arquivo
				if (escolha == JFileChooser.APPROVE_OPTION) {
					textAreaTitulo.append("Regra: "
							+ jf.getSelectedFile().getName());
					// aloca classe Arquivo

					arq.Arquivo(jf.getSelectedFile().getPath());
					
					/*
					for (int i = 0; i < arq.lista.size(); i++) {
						textArea1.append(arq.lista.get(i) + "\n");
					}
					*/
				} else
					textAreaTitulo.append("Erro ao abrir o arquivo!");
			}
		});
		mnFile.add(chckbxmntmOpen);
	}
}
