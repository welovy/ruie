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
	private String message;
	private int counter = 0;
	private int max = 0;
	private int fpsCounter = 0; 
	private static final double delay = 0.5;
	private boolean waiting;
	
	MessageText (String msg) {
		message = msg;
		max = message.length();
		fpsCounter = 1;
		waiting = false;
	}
	
	public String getDrawText(Sound typeSound) {
		D.d("msg=" + message + ",counter=" + counter);
		if (counter >= max) {
			waiting = true;
			return message;
		} else {
			waiting = false;
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
	
	public boolean isWaiting() { return waiting;}
}

public class MessageBox {
	private MessageText currentMessage;
	private List<MessageText> msgQueue;
	private int currentMessageIndex = -1;
	private static final int pos_x = 45;
	private static final int pos_y = 300;
	private static final int BOX_WIDTH = 550;
	private static final int BOX_HEIGHT = 150; 
	private static Sound typeSound = null;
	private boolean display = false;
	private boolean waiting;

	static {
		try {
			typeSound = new Sound("sound/typing.wav");
		} catch (SlickException ignore) { 
			ignore.printStackTrace();
			
		}
	}
	
	MessageBox() {
		msgQueue = new ArrayList<MessageText>();
		currentMessageIndex = 0;
	}
	
	public void enableDisplay() {
		this.display = true;
		D.d("msgqueue:" + msgQueue.toString());
		currentMessage = msgQueue.get(currentMessageIndex++);
	}

	public void disableDisplay() {
		this.display = false;
		msgQueue.clear();
		currentMessageIndex = 0;
	}
	
	public void render(GameContainer container, Graphics g) {
		if (display) {
			Rectangle box = new Rectangle(pos_x, pos_y, BOX_WIDTH, BOX_HEIGHT);
			g.setColor(Color.black);
			g.fill(box);
			g.setColor(Color.white);
			g.draw(box);
			String drawtext = currentMessage.getDrawText(typeSound);
			g.drawString(drawtext, 60, 320);
			waiting = currentMessage.isWaiting();
		}
	}
	
	public void addMsg(String msg) {
		msgQueue.add(new MessageText(msg));
	}
	
	public void update(GameContainer container, int delta)  throws SlickException {

	}

	public void moveNextMsg() {
		try {
			currentMessage = msgQueue.get(currentMessageIndex++);
		} catch (IndexOutOfBoundsException e) {
			// which means it ends;
			disableDisplay();
		}
	}

	public void tellKeySpaceDown() {
		if (display) {
			if (waiting) {
				D.d("move text!!");
				moveNextMsg();
			}
		}
	}
}
