package org.welovy.jrpg;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class Actor {
	String name;
	public Animation sprite;
	public float posX = 0f, posY = 0f ;
	
	private Animation movementUp, movementDown, movementRight, movementLeft;
	private SpriteSheet ssheet; 
	private static final int[] duration = {300, 300};
	private int chipWidth = 0, chipHeight = 0;
	
	public Actor(String name) {
		this.name = name;
		sprite = null;
	}
	
	public Actor(String name, String sheetpath, int width, int height) {
		this.name = name;
		chipWidth = width;
		chipHeight = height;
		try {
			ssheet = new SpriteSheet(sheetpath, chipWidth, chipHeight);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	//TODO: fix method pattern
	public void setupActorUp(int x1, int y1, int x2, int y2) {
		Image[] img = {
				ssheet.getSubImage(x1, y1),
				ssheet.getSubImage(x2, y2)
		};
		movementUp = new Animation(img, duration, false);
	}
	public void setupActorDown(int x1, int y1, int x2, int y2) {
		Image[] img = {
				ssheet.getSubImage(x1, y1),
				ssheet.getSubImage(x2, y2)
		};
		movementDown = new Animation(img, duration, false);
	}
	public void setupActorLeft(int x1, int y1, int x2, int y2) {
		Image[] img = {
				ssheet.getSubImage(x1, y1),
				ssheet.getSubImage(x2, y2)
		};
		movementLeft = new Animation(img, duration, false);
	}
	public void setupActorRight(int x1, int y1, int x2, int y2) {
		Image[] img = {
				ssheet.getSubImage(x1, y1),
				ssheet.getSubImage(x2, y2)
		};
		movementRight = new Animation(img, duration, false);
	}
	/* instructions */

	public void faceUp() { sprite = movementUp; }
	public void faceDown() { sprite = movementDown;}
	public void faceRight() { sprite = movementRight; }
	public void faceLeft() { sprite = movementLeft; }
	
	public void setTilePosition(int x, int y) {
		this.posX = x * chipWidth;
		this.posY = y * chipHeight;
	}
	
	// FIXME: don't want to take map argument... 
	public void moveUp(TiledMap map, long delta) {
		this.faceUp();
		sprite.update(delta);
		float nextTileX = (posX + 32);
		float nextTileY = (posY - delta * 0.1f);
		int tileWidth = 16, tileHeight = 16;
		int tileID = map.getTileId((int)nextTileX / tileWidth, (int)nextTileY / tileHeight, 1);
		if (tileID == 0) {
			posY -= delta * 0.1f;
		}
	}
	
	public void moveDown(TiledMap map, long delta) {
		this.faceDown();
		sprite.update(delta);
		float nextTileX = (posX + 32);
		float nextTileY = (posY + 32 + delta * 0.1f);
		int tileWidth = 16, tileHeight = 16;
		int tileID = map.getTileId((int)nextTileX / tileWidth, (int)nextTileY / tileHeight, 1);
		if (tileID == 0) {
			posY += delta * 0.1f;
		}	
	}
	
	public void moveLeft(TiledMap map, long delta){
		this.faceLeft();
		sprite.update(delta);
		float nextTileX = (posX - delta * 0.1f);
		float nextTileY = (posY + 32);
		int tileWidth = 16, tileHeight = 16;
		int tileID = map.getTileId((int)nextTileX / tileWidth, (int)nextTileY / tileHeight, 1);
		if (tileID == 0) {
			posX -= delta * 0.1f;
		}
	}
	
	public void moveRight(TiledMap map, long delta) {
		int tileWidth = 16, tileHeight = 16;
		this.faceRight();
		sprite.update(delta);
		float nextTileX = (posX + 32 + delta * 0.1f);
		float nextTileY = posY;
		int tileID = map.getTileId((int)nextTileX / tileWidth, (int)nextTileY / tileHeight, 1);
		if (tileID == 0) {
			posX += delta * 0.1f;
		}
	}
}
