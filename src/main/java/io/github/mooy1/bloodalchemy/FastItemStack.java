package io.github.mooy1.bloodalchemy;

import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.mooy1.infinitylib.common.StackUtils;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class FastItemStack extends ItemStack {
    @Getter
    private final ItemStack original;
    private Optional<String> idCache;
    private ItemMeta metaCache;

    @Nonnull
    public static FastItemStack of(@Nonnull ItemStack item) {
        return item instanceof FastItemStack ? (FastItemStack)item : new FastItemStack(item);
    }

    @Nonnull
    public static FastItemStack[] fastArray(@Nonnull ItemStack[] items) {
        if (items instanceof FastItemStack[]) {
            return (FastItemStack[])items;
        } else {
            FastItemStack[] arr = new FastItemStack[items.length];

            for(int i = 0; i < items.length; ++i) {
                if (items[i] != null) {
                    arr[i] = of(items[i]);
                }
            }

            return arr;
        }
    }

    @Nullable
    public String getID() {
        return this.cacheID().orElse(null);
    }

    @Nonnull
    public String getIDorType() {
        return this.cacheID().orElseGet(() -> this.getType().toString());
    }

    @Nonnull
    private Optional<String> cacheID() {
        if (this.idCache == null) {
            return this.hasItemMeta() ? (this.idCache = Optional.ofNullable(StackUtils.getId(this.getItemMeta()))) : (this.idCache = Optional.empty());
        } else {
            return this.idCache;
        }
    }

    public boolean fastEquals(@Nullable ItemStack item) {
        if (item == null) {
            return false;
        } else {
            String id = this.getID();
            String other = StackUtils.getId(item);
            if (id != null) {
                return id.equals(other);
            } else {
                return other == null && this.getType() == item.getType();
            }
        }
    }

    @Nonnull
    public ItemMeta cloneItemMeta() {
        return this.original.getItemMeta();
    }

    @Nonnull
    public ItemMeta getItemMeta() {
        if (this.metaCache == null) {
            this.metaCache = this.cloneItemMeta();
        }

        return this.metaCache;
    }

    public boolean setItemMeta(@Nullable ItemMeta itemMeta) {
        return this.original.setItemMeta(this.metaCache = itemMeta);
    }

    public boolean hasItemMeta() {
        return this.original.hasItemMeta();
    }

    @Nonnull
    public Material getType() {
        return this.original.getType();
    }

    public void setType(@Nonnull Material type) {
        this.original.setType(type);
    }

    public int getAmount() {
        return this.original.getAmount();
    }

    public void setAmount(int amount) {
        this.original.setAmount(amount);
    }

    public String toString() {
        return this.original.toString();
    }

    public boolean equals(Object obj) {
        return this.original.equals(obj);
    }

    public boolean isSimilar(ItemStack stack) {
        return this.original.isSimilar(stack);
    }

    @Nonnull
    public ItemStack clone() {
        return this.original.clone();
    }

    public int hashCode() {
        return this.original.hashCode();
    }

    public boolean containsEnchantment(@Nonnull Enchantment ench) {
        return this.original.containsEnchantment(ench);
    }

    public int getEnchantmentLevel(@Nonnull Enchantment ench) {
        return this.original.getEnchantmentLevel(ench);
    }

    @Nonnull
    public Map<Enchantment, Integer> getEnchantments() {
        return this.original.getEnchantments();
    }

    public void addUnsafeEnchantment(@Nonnull Enchantment ench, int level) {
        this.original.addUnsafeEnchantment(ench, level);
    }

    public int removeEnchantment(@Nonnull Enchantment ench) {
        return this.original.removeEnchantment(ench);
    }

    public FastItemStack(ItemStack original) {
        this.original = original;
    }

}
