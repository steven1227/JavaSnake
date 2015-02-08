import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;




public class BackGround  extends Canvas implements Runnable,KeyListener
{

	private LinkedList<Point> snake;
	//the fruit var
	private Point fruit;
	
	private Thread Run;
	//the map parameter
	private final int BOX_Height=15;
	private final int BOX_Width=15;
	private final int Grid_Width=30;
	private final int Grid_Height=30;
	private Graphics global;
	
	public void init()
	{
			
	}
	
	
	public void paint(Graphics g)
	{
		this.fruit = new Point(10,10);
		this.global=g.create();
		this.Run=new Thread(this);
		Run.start();
	}
	
	public void Draw(Graphics g)
	{
		g.clearRect(100, 0, this.Grid_Width*this.BOX_Width, this.Grid_Height*this.BOX_Height);
		this.DrawGrid(g);
		//this.DrawSanke(g);
		this.DrawFruit(g);
	}
	
	public void DrawGrid(Graphics g)
	{
		g.setColor(Color.BLACK);
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
	
	public void DrawFruit(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(this.fruit.x*BOX_Width+100,this.fruit.y*BOX_Height,BOX_Width,BOX_Height);
		
	}
	
	public void PlaceFruit(){
		Random rand = new Random();
		int randomX = rand.nextInt(this.Grid_Width);
		int randomY = rand.nextInt(this.Grid_Height);
		Point randomPoint = new Point(randomX,randomY);
		while (snake.contains(randomPoint)){
			randomX= rand.nextInt(this.Grid_Width);
			randomY = rand.nextInt(this.Grid_Height);
			randomPoint = new Point(randomX,randomY);
		}
		
		fruit = randomPoint;
	}
	
	public void Move() {
		Point head = snake.peekFirst();
		Point newPoint = head;
		
		
		//check if the point is fruit
		if (newPoint.equals(fruit)){
			// snake add head action 
			
			PlaceFruit();
			
			
		}
		
	}
	
	public void run() {
		while(true)
		{
			this.Draw(global);
			//this.Move();
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
