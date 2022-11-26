package me.trg.theraidgame.events;

import me.trg.theraidgame.RaidGame;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class diffarmormod implements Listener {

    private final RaidGame plugin;
    public diffarmormod(RaidGame plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void armorchange(CreatureSpawnEvent e){


        if(clickevent.diffc==0){
            if(e.getEntityType()==EntityType.PILLAGER){
                ItemStack reset1 = new ItemStack(Material.CROSSBOW);
                e.getEntity().getEquipment().setItemInMainHand(reset1);
            }
            if(e.getEntityType()==EntityType.VINDICATOR){
                ItemStack reset2 = new ItemStack(Material.IRON_AXE);
                e.getEntity().getEquipment().setItemInMainHand(reset2);
            }
            if(e.getEntityType()==EntityType.RAVAGER){
                e.getEntity().removePotionEffect(PotionEffectType.SPEED);
            }
        }

        if(e.getEntityType()== EntityType.PILLAGER){
//            Pillager pillager = (Pillager) e.getEntity();
            if(clickevent.diffc == 2){
                ItemStack p2 = new ItemStack(Material.CROSSBOW);
                p2.addEnchantment(Enchantment.PIERCING,1);
                p2.addEnchantment(Enchantment.QUICK_CHARGE,1);
                e.getEntity().getEquipment().setItemInMainHand(p2);
            }

            if(clickevent.diffc == 3){
                ItemStack p3 = new ItemStack(Material.CROSSBOW);
                p3.addEnchantment(Enchantment.QUICK_CHARGE,2);
                p3.addEnchantment(Enchantment.PIERCING,2);
                e.getEntity().getEquipment().setItemInMainHand(p3);
            }

            if(clickevent.diffc == 4){
                ItemStack p4 = new ItemStack(Material.CROSSBOW);
                p4.addEnchantment(Enchantment.QUICK_CHARGE,3);
                p4.addEnchantment(Enchantment.PIERCING,4);
                e.getEntity().getEquipment().setItemInMainHand(p4);
            }
        }


        if(e.getEntityType() == EntityType.VINDICATOR){
            if(clickevent.diffc==3){
                ItemStack v3 = new ItemStack(Material.DIAMOND_AXE);
                e.getEntity().getEquipment().setItemInMainHand(v3);
                ItemStack v3_1 = new ItemStack(Material.IRON_CHESTPLATE);
                e.getEntity().getEquipment().setChestplate(v3_1);
            }

            if(clickevent.diffc==4){
                ItemStack v4 = new ItemStack(Material.DIAMOND_AXE);
                v4.addEnchantment(Enchantment.DAMAGE_ALL,1);
                ItemStack v4_1 = new ItemStack(Material.IRON_CHESTPLATE);
                v4_1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,2);
                e.getEntity().getEquipment().setChestplate(v4_1);
                e.getEntity().getEquipment().setItemInMainHand(v4);
            }
        }


        if(e.getEntityType() == EntityType.RAVAGER){
            if(clickevent.diffc==4){
                PotionEffect re4 = new PotionEffect(PotionEffectType.SPEED,100000,1);
                e.getEntity().addPotionEffect(re4);
            }
        }

    }

}
