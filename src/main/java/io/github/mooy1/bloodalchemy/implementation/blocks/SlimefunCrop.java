package io.github.mooy1.bloodalchemy.implementation.blocks;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Bukkit;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import io.github.mooy1.bloodalchemy.BloodAlchemy;
import io.github.thebusybiscuit.slimefun4.api.events.AndroidFarmEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public final class SlimefunCrop extends SlimefunItem implements Listener {

    private final SlimefunItemStack seed;

    public SlimefunCrop(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, SlimefunItemStack seed) {
        super(category, item, recipeType, recipe);
        this.seed = seed;

        addItemHandler(getBreakHandler());

        Bukkit.getPluginManager().registerEvents(this, BloodAlchemy.inst());
    }

    private BlockBreakHandler getBreakHandler() {
        return new BlockBreakHandler(true, true) {

            @Override
            public void onPlayerBreak(@Nonnull BlockBreakEvent e, @Nonnull ItemStack item, @Nonnull List<ItemStack> drops) {
                BlockData data = e.getBlock().getBlockData();
                if (data instanceof Ageable ageable) {
                    if (ageable.getAge() == ageable.getMaximumAge()) {
                        // Drop a crop and extra seed
                        drops.add(getItem().clone());
                        drops.add(SlimefunCrop.this.seed.clone());
                    }
                }
            }

        };
    }

    @Nonnull
    @Override
    public Collection<ItemStack> getDrops() {
        return Collections.singletonList(SlimefunCrop.this.seed.clone());
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    private void onAndroidFarm(@Nonnull AndroidFarmEvent e) {
        if (e.getDrop() != null && BlockStorage.check(e.getBlock(), getId())) {
            e.setDrop(getItem().clone());
        }
    }

}
