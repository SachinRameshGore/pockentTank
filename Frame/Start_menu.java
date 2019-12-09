import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
public class Start_menu{
	public Rectangle Start;
	public Rectangle Help;
	public Rectangle Quit;
	public BufferedImage bf,bf2;
	public button_specifications B;
	public Color Trans = new Color(255,255,255,10);
	public Start_menu()
	{
		Start = new Rectangle(/*B.start_x*/0,B.start_y+40,800,50);	//300 100
		Help= new Rectangle(/*B.help_x*/0,B.help_y+40,800,50);		//300 100
		Quit = new Rectangle(/*B.quit_x*/0,B.quit_y+40,800,50);	//300 100
		try{
		//bf = ImageIO.read(new File("D:\\Graphics\\Game_development\\Pocketank\\cloud.jpg"));
		bf2 = ImageIO.read(new File("//exports//AG1//AG1//PocketTank//Frame//image2.png"));//saved_image.jpg"));
			

	
		}catch(Exception E){E.printStackTrace();}
	}
	public void render(Graphics G)
	{
		Graphics2D g = (Graphics2D)G;
		Font fnt = new Font("ariel",Font.BOLD,25);
		G.setFont(fnt);
		G.drawImage(bf2,0,0,B.screen_width,B.screen_height,null);
		G.setColor(Color.WHITE);
		
		//G.drawImage(bf,B.start_x,B.start_y,300,100,null);
		g.setColor(Trans);
		g.fill(Start);
		g.fill(Help);
		g.fill(Quit);
		g.setColor(Color.WHITE);
		g.drawString("Start",B.start_x+80,B.start_y+70);
		
		//G.drawImage(bf,B.help_x,B.help_y,300,100,null);
		
		g.drawString("Help",B.help_x+80,B.help_y+70);
		
		//G.drawImage(bf,B.quit_x,B.quit_y,300,100,null);
		
		g.drawString("Quit",B.quit_x+80,B.quit_y+70);
	}
}
