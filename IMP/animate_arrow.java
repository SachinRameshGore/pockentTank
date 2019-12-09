import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
public class animate_arrow{
	
	BufferedImage img;
	int ySpeed = 1,y_vel= 1;
	int xpos1,ypos1;
	int xpos2,ypos2;
	
	
	public animate_arrow()
	{
		try{
			this.img = ImageIO.read(new File("arrowDown.png"));
			}catch(Exception E){E.printStackTrace();}
			
	}
	
	public void render(Graphics G,handler hand)
	{
		Graphics2D g =  (Graphics2D)G;
		
			if(hand.get_turn() == TURN.PLAYER1)
			{
				xpos1 = hand.get_p1().t.x-10;
				ypos1 = hand.get_p1().t.y-100;
			}
			else if(hand.get_turn() == TURN.PLAYER2)
			{
				xpos1 = hand.get_p2().t.x-10;
				ypos1 = hand.get_p2().t.y-100;
			}
		
			g.drawImage(img,xpos1,ypos1+ySpeed,null);
			ySpeed +=y_vel;
			if(ySpeed > 15)
				y_vel *=-1;
			if(ySpeed <= 1)
				y_vel *= -1;
	}
}
