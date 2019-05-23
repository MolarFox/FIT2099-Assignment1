package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class RefillWaterPistolAction extends Action {
	
	public RefillWaterPistolAction() {}

	@Override
	public String execute(Actor actor, GameMap map) {
		Item waterPistol = Utilities.getItem(actor, '~');
		
		if (waterPistol != null) {
			// If water pistol is empty
			if (waterPistol.hasSkill(WaterPistolCharge.EMPTY)) {
				
				// Changes pistol charge to full
				waterPistol.removeSkill(WaterPistolCharge.EMPTY);
				waterPistol.addSkill(WaterPistolCharge.FULL);
				
				return actor + " successfully refills their water pistol.";
			}
			else
				return actor + " attempts to fill their water pistol, only to realise that it is already full.";
		}
		else
			return "Huh? " + actor + " does not have a water pistol.";
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " refills water pistol.";
	}

	@Override
	public String hotKey() {
		return "";
	}
	
}
