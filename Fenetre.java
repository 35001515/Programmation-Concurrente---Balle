import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	Panneau pan = new Panneau(10);
	int points;
	
	Animation mouvement = new Animation(this);
	boolean pause;

	Horloge heure = new Horloge();
	JLabel score = new JLabel("SCORE :  0 ",JLabel.CENTER);
	JButton bouton = new JButton("COMMENCER");
	JButton plus = new JButton(" + ");
	JButton moins = new JButton(" - ");
	
	
	public Fenetre() {
		super("Application");
		setSize(600,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contenu = getContentPane();
		contenu.setLayout(new BorderLayout());
		contenu.add(pan, BorderLayout.CENTER);
		pan.setBackground(Color.white);
		JPanel panel = new JPanel();
		contenu.add(panel,BorderLayout.SOUTH);
		panel.setBackground(new Color(223, 242, 255));
		bouton.addActionListener(this);
		plus.addActionListener(this);
		moins.addActionListener(this);
		bouton.setPreferredSize(new Dimension(120, 30)); 
		plus.setPreferredSize(new Dimension(50, 30)); 
		moins.setPreferredSize(new Dimension(50, 30));
		panel.add(bouton);
		panel.add(plus);
		panel.add(moins);
		JPanel panel_haut = new JPanel();
		contenu.add(panel_haut,BorderLayout.NORTH);
		panel_haut.setBackground(new Color(223, 242, 255));
		panel_haut.setLayout(new GridLayout(1,2));
		heure.setText("TEMPS : 0 s");
		heure.setHorizontalAlignment(JLabel.CENTER);
		panel_haut.add(heure);
		panel_haut.add(score);
		plus.setEnabled(false);
		moins.setEnabled(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Fenetre();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == bouton) {
			if(bouton.getText().equals("COMMENCER")) {
				mouvement.start();
				heure.start();
				bouton.setText("STOP");
				int R = (int)(Math.random()*256);
				int G = (int)(Math.random()*256);
				int B= (int)(Math.random()*256);
				int x1 = (int) (Math.random() * (pan.getWidth()*0.75));
				int y1 = (int) (Math.random() * (pan.getWidth()*0.75));
				pan.Balles[pan.nombre_balles] = new Balle(new Color(R, G, B),x1,y1);
				pan.nombre_balles++;
				plus.setEnabled(true);
				moins.setEnabled(true);
			}
			else {
				if(bouton.getText().equals("START")) {
					bouton.setText("STOP");
					heure.start();
					pause = false;
				}
				else {
					pause = true;
					heure.stop();
					bouton.setText("START");
				}
			}
		}
		
		if(source == plus) {
			if(pan.nombre_balles<pan.nb) {
				int R = (int)(Math.random()*256);
				int G = (int)(Math.random()*256);
				int B= (int)(Math.random()*256);
				int x1 = (int) (Math.random() * (pan.getWidth()*0.75));
				int y1 = (int) (Math.random() * (pan.getWidth()*0.75));
				Balle ball = new Balle(new Color(R, G, B),x1,y1);
				pan.check(ball);
				pan.Balles[pan.nombre_balles] = ball;
				pan.nombre_balles++;
				moins.setEnabled(true);
			}
			if(pan.nombre_balles==pan.nb) {
				plus.setEnabled(false);
			}
		}
		
		if(source == moins) {
			if(pan.nombre_balles>0) {
				pan.nombre_balles--;
				pan.Balles[pan.nombre_balles] = null;
				if(!plus.isEnabled())plus.setEnabled(true);
			}
			if(pan.nombre_balles==0) {
				pan.Balles[pan.nombre_balles] = null;
				moins.setEnabled(false);
				plus.setEnabled(true);
			}
		}
		
		repaint();
	}
}