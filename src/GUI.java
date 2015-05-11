import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;



public class GUI {	
	private JFrame frame;
	public Arquivo arq = new Arquivo();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 728, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//insere obj. gui
		JButton btnSelecionar = new JButton("Selecionar");		
		btnSelecionar.setBounds(312, 56, 97, 25);
		final JTextArea textAreaTitulo = new JTextArea();
		final JTextArea textArea1 = new JTextArea();
		
	
		//atribui valores
		textAreaTitulo.setTabSize(12);
		textAreaTitulo.setEditable(false);	
		textArea1.setTabSize(100);
		textArea1.setEditable(false);
		textArea1.setBounds(12, 57, 270, 381);
		
		
		//ações
		btnSelecionar.addActionListener(new ActionListener() {	

			public void actionPerformed(ActionEvent arg0) {
				//seleciona diretório
				//****abrir do diretorio que estiver executando
				JFileChooser jf = new  JFileChooser("C:/Users/Vinicius/Desktop/fantinatto/academico/TCC/alg/dataminingSE");
				//seleciona um arquivo por vez
				jf.setMultiSelectionEnabled(false);
				//mostra janela para secelionar file
				int escolha = jf.showOpenDialog(null);
				
				//se escolheu algum arquivo
				if(escolha == JFileChooser.APPROVE_OPTION){
					textAreaTitulo.append("Regra: "+jf.getSelectedFile().getName());
					
					//aloca classe Arquivo
					
					arq.Arquivo(jf.getSelectedFile().getPath());
					
					for(int i = 0; i < arq.lista.size(); i++){
						textArea1.append(arq.lista.get(i)+"\n");						
					}
				}
				else
					textAreaTitulo.append("Erro ao abrir o arquivo!");
				//textArea1.append();
			}
		});
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnSelecionar);
		frame.getContentPane().add(textArea1);
		
		
		textAreaTitulo.setBounds(12, 28, 270, 25);
		frame.getContentPane().add(textAreaTitulo);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Arvore ar = new Arvore();
				ar.geradorArvores(arq.lista);
			}
		});
		btnFiltrar.setBounds(312, 115, 97, 25);
		frame.getContentPane().add(btnFiltrar);
		
		
	}
}
