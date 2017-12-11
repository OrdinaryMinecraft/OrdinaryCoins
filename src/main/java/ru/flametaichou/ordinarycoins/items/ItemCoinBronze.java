package ru.flametaichou.ordinarycoins.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.flametaichou.ordinarycoins.OrdinaryCoinsBase;

public class ItemCoinBronze extends Item {

	public ItemCoinBronze() {
		setRegistryName("coinBronze");        // The unique name (within your mod) that identifies this item
		setUnlocalizedName(OrdinaryCoinsBase.MODID + ".coinBronze");     // Used for localization (en_US.lang)
		this.setCreativeTab(CreativeTabs.MISC);
		initModel();
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
