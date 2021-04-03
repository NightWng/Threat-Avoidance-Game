//package game.main;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Rectangle;
//import java.util.Random;
//
//
//public class Player2 extends GameObject
//{
//	Random r = new Random();
//	Handler handler;
//
//	public Player2(int x, int y, ID id, Handler handler) 
//	{
//		super(x, y, id);
//	this.handler = handler;
//		
//	}
//
//	public void tick() 
//	{
//		x += velX;
//		y += velY;
//		
//		x = Game.clamp(x, 0, Game.WIDTH - 71);
//		y = Game.clamp(y, 0, Game.HEIGHT - 100);
//		
//		collision();
//	}
//	
//	private void collision()
//	{
//		for(int i = 0; i < handler.object.size(); i++)
//		{
//			GameObject tempObject = handler.object.get(i);
//			
//			if(tempObject.getID() == ID.BasicEnemy )
//			{
//				if(getBounds().intersects(tempObject.getBounds()))// temp object is now basic enemy 
//				{
//					//Collision code
//					HUD.HEALTH -=2;
//				}
//			}
//		}
//	}
//
//	public void render(Graphics g) 
//	{
//		Graphics2D g2d = (Graphics2D)g;
//		if(id==ID.Player2)g.setColor(Color.blue );	
//		g.fillRect((int)x, (int)y, 64, 64);
//		
//	}
//
//	public Rectangle getBounds() 
//	{
//		return  new Rectangle((int)x, (int)y,64,64 );
//	}
//}
