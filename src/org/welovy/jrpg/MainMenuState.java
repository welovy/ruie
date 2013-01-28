package org.welovy.jrpg;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenuState extends BasicGameState {
	int stateID = -1;
	Image background;
	Music titleMusic;
	MenuBox menubox;
	
	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public int getID() {
		return stateID;
	}

	@SuppressWarnings("serial")
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		if (menubox == null){
			menubox = new MenuBox(225, 330);
			menubox.addMenuString(new ArrayList<String>() {{
				add("‚Í‚¶‚ß‚é");
				add("‘±‚¯‚é");
			}});
			
			background = new Image("img/title.png");
			// for the first time;
			Ruie.setupFont();
			
			titleMusic = new Music("music/theme.ogg");
			titleMusic.loop();
			titleMusic.setVolume(0f);
			titleMusic.fade(3000, 0.4f, false);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// draw splash
		background.draw(30, 130);
		// draw menu
		g.setFont(Ruie.getFont());
		g.setColor(Color.white);
		menubox.draw(250, 350, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input  = gc.getInput();
		if (input.isKeyPressed(Input.KEY_UP)) {
			menubox.moveUp();
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
			menubox.moveDown();
		}
		else if (input.isKeyPressed(Input.KEY_RETURN) || input.isKeyPressed(Input.KEY_SPACE)) {
			menubox.choose();
			GameLoader loader = Ruie.getLoader();
			loader.startFromBegining(sbg);
			titleMusic.fade(1000, 0f, true);
			sbg.enterState(Ruie.MAPSTATE,
					new FadeOutTransition(Color.black, 1000),
					new FadeInTransition(Color.black, 1000));
		}
	}
}
