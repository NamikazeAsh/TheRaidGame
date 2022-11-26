package me.trg.theraidgame.events;

import me.trg.theraidgame.RaidGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Raid;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

public class dragkillevent implements Listener {

    private final RaidGame plugin;
    public dragkillevent(RaidGame plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void dragdeath(EntityDeathEvent event){

        if (event.getEntity().getType() == EntityType.ENDER_DRAGON){
            Player p = event.getEntity().getKiller();
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.cancelTasks(plugin);

            Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "CONGRATULATIONS!");
            Bukkit.broadcastMessage(ChatColor.GREEN + "You Win!");
            Bukkit.broadcastMessage(ChatColor.ITALIC + "Dragon Killer: " +ChatColor.GOLD + "" + ChatColor.BOLD + p.getDisplayName());
            Bukkit.getServer().broadcastMessage(ChatColor.RED + "FINAL TIME: " + ChatColor.WHITE + (RaidGame.time-1)+"s");

            double newwx, newwy, newwz;

            newwx = p.getLocation().getX();
            newwy = p.getLocation().getY();
            newwz = p.getLocation().getZ();

            RaidGame.raidcondition=1;

            for(Player rpm : Bukkit.getServer().getOnlinePlayers()){
                RaidGame.moneydb.replace(rpm.getDisplayName(),0);
                rpm.removePotionEffect(PotionEffectType.GLOWING);
            }

            List<Entity> nearEntities = p.getNearbyEntities(newwx,newwy,newwz);

            for(Entity near : nearEntities) {
                if (near.getType() == EntityType.PILLAGER || near.getType() == EntityType.EVOKER || near.getType() == EntityType.RAVAGER || near.getType() == EntityType.VINDICATOR || near.getType() == EntityType.VEX) {
                    near.remove();
                }
            }



        }
    }

}
