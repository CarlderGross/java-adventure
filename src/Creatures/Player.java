package Creatures;

import java.util.Random;

import Items.Armor;
import Items.Weapon;
import Items.Potion;

import Meta.Location;

public class Player extends Creature {
	public Armor armor;
	public Weapon weapon;
	public Potion potion;
	public int dmgMod = 0;
	public int defMod = 0;
	public int gold = 0;
	int xp = 0;
	int lvl = 0;
	public Location location;

	public Player(int hp, Armor protect, Weapon hurts, Potion pot, Location place) {
		super("Player", hp);
		armor = protect;
		weapon = hurts;
		potion = pot;
		location = place;
	}

	public int attack(Monster mon, Random rand) {
		System.out.println("You attack the " + mon.name + " with your " + weapon.name + "!");
		int roll = rand.nextInt(2);
		if (roll > 0) {
			System.out.println("The attack hits!");
			int atkDmg = (weapon.dmg + dmgMod) - mon.def;
			if (atkDmg <= 0) {
				System.out.println("You couldn't hurt the " + mon.name + "!");
			} else {
				System.out.println("You did " + atkDmg + " damage!");
				mon.hp -= atkDmg;
				System.out.println("The " + mon.name + " now has " + mon.hp + " health.");
			}
		} else {
			System.out.println("You missed!");
		}
		return mon.hp;
	}

	public void lvlCheck() {
		while (xp >= (lvl + 1) * 100) {
			System.out.println("LEVEL UP!!!");
			xp -= (lvl + 1) * 100;
			lvl++;
			System.out.println("You are now level " + lvl + "!");
			dmgMod = lvl;
			defMod = lvl;
			int dmgTake = 100 - hp;
			hp = 100 + lvl * 50 - dmgTake;
		}
	}
}