import java.awt.event.*;
import javax.swing.*;

public class Horloge extends JLabel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private Timer timer = new Timer(1000, this);
	int temps = 0 ;
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source==timer) {
			temps++;
			setText("TEMPS : " + temps + " s ");
			setHorizontalAlignment(JLabel.CENTER);
		}
	}
	
}