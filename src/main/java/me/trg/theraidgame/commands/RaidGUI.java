package me.trg.theraidgame.commands;

import me.trg.theraidgame.RaidGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RaidGUI implements CommandExecutor {

    private final RaidGame plugin;
    public RaidGUI(RaidGame plugin){
        this.plugin=plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        Inventory rgui = Bukkit.createInventory(p,9, ChatColor.RED + "The Raid Game");

        ItemStack srmode = new ItemStack(Material.DRAGON_EGG);
        ItemStack endlessmode = new ItemStack(Material.WITHER_SKELETON_SKULL);
        ItemStack blank = new ItemStack(Material.LIME_STAINED_GLASS_PANE);

        ItemMeta srmodemeta = srmode.getItemMeta();
        srmodemeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Speedrun Mode");
        srmode.setItemMeta(srmodemeta);

        ItemMeta endlessmodemeta = endlessmode.getItemMeta();
        endlessmodemeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Endless Mode");
        endlessmode.setItemMeta(endlessmodemeta);

        ItemStack[] rgui_items = {blank,blank,srmode,blank,blank,blank,endlessmode,blank,blank};
        rgui.setContents(rgui_items);

        p.openInventory(rgui);

        return true;
    }
}
