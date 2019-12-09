import java.awt.event.*;
import java.util.*;

public class Key_input extends KeyAdapter{
	
	handler hand;
	public Key_input(handler hand)
	{
		this.hand = hand;
	}
	public void keyPressed(KeyEvent E)
	{
		int key = E.getKeyCode();
		
		if(key == E.VK_RIGHT)
		{
			if(hand.get_turn() == TURN.PLAYER1)
			{
				hand.get_p1().set_x_speed(2);
			}
			else if(hand.get_turn() == TURN.PLAYER2)
			{
				hand.get_p2().set_x_speed(2);
			}
		}
		if(key == E.VK_LEFT)
		{
			if(hand.get_turn() == TURN.PLAYER1)
			{
				hand.get_p1().set_x_speed(-2);
			}
			else if(hand.get_turn() == TURN.PLAYER2)
			{
				hand.get_p2().set_x_speed(-2);
			}
		}
		if(key == E.VK_ESCAPE)
		{
			if(hand.get_state() == STATE.GAME){
				hand.set_state(STATE.PAUSE);
			}else if(hand.get_state() == STATE.HELP)
			{
				hand.set_state(hand.prevState);
			}
		}
	}

}
