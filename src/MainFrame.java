import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.border.Border;



public class MainFrame extends JFrame{
	private JLabel statusLable = new JLabel("Task not complete");
	private JButton startButton = new JButton("Start");
	private JProgressBar progressBar = new JProgressBar();
	 
	
	public MainFrame(String title){
		super(title);
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.NONE;

		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		add(progressBar, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		add(statusLable, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		add(startButton, gc);
		
		  startButton.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent arg0){
				  start();
			  }
		  });
		
		setSize(800,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void start(){
		//classe abstrata
		SwingWorker<Boolean, Integer> work = new SwingWorker<Boolean, Integer>(){

			@Override
			protected Boolean doInBackground() throws Exception {
				
				for(int i = 0; i <= 100; i++){
					Thread.sleep(100);
					System.out.println(i);					
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
					statusLable.setText("Completed with status: " + status);
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
