package com.tyonakaisan.github.horsecheckerplugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class maincommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "/hch helpでコマンドを確認してください");
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(ChatColor.BLUE + "+----------------------------------");
                    sender.sendMessage(ChatColor.BLUE + " |" + ChatColor.GOLD + " /hch help" + ChatColor.WHITE + " → この画面");
                    sender.sendMessage(ChatColor.BLUE + " |" + ChatColor.GOLD + " /hch statsinfo" + ChatColor.WHITE + " → ステータス画面の説明");
                    sender.sendMessage(ChatColor.BLUE + "+----------------------------------");
                    return true;
                } else if (args[0].equalsIgnoreCase("statsinfo")) {
                    sender.sendMessage(ChatColor.BLUE + "+-------------------------------------------------");
                    sender.sendMessage(ChatColor.BLUE + " | " + ChatColor.GOLD + "Health" + ChatColor.WHITE + " → " + ChatColor.GOLD + "現在体力/馬の最大体力 " + ChatColor.WHITE + "で表示されます");
                    sender.sendMessage(ChatColor.BLUE + " | " + ChatColor.GOLD + "Speed" + ChatColor.WHITE + " → " + ChatColor.GOLD + "1秒間で進めるブロック数 " + ChatColor.WHITE + "が表示されます");
                    sender.sendMessage(ChatColor.BLUE + " | " + ChatColor.GOLD + "Jump" + ChatColor.WHITE + " → " + ChatColor.GOLD + "跳べるブロック数 " + ChatColor.WHITE + "が表示されます");
                    sender.sendMessage(ChatColor.BLUE + " | " + ChatColor.GOLD + "Tame" + ChatColor.WHITE + " → " + ChatColor.GOLD + "手なづけ度 " + ChatColor.WHITE + "が表示されます");
                    sender.sendMessage(ChatColor.BLUE + " | " + ChatColor.GOLD + "Owner" + ChatColor.WHITE + " → " + ChatColor.GOLD + "所有者 " + ChatColor.WHITE + "が表示されます");
                    sender.sendMessage(ChatColor.BLUE + " | " + ChatColor.GOLD + "Age" + ChatColor.WHITE + " → " + ChatColor.GOLD + "成体になるまでの時間 " + ChatColor.WHITE + "が表示されます");
                    sender.sendMessage(ChatColor.BLUE + " | " + ChatColor.GOLD + "BreedingCoolDown" + ChatColor.WHITE + " → " + ChatColor.GOLD + "繫殖可能になるまでの時間 " + ChatColor.WHITE + "が表示されます");
                    sender.sendMessage(ChatColor.BLUE + "+-------------------------------------------------");
                    return true;
                }
            }
        }
        return true;
    }
}


