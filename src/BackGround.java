import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;




public class BackGround  extends Canvas implements Runnable
{


	public void init()
	{
			
	}
	
	
	public void paint(Graphics g)
	{
		this.global=g.create();
		this.Run=new Thread(this);
		Run.start();
	}
	
	public void Draw(Graphics g)
	{
		this.DrawGrid(g);
		this.DrawSanke(g);
	}
	
	public void DrawGrid(Graphics g)
	{
		g.drawRect(100, 0, this.Grid_Width*this.BOX_Width, this.Grid_Height*this.BOX_Height);
		
		for(int x=100+this.BOX_Width;x<=100+this.Grid_Width*this.BOX_Width;x=x+this.BOX_Width)
		{
			g.drawLine(x, 0, x, this.Grid_Height*this.BOX_Height);
		}
		
		for(int y=this.BOX_Height;y<=this.Grid_Height*this.BOX_Width;y=y+this.BOX_Height)
		{
			g.drawLine(100, y, 100+this.Grid_Width*this.BOX_Height, y);
		}
	}
	
	public void DrawSanke(Graphics g)
	{
		g.setColor(Color.green);
	}
	
	private LinkedList<Point> snake;
	private Thread Run;
	//the map parameter
	private final int BOX_Height=15;
	private final int BOX_Width=15;
	private final int Grid_Width=30;
	private final int Grid_Height=30;
	private Graphics global;
	
	
	public void run() {
		while(true)
		{
			this.Draw(global);
			
			try
			{
				Thread.currentThread();
				Thread.sleep(100);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
	}
	
}
