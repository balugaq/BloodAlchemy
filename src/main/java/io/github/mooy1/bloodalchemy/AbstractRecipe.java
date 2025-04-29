//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.github.mooy1.bloodalchemy;

import javax.annotation.Nonnull;

public abstract class AbstractRecipe {
    private final FastItemStack[] rawInput;
    private AbstractRecipe matchingRecipe;

    public final boolean equals(Object recipe) {
        return recipe instanceof AbstractRecipe && (this.matchingRecipe = (AbstractRecipe) recipe).matches(this);
    }

    public abstract int hashCode();

    protected abstract boolean matches(@Nonnull AbstractRecipe var1);

    protected abstract void consume(@Nonnull AbstractRecipe var1);

    protected final FastItemStack[] getRawInput() {
        return this.rawInput;
    }

    final FastItemStack[] getRecipeInput() {
        return this.matchingRecipe.rawInput;
    }

    final void consumeMatchingRecipe() {
        this.matchingRecipe.consume(this);
    }

    public AbstractRecipe(FastItemStack[] rawInput) {
        this.rawInput = rawInput;
    }
}
