public class position{

	int x;
	int y;
	int xspeed;
	int yspeed;
	public position(int x,int y,int xspeed,int yspeed)
	{
		this.x = x;
		this.y = y;
		this.xspeed = xspeed;
		this.yspeed = yspeed;
	}
	public void set_x(int x)
	{
		this.x  = x;
	}
	public void set_y(int y)
	{
		this.y = y;
	}
	public void set_xspeed(int xspeed)
	{
		this.xspeed = xspeed;
	}
	public void set_yspeed(int yspeed)
	{
		this.yspeed = yspeed;
	}
	public int get_x()
	{
		return(this.x);
	}
	public int get_y()
	{
		return(this.y);
	}
	public int get_xspeed()
	{
		return(this.xspeed);
	}
	public int get_yspeed()
	{
		return(this.yspeed);
	}
}