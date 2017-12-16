package ru.flametaichou.ordinarycoins.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.flametaichou.ordinarycoins.OrdinaryCoinsBase;

public class ItemCoinSilver extends Item {

    public ItemCoinSilver() {
        setRegistryName("coinSilver");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(OrdinaryCoinsBase.MODID + ".coinSilver");     // Used for localization (en_US.lang)
        this.setCreativeTab(CreativeTabs.MISC);
    }
}
