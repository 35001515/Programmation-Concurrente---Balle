import java.awt.*;
import javax.swing.*;

public class Panneau extends JPanel{

	private static final long serialVersionUID = 1L;
	
	Balle Balles[];
	int nb;
	int nombre_balles;
	
	public Panneau(int n) {
		new JPanel();
		this.nb = n;
		Balles = new Balle[nb];
		nombre_balles = 0;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Balle o : Balles){
			if(o!=null) {
				o.paint(g);
			}
		}
	}

	public void check(Balle b) {
		for(Balle o : Balles){
			if(o!=null && o!=b) {
				int distance = (int) Math.sqrt( Math.pow( b.x - o.x, 2 ) + Math.pow( b.y - o.y, 2 ) );
				if(distance < b.largeur) {
					b.x = (int) (Math.random() * (getWidth()*0.75));
					b.y = (int) (Math.random() * (getWidth()*0.75));
					distance = (int) Math.sqrt( Math.pow( b.x - o.x, 2 ) + Math.pow( b.y - o.y, 2 ) );
					check(b);
				}
			}
		}
	}
	
	public void move() {
		for(Balle ball : Balles) {
			if(ball!=null) {
				if(ball.y + ball.largeur > getHeight() || ball.y < 0) ball.dy = -ball.dy;
				if(ball.x + ball.largeur > getWidth()  || ball.x < 0) ball.dx = -ball.dx;
				ball.y = ball.y - ball.dy;
				ball.x = ball.x - ball.dx;
			}
		}
		repaint();
	}
	
	public boolean collision() {
		for(Balle o : Balles){
			for(Balle ball : Balles) {
				if(o!=null && o!=ball && ball!=null) {
					int distance = (int) Math.sqrt( Math.pow( ball.x - o.x, 2 ) + Math.pow( ball.y - o.y, 2 ) );
					if(distance < ball.largeur) {
						for(int i=0;i<Balles.length;i++) {
							if(Balles[i]==o) {
								for(int j=i;j<Balles.length-1;j++) {
									Balles[j] = Balles[j+1];
								}
								Balles[Balles.length-1] = null;
								nombre_balles--;
							}
						}
						for(int k=0;k<Balles.length;k++) {
							if(Balles[k]==ball) {
								for(int l=k;l<Balles.length-1;l++) {
									Balles[l] = Balles[l+1];
								}
								Balles[Balles.length-1] = null;
								nombre_balles--;
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}
}