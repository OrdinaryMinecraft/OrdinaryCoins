package ru.flametaichou.ordinarycoins;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import ru.flametaichou.ordinarycoins.items.ItemCoinBronze;
import ru.flametaichou.ordinarycoins.items.ItemCoinGold;
import ru.flametaichou.ordinarycoins.items.ItemCoinPlatinum;
import ru.flametaichou.ordinarycoins.items.ItemCoinSilver;

@Mod (modid = "ordinarycoins", name = "Ordinary Coins", version = "1.5")

public class OrdinaryCoinsBase {
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new OrdinaryCoinsCommands());
		event.registerServerCommand(new OrdinaryCoinsPremium());
	}
	
	@EventHandler
	public void initialize(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new MobDropHandler());
	}
	
	public static Item coinBronze;
	public static Item coinSilver;
	public static Item coinGold;
	public static Item coinPlatinum;
	
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event) {
		ConfigHelper.setupConfig(new Configuration(event.getSuggestedConfigurationFile()));
		
		coinBronze = new ItemCoinBronze().setUnlocalizedName("coinBronze").setTextureName("ordinarycoins:coinBronze");
		GameRegistry.registerItem(coinBronze, "coinBronze");

		coinSilver = new ItemCoinSilver().setUnlocalizedName("coinSilver").setTextureName("ordinarycoins:coinSilver");
		GameRegistry.registerItem(coinSilver, "coinSilver");

		coinGold = new ItemCoinGold().setUnlocalizedName("coinGold").setTextureName("ordinarycoins:coinGold");
		GameRegistry.registerItem(coinGold, "coinGold");

		coinPlatinum = new ItemCoinPlatinum().setUnlocalizedName("coinPlatinum").setTextureName("ordinarycoins:coinPlatinum");
		GameRegistry.registerItem(coinPlatinum, "coinPlatinum");
	}
	
}
