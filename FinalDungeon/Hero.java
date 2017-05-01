import java.util.Random;

public class Hero
{
	Random r = new Random();

	private int hp = 100;
	private int potions = 0;
	private int largePotions = 0;
	private int gold = 0;
	private int grenades = 0;
	private int grenadeDamage = 0;

	private int thermalImploder = 0;
	private int thermalDamage = 0;

	public int Attack()
	{
		return r.nextInt(15) + 25;
	}

	public void addGrenade()
	{
		grenades++;
	}

	public int Grenade()
	{
		if(grenades > 0)
		{
			grenades--;
			grenadeDamage = r.nextInt(13) + 37;
		}
		return grenadeDamage;
	}

	public int getGrenade()
	{
		return grenades;
	}

	public int thermalImploder()
	{
		if(thermalImploder > 0)
		{
			thermalImploder--;
			thermalDamage = r.nextInt(10) + 200;
		}
		return thermalDamage;
	}

	public void addThermal()
	{
		thermalImploder++;
	}

	public int getThermal()
	{
		return thermalImploder;
	}

	public void usePotion()
	{
		if(potions > 0)
		{
			System.out.println("You use a small health pack.");
			potions--;
			hp = hp + r.nextInt(10) + 8;
			System.out.println("You now have " + getHP() + " health.");
		}
		else
		{
			System.out.println("\nYou don't have any small health packs!");
		}
	}

	public void useLargePotion()
	{
		if(largePotions > 0)
		{
			System.out.println("You use a large health pack.");
			largePotions--;
			hp = hp + r.nextInt(30) + 20;
			System.out.println("You now have " + getHP() + " health.");
		}
		else
		{
			System.out.println("\nYou don't have any large health packs!");
		}
	}

	public void alterHP(int n)
	{
		hp = hp - n;
	}

	public void gainHP(int n)
	{
		hp = hp + n;
	}

	public int getHP()
	{
		return hp;
	}

	public void addPotion()
	{
		potions++;
	}

	public void addLargePotion()
	{
		largePotions++;
	}

	public int getPotions()
	{
		return potions;
	}

	public int getLargePotions()
	{
		return largePotions;
	}
}