package ru.flametaichou.ordinarycoins.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ru.flametaichou.ordinarycoins.OrdinaryCoinsBase;

public class ItemCoinGold extends Item {

	public ItemCoinGold() {
		setRegistryName("coinGold");
		setUnlocalizedName(OrdinaryCoinsBase.MODID + ".coinGold");
		this.setCreativeTab(CreativeTabs.MISC);
	}
}
