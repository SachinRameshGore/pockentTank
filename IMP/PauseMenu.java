import java.awt.*;
import java.io.*;
import java.util.*;
public class PauseMenu implements button_specifications{
	Rectangle ResumeGame = new Rectangle();
	
	Rectangle StartNewGame = new Rectangle();
	
	Rectangle Help = new Rectangle();
	
	Rectangle Quit = new Rectangle();
	
	Color c = new Color(0,0,0,20);
	
	Font f = null;
	public PauseMenu(){
		 ResumeGame = new Rectangle(0,start_new_y+40,800,50);
		StartNewGame = new Rectangle(0,start_y+40,800,50);	//300 100
		Help= new Rectangle(0,help_y+40,800,50);		//300 100
		Quit = new Rectangle(0,quit_y+40,800,50);	//300 100
		
		try{
			
			 f = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("prototype.ttf"))).deriveFont(Font.PLAIN,30);
			//g.setFont(f);
		}catch(Exception E){}
		
	}
	public void render(Graphics G){
		Graphics2D g = (Graphics2D)G;
		g.fill(StartNewGame);
		g.fill(ResumeGame);
		g.fill(Help);
		g.fill(Quit);
		g.setFont(f);
		g.setColor(Color.white);
		g.drawString("Resume Game",start_new_x+25, start_new_y+70);
		g.drawString("Start",start_x+80,start_y+70);
		
		//G.drawImage(bf,B.help_x,B.help_y,300,100,null);
		
		g.drawString("Help",help_x+80,help_y+70);
		
		//G.drawImage(bf,B.quit_x,B.quit_y,300,100,null);
		
		g.drawString("Quit",quit_x+80,quit_y+70);
		
		g.setColor(c);
		
		g.fill(new Rectangle(0,0,800,600));
	}
}
