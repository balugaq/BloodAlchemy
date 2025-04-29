package io.github.mooy1.bloodalchemy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bukkit.inventory.ItemStack;

public final class RecipeMap<O> {
    private final Function<FastItemStack[], ? extends AbstractRecipe> recipeConstructor;
    private final Map<AbstractRecipe, O> recipes = new ConcurrentHashMap<>();

    public void put(@Nonnull ItemStack[] rawInput, @Nonnull O output) {
        this.recipes.put(this.toRecipe(rawInput), output);
    }

    @Nullable
    public RecipeOutput<O> get(@Nonnull ItemStack[] rawInput) {
        AbstractRecipe input = this.toRecipe(rawInput);
        O output = this.recipes.get(input);
        return output != null ? new RecipeOutput<>(output, input) : null;
    }

    @Nullable
    public O getAndConsume(@Nonnull ItemStack[] rawInput) {
        AbstractRecipe input = this.toRecipe(rawInput);
        O output = this.recipes.get(input);
        if (output != null) {
            input.consumeMatchingRecipe();
            return output;
        } else {
            return null;
        }
    }

    @Nullable
    public O getNoConsume(@Nonnull ItemStack[] rawInput) {
        return this.recipes.get(this.toRecipe(rawInput));
    }

    @Nonnull
    private AbstractRecipe toRecipe(@Nonnull ItemStack[] rawInput) {
        return this.recipeConstructor.apply(FastItemStack.fastArray(rawInput));
    }

    public RecipeMap(Function<FastItemStack[], ? extends AbstractRecipe> recipeConstructor) {
        this.recipeConstructor = recipeConstructor;
    }
}
