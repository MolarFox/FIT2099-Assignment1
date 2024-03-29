package game;

import edu.monash.fit2099.engine.*;
import java.util.*;

/**
 * neutral NPC actor Q - interacts with player and exchanges rocket plans for rocket parts
 * Performs random walk of map when idle
 * 
 * @author      Rithesh Jayaram <RJAY0006@student.monash.edu> 
 * @author      Sarah Dennis <SDEN0009@student.monash.edu>
 * @version     1.0
 * @since       1.0
 */
public class Qnpc extends Actor {

	public boolean exileReady = false;

	/**
	 * Actor that represents Q, a non-hostile non-player character who can talk and trade with the player.
	 * It randomly moves across the map.
	 * @param player The player actor that Q will interact with
	 */
    public Qnpc(Actor player){
		super("Q", 'Q', 4, 9999);
		addBehaviour(new RandomWalkBehaviour());
    }

    private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		actions.add(new TalkToQAction(this));
		actions.add(new GivePlanAction(this));
		return actions;
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		
		return new NPCSkipTurnAction();	// Do nothing if no actions available
	}

	@Override
	public void hurt(int points){
		return;	// Cannot hurt Q
	}
}