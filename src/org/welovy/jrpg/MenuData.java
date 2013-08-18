package org.welovy.jrpg;

import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.google.common.collect.Lists;

public class MenuData {
	private List<String> menuStrings = null;
	
	private int choice = 0;
	private Image cursorImage = null;
	private Sound selectingSound = null;
	private Sound chooseSound = null;
	public MenuData() {
		// TODO Auto-generated constructor stub
		menuStrings = Lists.newArrayList();
		try {
			cursorImage = new Image("img/menuarrow.png");
			selectingSound = new Sound("sound/menu_select.wav");
			chooseSound = new Sound("sound/menu_selected.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Image getCursor() {
		return this.cursorImage;
	}
	public int getChoice() {
		return this.choice;
	}
	
	public void addMenuString(String line) {
		menuStrings.add(line);
	}
	
	public void addMenuStrings(List<String> lines) {
		for (String line : lines) {
			menuStrings.add(line);
		}
	}
	
	public List<String> getStrings() {
		return this.menuStrings;
	}
	
	public void chooseNext() {
		if (choice < menuStrings.size() -1) {
			choice ++;
		} else {
			choice = 0;
		}		
	}
	
	public void choosePrev() {
		if (choice > 0) {
			choice --;
		} else {
			choice = menuStrings.size() - 1;
		}		
	}
	
	public void playSelecting() {
		selectingSound.play(1f, 0.4f);
	}
	
	public void playChoose() {
		chooseSound.play(1f, 0.4f);
	}
}
