import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.awt.image.*;
import java.util.*;
public class button_setter{
	public Rectangle Fire;
	public Rectangle angle1;
	public Rectangle angle;
	public Rectangle inc_ang;
	public Rectangle dnc_ang;
	
	public Rectangle power1;
	public Rectangle power;
	
	public Rectangle inc_pow;
	public Rectangle dnc_pow;
	
	public button_specifications  B;
	public BufferedImage img,fire_img,inc_img,dnc_img,fire_img2,angle_img,angle_img2;
	
	public BufferedImage IMG = null;
	public Graphics2D dbg;
	
	handler hand;
	
	
	public MenuPad focus = MenuPad.NONE;
	
	public button_setter(handler hand)
	{
		
		this.hand = hand;
		angle1 = new Rectangle(B.angle_label_x,B.angle_label_y,55,20);
		angle = new Rectangle(B.angle_x,B.angle_y,35,34);
		inc_ang = new Rectangle(B.inc_ang_x,B.inc_ang_y,15,30);
		dnc_ang = new Rectangle(B.dnc_ang_x,B.dnc_ang_y,15,30);
		
		
		Fire = new Rectangle(B.Fire_x,B.Fire_y,50,50);
		
		power = new Rectangle(B.power_x,B.power_y,35,34);
		power1 = new Rectangle(B.power_label_x,B.power_label_y,60,22);
		inc_pow = new Rectangle(B.inc_pow_x,B.inc_pow_y,15,30);
		dnc_pow = new Rectangle(B.dnc_pow_x,B.dnc_pow_y,15,30);
		
		try{
			img = ImageIO.read(new File("//exports//AG1//AG1//PocketTank//Frame//bb.png"));
			fire_img = ImageIO.read(new File("//exports//AG1//AG1//PocketTank//Frame//Fire_button1.png"));
			fire_img2 = ImageIO.read(new File("//exports//AG1//AG1//PocketTank//Frame//Fire_button2.png"));
			
			angle_img = ImageIO.read(new File("//exports//AG1//AG1//PocketTank//Frame//red_button04.png"));
			angle_img2 = ImageIO.read(new File("//exports//AG1//AG1//PocketTank//Frame//red_button05.png"));
			
			
			
			inc_img = ImageIO.read(new File("//exports//AG1//AG1//PocketTank//Frame//inc_img.png"));
			dnc_img  = ImageIO.read(new File("//exports//AG1//AG1//PocketTank//Frame//dnc_img.png"));
		}catch(Exception E){E.printStackTrace();}
		
			
	}
	public void render(Graphics G,Player_1 p1,Player_2 p2)//,int variable)//Tank T)			<----- DUMB THING!!!
	{
		Graphics2D g = (Graphics2D)G;
		//if(variable == 0)
		//{
			Font fnt = new Font("ariel",Font.BOLD,15);
			G.setFont(fnt);
			G.setColor(Color.BLACK);
			G.drawImage(this.img,0,470,B.screen_width,B.screen_height-470,null);
			
			if(focus == MenuPad.FIRE)
				g.drawImage(fire_img2,B.Fire_x,B.Fire_y,60,50,null);
			else
				g.drawImage(fire_img,B.Fire_x,B.Fire_y,60,50,null);
			
			
			if(focus == MenuPad.ANGLE){
				g.drawImage(angle_img2,B.angle_label_x,B.angle_label_y,65,30,null);
				g.drawString("ANGLE",B.angle_label_x+3+3,B.angle_label_y+18);
			}
			else{
				g.drawImage(angle_img,B.angle_label_x,B.angle_label_y,65,30,null);
				g.drawString("ANGLE",B.angle_label_x+3,B.angle_label_y+18);
			}
			
			if(focus == MenuPad.POWER){
				g.drawImage(angle_img2,B.power_label_x,B.power_label_y,65,30,null);
				g.drawString("POWER",B.power_label_x+3+3,B.power_label_y+18);
				
			}
			else{
				g.drawImage(angle_img,B.power_label_x,B.power_label_y,65,30,null);
				g.drawString("POWER",B.power_label_x+3,B.power_label_y+18);
			}
			
			
			
			g.drawImage(inc_img,B.inc_ang_x,B.inc_ang_y,20,35,null);
			g.drawImage(dnc_img,B.dnc_ang_x,B.dnc_ang_y,20,35,null);
			g.drawString("+",B.inc_ang_x+5,B.inc_ang_y+18);
			g.drawString("-",B.dnc_ang_x+5,B.dnc_ang_y+15);
			
			g.drawImage(inc_img,B.inc_pow_x,B.inc_pow_y,20,35,null);
			g.drawImage(dnc_img,B.dnc_pow_x,B.dnc_pow_y,20,35,null);
			
			g.drawString("+",B.inc_pow_x+5,B.inc_pow_y+18);
			g.drawString("-",B.dnc_pow_x+5,B.dnc_pow_y+15);
			
			g.draw(power);
			g.draw(angle);
			
			if(p1 != null)
			g.drawString(""+(int)(hand.get_p1().t.ang),B.angle_x+6,B.angle_y+15+3);
		else
			g.drawString(""+(int)(hand.get_p2().t.ang),B.angle_x+6,B.angle_y+15+3);
			
			if(p1 != null)
			{
					g.drawString(""+(int)(hand.get_p1().velocity),B.power_x+1,B.power_y+15);
			}
			else{
					g.drawString(""+(int)(hand.get_p2().velocity),B.power_x+1,B.power_y+15);
			}
	}
	
}
