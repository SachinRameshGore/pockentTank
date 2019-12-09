import java.awt.Canvas;
import java.awt.geom.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class DropDownMenu implements button_specifications{
	public DropState state;
	public LinkedList <String> ListWeapons = new LinkedList <String>();
	public Rectangle field;
	public Rectangle fire;
	public Rectangle listSelector;
	public LinkedList <Rectangle> list = new LinkedList <Rectangle>(); 
	public Rectangle selected = null;
	
	
	
	 int fframe = 0;
	 int prevFrame = 0;
	
	public  DropDownMenu(){
		field = new Rectangle(fieldX,fieldY,fieldWidth,fieldHeight);
		listSelector = new Rectangle(listSelectorX,listSelectorY,listSelectorWidth,listSelectorHeight);
		fire = new Rectangle(fireSelectorX,fireSelectorY,fireSelectorWidth,fireSelectorHeight);
		state = DropState.NOT_CLICKED;
		ListWeapons.add("Sniper");
		ListWeapons.add("Cruiser");
		ListWeapons.add("Big Shot");
		ListWeapons.add("Ground-Hog");
		
		for(int i=0;i<ListWeapons.size();i++){
			list.add(new Rectangle(fieldX,fieldY+(i*fieldHeight),fieldWidth-listSelectorWidth,fieldHeight));
		}
		
		
		
	}
	public void updateRects(){
		for(int i=0;i<ListWeapons.size();i++){
			list.get(i).setRect(new Rectangle(fieldX,fieldY+(i*fieldHeight),fieldWidth-listSelectorWidth,fieldHeight));
		}
	}
	public void render(Graphics G){
		
		Graphics2D g = (Graphics2D)G;
		g.setColor(Color.red);
		g.draw(field);
		g.draw(listSelector);
		if(ListWeapons.size() > 0)
			g.drawString(ListWeapons.get(0),fieldX+40,fieldY+14);
		int i = 0;
		//System.out.println(state);
		if(state == DropState.CLICKED){
			for(Rectangle R : list){
				g.draw(R);
				if(selected != null){
					g.setColor(new Color(255,255,145,25));
					g.fill(selected);
					g.setColor(Color.red);
				}
				if(i != fframe && i!=0)
					g.drawString(ListWeapons.get(i),fieldX+40,(fieldY+(i*fieldHeight))+14);
				//if(i == fframe)
					//g.drawString(ListWeapons.get(prevFrame),fieldX+50,(fieldY+(i*fieldHeight))+14);
				i++;
			}
		}
	}
	public void update(){
		
	}
	public DropDownMenu getDropDown(){
		return(this);
	}
	public DropState getState() {
		return state;
	}
	public LinkedList<String> getListWeapons(){
		return(this.ListWeapons);
	}
	public LinkedList<Rectangle> getListRect(){
		return(this.list);
	}
	public void setState(DropState state) {
		this.state = state;
	}
	public void addWeapon(String weaponName){
		ListWeapons.add(weaponName);
	}
	public int getSizeOfWeapon(){
		return(this.ListWeapons.size());
	}
	public void removeWeapon(String weaponName){
		ListWeapons.remove(weaponName);
	}
	public void setRect(Rectangle R){
		selected = new Rectangle(R);
	}
	public void setFFrame(int frame){
		fframe = frame;
	}
	public void setPrevFrame(int prevFrame){
		this.prevFrame = prevFrame;
	}
}
