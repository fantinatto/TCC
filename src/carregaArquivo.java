import java.awt.EventQueue;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.JPanel;

import java.awt.BorderLayout;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;



public class carregaArquivo extends JInternalFrame {
	public JDesktopPane desktopPane;
	JProgressBar progressBar = new JProgressBar();
	public Arquivo arq = new Arquivo();
	public String caminho;

	
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					carregaArquivo frame = new carregaArquivo();
					frame.setResizable(true);
					frame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
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
	public carregaArquivo() {
		setClosable(true);
		
		
		start();
		setBounds(1, 1, 385, 233);			
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(48)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(123)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(237, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

	}
	

	private void start(){
		//classe abstrata
		SwingWorker<Boolean, Integer> work = new SwingWorker<Boolean, Integer>(){

			@Override
			protected Boolean doInBackground() throws Exception {	
					
				Thread.sleep(10);
				//arq.Arquivo(caminho);				
				publish(1);
				
				
				for(int i = 0; i <= 100; i++){
					Thread.sleep(10);
					//System.out.println(i);					
					publish(i);
				}
				
				
				return true;
			}
			
			

			@Override
			protected void process(List<Integer> arg0) {
				Integer value = arg0.get(arg0.size() - 1);
				progressBar.setValue(100);
				progressBar.setValue(value);				 
			}



			@Override
			protected void done() {
				
				try {
					Boolean status = get();					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}	
			
		};	
				
		work.execute();
	}

}
