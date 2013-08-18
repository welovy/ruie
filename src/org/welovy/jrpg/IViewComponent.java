package org.welovy.jrpg;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/*
 * ViewComponent is a renderable component
 * which can be added to specific view.
 * It has one instance for each, they initiated when
 * the View is initiated.
 */
public interface IViewComponent {
	public void init(GameContainer gc, StateBasedGame sbg);
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
	throws SlickException;
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta);

}
