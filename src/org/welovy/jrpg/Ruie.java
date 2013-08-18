package org.welovy.jrpg;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

// This is Singleton
public class Ruie extends StateBasedGame {
//	MessageBox msgbox;
//	TiledMap map;
	public static final int MAINMENUSTATE   = 0;
	public static final int MAPSTATE        = 1;
	private static UnicodeFont ufont;

	private static MapState mapView;
	private static GameLoader loader;
	static {
		loader = new GameLoader();
		try {
			ufont = new UnicodeFont("/Users/shinpei/Library/Fonts/migmix-1m-regular.ttf", 16, false, false);
			ufont.addAsciiGlyphs();
			ufont.addGlyphs(0x3000, 0x30ff); // Hiragana + katakanab + fullwidth punctuations
			ufont.addGlyphs(0x4e00,0x9fc0); // Kanji
		} catch (SlickException ignore) {
			
		};
	}

	public static void setupFont() {
		ufont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		try {
			ufont.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static UnicodeFont getFont() {
		return ufont;
	}
	public static GameLoader getLoader() {
		return loader;
	}
	public Ruie(String title) {
		super(title);
		addState(new MainMenuState(MAINMENUSTATE));
		mapView = new MapState(MAPSTATE);
		addState(mapView);
	}

	public static MapState getMap() { return mapView; }
	
	public static void main(String[] args) {
		Ruie game = new Ruie("The Battle of Ruie");
		AppGameContainer app = null;
		try {
			app = new AppGameContainer(game);
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException e) {
			// ignore
		} finally {
			app.destroy();
		}
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {

	}
}
