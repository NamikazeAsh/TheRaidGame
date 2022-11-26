package me.trg.theraidgame.events;

import me.trg.theraidgame.RaidGame;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Endless implements Listener {

    private final RaidGame plugin;
    public Endless(RaidGame plugin){
        this.plugin = plugin;
    }

    int bsc,bss,abi,abi2,bosstype,wavechk; //bsc boss spawn chance , bss boss spawn state , abi ability
    public int skelabi3s;

    public static int wave=0;
    public static UUID boss_uuid=null;
    public static BossBar bmobbar;

    public static boolean isSafeLocation(Location location) {
        Block feet = location.getBlock();
        if (!feet.getType().isTransparent() && !feet.getLocation().add(0, 1, 0).getBlock().getType().isTransparent()) {
            return false; // not transparent (will suffocate)
        }
        Block head = feet.getRelative(BlockFace.UP);
        if (!head.getType().isTransparent()) {
            return false; // not transparent (will suffocate)
        }
        Block ground = feet.getRelative(BlockFace.DOWN);
        if (!ground.getType().isSolid()) {
            return false; // not solid
        }
        return true;
    }
    public static void spawnWavePVE(ArrayList<Player> allPlayers,int nPillager,int nVindicator,int nEvoker){

        double newx,newz;

        int random = new Random().nextInt(allPlayers.size());
        Player np = allPlayers.get(random);

        newx = np.getLocation().getX();
        newz = np.getLocation().getZ();

        double spx, spz;

        spx = newx - 16 + new Random().nextInt(32);
        spz = newz - 16 + new Random().nextInt(32);
        double spy = (double) np.getWorld().getHighestBlockAt((int) spx,(int) spz).getY();

        Location sploc = new Location(Bukkit.getWorld("world"), spx, spy, spz);

        Bukkit.broadcastMessage(ChatColor.RED +"Spawning Raid Mobs!");
        for(int i=1;i<=nPillager;i++){
            np.getWorld().spawnEntity(sploc, EntityType.PILLAGER);
        }

        for(int j=1;j<=nVindicator;j++){
            np.getWorld().spawnEntity(sploc,EntityType.VINDICATOR);
        }

        for(int k=1;k<=nEvoker;k++){
            np.getWorld().spawnEntity(sploc,EntityType.EVOKER);
        }
    }
    public static void spawnWaveR(ArrayList<Player> allPlayers,int nRavager){

        double newx,newz;

        int random = new Random().nextInt(allPlayers.size());
        Player np = allPlayers.get(random);

        newx = np.getLocation().getX();
        newz = np.getLocation().getZ();

        double spx, spz;

        spx = newx - 16 + new Random().nextInt(32);
        spz = newz - 16 + new Random().nextInt(32);
        double spy = (double) np.getWorld().getHighestBlockAt((int) spx,(int) spz).getY();

        Location sploc = new Location(Bukkit.getWorld("world"), spx, spy, spz);

        for (int k = 1; k <= nRavager ; k++) {
            np.getWorld().spawnEntity(sploc, EntityType.RAVAGER);
        }

    }
    public static Zombie spawnBossZombie(ArrayList<Player> allPlayers){

        Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + "Spawning "+ ChatColor.DARK_RED + "" + ChatColor.BOLD +"Sug"+ ChatColor.BLUE + "" + ChatColor.ITALIC + ", the Raid Boss.");

        int random = new Random().nextInt(allPlayers.size());
        Player np = allPlayers.get(random);

        double newx,newz;

        newx = np.getLocation().getX();
        newz = np.getLocation().getZ();

        double spx, spz;

        spx = newx - 16 + new Random().nextInt(32);
        spz = newz - 16 + new Random().nextInt(32);
        double spy = (double) np.getWorld().getHighestBlockAt((int) spx,(int) spz).getY()+1;

        Location sploc = new Location(Bukkit.getWorld("world"), spx, spy, spz);
        Zombie zombieboss = (Zombie) np.getWorld().spawnEntity(sploc, EntityType.ZOMBIE);

        zombieboss.setCustomName(ChatColor.WHITE+""+ChatColor.BOLD + "Sug - The Undead Captain.");
        zombieboss.setGlowing(true);
        dmgIncr(zombieboss);

        ItemStack frostboots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta frostbootsm = frostboots.getItemMeta();
        frostbootsm.addEnchant(Enchantment.FROST_WALKER,1,false);
        frostbootsm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,4,false);
        frostboots.setItemMeta(frostbootsm);
        zombieboss.getEquipment().setBoots(frostboots);

        ItemStack zombieleggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta zombieleggingsm = zombieleggings.getItemMeta();
        zombieleggingsm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,4,false);
        zombieleggings.setItemMeta(zombieleggingsm);
        zombieboss.getEquipment().setLeggings(zombieleggings);

        ItemStack zombiechestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta zombiechestplatem = zombiechestplate.getItemMeta();
        zombiechestplatem.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,4,false);
        zombiechestplate.setItemMeta(zombiechestplatem);
        zombieboss.getEquipment().setChestplate(zombiechestplate);

        ItemStack zombiehelmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta zombiehelmetm = zombiehelmet.getItemMeta();
        zombiehelmetm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,4,false);
        zombiehelmet.setItemMeta(zombiehelmetm);
        zombieboss.getEquipment().setHelmet(zombiehelmet);
        
        zombieboss.setRemoveWhenFarAway(false);

        zombieboss.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE,2,false,false));
        zombieboss.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE,3,false,false));

        Endless.boss_uuid = zombieboss.getUniqueId();

        return zombieboss;
    }
    public static Skeleton spawnBossSkeleton(ArrayList<Player> allPlayers){
        Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + "Spawning "+ChatColor.BOLD + ""+ChatColor.DARK_RED+"Benji"+ChatColor.BLUE + "" + ChatColor.ITALIC + ", the Raid Boss.");

        int random = new Random().nextInt(allPlayers.size());
        Player np = allPlayers.get(random);

        double newx,newz;

        newx = np.getLocation().getX();
        newz = np.getLocation().getZ();

        double spx, spz;

        spx = newx - 16 + new Random().nextInt(32);
        spz = newz - 16 + new Random().nextInt(32);
        double spy = (double) np.getWorld().getHighestBlockAt((int) spx,(int) spz).getY()+1;

        Location sploc = new Location(Bukkit.getWorld("world"), spx, spy, spz);
        Skeleton skelboss = (Skeleton) np.getWorld().spawnEntity(sploc, EntityType.SKELETON);

        skelboss.setCustomName(ChatColor.WHITE+""+ChatColor.BOLD + "Sug - The Undead Captain.");
        skelboss.setGlowing(true);
        dmgIncrSkele(skelboss);

        ItemStack skelbow = new ItemStack(Material.BOW);
        ItemMeta skelbowm = skelbow.getItemMeta();
        skelbowm.addEnchant(Enchantment.ARROW_DAMAGE,2,false);
        skelbow.setItemMeta(skelbowm);
        skelboss.getEquipment().setItemInMainHand(skelbow);

        ItemStack frostboots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta frostbootsm = frostboots.getItemMeta();
        frostbootsm.addEnchant(Enchantment.FROST_WALKER,1,false);
        frostbootsm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,4,false);
        frostboots.setItemMeta(frostbootsm);
        skelboss.getEquipment().setBoots(frostboots);

        ItemStack zombieleggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta zombieleggingsm = zombieleggings.getItemMeta();
        zombieleggingsm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,4,false);
        zombieleggings.setItemMeta(zombieleggingsm);
        skelboss.getEquipment().setLeggings(zombieleggings);

        ItemStack zombiechestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta zombiechestplatem = zombiechestplate.getItemMeta();
        zombiechestplatem.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,4,false);
        zombiechestplate.setItemMeta(zombiechestplatem);
        skelboss.getEquipment().setChestplate(zombiechestplate);

        ItemStack zombiehelmet = new ItemStack(Material.IRON_HELMET);
        ItemMeta zombiehelmetm = zombiehelmet.getItemMeta();
        zombiehelmetm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,4,false);
        zombiehelmet.setItemMeta(zombiehelmetm);
        skelboss.getEquipment().setHelmet(zombiehelmet);

        skelboss.setRemoveWhenFarAway(false);
//        skelboss.setAbsorptionAmount(20);
        skelboss.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE,2,false,false));

        Endless.boss_uuid = skelboss.getUniqueId();

        return skelboss;
    }
    public static void ZombieTeleportAbility(Zombie zombieboss,ArrayList<Player> allPlayerss) {

        int safetpc = 0,tpchecks=0;
        Location spploc=null;
        while (safetpc == 0) {
            tpchecks +=1;
            int rp = bossSpC(allPlayerss.size());
            Player p = allPlayerss.get(rp);

            double newwx, newwy, newwz;

            newwx = p.getLocation().getX();
            newwy = p.getLocation().getY();
            newwz = p.getLocation().getZ();

            double sppx, sppz;

            sppx = newwx - 3 + new Random().nextInt(6);
            sppz = newwz - 3 + new Random().nextInt(6);

            spploc = new Location(Bukkit.getWorld("world"), sppx, newwy, sppz);
            if (isSafeLocation(spploc) == true | tpchecks == 50) {
                safetpc = 1;
            }
        }
        if(tpchecks == 50){
            spploc = allPlayerss.get(bossSpC(allPlayerss.size())).getLocation();
        }
        if(safetpc==1){ //checks 50 times and then straight up teleports to the player
            zombieboss.teleport(spploc);
            Bukkit.getServer().broadcastMessage(ChatColor.DARK_BLUE + "[Sug]: " + ChatColor.GRAY + "I'm coming to kill you! Skadoosh~!");
            }

    }

    public static void SkeletonTeleportAbility(Skeleton skelboss,ArrayList<Player> allPlayerss) {

        int safetpc = 0,tpchecks=0;
        Location spploc=null;
        while (safetpc == 0) {
            tpchecks +=1;
            int rp = bossSpC(allPlayerss.size());
            Player p = allPlayerss.get(rp);

            double newwx, newwy, newwz;

            newwx = p.getLocation().getX();
            newwy = p.getLocation().getY();
            newwz = p.getLocation().getZ();

            double sppx, sppz;

            sppx = newwx - 3 + new Random().nextInt(6);
            sppz = newwz - 3 + new Random().nextInt(6);

            spploc = new Location(Bukkit.getWorld("world"), sppx, newwy, sppz);
            if (isSafeLocation(spploc) == true | tpchecks == 50) {
                safetpc = 1;
            }
        }
        if(tpchecks == 50){
            spploc = allPlayerss.get(bossSpC(allPlayerss.size())).getLocation();
        }
        if(safetpc==1){ //checks 50 times and then straight up teleports to the player
            skelboss.teleport(spploc);
            Bukkit.getServer().broadcastMessage(ChatColor.DARK_BLUE + "[Sug]: " + ChatColor.GRAY + "I'm coming to kill you! Skadoosh~!");
        }
    }

    public static int bossSpC(int bound){
        Random r = new Random();
        int x = r.nextInt(bound);
        return x;
    }
    public static void dmgIncr(Zombie bmob) {
        if (Endless.wave < 20) {
//            Bukkit.broadcastMessage("Pre Wave 20 Strength");
            bmob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1, false, false));
        }
        else if(Endless.wave>=20 && Endless.wave<40){
//            Bukkit.broadcastMessage("Wave 20-40 Strength");
            bmob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE,2,false,false));
        }
        else if(Endless.wave>=40){
//            Bukkit.broadcastMessage("Post Wave 40 Strength");
            bmob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,Integer.MAX_VALUE,3,false,false));
        }
    }
    public static void dmgIncrSkele(Skeleton bmob) {
        if (Endless.wave < 20) {
//            Bukkit.broadcastMessage("Pre Wave 20 Strength");
            bmob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1, false, false));
        }
        else if(Endless.wave>=20 && Endless.wave<40){
//            Bukkit.broadcastMessage("Wave 20-40 Strength");
            bmob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE,2,false,false));
        }
        else if(Endless.wave>=40){
//            Bukkit.broadcastMessage("Post Wave 40 Strength");
            bmob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,Integer.MAX_VALUE,3,false,false));
        }
    }
    public static void bossCurseEffect(){
        Random r = new Random();
        int whicheff = r.nextInt(8);
        PotionEffectType[] badeffs = {PotionEffectType.BLINDNESS, PotionEffectType.CONFUSION,PotionEffectType.HUNGER,PotionEffectType.POISON,PotionEffectType.SLOW,PotionEffectType.SLOW_DIGGING,PotionEffectType.UNLUCK,PotionEffectType.WEAKNESS};
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            p.sendMessage(ChatColor.DARK_BLUE + "[Sug]: " + ChatColor.RED + "CURSE YOU!");
            PotionEffectType eff = badeffs[whicheff];
            p.addPotionEffect(new PotionEffect(eff,2400,1));
        }
    }
    public static void bossCurseEffectSkele(){
        Random r = new Random();
        int whicheff = r.nextInt(8);
        PotionEffectType[] badeffs = {PotionEffectType.BLINDNESS, PotionEffectType.CONFUSION,PotionEffectType.HUNGER,PotionEffectType.POISON,PotionEffectType.SLOW,PotionEffectType.SLOW_DIGGING,PotionEffectType.UNLUCK,PotionEffectType.WEAKNESS};
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            p.sendMessage(ChatColor.DARK_BLUE + "[Benji]: " + ChatColor.RED + "CURSE YOU!");
            PotionEffectType eff = badeffs[whicheff];
            p.addPotionEffect(new PotionEffect(eff,2400,1));
        }
    }
    public static BossBar createBossBarZombie(){
        BossBar bmobbar = Bukkit.createBossBar(ChatColor.WHITE+""+ChatColor.BOLD + "Sug - The Undead Captain.", BarColor.RED, BarStyle.SOLID);
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            bmobbar.addPlayer(p);
        }
        return bmobbar;
    }
    public static BossBar createBossBarSkeleton(){
        BossBar bmobbar = Bukkit.createBossBar(ChatColor.WHITE+""+ChatColor.BOLD + "Benji - The Sharp Eyed", BarColor.BLUE, BarStyle.SOLID);
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            bmobbar.addPlayer(p);
        }
        return bmobbar;
    }
    public static void ZombieMinion(Zombie bmob){
        
        Location bmobloc = bmob.getLocation();
        Location lmini1 = new Location(Bukkit.getServer().getWorld("world"),bmobloc.getX()+2,bmobloc.getY(),bmobloc.getZ());
        Location lmini2 = new Location(Bukkit.getServer().getWorld("world"),bmobloc.getX()-2,bmobloc.getY(),bmobloc.getZ());
        Location lmini3 = new Location(Bukkit.getServer().getWorld("world"),bmobloc.getX(),bmobloc.getY(),bmobloc.getZ()+2);
        Location lmini4 = new Location(Bukkit.getServer().getWorld("world"),bmobloc.getX(),bmobloc.getY(),bmobloc.getZ()-2);

        ItemStack nfc = new ItemStack(Material.LEATHER_HELMET);
        ItemStack fwb = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta fwbm = fwb.getItemMeta();
        fwbm.addEnchant(Enchantment.FROST_WALKER,1,false);
        fwb.setItemMeta(fwbm);

        Zombie minion1 = (Zombie) Bukkit.getWorld("world").spawnEntity(lmini1,EntityType.ZOMBIE);
        minion1.setBaby();
        minion1.getEquipment().setHelmet(nfc);
        minion1.getEquipment().setBoots(fwb);

        Zombie minion2 = (Zombie) Bukkit.getWorld("world").spawnEntity(lmini2,EntityType.ZOMBIE);
        minion2.setBaby();
        minion2.getEquipment().setHelmet(nfc);
        minion2.getEquipment().setBoots(fwb);

        Zombie minion3 = (Zombie) Bukkit.getWorld("world").spawnEntity(lmini3,EntityType.ZOMBIE);
        minion3.setBaby();
        minion3.getEquipment().setHelmet(nfc);
        minion3.getEquipment().setBoots(fwb);

        Zombie minion4 = (Zombie) Bukkit.getWorld("world").spawnEntity(lmini4,EntityType.ZOMBIE);
        minion4.setBaby();
        minion4.getEquipment().setHelmet(nfc);
        minion4.getEquipment().setBoots(fwb);

    }
    public static void SkeletonMinion(Skeleton skelboss){

        Location skelbossloc = skelboss.getLocation();
        Location lmini1 = new Location(Bukkit.getServer().getWorld("world"),skelbossloc.getX()+2,skelbossloc.getY(),skelbossloc.getZ());
        Location lmini2 = new Location(Bukkit.getServer().getWorld("world"),skelbossloc.getX()-2,skelbossloc.getY(),skelbossloc.getZ());
        Location lmini3 = new Location(Bukkit.getServer().getWorld("world"),skelbossloc.getX(),skelbossloc.getY(),skelbossloc.getZ()+2);
        Location lmini4 = new Location(Bukkit.getServer().getWorld("world"),skelbossloc.getX(),skelbossloc.getY(),skelbossloc.getZ()-2);

        ItemStack nfc = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemStack fwb = new ItemStack(Material.BOW);
        ItemMeta fwbm = fwb.getItemMeta();
        fwbm.addEnchant(Enchantment.ARROW_FIRE,1,false);
        fwb.setItemMeta(fwbm);
        
        Skeleton trooper1 = (Skeleton) Bukkit.getWorld("world").spawnEntity(lmini1,EntityType.SKELETON);
        trooper1.setCustomName(ChatColor.GRAY + "Trooper");
        trooper1.getEquipment().setItemInMainHand(fwb);
        trooper1.getEquipment().setHelmet(nfc);

        Skeleton trooper2 = (Skeleton) Bukkit.getWorld("world").spawnEntity(lmini1,EntityType.SKELETON);
        trooper2.setCustomName(ChatColor.GRAY + "Trooper");
        trooper2.getEquipment().setItemInMainHand(fwb);
        trooper2.getEquipment().setHelmet(nfc);

        Skeleton trooper3 = (Skeleton) Bukkit.getWorld("world").spawnEntity(lmini1,EntityType.SKELETON);
        trooper3.setCustomName(ChatColor.GRAY + "Trooper");
        trooper3.getEquipment().setItemInMainHand(fwb);
        trooper3.getEquipment().setHelmet(nfc);

        Skeleton trooper4 = (Skeleton) Bukkit.getWorld("world").spawnEntity(lmini1,EntityType.SKELETON);
        trooper4.setCustomName(ChatColor.GRAY + "Trooper");
        trooper4.getEquipment().setItemInMainHand(fwb);
        trooper4.getEquipment().setHelmet(nfc);
        
        SkeletonHorse minion1 = (SkeletonHorse) Bukkit.getWorld("world").spawnEntity(lmini1,EntityType.SKELETON_HORSE);
        minion1.setCustomName(ChatColor.DARK_GRAY+"Cavalry");
        minion1.setTamed(true);
        minion1.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        minion1.addPassenger(trooper1);


        SkeletonHorse minion2 = (SkeletonHorse) Bukkit.getWorld("world").spawnEntity(lmini2,EntityType.SKELETON_HORSE);
        minion2.setCustomName(ChatColor.DARK_GRAY+"Cavalry");
        minion2.setTamed(true);
        minion2.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        minion2.addPassenger(trooper2);


        SkeletonHorse minion3 = (SkeletonHorse) Bukkit.getWorld("world").spawnEntity(lmini3,EntityType.SKELETON_HORSE);
        minion3.setCustomName(ChatColor.DARK_GRAY+"Cavalry");
        minion3.setTamed(true);
        minion3.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        minion3.addPassenger(trooper3);


        SkeletonHorse minion4 = (SkeletonHorse) Bukkit.getWorld("world").spawnEntity(lmini4,EntityType.SKELETON_HORSE);
        minion4.setCustomName(ChatColor.DARK_GRAY+"Cavalry");
        minion4.setTamed(true);
        minion4.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        minion4.addPassenger(trooper4);

    }
    public static void SkeletonJockeys(Skeleton skelboss){

        Location skelbossloc = skelboss.getLocation();
        Location lmini1 = new Location(Bukkit.getServer().getWorld("world"),skelbossloc.getX()+2,skelbossloc.getY(),skelbossloc.getZ());
        Location lmini2 = new Location(Bukkit.getServer().getWorld("world"),skelbossloc.getX()-2,skelbossloc.getY(),skelbossloc.getZ());
        Location lmini3 = new Location(Bukkit.getServer().getWorld("world"),skelbossloc.getX(),skelbossloc.getY(),skelbossloc.getZ()+2);
        Location lmini4 = new Location(Bukkit.getServer().getWorld("world"),skelbossloc.getX(),skelbossloc.getY(),skelbossloc.getZ()-2);

        ItemStack nfc = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemStack fwb = new ItemStack(Material.BOW);
        ItemMeta fwbm = fwb.getItemMeta();
        fwbm.addEnchant(Enchantment.ARROW_DAMAGE,1,false);
        fwb.setItemMeta(fwbm);

        Skeleton trooper1 = (Skeleton) Bukkit.getWorld("world").spawnEntity(lmini1,EntityType.SKELETON);
        trooper1.setCustomName(ChatColor.GRAY + "Never");
        trooper1.getEquipment().setItemInMainHand(fwb);
        trooper1.getEquipment().setHelmet(nfc);

        Skeleton trooper2 = (Skeleton) Bukkit.getWorld("world").spawnEntity(lmini1,EntityType.SKELETON);
        trooper2.setCustomName(ChatColor.GRAY + "Gonna");
        trooper2.getEquipment().setItemInMainHand(fwb);
        trooper2.getEquipment().setHelmet(nfc);

        Skeleton trooper3 = (Skeleton) Bukkit.getWorld("world").spawnEntity(lmini1,EntityType.SKELETON);
        trooper3.setCustomName(ChatColor.GRAY + "Give");
        trooper3.getEquipment().setItemInMainHand(fwb);
        trooper3.getEquipment().setHelmet(nfc);

        Skeleton trooper4 = (Skeleton) Bukkit.getWorld("world").spawnEntity(lmini1,EntityType.SKELETON);
        trooper4.setCustomName(ChatColor.GRAY + "Yuh");
        trooper4.getEquipment().setItemInMainHand(fwb);
        trooper4.getEquipment().setHelmet(nfc);

        CaveSpider minion1 = (CaveSpider) Bukkit.getWorld("world").spawnEntity(lmini1,EntityType.CAVE_SPIDER);
        minion1.setCustomName(ChatColor.DARK_GRAY+"Up");
        minion1.addPassenger(trooper1);


        CaveSpider minion2 = (CaveSpider) Bukkit.getWorld("world").spawnEntity(lmini2,EntityType.CAVE_SPIDER);
        minion2.setCustomName(ChatColor.DARK_GRAY+"Let you down");
        minion2.addPassenger(trooper2);


        CaveSpider minion3 = (CaveSpider) Bukkit.getWorld("world").spawnEntity(lmini3,EntityType.CAVE_SPIDER);
        minion3.setCustomName(ChatColor.DARK_GRAY+"Turn around");
        minion3.addPassenger(trooper3);


        CaveSpider minion4 = (CaveSpider) Bukkit.getWorld("world").spawnEntity(lmini4,EntityType.CAVE_SPIDER);
        minion4.setCustomName(ChatColor.DARK_GRAY+"Desert ya");
        minion4.addPassenger(trooper4);

    }


    @EventHandler
    public void endlessItemBeginClickE(InventoryClickEvent click){
        Player p = (Player) click.getWhoClicked();
        if(click.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "The Raid Game")) {
            switch (click.getCurrentItem().getType()) {

                case WITHER_SKELETON_SKULL:
                    Endless.wave = 0;

                    RaidGame.raidcondition = 0;

                    ArrayList<Player> allPlayers = new ArrayList<Player>();
                    allPlayers.addAll(Bukkit.getOnlinePlayers());
                    for(Player geff : allPlayers){
                        geff.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE,1));
                    }

                    p.sendMessage(ChatColor.GOLD + "" + ChatColor.ITALIC + "Stay alive for as long as you can.\nTest your limits in this gamemode\nBeware of the bosses that spawn.");

                    BukkitScheduler pve = Bukkit.getServer().getScheduler();
                    pve.runTaskTimer(plugin, (Runnable) () -> {
                        Endless.wave += 1;
                        wavechk = 0;
                        Bukkit.getServer().broadcastMessage(ChatColor.DARK_GRAY + "Wave : " + Endless.wave);

                        if (Endless.wave <= 20) {
                            spawnWavePVE(allPlayers, 2, 1, 1);
                        } else if (Endless.wave > 20 && Endless.wave <= 40) {
                            spawnWavePVE(allPlayers, 3, 2, 1);
                        } else if (Endless.wave > 40 && Endless.wave <= 60) {
                            spawnWavePVE(allPlayers, 4, 2, 2);
                        } else if (Endless.wave > 60) {
                            spawnWavePVE(allPlayers, 4, 3, 3);
                        }


                    }, 1200, 1200); //Per minute

                    BukkitScheduler rav = Bukkit.getServer().getScheduler();
                    rav.runTaskTimer(plugin, () -> {
                        Bukkit.getServer().broadcastMessage(ChatColor.RED + "" + ChatColor.ITALIC + "A ravager has been bestowed on thine land. Goodluck builder!");
                        if (Endless.wave < 30) {
                            spawnWaveR(allPlayers, 1);
                        } else if (Endless.wave > 30 && Endless.wave <= 60) {
                            spawnWaveR(allPlayers, 2);
                        } else if (Endless.wave > 60) {
                            spawnWaveR(allPlayers, 3);
                        }
                    }, 6000, 6000); //Per 5 minutes


                    RaidGame.time = 0;
                    BukkitScheduler timer = Bukkit.getServer().getScheduler();
                    timer.runTaskTimer(plugin, () -> {
                        Bukkit.getOnlinePlayers().forEach(player -> {
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "" + ChatColor.BOLD + RaidGame.shortInteger(RaidGame.time)));
                        });
                        RaidGame.time++;

                        bsc = bossSpC(300);
//                        bsc = 169;
                        bosstype = bossSpC(2);

                        if (boss_uuid == null && wavechk == 0) {

                            if ((Endless.wave != 0) && ((Endless.wave % 20) == 0 | bsc == 69) && (bosstype==0)) {  //Spawning ZOMBIE BOSS
                                Zombie zombieboss = spawnBossZombie(allPlayers);
                                Endless.bmobbar = createBossBarZombie();
//                                bss = 1;
                                wavechk = 1;

                                Bukkit.getServer().getScheduler().runTaskTimer(plugin, bukkitTask -> {

                                    if (!zombieboss.isDead()) {
                                        bmobbar.setProgress(zombieboss.getHealth() / zombieboss.getMaxHealth());
                                    } else {
                                        List<Player> players = bmobbar.getPlayers();
                                        for (Player player : players) {
                                            bmobbar.removePlayer(player);
                                        }
                                        bossCurseEffect();
                                        Bukkit.broadcastMessage(ChatColor.AQUA + "-+-+-+-\n" + ChatColor.BOLD + "" + ChatColor.GRAY + "Sug has been Killed. Do you wanna know who Sug was?\n" + ChatColor.AQUA + "-+-+-+-");
                                        bmobbar.setVisible(false);
                                        bukkitTask.cancel();

                                    }

                                }, 0, 1);

                                BukkitScheduler bossAbi = Bukkit.getServer().getScheduler();
                                bossAbi.runTaskTimer(plugin, bossAbilityTask -> {

                                    abi = bossSpC(15);
                                    if (!zombieboss.isDead()) {
                                        if (abi == 1) {
                                            ZombieTeleportAbility(zombieboss,allPlayers);
                                        } // teleport
                                        else if (abi == 2) {
                                            Bukkit.broadcastMessage(ChatColor.DARK_BLUE + "[Sug]: " + ChatColor.GRAY + "Fsh! -slips into the darkness-");
                                            zombieboss.setInvisible(true);
                                            zombieboss.setGlowing(false);
                                            BukkitScheduler invi = Bukkit.getServer().getScheduler();
                                            invi.runTaskLater(plugin, invitask -> {
                                                Bukkit.broadcastMessage(ChatColor.DARK_BLUE + "[Sug]: " + ChatColor.GRAY + "Darn it! Lost my camouflage.");
                                                zombieboss.setInvisible(false);
                                                zombieboss.setGlowing(true);
                                            }, 150L);
                                        } // invis
                                        else if (abi == 3) {
                                            zombieboss.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*10, 4));
                                            Bukkit.getServer().broadcastMessage(ChatColor.DARK_BLUE + "[Sug]: " + ChatColor.GRAY + "Speeed is keyyyyy! For 10 seconds smh..");
                                        } // speed
                                        else if (abi == 4) {
                                            Bukkit.broadcastMessage(ChatColor.DARK_BLUE + "[Sug]: " + ChatColor.GRAY + "Come forth, my faithful minions!");
                                            ZombieMinion(zombieboss);
                                        } //minions
                                    } else {
//                                        bss = 0;
                                        bossAbilityTask.cancel();
                                    }

                                }, 0, 20*25);

                            } //Zombie Boss

                            if ((Endless.wave != 0) && ((Endless.wave % 20) == 0 | bsc == 169) && (bosstype == 1)) {
                                Skeleton skelboss = spawnBossSkeleton(allPlayers);
                                Endless.bmobbar = createBossBarSkeleton();
//                                bss = 1;
                                wavechk = 1;

                                Bukkit.getServer().getScheduler().runTaskTimer(plugin, bukkitTask -> {

                                    if (!skelboss.isDead()) {
                                        bmobbar.setProgress(skelboss.getHealth() / skelboss.getMaxHealth());
                                    } else {
                                        List<Player> players = bmobbar.getPlayers();
                                        for (Player player : players) {
                                            bmobbar.removePlayer(player);
                                        }
                                        bossCurseEffectSkele();
                                        Bukkit.broadcastMessage(ChatColor.AQUA + "-+-+-" + ChatColor.BOLD + "" + ChatColor.GRAY + "Benji has been Killed. But who was he?" + ChatColor.AQUA + "-+-+-");
                                        bmobbar.setVisible(false);
                                        bukkitTask.cancel();
                                    }

                                }, 0, 1);

                                BukkitScheduler bossAbi2 = Bukkit.getServer().getScheduler();
                                bossAbi2.runTaskTimer(plugin, boss2AbilityTask -> {

                                    abi2 = bossSpC(15);

                                    if (!skelboss.isDead()) {
                                        if (abi2 == 1) {
                                            SkeletonTeleportAbility(skelboss,allPlayers);
                                        } // teleport
                                        else if (abi2 == 2) {
                                            Bukkit.broadcastMessage(ChatColor.DARK_BLUE + "[Benji]: " + ChatColor.GRAY + "You can't attack what you can't see and you can't harm what you can't touch , zehahaha!");
                                            skelboss.setInvisible(true);
                                            skelboss.setGlowing(false);
                                            skelabi3s = 1;

                                            BukkitScheduler invi = Bukkit.getServer().getScheduler();
                                            invi.runTaskLater(plugin, invitask -> {
                                                Bukkit.broadcastMessage(ChatColor.DARK_BLUE + "[Benji]: " + ChatColor.GRAY + "Oof , now you can see me huh? Who am I?");
                                                skelboss.setInvisible(false);
                                                skelabi3s = 0;
                                                skelboss.setGlowing(true);
                                            }, 150L);
                                        } // invis + invinci
                                        else if (abi2 == 3) {
                                            Bukkit.broadcastMessage(ChatColor.DARK_BLUE + "[Benji]: " + ChatColor.GRAY + "The jockeys~! Come forth from Mordor!");
                                            SkeletonJockeys(skelboss);
                                        } //cave spider jockey mob spawn
                                        else if (abi2 == 4) {
                                            Bukkit.broadcastMessage(ChatColor.DARK_BLUE + "[Benji]: " + ChatColor.GRAY + "Cavalry! I choose you!");
                                            SkeletonMinion(skelboss);
                                        } //cavalry mob spawn
                                    } else {
//                                        bss = 0;
                                        boss2AbilityTask.cancel();
                                    }

                                }, 0, 25 * 20);

                            } //Skeleton Boss

                        }
                    }, 0, 20);
                    break;

            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (skelabi3s==1){
            if (event.getEntity().getUniqueId()==boss_uuid){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void bossSpawnStateSetterDeath(EntityDeathEvent event) {
        if (event.getEntity().getType() == EntityType.ZOMBIE || event.getEntity().getType() == EntityType.SKELETON) {
            if (event.getEntity().getUniqueId() == boss_uuid) {
                bss = 0;
                boss_uuid = null;
                Bukkit.getServer().broadcastMessage("<--->");

                if(event.getEntity().getKiller() instanceof Player){
                    Bukkit.broadcastMessage(ChatColor.AQUA + "-+-+-" + ChatColor.GOLD + "All players have been awarded with 15$ each." + ChatColor.AQUA + "-+-+-");
                    for (Player pm : Bukkit.getServer().getOnlinePlayers()) {
                        int m = RaidGame.moneydb.get(pm.getDisplayName());
                        m += 15;
                        RaidGame.moneydb.replace(pm.getDisplayName(), m);
                    } // adding money to all players online.

            }

            }
        }
    }



}



