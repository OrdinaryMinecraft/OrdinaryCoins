package ru.flametaichou.ordinarycoins;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import ru.flametaichou.ordinarycoins.items.ItemCoinBronze;
import ru.flametaichou.ordinarycoins.items.ItemCoinGold;
import ru.flametaichou.ordinarycoins.items.ItemCoinPlatinum;
import ru.flametaichou.ordinarycoins.items.ItemCoinSilver;

public class OrdinaryCoinsCommands extends CommandBase {

    private final List<String> aliases;

    public OrdinaryCoinsCommands() {
        aliases = new ArrayList<String>();
        aliases.add("coins");
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public String getCommandName() {
        return "coins";
    }

    @Override
    public String getCommandUsage(ICommandSender var1) {
        return "coins <stack/unstack/repair>";
    }

    @Override
    public List<String> getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] argString) {
        World world = sender.getEntityWorld();

        if (!world.isRemote) {
            if (argString.length == 0) {
                sender.addChatMessage(new ChatComponentText("/coins <stack/unstack>"));
                return;
            }
            if (argString[0].equals("stack")) {
                if (sender instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) sender;
                    InventoryPlayer inventory = player.inventory;
                    int countBronze = 0;
                    int countSilver = 0;
                    int countGold = 0;
                    for (ItemStack s : inventory.mainInventory) {
                        if (s != null && s.getItem() instanceof ItemCoinBronze) {
                            countBronze = countBronze + s.stackSize;
                        }
                        if (s != null && s.getItem() instanceof ItemCoinSilver) {
                            countSilver = countSilver + s.stackSize;
                        }
                        if (s != null && s.getItem() instanceof ItemCoinGold) {
                            countGold = countGold + s.stackSize;
                        }
                    }
                    inventory.clearInventory(OrdinaryCoinsBase.coinBronze, 0);
                    inventory.clearInventory(OrdinaryCoinsBase.coinSilver, 0);
                    inventory.clearInventory(OrdinaryCoinsBase.coinGold, 0);
                    inventory.addItemStackToInventory(new ItemStack(OrdinaryCoinsBase.coinSilver, countBronze / ConfigHelper.coinsStackSize));
                    inventory.addItemStackToInventory(new ItemStack(OrdinaryCoinsBase.coinBronze, countBronze % ConfigHelper.coinsStackSize));
                    inventory.addItemStackToInventory(new ItemStack(OrdinaryCoinsBase.coinGold, countSilver / ConfigHelper.coinsStackSize));
                    inventory.addItemStackToInventory(new ItemStack(OrdinaryCoinsBase.coinSilver, countSilver % ConfigHelper.coinsStackSize));
                    inventory.addItemStackToInventory(new ItemStack(OrdinaryCoinsBase.coinPlatinum, countGold / ConfigHelper.coinsStackSize));
                    inventory.addItemStackToInventory(new ItemStack(OrdinaryCoinsBase.coinGold, countGold % ConfigHelper.coinsStackSize));
                    sender.addChatMessage(new ChatComponentTranslation("coins.stacked"));
                }
                return;
            }

            if (argString[0].equals("unstack")) {
                if (sender instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) sender;
                    InventoryPlayer inventory = player.inventory;
                    int countPlatinum = 0;
                    int countGold = 0;
                    int countSilver = 0;
                    for (ItemStack s : inventory.mainInventory) {
                        if (s != null && s.getItem() instanceof ItemCoinPlatinum) {
                            countPlatinum = countPlatinum + s.stackSize;
                        }
                        if (s != null && s.getItem() instanceof ItemCoinGold) {
                            countGold = countGold + s.stackSize;
                        }
                        if (s != null && s.getItem() instanceof ItemCoinSilver) {
                            countSilver = countSilver + s.stackSize;
                        }
                    }
                    inventory.clearInventory(OrdinaryCoinsBase.coinPlatinum, 0);
                    inventory.clearInventory(OrdinaryCoinsBase.coinGold, 0);
                    inventory.clearInventory(OrdinaryCoinsBase.coinSilver, 0);

                    if (countPlatinum > 0) {
                        ItemStack is = new ItemStack(OrdinaryCoinsBase.coinGold, countPlatinum * ConfigHelper.coinsStackSize);
                        //inventory.addItemStackToInventory(is);
                        spawnEntityItemInWorld(is, player.worldObj, player.posX, player.posY, player.posZ);
                    }
                    if (countGold > 0) {
                        ItemStack is = new ItemStack(OrdinaryCoinsBase.coinSilver, countGold * ConfigHelper.coinsStackSize);
                        //inventory.addItemStackToInventory(is);
                        spawnEntityItemInWorld(is, player.worldObj, player.posX, player.posY, player.posZ);
                    }
                    if (countSilver > 0) {
                        ItemStack is = new ItemStack(OrdinaryCoinsBase.coinBronze, countSilver * ConfigHelper.coinsStackSize);
                        //inventory.addItemStackToInventory(is);
                        spawnEntityItemInWorld(is, player.worldObj, player.posX, player.posY, player.posZ);
                    }

                    sender.addChatMessage(new ChatComponentTranslation("coins.unstacked"));
                }
                return;
            }

            if (argString[0].equals("repair")) {
                if (argString.length < 2 && ConfigHelper.playersRepair) {

                    if (sender instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer) sender;
                        InventoryPlayer inventory = player.inventory;

                        Item coin = pickCoinFromConfig();

                        int countCoins = 0;
                        for (ItemStack s : inventory.mainInventory) {
                            if (s != null && s.getItem() == coin) countCoins = countCoins + s.stackSize;
                        }
                        ItemStack item = player.getHeldItem();
                        if (countCoins >= ConfigHelper.repairCost) {
                            if (item != null && item.isItemDamaged()) {
                                inventory.clearInventory(coin, 0);
                                item.setItemDamage(0);
                                sender.addChatMessage(new ChatComponentTranslation("coins.repaired"));
                                inventory.addItemStackToInventory(new ItemStack(coin, countCoins - ConfigHelper.repairCost));
                                player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "random.anvil_use", 1.1F, 1.1F);
                            } else {
                                sender.addChatMessage(new ChatComponentTranslation("coins.cantrepair"));
                            }
                        } else {
                            switch (ConfigHelper.repairCoinType) {
                                case 0:
                                    sender.addChatMessage(new ChatComponentTranslation("coins.notenough.bronze", ConfigHelper.repairCost));
                                    break;
                                case 1:
                                    sender.addChatMessage(new ChatComponentTranslation("coins.notenough.silver", ConfigHelper.repairCost));
                                    break;
                                case 2:
                                    sender.addChatMessage(new ChatComponentTranslation("coins.notenough.gold", ConfigHelper.repairCost));
                                    break;
                                case 3:
                                    player.addChatMessage(new ChatComponentTranslation("coins.notenough.platinum", ConfigHelper.repairCost));
                                    break;
                            }
                        }
                    }
                    return;
                } else {

                    if (!(sender instanceof EntityPlayer)) {
                        EntityPlayer player = world.getPlayerEntityByName(argString[1]);
                        InventoryPlayer inventory = player.inventory;

                        Item coin = pickCoinFromConfig();

                        int countCoins = 0;
                        for (ItemStack s : inventory.mainInventory) {
                            if (s != null && s.getItem() == coin) countCoins = countCoins + s.stackSize;
                        }
                        ItemStack item = player.getHeldItem();
                        if (countCoins >= ConfigHelper.repairCost) {
                            if (item != null && item.isItemDamaged()) {
                                inventory.clearInventory(coin, 0);
                                item.setItemDamage(0);
                                player.addChatMessage(new ChatComponentTranslation("coins.repaired"));
                                inventory.addItemStackToInventory(new ItemStack(coin, countCoins - ConfigHelper.repairCost));
                                player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "random.anvil_use", 1.1F, 1.1F);
                            } else {
                                player.addChatMessage(new ChatComponentTranslation("coins.cantrepair"));
                            }
                        } else {
                            switch (ConfigHelper.repairCoinType) {
                                case 0:
                                    player.addChatMessage(new ChatComponentTranslation("coins.notenough.bronze", ConfigHelper.repairCost));
                                    break;
                                case 1:
                                    player.addChatMessage(new ChatComponentTranslation("coins.notenough.silver", ConfigHelper.repairCost));
                                    break;
                                case 2:
                                    player.addChatMessage(new ChatComponentTranslation("coins.notenough.gold", ConfigHelper.repairCost));
                                    break;
                                case 3:
                                    player.addChatMessage(new ChatComponentTranslation("coins.notenough.platinum", ConfigHelper.repairCost));
                                    break;
                            }
                        }
                    }
                    return;
                }
            }
        }
    }

    private void spawnEntityItemInWorld(ItemStack is, World world, Double x, Double y, Double z) {
        EntityItem loot = new EntityItem(world, x, y, z, is);
        world.spawnEntityInWorld(loot);
    }

    private Item pickCoinFromConfig() {
        Item coin = OrdinaryCoinsBase.coinSilver;
        switch (ConfigHelper.repairCoinType) {
            case 0:
                coin = OrdinaryCoinsBase.coinBronze;
                break;
            case 1:
                coin = OrdinaryCoinsBase.coinSilver;
                break;
            case 2:
                coin = OrdinaryCoinsBase.coinGold;
                break;
            case 3:
                coin = OrdinaryCoinsBase.coinPlatinum;
                break;
        }
        return coin;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender var1) {
        return true;
    }

    @Override
    public List<?> addTabCompletionOptions(ICommandSender var1, String[] var2) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] var1, int var2) {
        return false;
    }
}
