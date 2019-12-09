import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
public class terrain extends Canvas implements button_specifications{
	public int scaling;
	
	
	
	private Graphics2D dbg;
	private BufferedImage img = null;
	
	int Height = 640;
	int Width = 640;
	
	double height = 200;
	double width = 200;
	button_specifications bt;
	private static byte[] perm;
	int rows = bt.Height/bt.Pixel_size;
	int cols = bt.Width/bt.Pixel_size;
	
	int centerX;
	int centerY;
	
	Color green0 = new Color(111,72,21);		//69 153 36
	Color green1 = new Color(44,98,47);
	Color green2 = new Color(23,78,26);
	Color black = new Color(17,33,38);
	Color black1 = new Color(18,20,18);
	
	
	int[] rand = new int[400];
	
	public Ellipse2D eli;
	
	public Rectangle2D rec = new Rectangle2D.Double();
	public Rectangle2D tampCheck = new Rectangle2D.Double();
	
	public int cooler[][] = new int[900][900];		////
	
	public int[] y2 = new int[cols];
	public int[] temp_y = new int[cols];
	Random ser = new Random();

	double [] noise1D=new double[512];
	double [] perlin1D=new double[cols];
	
	public terrain()
	{
		int buffer,Flag_1 = -1,Flag_2 = -1;
		int grd = 1;
		int scaling = 2;
		y2[0] = 100;
		temp_y[0] = y2[0];
		double limit = Math.random();
		int grd_limit = (int)((limit-0)/(1-0) * (10-5) + 5);
		int curve = (int)((limit-0)/(1-0) * (4-2) + 2);
		double ang = 0;
		double angGrad = Math.PI/30;
		
		double amplCos = ser.nextInt(50);
		double amplSin = ser.nextInt(50);
		double noise = ser.nextDouble();
		int sinuses = ser.nextInt(10);
		int cosuses = ser.nextInt(10);
		
		for(int i=0;i<512;i++)
			noise1D[i]=Math.random();
			perlin1D=perlinNoise1D(512,noise1D,7);
		for(int i=0;i<perlin1D.length;i++)
		{
			if(i<cols)
			{
				y2[i]=(int)(perlin1D[i]*(double)(Height/2));
				temp_y[i]=y2[i];
			}
		}
		map();
		
	}
	public double [] perlinNoise1D(int count,double[] noise,int octaves)
	{
		double output[]=new double[count];
		for(int x=0;x<count;x++)
		{
			double dNoise=0;
			double fScale=1;
			double fScaleAcc=0;
			for(int oct=0;oct<octaves;oct++)
			{
				int nPitch=count>>oct;
				int nSample1=(x/nPitch)*nPitch;
				int nSample2=(nSample1+nPitch)%count;
				double fBlend=(double)(x-nSample1)/(double)nPitch;
				double fSample=(1-fBlend)*noise[nSample1]+fBlend*noise[nSample2];
				dNoise +=fSample*fScale;
				fScaleAcc +=fScale;
				fScale /=2;
			}
			output[x]=dNoise/fScaleAcc;
		}
		return(output);
	}
	public void updateArray(int row1,int col1,int row2,int col2){
		for(int i = col1;i<col2;i++){
			int flagFirstOccurance = 0;
			for(int j=0;j<bt.rows;j++){
				if(cooler[j][i] >= 0 && flagFirstOccurance == 0){
					temp_y[i] = (j);
					flagFirstOccurance = 1;
				}
				
			}
		}
	}
	public void gradual_decrease(int i)				////////////////////////////////	
	{
		double limit = Math.random();
		int c = (int)((limit-0)/(1-0) * (1) + 0);
		
		if(c == 0)
		{
			y2[i] = y2[i-1]; 							/////////////////////////////
		}
		else{
			y2[i] = y2[i-1]-1;							//////////////////////////////
		}
	
	}
	public void render(Graphics G)
	{
		if(img != null)
		{
			 G.drawImage(img, 0, 0,null);
		}
		else
		{
			System.out.println("\n Pointer is here");
		}
		
		
		
	}
	public void render(int row1,int col1,int row2,int col2)
	{
		dbg.setColor(green0);
		for(int i=col1;i<col2;i++)
		{
			
			for(int j = row1;j<row2;j++)
			{
				rec.setRect((double)((i*bt.Pixel_size)),(double)((j*bt.Pixel_size)),(double)bt.Pixel_size,(double)bt.Pixel_size);
				if(cooler[j][i]==-1){
					dbg.setColor(black);
					dbg.fill(rec);
					dbg.setColor(green0);
				}
				else if(cooler[j][i] == 0)
				{                                                                                                          
					dbg.fill(rec);
				}
				else if(cooler[j][i] == 1)
				{
					dbg.setColor(green1);
					dbg.fill(rec);
					dbg.setColor(green0);
				}
				else if(cooler[j][i] == 2)
				{
					dbg.setColor(black1);
					dbg.fill(rec);
					dbg.setColor(green0);
				}
		
			}
		}
	}
	public void map()
	{
		for(int i=0;i<bt.cols;i++)
		{
			double limit = Math.random();
			int grd_limit = 50;
			for(int j=0;j<bt.rows;j++)
			{
				if(y2[i]<=0)
				{
					if(grd_limit < 0)
						cooler[j][i] = 0;
					else if(grd_limit == 0)
						cooler[j][i] = 2;
					else
						cooler[j][i] = 1;
					grd_limit--;
				}
				else
				{
					cooler[j][i] = -1;
				}
				y2[i]--;
				
			}
		}
	}
	public void move_x(int row1,int col1,int row,int col,Ellipse2D eli1)
	{
		/*******this is so litttttt!!!!!!!!!!!!!!!!**********/////
		
		for(int i=col1;i<col;i++)////10			x
		{
			for(int j=row1;j<row;j++)///150		y
			{
				rec.setRect((double)((i*bt.Pixel_size)),(double)((j*bt.Pixel_size)),(double)bt.Pixel_size,(double)bt.Pixel_size);
				if(eli1!=null)
				{
					if(eli1.contains(rec)){
						if(cooler[j][i] >= 0){

								//System.out.print("	Here@@@@	");
									cooler[j][i] = -1;
						}
					}
				}
				
			}
		}
	}						// x1	   //y1		//x2    //y2
	public boolean tick_x(int row1,int col1,int row,int col)//boolean cooler[][])
	{
		int mark= 0;
		for(int i=col1;i<col;i++)					
		{
			for(int j=row;j>0;j--)				//row1 row ++ 
			{
				if(cooler[j][i] == -1 && cooler[j-1][i] >= 0)
				{
					cooler[j][i] = cooler[j-1][i];//				**Working!!**
					cooler[j-1][i] = -1;
					mark = 1;
					//break;
					//swap(i,j);
					//break;
				}
			}
		}
		if(mark == 1)
			return(true);
		else
			return(false);
	}
	public void swap(int i,int j)
	{
		int k;
		for(k=j+1;k<y2[i];k--)			//change if not working!!!
		{
			cooler[k][i] = cooler[k-1][i];
			cooler[k-1][i] = -1;
		}
	}
	public void load()
	{
		System.out.println("\n Here!!");
		if(img == null){
				img = null;
						 img = new BufferedImage( bt.Width, bt.Height, BufferedImage.TYPE_INT_ARGB );
	
				//else
				{
					dbg = img.createGraphics();
					System.out.println("\n Here!!");
				}
		}
		dbg.setColor(Color.WHITE);
		dbg.fillRect(0,0,bt.Width,bt.Height);
		
		dbg.setColor(green0);
		for(int i=0;i<bt.cols;i++)
		{
			
			for(int j = 0;j<bt.rows;j++)
			{
				rec.setRect((double)((i*bt.Pixel_size)),(double)((j*bt.Pixel_size)),(double)bt.Pixel_size,(double)bt.Pixel_size);
				if(cooler[j][i]==-1){
					dbg.setColor(black);
					dbg.fill(rec);
					dbg.setColor(green0);
				}
				else if(cooler[j][i] == 0)
				{                                                                                                          
					dbg.fill(rec);
				}	
				else if(cooler[j][i] == 1)
				{
					dbg.setColor(green1);
					dbg.fill(rec);
					dbg.setColor(green0);
				}
				else if(cooler[j][i] == 2)
				{
					dbg.setColor(black1);
					dbg.fill(rec);
					dbg.setColor(green0);
				}
			}
		}
		
	}
	public double mapTo(double max,double min,double upLimit,double downLimit,double value){
		double map;
		if(value < max)
			 map = ((value - min)/ (max-min)*(upLimit-downLimit)) + downLimit;
		else
			map = upLimit;
			
		return map;
	}
	
	public void tick_x(int row,int col)//boolean cooler[][])
	{
		for(int i=0;i<cols;i++)//change if not working
		{
			for(int j=0;j<rows;j++)
			{
				if(cooler[j+1][i] == -1 && cooler[j][i] == 0)
				{
					cooler[j+1][i] = 0;
					cooler[j][i] = -1;
					break;
				}
			}
		}
	}
	 
}
