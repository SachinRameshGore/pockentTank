import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
public class Cruiser extends shots{
	private button_specifications bt;
	int flag = 0;
	private final double damage = 100;
	private final double damage_radius = 72;
	int countPass = 100;
	Explosion e;
	//private damage_terrain dm = new damage_terrain();
	public Cruiser(double x,double y,double velocity,double angle){
		super(x,y,velocity,angle,100,30);
	}
	public void render(Graphics G)
	{
		if(!destroyed){
				G.setColor(Color.BLUE);
				Graphics2D g = (Graphics2D)G;
				g.setColor(Color.BLUE);
				g.fill(new Ellipse2D.Double(this.x,this.y,5,5));
			}
			if(e != null)
				e.render(G);
	}
	public void tick_x(terrain t,handler h)
	{
		if(this.alive){
			
			
			behavior(t,h);
			if(this.x < 0 || this.y > bt.Height || this.x > bt.Width){
					System.out.println("\n GONE");
					destroyed = true;
					this.gone = true;
					this.alive = false;
			}
		}
		if(e != null){
			e.update();
			if(e.alpha <= 0)
			{
				e = null;
			}
		}
	}
	public void behavior(terrain t,handler h){
		boolean br=  false;
		if(flag == 0)
			br  = collision_detection(t,h);
		if(br && flag == 0){
			flag = 1;
			this.mechanics  = true; 
		}
		if(br || flag == 1){
			if(countPass > 0){
				int mark_x = (int)(this.x/bt.Pixel_size);
				this.y =	(t.temp_y[mark_x]*bt.Pixel_size)-2;
				this.x += (velocity_x/Math.abs(velocity_x));
				
				if(collisionWithTank(t,h,0) == 1){
					int xc = (int)this.x/bt.Pixel_size;int yc = (int)this.y/bt.Pixel_size;
					int x_11 = xc,	y_11 = yc;
					int acc[] = add_sub(x_11,y_11,xc,yc,20);
					x_11 = acc[0]; 	y_11 = acc[1];	xc = acc[2];	yc = acc[3];
					e = new Explosion(x,y,5);
					super.explosion(x_11,y_11,xc,yc,damage_radius,t,h);
					countPass = 100;
				}
			}
			else{
				 int xc = (int)this.x/bt.Pixel_size;int yc = (int)this.y/bt.Pixel_size;
				int x_11 = xc,	y_11 = yc;
				int acc[] = add_sub(x_11,y_11,xc,yc,20);
				x_11 = acc[0]; 	y_11 = acc[1];	xc = acc[2];	yc = acc[3];
				e = new Explosion(x,y,5);
				super.explosion(x_11,y_11,xc,yc,damage_radius,t,h);
			}
			countPass--;
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