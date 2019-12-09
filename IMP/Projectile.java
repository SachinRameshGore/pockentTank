import java.awt.*;
import java.util.Random;
public class Projectile {
	VectorX position;
	VectorX velocity;
	VectorX accelaration;
	static double angleGradient = Math.PI/15;
	static double ang = 0;
	boolean exploded= false;
	VectorX timeDelay;
	 Particle particle[] = new Particle[150];
	 Random rand = new Random();
	 int max;
	 Color c;
	public Projectile(double x,double y){
		
		position = new VectorX(x,y);
		velocity = new VectorX(0,0);
		timeDelay = new VectorX(0.4,0.4);
		velocity.setByAngleLength(-Math.PI/2,rand.nextInt(20)+(20/2));
		ang+=angleGradient;
		accelaration = new VectorX(0,0.5);
		max = rand.nextInt(5);
		c = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
	}

	public void render(Graphics G){
		G.setColor(c);
		G.fillOval((int)position.getX(),(int)position.getY(),10,10);
	}
	public void update(){
		VectorX tempx = new VectorX(velocity);
		tempx.multiplyBY(timeDelay);
		position.addTo(tempx);
		tempx = new VectorX(accelaration);
		tempx.multiplyBY(timeDelay);
		velocity.addTo(accelaration);
		if(velocity.getY() >= max){
			exploded = true;
		}
	}
	public void initiateParticles(){
		for(int i= 0;i<150;i++){
			particle[i] = new Particle(position.getX(),position.getY());
		}
	}
}
