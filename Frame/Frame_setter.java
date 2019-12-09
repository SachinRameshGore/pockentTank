import javax.swing.*;
import java.awt.*;
public class Frame_setter extends Canvas{

	String title;
	JFrame JF;
	button_specifications bt;
	public Frame_setter(String title,Pocket_tank game)
	{
		this.title = title;
		 JF = new JFrame(this.title);
		JF.setDefaultCloseOperation(JF.EXIT_ON_CLOSE);
		JF.setVisible(true);
		JF.setResizable(false);
		JF.setSize(bt.screen_width,bt.screen_height);
		JF.setLocation(100,100);
		JF.add(game);
		game.start();
	}
	public JFrame get_frame()
	{
		return(this.JF);
	}
	
	
}