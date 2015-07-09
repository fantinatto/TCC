import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JDesktopPane;
import javax.swing.UIManager;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JCheckBoxMenuItem;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;




public class Menu extends JFrame {
	JPanel panel = new JPanel();
	JDesktopPane desktopPane = new JDesktopPane();
	public Arquivo arq = new Arquivo();
	public ImageIcon firstImg;
	ArrayList<String> lista = new ArrayList<String>();

	private static final long serialVersionUID = 1L;
	private JLabel ImagePanel;

	/**
	 * Launch the application.
	 */	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		inicializaMenu();
		initializePane();		
		
	}
	
	private void inicializaMenu(){
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
				
		setJMenuBar(menuBar);		
		menuBar.add(menu);
		
		firstImg = new ImageIcon("img/menu2.png");		
		ImagePanel = new JLabel(firstImg);
		ImagePanel.setForeground(UIManager.getColor("Button.background"));
		ImagePanel.setBounds(1, 2, 1024, 725);		
		desktopPane.add(ImagePanel);
			
		JButton btnNewButton = new JButton("Abrir");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jf = new JFileChooser(
						"C:/Users/Vinicius/Desktop/fantinatto/academico/TCC/alg/dataminingSE");
				// seleciona um arquivo por vez
				jf.setMultiSelectionEnabled(false);
					            
				// mostra janela para secelionar file
				int escolha = jf.showOpenDialog(null);
							
				// se escolheu algum arquivo
				if (escolha == JFileChooser.APPROVE_OPTION) {										
					/*
					textAreaTitulo.append("Regra: "
							+ jf.getSelectedFile().getName());
					// aloca classe Arquivo
					 */
					
					//initializeInternalFrame();
					carregaArquivo ca = new carregaArquivo();
					ca.setVisible(true);
					arq.Arquivo(jf.getSelectedFile().getPath());
					//ca.setCaminho(jf.getSelectedFile().getPath());
					desktopPane.add(ca);
							        
				} else;
					//textAreaTitulo.append("Erro ao abrir o arquivo!");
				
			}
		});			
		
		menu.add(btnNewButton);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JButton btnGeneralizar = new JButton("Generalizar");
		btnGeneralizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FiltraArquivo filtraArq = new FiltraArquivo();
				filtraArq.setLista(arq.lista);
				filtraArq.setVisible(true);				
				desktopPane.add(filtraArq);
			}
		});
		mnTools.add(btnGeneralizar);
	}
		
	private void initializePane(){	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1029, 800);				
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1011, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
		);		
		
		desktopPane.setBackground(UIManager.getColor("Button.background"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 1011, Short.MAX_VALUE)
		);
		
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
		);
		
		//carrega e insere imagem no Jlabel	
		
		getContentPane().setLayout(groupLayout);
		panel.setLayout(gl_panel);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel, desktopPane, ImagePanel}));
	}
}



