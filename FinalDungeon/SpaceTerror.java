/******************************************************
*SpaceTerror.java
*
*Programmer: Hayden Archambault
*Date Completed: 4-29-2017
*Version 2.0
*
*Program Description: SpaceTerror is a text based
*adventure/survival game. Read the attached text file
*for an extensive look at the game features.
*(ReadMeST.txt)
*******************************************************/

import java.util.Scanner;
import java.util.Random;

import java.io.*;
import sun.audio.*;

public class SpaceTerror
{
	private static Scanner keys = new Scanner(System.in);
	private static Random r = new Random();
	private static Hero hero = new Hero();
	private static Mimic mimic = new Mimic();

	private static int totalCredits = 0;

	private static boolean stop = false;


	public static void main(String[] args) throws Exception
	{
		int[] subzero = new int[10];

		String choice = "";
		String battle = "";

		int credits = 0;
		int input = 0;
		int index = 0;

		String backMusic = "DungeonSong.wav";
		InputStream in = new FileInputStream(backMusic);
		AudioStream audioStream = new AudioStream(in);
		AudioPlayer.player.start(audioStream);

		intro();

		while(true)
		{
			System.out.print("\nBrave the unknown dangers of the ship (m) view inventory (v) or check status (s): ");
			choice = keys.nextLine();
			if(choice.equals("m"))
			{
				int rand = r.nextInt(7);
				if(rand == 0 || rand == 1 || rand == 2 || rand == 3 || rand == 4 || rand == 5)
				{
					Enemy bad = new Enemy();
					String z = ("\nYou have encountered a " + bad.getEnemyType() + "!\n");
					char[] slowtext = z.toCharArray();
					for(int i = 0; i < slowtext.length; i++)
					{
						Thread.sleep(29);
						System.out.print(slowtext[i]);
					}
					while(bad.getHP() > 0 && hero.getHP() > 0)
					{
						battle = menu();

						if(battle.equals("f"))
						{
							int heroAttack = hero.Attack();
							String s7 = ("\nYou attack and do " + heroAttack + " points of damage.\n");
							slowtext = s7.toCharArray();
							for(int i = 0; i < slowtext.length; i++)
							{
								Thread.sleep(29);
								System.out.print(slowtext[i]);
							}
							bad.alterHP(heroAttack);

							if(bad.getHP() > 0)
							{
								int enemyAttack = bad.enemyAttack();
								String s8 = ("The creature fights back and does " + enemyAttack + " points of damage.\n\n. . . .\n");
								slowtext = s8.toCharArray();
								for(int i = 0; i < slowtext.length; i++)
								{
									Thread.sleep(29);
									System.out.print(slowtext[i]);
								}
								hero.alterHP(enemyAttack);

								System.out.println("\nYour remaining health: " + hero.getHP());
								System.out.println(bad.getEnemyType() + " health: " + bad.getHP());
							}
						}
						else if(battle.equals("g"))
						{
							if(hero.getGrenade() > 0)
							{
								int grenade = hero.Grenade();
								String s9 = ("\nYou throw a grenade and do " + grenade + " points of damage.\n");
								slowtext = s9.toCharArray();
								for(int i = 0; i < slowtext.length; i++)
								{
									Thread.sleep(29);
									System.out.print(slowtext[i]);
								}
								bad.alterHP(grenade);

								if(bad.getHP() > 0)
								{
									int enemyAttack = bad.enemyAttack();
									String s = ("The creature fights back and does " + enemyAttack + " points of damage.\n\n. . . .\n");
									slowtext = s.toCharArray();
									for(int i = 0; i < slowtext.length; i++)
									{
										Thread.sleep(29);
										System.out.print(slowtext[i]);
									}
									hero.alterHP(enemyAttack);

									System.out.println("Your remaining health: " + hero.getHP());
									System.out.println(bad.getEnemyType() + " health: " + bad.getHP());
								}
							}
							else
							{
								System.out.println("\nYou don't have any grenades!");
							}
						}
						else if(battle.equals("s"))
						{
							if(hero.getThermal() > 0)
							{
								int thermal = hero.thermalImploder();
								String s9 = ("\nYou deploy a Thermal Imploder and do " + thermal + " points of damage.\n");
								slowtext = s9.toCharArray();
								for(int i = 0; i < slowtext.length; i++)
								{
									Thread.sleep(29);
									System.out.print(slowtext[i]);
								}
								bad.alterHP(thermal);

								if(bad.getHP() > 0)
								{
									int enemyAttack = bad.enemyAttack();
									String s = ("The creature fights back and does " + enemyAttack + " points of damage.\n\n. . . .\n");
									slowtext = s.toCharArray();
									for(int i = 0; i < slowtext.length; i++)
									{
										Thread.sleep(29);
										System.out.print(slowtext[i]);
									}
									hero.alterHP(enemyAttack);

									System.out.println("Your remaining health: " + hero.getHP());
									System.out.println(bad.getEnemyType() + " health: " + bad.getHP());
								}
							}
							else
							{
								System.out.println("\nYou don't have any special items!");
							}
						}
						else if(battle.equals("h"))
						{
							heal();
						}
						else if(battle.equals("v"))
						{
							inventory();
						}
						else if(battle.equals("r"))
						{
							int run = r.nextInt(4);
							if(run == 0)
							{
								System.out.println("\nYou run for your life but lose the chance to collect any credits from your enemies.");
								break;
							}
							else
							{
								System.out.println("\nYou try to run but cannot escape!");
							}
						}
						else
						{
							System.out.println("\nMake a decision!");
						}

						//////////////////////////////// end of battle sequence

						if(bad.getHP() <= 0)
						{
							credits = bad.enemyDrop();
							totalCredits += credits;
							System.out.println("\n\u0007You killed the " + bad.getEnemyType() + " and gained " + credits + " credits.");
							System.out.println("You have " + hero.getHP() + " health remaining.");
							System.out.println("You have " + totalCredits + " total credits.");
							int pickups = r.nextInt(12);
							if(pickups == 0 || pickups == 1 || pickups == 2)
							{
								System.out.println("You found a grenade!");
								hero.addGrenade();
							}
							else if(pickups == 3 || pickups == 4 || pickups == 5)
							{
								System.out.println("You found a small health pack!");
								hero.addPotion();
							}
							else if(pickups == 6 || pickups == 7)
							{
								System.out.println("You found a large health pack!");
								hero.addLargePotion();
							}
							if(pickups == 8 || pickups == 9)
							{
								mimicFight();
							}

							subzero[index] = input;
							index++;
						}
					}
				}
				else
				{
					safe();
				}
			} //end if
			else if(choice.equals("s"))
			{
				status();
			}
			else if(choice.equals("v"))
			{
				inventory();
			}
			else
			{
				System.out.println("\nYou must make a choice.");
			}

			if(index == 5)
			{
				index++;
				getItem();
			}

			if(index == 10 && hero.getHP() > 0)
			{
				win();
////////////////
				AudioPlayer.player.stop(audioStream);
				break;
			}
			if(hero.getHP() <= 0)
			{
				lose();
/////////////////
				AudioPlayer.player.stop(audioStream);
				break;
			}
		} //end while
	}

	public static String menu() throws Exception
	{
		System.out.println("\nWhat will you do?");

		System.out.println(". fight (f)\n. throw grenade (g)\n. use health pack (h)\n. special item (s)\n. view inventory (v)\n. run away (r)");
		String input = keys.nextLine();

		String s1 = ("\n\n. . . .\n\n");
		char[] slowtext = s1.toCharArray();
		for(int i = 0; i < slowtext.length; i++)
		{
			Thread.sleep(59);
			System.out.print(slowtext[i]);
		}
		return input;
	}

	public static void win() throws Exception
	{
		String s5 = ("\nYou have reached an escape pod!\nYou managed to survive the terrors of the ship and collected " + totalCredits + " credits.\nYou have " + hero.getHP() + " points of health remaining.\n\n\u0007YOU WIN\n");
		char[] slowtext = s5.toCharArray();
		for(int i = 0; i < slowtext.length; i++)
		{
			Thread.sleep(69);
			System.out.print(slowtext[i]);
		}
	}

	public static void lose() throws Exception
	{
		String s4 = ("\nYou have died!\nYou had " + totalCredits + " credits...\nSomething terrible begins to devour your corpse.\n\nGAME OVER\u0007\n");
		char[] slowtext = s4.toCharArray();
		for(int i = 0; i < slowtext.length; i++)
		{
			Thread.sleep(75);
			System.out.print(slowtext[i]);
		}
	}

	public static void status()
	{
		System.out.println("\n. . . . . . . . . . . .");
		System.out.println(". Current Health: " + hero.getHP() + "\n. Total Credits: " + totalCredits);
		System.out.println(". . . . . . . . . . . .");
	}

	public static void getItem() throws Exception
	{
		String depot = ("\n. . . . .\n\nIt is quiet... You cautiously approach a blast door down the corridor...\nIt opens automatically.\nYou enter into what must have been the ships supply depot!\n\n. . . . .\n");
		char[] slowtext = depot.toCharArray();
		for(int i = 0; i < slowtext.length; i++)
		{
			Thread.sleep(49);
			System.out.print(slowtext[i]);
		}

		while(true)
		{
			String depot2 = ("\nChoose an updgrade:\n. Special (s)\n. First Aid Kit (f)\n. Grenade Pouch (g)\n");
			slowtext = depot2.toCharArray();
			for(int i = 0; i < slowtext.length; i++)
			{
				Thread.sleep(49);
				System.out.print(slowtext[i]);
			}
			String upgrade = keys.nextLine();
			if(upgrade.equals("s"))
			{
				hero.addThermal();
				String item3 = ("\nYou found a Thermal Imploder! Deploy for devastating effect.\n");
				slowtext = item3.toCharArray();
				for(int i = 0; i < slowtext.length; i++)
				{
					Thread.sleep(39);
					System.out.print(slowtext[i]);
				}
				break;
			}
			else if(upgrade.equals("f"))
			{
				hero.addLargePotion();
				hero.addLargePotion();
				String item = ("\nYou got two large health packs!\n");
				slowtext = item.toCharArray();
				for(int i = 0; i < slowtext.length; i++)
				{
					Thread.sleep(39);
					System.out.print(slowtext[i]);
				}
				break;
			}
			else if(upgrade.equals("g"))
			{
				hero.addGrenade();
				hero.addGrenade();
				hero.addGrenade();
				String item2 = ("\nYou got three grenades!\n");
				slowtext = item2.toCharArray();
				for(int i = 0; i < slowtext.length; i++)
				{
					Thread.sleep(39);
					System.out.print(slowtext[i]);
				}
				break;
			}
			else
			{
				System.out.println("\nYou must choose an upgrade.");
			}
		}
	}

	public static void safe() throws Exception
	{
		String a = ("\nYou are safe for now...\n");
		char[] slowtext = a.toCharArray();
		for(int i = 0; i < slowtext.length; i++)
		{
			Thread.sleep(29);
			System.out.print(slowtext[i]);
		}
	}
	public static void heal()
	{
		while(true)
		{
			System.out.print("\nSmall or large health pack (s or l)? ");
			String size = keys.nextLine();
			if(size.equals("s"))
			{
				hero.usePotion();
				break;
			}
			else if(size.equals("l"))
			{
				hero.useLargePotion();
				break;
			}
			else
			{
				System.out.println("You must choose a size.");
			}
		}
	}

	public static void intro() throws Exception
	{
		String s1 = ("You awaken in the infirmary of a strange vessel in the outer reaches of space.\nYou can not remember anything, yet the layout of the spacecraft seems familiar.\nYou hear screams from somewhere inside the ship.");
		char[] slowtext = s1.toCharArray();
		for(int i = 0; i < slowtext.length; i++)
		{
			Thread.sleep(59);
			System.out.print(slowtext[i]);
		}
		String s2 = (" Something is terribly wrong...\nYou must find your way to an escape pod!");
		slowtext = s2.toCharArray();
		for(int i = 0; i < slowtext.length; i++)
		{
			Thread.sleep(59);
			System.out.print(slowtext[i]);
		}
		String s3 = ("\nMore screams... It is dark... What will you do?\n");
		slowtext = s3.toCharArray();
		for(int i = 0; i < slowtext.length; i++)
		{
			Thread.sleep(99);
			System.out.print(slowtext[i]);
		}
	}

	public static void inventory()
	{
		System.out.println("\n. . . . . . . . . . . . .");
		System.out.println(". Grenades: " + hero.getGrenade());
		System.out.println(". Small health packs: " + hero.getPotions());
		System.out.println(". Large health packs: " + hero.getLargePotions());
		System.out.println(". Special Items: " + hero.getThermal());
		System.out.println(". . . . . . . . . . . . .");
	}

	public static void mimicFight() throws Exception
	{
		String f1 = ("\nYou have found a briefcase that seems to have something inside of it...");
		char[] slowtext = f1.toCharArray();
		for(int i = 0; i < slowtext.length; i++)
		{
			Thread.sleep(49);
			System.out.print(slowtext[i]);
		}
		int luck = r.nextInt(2);
		if(luck == 0)
		{
			String f2 = ("\nIt has a broken handle. Will you open it (y or n)? ");
			slowtext = f2.toCharArray();
			for(int i = 0; i < slowtext.length; i++)
			{
				Thread.sleep(49);
				System.out.print(slowtext[i]);
			}
			String open = keys.nextLine();
			if(open.equals("y"))
			{
				String bcase = ("\nThe briefcase explodes in your hands and you drop all of your items!\nYou have encountered a " + Mimic.getMimic() + "!");
				slowtext = bcase.toCharArray();
				for(int i = 0; i < slowtext.length; i++)
				{
					Thread.sleep(39);
					System.out.print(slowtext[i]);
				}

				while(true)
				{
					System.out.println("\n\nWhat will you do?");
					System.out.println(". fight (f)\n. run (r)");
					String fite = keys.nextLine();

					if(fite.equals("f"))
					{
						int heroAttack = hero.Attack();
						String s7 = ("\nYou attack and do " + heroAttack + " points of damage.\n");
						slowtext = s7.toCharArray();
						for(int i = 0; i < slowtext.length; i++)
						{
							Thread.sleep(29);
							System.out.print(slowtext[i]);
						}
						mimic.alterHP(heroAttack);

						if(mimic.getHP() > 0)
						{
							int enemyAttack = mimic.mimicAttack();
							String s8 = ("The creature fights back and does " + enemyAttack + " points of damage.\n\n. . . .\n");
							slowtext = s8.toCharArray();
							for(int i = 0; i < slowtext.length; i++)
							{
								Thread.sleep(29);
								System.out.print(slowtext[i]);
							}
							hero.alterHP(enemyAttack);

							System.out.println("\nYour remaining health: " + hero.getHP());
							System.out.println(mimic.getMimic() + " health: " + mimic.getHP());
						}
					}
					else if(fite.equals("r"))
					{
						System.out.println("\nYou can not escape the Mimic!");
					}
					else
					{
						System.out.println("\nYou must decide!");
					}

					if(mimic.getHP() <= 0)
					{
						int mcredits = mimic.mimicDrop();
						totalCredits += mcredits;
						System.out.println("\n\u0007You defeated the " + mimic.getMimic() + " and retrieved your lost items.");
						System.out.println("You found a Thermal Imploder!");
						hero.addThermal();
						System.out.println("You gained " + mcredits + " credits.");
						System.out.println("You have " + hero.getHP() + " health remaining.");
						System.out.println("You have " + totalCredits + " total credits.");
						break;
					}
					if(hero.getHP() <= 0)
					{
						break;
					}
				}//while
			}
			else if(open.equals("n"))
			{
				System.out.println("\nYou carefully put the briefcase back where you found it.");
			}
			else
			{
				System.out.println("You must decide what to do.");
			}
		}
		else if(luck == 1)
		{
			String f3 = ("\nIt is in good shape. Will you open it (y or n)? ");
			slowtext = f3.toCharArray();
			for(int i = 0; i < slowtext.length; i++)
			{
				Thread.sleep(49);
				System.out.print(slowtext[i]);
			}
			String nothing = keys.nextLine();
			if(nothing.equals("y"))
			{
				System.out.println("\nThere is nothing inside.");
			}
			else if(nothing.equals("n"))
			{
				System.out.println("\nYou carefully put the briefcase back where you found it.");
			}
			else
			{
				System.out.println("You must decide what to do.");
			}
		}
	}

	/*public static void song() throws Exception
	{
		String backMusic = "DungeonSong.wav";
		InputStream in = new FileInputStream(backMusic);
		AudioStream audioStream = new AudioStream(in);
		AudioPlayer.player.start(audioStream);

		if(stop == true)
		{
			AudioPlayer.player.stop(audioStream);
		}
	}*/
}