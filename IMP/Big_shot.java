import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
public class Big_shot extends shots{
	private button_specifications bt;
	int flag = 0;
	private final double damage = 100;
	private final double damage_radius = 108;
	private Ellipse2D eli = new Ellipse2D.Double();
	//private damage_terrain dm = new damage_terrain();
	public Big_shot(double x,double y,double velocity,double angle){
		super(x,y,velocity,angle,100,30);
	}
	public void render(Graphics G)
	{
		if(!destroyed){
				G.setColor(Color.BLACK);
				Graphics2D g = (Graphics2D)G;
				g.setColor(Color.GRAY);
				g.fill(new Ellipse2D.Double(this.x,this.y,5,5));
			}
			if(this.e != null)
				e.render(G);
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
		if(this.e != null){
				e.update();
				if(e.alpha <= 0)
				{
					e = null;
				}
		}
	}
	public void behavior(terrain t,handler h)
	{
			if(collision_detection(t,h))
			{
					System.out.print("\n HhH");
					int xc = (int)x/bt.Pixel_size;int yc = (int)y/bt.Pixel_size;
					int x_11 = xc;	int y_11 = yc;
					int acc[] = add_sub(x_11,y_11,xc,yc,(int)(damage_radius/2));		//20
					x_11 = acc[0]; 	y_11 = acc[1];	xc = acc[2];	yc = acc[3];
					this.e = new Explosion(this.x,this.y,6);
					super.explosion(x_11,y_11,xc,yc,damage_radius,t,h);
			}
			else{
				super.tick_x(t,h);	
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