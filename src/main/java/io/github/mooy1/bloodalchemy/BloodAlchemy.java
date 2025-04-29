package io.github.mooy1.bloodalchemy;

import javax.annotation.Nonnull;

import io.github.mooy1.bloodalchemy.implementation.Items;
import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.mooy1.infinitylib.metrics.bukkit.Metrics;
import org.bukkit.NamespacedKey;

public final class BloodAlchemy extends AbstractAddon {
    
    private static BloodAlchemy instance;

    public BloodAlchemy() {
        super("balugaq", "BloodAlchemy", " ", " ");
    }

    public static BloodAlchemy inst() {
        return instance;
    }

    protected Metrics setupMetrics() {
        return new Metrics(this, 11483);
    }

    @Nonnull
    protected String getGithubPath() {
        return "Mooy1/BloodAlchemy/master";
    }

    @Override
    protected void enable() {
        // All of the config and auto update stuff is taken care of in AbstractAddon#onEnable
        Items.setup(instance = this);
        setupMetrics();
    }

    @Override
    protected void disable() {

    }

    public NamespacedKey getKey(String key) {
        return new NamespacedKey(this, key);
    }
}
