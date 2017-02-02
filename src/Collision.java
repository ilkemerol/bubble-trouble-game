import java.applet.Applet;
import java.applet.AudioClip;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class Collision implements Runnable {
	AudioClip infoSound;
	static String highScorePARSE; 
	static int highScore;
	//ARRAYDEKÝ HIGHSCORE DEÐERÝ
	int InsideArray = Integer.parseInt(OuterFrame.UserList.get(MainFrame.GLO_COUNT+2));
	String insideArray = Integer.toString(InsideArray);
	
	Icon highscoreTABLE = new ImageIcon(getClass().getResource("HandMadeCS.png"));
	//OYUN BÝTÝRME KOÞULU
	static int gameEND = 0;
	//EKRANDAKÝ HIGHSCORE
	JTextArea AREA = new JTextArea();
	
	Icon explosionL = new ImageIcon(getClass().getResource("Explosion.png"));
	Icon explosionM = new ImageIcon(getClass().getResource("ExplosionMed.png"));
	Icon explosionS = new ImageIcon(getClass().getResource("ExplosionS.png"));
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	String currentDate;
	
	
	
	public Collision(){
		JLabel HSTABLE = new JLabel(highscoreTABLE);
		HSTABLE.setBounds(30, 50 , 160,20);
		AREA.setBounds(200, 50 , 200, 20);
		AREA.setOpaque(false);
		AREA.setEditable(false);
		
		AREA.append(insideArray);
		MainFrame.object.add(HSTABLE);
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(true){
			//System.out.println("INT HIGH SCORE:" + highScore+ "STRING:"+highScorePARSE);
			//System.out.println("ARRAY:"+OuterFrame.UserList.get(MainFrame.GLO_COUNT+2));
			//System.out.println("BALL SIZE:"+MainFrame.checkList.size());
			//System.out.println("BALL SIZE:"+MainFrame.checkListMed.size());
			//System.out.println("BALL SIZE:"+MainFrame.checkListS.size());
			for(int count = 0; count < MainFrame.checkList.size() ; count++){
				//ÝLK ARRAY LARGE BALL BÜTÜN COLLISIONLAR 2 IF
				//System.out.println("GLO_COUNT:"+MainFrame.GLO_COUNT);
				if(MainFrame.checkList.get(count).Gen_Ball.getBounds().intersects(MainFrame.Man.getBounds())){
					Date date = Calendar.getInstance().getTime();
					currentDate = dateFormat.format(date); 
					OuterFrame.UserList.add(MainFrame.GLO_COUNT+3, currentDate);
					//System.out.println(currentDate);
					if(highScore > InsideArray){
						OuterFrame.UserList.add(MainFrame.GLO_COUNT+2 , highScorePARSE);
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+3);
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
						JOptionPane.showMessageDialog(MainFrame.Gen_Panel,"GAME OVER.\nYour High Score Updated!!!");
					}
					else{
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
						JOptionPane.showMessageDialog(MainFrame.Gen_Panel,"GAME OVER.");
					}
					
					try {
						OuterFrame.writer = new PrintWriter("Bubble Trouble.txt", "UTF-8");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(String printable : OuterFrame.UserList){
						OuterFrame.writer.print(printable);
						OuterFrame.writer.print(" ");
					}
					OuterFrame.writer.close();
					
					//highScorePARSE = Integer.toString(highScore);
					//OuterFrame.UserList.add(MainFrame.GLO_COUNT+2 , highScorePARSE);
					
					System.exit(0);
				}
				if(MainFrame.checkVisible == 1 && MainFrame.checkList.get(count).Gen_Ball.getBounds().intersects(Bow_Attack.Bow.getBounds())){
					MainFrame.objectPanel.remove(Bow_Attack.Bow);
					Bow_Attack.Bow.setLocation(0, 0);
					MainFrame.objectPanel.repaint();
					MainFrame.checkList.get(count).Gen_Ball.setIcon(explosionL);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//BÜYÜK TOP 100 PUAN
					highScore = highScore + 100;
					highScorePARSE = Integer.toString(highScore);
					MainFrame.object.add(AREA);
					
					Balls addBalls = new Balls(MainFrame.checkList.get(count).getX() - 100 , MainFrame.checkList.get(count).getY() - 10 , 1 , 2);
					MainFrame.checkListMed.add(addBalls);
					MainFrame.objectPanelBall.add(MainFrame.checkListMed.get(MainFrame.checkListMed.size()-1).Gen_Ball);
					
					MainFrame.checkListMed.add(new Balls(MainFrame.checkList.get(count).getX() + 100 , MainFrame.checkList.get(count).getY() - 10 , 2 , 2));
					MainFrame.objectPanelBall.add(MainFrame.checkListMed.get(MainFrame.checkListMed.size()-1).Gen_Ball);
					
					MainFrame.objectPanelBall.remove(MainFrame.checkList.get(count).Gen_Ball);
					MainFrame.checkList.remove(count);
					
					if(MainFrame.checkList.size() == 0){
						gameEND++;
					}
					
					MainFrame.objectPanelBall.repaint();
				}
			}
			//MEDIUM TOPLAR-MEDIUM ARRAY BÜTÜN COLLISION
			for(int countMed = 0 ; countMed < MainFrame.checkListMed.size() ; countMed++){
				if(MainFrame.checkListMed.get(countMed).Gen_Ball.getBounds().intersects(MainFrame.Man.getBounds())){
					Date date = Calendar.getInstance().getTime();
					currentDate = dateFormat.format(date); 
					OuterFrame.UserList.add(MainFrame.GLO_COUNT+3, currentDate);
					//System.out.println(currentDate);
					if(highScore > InsideArray){
						OuterFrame.UserList.add(MainFrame.GLO_COUNT+2 , highScorePARSE);
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+3);
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
						JOptionPane.showMessageDialog(MainFrame.Gen_Panel,"GAME OVER.\nYour High Score Updated!!!");
					}
					else{
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
						JOptionPane.showMessageDialog(MainFrame.Gen_Panel,"GAME OVER.");
					}
					
					try {
						OuterFrame.writer = new PrintWriter("Bubble Trouble.txt", "UTF-8");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(String printable : OuterFrame.UserList){
						OuterFrame.writer.print(printable);
						OuterFrame.writer.print(" ");
					}
					OuterFrame.writer.close();
					
					System.exit(0);
					
				}
				if(MainFrame.checkVisible == 1 && MainFrame.checkListMed.get(countMed).Gen_Ball.getBounds().intersects(Bow_Attack.Bow.getBounds())){
					
					MainFrame.objectPanel.remove(Bow_Attack.Bow);
					MainFrame.objectPanel.setLocation(0, 0);
					MainFrame.objectPanel.repaint();
					
					MainFrame.checkListMed.get(countMed).Gen_Ball.setIcon(explosionM);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//300 PUAN 
					highScore = highScore + 300;
					highScorePARSE = Integer.toString(highScore);
					
					Balls addBall = new Balls(MainFrame.checkListMed.get(countMed).getX() - 100 , MainFrame.checkListMed.get(countMed).getY() , 1 , 3);
					MainFrame.checkListS.add(addBall);
					MainFrame.objectPanelBall.add(MainFrame.checkListS.get(MainFrame.checkListS.size() - 1).Gen_Ball);
					
					Balls addBalls = new Balls(MainFrame.checkListMed.get(countMed).getX() + 100 , MainFrame.checkListMed.get(countMed).getY() , 2 , 3);
					MainFrame.checkListS.add(addBalls);
					MainFrame.objectPanelBall.add(MainFrame.checkListS.get(MainFrame.checkListS.size() - 1).Gen_Ball);
					
					MainFrame.objectPanelBall.remove(MainFrame.checkListMed.get(countMed).Gen_Ball);
					MainFrame.checkListMed.remove(countMed);
					
					if(MainFrame.checkListMed.size() == 0){
						gameEND++;
					}
					
					MainFrame.objectPanelBall.repaint();
						
				}
			}
			//System.out.println("BALL SMALL SIZE:"+MainFrame.checkListS.size());
			//SMALL TOPLAR-SMALL ARRAY
			for(int countS = 0 ; countS < MainFrame.checkListS.size() ;countS++){
				if(MainFrame.checkListS.get(countS).Gen_Ball.getBounds().intersects(MainFrame.Man.getBounds())){
					Date date = Calendar.getInstance().getTime();
					currentDate = dateFormat.format(date); 
					OuterFrame.UserList.add(MainFrame.GLO_COUNT+3, currentDate);
					//System.out.println(currentDate);
					if(highScore > InsideArray){
						OuterFrame.UserList.add(MainFrame.GLO_COUNT+2 , highScorePARSE);
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+3);
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
						JOptionPane.showMessageDialog(MainFrame.Gen_Panel,"GAME OVER.\nYour High Score Updated!!!");
					}
					else{
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
						OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
						JOptionPane.showMessageDialog(MainFrame.Gen_Panel,"GAME OVER.");
					}
					
					try {
						OuterFrame.writer = new PrintWriter("Bubble Trouble.txt", "UTF-8");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(String printable : OuterFrame.UserList){
						OuterFrame.writer.print(printable);
						OuterFrame.writer.print(" ");
					}
					OuterFrame.writer.close();
					

					//highScorePARSE = Integer.toString(highScore);
					//OuterFrame.UserList.add(MainFrame.GLO_COUNT+2 , highScorePARSE);
					
					System.exit(0);
				}
				if(MainFrame.checkVisible == 1 && MainFrame.checkListS.get(countS).Gen_Ball.getBounds().intersects(Bow_Attack.Bow.getBounds())){
					MainFrame.objectPanel.remove(Bow_Attack.Bow);
					MainFrame.objectPanel.setLocation(0, 0);
					MainFrame.objectPanel.repaint();
					
					MainFrame.checkListS.get(countS).Gen_Ball.setIcon(explosionS);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					highScore = highScore + 500;
					highScorePARSE = Integer.toString(highScore);
					
					MainFrame.objectPanelBall.remove(MainFrame.checkListS.get(countS).Gen_Ball);
					MainFrame.checkListS.remove(countS);
					if(MainFrame.checkListS.size() == 0){
						gameEND++;
					}
					
					MainFrame.objectPanelBall.repaint();
					
				}
			}
			
			//BÝTÝRME KOÞULU 
			if(gameEND == 3){
				Date date = Calendar.getInstance().getTime();
				currentDate = dateFormat.format(date); 
				OuterFrame.UserList.add(MainFrame.GLO_COUNT+3, currentDate);
				//System.out.println(currentDate);
				if(highScore > InsideArray){
					OuterFrame.UserList.add(MainFrame.GLO_COUNT+2 , highScorePARSE);
					OuterFrame.UserList.remove(MainFrame.GLO_COUNT+3);
					OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
					OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
					JOptionPane.showMessageDialog(MainFrame.Gen_Panel,"CONGRATULATIONS!!!\nYour High Score Updated!!!");
				}
				else{
					OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
					OuterFrame.UserList.remove(MainFrame.GLO_COUNT+4);
					JOptionPane.showMessageDialog(MainFrame.Gen_Panel,"CONGRATULATIONS!!!");
				}
				try {
					OuterFrame.writer = new PrintWriter("Bubble Trouble.txt", "UTF-8");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(String printable : OuterFrame.UserList){
					OuterFrame.writer.print(printable);
					OuterFrame.writer.print(" ");
				}
				OuterFrame.writer.close();
				System.exit(0);
			}
		}
	}
	

}
