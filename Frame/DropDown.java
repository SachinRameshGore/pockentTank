import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;

public class DropDown implements button_specifications{
	
	public LinkedList <String> WeaponList = new LinkedList <String>();
	public int index = 0;
	BufferedImage drop,arrow1,arrow2;
	
	
	public DropDown(){
		WeaponList.add("Sniper");
		WeaponList.add("Cruiser");
		WeaponList.add("Big Shot");
		WeaponList.add("Ground-Hog");
		
		try{
			drop = ImageIO.read(new File("//exports//AG1//AG1//PocketTank//Frame//DropDown0.png"));
			arrow1 = ImageIO.read(new File("//exports//AG1//AG1//PocketTank//Frame//inc_img1.png"));
			arrow2 = ImageIO.read(new File("//exports//AG1//AG1//PocketTank//Frame//dnc_img1.png"));
		}catch(Exception E){E.printStackTrace();}
		
	}
	public void render(Graphics G)
	{
		Graphics2D g = (Graphics2D)G;
		g.drawImage(drop,drop_down_label_x,drop_down_label_y,120,40,null);
		g.drawImage(arrow1,drop_down_inc_x,drop_down_inc_y,20,20,null);
		g.drawImage(arrow2,drop_down_dnc_x,drop_down_dnc_y,20,20,null);
		g.drawString(""+WeaponList.get(Math.abs(index)),drop_down_label_x+15,drop_down_label_y+20);
		
	}
	public void update(){
	}
	public void incrementIndex(){
		index++;
		index %= WeaponList.size();
	}
	public void decrementIndex(){
		index--;
		if(index < 0)
			index = WeaponList.size()-1;
	}
}
