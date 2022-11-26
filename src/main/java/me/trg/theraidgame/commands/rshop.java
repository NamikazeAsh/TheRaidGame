package me.trg.theraidgame.commands;

import me.trg.theraidgame.RaidGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class rshop implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        Inventory raidshop = Bukkit.createInventory(p,9*6, ChatColor.LIGHT_PURPLE + "Raid Shop");

        ItemStack gapp = new ItemStack(Material.GOLDEN_APPLE);
        ItemStack strengthpot = new ItemStack(Material.POTION);
        ItemStack blank = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemStack remmob = new ItemStack(Material.DEAD_BUSH);
        ItemStack speedpot = new ItemStack(Material.POTION);
        ItemStack fireres = new ItemStack(Material.POTION);
        ItemStack ep = new ItemStack(Material.ENDER_PEARL);
        ItemStack wood = new ItemStack(Material.OAK_LOG);
        ItemStack opaxe = new ItemStack(Material.DIAMOND_AXE);
        ItemStack opsword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack nfor = new ItemStack(Material.NETHER_BRICK);
        ItemStack fstick = new ItemStack(Material.STICK);
        ItemStack tot = new ItemStack(Material.TOTEM_OF_UNDYING);
        
        ItemMeta gappmeta = gapp.getItemMeta();
        ArrayList<String> gapplore = new ArrayList<>();
        gapplore.add(ChatColor.BOLD + "10$");
        gappmeta.setLore(gapplore);
        gappmeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Golden Apple");
        gapp.setItemMeta(gappmeta);

        ItemMeta strengthpotmeta = strengthpot.getItemMeta();
        ArrayList<String> strengthpotlore = new ArrayList<>();
        strengthpotlore.add(ChatColor.BOLD + "20$");
        strengthpotmeta.setLore(strengthpotlore);
        strengthpotmeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Strength Potion");
        PotionMeta strengthpotmetashop = (PotionMeta) strengthpot.getItemMeta();
        PotionEffect strshop = new PotionEffect(PotionEffectType.INCREASE_DAMAGE,0,0);
        strengthpotmetashop.addCustomEffect(strshop,true);
        strengthpot.setItemMeta(strengthpotmeta);

        ItemMeta remmobmeta = remmob.getItemMeta();
        ArrayList<String> remmoblore = new ArrayList<>();
        remmoblore.add(ChatColor.BOLD + "30$");
        remmobmeta.setLore(remmoblore);
        remmobmeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Remove Mobs");
        remmob.setItemMeta(remmobmeta);

        ItemMeta speedpotmeta = speedpot.getItemMeta();
        ArrayList<String> speedpotlore = new ArrayList<>();
        speedpotlore.add(ChatColor.BOLD + "15$");
        speedpotmeta.setLore(speedpotlore);
        speedpotmeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Speed Potion");
        PotionMeta speedpotmetashop = (PotionMeta) speedpot.getItemMeta();
        PotionEffect spdshop = new PotionEffect(PotionEffectType.SPEED,0,0);
        speedpotmetashop.addCustomEffect(spdshop,true);
        speedpot.setItemMeta(speedpotmeta);

        ItemMeta fireresmeta = fireres.getItemMeta();
        ArrayList<String> firereslore = new ArrayList<>();
        firereslore.add(ChatColor.BOLD + "5$");
        fireresmeta.setLore(firereslore);
        fireresmeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Fire Resistance Potion");
        PotionMeta fireresmetashop = (PotionMeta) fireres.getItemMeta();
        PotionEffect fireresshop = new PotionEffect(PotionEffectType.FIRE_RESISTANCE,0,0);
        fireresmetashop.addCustomEffect(fireresshop,true);
        fireres.setItemMeta(fireresmeta);

        ItemMeta epmeta = ep.getItemMeta();
        ArrayList<String> eplore = new ArrayList<>();
        eplore.add(ChatColor.BOLD + "10$");
        epmeta.setLore(eplore);
        epmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Ender Pearl");
        ep.setItemMeta(epmeta);

        ItemMeta woodmeta = wood.getItemMeta();
        ArrayList<String> woodlore = new ArrayList<>();
        woodlore.add(ChatColor.WHITE + "8 Logs of Oak.");
        woodlore.add(ChatColor.BOLD + "5$");
        woodmeta.setLore(woodlore);
        woodmeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Wood");
        wood.setItemMeta(woodmeta);

        ItemMeta opaxemeta = opaxe.getItemMeta();
        ArrayList<String> opaxelore = new ArrayList<>();
        opaxelore.add(ChatColor.BOLD + "8$");
        opaxemeta.setLore(opaxelore);
        opaxemeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "OP Axe");
        opaxe.setItemMeta(opaxemeta);

        ItemMeta opswordmeta = opsword.getItemMeta();
        ArrayList<String> opswordlore = new ArrayList<>();
        opswordlore.add(ChatColor.BOLD + "8$");
        opswordmeta.setLore(opswordlore);
        opswordmeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "OP Sword");
        opsword.setItemMeta(opswordmeta);

        ItemMeta nformeta = nfor.getItemMeta();
        ArrayList<String> nforlore = new ArrayList<>();
        nforlore.add(ChatColor.BOLD + "12$");
        nformeta.setLore(nforlore);
        nformeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Nether Fortress Locator");
        nfor.setItemMeta(nformeta);

        ItemMeta fstickmeta = fstick.getItemMeta();
        ArrayList<String> fsticklore = new ArrayList<>();
        fsticklore.add(ChatColor.BOLD + "10$");
        fstickmeta.setLore(fsticklore);
        fstickmeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Fire Touch");
        fstick.setItemMeta(fstickmeta);

        ItemMeta totmeta = tot.getItemMeta();
        ArrayList<String> totlore = new ArrayList<>();
        totlore.add(ChatColor.BOLD + "10$");
        totmeta.setLore(totlore);
        totmeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Totem of Undying");
        tot.setItemMeta(totmeta);

        ItemStack[] raidshop_items = {blank,blank,blank,blank,blank,blank,blank,blank,blank
                                    ,blank,blank,blank,gapp,remmob,ep,blank,blank,blank
                                    ,blank,blank,blank,speedpot,fireres,strengthpot,blank,blank,blank
                                    ,blank,blank,blank,wood,opsword,opaxe,blank,blank,blank
                                    ,blank,blank,blank,nfor,fstick,tot,blank,blank,blank
                                    ,blank,blank,blank,blank,blank,blank,blank,blank,blank};

        raidshop.setContents(raidshop_items);

        p.openInventory(raidshop);
        
        return true;
    }

    @EventHandler
    public void shopclick(InventoryClickEvent click){

        Player p = (Player) click.getWhoClicked();

        if (click.getView().getTitle().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Raid Shop")) {

            switch (Objects.requireNonNull(click.getCurrentItem()).getType()) {
                case GOLDEN_APPLE:
                    if(RaidGame.moneydb.get(p.getDisplayName()) >=10) {
                        ItemStack igapp = new ItemStack(Material.GOLDEN_APPLE, 1);
                        int pm = RaidGame.moneydb.get(p.getDisplayName())-10;
                        RaidGame.moneydb.replace(p.getDisplayName(),pm);
                        click.setCancelled(true);
                        p.getInventory().addItem(igapp);
                    }
                    else{
                        click.setCancelled(true);
                        p.sendMessage("Insufficient Money.");
                    }

                    break;

                case POTION:
                    if(Objects.requireNonNull(click.getCurrentItem().getItemMeta()).getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "Strength Potion")) {
                        if (RaidGame.moneydb.get(p.getDisplayName()) >= 20) {
                            ItemStack istrength = new ItemStack(Material.POTION, 1);
                            PotionMeta istrength_meta = (PotionMeta) istrength.getItemMeta();
                            PotionEffect str = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 1);
                            istrength_meta.addCustomEffect(str, true);
                            istrength_meta.setDisplayName(ChatColor.RED + "Strength Potion");
                            istrength.setItemMeta(istrength_meta);
                            int pm = RaidGame.moneydb.get(p.getDisplayName())-20;
                            RaidGame.moneydb.replace(p.getDisplayName(),pm);
                            p.getInventory().addItem(istrength);
                        } else {
                            p.sendMessage("Insufficient Money.");
                        }
                        click.setCancelled(true);
                    }

                    else if(Objects.requireNonNull(click.getCurrentItem().getItemMeta()).getDisplayName().equals(ChatColor.AQUA + "" + ChatColor.BOLD + "Speed Potion")){
                        if (RaidGame.moneydb.get(p.getDisplayName()) >= 15) {
                            ItemStack ispeed = new ItemStack(Material.POTION, 1);
                            PotionMeta ispeed_meta = (PotionMeta) ispeed.getItemMeta();
                            PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 1200, 1);
                            ispeed_meta.addCustomEffect(speed, true);
                            ispeed_meta.setDisplayName(ChatColor.RED + "Speed Potion");
                            ispeed.setItemMeta(ispeed_meta);
                            int pm = RaidGame.moneydb.get(p.getDisplayName())-15;
                            RaidGame.moneydb.replace(p.getDisplayName(),pm);
                            p.getInventory().addItem(ispeed);
                        } else {
                            p.sendMessage("Insufficient Money.");
                        }
                        click.setCancelled(true);
                    }
                    
                    else if(Objects.requireNonNull(click.getCurrentItem().getItemMeta()).getDisplayName().equals(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Fire Resistance Potion")){
                        if (RaidGame.moneydb.get(p.getDisplayName()) >= 5) {
                            ItemStack ifireres = new ItemStack(Material.POTION, 1);
                            PotionMeta ifireres_meta = (PotionMeta) ifireres.getItemMeta();
                            PotionEffect fres = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1200, 1);
                            ifireres_meta.addCustomEffect(fres, true);
                            ifireres_meta.setDisplayName(ChatColor.RED + "Fire Resistance Potion");
                            ifireres.setItemMeta(ifireres_meta);
                            int pm = RaidGame.moneydb.get(p.getDisplayName())-5;
                            RaidGame.moneydb.replace(p.getDisplayName(),pm);
                            p.getInventory().addItem(ifireres);
                        } else {
                            p.sendMessage("Insufficient Money.");
                        }
                        click.setCancelled(true);
                    }

                    break;

                case DEAD_BUSH:
                    if(RaidGame.moneydb.get(p.getDisplayName())>=30){
                        double newwx, newwy, newwz;

                        newwx = p.getLocation().getX();
                        newwy = p.getLocation().getY();
                        newwz = p.getLocation().getZ();

                        List<Entity> nearEntities = p.getNearbyEntities(newwx,newwy,newwz);

                        for(Entity near : nearEntities){
                            if(near.getType()== EntityType.PILLAGER || near.getType()==EntityType.EVOKER || near.getType() == EntityType.RAVAGER || near.getType() == EntityType.VINDICATOR || near.getType() == EntityType.VEX){
                                near.remove();
                            }
                        }
                        int pm = RaidGame.moneydb.get(p.getDisplayName()) - 50;
                        RaidGame.moneydb.replace(p.getDisplayName(),pm);
                    }
                    else{
                        p.sendMessage("Insufficient Money");
                    }

                    break;
                
                case ENDER_PEARL:
                    if(RaidGame.moneydb.get(p.getDisplayName())>=10){
                        ItemStack iep = new ItemStack(Material.ENDER_PEARL, 1);
                        int pm = RaidGame.moneydb.get(p.getDisplayName())-10;
                        RaidGame.moneydb.replace(p.getDisplayName(),pm);
                        click.setCancelled(true);
                        p.getInventory().addItem(iep);
                    }
                    else{
                        p.sendMessage("Insufficient Money.");
                    }

                    break;

                case OAK_LOG:
                    if(RaidGame.moneydb.get(p.getDisplayName())>=5){
                        ItemStack iwood = new ItemStack(Material.OAK_LOG, 8);
                        int pm = RaidGame.moneydb.get(p.getDisplayName())-5;
                        RaidGame.moneydb.replace(p.getDisplayName(),pm);
                        click.setCancelled(true);
                        p.getInventory().addItem(iwood);
                    }
                    else{
                        p.sendMessage("Insufficient Money.");
                    }

                    break;
                
                case DIAMOND_AXE:
                    if(RaidGame.moneydb.get(p.getDisplayName()) >=8) {
                        ItemStack iopaxe = new ItemStack(Material.DIAMOND_AXE, 1);
                        ItemMeta iopaxem = iopaxe.getItemMeta();
                        iopaxem.addEnchant(Enchantment.DAMAGE_ALL,5,false);
                        org.bukkit.inventory.meta.Damageable iopaxed = (org.bukkit.inventory.meta.Damageable) iopaxem;
                        iopaxed.setDamage(1551);
                        iopaxe.setItemMeta(iopaxem);
                        int pm = RaidGame.moneydb.get(p.getDisplayName())-8;
                        RaidGame.moneydb.replace(p.getDisplayName(),pm);
                        click.setCancelled(true);
                        p.getInventory().addItem(iopaxe);
                    }
                    else{
                        click.setCancelled(true);
                        p.sendMessage("Insufficient Money.");
                    }

                    break;
                    
                case DIAMOND_SWORD:
                    if(RaidGame.moneydb.get(p.getDisplayName()) >=8) {
                        ItemStack iopsword = new ItemStack(Material.DIAMOND_SWORD, 1);
                        ItemMeta iopswordm = iopsword.getItemMeta();
                        iopswordm.addEnchant(Enchantment.DAMAGE_ALL,5,false);
                        iopswordm.addEnchant(Enchantment.SWEEPING_EDGE,2,false);
                        Damageable iopswordd = (Damageable) iopswordm;
                        iopswordd.setDamage(1556);
                        iopsword.setItemMeta(iopswordm);
                        int pm = RaidGame.moneydb.get(p.getDisplayName())-8;
                        RaidGame.moneydb.replace(p.getDisplayName(),pm);
                        click.setCancelled(true);
                        p.getInventory().addItem(iopsword);
                    }
                    else{
                        click.setCancelled(true);
                        p.sendMessage("Insufficient Money.");
                    }

                    break;

                case NETHER_BRICK:
                    if(RaidGame.moneydb.get(p.getDisplayName())>=12) {
                        if (p.getLocation().getWorld().getName().endsWith("_nether") == true) {
                            String command = "locate fortress";
                            if(p.isOp()){
                                Bukkit.dispatchCommand(p, command);
                            }
                            else{
                                p.setOp(true);
                                Bukkit.dispatchCommand(p, command);
                                p.setOp(false);
                            }

                            int pm = RaidGame.moneydb.get(p.getDisplayName()) - 12;
                            RaidGame.moneydb.replace(p.getDisplayName(), pm);
                            click.setCancelled(true);
                        }
                        else{
                            p.sendMessage("Transaction failed! Buy the item in the nether.");
                        }
                    }
                    else{
                        click.setCancelled(true);
                        p.sendMessage("Insufficient Money.");
                    }
                    break;

                case STICK:
                    if(RaidGame.moneydb.get(p.getDisplayName())>=10){
                        ItemStack ifstick = new ItemStack(Material.STICK, 1);
                        ItemMeta ifstickm = ifstick.getItemMeta();
                        ifstickm.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Fire Touch");
                        ifstickm.addEnchant(Enchantment.FIRE_ASPECT,1,false);
                        ifstick.setItemMeta(ifstickm);
                        int pm = RaidGame.moneydb.get(p.getDisplayName())-10;
                        RaidGame.moneydb.replace(p.getDisplayName(),pm);
                        click.setCancelled(true);
                        p.getInventory().addItem(ifstick);
                    }
                    else{
                        p.sendMessage("Insufficient Money.");
                    }
                    break;

                case TOTEM_OF_UNDYING:
                    if(RaidGame.moneydb.get(p.getDisplayName())>=10){
                        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
                        ItemMeta totemm = totem.getItemMeta();
                        totemm.setDisplayName(ChatColor.GOLD + "" +ChatColor.BOLD + "Totem of Undying");
                        totem.setItemMeta(totemm);
                        int pm = RaidGame.moneydb.get(p.getDisplayName()) - 10;
                        RaidGame.moneydb.replace(p.getDisplayName(),pm);
                        click.setCancelled(true);
                        p.getInventory().addItem(totem);
                    }
                    else{
                        p.sendMessage("Insufficient Money.");
                    }


            }

            click.setCancelled(true);

        }
    }
}
