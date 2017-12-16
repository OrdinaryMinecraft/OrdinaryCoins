package ru.flametaichou.ordinarycoins;

import net.minecraftforge.common.config.Configuration;

public class ConfigHelper {

	public ConfigHelper() {
		
	}
	
	public static int amountZombie;
	public static int amountSkeleton;
	public static int amountSpider;
	public static int amountEnderman;
	public static int amountCreeper;
	public static int amountWitch;
	public static int coinTypeZombie;
	public static int coinTypeSkeleton;
	public static int coinTypeSpider;
	public static int coinTypeEnderman;
	public static int coinTypeCreeper;
	public static int coinTypeWitch;
	public static int repairCost;
	public static int repairCoinType;
	public static boolean repair;
	public static boolean drop;
	
	
	public static void setupConfig(Configuration config) {
		try {
			config.load();
			drop = config.get("DropsAmount","drop",true,"Enable or disable coins drops from mobs").getBoolean(true);
			amountZombie = config.get("Drops Amount", "amountZombie", 3, "How many coins will drop from zombies").getInt(3);
			amountSkeleton = config.get("Drops Amount", "amountSkeleton", 3, "How many coins will drop from skeletons").getInt(3);
			amountSpider = config.get("Drops Amount", "amountSpider", 5, "How many coins will drop from spiders").getInt(5);
			amountEnderman = config.get("Drops Amount", "amountEnderman", 50, "How many coins will drop from endermen").getInt(50);
			amountCreeper = config.get("Drops Amount", "amountCreeper", 5, "How many coins will drop from creepers").getInt(5);
			amountWitch = config.get("Drops Amount", "amountWitch", 50, "How many coins will drop from witches").getInt(50);
			coinTypeZombie = config.get("Coins Type", "coinTypeZombie", 0, "Which coins use to drop from zombies? (0 - bronze, 1 - silver, 2 - gold)").getInt(0);
			coinTypeSkeleton = config.get("Coins Type", "coinTypeSkeleton", 0, "Which coins use to drop from skeletons? (0 - bronze, 1 - silver, 2 - gold)").getInt(0);
			coinTypeSpider = config.get("Coins Type", "coinTypeSpider", 0, "Which coins use to drop from spiders? (0 - bronze, 1 - silver, 2 - gold)").getInt(0);
			coinTypeEnderman = config.get("Coins Type", "coinTypeEnderman", 0, "Which coins use to drop from endermen? (0 - bronze, 1 - silver, 2 - gold)").getInt(0);
			coinTypeCreeper = config.get("Coins Type", "coinTypeCreeper", 0, "Which coins use to drop from creepers? (0 - bronze, 1 - silver, 2 - gold)").getInt(0);
			coinTypeWitch = config.get("Coins Type", "coinTypeWitch", 0, "Which coins use to drop from witches? (0 - bronze, 1 - silver, 2 - gold)").getInt(0);
			repair = config.get("Repairing","repair",true,"Enable or disable repairing items by coins").getBoolean(true);
			repairCost = config.get("Repairing", "repairCost", 50, "How much does the item repairing?").getInt(50);
			repairCoinType = config.get("Repairing", "repairCoinType", 1, "Which coins use to repairing? (0 - bronze, 1 - silver, 2 - gold)").getInt(0);
			
		} catch(Exception e) {
			System.out.println("Error loading config file!");
		} finally {
			if(config.hasChanged()) {
				config.save();
			}
		}
	}

}