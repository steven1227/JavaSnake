import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
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
	private final int BOX_Height=20;
	private final int BOX_Width=20;
	private final int Grid_Width=30;
	private final int Grid_Height=30;
	private int direct=direction.No_direction;
	private Graphics global;
	private int score=0;
	
	public void init()
	{
			
	}
	
	
	public void paint(Graphics g)
	{
		
		System.out.print("fuck\n");
		
		this.fruit = new Point(10,10);
		
		snake = new LinkedList<Point>();
		
		
		snake.add(new Point(3,3));
		snake.add(new Point(3,2));
		snake.add(new Point(3,1));
		
		
		this.global=g.create();			//capture the graphics that we can use
		
		this.addKeyListener(this);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN,18)); 
		String s="Score:";
		g.drawString(s, 800, 300);
		
		if(this.Run==null)
		{
			this.Run=new Thread(this,"draw");
			Run.start();
		}
	
	}
	
	public void Draw(Graphics g)
	{
		
		g.clearRect(100, 0, this.Grid_Width*this.BOX_Width, this.Grid_Height*this.BOX_Height);
	
		this.DrawGrid(g);
		this.DrawSanke(g);
		this.DrawFruit(g);
		this.DrawScore(g);
	}
	
	public void DrawGrid(Graphics g)
	{
		g.setColor(Color.GRAY);
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
	
	public void DrawScore(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.clearRect(850, 250, 100, 100);
		g.setFont(new Font("TimesRoman", Font.PLAIN,18)); 
		g.drawString(""+this.score, 860, 300);
	}
	
	public void move()
	{
		Point head = snake.peekFirst();
		Point tail = snake.peekLast();
		Point newPoint = head;
		
		switch (this.direct)
		{
		case direction.east:
			newPoint =new Point(head.x+1,head.y);
			break;
		case direction.west:
			newPoint =new Point(head.x-1,head.y);
			break;
		case direction.south:
			newPoint =new Point(head.x,head.y+1);
			break;
		case direction.north:	
			newPoint =new Point(head.x,head.y-1);
			break;
			
		}
		if(this.direct!=direction.No_direction)
		snake.remove(snake.peekLast());
		
		//check if the point is fruit
		if (newPoint.equals(fruit)){
			// snake add head action 
			snake.add(tail);
			PlaceFruit();
			this.score=this.score+10;
			
		}
		else if (newPoint.x<0||newPoint.x>=this.Grid_Width||newPoint.y>=this.Grid_Height||newPoint.y<0)
		{
			try {
				Thread.currentThread().wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if (snake.contains(newPoint))
		{
		
		}
		if(this.direct!=direction.No_direction)	
			snake.push(newPoint);
	}
	
	
	public void DrawSanke(Graphics g)
	{
		
		for(int i=0;i<snake.size();i++)
		{
			if(i==0)
			{
				g.setColor(Color.yellow);
			}
			else{
				g.setColor(Color.green);
			}
			g.fillRect(100+snake.get(i).x * BOX_Width, snake.get(i).y * BOX_Height, BOX_Width, BOX_Height);
			
		}
//		for (Point p : snake)
//		{
//			g.fillRect(100+p.x * BOX_Width, p.y * BOX_Height, BOX_Width, BOX_Height);
//		}
//		g.setColor(Color.white);
//		g.setColor(Color.GRAY);
	}
	
	

	public void DrawFruit(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(this.fruit.x*BOX_Width+100,this.fruit.y*BOX_Height,BOX_Width,BOX_Height);
		g.setColor(Color.white);
		g.setColor(Color.GRAY);
		
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

	
	
	public void run() {
		while(true)
		{		
			
			this.move();
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
