import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;
import java.util.logging.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
public class Pocket_tank extends Canvas implements Runnable{  

	public button_specifications B;
	private Thread T1 = new Thread(this);
	public Frame_setter fs;
	private boolean running = false;
	static BufferedImage img;
	public handler hand; //////////////////////////////////////////////////////////////////////////
	public Tank tank;		public Player_1 p1;  	public Player_2 p2;		Start_menu start_menu = new Start_menu(); 		
	//public button_setter bt = new button_setter();
	public terrain terra;
	public double velocity = 40;
	public shots shot;
	public double angle_1 = 0;
	public double angle_2 = 0;
	public int pass = 0;
	public int passes = 300;
	//public Pocket_tank pk;  					////******This needs to be changed later on /***********
	
	
	public Pocket_tank()
	{
		hand = new handler();
		
		/*hand.add_player_1();
		hand.add_player_2();*/
		hand.add_terrain();
		this.addKeyListener(new Key_input(hand));
		this.addMouseListener(new Mouse_input(hand));
		this.addMouseMotionListener(new Mouse_input(hand));
		
		fs = new Frame_setter("Pocket_tank",this);
		hand.setFrame(fs.get_frame());
	}

	public void tick_x(double delta) throws NoninvertibleTransformException
	{
		hand.tick_x(delta);
	}
	public void render()
	{
		BufferStrategy B = this.getBufferStrategy();
		if(B == null)
		{
			this.createBufferStrategy(3);
			return;
		}
	
		Graphics G = B.getDrawGraphics();
		
		hand.render(G);
		G.dispose();
		B.show();
	}
	public void clear_x()
	{
		BufferStrategy B1 = this.getBufferStrategy();
		if(B1 == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics G = B1.getDrawGraphics();
		G.setColor(Color.BLACK);
		G.fillRect(0,0,B.screen_width,B.screen_height);
	}
	public synchronized void run() 
	{
		long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000.0 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
		double lastFrame = 0;
		double updateTime = 0;
		long target_time = 1000/30;
		while(running)
		{
			long now = System.nanoTime();
		//	start_time = System.nanoTime();
		updateTime = now-lastTime;
			delta += updateTime/ns;
			lastTime = now;
			
			lastFrame += updateTime;
			frames++;
			
			 if (lastFrame >= 1000000000)
			{
				fs.JF.setTitle("(FPS: "+frames+")");
				lastFrame = 0;
				frames = 0;
			}
			
			if(hand.get_state() == STATE.GAME || hand.get_state() == STATE.END || hand.get_state() == STATE.WEAPONPICK){
				//while(delta >= 1)
				//{
					if(hand.get_state() != STATE.PAUSE)
						clear_x();
					try{
					tick_x(delta);					////////////////////////////////////////////////////////////
					//System.out.println("delta: "+ delta);
					}catch(NoninvertibleTransformException E){}
					delta--;
				//}
			}
			//if(running)
				render();
			
			try{Thread.sleep( (lastTime-System.nanoTime() +(long) ns)/(long)1000000.0 );}catch(Exception E){}
		
		}
	}
	public synchronized void stop()
	{
		try{
				T1.join();
				running = false;
		}catch(Exception e){e.printStackTrace();}
	}
	public synchronized void start() 
	{
		setBackground(Color.BLACK);
		T1.start();
		running = true;
	}
	public static void main(String args[]){
		  new Pocket_tank();
	}
}