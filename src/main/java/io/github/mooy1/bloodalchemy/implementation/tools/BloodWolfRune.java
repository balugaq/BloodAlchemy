package io.github.mooy1.bloodalchemy.implementation.tools;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.mooy1.bloodalchemy.BloodAlchemy;
import io.github.mooy1.bloodalchemy.utils.BloodUtils;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;

/**
 * A rune that gives wolves the ability to heal themselves and drop blood when attacking
 */
public final class BloodWolfRune extends SlimefunItem implements Listener, NotPlaceable {

    private final NamespacedKey bloodWolf = BloodAlchemy.inst().getKey("blood_wolf");

    public BloodWolfRune(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemHandler(getEntityHandler(), (ItemUseHandler) e -> e.setUseBlock(Event.Result.DENY));

        Bukkit.getPluginManager().registerEvents(this, BloodAlchemy.inst());
    }

    private EntityInteractHandler getEntityHandler() {
        return (e, item, offHand) -> {
            e.setCancelled(true);

            Entity wolf = e.getRightClicked();

            // Make sure it is a normal wolf
            if (wolf instanceof Wolf) {
                Player p = e.getPlayer();

                // Mak sure the wolf is tamed
                if (!((Wolf) wolf).isTamed()) {
                    p.sendMessage(ChatColor.RED + "你必须先驯服狼!");
                    return;
                }

                PersistentDataContainer con = wolf.getPersistentDataContainer();

                if (con.has(this.bloodWolf, PersistentDataType.BYTE)) {
                    p.sendMessage(ChatColor.RED + "这只狼已经是血狼了");

                } else {
                    p.sendMessage(ChatColor.GREEN + "变成了血狼!");

                    con.set(this.bloodWolf, PersistentDataType.BYTE, (byte) 1);
                    item.setAmount(item.getAmount() - 1);
                    BloodUtils.playEffect(wolf.getLocation(), 40);
                }
            }
        };
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    private void onBloodWolfAttack(@Nonnull EntityDamageByEntityEvent e) {

        // Check for blood wolf
        if (e.getDamager() instanceof Wolf wolf
                && e.getDamager().getPersistentDataContainer().has(this.bloodWolf, PersistentDataType.BYTE)) {

            // Heal
            double max = wolf.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            wolf.setHealth(Math.min(wolf.getHealth() + 4, max));

            if (ThreadLocalRandom.current().nextBoolean()) {

                // Drop blood
                BloodUtils.dropBlood(e.getEntity().getLocation(), 1);
            }
        }
    }

}
