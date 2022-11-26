package me.trg.theraidgame.events;

import me.trg.theraidgame.RaidGame;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.Random;

public class clickevent implements Listener {

    public static int diffc;

    private final RaidGame plugin;
    public clickevent(RaidGame plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void clickevent(InventoryClickEvent click){

        Player p = (Player) click.getWhoClicked();

        if(click.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "The Raid Game")){
            switch(click.getCurrentItem().getType()){
                case DRAGON_EGG:
                    click.setCancelled(true);
                    p.closeInventory();

                    Inventory srmodegui = Bukkit.createInventory(p,9,ChatColor.LIGHT_PURPLE + "The Raid Game : Speedrun Mode");

                    ItemStack sr_ez = new ItemStack(Material.COAL_BLOCK);
                    ItemStack sr_med = new ItemStack(Material.IRON_BLOCK);
                    ItemStack sr_hard = new ItemStack(Material.DIAMOND_BLOCK);
                    ItemStack sr_hell = new ItemStack(Material.NETHERRACK);
                    ItemStack blank = new ItemStack(Material.LIME_STAINED_GLASS_PANE);

                    ItemMeta sr_ezmeta = sr_ez.getItemMeta();
                    sr_ezmeta.setDisplayName("Easy");
                    sr_ez.setItemMeta(sr_ezmeta);

                    ItemMeta sr_medmeta = sr_med.getItemMeta();
                    sr_medmeta.setDisplayName("Medium");
                    sr_med.setItemMeta(sr_medmeta);

                    ItemMeta sr_hardmeta = sr_hard.getItemMeta();
                    sr_hardmeta.setDisplayName("Hard");
                    sr_hard.setItemMeta(sr_hardmeta);

                    ItemMeta sr_hellmeta = sr_hell.getItemMeta();
                    sr_hellmeta.setDisplayName("Hell");
                    sr_hell.setItemMeta(sr_hellmeta);

                    ItemStack[] srmodegui_items = {blank,sr_ez,blank,sr_med,blank,sr_hard,blank,sr_hell,blank};
                    srmodegui.setContents(srmodegui_items);

                    p.openInventory(srmodegui);
                    click.setCancelled(true);
                    break;

                case WITHER_SKELETON_SKULL:

                    p.closeInventory();
                    p.sendMessage("====Endless====");
                    break;

                case LIME_STAINED_GLASS_PANE:
                    click.setCancelled(true);
                    break;
            }
        }

        if(click.getView().getTitle().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "The Raid Game : Speedrun Mode")){

            ArrayList<Player> allPlayers = new ArrayList<Player>();
            allPlayers.addAll(Bukkit.getOnlinePlayers());

            switch(click.getCurrentItem().getType()){

                case COAL_BLOCK:
                    click.setCancelled(true);
                    p.closeInventory();

                    RaidGame.raidcondition = 0;
                    diffc = 1;

                    Bukkit.broadcastMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+"Starting Raid !\nDifficulty : Easy");
                    Bukkit.broadcastMessage(ChatColor.RED + "1 minute " + ChatColor.AQUA + "grace period.\nG O O D L U C K" );
                    BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                    scheduler.runTaskTimer(plugin, () -> {

                        double newx, newy, newz;

                        int random = new Random().nextInt(allPlayers.size());
                        Player np = allPlayers.get(random);

                        newx = np.getLocation().getX();
                        newy = np.getLocation().getY();
                        newz = np.getLocation().getZ();

                        double spx, spz;

                        spx = newx - 16 + new Random().nextInt(32);
                        spz = newz - 16 + new Random().nextInt(32);
                        double spy = (double) np.getWorld().getHighestBlockAt((int) spx,(int) spz).getY();

                        Location sploc = new Location(Bukkit.getWorld("world"), spx, spy, spz);

                        Bukkit.broadcastMessage(ChatColor.RED +"Spawning Raid Mobs!");
                        for(int i=1;i<=2;i++){
                            np.getWorld().spawnEntity(sploc, EntityType.PILLAGER); //2 pillagers
                        }

                        for(int j=1;j<=1;j++){
                            np.getWorld().spawnEntity(sploc,EntityType.VINDICATOR); //1 vindicator
                        }

                        for(int k=1;k<=1;k++){
                            np.getWorld().spawnEntity(sploc,EntityType.EVOKER); //1 evoker
                        }

                    },1200L,1200L);

                    BukkitScheduler scheduler2 = Bukkit.getServer().getScheduler();
                    scheduler2.runTaskTimer(plugin, () -> {

                        double newx, newy, newz;

                        int random = new Random().nextInt(allPlayers.size());
                        Player np = allPlayers.get(random);

                        newx = np.getLocation().getX();
                        newy = np.getLocation().getY();
                        newz = np.getLocation().getZ();

                        double spx, spz;

                        spx = newx - 16 + new Random().nextInt(32);
                        spz = newz - 16 + new Random().nextInt(32);
                        double spy = (double) np.getWorld().getHighestBlockAt((int) spx,(int) spz).getY();

                        Location sploc = new Location(Bukkit.getWorld("world"), spx, spy, spz);

                        Bukkit.broadcastMessage(ChatColor.RED + "A wild Ravager has appeared.");
                        for (int k = 1; k <= 1; k++) {
                            np.getWorld().spawnEntity(sploc, EntityType.RAVAGER); //1 ravager/m
                        }
                    },6000L,6000L); //should be 6000l for period


                    RaidGame.time = 0;
                    BukkitScheduler timer = Bukkit.getServer().getScheduler();
                    timer.runTaskTimer(plugin, new Runnable() {
                        @Override
                        public void run() {
                            Bukkit.getOnlinePlayers().forEach(player -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "" + ChatColor.BOLD + RaidGame.shortInteger(RaidGame.time)));
                            });
                            RaidGame.time++;
                        }
                    }, 0, 20);


                    break;

                case IRON_BLOCK:
                    click.setCancelled(true);
                    p.closeInventory();

                    diffc = 2;
                    RaidGame.raidcondition = 0;

                    Bukkit.broadcastMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+"Starting Raid !\nDifficulty : Medium");
                    Bukkit.broadcastMessage(ChatColor.RED + "1 minute " + ChatColor.AQUA + "grace period.\nG O O D L U C K" );
                    BukkitScheduler scheduler3 = Bukkit.getServer().getScheduler();
                    scheduler3.runTaskTimer(plugin, () -> {
                        double newx, newy, newz;

                        int random = new Random().nextInt(allPlayers.size());
                        Player np = allPlayers.get(random);

                        newx = np.getLocation().getX();
                        newy = np.getLocation().getY();
                        newz = np.getLocation().getZ();

                        double spx, spz;

                        spx = newx - 16 + new Random().nextInt(32);
                        spz = newz - 16 + new Random().nextInt(32);
                        double spy = (double) np.getWorld().getHighestBlockAt((int) spx,(int) spz).getY();

                        Location sploc = new Location(Bukkit.getWorld("world"), spx, spy, spz);

                        Bukkit.broadcastMessage(ChatColor.RED +"Spawning Raid Mobs!");
                        for(int i=1;i<=3;i++){
                            np.getWorld().spawnEntity(sploc, EntityType.PILLAGER); //2 pillagers
                        }

                        for(int j=1;j<=2;j++){
                            np.getWorld().spawnEntity(sploc,EntityType.VINDICATOR); //1 vindicator
                        }

                        for(int k=1;k<=1;k++){
                            np.getWorld().spawnEntity(sploc,EntityType.EVOKER); //1 evoker
                        }



                    },1200L,1200L);

                    BukkitScheduler scheduler4 = Bukkit.getServer().getScheduler();
                    scheduler4.runTaskTimer(plugin, () -> {

                        double newx, newy, newz;

                        int random = new Random().nextInt(allPlayers.size());
                        Player np = allPlayers.get(random);

                        newx = np.getLocation().getX();
                        newy = np.getLocation().getY();
                        newz = np.getLocation().getZ();

                        double spx, spz;

                        spx = newx - 16 + new Random().nextInt(32);
                        spz = newz - 16 + new Random().nextInt(32);
                        double spy = (double) np.getWorld().getHighestBlockAt((int) spx,(int) spz).getY();

                        Location sploc = new Location(Bukkit.getWorld("world"), spx, spy, spz);

                        Bukkit.broadcastMessage(ChatColor.RED + "A wild Ravager has appeared.");
                        for (int k = 1; k <= 1; k++) {
                            np.getWorld().spawnEntity(sploc, EntityType.RAVAGER); //1 ravager/m
                        }
                    },4800L,4800L); //should be 4800l for period


                    RaidGame.time = 0;
                    BukkitScheduler timer1 = Bukkit.getServer().getScheduler();
                    timer1.runTaskTimer(plugin, new Runnable() {
                        @Override
                        public void run() {
                            Bukkit.getOnlinePlayers().forEach(player -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "" + ChatColor.BOLD + RaidGame.shortInteger(RaidGame.time)));
                            });
                            RaidGame.time++;
                        }
                    }, 0, 20);


                    break;

                case DIAMOND_BLOCK:
                    click.setCancelled(true);
                    p.closeInventory();

                    diffc = 3;
                    RaidGame.raidcondition = 0;


                    Bukkit.broadcastMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+"Starting Raid !\nDifficulty : Hard");
                    Bukkit.broadcastMessage(ChatColor.RED + "1 minute " + ChatColor.AQUA + "grace period.\nG O O D L U C K" );
                    BukkitScheduler scheduler5 = Bukkit.getServer().getScheduler();
                    scheduler5.runTaskTimer(plugin, () -> {
                        double newx, newy, newz;

                        int random = new Random().nextInt(allPlayers.size());
                        Player np = allPlayers.get(random);

                        newx = np.getLocation().getX();
                        newy = np.getLocation().getY();
                        newz = np.getLocation().getZ();


                        double spx, spz;

                        spx = newx - 16 + new Random().nextInt(32);
                        spz = newz - 16 + new Random().nextInt(32);
                        double spy = (double) np.getWorld().getHighestBlockAt((int) spx,(int) spz).getY() + 1;

                        Location sploc = new Location(Bukkit.getWorld("world"), spx, spy, spz);

                       Bukkit.broadcastMessage(ChatColor.RED +"Spawning Raid Mobs!");
                        for(int i=1;i<=4;i++){
                            np.getWorld().spawnEntity(sploc, EntityType.PILLAGER); //4 pillagers
                        }

                        for(int j=1;j<=2;j++){
                            np.getWorld().spawnEntity(sploc,EntityType.VINDICATOR); //2 vindicator
                        }

                        for(int k=1;k<=2;k++){
                            np.getWorld().spawnEntity(sploc,EntityType.EVOKER); //2 evoker
                        }



                    },1200L,1200L);

                    BukkitScheduler scheduler6 = Bukkit.getServer().getScheduler();
                    scheduler6.runTaskTimer(plugin, () -> {

                        double newx, newy, newz;

                        int random = new Random().nextInt(allPlayers.size());
                        Player np = allPlayers.get(random);

                        newx = np.getLocation().getX();
                        newy = np.getLocation().getY();
                        newz = np.getLocation().getZ();

                        double spx, spz;

                        spx = newx - 16 + new Random().nextInt(32);
                        spz = newz - 16 + new Random().nextInt(32);
                        double spy = (double) Bukkit.getWorld("world").getHighestBlockAt((int) spx,(int) spz).getY() + 1;

                        Location sploc = new Location(Bukkit.getWorld("world"), spx, spy, spz);

                        Bukkit.broadcastMessage(ChatColor.RED + "Some wild Ravagers have appeared.");
                        for (int k = 1; k <= 2; k++) {
                            np.getWorld().spawnEntity(sploc, EntityType.RAVAGER); //1 ravager/m
                        }
                    },4800L,4800L); //should be 4800l for period


                    RaidGame.time = 0;
                    BukkitScheduler timer2 = Bukkit.getServer().getScheduler();
                    timer2.runTaskTimer(plugin, new Runnable() {
                        @Override
                        public void run() {
                            Bukkit.getOnlinePlayers().forEach(player -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "" + ChatColor.BOLD + RaidGame.shortInteger(RaidGame.time)));
                            });
                            RaidGame.time++;
                        }
                    }, 0, 20);


                    break;

                case NETHERRACK:
                    click.setCancelled(true);
                    p.closeInventory();

                    diffc = 4;
                    RaidGame.raidcondition = 0;


                    Bukkit.broadcastMessage(ChatColor.DARK_RED+""+ChatColor.BOLD+"Starting Raid !\nDifficulty : HELL");
                    Bukkit.broadcastMessage(ChatColor.RED + "1 minute " + ChatColor.AQUA + "grace period.\nG O O D L U C K" );
                    BukkitScheduler scheduler7 = Bukkit.getServer().getScheduler();
                    scheduler7.runTaskTimer(plugin, () -> {
                        double newx, newy, newz;

                        int random = new Random().nextInt(allPlayers.size());
                        Player np = allPlayers.get(random);

                        newx = np.getLocation().getX();
                        newy = np.getLocation().getY();
                        newz = np.getLocation().getZ();

                        double spx, spz;

                        spx = newx - 16 + new Random().nextInt(32);
                        spz = newz - 16 + new Random().nextInt(32);
                        double spy = (double) np.getWorld().getHighestBlockAt((int) spx,(int) spz).getY();


                        Location sploc = new Location(Bukkit.getWorld("world"), spx, spy, spz);
                        Bukkit.broadcastMessage(ChatColor.RED +"Spawning Raid Mobs!");
                        for(int i=1;i<=4;i++){
                            np.getWorld().spawnEntity(sploc, EntityType.PILLAGER); //4 pillagers
                        }

                        for(int j=1;j<=3;j++){
                            np.getWorld().spawnEntity(sploc,EntityType.VINDICATOR); //3 vindicator
                        }

                        for(int k=1;k<=3;k++){
                            np.getWorld().spawnEntity(sploc,EntityType.EVOKER); //3 evoker
                        }

                    },1200L,1200L);

                    BukkitScheduler scheduler8 = Bukkit.getServer().getScheduler();
                    scheduler8.runTaskTimer(plugin, () -> {

                        double newx, newy, newz;

                        int random = new Random().nextInt(allPlayers.size());
                        Player np = allPlayers.get(random);

                        newx = np.getLocation().getX();
                        newy = np.getLocation().getY();
                        newz = np.getLocation().getZ();

                        double spx, spz;

                        spx = newx - 16 + new Random().nextInt(32);
                        spz = newz - 16 + new Random().nextInt(32);
                        double spy = (double) np.getWorld().getHighestBlockAt((int) spx,(int) spz).getY();

                        Location sploc = new Location(Bukkit.getWorld("world"), spx, spy, spz);

                        Bukkit.broadcastMessage(ChatColor.RED + "Some wild Ravagers have appeared.");
                        for (int k = 1; k <= 2; k++) {
                            np.getWorld().spawnEntity(sploc, EntityType.RAVAGER); //1 ravager/m
                        }
                    },2400L,2400L); //should be 2400l for period


                    RaidGame.time = 0;
                    BukkitScheduler timer3 = Bukkit.getServer().getScheduler();
                    timer3.runTaskTimer(plugin, new Runnable() {
                        @Override
                        public void run() {
                            Bukkit.getOnlinePlayers().forEach(player -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "" + ChatColor.BOLD + RaidGame.shortInteger(RaidGame.time)));
                            });
                            RaidGame.time++;
                        }
                    }, 0, 20);


                    break;

            }
        }

    }

}
