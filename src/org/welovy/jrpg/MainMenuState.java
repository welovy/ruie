package org.welovy.jrpg;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MainMenuState extends BasicGameState {
	int stateID = -1;
	Image background;
	Music titleMusic;
	MenuViewComponent mvComponent;
	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		mvComponent = new MenuViewComponent(255, 330, 200, 100);
		mvComponent.menudata.addMenuString("‚Í‚¶‚ß‚é");
		mvComponent.menudata.addMenuString("‘±‚¯‚é");
		background = new Image("img/title.png");
		// for the first time;
		Ruie.setupFont();

		titleMusic = new Music("music/theme.ogg");
		titleMusic.loop();
		titleMusic.setVolume(0f);
		titleMusic.fade(3000, 0.4f, false);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// draw splash
		background.draw(30, 130);
		// draw menu
		g.setFont(Ruie.getFont());
		g.setColor(Color.white);
		mvComponent.render(gc, sbg, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		mvComponent.update(gc, sbg, delta);
	}
}
