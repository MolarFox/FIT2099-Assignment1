package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.World;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.WeaponItem;
import game.Door;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new Door(), new RocketPad(), new Rocket());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....#...#....#....#....",
				"....#...D....#....#....",
				"....#####....##D###....",
				".......................",
				".......................",
				".......................",
				".......................",
				"...................=...",
				".......................");
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);
		
		Actor player = new Player("Player", '@', 1, 100);
		world.addPlayer(player, gameMap, 2, 15);
		
		Grunt grunt = new Grunt("Mongo", player);
		gameMap.addActor(grunt, 0, 0);
		Grunt grunt2 = new Grunt("Norbert", player);
		gameMap.addActor(grunt2,  10, 10);
		
		Item body = new Item("Rocket Body", 'h');
		Item engine = new Item("Rocket Engine", 'e');
		Item plan = new Item("Rocket Plan", 'p');
		
		grunt.addItemToInventory(Item.newInventoryItem("Key", '$'));
		grunt2.addItemToInventory(Item.newInventoryItem("Key", '$'));
		
		gameMap.addItem(body, 5, 2);
		
		WeaponItem lightsaber = new WeaponItem("Light Saber", '¬', 11, "slices");
		
		Miniboss miniboss = new Miniboss("Dr Maybe", player);
		miniboss.addItemToInventory(engine);
		miniboss.addItemToInventory(lightsaber);
		gameMap.addActor(miniboss, 16, 2);
		
		// TESTS START
		
		player.addItemToInventory(Item.newInventoryItem("Rocket Body", 'h'));
		player.addItemToInventory(Item.newInventoryItem("Rocket Engine", 'e'));
		
		// TESTS END
		
		world.run();
	}
}
