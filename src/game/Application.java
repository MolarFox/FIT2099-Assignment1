package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * Main game driver - initialises world and handles all calls (contains main)
 * 
 * @author      Rithesh Jayaram <RJAY0006@student.monash.edu> 
 * @author      Sarah Dennis <SDEN0009@student.monash.edu>
 * @version     1.0
 * @since       1.0
 */
public class Application {

	/**
	 * Main program loop for runtime
	 * Note that all locations, and their placed NPCs, items, and ground panels are initialised here
	 */
	public static void main(String[] args) {
		World world = new FancyWorld(new Display());
		
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new Door(), new RocketPad(), new Rocket(), new WaterPool(), new OxygenDispenser());

		// -- CREATE MAPS -------------------------------------------------------------------- //

		GameMap lairMap;
		List<String> lairMapString = Arrays.asList(
			".................................",
			"...#####D#####....###............",
			"=..#.........#.....#.............",
			"...#.........#...........#.#.#...",
			"...#......###...........#.....#..",
			"...##D#####..............#...#...",
			"..............#.#....##..#...#...",
			"..............###....#...........",
			".........#.....#.............#...",
			"..#......#.....#.................",
			".........###.........#######.....",
			"....................#.......#....",
			".....#..............D.......#....",
			".╬.........#........#.......#....",
			"....................#########....");
		
		lairMap = new GameMap(groundFactory, lairMapString);
		world.addMap(lairMap);
		
		GameMap moonMap;
		List<String> moonMapString = Arrays.asList(
			".......R..",
			"..........",
			".&........",
			".......&..",
			"..........",
			"..........",
			".&........",
			"..........",
			"........&.",
			"..........");
	
		moonMap = new GameMap(groundFactory, moonMapString);
		world.addMap(moonMap);
		
		List<GameMap> maps = new ArrayList<GameMap>();
		maps.add(lairMap);
		maps.add(moonMap);
		

		// -- CREATE ACTORS ------------------------------------------------------------------ //

		Actor player = new FancyPlayer("Player", '@', 1, 100, maps);	// Player instance
		world.addPlayer(player, lairMap, 4, 19);

		Actor yugo = new FinalBoss("Yugo Maxx", player);
		moonMap.addActor(yugo, 0, 0);
		
		Actor moonGoon = new Goon("Eureka", player);
		moonMap.addActor(moonGoon, 7, 5);
		
		Actor moonGrunt = new Grunt("Alpha", player);
		moonMap.addActor(moonGrunt, 9, 8);

		Actor miniboss = new Miniboss("Dr Maybe", player);
		lairMap.addActor(miniboss, 26, 12);
		
		Actor lairGoon = new Goon("Mongo", player);
		lairMap.addActor(lairGoon, 1, 1);

		Actor lairNinja = new Ninja("Norbert", player);
		lairMap.addActor(lairNinja, 14, 12);

		Actor lairGrunt = new Grunt("Obediah", player);
		lairMap.addActor(lairGrunt, 10, 5);
		
		Actor lairQ = new Qnpc(player);
		lairMap.addActor(lairQ, 27, 4);
		

		// -- CREATE ITEMS ------------------------------------------------------------------- //

		Item wristwatchLazer = new WeaponItem("Wristwatch Lazer", '>', 20, "burns");
		Item body = new Item("Rocket Body", 'h');
		Item engine = new Item("Rocket Engine", 'e');
		Item plan = new Item("Rocket Plan", 'p');
		Item exoskeleton = new Item("Exo-skeleton", 'x');
		Item spacesuit = new Item("Space Suit", '8');
		
		Item waterpistol = new Item("Water Pistol", '~');
		waterpistol.addSkill(WaterPistolCharge.EMPTY);
		
		yugo.addItemToInventory(exoskeleton);

		miniboss.addItemToInventory(engine);
		lairGoon.addItemToInventory(Item.newInventoryItem("Key", '$'));
		lairGrunt.addItemToInventory(Item.newInventoryItem("Key", '$'));
		
		lairMap.addItem(wristwatchLazer, 8, 2);
		lairMap.addItem(plan, 7, 4);
		lairMap.addItem(waterpistol, 16, 8);
		lairMap.addItem(spacesuit, 24, 2);
		

		// -- RUNTIME AND TERMINATION -------------------------------------------------------- //
		world.run();
	}
}
