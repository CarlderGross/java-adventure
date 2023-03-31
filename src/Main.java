import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

import Creatures.*;
import Items.*;
import Meta.*;

class Main {
	// Initialize game elements
	// Basic Variables
	static boolean isdead = false;
	static Scanner scan = new Scanner(System.in);
	static Random rand = new Random();
	// Armor
	static Armor leather = new Armor("leather armor", 3, "Some basic leather armor. Typical adventuring gear.");
	static Armor chain = new Armor("chain mail", 5,
			"Chain mail armor. High quality protection that is still pretty light.");
	static Armor plate = new Armor("plate mail", 8,
			"Plate mail armor: the toughest armor around. Made from plates of solid steel. Can stop even a spear.");
	static Armor[] armList = { leather, chain, plate };
	// Weapons
	static Weapon stick = new Weapon("stick", 1,
			"A largish stick from the ground. Even the worst-quality armor can stop it.");
	static Weapon swordShort = new Weapon("shortsword", 5,
			"A shortsword, the typical weapon for most people. Can penetrate leather armor.");
	static Weapon swordLong = new Weapon("longsword", 7,
			"A high quality longsword. Chops through anything short of plate mail, and very hard to break.");
	static Weapon spear = new Weapon("spear", 8,
			"A nice long spear, tipped with a steel point. Stabs through almost anything if used properly.");
	static Weapon axeBattle = new Weapon("battle axe", 10,
			"A huge double-bladed axe. One of the most feared weapons in the land, this fearsome bladed weapon can cut holes in even the toughest plate mail.");
	static Weapon swordGreat = new Weapon("greatsword", 10,
			"A huge sword that hacks through metal. Specifically designed for taking out knights and other heavily armored targets. Can penertrate plate mail in a single swipe.");
	static Weapon swordShortSilver = new Weapon("silver shortsword", 5,
			"A shortsword made of silver, this weapon is much more expensive than its normal counterpart but can injure undead and other magical creatures much more effectively.");
	static Weapon swordShortEnch = new Weapon("enchanted shortsword", 6,
			"A shortsword enchanted with magical spells, this weapon does a bit more damage than usual, as well as injuring magical creatures easily.");
	static Weapon[] wepList = { swordShort, swordLong, spear, axeBattle, swordGreat, swordShortSilver, swordShortEnch };
	// Potions
	static Potion ironskin = new Potion("ironskin potion", 0, 5, 0,
			"An ironskin potion gives an unarmored man the equivalent of chain mail for a certain time period. Valued by adventurers and nobles worried about assasination.");
	static Potion strength = new Potion("strength potion", 0, 0, 5,
			"A strength potion makes the drinker stronger, allowing them to swing their weapon harder and do more damage.");
	static Potion healing = new Potion("healing potion", 5, 0, 0,
			"A delicious nectar, healing potions can heal any wound, as long as the wound is only as bad as a shortsword injury.");
	static Potion beerMage = new Potion("mage's beer", 2, 1, 2,
			"Magical beer created by mages, this magical brew is much better than alcohol, as it tastes just as good while having none of the side effects. In addition, the beer actually increases the abilities of the drinker for a short time.");
	static Potion beer = new Potion("beer", -2, -1, 3,
			"A foaming mug of beer is the favorite drink of most humans. However, it can make you drunk, inhibiting your ability to dodge, as well as not being very good for you. However, drunkenness usually allows people to attempt attacks that sober folk would dismiss as absurd.");
	static Potion poison = new Potion("poison", -10, -1, -1,
			"A potent bottle of poison is the favorite weapon of unscrupulous assasins. This particular poison is a contact poison, and causes slight nausea as well as being injurious.");
	static Potion[] potList = { ironskin, strength, healing, beerMage, beer, poison };

	// Monsters
	static Monster dummy = new Monster("training dummy", 100, 1, 1, 1, 1,
			"A basic training dummy enchanted to provide adventurers with basic combat training.");
	static Monster goblin = new Monster("goblin", 50, 5, 2, 5, 5, "A goblin. Super lame. Not really dangerous.");
	static Monster dragRed = new Monster("red dragon", 500, 10, 8, 200, 500,
			"A fire-type dragon with red scales. Extremely dangerous. Typically inhabits volcanoes.");
	static Monster impFire = new Monster("fire imp", 75, 10, 1, 8, 20,
			"A tiny hostile fire elemental that shoots fire bolts.");
	static Monster orc = new Monster("orc", 100, 7, 3, 7, 10,
			"An orc with a sword. Equivalent to a basic adventurer, except hostile.");
	static Monster spider = new Monster("giant spider", 50, 5, 0, 3, 4, "A huge spider that likes to eat people.");
	static Monster spiderPois = new Monster("giant poisonous spider", 50, 8, 0, 4, 8,
			"A huge poisonous spider that likes to eat people.");
	static Monster spiderDeath = new Monster("giant deadly spider", 50, 100, 0, 20, 150,
			"A huge spider with deadly poison that likes to eat people. Run away immediately if you see one because it can kill in one bite.");
	static Monster zombie = new Monster("zombie", 100, 5, 5, 10, 10, "A walking corpse that likes to eat brains.");
	static Monster skelly = new Monster("skeleton", 75, 7, 2, 10, 8,
			"A walking skeleton (duh) that likes to kill people.");
	static Monster golem = new Monster("malfunctioning golem", 300, 15, 10, 30, 50,
			"A malfunctioning defense golem that will attack anything.");
	static Monster dummyBroken = new Monster("broken training dummy", 42, 4, 1, 1, 5,
			"A broken training dummy. The splintered wood might actually be able to injure you.");
	static Monster eleRock = new Monster("rock elemental", 100, 7, 8, 10, 30,
			"A walking pile of rocks that does not like anything invading its territory.");
	// Encounter Tables
	static Monster[] wilderness = { goblin, goblin, orc };
	static Monster[] town = { dummy };
	static Monster[] volcano = { dragRed, impFire, impFire, impFire };
	static Monster[] woodSpiderMons = { spider, spider, spider, spider, spider, spider, spiderPois, spiderPois,
			spiderPois, spiderDeath };
	static Monster[] ruins = { dummyBroken, zombie, zombie, skelly, skelly, golem };
	static Monster[] stone = { eleRock };
	static Monster[] gobMtMons = { goblin, goblin, goblin, goblin, goblin, goblin, orc };
	static Monster[] orcMtMons = { orc, orc, orc, goblin };
	// Locations
	static Location guild = new Location("Adventurer's Guildhall", town, true,
			"The main guildhall of the Adventurer's Guild.");
	static Location dunTown = new Location("Dungeon Town", town, true,
			"A town surrounded by dungeons and home of the Adventurer's Guild.");
	static Location roadOld = new Location("The Ancient Road", wilderness, true,
			"An ancient road that has fallen into disrepair.");
	static Location dragMt = new Location("Dragon's Peak", volcano, false, "A volcano inhabited by a powerful dragon.");
	static Location roadOld2 = new Location("The Ancient Road", wilderness, true,
			"An ancient road that has fallen into disrepair.");
	static Location woodSpider = new Location("The Webbed Woods", woodSpiderMons, false,
			"A dark and gloomy forest full of huge spiderwebs.");
	static Location woodSpider2 = new Location("The Webbed Woods", woodSpiderMons, false,
			"A dark and gloomy forest full of huge spiderwebs.");
	static Location woodSpider3 = new Location("The Webbed Woods", woodSpiderMons, false,
			"A dark and gloomy forest full of huge spiderwebs.");
	static Location woodSpider4 = new Location("The Webbed Woods", woodSpiderMons, false,
			"A dark and gloomy forest full of huge spiderwebs.");
	static Location woodSpider5 = new Location("The Webbed Woods", woodSpiderMons, false,
			"A dark and gloomy forest full of huge spiderwebs.");
	static Location ruinsTown = new Location("Ruined Town", ruins, false,
			"Smoldering ruins of a town. Most valuables have been carried off long ago. A smoking volcano looms in the distance.");
	static Location rockMt = new Location("Stonemount", stone, false,
			"A rocky mountain prone to avalanches. Some rocks don't seem quite right...");
	static Location woodGreen = new Location("The Greenwood", wilderness, false,
			"A green and leafy forest full of goblins and orcs.");
	static Location woodGreen2 = new Location("The Greenwood", wilderness, false,
			"A green and leafy forest full of goblins and orcs.");
	static Location woodGreen3 = new Location("The Greenwood", wilderness, false,
			"A green and leafy forest full of goblins and orcs.");
	static Location woodGreen4 = new Location("The Greenwood", wilderness, false,
			"A green and leafy forest full of goblins and orcs.");
	static Location orcMt = new Location("Orccliff", orcMtMons, false,
			"A huge cave complex infested with orcs. Some tunnels connect to the goblin caves.");
	static Location gobMt = new Location("Goblincrag", gobMtMons, false,
			"A huge cave complex infested with goblins. Some tunnels connect to the orc caves.");

	static Location[][] map = { { null, null, ruinsTown, dragMt, null }, // 0
			{ null, woodGreen4, roadOld2, woodSpider, null }, // 1
			{ woodGreen3, woodGreen2, roadOld, woodSpider2, woodSpider4 }, // 2
			{ gobMt, woodGreen, dunTown, woodSpider3, woodSpider5 }, // 3
			{ orcMt, rockMt, guild, null, null } // 4
	};

	static Player play = new Player(100, leather, swordShort, healing, guild);

	static Player move(Player play, Location[][] map, Scanner scan) {
		int array = 0;
		int place = 0;
		for (int arrayId = 0; arrayId < map.length; arrayId++) {
			for (int loc = 0; loc < map[arrayId].length; loc++) {
				if (map[arrayId][loc] == play.location) {
					array = arrayId;
					place = loc;
				}
			}
		}
		Location north = map[array - 1][place];
		Location south;
		if (array + 1 > map.length - 1) {
			south = null;
		} else {
			south = map[array + 1][place];
		}
		Location east = map[array][place - 1];
		Location west = map[array][place + 1];
		if (north != null) {
			System.out.println("To the north is " + north.name);
		}
		if (south != null) {
			System.out.println("To the south is " + south.name);
		}
		if (east != null) {
			System.out.println("To the east is " + east.name);
		}
		if (west != null) {
			System.out.println("To the west is " + west.name);
		}
		System.out.println("Choose a direction:");
		System.out.println("(n)orth, (s)outh, (e)ast, (w)est");
		String directCho = scan.next();
		if (directCho.equals("n")) {
			if (north == null) {
				System.out.println("You can't go that way!");
			} else {
				System.out.println("You go north to " + north.name + ".");
				play.location = north;
			}
		}
		if (directCho.equals("s")) {
			if (south == null) {
				System.out.println("You can't go that way!");
			} else {
				System.out.println("You go south to " + south.name + ".");
				play.location = south;
			}
		}
		if (directCho.equals("e")) {
			if (east == null) {
				System.out.println("You can't go that way!");
			} else {
				System.out.println("You go east to " + east.name + ".");
				play.location = east;
			}
		}
		if (directCho.equals("w")) {
			if (west == null) {
				System.out.println("You can't go that way!");
			} else {
				System.out.println("You go west to " + west.name + ".");
				play.location = west;
			}
		}
		return play;
	}

	static Player combat(Player play, Monster mon, Scanner scan, Random rand) {
		System.out.println("You encounter a hostile " + mon.name + "!");
		while (true) {
			System.out.println("Choose an action:");
			System.out.println("(f)ight, (r)un, (u)se potion, (c)heck, (n)othing");
			String action = scan.next();
			if (action.equals("f")) {
				play.attack(mon, rand);
				System.out.println("");
				mon.attack(play, rand);
				if (play.hp <= 0 || mon.hp <= 0) {
					break;
				}
			} else if (action.equals("r")) {
				System.out.println("You run away.");
				break;
			} else if (action.equals("u")) {
				System.out.println("Choose what to do with the " + play.potion.name + ":");
				System.out.println("(t)hrow at the " + mon.name + ", (d)rink");
				String potChoice = scan.next();
				if (potChoice.equals("t")) {
					System.out.println("You throw the " + play.potion.name + " at the " + mon.name + "!");
					play.potion.affectMon(mon);
					play.potion = null;
				}
				if (potChoice.equals("d")) {
					System.out.println("You drink the " + play.potion.name + ".");
					play.potion.affectPlay(play);
					play.potion = null;
				}
			} else if (action.equals("c")) {
				System.out.println("Choose what to check:");
				System.out.println("(w)eapon, (a)rmor, (p)otion, (m)onster");
				String check = scan.next();
				if (check.equals("w")) {
					System.out.println(play.weapon.describe);
					System.out.println(play.weapon.dmg + " damage.");
				}
				if (check.equals("a")) {
					System.out.println(play.armor.describe);
					System.out.println(play.armor.def + " defense.");
				}
				if (check.equals("p")) {
					System.out.println(play.potion.describe);
					if (play.potion.hp != 0) {
						System.out.println(play.potion.hp + " to health.");
					}
					if (play.potion.def != 0) {
						System.out.println(play.potion.def + " to defense.");
					}
					if (play.potion.dmg != 0) {
						System.out.println(play.potion.dmg + " to damage.");
					}
				}
				if (check.equals("m")) {
					System.out.println(mon.describe);
					if (mon.hp > play.hp) {
						System.out.println("It currently has more health than you.");
					}
					if (mon.hp < play.hp) {
						System.out.println("It currently has less health than you.");
					}
					if (mon.hp == play.hp) {
						System.out.println("It currently has the same health as you.");
					}
					if (mon.def >= play.weapon.dmg) {
						System.out.println("Your weapon is too weak to hurt it!");
					}
				}
			} else if (action.equals("n")) {
				System.out.println("The " + mon.name + " takes advantage of your hesitation!");
				mon.attack(play, rand);
			} else {
				System.out.println("That's not a valid action!");
			}
		}
		if (play.hp <= 0) {
			System.out.println("YOU DIED!");
			isdead = true;
		}
		if (mon.hp <= 0) {
			System.out.println("You killed the " + mon.name + "!");
			mon.loot(play, rand);
		}
		if (play.hp > 0) {
			play.lvlCheck();
		}
		return play;
	}

	static Player encounter(Player play, Random rand, Scanner scan) {
		int type = rand.nextInt(3);
		if (type == 0) {
			System.out.println("You don't encounter anything of interest.");
		}
		if (type == 1 && play.location.hasMerch == true) {
			System.out.println("You encounter a traveling merchant!");
			int cost;
			String buyChoice;
			int merchType = rand.nextInt(3);
			if (merchType == 0) {
				System.out.println("The merchant is selling weapons.");
				System.out.println("Most aren't any good, but one weapon catches your eye:");
				Weapon merchWep = wepList[rand.nextInt(wepList.length)];
				while (merchWep == play.weapon) {
					merchWep = wepList[rand.nextInt(wepList.length)];
				}
				System.out.println("It's a " + merchWep.name + "!");
				cost = 2 * merchWep.dmg;
				System.out.println("The merchant says that it will cost " + cost + " gold.");
				System.out.println("You have " + play.gold + " gold.");
				System.out.println("The " + merchWep.name + " does " + merchWep.dmg + " damage.");
				System.out.println("Your " + play.weapon.name + " does " + play.weapon.dmg + " damage.");
				System.out.println("Do you want to buy the " + merchWep.name + "?");
				System.out.println("(y)es, (n)o");
				buyChoice = scan.next();
				if (buyChoice.equals("y")) {
					if (play.gold < cost) {
						System.out.println("You don't have enough gold!");
					} else {
						System.out
								.println("You buy the " + merchWep.name + " and replace your current weapon with it.");
						play.weapon = merchWep;
						play.gold -= cost;
					}
				}
			}
			if (merchType == 1) {
				System.out.println("The merchant is selling armor.");
				System.out.println("Most isn't any good, but one set catches your eye...");
				Armor merchArm = armList[rand.nextInt(armList.length)];
				while (merchArm == play.armor) {
					merchArm = armList[rand.nextInt(armList.length)];
				}
				System.out.println("It's a set of " + merchArm.name + "!");
				cost = merchArm.def * 2;
				System.out.println("The merchant says it will cost " + cost + " gold.");
				System.out.println("You have " + play.gold + " gold.");
				System.out.println("The " + merchArm.name + " has " + merchArm.def + " defense.");
				System.out.println("Your " + play.armor.name + " has " + play.armor.def + " defense.");
				System.out.println("Do you want to buy the " + merchArm.name + "?");
				System.out.println("(y)es, (n)o");
				buyChoice = scan.next();
				if (buyChoice.equals("y")) {
					if (play.gold < cost) {
						System.out.println("You don't have enough gold!");
					} else {
						System.out.println("You buy the " + merchArm.name + " and replace your current armor with it.");
						play.armor = merchArm;
						play.gold -= cost;
					}
				}
			}
			if (merchType == 2) {
				System.out.println("The merchant is selling potions.");
				System.out.println("Most aren't any good, but one catches your eye...");
				Potion merchPot = potList[rand.nextInt(potList.length)];
				while (merchPot == play.potion) {
					merchPot = potList[rand.nextInt(potList.length)];
				}
				System.out.println("It's a " + merchPot.name + "!");
				int potStats = Math.abs(merchPot.def + merchPot.dmg + merchPot.hp);
				cost = 2 * potStats;
				System.out.println("The merchant says it will cost " + cost + " gold.");
				System.out.println("You have " + play.gold + " gold.");
				System.out.println("The " + merchPot.name + " gives " + merchPot.def + " to defense, " + merchPot.dmg
						+ " to damage, and " + merchPot.hp + " to health.");
				System.out.println("Your " + play.potion.name + " gives " + play.potion.def + " to defense, "
						+ play.potion.dmg + " to damage, and " + play.potion.hp + " to health.");
				System.out.println("Do you want to buy the " + merchPot.name + "?");
				System.out.println("(y)es, (n)o");
				buyChoice = scan.next();
				if (buyChoice.equals("y")) {
					if (play.gold < cost) {
						System.out.println("You don't have enough gold!");
					} else {
						System.out
								.println("You buy the " + merchPot.name + " and replace your current potion with it.");
						play.potion = merchPot;
						play.gold -= cost;
					}
				}
			}
		}
		if (type == 2) {
			Monster mon = play.location.mons[rand.nextInt(play.location.mons.length)];
			combat(play, mon, scan, rand);
		}
		if (type == 1 && play.location.hasMerch == false) {
			Monster mon = play.location.mons[rand.nextInt(play.location.mons.length)];
			combat(play, mon, scan, rand);
		}
		return play;
	}

	public static void main(String[] args) {
		while (true) {
			if (isdead == true) {
				break;
			}
			System.out.println("You are in " + play.location.name + ":");
			System.out.println(play.location.describe);
			encounter(play, rand, scan);
			if (isdead != true) {
				System.out.println("Which would you like to do:");
				System.out.println("(w)ait for an encounter, (l)eave");
				String actChoice = scan.next();
				if (actChoice.equals("l")) {
					move(play, map, scan);
				}
			}
		}
		System.out.println("GAME OVER!");
	}
}