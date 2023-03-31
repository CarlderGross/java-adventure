package Meta;

import Creatures.Monster;

public class Location {
	public String name;
	public String describe;
	public Monster[] mons;
	public boolean hasMerch;

	public Location(String place, Monster[] encounters, boolean merchs, String descr) {
		name = place;
		describe = descr;
		mons = encounters;
		hasMerch = merchs;
	}
}