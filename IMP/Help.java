import java.awt.*;
//import java.awt.font.*;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.text.*;
//import java.text.AttributedString;
import java.util.*;
import javax.imageio.*;


public class Help implements button_specifications{
	Rectangle backward=new Rectangle(50,(600-200),100,50);
	Rectangle forward=new Rectangle(800-150,(600-200),100,50);
	BufferedImage showImage;
	String fileDisplay="My name is\n\t Digvijay Wadkar";
	FileReader helpFile;
	String title="one";
	//BufferedReader Reader;
	RandomAccessFile reader;
	Font f = new Font("Ariel",Font.PLAIN,16);
	PageState pageState=PageState.Page1;
	Hashtable<TextAttribute,Object>map=new Hashtable<TextAttribute,Object>();
	private AttributedString vanGogh=new AttributedString(fileDisplay,map);
	int init=0;
	LineBreakMeasurer lineMeasurer;
	int paragraphStart;
	int paragraphEnd;
	Color c=new Color(0,0,0,20);
	public Help()
	{
		try{
			showImage=ImageIO.read(new File("red_button04.png"));
			//f=Font.createFont(Font.TRUETYPE_FONT,new FileInputStream(new File("prototype.ttf"))).deriveFont(Font.PLAIN,20);
			//map=(Hashtable<TextAttribute,Object>)f.getAttributes();
			map.put(TextAttribute.FONT,f);
			map.put(TextAttribute.SIZE,new Float(16.0));
			helpFile=new FileReader("Help.txt");
			//reader=new BufferedReader(helpFile);
			//reader.markSupported();
			reader=new RandomAccessFile("Help.txt","r");
		}catch(Exception e){
			e.printStackTrace();
		}
		readFromFile();	
		System.out.println("\n S1 :  "+fileDisplay);
	}
	public void readFromFile()
	{
		String prevFileDisplay = "";
		fileDisplay="";
		int raiseFlag=0;
		int flag=getFlagFromState();
		try
		{	
			reader.seek(0);
			String s1=null;
			while((s1=reader.readLine())!=null)	
			{
				
				if((s1.compareTo(one)==0||raiseFlag==1)&& flag==1){
					System.out.println("TRUE ANGLE  "+s1);	
					raiseFlag=1;
					if(checkOthers(s1,two,three,four,five))
						raiseFlag=0;
					else
						fileDisplay=fileDisplay.concat(s1);
						title="Angle";
					
				}else if((s1.compareTo(two)==0||raiseFlag==1)&& flag==2){
				       raiseFlag=1;
					if(checkOthers(s1,one,three,four,five))
						raiseFlag=0;
					else
						fileDisplay=fileDisplay.concat(s1);
						title="Power";	
					
				}else if((s1.compareTo(three)==0||raiseFlag==1)&& flag==3){
				       raiseFlag=1;
					if(checkOthers(s1,two,one,four,five))
						raiseFlag=0;
					else
						fileDisplay=fileDisplay.concat(s1);
					title="Weapons";	
				}else if((s1.compareTo(four)==0||raiseFlag==1)&& flag==4){
				       raiseFlag=1;
					if(checkOthers(s1,two,three,one,four))
						raiseFlag=0;
					else
						fileDisplay=fileDisplay.concat(s1);
						title="Fire";	
				}else if((s1.compareTo(five)==0||raiseFlag==1)&& flag==5){
				       raiseFlag=1;
					if(checkOthers(s1,two,three,four,one))
						raiseFlag=0;
					else
						fileDisplay=fileDisplay.concat(s1);
							
				}
			}
		}catch(Exception e){e.printStackTrace();}
		System.out.println("\n File display : "+fileDisplay);
		if(fileDisplay.length()==0)
			vanGogh=new AttributedString(prevFileDisplay,map);	
		else
			vanGogh=new AttributedString(fileDisplay,map);

		lineMeasurer=null;
	}
	public boolean checkOthers(String checkString,String tok1,String tok2,String tok3,String tok4)
	{
		if(checkString.compareTo(tok1)==0 || checkString.compareTo(tok2)==0 || checkString.compareTo(tok3)==0 || checkString.compareTo(tok4)==0)
		{
			return true;
		}
		return false;
	}
	public void setStringAttribute(){
		vanGogh=new AttributedString(fileDisplay);
		vanGogh.addAttribute(TextAttribute.FONT,f);
		vanGogh.addAttribute(TextAttribute.FOREGROUND,Color.black);
	}
	public void render(Graphics G)
	{
		Graphics2D g=(Graphics2D)G;
		G.setFont(f);
		g.setColor(Color.WHITE);
		//g.draw(new Rectangle(100,50,100,100));
		g.drawImage(showImage,100,50,100,100,null);
		g.drawString(title,120,100);
		g.drawString("Next",800-150+30,(600-200)+30);
		g.drawString("Previous",50+30,(600-200)+30);
		//g.drawString(fileDisplay,100,200);
		if(init==-1){
				//setStringAttribute();
		init=0;
		}
		g.setColor(Color.WHITE);
		g.draw(forward);
		g.draw(backward);
		renderText(g);
		g.setColor(c);
		g.fill(new Rectangle(0,0,800,600));
	}
	public void renderText(Graphics2D G)
	{
		G.setColor(Color.WHITE);
		if(lineMeasurer==null){
			AttributedCharacterIterator paragraph=vanGogh.getIterator();
			paragraphStart = paragraph.getBeginIndex();
			paragraphEnd= paragraph.getEndIndex();
			FontRenderContext frc = G.getFontRenderContext();
			lineMeasurer=new LineBreakMeasurer(paragraph,frc);
		}
		//set break width to width of Component.
		float breakWidth=(float)600;
		float drawPosY=200;
		//set position to the index of the first character in the paragraph.
		lineMeasurer.setPosition(paragraphStart);
		//get lines until the entire paragraph has been Displayed.
		while(lineMeasurer.getPosition()< paragraphEnd){
				TextLayout layout=lineMeasurer.nextLayout(breakWidth);
			
				float drawPosX=layout.isLeftToRight()? 100 : breakWidth-layout.getAdvance();
			
				//move the Y co-ordinates by the ascent of th layout.
				drawPosY +=layout.getAscent(); 
		
				//draw the Textlayout at (drawPosX,drawPosY);	
				layout.draw(G,drawPosX,drawPosY);
				//move y co-ordinates in preparation for next layout.
				drawPosY +=layout.getDescent()+layout.getLeading();
			}
	}
	public void update()
	{
		
	}
	public int getFlagFromState(){
		switch(pageState){
				case Page1: return(1);
				case Page2: return(2);
				case Page3: return(3);		
				case Page4: return(4);
				case Page5: return(5);
				default: return(-1);
			}
		}
	public void stateChange(int check){

			if(check==1){
				switch(pageState){
					case Page1: pageState=PageState.Page2;
							break;
					case Page2: pageState=PageState.Page3;
							break;		
					case Page3: pageState=PageState.Page4;
							break;
					case Page4: pageState=PageState.Page5;
							break;
					case Page5: pageState=PageState.Page1;
							break;
					}
				}else{
					switch(pageState){
					case Page1: pageState=PageState.Page5;
							break;
					case Page2: pageState=PageState.Page1;
							break;		
					case Page3: pageState=PageState.Page2;
							break;
					case Page4: pageState=PageState.Page3;
							break;
					case Page5: pageState=PageState.Page4;
							break;
					}
					
				}
		
			readFromFile();
			
	}
}
						
		
	





