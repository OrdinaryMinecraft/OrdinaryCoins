package ru.flametaichou.ordinarycoins.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ru.flametaichou.ordinarycoins.OrdinaryCoinsBase;

public class ItemCoinBronze extends Item {

	public ItemCoinBronze() {
		setRegistryName("coinBronze");
		setUnlocalizedName(OrdinaryCoinsBase.MODID + ".coinBronze");
		this.setCreativeTab(CreativeTabs.MISC);
	}
}
