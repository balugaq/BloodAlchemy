package io.github.mooy1.bloodalchemy.implementation.blocks;

import javax.annotation.Nonnull;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import io.github.mooy1.bloodalchemy.BloodAlchemy;
import io.github.thebusybiscuit.slimefun4.api.events.BlockPlacerPlaceEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public final class SlimefunSeed extends SlimefunItem {

    public static final RecipeType TYPE = new RecipeType(BloodAlchemy.inst().getKey("farming"),
            new CustomItemStack(Material.DIAMOND_HOE, "&e快了..."));

    private final SlimefunItemStack crop;

    public SlimefunSeed(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, SlimefunItemStack crop) {
        super(category, item, recipeType, recipe);
        this.crop = crop;

        addItemHandler(getPlaceHandler());
    }

    private BlockPlaceHandler getPlaceHandler() {
        return new BlockPlaceHandler(true) {

            @Override
            public void onPlayerPlace(@Nonnull BlockPlaceEvent e) {
                Slimefun.getDatabaseManager().getBlockDataController().removeBlock(e.getBlock().getLocation());
                Slimefun.getDatabaseManager().getBlockDataController().createBlock(e.getBlock().getLocation(), SlimefunSeed.this.crop.getItemId());
            }

            @Override
            public void onBlockPlacerPlace(@Nonnull BlockPlacerPlaceEvent e) {
                Slimefun.getDatabaseManager().getBlockDataController().removeBlock(e.getBlock().getLocation());
                Slimefun.getDatabaseManager().getBlockDataController().createBlock(e.getBlock().getLocation(), SlimefunSeed.this.crop.getItemId());
            }

        };
    }

}
