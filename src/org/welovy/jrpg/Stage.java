package org.welovy.jrpg;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/*
 * Stage is a data class that contains all events,
 * tiled map, and local switches.
 */
public class Stage {
	private TiledMap tiles;
	private Map<String, Event> eventMap;
	private Map<String, Actor> actorMap;

	public Stage(String baseTMXPath) {
		try {
			setTiles(new TiledMap(baseTMXPath));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		eventMap = new HashMap<String, Event>();
		actorMap = new HashMap<String, Actor>();
	}
	
	public void addEvent(String name, Event eve) {
		eventMap.put(name, eve);
	}
	
	public Event getEvent(String name) {
		return eventMap.get(name);
	}

	public TiledMap getTiles() {
		return tiles;
	}

	private void setTiles(TiledMap stageTiles) {
		this.tiles = stageTiles;
	}
	
	public void addActor(Actor actor) {
		actorMap.put(actor.name, actor);
	}
	public Actor getActor(String key) {
		return actorMap.get(key);
	}
}
