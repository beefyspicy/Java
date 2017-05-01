import java.util.Random;

public class Mimic
{
	private static Random r = new Random();

	private static int mimicHP = 40;
	private static int mimicDG = r.nextInt(7) + 13;
	private static int mimicCredits = r.nextInt(10) + 50;

	private static String type = "Mimic";

	public static int mimicAttack()
	{
		return mimicDG;
	}

	public static int mimicDrop()
	{
		return mimicCredits;
	}

	public static void alterHP(int n)
	{
		mimicHP = mimicHP - n;
	}

	public static String getMimic()
	{
		return type;
	}

	public static int getHP()
	{
		return mimicHP;
	}
}