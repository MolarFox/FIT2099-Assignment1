package game;

import edu.monash.fit2099.engine.*;
import java.util.Random;

/**
 * Special action for miniboss - ranged attack on player when line of sight in vertical / horizontal
 * is available
 * 
 * @author      Rithesh Jayaram <RJAY0006@student.monash.edu> 
 * @author      Sarah Dennis <SDEN0009@student.monash.edu>
 * @version     1.0
 * @since       1.0
 */
public class ShootLazerAction extends Action implements ActionFactory {

	private Actor target;
	private Random rand = new Random();

	/**
	 * Instantiate an instance of lazer shot (used by miniboss)
	 * @param subject target of the miniboss (player)
	 */
	public ShootLazerAction(Actor subject) {
		this.target = subject;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		if (target == null){	// Safety check, in case target despawns
			return null;
    	}

		if (rand.nextBoolean()) {
			target.hurt(10);
			
			if (!target.isConscious()) {
				Item sleepingActor = new Item("Sleeping " + target, '%');
				map.locationOf(target).addItem(sleepingActor);
				map.removeActor(target);
				// Game ends here :(
				return "After taking a critical shot from " + actor + ", " + target + " has been knocked out. Oh no!";
			}
			
			return actor + " shoots small lazer beams at " + target + System.lineSeparator() + target + " takes 10 damage.";
		}
		else {
			return actor + " shoots at " + target + " but misses.";
		}
	}
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		Range xs, ys;
		if (here.x() == there.x() || here.y() == there.y()) {
			xs = new Range(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
			ys = new Range(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

			for (int x : xs) {
				for (int y : ys) {
					if(map.at(x, y).getGround().blocksThrownObjects())
						return null;
				}
			}
			return this;
		}
		return null;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "";
	}

	@Override
	public String hotKey() {
		return "";
	}
}
