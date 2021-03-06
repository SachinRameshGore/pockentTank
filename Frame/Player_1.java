import java.util.logging.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;

public class Player_1{
		public String path = "//exports//AG1//AG1//PocketTank//Frame//Tank_body.png";
		public int score; 
		public int fire_left;
		public Tank t;
		public shots s;
		public handler hand;
		int life = 400;
		DropDownMenu Drop;
		public DropDown drop;
		////////////Implement Linked List for Shots!!!
		int flag_once = 0,passes = 50;
		button_specifications bt;	
		
		
		public int weaponCount = 3;
		
		
		public double velocity = 59;
		
		public int indicator = 1;
		
		public Player_1(handler hand)
		{
			this.path = "//exports//AG1//AG1//PocketTank//Frame//Tank_body.png";
			this.score = 0;
			this.fire_left = 3;
			this.t  = new Tank(100,100,0,0,this.path);
			this.hand = hand;
			this.Drop = new DropDownMenu();
			drop = new DropDown();
		}
		public void render(Graphics G)
		{
			G.setColor(Color.BLUE);
			G.setFont(new Font("TimesRoman",Font.ITALIC,20));
			G.drawString("Player 1",50,30);
			G.drawString(""+score,75,50);
			t.render(G);
			
			if(s != null)
				s.render(G);
		}
		public void tick_x(terrain r,handler h)throws NoninvertibleTransformException
		{
			t.tick_x(r);
			if(s != null){
				s.tick_x(r,h);
				if(!s.get_alive() && life == 0)
				{
					System.out.println("\n HEE");
					
					//if(hand.get_turn() == TURN.PLAYER1)
						hand.set_turn(TURN.PLAYER2);
						life = 400;
						s = null;
				}
				life--;
				
			}
			indicator = 1;
			move_the_terrain(r);
			
			
		}
		public void renderDropDown(Graphics G){
			//Drop.render(G);
			drop.render(G);
		}
		public void move_the_terrain(terrain t){
			boolean br = false;
			boolean b2 = true;
			double dist;
			if(hand.get_pop() == TURN.PLAYER1 && s != null){br = s.get_destroyed(); b2 = s.get_gone();}
			
			if(hand.get_turn() == TURN.LOADING && br == true && b2 == false)
			{
				indicator = 0;
				//This is the loading part where the one of the player has fired a shot and there are some changes to do on the terrain
				int x1 = 0,x2 = 0,y1 = 0,y2 = 0;
				if(hand.get_pop() == TURN.PLAYER1)
				{
					x1 =  (int)((s.getX()-(s.get_damage_radius()/2))/bt.Pixel_size);
					x2 =  (int)((s.getX()+(s.get_damage_radius()/2))/bt.Pixel_size);
					y1 =  (int)((s.getY()-(s.get_damage_radius()/2))/bt.Pixel_size);
					y2 =  (int)((s.getY()+(s.get_damage_radius()/2))/bt.Pixel_size);
					
				}
				int acc[] = add_sub(x1,y1,x2,y2,(int)(s.damage_radius/2));			//30
						x1 = acc[0]; 	y1 = acc[1];	x2 = acc[2];	y2 = acc[3];
									// x1	   //y1		//x2    //y2
				boolean b = t.tick_x(y1,x1,bt.rows,x2);		//y1 x1 y2 x2	//0
				t.render(0,x1,bt.rows,x2);			//y1 x1 y2 x2
				if(passes<=0){
							hand.set_turn(hand.get_pop());
							if(hand.get_turn() == TURN.PLAYER1)
								hand.set_turn(TURN.PLAYER2);
							else
								hand.set_turn(TURN.PLAYER1);
							passes = 50;
							flag_once = 0;
				}
				passes--;
			}
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
		
		public DropDownMenu getDrop(){
			return(this.Drop.getDropDown());
		}
		public void add_shot(shots s1)
		{
			this.s = s1;//new shots(s1.x,s1.y,s1.velocity,s1.angle); 
		}
		public void remove_shot()
		{
			this.s = null;
		}
		public void set_ang(double a)
		{
			this.t.setAngle(a);
		}
		public double get_x_turr()
		{
			return(t.turret.getX());
		}
		public double get_y_turr()
		{
			return((int)t.turret.getY());
		}
		public void set_x_speed(int xspeed)
		{
			this.t.set_xspeed(xspeed);
		}
		public void set_y_speed(int yspeed)
		{
			this.t.set_yspeed(yspeed);
		}
		public void set_velocity(double v)
		{
			this.velocity += v;
		}
		public void  set_score(int sc)
		{
			this.score+=sc;
		}

}
