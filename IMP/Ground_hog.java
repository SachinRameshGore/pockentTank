import java.awt.*;
import java.awt.geom.*;
public class Ground_hog extends shots{
	int flag = 0;
	button_specifications bt;
	Ellipse2D eli = new Ellipse2D.Double();
	Explosion e;
	//damage_terrain dm = new damage_terrain();
	double damage_radius = 144;
	double damage  = 100;
	public Ground_hog(double x,double y,double velocity,double angle){
		super(x,y,velocity,angle,100.00,35.00);
	}
	public void render(Graphics G)
	{
			if(!destroyed){
				G.setColor(Color.WHITE);
				Graphics2D g = (Graphics2D)G;
				g.setColor(Color.WHITE);
				g.fill(new Ellipse2D.Double(this.x,this.y,5,5));
			}
			if(e != null)
				e.render(G);
	}
	public void tick_x(terrain t,handler h){
		if(this.alive){
			
			
			behavior(t,h);
			
			if(this.x < 0 || this.y > bt.Height || this.x > bt.Width){
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
			G_hog(t);
			if(this.getY() >= bt.Height-10)
			{
				System.out.print("\n Here");
				 int xc = (int)this.x/bt.Pixel_size;int yc = (int)this.y/bt.Pixel_size;
				int x_11 = xc,	y_11 = yc;
				int acc[] = add_sub(x_11,y_11,xc,yc,(int)(damage_radius/2));
				x_11 = acc[0]; 	y_11 = acc[1];	xc = acc[2];	yc = acc[3];
				e = new Explosion(x,y,7);
				super.explosion(x_11,y_11,xc,yc,damage_radius,t,h);
			}
		}
		else{
			super.tick_x(t,h);
		}
	}
	public void G_hog(terrain t)
	{
		int mark_x,mark_y;
		if(getX() > 0 && getX() < bt.Width)
		{
			
				x += velocity_x*0.1;
				y += 2;
				
				 mark_x = (int)(x/bt.Pixel_size);
				 mark_y = (int)(y/bt.Pixel_size);
				
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