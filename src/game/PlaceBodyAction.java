package game;

import edu.monash.fit2099.engine.*;

/**
 * Action for player to interact with rocket pad - places body part
 * 
 * @author      Rithesh Jayaram <RJAY0006@student.monash.edu> 
 * @author      Sarah Dennis <SDEN0009@student.monash.edu>
 * @version     1.0
 * @since       1.0
 */
public class PlaceBodyAction extends Action {
	
	private Location rocketPadLocation;

	/**
	 * Action that allows the player to place a rocket body item onto the rocket pad
	 * @param rocketPadLocation Location of the rocket pad on the map
	 */
	public PlaceBodyAction(String direction, Location rocketPadLocation) {
		this.rocketPadLocation = rocketPadLocation;
	}

	private Item playerHasItem(Actor actor, char itemDisplayChar) {
		for (int i = 0; i < actor.getInventory().size(); i++) {
			if (actor.getInventory().get(i).getDisplayChar() == itemDisplayChar) {
				return actor.getInventory().get(i);
			}
		}
		
		return null;
	}
	
	private Item rocketPadHasItem(char itemDisplayChar) {
		for (int i = 0; i < rocketPadLocation.getItems().size(); i++) {
			if (rocketPadLocation.getItems().get(i).getDisplayChar() == itemDisplayChar) {
				return rocketPadLocation.getItems().get(i);
			}
		}
		
		return null;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		Item body = playerHasItem(actor, 'h');
		Item engine = rocketPadHasItem('e');
		
		if (body != null) {
			actor.removeItemFromInventory(body);
			rocketPadLocation.addItem(body);
			
			if (engine != null) {
				// Clear inventory
				rocketPadLocation.removeItem(body);
				rocketPadLocation.removeItem(engine);
				
				map.add(new Rocket(), rocketPadLocation);
				return actor + " has placed the rocket body.\nThe rocket has now been completed.";
			}
			
			return actor + " has placed the rocket body.";
		}
		else {
			return actor + " does not have a rocket body part.";
		}
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attempts to place rocket body onto rocket pad";
	}

	@Override
	public String hotKey() {
		return "";
	}
}
