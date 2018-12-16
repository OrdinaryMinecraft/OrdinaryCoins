package ru.flametaichou.ordinarycoins.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ru.flametaichou.ordinarycoins.OrdinaryCoinsBase;

public class ItemCoinPlatinum extends Item {

    public ItemCoinPlatinum() {
        setRegistryName("coinPlatinum");
        setUnlocalizedName(OrdinaryCoinsBase.MODID + ".coinPlatinum");
        this.setCreativeTab(CreativeTabs.MISC);
    }
}
