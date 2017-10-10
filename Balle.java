import java.awt.*;

public class Balle { 
	
	Color couleur;
	int x,y,largeur,dx,dy;
	double angleX, angleY;
	
	public Balle(Color couleur, int x , int y) {
		this.x = x;
		this.y = y;
		this.largeur = 50;
		this.couleur = couleur;
		this.angleX = Math.random();
		this.angleY = Math.random();
		if(angleX < 0.50) this.dx = 1;
		else this.dx = -1;
		if(angleY < 0.50) this.dy = 1;
		else this.dy = -1;
	}
	
	public void paint(Graphics g) {
		g.setColor(couleur);
		g.fillOval(x, y, largeur, largeur);
	}
	
}