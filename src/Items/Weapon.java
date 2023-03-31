package Items;

public class Weapon extends Item {
	public int dmg;

	public Weapon(String name, int pain, String descrip) {
		super(name, descrip);
		dmg = pain;
	}
}