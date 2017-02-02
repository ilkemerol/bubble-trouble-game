import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Balls implements Runnable {
	
	JLabel Gen_Ball= new JLabel();;
	Icon gen_Ball;
	//TOP HIZLARI
	static int levelSpeed;
	
	int xAxis;
	int yAxis;
	int GETX;
	int GETY;
	//DÝRECTÝON 1 = LEFT ; 2 = RIGHT;
	int direction;
	//SEKMELER
	int side_x;
	int ground_y;
	
	public Balls(int ball_x,int ball_y,int direction,int kindOfBall){
		Gen_Ball = new JLabel(gen_Ball);
		//LEVELLER ÝÇÝN TOP SAYILARI
		if(kindOfBall == 1){
			gen_Ball = new ImageIcon(getClass().getResource("Red_Ball.png"));
			Gen_Ball.setIcon(gen_Ball);
			Gen_Ball.setBounds(ball_x, ball_y, 100, 100);
			
			this.direction=direction;
			this.xAxis = ball_x;
			this.yAxis = ball_y;
			
			side_x = 910;
			ground_y = 650;
		}
		if(kindOfBall == 2){
			gen_Ball = new ImageIcon(getClass().getResource("Red_Ball_Med.png"));
			Gen_Ball.setIcon(gen_Ball);
			Gen_Ball.setBounds(ball_x, ball_y, 50, 50);
			
			this.direction=direction;
			this.xAxis = ball_x;
			this.yAxis = ball_y;
			
			side_x = 960;
			ground_y = 700;
		}
		if(kindOfBall == 3){
			gen_Ball = new ImageIcon(getClass().getResource("Red_Ball_S.png"));
			Gen_Ball.setIcon(gen_Ball);
			Gen_Ball.setBounds(ball_x, ball_y, 30, 30);
			
			this.direction=direction;
			this.xAxis = ball_x;
			this.yAxis = ball_y;
			
			side_x = 985;
			ground_y = 720;
		}
		new Thread(this).start();
	}
	//COLLISION ÝÇÝN X-Y ALMAK
	public int getX(){
		return GETX;
	}
	public int getY(){
		return GETY;
	}
	public void moveBall(int new_x , int new_y){
		Gen_Ball.setLocation(new_x, new_y);
	}
	@Override
	public void run() {
		double velocity=0;
		double change_y=0;
		double change_x=0;
		boolean flag=true;
		while(true) {
			velocity = velocity + 0.5;
			change_y = 0.01 * velocity * velocity;
			if(flag) {
				if(direction == 1){
					change_x = change_x - 0.5 - levelSpeed;
				}
				if(direction == 2){
					change_x = change_x + 0.5 + levelSpeed;
				}
				moveBall((int)change_x + xAxis , (int)change_y + yAxis);
				GETX=(int)change_x + xAxis;
				GETY=(int)change_y + yAxis;
			}
			if(!flag){
				if(direction == 1){
					change_x = change_x + 0.5 + levelSpeed;
				}
				if(direction == 2){
					change_x = change_x - 0.5 - levelSpeed;
				}
				moveBall((int)change_x + xAxis , (int)change_y + yAxis);
				GETX=(int)change_x + xAxis;
				GETY=(int)change_y + yAxis;
			}
			if(yAxis + change_y > ground_y){
				velocity = - velocity;
				moveBall((int)change_x + xAxis , (int)change_y + yAxis);
				GETX=(int)change_x + xAxis;
				GETY=(int)change_y + yAxis;
			}
			if(direction == 1){
				if(xAxis + change_x > side_x){
					flag=true;
				}
			
				if(xAxis + change_x < 25){
					flag=false;
				}
			}
			if(direction == 2){
				if(xAxis + change_x > side_x){
					flag=false;
				}
			
				if(xAxis + change_x < 25){
					flag=true;
				}
			}
			try {
				Thread.sleep(5);
			} 
		
			catch (InterruptedException events){
				events.printStackTrace();
			}
			
		}
	}
}
	
