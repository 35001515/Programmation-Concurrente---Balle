public class Animation extends Thread{
	
	Fenetre fen;
	
	public Animation(Fenetre fen){
		this.setDaemon(true);
		this.fen = fen;
	}
	
	public void run() {
		try {
			while(true) {
				if(!fen.pause) {
					fen.pan.move();
					if(fen.pan.collision()) {
						if(fen.pan.nombre_balles>0) {
							if(!fen.plus.isEnabled()) fen.plus.setEnabled(true);
						}
						if(fen.pan.nombre_balles==0) {
							fen.moins.setEnabled(false);
							fen.plus.setEnabled(true);
						}
						if(fen.pan.nombre_balles==8) {
							fen.plus.setEnabled(false);
						}
						fen.points++;
						fen.score.setText("SCORE :   " + fen.points);
					}
					sleep(10);
				}
				else sleep(10);
			}
		} catch (InterruptedException e) {}
	}
}





