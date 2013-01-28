package org.welovy.jrpg;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameLoader {

	public GameLoader() {
		
	}
	
	public void startFromBegining(StateBasedGame sbg) {
		Event oe = new Event();
		
		Stage beginning = new Stage("tiled/worldmap.tmx");
		
		Actor chara = new Actor("player", "img/player.png", 32, 32);
		chara.setupActorUp(0, 3, 2, 3);
		chara.setupActorDown(0, 0, 2, 0);
		chara.setupActorRight(0, 2, 2, 2);
		chara.setupActorLeft(0, 1, 2, 1);
		chara.faceDown();
		beginning.addActor(chara);
		
		try {
			Music m = new Music("music/opening.ogg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		oe.add(new LoadStage(beginning));
		oe.add(new PlacePlayerAt("player", 10, 10));
		oe.add(new Dialogue("“ú–{Œê‚Ì’Ç‰Á‚ª‚Ş‚¸‚©‚µ‚©‚Á‚½‚æ‚Ë"));
		oe.add(new Dialogue("‚³‚ç‚È‚é“ú–{Œê‚ğ“ü—Í‚µ‚Ä‚İ‚é‚¨B"));
		oe.run(sbg);
	}

}
