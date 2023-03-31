package Creatures;

import java.util.Random;

public class Monster extends Creature {
	public int dmg;
	public int def;
	public String describe;
	int gold;
	int xp;

	public Monster(String called, int health, int pain, int defe, int mun, int skill, String description) {
		super(called, health);
		dmg = pain;
		def = defe;
		describe = description;
		gold = mun;
		xp = skill;
	}

	public int attack(Player play, Random rand) {
		System.out.println("The " + name + " attacks you!");
		int roll = rand.nextInt(2);
		if (roll > 0) {
			System.out.println("The attack hits!");
			int atkDmg = dmg - play.armor.def;
			if (atkDmg <= 0) {
				System.out.println("The " + name + " couldn't penetrate your " + play.armor.name + ".");
			} else {
				System.out.println("The " + name + " did " + atkDmg + " damage!");
				play.hp -= atkDmg;
				System.out.println("You now have " + play.hp + " health.");
			}
		} else {
			System.out.println("The attack misses!");
		}
		return play.hp;
	}

	public Player loot(Player play, Random rand) {
		int cash = rand.nextInt(gold) + 1;
		play.gold += cash;
		int exp = rand.nextInt(xp) + 1;
		play.xp += exp;
		return play;
	}
}