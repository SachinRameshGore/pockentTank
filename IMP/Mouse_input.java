import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class Mouse_input extends MouseAdapter implements MouseMotionListener,button_specifications{
	
	private handler hand;
	private button_specifications B;
	
	String CurrentWeaponPlayer1;
	String CurrentWeaponPlayer2;
	
	
	String tempString = "";
	
	double angle_1 = 0,angle_2 = 0,velocity = 59;
	
	public Mouse_input(handler hand)
	{
		this.hand = hand;
		//CurrentWeaponPlayer1 = hand.get_p1().getDrop().ListWeapons.get(hand.get_p1().getDrop().fframe);
		//CurrentWeaponPlayer2 = hand.get_p2().getDrop().ListWeapons.get(hand.get_p2().getDrop().fframe);
	}
	public void mousePressed(MouseEvent E)
	{
			int a,b;
			a = E.getX();
			b = E.getY();
		if(hand.get_turn() == TURN.ANGLECHANGE){
			if(hand.get_pop() == TURN.PLAYER1){
				hand.set_turn(TURN.PLAYER1);
			}else if(hand.get_pop() == TURN.PLAYER2){
				hand.set_turn(TURN.PLAYER2);
			}
		}
		if(hand.get_state() == STATE.GAME)
		{
				handleGame(a,b);
		}
		else if(hand.get_state() == STATE.MENU)
		{
				handleStart(a,b);
		}
		else if(hand.get_state() == STATE.HELP)
		{
				if(checkInTheBox(a,b,hand.help.forward))
				{
					hand.help.stateChange(1);
				}
				else if(checkInTheBox(a,b,hand.help.backward))
				{
					hand.help.stateChange(0);
				}
		}
		else if(hand.get_state() == STATE.WEAPONPICK){
			handleWEaponPick(a,b);
		}else if(hand.get_state() == STATE.PAUSE){
			if(checkInTheBox(a,b,hand.pause.ResumeGame)){
				hand.set_state(STATE.GAME);
			}
			else{
				handleStart(a,b);
			}
		}
	}
	
	
	
	
	
	
	
	
	/*****************************Handle The Game*///////////////////////////////////////////////////////////////
	
	public void handleGame(int mouseX,int mouseY){
		
			/***************************Angle Increase or Decrease***************************/
			
			if((B.inc_ang_x <= mouseX && mouseX <= B.inc_ang_x+20) && (B.inc_ang_y <= mouseY && mouseY <= B.inc_ang_y+35))
			{
				if(hand.get_turn() == TURN.PLAYER1){
					hand.get_p1().t.setAngle(Math.PI/120);
					//angle_1 += 3;
				}
				else if(hand.get_turn() == TURN.PLAYER2){
					hand.get_p2().t.setAngle(Math.PI/120);
					//angle_2 += 3;
					//tank.set_angle(1);
				}
				
			}
			else if((B.dnc_ang_x <= mouseX && mouseX <= B.dnc_ang_x+20) && (B.dnc_ang_y <= mouseY && mouseY <= B.dnc_ang_y+35))
			{
				if(hand.get_turn() == TURN.PLAYER1){
					hand.get_p1().t.setAngle(-Math.PI/120);
					//angle_1 -= 3;
				}
				else if(hand.get_turn() == TURN.PLAYER2){
					hand.get_p2().t.setAngle(-Math.PI/120);
					//angle_2 -= 3;
				}
				
				//tank.set_angle(-1);
			}
		   /***************************///Angle Increase or Decrease***************************/
			
			
			/*****************************Fire ********************************************/
			else if((B.Fire_x <= mouseX && mouseX <= B.Fire_x+60) && (B.Fire_y <= mouseY && mouseY <= B.Fire_y+50))
			{
				if(hand.get_turn() == TURN.PLAYER1){
					WeaponChoice(hand.get_p1().drop.CurrentWeapon,hand.get_p1(),null);
					hand.set_pop(hand.get_turn());
					hand.get_p1().weaponCount--;
					hand.set_turn (TURN.LOADING);
					
					hand.get_p1().drop.WeaponList.remove(hand.get_p1().drop.index);
					hand.get_p1().drop.incrementIndex();
					hand.get_p1().drop.updateWeapon();
					//updateWeapon(1);
					
				}
				else if(hand.get_turn() == TURN.PLAYER2){
					WeaponChoice(hand.get_p2().drop.CurrentWeapon,null,hand.get_p2());
					hand.get_p2().weaponCount--;
					hand.set_pop(hand.get_turn());
					hand.set_turn(TURN.LOADING);
					
					hand.get_p2().drop.WeaponList.remove(hand.get_p2().drop.index);
					hand.get_p2().drop.incrementIndex();
					hand.get_p2().drop.updateWeapon();
					//updateWeapon(2);
					
				}
			}
			/*************************//*******************************************/
			/**************************ANGLE /********************************************/
			
			else if((B.angle_label_x <= mouseX && mouseX <= B.angle_label_x+55) && (B.angle_label_y <= mouseY && mouseY <= B.angle_label_y+20))
			{
				if(hand.get_turn() == TURN.PLAYER1){
					hand.set_pop(TURN.PLAYER1);
					hand.set_turn(TURN.ANGLECHANGE);
				}
				else if(hand.get_turn() == TURN.PLAYER2){
					hand.set_pop(TURN.PLAYER2);
					hand.set_turn(TURN.ANGLECHANGE);
				}
			}
			
			/*////////***////////// ////////////////////////////////////////////////////
			
			
			else if((B.drop_down_inc_x <= mouseX && mouseX <= B.drop_down_inc_x+20) && (B.drop_down_inc_y <= mouseY && mouseY <= B.drop_down_inc_y+20))
			{
				if(hand.get_turn() == TURN.PLAYER1){
					hand.get_p1().drop.incrementIndex();
					updateWeapon(1);
				}
				else if(hand.get_turn() == TURN.PLAYER2){
					hand.get_p2().drop.incrementIndex();
					updateWeapon(2);
				}
				
			}
			
			else if((B.drop_down_dnc_x <= mouseX && mouseX <= B.drop_down_dnc_x+20) && (B.drop_down_dnc_y <= mouseY && mouseY <= B.drop_down_dnc_y+20))
			{
				if(hand.get_turn() == TURN.PLAYER1){
					hand.get_p1().drop.decrementIndex();
					updateWeapon(1);
				}
				else if(hand.get_turn() == TURN.PLAYER2){
					hand.get_p2().drop.decrementIndex();
					updateWeapon(2);
				}
			}
			
			
			
			///////////*///////////////////////Power Increase Decrease************************/
			
			else if((B.inc_pow_x <= mouseX && mouseX <= B.inc_pow_x+20) && (B.inc_pow_y <= mouseY && mouseY <= B.inc_pow_y+35))
			{
				if(hand.get_turn() == TURN.PLAYER1){
					hand.get_p1().set_velocity(3);
					//angle_1 += 3;
				}
				else if(hand.get_turn() == TURN.PLAYER2){
					hand.get_p2().set_velocity(3);
					//angle_2 += 3;
					//tank.set_angle(1);
				}
				
			}
			else if((B.dnc_pow_x <= mouseX && mouseX <= B.dnc_pow_x+20) && (B.dnc_pow_y <= mouseY && mouseY <= B.dnc_pow_y+35))
			{
				if(hand.get_turn() == TURN.PLAYER1){
					hand.get_p1().set_velocity(-3);
					//angle_1 -= 3;
				}
				else if(hand.get_turn() == TURN.PLAYER2){
					hand.get_p2().set_velocity(-3);
				}
				
				//tank.set_angle(-1);
			}
	}
	

	public void mouseMoved(MouseEvent E){
		int mouseX = E.getX(), mouseY = E.getY();
		 if(hand.get_turn() == TURN.ANGLECHANGE){
			
			if(hand.get_pop() == TURN.PLAYER1){
				int pointX = (int)(hand.get_p1().t.get_x_turret()+7);
				int pointY = (int)(hand.get_p1().t.get_y_turret()+1);
				
				int targetX = mouseX - pointX;
				int targetY = mouseY - pointY;
				
				double convertAngle = Math.atan2(targetY,targetX);
				
				hand.get_p1().t.setAnglex(convertAngle);
			}
			else if(hand.get_pop() == TURN.PLAYER2){
				int pointX = (int)(hand.get_p2().t.get_x_turret()+7);
				int pointY = (int)(hand.get_p2().t.get_y_turret()+1);
				
				int targetX = mouseX - pointX;
				int targetY = mouseY - pointY;
				
				double convertAngle = Math.atan2(targetY,targetX);
				
				hand.get_p2().t.setAnglex(convertAngle);
			}
			
		}
		else if(hand.get_state() == STATE.WEAPONPICK){
			
			for(Rectangle r : hand.weaponChoice.list){
				if(checkInTheBox(mouseX,mouseY,r)){
						hand.weaponChoice.setRect(new Rectangle((int)r.getMinX()+1 ,(int)r.getMinY()+1 , (int)r.getWidth()-2 ,(int)r.getHeight()-2));
					}
				
			}
		}
		mouseMenuPad(mouseX,mouseY);
	}
/*****************************************************************************************************/	
	
	public void WeaponChoice(String WeaponName,Player_1 p1, Player_2 p2){
		if(p1 == null){
			if(WeaponName.compareTo("Big Shot") == 0)
				hand.get_p2().add_shot(new Big_shot((double)hand.get_p2().t.get_x_turret(),(double)hand.get_p2().t.get_y_turret()-5,-hand.get_p2().velocity,hand.get_p2().t.ang));
			else if(WeaponName.compareTo("Cruiser") == 0)
				hand.get_p2().add_shot(new Cruiser((double)hand.get_p2().t.get_x_turret(),(double)hand.get_p2().t.get_y_turret()-5,-hand.get_p2().velocity,hand.get_p2().t.ang));
			else if(WeaponName.compareTo("Sniper") == 0)
				hand.get_p2().add_shot(new Sniper((double)hand.get_p2().t.get_x_turret(),(double)hand.get_p2().t.get_y_turret()-5,-hand.get_p2().velocity,hand.get_p2().t.ang));
			else if(WeaponName.compareTo("Groundhog") == 0)
				hand.get_p2().add_shot(new Ground_hog((double)hand.get_p2().t.get_x_turret(),(double)hand.get_p2().t.get_y_turret()-5,-hand.get_p2().velocity,hand.get_p2().t.ang));
			else if(WeaponName.compareTo("Jackhammer") == 0)
				hand.get_p2().add_shot(new JackHammer((double)hand.get_p2().t.get_x_turret(),(double)hand.get_p2().t.get_y_turret()-5,-hand.get_p2().velocity,hand.get_p2().t.ang));
			else if(WeaponName.compareTo("Worm") == 0)
				hand.get_p2().add_shot(new Worm((double)hand.get_p2().t.get_x_turret(),(double)hand.get_p2().t.get_y_turret()-5,-hand.get_p2().velocity,hand.get_p2().t.ang));
			
		}else if(p2 == null){
			if(WeaponName.compareTo("Big Shot") == 0)
				hand.get_p1().add_shot(new Big_shot((double)hand.get_p1().t.get_x_turret(),(double)hand.get_p1().t.get_y_turret()-5,-hand.get_p1().velocity,hand.get_p1().t.ang));
			else if(WeaponName.compareTo("Cruiser") == 0)
				hand.get_p1().add_shot(new Cruiser((double)hand.get_p1().t.get_x_turret(),(double)hand.get_p1().t.get_y_turret()-5,-hand.get_p1().velocity,hand.get_p1().t.ang));
			else if(WeaponName.compareTo("Sniper") == 0)
				hand.get_p1().add_shot(new Sniper((double)hand.get_p1().t.get_x_turret(),(double)hand.get_p1().t.get_y_turret()-5,-hand.get_p1().velocity,hand.get_p1().t.ang));
			else if(WeaponName.compareTo("Groundhog") == 0)
				hand.get_p1().add_shot(new Ground_hog((double)hand.get_p1().t.get_x_turret(),(double)hand.get_p1().t.get_y_turret()-5,-hand.get_p1().velocity,hand.get_p1().t.ang));
			else if(WeaponName.compareTo("Jackhammer") == 0)
				hand.get_p1().add_shot(new JackHammer((double)hand.get_p1().t.get_x_turret(),(double)hand.get_p1().t.get_y_turret()-5,-hand.get_p1().velocity,hand.get_p1().t.ang));
			else if(WeaponName.compareTo("Worm") == 0)
				hand.get_p1().add_shot(new Worm((double)hand.get_p1().t.get_x_turret(),(double)hand.get_p1().t.get_y_turret()-5,-hand.get_p1().velocity,hand.get_p1().t.ang));
		}
	}
	
	
	public void mouseMenuPad(int mouseX,int mouseY){
		
		 if((B.Fire_x <= mouseX && mouseX <= B.Fire_x+60) && (B.Fire_y <= mouseY && mouseY <= B.Fire_y+50))
			{
				if(hand.bt.focus == MenuPad.NONE)
					hand.bt.focus = MenuPad.FIRE;
			}
			
			else if((B.angle_label_x <= mouseX && mouseX <= B.angle_label_x+55) && (B.angle_label_y <= mouseY && mouseY <= B.angle_label_y+20))
			{
				if(hand.bt.focus == MenuPad.NONE)
					hand.bt.focus = MenuPad.ANGLE;
			}
			else if((B.power_label_x <= mouseX && mouseX <= B.power_label_x+55) && (B.power_label_y <= mouseY && mouseY <= B.power_label_y+20))
			{
				if(hand.bt.focus == MenuPad.NONE)
					hand.bt.focus = MenuPad.POWER;
			}
			else 
			{
				if(hand.bt.focus != MenuPad.NONE)
					hand.bt.focus = MenuPad.NONE;
			}
		
		
		
	}
	
	public void updateWeapon(int i){
		if(i == 1){
			if(hand.get_p1().drop.index >= 0 && hand.get_p1().drop.WeaponList.size() > 0)
			CurrentWeaponPlayer1 = hand.get_p1().drop.WeaponList.get(hand.get_p1().drop.index);
		}
		else if(i == 2){
			if(hand.get_p2().drop.index >= 0 && hand.get_p2().drop.WeaponList.size() > 0)
			CurrentWeaponPlayer2 = hand.get_p2().drop.WeaponList.get(hand.get_p2().drop.index);
		}
	}
	
	/***************************Handle The Start*///////////////////////////////////////////////////////////////
	
	public void handleStart(int mouseX,int mouseY){
		
			if((B.start_x <= mouseX && mouseX <= B.start_x+300) && (B.start_y <= mouseY && mouseY <= B.start_y+100))
			{
				if(hand.get_state() == STATE.PAUSE){
					hand.frame.setVisible(false);
					new Pocket_tank();
				}
				else{
				hand.set_state(STATE.WEAPONPICK);
				}
			}
			else if((B.quit_x <= mouseX && mouseX <= B.quit_x+300) && (B.quit_y <= mouseY && mouseY <= B.quit_y+100)){
				System.exit(0);
			}
			else if((B.help_x <= mouseX && mouseX <= B.help_x+300) && (B.help_y <= mouseY && mouseY <= B.help_y+100)){
				System.out.println("\n Help");
				hand.prevState = hand.get_state();
				hand.set_state(STATE.HELP);
			}
	}
	public void handleWEaponPick(int mouseX,int mouseY){
			//synchronized (this){
				for(int i=0; i<hand.weaponChoice.list.size();i++){
				if(checkInTheBox(mouseX,mouseY,hand.weaponChoice.list.get(i))){
					hand.weaponChoice.list.remove(i);
					if(hand.weaponChoice.t == PickTurn.Player1){
						hand.weaponChoice.player1Weapons.add(hand.weaponChoice.ListWeapons.get(i));
						hand.weaponChoice.t = PickTurn.Player2;
					}
						
					else{
						hand.weaponChoice.player2Weapons.add(hand.weaponChoice.ListWeapons.get(i));
						hand.weaponChoice.t = PickTurn.Player1;
					}
					hand.weaponChoice.ListWeapons.remove(i);
					
				}
			//}
		}
	}
	public boolean checkInTheBox(int mouseX,int mouseY,Rectangle box){
		if(mouseX > box.getMinX() && mouseX < box.getMaxX() && mouseY > box.getMinY() && box.getMaxY() > mouseY)
			return true;
		else
			return false;
	}
	
}
