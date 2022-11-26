package me.trg.theraidgame.events;

import me.trg.theraidgame.RaidGame;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class mobkillcounter implements Listener, CommandExecutor {

    @EventHandler
    public void addToHashMap(PlayerJoinEvent e){
        String jp = e.getPlayer().getDisplayName();
        if(!RaidGame.moneydb.containsKey(jp)){
            RaidGame.moneydb.put(jp,0);
        }
    }

    @EventHandler
    public void rmobdeath(EntityDeathEvent event){

        if(event.getEntity().getKiller() instanceof Player) {

            String mobkiller = Objects.requireNonNull(event.getEntity().getKiller()).getDisplayName();

            if (!RaidGame.moneydb.containsKey(mobkiller)) {
                RaidGame.moneydb.put(mobkiller, 0);
            }


            if (event.getEntity().getType() == EntityType.PILLAGER) {
                int money = RaidGame.moneydb.get(mobkiller);
                money += 1;
                RaidGame.moneydb.replace(mobkiller, money);

            } else if (event.getEntity().getType() == EntityType.VINDICATOR) {
                int money = RaidGame.moneydb.get(mobkiller);
                money += 2;
                RaidGame.moneydb.replace(mobkiller, money);

            } else if (event.getEntity().getType() == EntityType.EVOKER) {
                int money = RaidGame.moneydb.get(mobkiller);
                money += 3;
                RaidGame.moneydb.replace(mobkiller, money);

            } else if (event.getEntity().getType() == EntityType.RAVAGER) {
                int money = RaidGame.moneydb.get(mobkiller);
                money += 4;
                RaidGame.moneydb.replace(mobkiller, money);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("bal")) {

            if (args.length < 1) {
                if (RaidGame.moneydb.get(p.getDisplayName()) != null) {
                    p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "> ---------- Balance ---------- <");
                    p.sendMessage(ChatColor.AQUA + "Money : " + ChatColor.GOLD + RaidGame.moneydb.get(p.getDisplayName()));
                    p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "> ---------------------------- <");
                } else {
                    p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "> > > > > Balance < < < < <");
                    p.sendMessage(ChatColor.ITALIC + "No Money to display , kindly begin the RaidGame </raid>");
                    p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "> ------------------------- <");
                }
            }
            else if (args.length>=1){
                try{
                    String pname = args[0];
                    p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "> ---------- Balance : " + pname + " ---------- <");
                    p.sendMessage(ChatColor.AQUA + "Money : " + ChatColor.GOLD + RaidGame.moneydb.get(pname));
                    p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "> ---------------------------- <");
                }
                catch(IllegalArgumentException e){
                    p.sendMessage("Incorrect command. Usage : /bal <playername>");
                }
            }

        }



        return true;
    }

}
