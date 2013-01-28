package org.welovy.jrpg;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Sound;

public class MenuBox {

	private Rectangle box = null;
	private Image selecter = null;
	private List<String> menuStrings = null;
	private int box_pos_x;
	private int box_pos_y;
	private int choice = 0;
	private int choiceMax = 0;
	private Sound selectSound = null;
	private Sound chooseSound = null;
	
	public MenuBox(int pos_x, int pos_y) {
		// TODO Auto-generated constructor stub
		box_pos_x = pos_x;
		box_pos_y = pos_y;
		choiceMax = 0;
		box = new Rectangle(box_pos_x, box_pos_y, 200, 100);
		try {
			selecter = new Image("img/menuarrow.png");
			selectSound = new Sound("sound/menu_select.wav");
			chooseSound = new Sound("sound/menu_selected.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		menuStrings = new ArrayList<String>();

	}
	
	public void addMenuString(String line) {
		menuStrings.add(line);
		choiceMax+=1;
	}
	public void addMenuString(List<String> lines) {
		for (String line: lines) {
			menuStrings.add(line);
			choiceMax += 1;
		}
	}

	public void draw(int pos_x, int pos_y, Graphics g) {
		g.draw(box);
		selecter.draw(pos_x, pos_y + 30 * choice);
		int idx = 0;
		for (String line: menuStrings) {
			g.drawString(line, pos_x + 24, pos_y + 30 * idx);
			idx++;
		}
	}

	public void moveDown () {
		if (choice < choiceMax-1) {
			choice ++;
		} else {
			choice = 0;
		}
		selectSound.play(1f, 0.4f);
	}
	public void moveUp() {
		if (choice > 0) {
			choice --;
		} else {
			choice = choiceMax-1;
		}
		selectSound.play(1f, 0.4f);
	}
	public void choose() {
		chooseSound.play(1f, 0.4f);
	}
}
