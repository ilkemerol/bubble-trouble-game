import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
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

import javax.swing.*;

public class MainFrame extends JFrame implements KeyListener{
	static JPanel Gen_Panel = new JPanel();
	static JPanel objectPanel = new JPanel();
	static JPanel objectPanelBall = new JPanel();
	static JPanel object = new JPanel();
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	String currentDate;
	
	JPanel bowtype = new JPanel();
	
	static AudioClip gameSound;
	AudioClip attackSound;
	AudioClip infoSound;
	
	JLabel Background;
	static JLabel Man;
	Bow_Attack RunnableBow;
	JLabel BOWTYPE;
	
	Collision collision;
	
	JMenuBar menuBar = new JMenuBar();
	JMenu Game = new JMenu("Game");
	JMenu Option = new JMenu("Option");
	JMenu Help = new JMenu("Help");
	
	JMenuItem Sub_Exit = new JMenuItem("Exit",new ImageIcon(getClass().getResource("ExitMenuBar.jpg")));
	JMenuItem Sub_Novice = new JMenuItem("Low Speed",new ImageIcon(getClass().getResource("LowSpeed.jpg")));
	JMenuItem Sub_Intermediate = new JMenuItem("Medium Speed",new ImageIcon(getClass().getResource("MediumSpeed.jpg")));
	JMenuItem Sub_Advanced = new JMenuItem("High Speed",new ImageIcon(getClass().getResource("HighSpeed.jpg")));
	JMenuItem Sub_HighScore = new JMenuItem("High Score",new ImageIcon(getClass().getResource("HighScore.jpg")));
	JMenuItem Sub_About = new JMenuItem("About",new ImageIcon(getClass().getResource("About.jpg")));
	
	static ArrayList <Balls> checkList = new ArrayList <Balls> ();
	static ArrayList <Balls> checkListMed = new ArrayList<Balls> ();
	static ArrayList <Balls> checkListS = new ArrayList<Balls> ();
	
	Icon background;
	Icon man;
	
	Icon BowType = new ImageIcon(getClass().getResource("BowType_1.png"));
	
	int bow_type = 1;
	
	static int check_Bow = 0;
	static int GLO_COUNT;
	
	static int man_x = 500;
	static int man_y = 650;
	/*int ball_x = 300;
	int ball_y = 200;
	int ball_2_x = 800;
	int ball_2_y = 200;*/
	
	int bow_x = man_x;
	int bow_y = man_y;
	static int checkVisible = 0;
	
	public MainFrame(){
		super("Bubble Trouble");
		addKeyListener(this);
		setLayout(new BorderLayout());
		Gen_Panel.setLayout(new BorderLayout() );
		objectPanel.setLayout(null);
		objectPanel.setOpaque(false);
		objectPanel.setBounds(0 , 0 , 1024 , 768);
		
		objectPanelBall.setLayout(null);
		objectPanelBall.setOpaque(false);
		objectPanelBall.setBounds(0 , 0 , 1024 , 768);
		
		object.setLayout(null);
		object.setOpaque(false);
		object.setBounds(0 , 0 , 1024 , 768);
		
		URL urlgameSound = OuterFrame.class.getResource("GameSound.wav");
		gameSound = Applet.newAudioClip(urlgameSound);
        gameSound.loop();
		
		for(int count = 0 ; count < OuterFrame.UserList.size() ; count++){
        	if(OuterFrame.UserList.get(count).equals(OuterFrame.GLO_Name) && OuterFrame.UserList.get(count+1).equals(OuterFrame.GLO_Password)){
        		//System.out.printf("%s",OuterFrame.UserList.get(count));
        		GLO_COUNT = count;
        	}
        }
		
		BOWTYPE = new JLabel(BowType);
		BOWTYPE.setBounds(30, 100, 50, 50);
		object.add(BOWTYPE);
		
		background = new ImageIcon(getClass().getResource("Background.jpg"));
		
		man = new ImageIcon(getClass().getResource("Man_B.png"));
		
		Background = new JLabel(background);
		Background.setLocation(0 , 0);
		Man = new JLabel(man);
		Man.setBounds(man_x , man_y , 62 , 99);
		Man.setIcon(man);
		
		menuBar.add(Game);
		Game.add(Sub_Exit);
		
		menuBar.add(Option);
		Option.add(Sub_Novice);
		Option.add(Sub_Intermediate);
		Option.add(Sub_Advanced);
		Option.add(Sub_HighScore);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(Help);
		Help.add(Sub_About);
		
		MenuListener mListener = new MenuListener();
		Sub_Exit.addActionListener(mListener);
		Sub_About.addActionListener(mListener);
		Sub_Novice.addActionListener(mListener);
		Sub_Intermediate.addActionListener(mListener);
		Sub_Advanced.addActionListener(mListener);
		Sub_HighScore.addActionListener(mListener);
		if(OuterFrame.levelSelection == 1){
			Balls RunnableBall = new Balls(300 , 100 , 1 ,1);
			Balls RunnableBall_2 =new Balls(600 , 100 , 2 ,1);
			objectPanelBall.add(RunnableBall.Gen_Ball);
			objectPanelBall.add(RunnableBall_2.Gen_Ball);
			checkList.add(RunnableBall);
			checkList.add(RunnableBall_2);
		}
		if(OuterFrame.levelSelection == 2){
			Balls RunnableBall = new Balls(200 , 150 , 1 ,1);
			Balls RunnableBall_2 =new Balls(500 , 150 , 2 ,1);
			Balls RunnableBall_3 =new Balls(800 , 150 , 2 ,1);
			
			objectPanelBall.add(RunnableBall.Gen_Ball);
			objectPanelBall.add(RunnableBall_2.Gen_Ball);
			objectPanelBall.add(RunnableBall_3.Gen_Ball);
			checkList.add(RunnableBall);
			checkList.add(RunnableBall_2);
			checkList.add(RunnableBall_3);
		}
		if(OuterFrame.levelSelection == 3){
			Balls RunnableBall = new Balls(200 , 200 , 1 ,1);
			Balls RunnableBall_2 = new Balls(400 , 200 , 1 ,1);
			Balls RunnableBall_3 =new Balls(700 , 200 , 2 ,1);
			Balls RunnableBall_4 =new Balls(900 , 200 , 2 ,1);
			
			objectPanelBall.add(RunnableBall.Gen_Ball);
			objectPanelBall.add(RunnableBall_2.Gen_Ball);
			objectPanelBall.add(RunnableBall_3.Gen_Ball);
			objectPanelBall.add(RunnableBall_4.Gen_Ball);
			checkList.add(RunnableBall);
			checkList.add(RunnableBall_2);
			checkList.add(RunnableBall_3);
			checkList.add(RunnableBall_4);
		}
		
		collision = new Collision();
		
		Gen_Panel.add(Man);
		Gen_Panel.add(object);
		Gen_Panel.add(objectPanelBall);
		Gen_Panel.add(objectPanel);
		Gen_Panel.add(Background);
		
		setJMenuBar(menuBar);
		add(Gen_Panel,BorderLayout.CENTER);
	}
	public void moveBox(int newman_x ,int newman_y){
		Man.setLocation(newman_x , newman_y);
	}
	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode()== event.VK_RIGHT ){
			man = new ImageIcon(getClass().getResource("Man_R.png"));
			Man.setIcon(man);
			if(man_x <= 940){
				man_x = man_x + 10;
				moveBox(man_x , man_y);
			}
			else{
				moveBox(940 , man_y);
			}
		}
		if(event.getKeyCode()==event.VK_LEFT ){
			man = new ImageIcon(getClass().getResource("Man_L.png"));
			Man.setIcon(man);
			if(man_x >= 25){
				man_x = man_x - 10;
				moveBox(man_x , man_y);
			}
			else{
				moveBox(25 , man_y);
			}
		}
		if(event.getKeyCode() == event.VK_Z){
			bow_type = 1;
			BowType = new ImageIcon(getClass().getResource("BowType_1.png"));
			BOWTYPE.setIcon(BowType);
		}
		if(event.getKeyCode() == event.VK_X){
			bow_type = 2;
			BowType = new ImageIcon(getClass().getResource("BowType_2.jpg"));
			BOWTYPE.setIcon(BowType);
		}
		if(event.getKeyCode() != event.VK_LEFT && event.getKeyCode() != event.VK_RIGHT && event.getKeyCode() == event.VK_SPACE && check_Bow == 0){
			URL urlattackSound = OuterFrame.class.getResource("Attack.wav");
			attackSound = Applet.newAudioClip(urlattackSound);
	        attackSound.play();
	        
			RunnableBow = new Bow_Attack(man_x , man_y , bow_type);
			objectPanel.add(RunnableBow.Bow);
			
			checkVisible = 1;
			
			new Thread(RunnableBow).start();
		}
	}
	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getKeyCode()== event.VK_RIGHT ){
			man = new ImageIcon(getClass().getResource("Man_B.png"));
			Man.setIcon(man);
		}
		if(event.getKeyCode()== event.VK_LEFT ){
			man = new ImageIcon(getClass().getResource("Man_B.png"));
			Man.setIcon(man);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	private class MenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent events) {
			if(events.getActionCommand() == "Low Speed"){
				Balls.levelSpeed = 0;
			}
			if(events.getActionCommand() == "Medium Speed"){
				Balls.levelSpeed = 1;
				
			}
			if(events.getActionCommand() == "High Speed"){
				Balls.levelSpeed = 2;
			}
			//H›GH SCORE TABLE
			if(events.getActionCommand() == "High Score"){
				System.out.println("INSIDE HS");
				String OneScore="";
				String AllScores = "";
				for(int count = 0 ; count < OuterFrame.UserList.size() ; count = count +5){
					OneScore = ("Account Name:"+OuterFrame.UserList.get(count)+ ">>>High Score:" + OuterFrame.UserList.get(count+2)+">>>Date:"+OuterFrame.UserList.get(count+3)+"--"+OuterFrame.UserList.get(count+4));
					AllScores += OneScore + "\n";
				}
				JOptionPane.showMessageDialog(objectPanel,AllScores );
			}
			if(events.getActionCommand() == "Exit"){
				Date date = Calendar.getInstance().getTime();
				currentDate = dateFormat.format(date); 
				OuterFrame.UserList.add(GLO_COUNT+3, currentDate);
				OuterFrame.UserList.remove(GLO_COUNT+4);
				OuterFrame.UserList.remove(GLO_COUNT+4);
				//OuterFrame.UserList.remove(GLO_COUNT+4);

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
			if(events.getActionCommand() == "About"){
				gameSound.stop();
				
				URL urlinfoSound = OuterFrame.class.getResource("Info.wav");
				infoSound = Applet.newAudioClip(urlinfoSound);
		        infoSound.play();
				
				JOptionPane.showMessageDialog(Gen_Panel,"Desing by ›lkem Erol\nPersonal Information:\n›lkem Erol / Yeditepe University.\n*EMail: ilkem.erol@hotmail.com");
			}
			
		}
		
	}

}
