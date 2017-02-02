import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bow_Attack implements Runnable {
	static JLabel Bow;

	Icon bow;

	int bow_x;
	int bow_y;
	int bow_type;
	
	public Bow_Attack(int bow_x, int bow_y , int bow_type) {
		//OK TÝPÝ SEÇME
		if(bow_type == 1){
			bow = new ImageIcon(getClass().getResource("Bow.png"));

			Bow = new JLabel(bow);
			this.bow_type = bow_type;
			this.bow_x=bow_x;
			this.bow_y=bow_y;
			Bow.setBounds(bow_x, bow_y, 33, 720);
		}
		if(bow_type == 2){
			bow = new ImageIcon(getClass().getResource("Second_Bow.jpg"));

			Bow = new JLabel(bow);
			this.bow_type = bow_type;
			this.bow_x=bow_x;
			this.bow_y=bow_y;
			Bow.setBounds(bow_x, bow_y, 10, 720);
		}
	}
	//OKUN HAREKET METODU
	public void moveBoxBow(int newbow_x, int newbow_y) {
		Bow.setLocation(newbow_x , newbow_y);
	}
	@Override
	public void run() {
		while (true) {
			MainFrame.check_Bow = 1;
			bow_y = bow_y - 10;
			moveBoxBow(bow_x + 15 , bow_y - 70);
			//1 SANÝYE BEKLETME
			if(bow_type == 2 && bow_y <= 100){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException event) {
					event.printStackTrace();
				}
			}
			//OKU SÝLME
			if(bow_y <= 100){
				MainFrame.objectPanel.remove(Bow);
				MainFrame.objectPanel.repaint();
				break;
			}
			try {
				Thread.sleep(15);
			} 
			catch (InterruptedException event) {
				event.printStackTrace();
			}
		}
		//TEK OK ÝÇÝN
		MainFrame.check_Bow = 0;
		MainFrame.checkVisible = 0;
	}
}