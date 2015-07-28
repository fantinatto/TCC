import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

public class FiltraArquivo extends JInternalFrame {
	ArrayList<String> lista = new ArrayList<String>();
	Grafo ar = new Grafo();

	public ArrayList<String> getLista() {
		return lista;
	}

	public void setLista(ArrayList<String> lista) {
		this.lista = lista;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FiltraArquivo frame = new FiltraArquivo();
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
	public FiltraArquivo() {
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 580, 413);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		final JTextArea textAreaRegrasGeneralizadas = new JTextArea();

		JScrollPane scrollPane = new JScrollPane();

		final JLabel label = new JLabel("Tipo:");

		final JLabel label_1 = new JLabel("N\u00FAmero Inicial:");

		final JLabel label_2 = new JLabel("Regras Generalizadas:");

		JButton StartGeneralizaRegras = new JButton("Start");
		StartGeneralizaRegras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				long startTime = System.currentTimeMillis();
				long estimatedTime = System.currentTimeMillis() - startTime;
				System.out.println(System.currentTimeMillis() - startTime);
				ar.geradorArvores(lista);
				for (int i = 0; i < ar.regrasFiltradas.size(); i++) {
					textAreaRegrasGeneralizadas.append(ar.regrasFiltradas
							.get(i) + " " + "" + "\n");
				}
				System.out.println(System.currentTimeMillis() - startTime);
				label_2.setText("Regras Generalizadas: "
						+ String.valueOf(ar.regrasFiltradas.size()));
			}
		});

		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				long startTime = System.currentTimeMillis();
				long estimatedTime = System.currentTimeMillis() - startTime;
				System.out.println(System.currentTimeMillis() - startTime);

				File arquivo = new File("print.txt");
				try (PrintWriter pw = new PrintWriter(arquivo)) {

					for (int i = 0; i < ar.regrasFiltradas.size(); i++) {
						pw.println(ar.regrasFiltradas.get(i) + "\n");

					}
					System.out.println(System.currentTimeMillis() - startTime);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		});

		JButton btnAgrupa = new JButton("Agrupa");
		btnAgrupa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(11)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						label)
																				.addComponent(
																						label_1,
																						GroupLayout.PREFERRED_SIZE,
																						111,
																						GroupLayout.PREFERRED_SIZE))
																.addGap(203)
																.addComponent(
																		btnAgrupa)
																.addGap(18)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING,
																				false)
																				.addComponent(
																						StartGeneralizaRegras,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnExport,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)))
												.addComponent(
														scrollPane,
														GroupLayout.PREFERRED_SIZE,
														459,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(12)
																.addComponent(
																		label_2,
																		GroupLayout.PREFERRED_SIZE,
																		199,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(43, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(1)
																.addComponent(
																		label)
																.addGap(13)
																.addComponent(
																		label_1)
																.addGap(13)
																.addComponent(
																		label_2)
																.addGap(13)
																.addComponent(
																		scrollPane,
																		GroupLayout.PREFERRED_SIZE,
																		253,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.BASELINE)
																				.addComponent(
																						StartGeneralizaRegras)
																				.addComponent(
																						btnAgrupa))
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnExport)))
								.addContainerGap(23, Short.MAX_VALUE)));

		scrollPane.setViewportView(textAreaRegrasGeneralizadas);
		panel.setLayout(gl_panel);

	}
}
