package io.github.mooy1.bloodalchemy.implementation;

import java.util.Arrays;
import java.util.Collections;

import javax.annotation.Nonnull;

import lombok.experimental.UtilityClass;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.mooy1.bloodalchemy.BloodAlchemy;
import io.github.mooy1.bloodalchemy.implementation.blocks.BloodHopper;
import io.github.mooy1.bloodalchemy.implementation.blocks.SlimefunSeed;
import io.github.mooy1.bloodalchemy.implementation.blocks.SlimefunCrop;
import io.github.mooy1.bloodalchemy.implementation.blocks.SlimefunShroom;
import io.github.mooy1.bloodalchemy.implementation.blocks.altar.BloodAltar;
import io.github.mooy1.bloodalchemy.implementation.tools.BloodTotem;
import io.github.mooy1.bloodalchemy.implementation.tools.BloodWolfRune;
import io.github.mooy1.bloodalchemy.implementation.tools.HarvestScythe;
import io.github.mooy1.bloodalchemy.implementation.tools.InfusedVampireBlade;
import io.github.mooy1.bloodalchemy.implementation.tools.SacrificialDagger;
import io.github.mooy1.bloodalchemy.utils.BloodUtils;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

/**
 * Items added in this addon
 */
@UtilityClass
public final class Items {

    //region Items
    public static final SlimefunItemStack BLOOD_GEM = new SlimefunItemStack(
            "BLOOD_GEM",
            Material.RED_DYE,
            "&c血宝石",
            "&7充满血的力量的钻石"
    );
    public static final SlimefunItemStack BLOOD = new SlimefunItemStack(
            "BLOOD",
            Material.REDSTONE,
            "&4血",
            "&7闪烁着力量"
    );
    //endregion

    //region Tools
    public static final SlimefunItemStack SACRIFICIAL_DAGGER = new SlimefunItemStack(
            "SACRIFICIAL_DAGGER",
            Material.IRON_SWORD,
            "&f祭祀匕首",
            "&7收集杀死生物的血液",
            "&7右击可采集自己的血液"
    );
    public static final SlimefunItemStack HARVEST_SCYTHE = new SlimefunItemStack(
            "HARVEST_SCYTHE",
            Material.DIAMOND_HOE,
            "&e收割镰刀",
            "&7右击可在5x5区域收割和重新种植作物"
    );
    public static final SlimefunItemStack BLOOD_TOTEM = new SlimefunItemStack(
            "BLOOD_TOTEM",
            Material.TOTEM_OF_UNDYING,
            "&4血图腾",
            "&7攻击或杀戮时获得鲜血和能量",
            "&7当你死后充满鲜血时，你会复活",
            "",
            BloodUtils.getStoredString(0)
    );
    public static final SlimefunItemStack BLOOD_WOLF_RUNE = new SlimefunItemStack(
            "BLOOD_WOLF_RUNE",
            Material.RED_GLAZED_TERRACOTTA,
            "&4血狼符文",
            "&7使狼在攻击时有自愈和吸血的能力",
            "&7右键狼激活"
    );
    public static final SlimefunItemStack INFUSED_VAMPIRE_BLADE = new SlimefunItemStack(
            "INFUSED_VAMPIRE_BLADE",
            Material.NETHERITE_SWORD,
            "&4吸血鬼之刃",
            meta -> {
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY + "杀死或伤害将会治愈你",
                        ChatColor.GRAY + "右击空气可传送,但需要20血值",
                        "",
                        BloodUtils.getStoredString(0)
                ));
                meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
                meta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
            }
    );
    public static final SlimefunItemStack ENCHANTED_BLOOD_APPLE = new SlimefunItemStack(
            "ENCHANTED_BLOOD_APPLE",
            Material.ENCHANTED_GOLDEN_APPLE,
            "&c附魔血苹果",
            "&7充满了血的力量"
    );
    public static final SlimefunItemStack GOLDEN_POTION = new SlimefunItemStack(
            "GOLDEN_POTION",
            Material.POTION,
            "&c黄金药水",
            meta -> {
                meta.setLore(Collections.singletonList(ChatColor.GRAY + "注入保护效果"));
                PotionMeta potion = (PotionMeta) meta;
                potion.setColor(Color.YELLOW);
                potion.addCustomEffect(new PotionEffect(PotionEffectType.ABSORPTION, 14400, 4), true);
                potion.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 14400, 1), true);
                potion.addCustomEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 14400, 1), true);
                potion.addCustomEffect(new PotionEffect(PotionEffectType.LUCK, 14400, 2), true);
            }
    );
    public static final SlimefunItemStack VAMPIRIC_SPEED_POTION = new SlimefunItemStack(
            "VAMPIRIC_SPEED_POTION",
            Material.POTION,
            "&c吸血鬼速度药剂",
            meta -> {
                meta.setLore(Collections.singletonList(ChatColor.GRAY + "注入速度效果"));
                PotionMeta potion = (PotionMeta) meta;
                potion.setColor(Color.AQUA);
                potion.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 14400, 2), true);
            }
    );
    public static final SlimefunItemStack VAMPIRIC_STRENGTH_POTION = new SlimefunItemStack(
            "VAMPIRIC_STRENGTH_POTION",
            Material.POTION,
            "&c吸血鬼力量药剂",
            meta -> {
                meta.setLore(Collections.singletonList(ChatColor.GRAY + "注入力量效果"));
                PotionMeta potion = (PotionMeta) meta;
                potion.setColor(Color.PURPLE);
                potion.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 14400, 2), true);
            }
    );
    public static final SlimefunItemStack VAMPIRIC_REGENERATION_POTION = new SlimefunItemStack(
            "VAMPIRIC_REGENERATION_POTION",
            Material.POTION,
            "&c吸血鬼再生药剂",
            meta -> {
                meta.setLore(Collections.singletonList(ChatColor.GRAY + "注入生命恢复效果"));
                PotionMeta potion = (PotionMeta) meta;
                potion.setColor(Color.RED);
                potion.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 14400, 2), true);
            }
    );
    public static final SlimefunItemStack DEATH_POTION = new SlimefunItemStack(
            "DEATH_POTION",
            Material.SPLASH_POTION,
            "&8死亡药剂",
            meta -> {
                meta.setLore(Collections.singletonList(ChatColor.GRAY + "充满了死亡的气息"));
                PotionMeta potion = (PotionMeta) meta;
                potion.setColor(Color.BLACK);
                potion.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 0, 3), true);
                potion.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 1200, 2), true);
                potion.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 400, 2), true);
            }
    );
    //endregion

    //region Blocks
    public static final SlimefunItemStack GOLDEN_SEEDS = new SlimefunItemStack(
            "GOLDEN_SEEDS",
            Material.WHEAT_SEEDS,
            "&e金色种子",
            "&7充满了鲜血和黄金"
    );
    public static final SlimefunItemStack BLOOD_ALTAR = new SlimefunItemStack(
            "BLOOD_ALTAR",
            Material.ENCHANTING_TABLE,
            "&c血液祭坛",
            "&7用来制造和注入的物品",
            "&7将物品配方丢到血液祭坛旁边,然后右击制造"
    );
    public static final SlimefunItemStack BLOOD_HOPPER = new SlimefunItemStack(
            "BLOOD_HOPPER",
            Material.HOPPER,
            "&c血漏斗",
            "&7从上面垂死的生物身上采集血液",
            "",
            "&c几率: 15%"
    );
    public static final SlimefunItemStack INFUSED_BLOOD_HOPPER = new SlimefunItemStack(
            "INFUSED_BLOOD_HOPPER",
            Material.HOPPER,
            "&c高级血漏斗",
            "&7从上面垂死的生物身上采集血液",
            "",
            "&c几率: 100%"
    );
    public static final SlimefunItemStack GOLDEN_WHEAT = new SlimefunItemStack(
            "GOLDEN_WHEAT",
            Material.WHEAT,
            "&e金色小麦",
            "&7充满了黄金的力量"
    );
    public static final SlimefunItemStack BLOOD_SHROOM = new SlimefunItemStack(
            "BLOOD_SHROOM",
            Material.RED_MUSHROOM,
            "&c血迹",
            "&7给附近的人带来药水效果"
    );
    public static final SlimefunItemStack DEATH_SHROOM = new SlimefunItemStack(
            "DEATH_SHROOM",
            Material.BROWN_MUSHROOM,
            "&8死亡血迹",
            "&7给附近的人带来死亡..."
    );
    //endregion

    public static void setup(@Nonnull BloodAlchemy plugin) {
        Category category = new Category(plugin.getKey("blood_alchemy"),
                new CustomItem(Material.NETHER_WART_BLOCK, "&4Blood Alchemy(血炼金术)"));

        new SacrificialDagger(category, SACRIFICIAL_DAGGER, RecipeType.MAGIC_WORKBENCH, new ItemStack[] {
                null, SlimefunItems.SILVER_INGOT, SlimefunItems.SILVER_INGOT,
                null, SlimefunItems.SILVER_INGOT, null,
                null, new ItemStack(Material.STICK), null
        }).register(plugin);

        new SlimefunItem(category, BLOOD, SacrificialDagger.TYPE, new ItemStack[] {
                new CustomItem(Material.ZOMBIE_HEAD, "&c攻击生物或玩家")
        }).register(plugin);

        new BloodAltar(category, BLOOD_ALTAR, RecipeType.MAGIC_WORKBENCH, new ItemStack[] {
                null, BLOOD, null,
                BLOOD, new ItemStack(Material.ENCHANTING_TABLE), BLOOD,
                null, BLOOD, null
        }).register(plugin);

        new BloodHopper(category, BLOOD_HOPPER, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 16),
                new ItemStack(Material.HOPPER)
        }, 15).register(plugin);

        new BloodWolfRune(category, BLOOD_WOLF_RUNE, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 16),
                new ItemStack(Material.BONE, 16),
                new ItemStack(Material.BEEF, 16)
        }).register(plugin);

        new SlimefunItem(category, BLOOD_GEM, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 64),
                new ItemStack(Material.DIAMOND, 64)
        }).register(plugin);

        new BloodHopper(category, INFUSED_BLOOD_HOPPER, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 16),
                BLOOD_HOPPER,
                BLOOD_GEM
        }, 100).register(plugin);

        new InfusedVampireBlade(category, INFUSED_VAMPIRE_BLADE, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 64),
                SlimefunItems.BLADE_OF_VAMPIRES,
                new ItemStack(Material.NETHERITE_INGOT, 4),
                new SlimefunItemStack(BLOOD_GEM, 4)
        }).register(plugin);

        new BloodTotem(category, BLOOD_TOTEM, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 64),
                new ItemStack(Material.TOTEM_OF_UNDYING),
                new SlimefunItemStack(BLOOD_GEM, 4),
                new SlimefunItemStack(GOLDEN_WHEAT, 32)
        }).register(plugin);

        new SlimefunSeed(category, GOLDEN_SEEDS, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 16),
                new ItemStack(Material.WHEAT_SEEDS, 16),
                new ItemStack(Material.GOLD_INGOT, 16)
        }, GOLDEN_WHEAT).register(plugin);

        new SlimefunCrop(category, GOLDEN_WHEAT, SlimefunSeed.TYPE, new ItemStack[] {
                GOLDEN_SEEDS
        }, GOLDEN_SEEDS).register(plugin);

        new HarvestScythe(category, HARVEST_SCYTHE, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 16),
                new SlimefunItemStack(GOLDEN_WHEAT, 32),
                new ItemStack(Material.DIAMOND_HOE),
                BLOOD_GEM
        }).register(plugin);

        new SlimefunItem(category, ENCHANTED_BLOOD_APPLE, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 32),
                new SlimefunItemStack(GOLDEN_WHEAT, 32),
                new ItemStack(Material.APPLE, 32)
        }).register(plugin);

        new SlimefunItem(category, GOLDEN_POTION, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 8),
                new ItemStack(Material.HONEY_BOTTLE, 8),
                new ItemStack(Material.GOLDEN_APPLE, 4),
                new SlimefunItemStack(GOLDEN_WHEAT, 8)
        }).register(plugin);

        new SlimefunShroom(category, BLOOD_SHROOM, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 32),
                new ItemStack(Material.RED_MUSHROOM, 32)
        }, new PotionEffect(PotionEffectType.REGENERATION, 400, 0),
                Particle.CRIMSON_SPORE).register(plugin);

        new SlimefunItem(category, VAMPIRIC_REGENERATION_POTION, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 32),
                new ItemStack(Material.GLASS_BOTTLE),
                new SlimefunItemStack(BLOOD_SHROOM, 8),
                new ItemStack(Material.GHAST_TEAR, 8)
        }).register(plugin);

        new SlimefunItem(category, VAMPIRIC_STRENGTH_POTION, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 32),
                new ItemStack(Material.GLASS_BOTTLE),
                new SlimefunItemStack(BLOOD_SHROOM, 8),
                new ItemStack(Material.BLAZE_ROD, 8),
                new ItemStack(Material.NETHERITE_SCRAP, 1)
        }).register(plugin);

        new SlimefunItem(category, VAMPIRIC_SPEED_POTION, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 8),
                new ItemStack(Material.GLASS_BOTTLE),
                new ItemStack(Material.SUGAR, 16),
                new ItemStack(Material.GLOWSTONE_DUST, 16),
                new SlimefunItemStack(SlimefunItems.MAGIC_SUGAR, 16)
        }).register(plugin);

        new SlimefunShroom(category, DEATH_SHROOM, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 32),
                new ItemStack(Material.BROWN_MUSHROOM, 32),
                new ItemStack(Material.BONE, 32),
                new ItemStack(Material.WITHER_ROSE, 8)
        }, new PotionEffect(PotionEffectType.WITHER, 400, 2),
                Particle.WARPED_SPORE).register(plugin);

        new SlimefunItem(category, DEATH_POTION, BloodAltar.TYPE, new ItemStack[] {
                new SlimefunItemStack(BLOOD, 16),
                new ItemStack(Material.GLASS_BOTTLE),
                new ItemStack(Material.GUNPOWDER, 8),
                new ItemStack(Material.SPIDER_EYE, 4),
                new ItemStack(Material.WITHER_ROSE, 4),
                new SlimefunItemStack(DEATH_SHROOM, 4)
        }).register(plugin);
    }

}
