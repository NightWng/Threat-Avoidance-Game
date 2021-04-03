package game.main; //Gibran's Stuff

import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import game.main.Game.STATE;

public class Menu extends MouseAdapter 
{
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	 String name;
	 boolean windowActive;
	
	public Menu(Game game, Handler handler, HUD hud)
	{
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu)
		{
			//Play Button
			if(mouseOver(mx,my,350,250,400,80))
			{
				game.gameState = STATE.Game;
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.BasicEnemy, handler));
				hud.setScore(0);
				hud.setLevel(1);
				
			
				
				
					handler.play();
					handler.addObject(new Player(Game.WIDTH/2+64,Game.HEIGHT/2 -32, ID.Player2,handler));
					handler.addObject(new Player(Game.WIDTH/2+64,Game.HEIGHT/2 +64, ID.Player,handler));

					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.BasicEnemy, handler));
				
			}
		
			//help Button
			if(mouseOver(mx,my,350, 350,400, 80))
			{
				game.gameState = STATE.Help;
			}
			
			//Quit Button
			if(mouseOver(mx,my,350, 550,400, 80))
			{
				System.exit(1);
			}
			
			//High Score  Button
			if(mouseOver(mx,my,350, 450,400, 80))
			{
				game.gameState = STATE.HighScore;
			}
			
		}
		
		
		
		if(game.gameState == STATE.Help || game.gameState == STATE.HighScore)
		{
			//back Button for Help 
			if(mouseOver(mx,my,350, 650,400, 80))
			{
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		if(game.gameState == STATE.End)
		{
		//Try Again Button
				if(mouseOver(mx,my,350, 650,400, 80))
				{
					game.gameState = STATE.Game;
					handler.addObject(new Player(Game.WIDTH/2+64,Game.HEIGHT/2 -32, ID.Player2,handler));
					handler.addObject(new Player(Game.WIDTH/2+64,Game.HEIGHT/2 +64, ID.Player,handler));
					handler.clearEnemys();
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.BasicEnemy, handler));
					hud.setScore(0);
					hud.setLevel(1);
				}
				
				// exit button
				if(mouseOver(mx,my,350, 550,400, 80))
				{
					game.gameState = STATE.Menu;
					return;
				}
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		
	}
	
	private boolean mouseOver(int mx, int my,int x, int y, int width, int height)
	{
		if(mx > x && mx < x + width)
		{
			if (my > y && my < y +height)
			{
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick()
	{

	}

	
	public void render(Graphics g)
	{

		Font openSans = new Font("Open Sans", 1,100);
		Font openSansSmall = new Font("Open Sans", 1,50);
		Font openSansTiny = new Font("Open Sans", 1,20);
		
		if(game.gameState == STATE.Menu)
		{
			g.setFont(openSans);
			g.setColor(Color.green.darker().darker());
			g.drawString("Game", 400, 150);
			
			g.setColor(Color.green);
			g.drawString("Game", 402, 152);
			
			g.setFont(openSansSmall);
			g.drawRect(350, 250,400, 80);
			g.drawString("Play", 500, 305);		
			
			g.drawRect(350, 350,400, 80);
			g.drawString("Help", 500, 405);
			
			g.drawRect(350, 550,400, 80);
			g.drawString("Quit", 500, 605);
			
			g.drawRect(350, 450,400, 80);
			g.drawString("High Score", 420, 505);
			
		}else if (game.gameState == STATE.Help)
		{
			
			g.setFont(openSans);
			g.setColor(Color.green);
			g.drawString("Help", 400, 150);
			
			g.setFont(openSansTiny);
			g.setColor(Color.WHITE);
			g.drawString("Use WASD key to move around and doge enemies", 300, 300);
			
			g.setFont(openSansSmall);
			g.drawRect(350, 650,400, 80);
			g.drawString("Back", 490, 705);
			
		}else if (game.gameState == STATE.HighScore)
		{
			
			g.setFont(openSans);
			g.setColor(Color.green);
			g.drawString("HighScore", 270, 150);
			
			g.setFont(openSansTiny);
			g.drawRect(145, 200,800, 400);
			
			g.drawString("Name : john Doe" , 200, 300);
			g.drawString("Score :" + hud.getHighScore() , 700, 300);
			
			g.setFont(openSansSmall);
			g.drawRect(350, 650,400, 80);
			g.drawString("Back", 490, 705);
			
		}
		else if (game.gameState == STATE.End)
		{
			
			g.setFont(openSans);
			g.setColor(Color.green);
			g.drawString("Game Over", 250, 150);
			
			g.setFont(openSansSmall);
			g.setColor(Color.green);
			g.drawString("You Lost with a score of: " + hud.getScore(), 190, 400);
			
			g.setFont(openSansSmall);
			g.drawRect(350, 650,400, 80);
			g.drawString("Try Again", 430, 705);
			
			g.setFont(openSansSmall);
			g.drawRect(350, 550,400, 80);
			g.drawString("Exit", 500, 610);
		
		}
	}
	
}
