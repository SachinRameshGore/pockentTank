import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
public class Help implements button_specifications{
	RandomAccessFile fileReader;
	public Help()
	{
		try{	
			fileReader = new RandomAccessFile("Help.txt","r")
		}catch(Exception E){E.printStackTrace();}
	}
	public void render(Graphics G){
		Graphics2D g = (Graphics2D)G;
	}
	public void update(){
	}
}
