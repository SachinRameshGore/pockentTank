import java.awt.*;
import java.awt.geom.*;
public class Sniper extends shots{
	double damage_radius = 10;
	double damage  = 100;
	public Sniper(double x,double y,double velocity,double angle){
		super(x,y,velocity,angle,100,10);
	}
	public void render(Graphics G)
	{
			if(!destroyed){
					G.setColor(Color.WHITE);
					Graphics2D g = (Graphics2D)G;
					g.setColor(Color.RED);
					//g.fill(new Ellipse2D.Double(prev_x,prev_y,3,2));
					g.fill(new Ellipse2D.Double(this.x,this.y,5,5));
			}
	}
	public void behavior(terrain t,handler h){
		if(collision_detection(t,h)){
			System.out.println("HIT");
			 int xc = (int)this.x/bt.Pixel_size;int yc = (int)this.y/bt.Pixel_size;
				int x_11 = xc,	y_11 = yc;
				int acc[] = add_sub(x_11,y_11,xc,yc,20);
				x_11 = acc[0]; 	y_11 = acc[1];	xc = acc[2];	yc = acc[3];
				super.explosion(x_11,y_11,xc,yc,damage_radius,t,h);
		}
		else{
			super.tick_x(t,h);
		}
	}
	public void tick_x(terrain t,handler h)
	{
		if(this.alive){
		
			
			behavior(t,h);
			
			if(this.x < 0 || this.y > bt.Height || this.x > bt.Width){
					destroyed = true;
					this.gone = true;
					this.alive = false;
			}
		}
	}
	public double get_damage_radius()
	{
			return(this.damage_radius);
	}
	public double get_damage()
	{
			return(this.damage);
	}
}