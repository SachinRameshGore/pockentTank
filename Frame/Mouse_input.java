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
		CurrentWeaponPlayer1 = hand.get_p1().getDrop().ListWeapons.get(hand.get_p1().getDrop().fframe);
		CurrentWeaponPlayer2 = hand.get_p2().getDrop().ListWeapons.get(hand.get_p2().getDrop().fframe);
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
					WeaponChoice(CurrentWeaponPlayer1,hand.get_p1(),null);
					hand.set_pop(hand.get_turn());
					hand.get_p1().weaponCount--;
					hand.set_turn (TURN.LOADING);
					
					hand.get_p1().drop.WeaponList.remove(hand.get_p1().drop.index);
					hand.get_p1().drop.incrementIndex();
					updateWeapon(1);
					
						
					//Drop Down Menu!!!!**//
					
					/*if(hand.get_p1().getDrop().getState() == DropState.CLICKED)
						hand.get_p1().getDrop().setState(DropState.NOT_CLICKED);
					hand.get_p1().getDrop().getListWeapons().remove(hand.get_p1().getDrop().fframe);
					hand.get_p1().getDrop().getListRect().remove(hand.get_p1().getDrop().fframe);
					hand.get_p1().getDrop().updateRects();
					if(hand.get_p1().getDrop().ListWeapons.size() > 0)
						hand.get_p1().getDrop().fframe = 0;*/
					
					
				}
				else if(hand.get_turn() == TURN.PLAYER2){
					WeaponChoice(CurrentWeaponPlayer2,null,hand.get_p2());
					hand.get_p2().weaponCount--;
					hand.set_pop(hand.get_turn());
					hand.set_turn(TURN.LOADING);
					
					hand.get_p2().drop.WeaponList.remove(hand.get_p2().drop.index);
					hand.get_p2().drop.incrementIndex();
					updateWeapon(2);
					
					
					//Drop Down Menu!!!!**//
					/*if(hand.get_p2().getDrop().getState() == DropState.CLICKED)
						hand.get_p2().getDrop().setState(DropState.NOT_CLICKED);
					hand.get_p2().Drop.getListWeapons().remove(hand.get_p1().getDrop().fframe);
					hand.get_p2().getDrop().getListRect().remove(hand.get_p1().getDrop().fframe);
					hand.get_p2().getDrop().updateRects();
					if(hand.get_p2().getDrop().ListWeapons.size() > 0)
						hand.get_p2().getDrop().fframe = 0;*/
					
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
			///////////*///////////////////////**********************************************************/
			
			////////////*********Handle The Drop Down Menu !!!****************************/////////////////
			
		/*else if((mouseX > B.listSelectorX && mouseX < (B.listSelectorX+B.listSelectorWidth)) && (mouseY > B.listSelectorY && mouseY < (B.listSelectorY+B.listSelectorHeight))){
			
			
			if(hand.get_turn() == TURN.PLAYER1){
				if(hand.get_p1().getDrop().getState() == DropState.CLICKED){
					hand.get_p1().getDrop().setState(DropState.NOT_CLICKED);
				}
				else if(hand.get_p1().getDrop().getState() == DropState.NOT_CLICKED){
					hand.get_p1().getDrop().setState(DropState.CLICKED);
				}
				
			
			}
			else if(hand.get_turn() == TURN.PLAYER2){
				if(hand.get_p2().getDrop().getState() == DropState.CLICKED)
					hand.get_p2().getDrop().setState(DropState.NOT_CLICKED);
				else if(hand.get_p2().getDrop().getState() == DropState.NOT_CLICKED)
					hand.get_p2().getDrop().setState(DropState.CLICKED);
			}
			
			
			
		}
		else if(hand.get_p1().getDrop().getState() == DropState.CLICKED || hand.get_p2().getDrop().getState() == DropState.CLICKED){
			if(hand.get_turn() == TURN.PLAYER1){
				 if(hand.get_p1().getDrop().getState() == DropState.CLICKED){
					
					for(int i= 0; i< hand.get_p1().getDrop().getSizeOfWeapon();i++){
						if((mouseX > B.fieldX && mouseX < (B.fieldX+B.fieldWidth)) && 
						   (mouseY > B.fieldY+(i*B.fieldHeight) && mouseY < (B.fieldY+(i*B.fieldHeight)+B.fieldHeight))){
							hand.get_p1().getDrop().setState(DropState.NOT_CLICKED);
							tempString = hand.get_p1().getDrop().getListWeapons().get(i);
							hand.get_p1().getDrop().getListWeapons().set(i,hand.get_p1().getDrop().getListWeapons().get(0));
							hand.get_p1().getDrop().getListWeapons().set(0,tempString);
							//hand.get_p1().getDrop().setPrevFrame(hand.get_p1().getDrop().fframe);
							//hand.get_p1().getDrop().setFFrame(i);
						}
					}
					CurrentWeaponPlayer1 = hand.get_p1().getDrop().ListWeapons.get(hand.get_p1().getDrop().fframe);
				}
			}else if(hand.get_turn() == TURN.PLAYER2){
				 if(hand.get_p2().getDrop().getState() == DropState.CLICKED){
					
					for(int i= 0; i< hand.get_p2().getDrop().getSizeOfWeapon();i++){
						if((mouseX > B.fieldX && mouseX < (B.fieldX+B.fieldWidth)) && 
						   (mouseY > B.fieldY+(i*B.fieldHeight) && mouseY < (B.fieldY+(i*B.fieldHeight)+B.fieldHeight))){
							hand.get_p2().getDrop().setState(DropState.NOT_CLICKED);
							tempString = hand.get_p2().getDrop().getListWeapons().get(i);
							hand.get_p2().getDrop().getListWeapons().set(i,hand.get_p2().getDrop().getListWeapons().get(0));
							hand.get_p2().getDrop().getListWeapons().set(0,tempString);
							//hand.get_p2().getDrop().setPrevFrame(hand.get_p2().getDrop().fframe);
							//hand.get_p2().getDrop().setFFrame(i);
						}
					}
				}
				CurrentWeaponPlayer2 = hand.get_p2().getDrop().ListWeapons.get(hand.get_p2().getDrop().fframe);
			}
		}*/
	}
	

	public void mouseMoved(MouseEvent E){
		int mouseX = E.getX(), mouseY = E.getY();
		/*if(hand.get_turn() == TURN.PLAYER1){
			if(hand.get_p1().getDrop().getState() == DropState.CLICKED){
				for(int i= 0; i< hand.get_p1().getDrop().getSizeOfWeapon();i++){
					if((mouseX > fieldX && mouseX < (fieldX+fieldWidth)) && 
					   (mouseY > fieldY+(i*fieldHeight) && mouseY < (fieldY+(i*fieldHeight)+fieldHeight))){
						hand.get_p1().getDrop().setRect(new Rectangle(fieldX+1,(fieldY+(i*fieldHeight)+1),fieldWidth-listSelectorWidth-2,fieldHeight-2));
						
					}
				}
				
			}
		}else if(hand.get_turn() == TURN.PLAYER2){
			if(hand.get_p2().getDrop().getState() == DropState.CLICKED){
				for(int i= 0; i< hand.get_p2().getDrop().getSizeOfWeapon();i++){
					if((mouseX > fieldX && mouseX < (fieldX+fieldWidth)) && 
					   (mouseY > fieldY+(i*fieldHeight) && mouseY < (fieldY+(i*fieldHeight)+fieldHeight))){
						hand.get_p2().getDrop().setRect(new Rectangle(fieldX+1,(fieldY+(i*fieldHeight)+1),fieldWidth-listSelectorWidth-2,fieldHeight-2));
						
					}
				}
				
			}
		}*/ if(hand.get_turn() == TURN.ANGLECHANGE){
			
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
		mouseMenuPad(mouseX,mouseY);
	}
/*****************************************************************************************************/	
	
	public void WeaponChoice(String WeaponName,Player_1 p1, Player_2 p2){
		if(p1 == null){
			if(WeaponName.compareTo("Big Shot") == 0)
				hand.get_p2().add_shot(new Big_shot((double)hand.get_p2().t.get_x_turret(),(double)hand.get_p2().t.get_y_turret()-5,-hand.get_p2().velocity,hand.get_p2().t.ang));
			else if(WeaponName.compareTo("Cruiser") == 0)
				hand.get_p2().add_shot(new Big_shot((double)hand.get_p2().t.get_x_turret(),(double)hand.get_p2().t.get_y_turret()-5,-hand.get_p2().velocity,hand.get_p2().t.ang));
			else if(WeaponName.compareTo("Sniper") == 0)
				hand.get_p2().add_shot(new Big_shot((double)hand.get_p2().t.get_x_turret(),(double)hand.get_p2().t.get_y_turret()-5,-hand.get_p2().velocity,hand.get_p2().t.ang));
			else if(WeaponName.compareTo("Ground-Hog") == 0)
				hand.get_p2().add_shot(new Big_shot((double)hand.get_p2().t.get_x_turret(),(double)hand.get_p2().t.get_y_turret()-5,-hand.get_p2().velocity,hand.get_p2().t.ang));
			
		}else if(p2 == null){
			if(WeaponName.compareTo("Big Shot") == 0)
				hand.get_p1().add_shot(new Big_shot((double)hand.get_p1().t.get_x_turret(),(double)hand.get_p1().t.get_y_turret()-5,-hand.get_p1().velocity,hand.get_p1().t.ang));
			else if(WeaponName.compareTo("Cruiser") == 0)
				hand.get_p1().add_shot(new Big_shot((double)hand.get_p1().t.get_x_turret(),(double)hand.get_p1().t.get_y_turret()-5,-hand.get_p1().velocity,hand.get_p1().t.ang));
			else if(WeaponName.compareTo("Sniper") == 0)
				hand.get_p1().add_shot(new Big_shot((double)hand.get_p1().t.get_x_turret(),(double)hand.get_p1().t.get_y_turret()-5,-hand.get_p1().velocity,hand.get_p1().t.ang));
			else if(WeaponName.compareTo("Ground-Hog") == 0)
				hand.get_p1().add_shot(new Big_shot((double)hand.get_p1().t.get_x_turret(),(double)hand.get_p1().t.get_y_turret()-5,-hand.get_p1().velocity,hand.get_p1().t.ang));
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
			CurrentWeaponPlayer1 = hand.get_p1().drop.WeaponList.get(hand.get_p1().drop.index);
		}
		else if(i == 2){
			CurrentWeaponPlayer2 = hand.get_p2().drop.WeaponList.get(hand.get_p2().drop.index);
		}
	}
	
	/***************************Handle The Start*///////////////////////////////////////////////////////////////
	
	public void handleStart(int mouseX,int mouseY){
		
			if((B.start_x <= mouseX && mouseX <= B.start_x+300) && (B.start_y <= mouseY && mouseY <= B.start_y+100))
			{
				hand.set_state(STATE.GAME);
			}
			if((B.quit_x <= mouseX && mouseX <= B.quit_x+300) && (B.quit_y <= mouseY && mouseY <= B.quit_y+100))
			{
				//hand.set_state(STATE.GAME);
				System.exit(0);
			}
	}
	
}
