package org.welovy.jrpg;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.state.StateBasedGame;

public class Event {
	List<Instruction> instQueue;
	Event () {
		instQueue = new ArrayList<Instruction>();
	}
	public void add(Instruction inst) {
		instQueue.add(inst);
	}
	
	public void run (StateBasedGame sbg) {
		
		for (Instruction inst: instQueue) {
			
			inst.exec(Ruie.getMap().getCurrentStage(), sbg);
		}
	}
}


