import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public class OuterFrame extends JFrame{
	JButton register;
	JButton login;
	JButton highScore;
	JButton history;
	JButton info;
	JButton gamenote;
	JButton Exit;
	
	static List <String> UserList = new ArrayList <String> ();
	File myFile;
	String holdName;
	String holdPassWord;
	String holdHighScore;
	String holdDate;
	String holdTime;
	
	static String GLO_Name;
	static String GLO_Password;
	
	String levelSelectionPARSE;
	static int levelSelection;
	
	int checkFile = 0;
	int checkRegister = 0;
	
	JLabel Outer_Back;
	
	JPanel outerPanel = new JPanel();
	
	Icon Outer_Background;
	
	static PrintWriter writer;
	
	AudioClip beepSound;
	
	public OuterFrame(){
		super("Welcome to  Bubble Trouble");
		setLayout(new BorderLayout());
		setBounds(0 , 0 , 1024 , 768);
		Scanner fileScanner = null;
		try {
			myFile =new File("Bubble Trouble.txt");
			if(!myFile.exists()){
				try {
					myFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			fileScanner = new Scanner(myFile);
			while(fileScanner.hasNext()){
				holdName = fileScanner.next();
				UserList.add(holdName);
				
				holdPassWord = fileScanner.next();
				UserList.add(holdPassWord);
				
				holdHighScore = fileScanner.next();
				UserList.add(holdHighScore);
				
				holdDate = fileScanner.next();
				UserList.add(holdDate);
				
				holdTime = fileScanner.next();
				UserList.add(holdTime);
			}
		}
		
		catch (FileNotFoundException event) {
			event.printStackTrace();
		}
		
		//System.out.println("SIZE:"+UserList.size());
		
		Outer_Background = new ImageIcon(getClass().getResource("OuterFrame.jpg"));
		
		Outer_Back = new JLabel(Outer_Background);
		Outer_Back.setBounds(0 , 0, 1024, 768);
		
		register = new JButton("REGISTER" , new ImageIcon(getClass().getResource("RegisterA.jpg")));
		register.setRolloverIcon(new ImageIcon(getClass().getResource("RegisterAp.jpg")));
		register.setBounds(600, 100, 390, 100);
		login = new JButton("LOGIN" , new ImageIcon(getClass().getResource("Login.jpg")));
		login.setRolloverIcon(new ImageIcon(getClass().getResource("LoginP.jpg")));
		login.setBounds(600, 300, 390, 100);
		highScore = new JButton("HIGH SCORE", new ImageIcon(getClass().getResource("HighScoreOuter.jpg")));
		highScore.setRolloverIcon(new ImageIcon(getClass().getResource("HighScoreOuterP.jpg")));
		highScore.setBounds(710, 680 , 140, 50);
		
		history = new JButton("HISTORY", new ImageIcon(getClass().getResource("History.jpg")));
		history.setRolloverIcon(new ImageIcon(getClass().getResource("HistoryP.jpg")));
		history.setBounds(640, 680 , 40, 50);
		
		info = new JButton("INFO", new ImageIcon(getClass().getResource("InfoOuter.jpg")));
		info.setRolloverIcon(new ImageIcon(getClass().getResource("InfoOuterP.jpg")));
		info.setBounds(880 , 680, 40, 50);
		gamenote =new JButton("GAMENOTE", new ImageIcon(getClass().getResource("Note.jpg")));
		gamenote.setRolloverIcon(new ImageIcon(getClass().getResource("NoteP.jpg")));
		gamenote.setBounds(30, 680, 40, 50);
		Exit = new JButton("EXIT" , new ImageIcon(getClass().getResource("ExitOuter.jpg")));
		Exit.setRolloverIcon(new ImageIcon(getClass().getResource("ExitOuterP.jpg")));
		Exit.setBounds(950, 680, 40, 50);
		outerPanel.setLayout(null);
		
		outerPanel.add(register);
		outerPanel.add(login);
		outerPanel.add(highScore);
		outerPanel.add(history);
		outerPanel.add(info);
		outerPanel.add(gamenote);
		outerPanel.add(Exit);
		outerPanel.add(Outer_Back);
		
		add(outerPanel,BorderLayout.CENTER);
		
		ButtonHandler handler = new ButtonHandler();
		register.addActionListener(handler);
		login.addActionListener(handler);
		highScore.addActionListener(handler);
		history.addActionListener(handler);
		info.addActionListener(handler);
		gamenote.addActionListener(handler);
		Exit.addActionListener(handler);
		
	}
	
	private class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent events) {
			if(events.getActionCommand() == "REGISTER"){
				String HighScore = "0";
				String Date = "";
				String Time = "";
				URL urlbeepSound = OuterFrame.class.getResource("Beep.wav");
				beepSound = Applet.newAudioClip(urlbeepSound);
		        beepSound.play();
		        GLO_Name = JOptionPane.showInputDialog(outerPanel , "Please,Enter Name OR Nickname.");
		        GLO_Password = JOptionPane.showInputDialog(outerPanel , "Please,Enter Password.");
		        
				for(int count = 0 ; count < UserList.size() ; count++){
		        	if(UserList.get(count).equals(GLO_Name) && UserList.get(count+1).equals(GLO_Password)){
		        		checkRegister = 1;
		        	}
				}
				if(checkRegister == 1){
					JOptionPane.showMessageDialog(outerPanel,"This User Aldready Registered\nPlease Try Another Username OR Password.");
				}
		        if(checkRegister == 0){
		        	UserList.add(GLO_Name);
					UserList.add(GLO_Password);
					UserList.add(HighScore);
					UserList.add(Date);
					UserList.add(Time);
					JOptionPane.showMessageDialog(outerPanel,"Your Account Created Succeesfully.\nNow,You Must Press Login and Play Game.");
		        }
		        //TEST USERLÝST
				/*for(String name : UserList){
					System.out.printf("Name:%s\n", name);
				}*/
			}
			if(events.getActionCommand() == "LOGIN"){
				URL urlbeepSound = OuterFrame.class.getResource("Beep.wav");
				beepSound = Applet.newAudioClip(urlbeepSound);
		        beepSound.play();
		        GLO_Name = JOptionPane.showInputDialog(outerPanel , "Please, Enter Registered Name OR Nickname.");
		        GLO_Password = JOptionPane.showInputDialog(outerPanel , "Please, Enter Your Password");
		        for(int count = 0 ; count < UserList.size() ; count++){
		        	if(UserList.get(count).equals(GLO_Name) && UserList.get(count+1).equals(GLO_Password)){
		        		System.out.printf("%s",UserList.get(count));
		        		checkFile = 1;
		        	}
		        }
		        if(checkFile == 1){
		        	levelSelectionPARSE = JOptionPane.showInputDialog(outerPanel , "LEVEL SELECTION\n1-Novice\n2-Intermediate\n3-Advanced\nNOTE:If You Play First Time This Game, You Should Select Novice Level.\n");
				    levelSelection = Integer.parseInt(levelSelectionPARSE);
				    
				    JOptionPane.showMessageDialog(outerPanel,"Your Game Is Ready.\nWhen You Ready, Press OK.\nGood LUCK!!!");
		        	
		        	
		        	MainFrame demo = new MainFrame();
					demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					demo.pack();
					demo.setVisible(true);
					demo.setResizable(false);
					dispose();
		        }
		        if(checkFile == 0){
		        	JOptionPane.showMessageDialog(outerPanel,"First, You Must Be Register!!!\n Please, Click Register Button.");
		        }
			}
			if(events.getActionCommand() == "HIGH SCORE"){
				URL urlbeepSound = OuterFrame.class.getResource("Beep.wav");
				beepSound = Applet.newAudioClip(urlbeepSound);
		        beepSound.play();
				String OneScore="";
				String AllScores = "HIGH SCORE TABLE\n\n";
				for(int count = 0 ; count < OuterFrame.UserList.size() ; count = count +5){
					OneScore = ("Account Name:"+OuterFrame.UserList.get(count)+ ">>>High Score:" + OuterFrame.UserList.get(count+2)+">>>Date:"+OuterFrame.UserList.get(count+3)+"--"+OuterFrame.UserList.get(count+4));
					AllScores += OneScore + "\n";
				}
				JOptionPane.showMessageDialog(outerPanel,AllScores );
			}
			if(events.getActionCommand() == "HISTORY"){
				URL urlbeepSound = OuterFrame.class.getResource("Beep.wav");
				beepSound = Applet.newAudioClip(urlbeepSound);
		        beepSound.play();
				String OneScore="";
				String AllScores = "HISTORY TABLE\n\n";
				for(int count = 0 ; count < OuterFrame.UserList.size() ; count = count +5){
					OneScore = ("Account Name:"+OuterFrame.UserList.get(count)+">>>Last Played:"+OuterFrame.UserList.get(count+3)+"--"+OuterFrame.UserList.get(count+4));
					AllScores += OneScore + "\n";
				}
				JOptionPane.showMessageDialog(outerPanel,AllScores );
			}
			if(events.getActionCommand() == "INFO"){
				URL urlbeepSound = OuterFrame.class.getResource("Beep.wav");
				beepSound = Applet.newAudioClip(urlbeepSound);
		        beepSound.play();
				JOptionPane.showMessageDialog(outerPanel,"LEVEL SETTINGS\n*Novice = 2 Balls And Start Low Speed\n*Intermediate = 3 Balls And Start Low Speed\n*Advanced = 4 Balls And Start Low Speed\nNOTE: All Level Starts Low Speed, If You Want To Increase Speed, Please Click Options Button In The Game.\nKEYBOARD CONTROL\n*Arrow Key = Move Man.\n*Space = Fire Bow.\n*Z = Standart Bow.\n*X = Second Bow." );
			}
			if(events.getActionCommand() == "GAMENOTE"){
				URL urlbeepSound = OuterFrame.class.getResource("Beep.wav");
				beepSound = Applet.newAudioClip(urlbeepSound);
		        beepSound.play();
				JOptionPane.showMessageDialog(outerPanel,"SAVE SETTING\n*If You Click Exit, Your High Score Will NOT Updated.");
			}
			if(events.getActionCommand() == "EXIT"){
				try {
					OuterFrame.writer = new PrintWriter("Bubble Trouble.txt", "UTF-8");
				} 
				catch (FileNotFoundException e) {
					e.printStackTrace();
				} 
				catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				for(String printable : OuterFrame.UserList){
					OuterFrame.writer.print(printable);
					OuterFrame.writer.print(" ");
				}
				OuterFrame.writer.close();
				URL urlbeepSound = OuterFrame.class.getResource("Beep.wav");
				beepSound = Applet.newAudioClip(urlbeepSound);
		        beepSound.play();
		        beepSound.play();
		        beepSound.play();
		        
				System.exit(0);
			}
		}
	}
}
