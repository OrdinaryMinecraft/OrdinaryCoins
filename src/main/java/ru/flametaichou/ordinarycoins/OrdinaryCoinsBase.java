package ru.flametaichou.ordinarycoins;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.flametaichou.ordinarycoins.items.ItemCoinBronze;
import ru.flametaichou.ordinarycoins.items.ItemCoinGold;
import ru.flametaichou.ordinarycoins.items.ItemCoinSilver;
import org.apache.logging.log4j.Logger;

@Mod (modid = OrdinaryCoinsBase.MODID, name = OrdinaryCoinsBase.MODNAME, version = OrdinaryCoinsBase.VERSION)

public class OrdinaryCoinsBase {

	public static final String MODID = "ordinarycoins";
	public static final String MODNAME = "Ordinary Coins";
	public static final String VERSION = "1.3";

	public static Logger logger;

	public static Item coinBronze;
	public static Item coinSilver;
	public static Item coinGold;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		coinBronze = new ItemCoinBronze().setUnlocalizedName("coinbronze");
		GameRegistry.register(coinBronze);
		coinSilver = new ItemCoinSilver().setUnlocalizedName("coinsilver");
		GameRegistry.register(coinSilver);
		coinGold = new ItemCoinGold().setUnlocalizedName("coingold");
		GameRegistry.register(coinGold);
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new OrdinaryCoinsCommands());
	}
	
	@EventHandler
	public void initialize(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new MobDropHandler());
	}

	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
		ConfigHelper.setupConfig(new Configuration(event.getSuggestedConfigurationFile()));
	}
	
}
