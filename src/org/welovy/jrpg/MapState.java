package org.welovy.jrpg;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class MapState extends BasicGameState {
	private int stateID = -1;
	private Stage currentStage;
	
	private static MessageBox msgbox;
	//private TiledMap map;
	private static int mapWidth;
	private static int mapHeight;
	private static final int viewWidth = 640;
	private static final int viewHeight = 480;
	private static final int tileWidth = 16;
	private static final int tileHeight = 16;
	
	public MapState(int stateID) {
		this.stateID = stateID;
		msgbox = new MessageBox();
	}
	@Override
	public int getID() {
		return stateID;
	}
	
	public MessageBox getMessageBox() {
		return msgbox;
	}
	public Stage getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(Stage s) {
		currentStage = s;
		TiledMap map = currentStage.getTiles();
		mapWidth = map.getWidth() * tileWidth;
		mapHeight = map.getHeight() * tileHeight;
		D.d("mapW=" + mapWidth + ", mapH=" + mapHeight);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		int skipTileX = 0, skipTileY = 0;
		int startDrawingX = 0, startDrawingY = 0;
		int spriteX = 0, spriteY = 0; 
		Actor player = currentStage.getActor("player");
		float charaX = player.posX;
		float charaY = player.posY;
		
		float absViewX = charaX - (viewWidth / 2 - 16);
		float absViewY = charaY - (viewHeight / 2 - 16);


		if (absViewX < 0) {
			// move character
			// no diffX, no skipX
			spriteX = (int)charaX;
		}
		else if (mapWidth - viewWidth < absViewX ) {
			// charamove
			skipTileX = mapWidth / 16 - viewWidth / 16;
			spriteX = (int)(charaX - skipTileX * 16);
		}
		else {
			// calc diffX
			skipTileX = (int)absViewX / tileWidth;
			startDrawingX = -(int)absViewX % tileWidth;
			spriteX = (int)(viewWidth / 2 - 16);
		}
		
		if (absViewY < 0) {
			//charamove
			spriteY = (int)charaY;
		}
		else if (mapHeight - viewHeight < absViewY) {
			// charamove
			skipTileY = mapHeight / 16 - viewHeight / 16;
			spriteY = (int)(charaY - skipTileY * 16);
		}
		else {
			skipTileY = (int)absViewY / tileHeight;
			startDrawingY = -(int)absViewY % tileWidth;
			spriteY = (int)(viewHeight / 2 - 16);
		}
		currentStage.getTiles().render(
			startDrawingX, startDrawingY,
			skipTileX, skipTileY,
			(int)viewWidth / 16 + 1, (int)viewWidth / 16 + 1,
			0, false
		);
		currentStage.getActor("player").sprite.draw(spriteX, spriteY);
		g.setFont(Ruie.getFont());
		msgbox.render(gc, g);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		TiledMap map = currentStage.getTiles();
		Actor player = currentStage.getActor("player");
		if (input.isKeyDown(Input.KEY_UP)) {
			player.moveUp(map, delta);
		}
		else if (input.isKeyDown(Input.KEY_DOWN)) {
			player.moveDown(map, delta);
		}
		else if (input.isKeyDown(Input.KEY_LEFT)) {
			player.moveLeft(map, delta);
		}
		else if (input.isKeyDown(Input.KEY_RIGHT)) {
			player.moveRight(map, delta);
		}
		else if (input.isKeyPressed(Input.KEY_SPACE)) {
			//TODO:
			msgbox.tellKeySpaceDown();
		}
		
		try {
			msgbox.update(gc, delta);
		} catch (SlickException ignore) {
			//ignore
		}
	}

}
