import java.awt.*;
import java.awt.geom.*;
public class Explosion{
	Ellipse2D circle;
	double accelaration = 0.1;
	double velocity;
	public int alpha = 255;
	double radius;
	double x;double y;
	Color color;
	int dnc = 15;
	public Explosion(double x,double y,double velocity){
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		circle = new Ellipse2D.Double(x,y,0,0);
		//this.color = color;
		//dnc = mapTo(15,1,20,1,velocity);
	}
	public void render(Graphics G){
		Graphics2D g = (Graphics2D)G;
		
		
	
		if(alpha > 0){
			g.setColor(new Color(255,0,0,alpha));
			g.fill(circle);
			g.setColor(new Color(255,0,0,alpha));
			g.draw(circle);
		
		}
		alpha -= dnc;
	}
	public void update(){
		this.radius += this.velocity;
		circle = new Ellipse2D.Double(this.x-radius/2,this.y-radius/2,this.radius,this.radius);
	}
}
