import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.geom.*;
public class Tank extends position{
	String path;
	BufferedImage body;
	BufferedImage lower_body;
	Path2D pat;
	int x_lower;
	int y_lower;
	double x_turret,y_turret;
	double x_turret_max,y_turret_max;
	double ang;
	int flag_saveme = 0;
	Rectangle2D turret;				public button_specifications bt;
	final String lower = "Tank_lower_body.png";
	Rectangle2D bodyCollision;
		
		public Tank(int x,int y,int xspeed,int yspeed,String path)
		{
			super(x,y,xspeed,yspeed);
			this.path = path;
			this.bodyCollision = new Rectangle2D.Double(this.x,this.y+5,20,30);
			updateTurret();
			try{
				body = ImageIO.read(new File(path));
				lower_body = ImageIO.read(new File(lower));
			}catch(Exception E){E.printStackTrace();}
			this.ang = -(Math.PI/4);
			this.pat = new Path2D.Double(turret);
		}
		public void updateTurret(){
			this.x_lower = this.x;
			this.y_lower = this.y + 5;
			this.x_turret = (double)this.x+10;
			this.y_turret = (double)this.y;
			this.x_turret_max = this.x_turret + 15;
			this.y_turret_max = this.y_turret + 3;
			this.turret = new Rectangle2D.Double(this.x_turret,this.y_turret,15,3);
		}
		/* this method constatntly updates the Tank object !!!*/
		public void tick_x(terrain t)throws NoninvertibleTransformException
		{
				AffineTransform inv = new AffineTransform();
				int acc = 3;
				double temp;
				Rectangle2D buffer;
				if(this.touching(t) != true)
				{
					this.yspeed = acc;
				}
			
			this.x_turret_max += (double)this.xspeed;
			this.y_turret_max += (double)this.yspeed;				//Translate the tank position according to the xspeed and yspeed
			this.x_turret += (double)this.xspeed;					//all the componenets should be moved!!!!
			this.y_turret += (double)this.yspeed;
			//************************************************		//Translate the turret position 
				updatePath();																						//by xspeed and yspeed 
				this.x += this.xspeed;
				this.y += this.yspeed;
				this.x_lower += this.xspeed;
				this.y_lower += this.yspeed;
				
				this.yspeed= 0;
				this.xspeed = 0;
		}
		public void updatePath(){
			bodyCollision.setRect(this.x,this.y,20,30);
			pat.reset();
														//setting the turret to the position of the tank!!!!
					pat.moveTo(x_turret,y_turret);													// fuck this shit!!!!!
					pat.lineTo(x_turret,y_turret_max);												//This is so insane!!!
					pat.lineTo(x_turret_max,y_turret_max);
					pat.lineTo(x_turret_max,y_turret);
				pat.closePath();
		}
		
		
		/* this method is used to check whether the tank is touching the terrain or not*/ 
		public boolean touching(terrain t)
		{
			
			int mark =0;
			int Pixel_size = button_specifications.Pixel_size;
			
			int col_max = (this.x+10)/Pixel_size;
			int col_min = (this.x-10)/Pixel_size;
			
			int row_max = (this.y+30)/Pixel_size;
			int row_min = (this.y-30)/Pixel_size;
			
			Rectangle2D rec1 = new Rectangle2D.Double();
			
			
			if(col_min < 0)
				col_min = 0;
			if(col_max > bt.cols)
				col_max = bt.cols;
			
			for(int i =row_min;i < row_max;i++){		//bt.rows
					
				for(int j=col_min;j < col_max;j++){		//bt.cols
					rec1.setRect((double)(j*Pixel_size),(double)(i*Pixel_size),(double)Pixel_size,(double)Pixel_size);
					if(t.cooler[i][j] >= 0){
						if(this.x_lower <= j*Pixel_size  && this.x_lower+20 >= j*Pixel_size){
						
							if(this.y_lower+20 >=  (int)rec1.getY() || this.y_lower >= button_specifications.Height)						//We can calculate Tan(theta) of the line 
							{												// in order to get the slope
								mark = 1;									//and then by turning the lower body to
								break;                                      //match the terrain
							}
						}
					}
					if(t.cooler[i][j] == -1){
						if(this.x_lower <= j*Pixel_size  && this.x_lower+20 >= j*Pixel_size){
						
							if(this.y_lower >= button_specifications.Height-17)						//We can calculate Tan(theta) of the line 
							{												// in order to get the slope
								mark = 1;									//and then by turning the lower body to
								break;                                      //match the terrain
							}
						}
					}
					
				}
			}
			if(mark == 0)
				return(false);
			else
				return(true);
		}
		public void render(Graphics G)
		{
			Graphics2D g = (Graphics2D)G;				// fuck this shit!!!!!
			
			g.setColor(Color.RED);
			G.drawImage(this.lower_body,this.x_lower,this.y_lower,20,20,null);							//Too much delicate// 
			G.drawImage(this.body,this.x,this.y,20,20,null);			//DONOT TOUCH THIS!!!!//
			//g.draw(bodyCollision);
			g.rotate(this.ang,(this.x+5),(this.y+3));
			g.fill(pat);
			g.rotate(-this.ang,(this.x+5),(this.y+3));
		}
		public boolean isColliding(double x,double y,double width){
			Rectangle2D tempRect = new Rectangle2D.Double(x,y,width,width);
			
			if(this.bodyCollision.intersects(tempRect))
				return(true);
			else 
				return(false);
		}
		public double get_x_turret()
		{
			return(this.x_turret);
		}
		public void setAngle(double angleChange){
			this.ang += angleChange;
		}
		public void setAnglex(double Angle){
			this.ang = Angle;
		}
		public double get_y_turret()
		{
			return(this.y_turret);
		}
		public double getAngle(){
			return(this.ang * (180/Math.PI));
		}
}
