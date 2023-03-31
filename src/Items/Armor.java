package Items;

public class Armor extends Item {
	public int def;

	public Armor(String name, int defense, String description) {
		super(name, description);
		def = defense;
	}
}