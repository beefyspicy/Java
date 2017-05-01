import java.util.Random;

public class Enemy
{
	private Random r = new Random();

	private String type = "";
	private int enemyHP = 0;
	private int enemyDG = 0;
	private int enemyCredits = 0;

	public Enemy()
	{
		int m = r.nextInt(4);
		if(m == 0)
		{
			type = "Frightful Abnormality";
			enemyHP = 25;
		}
		else if(m == 1)
		{
			type = "Bizarre Serpent";
			enemyHP = 35;
		}
		else if(m == 2)
		{
			type = "Cosmic Horror";
			enemyHP = 45;
		}
		else if(m == 3)
		{
			type = "Forgotten Evil";
			enemyHP = 55;
		}
	}

	public int enemyAttack()
	{
		if(type.equals("Frightful Abnormality"))
		{
			enemyDG = r.nextInt(7) + 9;
		}
		else if(type.equals("Bizarre Serpent"))
		{
			enemyDG = r.nextInt(7) + 13;
		}
		else if(type.equals("Cosmic Horror"))
		{
			enemyDG = r.nextInt(7) + 15;
		}
		else if(type.equals("Forgotten Evil"))
		{
			enemyDG = r.nextInt(7) + 17;
		}
		return enemyDG;
	}

	public int enemyDrop()
	{
		if(type.equals("Frightful Abnormality"))
		{
			enemyCredits = r.nextInt(5) + 5;
		}
		else if(type.equals("Bizarre Serpent"))
		{
			enemyCredits = r.nextInt(7) + 9;
		}
		else if(type.equals("Cosmic Horror"))
		{
			enemyCredits = r.nextInt(9) + 11;
		}
		else if(type.equals("Forgotten Evil"))
		{
			enemyCredits = r.nextInt(9) + 25;
		}
		return enemyCredits;
	}

	public int getCredits()
	{
		return enemyCredits;
	}

	public String getEnemyType()
	{
		return type;
	}

	public int getDG()
	{
		return enemyDG;
	}

	public void alterHP(int n)
	{
		enemyHP = enemyHP - n;
	}

	public int getHP()
	{
		return enemyHP;
	}
}