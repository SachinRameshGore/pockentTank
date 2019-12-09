import java.awt.*;
import java.util.*;
public class Ending implements button_specifications{
	Player_1 p1;
	Player_2 p2;
	
	String WinPlayer1 = "Player  1  Won";
	String WinPlayer2 = "Player  2  Won";
	String Draw = "Draw";
	
	double angValues[];
	int check = 0;
	int player = 0;
	int x_val[];
	int y_val[];
	
	
	private Projectile projectile[] = new Projectile[5];
	private Projectile X1;
	int flag[] = {0,0,0,0,0};
	Random rd = new Random();
	int count = 0;
	int secondaryCount = 0;
	
	
	
	
	public Ending(Player_1 p1,Player_2 p2){
		Random random = new Random();
		projectile[count] = new Projectile(rd.nextInt(screen_width),500);
		/*passes = new int[WinPlayer1.length()];
		Yspeed = new int[WinPlayer1.length()];*/
		x_val = new int[WinPlayer1.length()];
		y_val = new int[WinPlayer1.length()];
		
		angValues = new double[WinPlayer1.length()];
		
		
		int j = 3;
		for(int i=0;i<WinPlayer1.length();i++){
			x_val[i] = 50+15*i;
			y_val[i] = 300;
		}
		for(int i =0;i<WinPlayer1.length();i++)
		{
			angValues[i] = i*Math.PI/14;
		}
		
	}
	public void render(Graphics G,Player_1 p1,Player_2 p2){
		//System.out.println("HEER");
		G.setFont(new Font("Ariel",Font.BOLD,20));
		if(p1.score > p2.score){
			//System.out.println("HEER");
			if(check == 0)
			{
				check = 1;
				player = 0;
			}
			G.setColor(Color.BLUE);
			for(int i=0;i<WinPlayer1.length();i++){
				G.drawString(""+WinPlayer1.charAt(i),x_val[i],y_val[i]);
			}
			
		}
		else if(p2.score > p1.score){
			if(check == 0)
			{
				check = 1;
				player = screen_width/2;
			}
			G.setColor(Color.GREEN);
			for(int i=0;i<WinPlayer1.length();i++){
				G.drawString(""+WinPlayer2.charAt(i),x_val[i]+400,y_val[i]);	
			}
		}
		else{
			G.setColor(Color.WHITE);
			for(int i=0;i<Draw.length();i++){
				G.drawString(""+Draw.charAt(i),x_val[i]+400,y_val[i]);	
			}
		}
		
		
		
		
		if(projectile[secondaryCount].exploded && flag[secondaryCount] ==1){
				for(int i= 0;i<150;i++){
					projectile[secondaryCount].particle[i].render(G);
				}
			}
			
				projectile[count].render(G);
		
	}
	public void update(){
		if(projectile[count].exploded && flag[count] == 0){
					projectile[count].initiateParticles();
					flag[count] = 1;
					secondaryCount = count;
					count++;
					count = count%5;
					projectile[count] = new Projectile(rd.nextInt(screen_width/2)+player,500);
					flag[count] = 0;
					
		}
		
		for(int i=0;i<WinPlayer1.length();i++){
			y_val[i] = 300 + (int)(20*Math.sin(angValues[i]));
			angValues[i] += Math.PI/15;
		}
		
		
		if(projectile[secondaryCount].exploded && flag[secondaryCount] ==1){
				for(int i= 0;i<150;i++){
					projectile[secondaryCount].particle[i].update();
				}
			}
			
				projectile[count].update();
	}
}
