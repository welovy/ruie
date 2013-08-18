package org.welovy.jrpg;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MenuViewComponent implements IViewComponent {
	private Rectangle menuArea = null;
	public MenuData menudata= null;
	private int posX;
	private int posY;
	private int width;
	private int height;
	private int cursorBasePosX;
	private int cursorBasePosY;
//	private KeyHandler kh = null;
	
	public MenuViewComponent(int x, int y, int w, int h) {
		posX = x;
		posY = y;
		width = w;
		height = h;
		cursorBasePosX = posX + 20;
		cursorBasePosY = posY + 20;
		menuArea = new Rectangle(posX, posY, width, height);
		menudata= new MenuData();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.draw(menuArea);
		menudata.getCursor().draw(cursorBasePosX, cursorBasePosY + 30 * menudata.getChoice());
		int idx = 0;
		for (String line: menudata.getStrings()) {
			g.drawString(line,  cursorBasePosX + 24, cursorBasePosY + 30 * idx);
			idx++;
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		Input input  = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_UP)) {
			menudata.choosePrev();
			menudata.playSelecting();
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
			menudata.chooseNext();
			menudata.playSelecting();
		}
		else if (input.isKeyPressed(Input.KEY_RETURN) || input.isKeyPressed(Input.KEY_SPACE)) {
			menudata.playChoose();
			GameLoader loader = Ruie.getLoader();
			loader.startFromBegining(sbg);
			//titleMusic.fade(1000, 0f, true);
			sbg.enterState(Ruie.MAPSTATE,
					new FadeOutTransition(Color.black, 1000),
					new FadeInTransition(Color.black, 1000));
		}

	}

}
