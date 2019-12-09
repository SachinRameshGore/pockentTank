import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class WeaponChoice implements button_specifications{
	
	Rectangle Player_1;
	Rectangle Player_2;
	
	Rectangle selectionBox;
	String player1 = "Player 1";
	String player2 = "Player 2";
	int passer = 0;
	Rectangle part = new Rectangle();
	PickTurn t = PickTurn.Player1;
	public  LinkedList <String> ListWeapons = new LinkedList <String>();
	public LinkedList <Rectangle> list = new LinkedList <Rectangle>();
	
	public LinkedList <String> player1Weapons = new LinkedList <String>();
	public LinkedList <String> player2Weapons = new LinkedList <String>();
	
	
	private int flag = 0;
	private int frame = 0;
	handler hand;
	
	Font f1 = null;
	Font f2 = null;
	Font f3 = null;
	
	int maxWeapons = 10;
	
	public WeaponChoice(handler hand){
		randomSelection(maxWeapons);
		Player_1 = new Rectangle(20,80,200,screen_height-240);
		Player_2 = new Rectangle(screen_width-200-40,80,200,screen_height-240);
		for(int i=0;i<maxWeapons;i++){
			list.add(new Rectangle(fieldX,fieldY+((i)*fieldHeight),fieldWidth,fieldHeight));
		}
		int distance =  (screen_width-200-40)-220;
		distance -= 30;
			System.out.println(distance);
		selectionBox = new Rectangle(235,30,distance,screen_height-90);
		this.hand = hand;
		
		try{
			f1 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("prototype.ttf"))).deriveFont(Font.PLAIN,10);
			f2 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("prototype.ttf"))).deriveFont(Font.PLAIN,20);
			f3 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("prototype.ttf"))).deriveFont(Font.PLAIN,15);
		}catch(Exception E){}
		
		
	}
	public void   randomSelection(int maxWeapons){
		
		Random rnd = new Random();
		String[] arraylist = {"Groundhog","Cruiser","Jackhammer","Big Shot","Worm","Sniper"};
		
		for(int i=0;i<maxWeapons;i++){
			int random = rnd.nextInt(6);
			ListWeapons.add(arraylist[random]);
		}
		
	}
	public void   renderSelection(Graphics2D G) {
		G.setFont(f3);
		G.setColor(Color.WHITE);
		int i =0;
			/*for(Rectangle r : list){
				G.drawString(ListWeapons.get(i),(int)(r.getMinX()+r.getWidth()/3),(int)r.getMinY()+20);
				i++;
			}*/
			for(int j=0;j<list.size();j++){
				G.drawString(ListWeapons.get(i),(int)(list.get(j).getMinX()+list.get(j).getWidth()/3),(int)list.get(j).getMinY()+20);
				i++;
			}
		/*Iterator Iterator = list.iterator();
		while (Iterator.hasNext()) {
			G.drawString(ListWeapons.get(i),(int)(((Rectangle)Iterator.next()).getMinX()+((Rectangle)Iterator.next()).getWidth()/3),(int)((Rectangle)Iterator.next()).getMinY()+20);
			i++;
		}*/
	
	}
	public void  render(Graphics G) {
		Graphics2D g = (Graphics2D)G;
		g.setFont(f2);
		g.setColor(Color.WHITE);
		g.drawString(player1,20,60);
		g.drawString(player2,screen_width-200-40,60);
		
		g.drawString("Weapons", 235+fieldWidth/2-42, 60);
		g.setColor(Color.WHITE);
		
		
		g.draw(Player_1);
		g.draw(Player_2);
		g.draw(selectionBox);
		g.setColor(new Color(200,100,100,80));
	
		
		
		/*Iterator Iterator = list.iterator();
        while (Iterator.hasNext()) {
         //  System.out.println(Iterator.next());
			g.fill((Rectangle)Iterator.next());
        }*/
		
		for(int i=0;i<list.size();i++){
			g.fill(list.get(i));
		}
		
		
		drawWeaponsPlayers(g);		
		g.setColor(new Color(255,255,255,100));
		g.fill(part);
		Glow(G);
		renderSelection(g);
		if(getSizeOfWeapon() == 0){
			hand.set_state(STATE.GAME);
			passOnTheList();
		}
	}
	public void drawWeaponsPlayers(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(f3);
		for(int i=0;i<player1Weapons.size();i++)
		{
			g.drawString(player1Weapons.get(i),40+75,100+30*(i));
		}
		for(int i=0;i<player2Weapons.size();i++)
		{
			g.drawString(player2Weapons.get(i),screen_width-200-40+85,100+30*(i));
		}
	}
	public int getSizeOfWeapon(){
		return this.ListWeapons.size();
	}
	public void setRect(Rectangle r){
		part.setRect(r);
	}
	public void Glow(Graphics G){
		if(passer % 10 == 0 || flag ==1 ){
			G.setColor(Color.GREEN);
			
			if(t == PickTurn.Player1)
				G.drawRect(18,78, 204, screen_height-236);
			else{
				G.drawRect(screen_width-200-42,78,204,screen_height-236);
			}
			flag = 1;
			frame++;
			if(frame > 20)
			{
				frame = 0;
				flag = 0;
			}
			
		}
		passer++;
		passer %= 1000;
	}
	public void passOnTheList(){
		hand.get_p1().drop.setWeaponList(player1Weapons);
		hand.get_p2().drop.setWeaponList(player2Weapons);
	}
	
}
