import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
public class Buff_image extends Canvas{
	
	BufferedImage img;
	public void paint(Graphics G){
		super.paint(G);
		
		Graphics2D g =  (Graphics2D)G;
		AffineTransform Af = new AffineTransform();
		int x,y,width,height;
		x = img.getMinX();
		y = img.getMinY();
		width = img.getWidth();
		height = img.getHeight();
		g.translate(100+x,100+y+(width/2));
		g.rotate(-(Math.PI/2));
		g.drawImage(this.img,100,100,null);
		g.translate(-(100+x),-(100+(y+(width/2))));
		
	}
	
	
	public static void main(String args[]) throws FileNotFoundException,NoninvertibleTransformException{
		Buff_image im = new Buff_image();
			try{
			im.img = ImageIO.read(new File("D:\\Graphics\\Game_development\\Pocketank\\kenney_tankspack\\PNG\\Default size\\arrow1.png"));
			}catch(Exception E){E.printStackTrace();}
			
		JFrame F = new JFrame("Image");
		F.setVisible(true);
		F.setSize(500,500);
		F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		F.setLocation(50,50);
		F.add(im);
		
	}
}