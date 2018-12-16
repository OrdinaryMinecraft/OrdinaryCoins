package ru.flametaichou.ordinarycoins;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import ru.flametaichou.ordinarycoins.items.ItemCoinBronze;
import ru.flametaichou.ordinarycoins.items.ItemCoinGold;
import ru.flametaichou.ordinarycoins.items.ItemCoinSilver;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(OrdinaryCoinsBase.coinBronze, 0, new ModelResourceLocation(OrdinaryCoinsBase.coinBronze.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(OrdinaryCoinsBase.coinSilver, 0, new ModelResourceLocation(OrdinaryCoinsBase.coinSilver.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(OrdinaryCoinsBase.coinGold, 0, new ModelResourceLocation(OrdinaryCoinsBase.coinGold.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(OrdinaryCoinsBase.coinPlatinum, 0, new ModelResourceLocation(OrdinaryCoinsBase.coinPlatinum.getRegistryName(), "inventory"));
    }
}