import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;


public class SnakeApplet extends Applet
{

	public void init()
	{
		c=new BackGround();
		c.setPreferredSize(new Dimension(640,480));
		c.setVisible(true);
		c.setFocusable(true);
		this.add(c);
		this.setVisible(true);
		this.setSize(new Dimension(640,480));

				
	}

	
	
	private BackGround c;
}
