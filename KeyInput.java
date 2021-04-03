package game.main; //Gibran's Stuff

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter 
{
	private Handler handler;
	private boolean[] keyDown = new boolean [8];
	
	public KeyInput (Handler handler)
	{
		this.handler = handler;
		
		keyDown[0] = false;//W key
		keyDown[1] = false;//S key
		keyDown[2] = false;//D key
		keyDown[3] = false;//A key
		keyDown[4] = false;//UP key
		keyDown[5] = false;//DOWN key
		keyDown[6] = false;//RIGHT key
		keyDown[7] = false;//LEFT key
		
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player)
			{
				//key events for player one
				if(key == KeyEvent.VK_W) { tempObject.setVelY(-5); keyDown[0]=true;}
				if(key == KeyEvent.VK_S) { tempObject.setVelY(5);keyDown[1]=true;}
				if(key == KeyEvent.VK_D) { tempObject.setVelX(5);keyDown[2]=true;}
				if(key == KeyEvent.VK_A) { tempObject.setVelX(-5);keyDown[3]=true;}
			}
			if(tempObject.getID() == ID.Player2)
			{
				//key events for player two
				if(key == KeyEvent.VK_UP) { tempObject.setVelY(-5); keyDown[4] = true;}
				if(key == KeyEvent.VK_DOWN) { tempObject.setVelY(5);keyDown[5] = true;}
				if(key == KeyEvent.VK_RIGHT) { tempObject.setVelX(5);keyDown[6] = true;}
				if(key == KeyEvent.VK_LEFT) { tempObject.setVelX(-5);keyDown[7] = true;}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();		

		for(int i = 0; i < handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
			 
			if(tempObject.getID() == ID.Player)
			{
				//key events for player one
				if(key == KeyEvent.VK_W) keyDown[0]=false;
				if(key == KeyEvent.VK_S) keyDown[1]=false;
				if(key == KeyEvent.VK_D) keyDown[2]=false;
				if(key == KeyEvent.VK_A) keyDown[3]=false;
		

				
				//vertical  movement 
				
				if(!keyDown[0] && !keyDown[1]) {tempObject.setVelY(0);}
				
				
				//horizontal movement
				
				if(!keyDown[2] && !keyDown[3]) {tempObject.setVelX(0);}
					
			}
			
			if(tempObject.getID() == ID.Player2)
			{
				//key events for player two
				if(key == KeyEvent.VK_UP) keyDown[4] = false;
				if(key == KeyEvent.VK_DOWN) keyDown[5] = false;
				if(key == KeyEvent.VK_RIGHT) keyDown[6] = false;
				if(key == KeyEvent.VK_LEFT) keyDown[7] = false;
				
				
				//This is to fix sticky key glitch along with the keyDown[] array
				if(!keyDown[4] && !keyDown[5]) {tempObject.setVelY(0);}
				if(!keyDown[6] && !keyDown[7]) {tempObject.setVelX(0);}
			}
		}
	}
}
