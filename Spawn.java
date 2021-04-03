package game.main;

import java.util.*;

public class Spawn 
{
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scorekeep = 0;
	
	public Spawn(Handler handler, HUD hud)
	{
		this.handler = handler;
		this.hud = hud;
		
	}
	
	public void tick()
	{
		scorekeep ++;
		if(scorekeep >= 1000)
		{
			scorekeep = 0;
			hud.setLevel(hud.getLevel()+1);
			
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-100),r.nextInt(Game.HEIGHT-100),ID.BasicEnemy, handler));			
		}
			
	}
	
	public void setScorekeeper(int scorekeep)
	{
		this.scorekeep = scorekeep;
	}
}
