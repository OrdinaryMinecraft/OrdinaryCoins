package ru.flametaichou.ordinarycoins;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class OrdinaryCoinsPremium extends CommandBase {
    private final List<String> aliases;

    public OrdinaryCoinsPremium() {
        aliases = new ArrayList<String>();
        aliases.add("premium");
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
        return "premium";
    }

    @Override
    public String getCommandUsage(ICommandSender var1) {
        return "/premium <repair>";
    }

    @Override
    public List<String> getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] argString) {
        World world = sender.getEntityWorld();

        if (!world.isRemote && ConfigHelper.premiumRepair) {
            if (argString.length == 0) {
                sender.addChatMessage(new ChatComponentText("/premium <repair>"));
                return;
            }

            if (argString[0].equals("repair")) {
                if (sender instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) sender;
                    InventoryPlayer inventory = player.inventory;

                    ItemStack item = player.getHeldItem();
                    if (item != null && item.isItemDamaged()) {
                        item.setItemDamage(0);
                        sender.addChatMessage(new ChatComponentTranslation("coins.repaired"));
                    } else {
                        sender.addChatMessage(new ChatComponentTranslation("coins.cantrepair"));
                    }

                }
                return;
            }
        }
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
