package org.welovy.jrpg;

import org.newdawn.slick.state.StateBasedGame;

class GlobalContext { }
public class Instruction {
	public int instID = -1;
	public Instruction(int instID) {
		this.instID = instID;
	}
	public void exec (Stage s, StateBasedGame sbg) {
		
	}
}

class MoveActorInst extends Instruction {
	private String target;
	private int moveX, moveY;
	public MoveActorInst(String target, int move_x, int move_y) {
		super(1);
		this.target = target;
		this.moveX = move_x;
		this.moveY = move_y;
	}
	@Override
	public void exec (Stage s, StateBasedGame sbg) {
		Actor actor = s.getActor(target);
		//actor.move(moveX, moveY);
		
	}
}

class LoadStage extends Instruction {
	Stage stage;
	public LoadStage(Stage s) {
		super(2);
		this.stage = s;
	}
	
	@Override
	public void exec(Stage s, StateBasedGame sbg) {
		MapState mapState = Ruie.getMap();
		mapState.setCurrentStage(stage);
	}
}

class PlacePlayerAt extends Instruction {
	String charaName;
	int tileX;
	int tileY;
	public PlacePlayerAt(String name, int x, int y) {
		super(2);
		charaName = name;
		tileX = x;
		tileY = y;
	}
	@Override
	public void exec(Stage s, StateBasedGame sbg) {
		Actor act = s.getActor(charaName);
		act.setTilePosition(tileX, tileY);
	}
	
}

class Dialogue extends Instruction {
	String msgtext;
	public Dialogue (String text){
		super(3);
		msgtext = text;
	}
	public void exec(Stage s, StateBasedGame sbg) {
		MapState m = Ruie.getMap();
		MessageBox box = m.getMessageBox();
		box.addMsg(msgtext);
		box.enableDisplay();
	}
}