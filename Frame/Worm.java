import java.awt.*;
import java.awt.geom.*;
public class Worm extends Ground_hog{
	double damage_radius = 60;
	double damage  = 100;
	public Worm(double x,double y,double velocity,double angle){
		super(x,y,velocity,angle);
	}
	public void render(Graphics G)
	{
			if(!destroyed){
				G.setColor(Color.WHITE);
				Graphics2D g = (Graphics2D)G;
				g.setColor(Color.BLUE);
				g.fill(new Ellipse2D.Double(this.x,this.y,5,5));
			}
	}
	public void tick_x(terrain t,handler h)
	{
		super.tick_x(t,h);
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