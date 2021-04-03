package game.main; //Gibran's Stuff

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class HUD 
{
	public static int HEALTH = 100;
	private int greenValue = 255;
	
	private int score = 0;
	private int level = 1;
	private int highScore = 0;
	
	public void tick()
	{		
		HEALTH = (int) Game.clamp(HEALTH, 0, 100);
		greenValue = (int) Game.clamp(greenValue,0, 255);
		
		greenValue = HEALTH *2;
		
		score ++;
		if(score > highScore)
		{
			highScore = score;
		}
	}
	
	public void render (Graphics g)
	{
		Font openSans = new Font("Open Sans", 1,30);


		
		g.setColor(Color.red.darker());
		g.fillRect(30, 30, 400, 64);
		g.setColor(new Color(75,greenValue,0).brighter());
		g.fillRect(30, 30, HEALTH *4, 64);	
		g.setColor(Color.green.darker());
		g.drawRect(30, 30, HEALTH *4, 64);
		
		g.setFont(openSans);
		g.setColor(Color.green);
		g.drawString("Score: " + score, 30, 130);
		g.drawString("High Score: " + highScore, 800, 130);
		g.drawString("Level : " + level, 500, 720);

	}
	
	public void score(int score)
	{
		this.score = score;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getHighScore()
	{
		return highScore;
	}

	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}	

}
