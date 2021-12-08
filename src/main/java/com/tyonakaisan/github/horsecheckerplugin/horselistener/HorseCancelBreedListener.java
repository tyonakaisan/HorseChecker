package com.tyonakaisan.github.horsecheckerplugin.horselistener;


import com.tyonakaisan.github.horsecheckerplugin.HorseCheckerPlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class HorseCancelBreedListener implements Listener {

    private final HorseCheckerPlugin plugin = (HorseCheckerPlugin) HorseCheckerPlugin.getPlugin(HorseCheckerPlugin.class);

    @EventHandler
    public void onCancelledTame(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        if (plugin.getConfigManager().BREED_CANCEL_CHECK) {
            ItemStack item = (e.getPlayer().getInventory().getItemInMainHand());
            if (item.getType() == Material.GOLDEN_CARROT ||
                item.getType() == Material.GOLDEN_APPLE ||
                item.getType() == Material.ENCHANTED_GOLDEN_APPLE ||
                item.getType() == Material.HAY_BLOCK
            ) {
                if (e.getRightClicked() instanceof Horse ||
                    e.getRightClicked() instanceof Donkey ||
                    e.getRightClicked() instanceof Mule ||
                    e.getRightClicked() instanceof SkeletonHorse ||
                    e.getRightClicked() instanceof ZombieHorse
                ) {
                    AbstractHorse horse = (AbstractHorse) e.getRightClicked();

                    double MaxHealth = horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(),
                           Health = horse.getHealth(),
                           Age = horse.getAge(),
                           LoveMode = horse.getLoveModeTicks(),
                           LoveModeTime = LoveMode / 20 ,
                           BreedCoolTime = Age / 20 ;

                    int maxhealth = (int) MaxHealth,
                        health = (int) Health,
                        lovemodetime = (int) LoveModeTime,
                        breedcooltime = (int) BreedCoolTime;

                    String BREEDING_CANCEL_MESSAGE_1 = ChatColor.RED + "繫殖クールタイム(" + breedcooltime + "s)なので餌付けは出来ません!",
                           BREEDING_CANCEL_MESSAGE_2 = ChatColor.RED + "この馬は繫殖モード中(" + lovemodetime + "s)なので餌付けは出来ません!";

                    BaseComponent[] CANCEL_COMPONENT_1 = TextComponent.fromLegacyText(BREEDING_CANCEL_MESSAGE_1),
                                    CANCEL_COMPONENT_2 = TextComponent.fromLegacyText(BREEDING_CANCEL_MESSAGE_2);

                    //繫殖クールタイム中&体力がMAXであれば実行
                    if (Age > 0 && health == maxhealth) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, CANCEL_COMPONENT_1);
                        e.setCancelled(true);
                    //繫殖モード中(ハートが出てる時)&体力がMAXであれば実行
                    } else if (LoveMode > 0 && health == maxhealth) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, CANCEL_COMPONENT_2);
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
