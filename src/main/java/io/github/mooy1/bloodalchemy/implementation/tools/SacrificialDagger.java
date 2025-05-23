package io.github.mooy1.bloodalchemy.implementation.tools;

import javax.annotation.Nonnull;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import io.github.mooy1.bloodalchemy.BloodAlchemy;
import io.github.mooy1.bloodalchemy.implementation.Items;
import io.github.mooy1.bloodalchemy.utils.BloodUtils;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityKillHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;

/**
 * A dagger which drops blood from attacked entities
 */
public final class SacrificialDagger extends SlimefunItem implements Listener, NotPlaceable {

    public static final RecipeType TYPE = new RecipeType(BloodAlchemy.inst().getKey("sacrificial_dagger"), Items.SACRIFICIAL_DAGGER);

    public SacrificialDagger(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        Bukkit.getPluginManager().registerEvents(this, BloodAlchemy.inst());

        addItemHandler(getUseHandler(), getKillHandler());
    }

    private ItemUseHandler getUseHandler() {
        return e -> {
            e.setUseItem(Event.Result.DENY);
            e.setUseBlock(Event.Result.DENY);

            Player p = e.getPlayer();
            p.setHealth(Math.max(0, p.getHealth() - 4));

            Location l = p.getLocation();
            BloodUtils.dropBlood(l, 1);
            BloodUtils.playEffect(l, 20);
        };
    }

    private EntityKillHandler getKillHandler() {
        return (e, entity, killer, item1) -> {
            Location l = entity.getLocation();
            BloodUtils.dropBlood(l, 2);
            BloodUtils.playEffect(l, 20);
        };
    }

    // Replace with WeaponUseHandler once merged
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    private void onAttack(@Nonnull EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player p) {

            if (isItem(p.getInventory().getItemInMainHand()) && canUse(p, true)) {

                Location l = e.getEntity().getLocation();
                BloodUtils.dropBlood(l, 1);
                BloodUtils.playEffect(l, 10);
            }
        }

    }

}
