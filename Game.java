package game.main;  //Gibran's Stuff
import java.awt.Canvas;
import java.awt.GraphicsConfiguration;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable 
{
private static final long serialVersionUID = 6722640387316353634L;
	
	public static final int WIDTH = 1080, HEIGHT = WIDTH /12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	
	
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;

	public enum STATE
	{
		Menu,
		Game,
		Help,
		HighScore,
		End
	};
	
	public static STATE gameState =  STATE.Menu;
	
	public Game() 
	{
		
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);

		
		new Window(WIDTH, HEIGHT, "My game", this); 
						
		spawner = new Spawn(handler, hud);		
		r = new Random();
		
		System.out.println(handler.object_counter()); //useless to the player but is code I could to check active objects at different points in the program
		

	}	
	
	public synchronized void start() 
	{
		thread = new Thread(this);
		thread.start();
		 running = true;
	}
	
	public synchronized void stop() 
	{
		 try 
		 {
			 thread.join();
			 running = false;
		 }
		 catch(Exception e )
		 {
			e.printStackTrace(); 
		 }
}	
	
	
	
	public void run() // This is the game Loop(Heart of FPS and such)
	{
		do
		{
			this.requestFocus(); //removes need to click screen 
	
			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0; 
			long timer =System.currentTimeMillis();
			int frames = 0;
			while(running)
			{
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				while(delta >= 1)
				{
					tick();
					delta--;
				}
				if(running)
					render();
				frames++;
				
				if(System.currentTimeMillis() - timer > 1000)
				{
					timer += 1000;
					//System.out.println("FPS :" + frames);
					frames = 0;
				}
			}
			stop();
		}while(!(gameState == STATE.End));
	}
	
	private void tick()
	{
		handler.tick();
		if (gameState == STATE.Game)
		{
			hud.tick();
			spawner.tick();	
			
			
			if(HUD.HEALTH <= 0)
			{
				HUD.HEALTH = 100;
				gameState = STATE.End;
				handler.clearEnemys();
			}
		}else if(gameState == STATE.Menu || gameState == STATE.End)
		{
			menu.tick();
		}
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		
		
		handler.render(g);
		
		if (gameState == STATE.Game)
		{
			hud.render(g); 
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End|| gameState == STATE.HighScore)
		{
			menu.render(g);
		}
		
		
		g.dispose();
		bs.show();
	}
	
	
	public static float clamp(float var, float min, float max)
	{
		if(var >= max) 
			return var = max;
		else if (var <= min)
			return var = min; 
		else
			return var;
	}
	
	public static void main(String args[])
	{
		new Game();
		
		
	}
}
