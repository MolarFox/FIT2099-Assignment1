package game;

import edu.monash.fit2099.engine.*;

/**
 * Action for player to unlock doors. If player has key, door may be unlocked and 
 * key consumed if so (any key to any door).
 * 
 * @author      Rithesh Jayaram <RJAY0006@student.monash.edu> 
 * @author      Sarah Dennis <SDEN0009@student.monash.edu>
 * @version     1.0
 * @since       1.0
 */
public class UnlockDoorAction extends Action {

	private Location doorLocation;
	
	/**
	 * Action for player to attempt to unlock a door
	 * @param doorLocation relative direction of door to unlock to actor calling
	 */
	public UnlockDoorAction(Location doorLocation) {
		this.doorLocation = doorLocation;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		
		for (int i = 0; i < actor.getInventory().size(); i++) {
			if (actor.getInventory().get(i).getDisplayChar() == '$') {
				map.add(new Floor(), doorLocation);
				actor.removeItemFromInventory(actor.getInventory().get(i));
				return "The door has been unlocked";
			}
		}
		
		return actor + " does not have a key";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attempts to unlock the door";
	}

	@Override
	public String hotKey() {
		return "";
	}

}
