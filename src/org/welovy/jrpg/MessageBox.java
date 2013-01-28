package org.welovy.jrpg;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;


class MessageText {
	String message;
	private static int counter = 0;
	private static int max;
	private int fpsCounter; 
	private static double delay = 0.5;
	MessageText (String msg) {
		message = msg;
		max = message.length();
		fpsCounter = 1;
	}
	
	public String getDrawText(Sound typeSound) {
		if (counter >= max) {
			return message;
		} else {
			fpsCounter++;
			if (typeSound.playing() == false) {
				typeSound.play(1.4f, 0.4f);
			}
			
			if (fpsCounter % 5 == 0) {
				fpsCounter = 0;
				counter++;
				return message.substring(0, counter);
			} else {
				return message.substring(0, counter);
			}
		}
	}
}

public class MessageBox {
	private MessageText presentmsg;
	List<MessageText> msgQueue;
	private static final int pos_x = 45;
	private static final int pos_y = 300;
	private static final int BOX_WIDTH = 550;
	private static final int BOX_HEIGHT = 150; 
	private static Sound typeSound = null;
	private boolean display = false;
	static {
		try {
			typeSound = new Sound("sound/typing.wav");
		} catch (SlickException ignore) { 
			ignore.printStackTrace();
			
		}
	}
	
	MessageBox() {
		msgQueue = new ArrayList<MessageText>();
	}
	public void enableDisplay() {
		this.display = true;
	}
	public void disableDisplay() {
		this.display = false;
	}
	public void render(GameContainer container, Graphics g) {
		if (display) {
			Rectangle box = new Rectangle(pos_x, pos_y, BOX_WIDTH, BOX_HEIGHT);
			g.setColor(Color.black);
			g.fill(box);
			g.setColor(Color.white);
			g.draw(box);
			String drawtext = presentmsg.getDrawText(typeSound);
			g.drawString(drawtext, 60, 320);
		}
	}
	
	public void addMsg(String msg) {
		presentmsg = new MessageText(msg); 
		msgQueue.add(presentmsg);
		// TODO:
		
	}
	
	public void update(GameContainer container, int delta)  throws SlickException {

	}
}
