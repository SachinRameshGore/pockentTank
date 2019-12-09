import java.awt.*;
import java.util.*;
import java.awt.geom.*;
public class shots{
		double angle;
		Shape shape;
		double x;
		double y;
		double velocity;
		double velocity_x;
		double velocity_y;
		double acc_x = 0;
		double acc_y = 9.8;
		double time;
		double endTime;
		TYPE_OF_SHOT type;
		Ellipse2D eli = null;
		boolean destroyed = false;	boolean gone = false;
		boolean alive = true;
		int start_x,end_x;
		public boolean mechanics = false;
		private double prev_x,prev_y;
		Rectangle2D rec1;
		double damage_radius;
		double damage;
		Explosion e;
		button_specifications bt;			//damage_report dr = new damage_report(); 	damage_terrain dm = new damage_terrain();
		
		public shots(double x,double y,double velocity,double angle,double damage,double damage_radius)
		{
			this.x = x;
			this.y = y;
			this.velocity = velocity;
			this.angle = -angle;
			this.velocity_x = -(this.velocity * Math.cos(this.angle));
			this.velocity_y = this.velocity * Math.sin(this.angle);
			this.endTime = 100;
			this.alive = true;
			this.damage_radius = damage_radius;
			this.damage  = damage;
			this.type = TYPE_OF_SHOT.CRUISER;
			
		}
		public void tick_x(terrain t,handler h)
		{
			prev_x = this.x;
			prev_y = this.y;
			if(!this.destroyed && !mechanics){
				double delta_time = 0.1;
				Rectangle2D rec1 = new Rectangle2D.Double();
				if(this.time < this.endTime)
				{
					time += delta_time;
					
					
					this.x += this.velocity_x*delta_time;
					this.y += this.velocity_y*delta_time;

					this.velocity_x += this.acc_x*delta_time;
					this.velocity_y += this.acc_y*delta_time;
				}
				
				
				if(this.x < 0 || this.y > bt.Height || this.x > bt.Width){
					System.out.println("Gone !!");
					destroyed = true;
					this.gone = true;
					this.alive = false;
				}
			}
		}
		public void explosion(int x_11,int y_11,int xc,int yc,double damage_radius,terrain t){
					/*x_11 = xc;	y_11 = yc;
					int acc[] = add_sub(x_11,y_11,xc,yc,(int)((damage_radius/2)+5));
					x_11 = acc[0]; 	y_11 = acc[1];	xc = acc[2];	yc = acc[3];*/
					eli = new Ellipse2D.Double(x-(damage_radius/2),y-(damage_radius/2),damage_radius,damage_radius);
					t.move_x(y_11,x_11,yc,xc,eli);
					eli = null;
					set_destroyed(true);
					set_alive(false);
					t.updateArray(y_11,x_11,yc,xc);
		}
		public int[] add_sub(int x1,int y1,int x2,int y2,int offset)
		{
			//int [] lets = new int[4];
			if(y1-offset > 0)
				y1-=offset; 
			else 
				y1 = 0;	
			if(x1-offset > 0)	
				x1 -=offset; 
			else 
				x1 = 0;	
			if(x2+offset < bt.cols)	
				x2+=offset; 
			else 
				x2 = bt.cols; 	
			if(y2+offset < bt.rows)	
				y2+=offset; 
			else 
				y2 = bt.rows;
			
			int lets[] = {x1,y1,x2,y2};
			return(lets);
			
		}
		public double distance_x(double x1,double y1,double x2,double y2)
		{
			double distance;
			double x12,y12;
			x12  = Math.pow((x2-x1),2);
			y12 = Math.pow((y2-y1),2);
			
			distance = Math.sqrt(x12+y12);
			return(distance);
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
		
	public boolean collision_detection(terrain t,handler h)	/////With terrain!!!!!
	{
			int start_x,end_x;
			int start_y,end_y;
			int collide = 0;
			rec1 = new Rectangle2D.Double();
			eli = null;
			start_x =(int)(((x)/bt.Pixel_size)); 
			end_x = (int)(((x)/bt.Pixel_size));
			
			start_y =(int)(((y)/bt.Pixel_size)); 
			end_y = (int)(((y)/bt.Pixel_size));
			
				int  x_11 = 10; int y_11 = 10;
				int acc[] = add_sub(start_x,start_y,end_x,start_y,5);
					start_x = acc[0];	end_x = acc[2];
					start_y = acc[1];	end_y = acc[3];
																	//if the shot hits the terrain ,
			
				/*hit function!!*/ 
				if(start_x > 0 && end_x >0 ){
				for(int i=start_y;i<end_y;i++)			////////				//only that range of terrain should be updated and rendered!!
				{
					for(int j=start_x;j<end_x;j++)
					{
						if(t.cooler[i][j] >= 0)
						{
							double x_12 = j*bt.Pixel_size;					//check whether it hits the terrain or not
							double y_12 = i*bt.Pixel_size;	
							rec1.setRect(x_12,y_12,bt.Pixel_size,bt.Pixel_size);
							double dist = distance_x(x,y,x_12,y_12);		/**************/
							if(dist <=2)
							{
								System.out.println("\n Hit1!");
								/*x_11 = j;
								y_11 = i;*/
								collide = 1;
								break;
							}
						}
					}
				}
				
			}
			collide = collisionWithTank(t,h,collide);
		
			if(collide == 1)
					return true;
			else
				return false;
	}	
	
	public int collisionWithTank(terrain t,handler h,int collide){
		double tank1X = h.get_p1().t.x +10;
			double tank1Y = h.get_p1().t.y +10;
			
			double tank2X = h.get_p2().t.x +10;
			double tank2Y = h.get_p2().t.y +10;
			
			if(collide == 1){
				
				double distanceWithPlayer1 = distance_x(x,y,tank1X,tank1Y);
				double distanceWithPlayer2 = distance_x(x,y,tank2X,tank2Y);
				
				int damagePlayer1 = give_destruction(distanceWithPlayer1,damage,damage_radius);
				int damagePlayer2 = give_destruction(distanceWithPlayer2,damage,damage_radius);
				
				if(h.get_pop() == TURN.PLAYER1){
					h.get_p1().set_score(-damagePlayer1);
					h.get_p1().set_score(damagePlayer2);
				}
				else if(h.get_pop() == TURN.PLAYER2){
					h.get_p2().set_score(damagePlayer1);
					h.get_p2().set_score(-damagePlayer2);
				}
			}
			else {
					
					if(h.get_pop() == TURN.PLAYER1){
						if(h.get_p1().t.isColliding(this.x,this.y,5)){
							h.get_p1().set_score(-((int)damage));
							collide = 1;
						}
						else if(h.get_p2().t.isColliding(this.x,this.y,5)){
							h.get_p1().set_score((int)damage);
							collide = 1;
						}
					}
					else if(h.get_pop() == TURN.PLAYER2){
						if(h.get_p1().t.isColliding(this.x,this.y,5)){
							h.get_p2().set_score((int)damage);
							collide = 1;
						}
						else if(h.get_p2().t.isColliding(this.x,this.y,5)){
							h.get_p2().set_score(-((int)damage));
							collide = 1;
						}
					}
				}
			return(collide);
			
	}
	public int give_destruction(double dist,double damage,double damage_radius)
	{
		double total_damage;
		
		
		if(dist < damage_radius)
			total_damage = ((dist-0)/(damage_radius-0))*(0 - damage) + damage; 
		else
			total_damage = 0;
		
		
		return((int)total_damage);
	}
	
		
		public double getX()
		{
			return(this.x);
		}
		public double getY()
		{
			return(this.y);
		}
		public boolean get_destroyed()
		{
			return(this.destroyed);
		}
		public boolean get_alive()
		{
			return(this.alive);
		}
		public void  set_alive(boolean bo)
		{
			this.alive = bo;
		}
		public void set_destroyed(boolean bo)
		{
			this.destroyed = bo;
		}
		public void set_ellipse(Ellipse2D eli)
		{
				this.eli = eli;
		}
		public double get_damage_radius()
		{
			return(this.damage_radius);
		}
		public double get_damage()
		{
			return(this.damage);
		}
		public boolean get_gone()
		{
			return(this.gone);
		}
		
		public TYPE_OF_SHOT get_shot_type()
		{
			return(this.type);
		}
		
}