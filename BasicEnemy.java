package game.main; //Gibran's Stuff

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject 
{
	private Handler handler;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) 
	{
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds() 
	{
		return  new Rectangle((int)x, (int)y,32,32 );
	}
	
	public void tick() 
	{
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT-64) velY *=-1;
		if(x <= 0 ||  x >= Game.WIDTH-32) velX *=-1;
		 
	}

	public void render(Graphics g) 
	{

		g.setColor(Color.red);
		g.fillRect((int)x,(int)y, 32,32);
	}

}
