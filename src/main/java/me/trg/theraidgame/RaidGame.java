package me.trg.theraidgame;

import me.trg.theraidgame.commands.*;
import me.trg.theraidgame.events.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class RaidGame extends JavaPlugin {


    public static HashMap<String,Integer> moneydb = new HashMap<>();
    public static int time,raidcondition=1;

    @Override
    public void onEnable() {

        System.out.println("------RAID GAME------");
        System.out.println("Good Luck!");
        System.out.println("----------------------");

        //HashMap adding Key's for Money's DB
        for(Player jp : Bukkit.getOnlinePlayers()) {
            if (!RaidGame.moneydb.containsKey(jp.getDisplayName())) {
                RaidGame.moneydb.put(jp.getDisplayName(), 0);
            }
        }

        //cmds
        getCommand("raid").setExecutor(new RaidGUI(this));
        getCommand("raidstop").setExecutor(new raidstop(this));
        getCommand("bal").setExecutor(new mobkillcounter());
        getCommand("rshop").setExecutor(new rshop());
        getCommand("rmoney").setExecutor(new rmoneyaddsub());

        //events
        getServer().getPluginManager().registerEvents(new clickevent(this),this);
        getServer().getPluginManager().registerEvents(new deathevent(this),this);
        getServer().getPluginManager().registerEvents(new diffarmormod(this),this);
        getServer().getPluginManager().registerEvents(new mobkillcounter(),this);
        getServer().getPluginManager().registerEvents(new rshop(),this);
        getServer().getPluginManager().registerEvents(new dragkillevent(this),this);
        getServer().getPluginManager().registerEvents(new Endless(this),this);



    }
    public static String shortInteger(int duration) {
        String string = "";
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if (duration / 60 / 60 / 24 >= 1) {
            duration -= duration / 60 / 60 / 24 * 60 * 60 * 24;
        }
        if (duration / 60 / 60 >= 1) {
            hours = duration / 60 / 60;
            duration -= duration / 60 / 60 * 60 * 60;
        }
        if (duration / 60 >= 1) {
            minutes = duration / 60;
            duration -= duration / 60 * 60;
        }
        if (duration >= 1)
            seconds = duration;
        if (hours <= 9) {
            string = String.valueOf(string) + "0" + hours + ":";
        } else {
            string = String.valueOf(string) + hours + ":";
        }
        if (minutes <= 9) {
            string = String.valueOf(string) + "0" + minutes + ":";
        } else {
            string = String.valueOf(string) + minutes + ":";
        }
        if (seconds <= 9) {
            string = String.valueOf(string) + "0" + seconds;
        } else {
            string = String.valueOf(string) + seconds;
        }
        return string;
    }
}
    