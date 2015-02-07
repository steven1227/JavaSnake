import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;




public class BackGround  extends Canvas implements Runnable,KeyListener
{


	public void init()
	{
			
	}
	
	
	public void paint(Graphics g)
	{
		this.global=g.create();
		this.addKeyListener(this);
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
	
	
	public void move()
	{
		switch (this.direct)
		{
		case direction.east:
			System.out.println("right");
			break;
		case direction.west:
			System.out.println("left");
			break;
		case direction.south:
			System.out.println("down");
			break;
		case direction.north:	
			System.out.println("up");
			break;
	
		
		
		
		
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
	private int direct=direction.No_direction;
	
	
	public void run() {
		while(true)
		{
			this.Draw(global);
			this.move();
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


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
	//	System.out.println("right");
		if (arg0.getKeyCode()==KeyEvent.VK_UP){
			this.direct=direction.north;
			//System.out.println(this.direct);
		}
		else if (arg0.getKeyCode()==KeyEvent.VK_DOWN)
		{
			this.direct=direction.south;
		//	System.out.println(this.direct);
		}
		else if (arg0.getKeyCode()==KeyEvent.VK_RIGHT)
		{
				this.direct=direction.east;
				//System.out.println(this.direct);
		}
		else
		{
			this.direct =direction.west;
		//	System.out.println(this.direct);
		}
					
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
