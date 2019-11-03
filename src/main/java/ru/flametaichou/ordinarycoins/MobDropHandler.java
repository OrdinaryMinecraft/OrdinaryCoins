package ru.flametaichou.ordinarycoins;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class MobDropHandler {

    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        final Random random = new Random();
        if (event.source.getSourceOfDamage() instanceof EntityPlayer) {

            if (ConfigHelper.getCoinsAmount(event.entity.getClass()) > 0) {
                int count = random.nextInt(ConfigHelper.getCoinsAmount(event.entity.getClass())) + 1;

                while (count > 0) {
                    int toDrop = count;
                    if (count > 64) {
                        toDrop = 64;
                    }
                    count = count - toDrop;

                    ItemStack stack = new ItemStack(OrdinaryCoinsBase.coinBronze, toDrop);
                    switch (ConfigHelper.getCoinsType(event.entity.getClass())) {
                        case 0:
                            stack = new ItemStack(OrdinaryCoinsBase.coinBronze, toDrop);
                            break;
                        case 1:
                            stack = new ItemStack(OrdinaryCoinsBase.coinSilver, toDrop);
                            break;
                        case 2:
                            stack = new ItemStack(OrdinaryCoinsBase.coinGold, toDrop);
                            break;
                        case 3:
                            stack = new ItemStack(OrdinaryCoinsBase.coinPlatinum, toDrop);
                            break;
                    }
                    EntityItem drop = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, stack);
                    event.drops.add(drop);
                }
            }
        }
    }
}
