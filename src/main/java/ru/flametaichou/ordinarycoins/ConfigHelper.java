package ru.flametaichou.ordinarycoins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class ConfigHelper {

	private static Configuration config;

	public static int repairCost;
	public static int repairCoinType;
	public static int coinsStackSize;
	public static boolean repair;
	public static boolean drop;

	private static Map<Class, Integer> mobCoinAmounts = new HashMap<Class, Integer>();
	private static Map<Class, Integer> mobCoinTypes = new HashMap<Class, Integer>();

	public ConfigHelper() {
	}

	public static void setConfiguration(Configuration configuration) {
		config = configuration;
	}
	
	public static void setupConfig() {
		try {
			config.load();
			loadEntitiesData(config);

			drop = config.get("Main","drop",true,"Enable or disable coins drops from mobs").getBoolean(true);
			repair = config.get("Repairing","repair",true,"Enable or disable repairing items by coins").getBoolean(true);
			repairCost = config.get("Repairing", "repairCost", 50, "How much does the item repairing?").getInt(50);
			repairCoinType = config.get("Repairing", "repairCoinType", 1, "Which coins use to repairing? (0 - bronze, 1 - silver, 2 - gold, 3 - platinum)").getInt(0);
			coinsStackSize = config.get("Main", "coinsStackSize", 100, "How many coins should be in a stack? (100 means that 1 silver coin = 100 bronze coins, 1 gold = 100 silver)").getInt(100);

		} catch(Exception e) {
			System.out.println("[Ordinary Coins] Error occurred on loading config file!");
			e.printStackTrace();
		} finally {
			if(config.hasChanged()) {
				config.save();
			}
		}
	}

	private static void loadEntitiesData(Configuration config) {
		for (ResourceLocation resourceLocation : EntityList.getEntityNameList()) {
			if (resourceLocation == EntityList.LIGHTNING_BOLT) {
				continue;
			}

			EntityEntry entry = ForgeRegistries.ENTITIES.getValue(resourceLocation);
			if (entry == null) {
				System.out.println("[Ordinary Coins] Can't find entityEntry for entity: " + resourceLocation.getResourcePath());
				continue;
			}

			String entityName = getEntityName(entry);
			Class entityClass = entry.getEntityClass();
			if (entityClass == null) {
				System.out.println("[Ordinary Coins] Can't find entityClass for entry: " + entityName);
				continue;
			}

			try {
				if (Modifier.isAbstract(entityClass.getModifiers()) || entityClass == EntityArmorStand.class) {
					// Skip classes like EntityLiving, EntityMob, EntityArmorStand
					continue;
				}
				if (isEntityLiving(entityClass)) {
					int defaultAmount = 0;
					if (entityClass == EntityZombie.class) {
						defaultAmount = 3;

					} else if (entityClass == EntitySkeleton.class) {
						defaultAmount = 3;

					} else if (entityClass == EntitySpider.class) {
						defaultAmount = 5;

					} else if (entityClass == EntityEnderman.class) {
						defaultAmount = 50;

					} else if (entityClass == EntityCreeper.class) {
						defaultAmount = 5;

					} else if (entityClass == EntityWitch.class) {
						defaultAmount = 50;

					}
					int amount = config.get(
							"Drops Amount",
							"amount" + entityName,
							defaultAmount,
							"How many coins will drop from " + entityName.toLowerCase()
					).getInt(defaultAmount);
					mobCoinAmounts.put(entityClass, amount);

					int defaultCoinType = 0;
					int coinType = config.get(
							"Coins Type",
							"coinType" + entityName,
							defaultCoinType,
							"Which coins use to drop from " + entityName.toLowerCase() + "? (0 - bronze, 1 - silver, 2 - gold, 3 - platinum)"
					).getInt(defaultCoinType);
					mobCoinTypes.put(entityClass, coinType);
				}
			} catch (Exception e) {
				System.out.println("[Ordinary Coins] Error occurred on configuring entity: " + entityClass.getName());
				e.printStackTrace();
			}
		}
	}

	public static int getCoinsAmount(Class entityClass) {
		return (mobCoinAmounts.get(entityClass) != null) ? mobCoinAmounts.get(entityClass) : 0;
	}

	public static int getCoinsType(Class entityClass) {
		return (mobCoinTypes.get(entityClass) != null) ? mobCoinTypes.get(entityClass) : 0;
	}

	private static boolean isEntityLiving(Class entityClass) {
		Class parent = entityClass.getSuperclass();
		while (parent != null) {
			if (EntityLivingBase.class == parent) {
				return true;
			}

			if (Entity.class == parent) {
				return false;
			}

			parent = parent.getSuperclass();
		}
		return false;
	}

	private static String getEntityName(EntityEntry entry) {
		String s = entry.getName();
		s = s.substring(0, 1).toUpperCase() + s.substring(1);
		s = s.replace(" ", "");
		return s;
	}
}