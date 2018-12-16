package ru.flametaichou.ordinarycoins;

import java.util.Random;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class MobDropHandler {

    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        if (ConfigHelper.drop) {
            final Random random = new Random();
            int count = 0;
            if (event.getSource().getTrueSource() instanceof EntityPlayer) {

                if (event.getEntity().getClass().toString().contains("Zombie")) {
                    count = random.nextInt(ConfigHelper.amountZombie) + 1;
                    ItemStack stack = new ItemStack(OrdinaryCoinsBase.coinBronze, count);
                    switch (ConfigHelper.coinTypeZombie) {
                        case 0:
                            stack = new ItemStack(OrdinaryCoinsBase.coinBronze, count);
                            break;
                        case 1:
                            stack = new ItemStack(OrdinaryCoinsBase.coinSilver, count);
                            break;
                        case 2:
                            stack = new ItemStack(OrdinaryCoinsBase.coinGold, count);
                            break;
                        case 3:
                            stack = new ItemStack(OrdinaryCoinsBase.coinPlatinum, count);
                            break;
                    }
                    EntityItem drop = new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
                    event.getDrops().add(drop);
                }

                if (event.getEntity().getClass().toString().contains("Skeleton")) {
                    count = random.nextInt(ConfigHelper.amountSkeleton) + 1;
                    ItemStack stack = new ItemStack(OrdinaryCoinsBase.coinBronze, count);
                    switch (ConfigHelper.coinTypeSkeleton) {
                        case 0:
                            stack = new ItemStack(OrdinaryCoinsBase.coinBronze, count);
                            break;
                        case 1:
                            stack = new ItemStack(OrdinaryCoinsBase.coinSilver, count);
                            break;
                        case 2:
                            stack = new ItemStack(OrdinaryCoinsBase.coinGold, count);
                            break;
                        case 3:
                            stack = new ItemStack(OrdinaryCoinsBase.coinPlatinum, count);
                            break;
                    }
                    EntityItem drop = new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
                    event.getDrops().add(drop);
                }

                if (event.getEntity().getClass().toString().contains("Spider")) {
                    count = random.nextInt(ConfigHelper.amountSpider) + 1;
                    ItemStack stack = new ItemStack(OrdinaryCoinsBase.coinBronze, count);
                    switch (ConfigHelper.coinTypeSpider) {
                        case 0:
                            stack = new ItemStack(OrdinaryCoinsBase.coinBronze, count);
                            break;
                        case 1:
                            stack = new ItemStack(OrdinaryCoinsBase.coinSilver, count);
                            break;
                        case 2:
                            stack = new ItemStack(OrdinaryCoinsBase.coinGold, count);
                            break;
                        case 3:
                            stack = new ItemStack(OrdinaryCoinsBase.coinPlatinum, count);
                            break;
                    }
                    EntityItem drop = new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
                    event.getDrops().add(drop);
                }

                if (event.getEntity().getClass().toString().contains("Enderman")) {
                    count = random.nextInt(ConfigHelper.amountEnderman) + 1;
                    ItemStack stack = new ItemStack(OrdinaryCoinsBase.coinBronze, count);
                    switch (ConfigHelper.coinTypeEnderman) {
                        case 0:
                            stack = new ItemStack(OrdinaryCoinsBase.coinBronze, count);
                            break;
                        case 1:
                            stack = new ItemStack(OrdinaryCoinsBase.coinSilver, count);
                            break;
                        case 2:
                            stack = new ItemStack(OrdinaryCoinsBase.coinGold, count);
                            break;
                        case 3:
                            stack = new ItemStack(OrdinaryCoinsBase.coinPlatinum, count);
                            break;
                    }
                    EntityItem drop = new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
                    event.getDrops().add(drop);
                }

                if (event.getEntity().getClass().toString().contains("Creeper")) {
                    count = random.nextInt(ConfigHelper.amountCreeper) + 1;
                    ItemStack stack = new ItemStack(OrdinaryCoinsBase.coinBronze, count);
                    switch (ConfigHelper.coinTypeCreeper) {
                        case 0:
                            stack = new ItemStack(OrdinaryCoinsBase.coinBronze, count);
                            break;
                        case 1:
                            stack = new ItemStack(OrdinaryCoinsBase.coinSilver, count);
                            break;
                        case 2:
                            stack = new ItemStack(OrdinaryCoinsBase.coinGold, count);
                            break;
                        case 3:
                            stack = new ItemStack(OrdinaryCoinsBase.coinPlatinum, count);
                            break;
                    }
                    EntityItem drop = new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
                    event.getDrops().add(drop);
                }

                if (event.getEntity().getClass().toString().contains("Witch")) {
                    count = random.nextInt(ConfigHelper.amountWitch) + 1;
                    ItemStack stack = new ItemStack(OrdinaryCoinsBase.coinBronze, count);
                    switch (ConfigHelper.coinTypeWitch) {
                        case 0:
                            stack = new ItemStack(OrdinaryCoinsBase.coinBronze, count);
                            break;
                        case 1:
                            stack = new ItemStack(OrdinaryCoinsBase.coinSilver, count);
                            break;
                        case 2:
                            stack = new ItemStack(OrdinaryCoinsBase.coinGold, count);
                            break;
                        case 3:
                            stack = new ItemStack(OrdinaryCoinsBase.coinPlatinum, count);
                            break;
                    }
                    EntityItem drop = new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, stack);
                    event.getDrops().add(drop);
                }

            }
        }
    }
}
