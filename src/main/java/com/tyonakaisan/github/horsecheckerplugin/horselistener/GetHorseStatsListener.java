package com.tyonakaisan.github.horsecheckerplugin.horselistener;

import com.tyonakaisan.github.horsecheckerplugin.HorseCheckerPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public class GetHorseStatsListener implements Listener {

    private final HorseCheckerPlugin plugin = (HorseCheckerPlugin) HorseCheckerPlugin.getPlugin(HorseCheckerPlugin.class);


        @EventHandler
        public void onClickHorse(EntityDamageByEntityEvent e) {
            EntityType entityType = e.getEntityType();
            if (e.getDamager() instanceof Player) {
                Player player = (Player) e.getDamager();
                ItemStack item = ((Player) e.getDamager()).getInventory().getItemInMainHand();
                if (item.getType() == Material.matchMaterial(plugin.getConfigManager().STATS_ITEM)) {
                    if (entityType == EntityType.HORSE ||
                        entityType == EntityType.DONKEY ||
                        entityType == EntityType.MULE ||
                        entityType == EntityType.SKELETON_HORSE ||
                        entityType == EntityType.ZOMBIE_HORSE ) {
                        e.setCancelled(true);

                        DecimalFormat df2 = new DecimalFormat("0.00"),
                                df1 = new DecimalFormat("0.0");

                        AbstractHorse horse = (AbstractHorse) e.getEntity();

                        double maxhealth = horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(),
                               health = horse.getHealth(),
                               speed = horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue(),
                               jump = horse.getJumpStrength(),
                               tame = horse.getDomestication(),
                               age = horse.getAge();

                        int MaxHealth = (int) maxhealth,
                            Health = (int) health;

                        double speedPercent = speed * 42.163,
                               jumpPercent = -0.1817584952 * jump * jump * jump + 3.689713992 * jump * jump + 2.128599134 * jump - 0.343930367,
                               breedingpercent = age / 20,
                               agepercent = age / 20 * -1;

                        int agePercent = (int) agepercent,
                            breedingPercent = (int) breedingpercent;

                        ChatColor BAR_COLOR = ChatColor.BLUE,
                                  LABEL_COLOR = ChatColor.AQUA,
                                  PERCENT_COLOR = ChatColor.WHITE,
                                  UNIT_COLOR = ChatColor.AQUA;

                        String HEALTH_RESULT_MESSAGE = LABEL_COLOR + "--Health-- " + PERCENT_COLOR + Health + UNIT_COLOR + " / " + PERCENT_COLOR + MaxHealth + UNIT_COLOR + " HP",
                               SPEED_RESULT_MESSAGE = LABEL_COLOR + "--Speed-- " + PERCENT_COLOR + df2.format(speedPercent) + UNIT_COLOR + " block/s",
                               JUMP_RESULT_MESSAGE = LABEL_COLOR + "--Jump-- " + PERCENT_COLOR + df2.format(jumpPercent) + UNIT_COLOR + " block",
                               TAME_RESULT_MESSAGE = LABEL_COLOR + "--Tame-- " + PERCENT_COLOR + df1.format(tame) + UNIT_COLOR + " %",
                               BREEDING_POSSIBLE_MESSAGE = ChatColor.GOLD + "餌付けが可能になりました!",
                               BREEDING_COOL_DOWN_MESSAGE = LABEL_COLOR + "--BreedingCoolDown-- " + PERCENT_COLOR + breedingPercent + UNIT_COLOR + " s",
                               AGE_RESULT_MESSAGE = LABEL_COLOR + "--Age-- " + PERCENT_COLOR + agePercent + UNIT_COLOR + " s",
                               OWNER_NONE_MESSAGE = LABEL_COLOR + "--Owner-- " + PERCENT_COLOR + "NONE";

                        player.sendMessage(BAR_COLOR + "----------" + ChatColor.GOLD + "[" + ChatColor.GREEN + "HorseChecker" + ChatColor.GOLD + "]" + BAR_COLOR + "----------");

                        if (plugin.getConfigManager().MESSAGE_CHECK_HEALTH) {
                            player.sendMessage(HEALTH_RESULT_MESSAGE);
                        }

                        if (plugin.getConfigManager().MESSAGE_CHECK_SPEED) {
                            player.sendMessage(SPEED_RESULT_MESSAGE);
                        }

                        if (plugin.getConfigManager().MESSAGE_CHECK_JUMP) {
                            player.sendMessage(JUMP_RESULT_MESSAGE);
                        }

                        if (plugin.getConfigManager().MESSAGE_CHECK_TAME) {
                            player.sendMessage(TAME_RESULT_MESSAGE);
                        }

                        if (plugin.getConfigManager().MESSAGE_CHECK_OWNER) {
                            if (horse.getOwner() == null) {
                                player.sendMessage(OWNER_NONE_MESSAGE);
                            } else {
                                String OWNER_RESULT_MESSAGE = LABEL_COLOR + "--Owner-- " + PERCENT_COLOR + (horse.getOwner().getName());
                                player.sendMessage(OWNER_RESULT_MESSAGE);
                            }
                        }

                        if (plugin.getConfigManager().MESSAGE_CHECK_AGE && age > 0) {
                            player.sendMessage(BREEDING_COOL_DOWN_MESSAGE);
                        } else if (age == 0) {
                            player.sendMessage(BREEDING_POSSIBLE_MESSAGE);
                        } else if (age < 0) {
                            player.sendMessage(AGE_RESULT_MESSAGE);
                        }

                        player.sendMessage(BAR_COLOR + "---------------------------------");
                    }
                }
            }
        }
}
