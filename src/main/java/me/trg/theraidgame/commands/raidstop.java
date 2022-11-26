package me.trg.theraidgame.commands;

import me.trg.theraidgame.RaidGame;
import me.trg.theraidgame.events.Endless;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;
import java.util.Objects;

public class raidstop implements CommandExecutor {

    private final RaidGame plugin;
    public raidstop(RaidGame plugin){
        this.plugin=plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        if(p.hasPermission("RaidGame.raidstop")){

            Bukkit.broadcastMessage(ChatColor.AQUA + "Operator " + ChatColor.RED + p.getDisplayName() + ChatColor.AQUA + " has stopped the raids");
            BukkitScheduler sch = Bukkit.getScheduler();
            sch.cancelTasks(plugin);

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

            RaidGame.raidcondition=1;

            for(Player rpm : Bukkit.getServer().getOnlinePlayers()){
                RaidGame.moneydb.replace(rpm.getDisplayName(),0);
                rpm.removePotionEffect(PotionEffectType.GLOWING);
            }
            if(Endless.boss_uuid!=null){
                if (!Bukkit.getServer().getEntity(Endless.boss_uuid).isDead()) {
                    Objects.requireNonNull(Bukkit.getServer().getEntity(Endless.boss_uuid)).remove();
                }
            }
            try{
                Endless.bmobbar.removeAll();
            }catch(NullPointerException e){
            }
            Endless.boss_uuid=null;

        }

        else{
            p.sendMessage(ChatColor.RED + "Insufficient permissions , Operator role needed.");
        }

        return true;
    }
}
