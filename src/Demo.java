import java.io.IOException;

import javax.swing.JFrame;

public class Demo {
	public static void main(String[] args) throws IOException{
		OuterFrame demo = new OuterFrame();
		demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//demo.pack();
		demo.setVisible(true);
		demo.setResizable(false);
	}
}
