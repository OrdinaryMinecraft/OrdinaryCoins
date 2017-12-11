package ru.flametaichou.ordinarycoins.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.flametaichou.ordinarycoins.OrdinaryCoinsBase;

public class ItemCoinGold extends Item {

	public ItemCoinGold() {
		setRegistryName("coingold");
		setUnlocalizedName(OrdinaryCoinsBase.MODID + ".coinGold");
		this.setCreativeTab(CreativeTabs.MISC);
		initModel();
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
