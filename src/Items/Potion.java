package Items;

import java.lang.Math;

import Creatures.Monster;
import Creatures.Player;

public class Potion extends Item {
	public int hp;
	public int def;
	public int dmg;

	public Potion(String name, int heal, int defboost, int dmgboost, String describe) {
		super(name, describe);
		hp = heal;
		def = defboost;
		dmg = dmgboost;
	}

	public Monster affectMon(Monster mon) {
		mon.hp += hp;
		mon.def += def;
		mon.dmg += dmg;
		if (hp < 0) {
			System.out.println("The " + mon.name + " takes " + Math.abs(hp) + " damage!");
		}
		if (hp > 0) {
			System.out.println("The " + mon.name + " gains " + hp + " health!");
		}
		if (def < 0) {
			System.out.println("The " + mon.name + " loses " + Math.abs(def) + " defense!");
		}
		if (def > 0) {
			System.out.println("The " + mon.name + " gains " + def + " defense!");
		}
		if (dmg < 0) {
			System.out.println("The " + mon.name + " now does " + Math.abs(dmg) + " less damage!");
		}
		if (dmg > 0) {
			System.out.println("The " + mon.name + " now does " + dmg + " more damage!");
		}
		if (dmg > 0 || def > 0 || hp > 0) {
			System.out.println("This may not have been a good idea...");
		}
		return mon;
	}

	public Player affectPlay(Player play) {
		play.hp += hp;
		play.defMod += def;
		play.dmgMod += dmg;
		if (hp < 0) {
			System.out.println("You take " + Math.abs(hp) + " damage!");
		}
		if (hp > 0) {
			System.out.println("You gain " + hp + " health!");
		}
		if (def < 0) {
			System.out.println("You lose " + Math.abs(def) + " defense!");
		}
		if (def > 0) {
			System.out.println("You gain " + def + " defense!");
		}
		if (dmg < 0) {
			System.out.println("You now do " + Math.abs(dmg) + " less damage!");
		}
		if (dmg > 0) {
			System.out.println("You now do " + dmg + " more damage!");
		}
		if (dmg < 0 || def < 0 || hp < 0) {
			System.out.println("This may not have been a good idea...");
		}
		return play;
	}
}