package game.main; //Gibran's Stuff

import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Handler 
{
	LinkedList <GameObject> object = new LinkedList<GameObject>();
	 public void tick() 
	 {
		 for(int i = 0; i < object.size(); i++) 
		 {
			 GameObject tempObject = object.get(i);
			 
			 tempObject.tick();
		 }
	 }
	 
	 public void render(Graphics g)
	 {
		 for(int i = 0; i < object.size(); i++) 
		 {
			 GameObject tempObject = object.get(i);
			 
			 tempObject.render(g);
		 }
		 
	 }
	 
	 public void clearEnemys()
	 {
		 for(int i = 0; i < object.size(); i++) 
		 {
			 GameObject tempObject = object.get(i);
			 
			 if (tempObject.getID() != ID.Player) 
			 {
	                this.removeObject(tempObject);
	                i--;
			 }
		 }
		 
		 

		 for(int i = 0; i < object.size(); i++) 
		 {
			 GameObject tempObject = object.get(i);
			 
			 if((tempObject.getID() == ID.Player) && Game.gameState == Game.STATE.End)
			 {
				 this.removeObject(tempObject);
			 }
		 }
	 
	 } 
	 public void addObject(GameObject object)
	 {
		 this.object.add(object);
	 }
	 
	 public void removeObject(GameObject object)
	 {
		 this.object.remove(object);
	 }
	 
	 public int object_counter()
	 {
		 LinkedList<GameObject> obj  = object;
		 int counter = 0; //size
		 ListIterator<GameObject> iter = object.listIterator();
		 
		 while (iter.hasNext())
		 {
			 iter.next();
			 counter++;
		 }
		 
		 while(iter.hasPrevious()) //resets the iterator
		 {
			 iter.previous();
		 }
		 
		 return counter;
	 }
	 
	 public void play()
		{
			try
			{
				File music = new File("C:\\Users\\Gibran Akmal\\workspace\\Game\\src\\game\\music\\tune.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(music));
				clip.start();
			}
			catch(Exception e)
			{
				e.printStackTrace(System.out);
			}
		}

}
