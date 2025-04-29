//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.github.mooy1.bloodalchemy;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

public final class ShapelessRecipe extends AbstractRecipe {
    private final Map<String, Integer> map = new HashMap<>(8);

    public ShapelessRecipe(FastItemStack[] input) {
        super(input);

        for(FastItemStack item : input) {
            if (item != null) {
                this.map.compute(item.getIDorType(), (k, v) -> v == null ? item.getAmount() : v + item.getAmount());
            }
        }

    }

    public int hashCode() {
        int hash = 0;

        for(String s : this.map.keySet()) {
            hash += s.hashCode();
        }

        return hash;
    }

    protected boolean matches(@Nonnull AbstractRecipe input) {
        Map<String, Integer> inputMap = ((ShapelessRecipe)input).map;

        for(Map.Entry<String, Integer> entry : this.map.entrySet()) {
            if (inputMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }

        return true;
    }

    protected void consume(@Nonnull AbstractRecipe input) {
        for(Map.Entry<String, Integer> entry : this.map.entrySet()) {
            int rem = entry.getValue();

            for(FastItemStack item : input.getRawInput()) {
                if (item != null && item.getIDorType().equals(entry.getKey())) {
                    int amt = item.getAmount();
                    if (amt >= rem) {
                        item.setAmount(amt - rem);
                        break;
                    }

                    rem -= amt;
                }
            }
        }

    }
}
