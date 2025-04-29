//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.github.mooy1.bloodalchemy;

import lombok.Getter;

public final class RecipeOutput<O> {
    @Getter
    private final O output;
    private final AbstractRecipe input;

    public FastItemStack[] getOriginalInput() {
        return this.input.getRawInput();
    }

    public FastItemStack[] getRecipeInput() {
        return this.input.getRecipeInput();
    }

    public void consumeInput() {
        this.input.consumeMatchingRecipe();
    }

    public O getAndConsume() {
        this.consumeInput();
        return this.output;
    }

    RecipeOutput(O output, AbstractRecipe input) {
        this.output = output;
        this.input = input;
    }

}
