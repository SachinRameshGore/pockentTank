import java.awt.*;
import java.util.*;

public class Particle {
		VectorX position;
		VectorX velocity;
		VectorX accelaration;
		static double angleGradient = Math.PI/15;
		static double ang = 0;
		Random rand = new Random();
		Color c;
	public Particle(double x,double y){
		
		position = new VectorX(x,y);
		velocity = new VectorX(0,0);
		velocity.setByAngleLength(ang,rand.nextInt(6));
		ang+=angleGradient;
		accelaration = new VectorX(0,0.1);
		c = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
	}
	
	public void render(Graphics G){
		G.setColor(c);
		G.fillOval((int)position.getX(),(int)position.getY(),3,3);
	}
	public void update(){
		position.addTo(velocity);
		velocity.addTo(accelaration);
	}
}
