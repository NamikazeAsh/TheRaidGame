package me.trg.theraidgame.events;

import me.trg.theraidgame.RaidGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;
import java.util.Objects;

public class deathevent implements Listener {

    private final RaidGame plugin;
    public deathevent(RaidGame plugin){
        this.plugin=plugin;
    }

    @EventHandler
    public void death(PlayerDeathEvent event){

        Player p = event.getEntity().getPlayer();
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.cancelTasks(plugin);

        RaidGame.moneydb.replace(Objects.requireNonNull(p).getDisplayName(),0);

        p.sendMessage(ChatColor.DARK_RED + "You lose.");
        Bukkit.broadcastMessage(ChatColor.DARK_RED + "Ongoing Raids have been stopped.\nTry again with /raid if you're up to it.");

        ///////////////////////////////////////////////////////////////////////////////////////////
        if((RaidGame.time-1)!=-1) {
            BukkitScheduler ctimer = Bukkit.getServer().getScheduler();
            Objects.requireNonNull(event.getEntity().getPlayer()).sendMessage(ChatColor.RED + "FINAL TIME: " + ChatColor.WHITE + (RaidGame.time - 1) + "s");
            ctimer.cancelTasks(plugin);
            RaidGame.time = 0;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////

        clickevent.diffc = 0;
        RaidGame.raidcondition = 1;

        double newwx, newwy, newwz;

        newwx = p.getLocation().getX();
        newwy = p.getLocation().getY();
        newwz = p.getLocation().getZ();

        List<Entity> nearEntities = p.getNearbyEntities(newwx,newwy,newwz);

        for(Entity near : nearEntities){
            if(near.getType()== EntityType.PILLAGER || near.getType()==EntityType.EVOKER || near.getType() == EntityType.RAVAGER || near.getType() == EntityType.VINDICATOR || near.getType() == EntityType.VEX || near.getType() == EntityType.ZOMBIE || near.getType() == EntityType.SKELETON || near.getType() == EntityType.SKELETON_HORSE){
                near.remove();
            }
        }

        for(Player rpm : Bukkit.getServer().getOnlinePlayers()){
            RaidGame.moneydb.replace(rpm.getDisplayName(),0);
            rpm.removePotionEffect(PotionEffectType.GLOWING);
        }

        try {
            if (Endless.boss_uuid != null) {
                if (!Bukkit.getServer().getEntity(Endless.boss_uuid).isDead()) {
                    Objects.requireNonNull(Bukkit.getServer().getEntity(Endless.boss_uuid)).remove();
                }
            }
        }catch (NullPointerException e){
        }
        Endless.boss_uuid = null;
        try{
            Endless.bmobbar.removeAll();
        }catch(NullPointerException e){
        }
        
    }

    @EventHandler
    public void disableTotemDrop(EntityDeathEvent e){

        if(e.getEntity().getType() == EntityType.EVOKER){

            if(RaidGame.raidcondition == 0) {
                if (e.getDrops().contains(new ItemStack(Material.TOTEM_OF_UNDYING))) {
                    e.getDrops().remove(new ItemStack(Material.TOTEM_OF_UNDYING));
                }
            }
            else{
                if(e.getDrops().contains(new ItemStack(Material.TOTEM_OF_UNDYING))==false) {
                    e.getDrops().add(new ItemStack(Material.TOTEM_OF_UNDYING));
                }
            }
        }

    }

}
