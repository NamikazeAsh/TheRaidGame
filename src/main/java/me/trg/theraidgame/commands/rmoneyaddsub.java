package me.trg.theraidgame.commands;

import me.trg.theraidgame.RaidGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rmoneyaddsub implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if(args.length>=2){
            if (args[0].equalsIgnoreCase("add")) {
                try {
                    int rmoneyadd = Integer.parseInt(args[1]);
                    Player p = (Player) sender;
                    int money = RaidGame.moneydb.get(p.getDisplayName());
                    money+=rmoneyadd;
                    RaidGame.moneydb.replace(p.getDisplayName(),money);
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Money added for player: "+ChatColor.AQUA + p.getDisplayName() + ChatColor.GREEN + " | Money added: " + ChatColor.AQUA + rmoneyadd);
                }
                catch (IllegalArgumentException arg){
                    Player p = (Player) sender;
                    p.sendMessage("Invalid argument : /rmoney add <amount>");
                }
                catch (ArrayIndexOutOfBoundsException e){
                    Player p = (Player) sender;
                    p.sendMessage(ChatColor.GREEN+"Invalid argument : /rmoney add <amount>");
                }
            }
            else if(args[0].equalsIgnoreCase("sub")){
                try{
                    int rmoneysub = Integer.parseInt(args[1]);
                    Player p = (Player) sender;
                    int money = RaidGame.moneydb.get(p.getDisplayName());
                    money= money - rmoneysub;
                    RaidGame.moneydb.replace(p.getDisplayName(),money);
                    p.sendMessage(ChatColor.GREEN + "Money deducted from"+ChatColor.AQUA+" "+p.getDisplayName()+ChatColor.GREEN + " | Money deducted: "+ChatColor.AQUA + rmoneysub);
                }
                catch(IllegalArgumentException arg){
                    Player p = (Player) sender;
                    p.sendMessage("Invalid argument : /rmoney sub <amount>");
                }
                catch (ArrayIndexOutOfBoundsException e){
                    Player p = (Player) sender;
                    p.sendMessage(ChatColor.GREEN+"Invalid argument : /rmoney sub <amount>");
                }
            }
            else if(args[0].equalsIgnoreCase("give")){
                try{
                    Player p = (Player) sender;
                    String p_giver = p.getDisplayName();
                    String p_receiver = args[1];

                    int money_sender,money_receiver,money_sent;

                    money_sender = RaidGame.moneydb.get(p_giver);
                    money_receiver = RaidGame.moneydb.get(p_receiver);
                    money_sent = Integer.parseInt(args[2]);
                    if(money_sender>=money_sent) {
                        money_sender -= money_sent;
                        money_receiver += money_sent;

                        RaidGame.moneydb.replace(p_giver, money_sender);
                        RaidGame.moneydb.replace(p_receiver, money_receiver);
                        p.sendMessage(ChatColor.GREEN + "Money sent to " + p_receiver + " <" + money_sent + "$>");
                        Player pr = Bukkit.getPlayer(args[1]);
                        pr.sendMessage(ChatColor.GREEN + "Money received from " + p_giver + " <" + money_sent + "$>");
                    }
                    else{
                        p.sendMessage(ChatColor.RED + "Insufficient money.");
                    }
                }
                catch(IllegalArgumentException | ArrayIndexOutOfBoundsException arg){
                    Player p = (Player) sender;
                    p.sendMessage(ChatColor.GREEN+"Invalid argument : /rmoney give <playername> <amount>");
                } catch (NullPointerException e){
                    Player p = (Player) sender;
                    p.sendMessage(ChatColor.GREEN+"Please check the entered command or if the player is online.");
                }

            }

        }

        else{
            Player p = (Player) sender;
            p.sendMessage("Correct Command : /rmoney <add/sub/give> <amount>");
        }


        return true;
    }
}
