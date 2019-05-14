package game;

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
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new Door(), new RocketPad(), new Rocket());
		GameMap lairMap;
		GameMap moonMap;

		List<String> lairMapString = Arrays.asList(
			".................................",
			"...#####D#####....###............",
			"...#.........#.....#.............",
			"...#.........#...........#.#.#...",
			"...#......###...........#.....#..",
			"...##D#####..............#...#...",
			"..............#.#....##..#...#...",
			"..............###....#...........",
			".........#.....#.............#...",
			"..#......#.....#.................",
			".........###.........#######.....",
			"....................#.......#....",
			".....#..............D...=...#....",
			"...........#........#.......#....",
			"....................#########....");
		
		lairMap = new GameMap(groundFactory, lairMapString);
		world.addMap(lairMap);
		
		List<String> moonMapString = Arrays.asList(
				".................................",
				".................................",
				".................................",
				".................................",
				".................................",
				".................................",
				".................................",
				".................................",
				".................................",
				".................................",
				".................................",
				".................................",
				".................................",
				".................................",
				".................................");
		
		moonMap = new GameMap(groundFactory, moonMapString);
		world.addMap(moonMap);
		
		Actor player = new Player("Player", '@', 1, 100);
		world.addPlayer(player, lairMap, 4, 19);
		
		Grunt testGoon = new Goon("Mongo", player);
		lairMap.addActor(testGoon, 0, 0);

		Ninja testNinja = new Ninja("Norbert", player);
		lairMap.addActor(testNinja, 14, 12);

		Grunt testGrunt = new Grunt("Obediah", player);
		lairMap.addActor(testGrunt, 10, 5);
		
		Actor testQ = new Qnpc(player);
		lairMap.addActor(testQ, 27, 4);
		
		Item body = new Item("Rocket Body", 'h');
		Item engine = new Item("Rocket Engine", 'e');
		Item plan = new Item("Rocket Plan", 'p');

		testGoon.addItemToInventory(Item.newInventoryItem("Key", '$'));
		testGrunt.addItemToInventory(Item.newInventoryItem("Key", '$'));
		
		lairMap.addItem(plan, 6, 3);
		lairMap.addItem(new WeaponItem("Wristwatch Lazer", '>', 20, "burns"), 8, 3);
		
		Miniboss miniboss = new Miniboss("Dr Maybe", player);
		miniboss.addItemToInventory(Item.newInventoryItem("Rocket Engine", 'e'));
		lairMap.addActor(miniboss, 26, 12);

		lairMap.addItem(body, 2, 1);
			
		world.run();

		System.out.println("\n𝕋𝕙𝕒𝕟𝕜𝕤 𝕗𝕠𝕣 𝕡𝕝𝕒𝕪𝕚𝕟𝕘 !");
	}
}
