import java.util.logging.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;

/*this class handles all the game objects !!!*/



public class handler{
	private Player_1 p1;
	private Player_2 p2;
	private terrain terra;
	
	
	
	public STATE state = STATE.MENU;
	
	public TURN turn = TURN.PLAYER1;
	
	public TURN pop;
	
	public Start_menu start_menu = new Start_menu();
	public Ending End;
	//public damage_report dr = new damage_report();
	//public damage_terrain dt = new damage_terrain();
	
	button_specifications B;
	LinkedList <shots> shot = new LinkedList <shots>();			//This is useful in future!!!
	public button_setter bt;
	public animate_arrow arrow;
	int passes = 50;
	int flag_once = 0;
	int count = 0;
	int GameEnded = 0;
	
	public handler()
	{
		bt = new button_setter(this);
		arrow = new animate_arrow();
		
		this.p1 = new Player_1(this);
		this.p2 = new Player_2(this);
		
		End = new Ending(p1,p2);
	}
	public void render(Graphics G) ///***** we need to pass three things Graphics , STATE , TURN ;****
	{
		if(state == STATE.GAME || state == STATE.END)
		{
			
			
			
			terra.render(G);
			p1.render(G);
			p2.render(G);
			if(turn == TURN.PLAYER1){
				bt.render(G,p1,null);//,count++);
				p1.renderDropDown(G);
			}
			else{
				bt.render(G,null,p2);//,count++);
				p2.renderDropDown(G);
			}
			if(state == STATE.GAME)
				arrow.render(G,this);
			
			
			if(state == STATE.END){
				//System.out.print("asd");
				End.render(G,p1,p2);
			}
			
		}
		else if(state == STATE.MENU)
		{
			start_menu.render(G);
		}
		
		/*All the objects should be rendered here!!!!*/
	}
	public void tick_x(double delta) throws NoninvertibleTransformException				// 2 things STATE,TURN
	{
		///double dist;
		/*All the objects should be updated here!!!!*/
		if(GameEnded == 1 && turn == TURN.PLAYER1){
			state = STATE.END;
			GameEnded = 0;
		}
		
		if(state == STATE.GAME )//&& turn != TURN.LOADING)
		{
				p1.tick_x(terra,this);
				p2.tick_x(terra,this);
			
		}
		if(state == STATE.END){
			//System.out.print("asd");
			End.update();
		}
		if(p1.weaponCount == 0 && p2.weaponCount == 0){
				GameEnded = 1;
		}
		
	}
	public void add_player_1()
	{
		this.p1 = new Player_1(this);
	}
	public void add_player_2()
	{
		this.p2 = new Player_2(this);
	}
	public void add_terrain()
	{
		this.terra = new terrain();
		this.terra.load();
	}
	public terrain get_terrain()
	{
		return(this.terra);
	}
	public Player_1 get_p1()
	{
		return(this.p1);
	}
	public Player_2 get_p2()
	{
		return(this.p2);
	}
	public STATE get_state()
	{
		return(this.state);
	}
	public TURN get_turn()
	{
		return(this.turn);
	}
	public TURN get_pop()
	{
		return(this.pop);
	}
	public void set_state(STATE state)
	{
		this.state = state;
	}
	public void set_turn(TURN turn)
	{
		this.turn = turn;
	}
	public void set_pop(TURN turn)
	{
		this.pop = turn;
	}
	
}
