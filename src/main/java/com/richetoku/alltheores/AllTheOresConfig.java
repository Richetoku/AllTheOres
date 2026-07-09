package com.richetoku.alltheores;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class AllTheOresConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue ENABLE_WORLDGEN;
    public static final ModConfigSpec.BooleanValue ENABLE_VANILLA_STONE_ORES;
    public static final ModConfigSpec.BooleanValue ENABLE_CREATE_STONE_ORES;
    public static final ModConfigSpec.BooleanValue ENABLE_SAND_ORES;
    public static final ModConfigSpec.BooleanValue ENABLE_GRAVEL_ORES;
    public static final ModConfigSpec.BooleanValue ENABLE_NETHER_ORES;
    public static final ModConfigSpec.BooleanValue ENABLE_END_ORES;
    public static final ModConfigSpec.DoubleValue GLOBAL_RATE_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue VANILLA_STONE_RATE_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue CREATE_STONE_RATE_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue SEDIMENT_RATE_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue NETHER_RATE_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue END_RATE_MULTIPLIER;
    public static final ModConfigSpec.IntValue COMMON_VEIN_COUNT;
    public static final ModConfigSpec.IntValue RARE_VEIN_COUNT;
    public static final ModConfigSpec.IntValue COMMON_VEIN_SIZE;
    public static final ModConfigSpec.IntValue RARE_VEIN_SIZE;

    static {
        ModConfigSpec.Builder b = new ModConfigSpec.Builder();
        b.push("worldgen");
        ENABLE_WORLDGEN = b.comment("Master switch documented for pack authors. The generated biome modifiers are data-driven; use the included values as defaults when regenerating or overriding the data pack layer.").define("enableWorldgen", true);
        ENABLE_VANILLA_STONE_ORES = b.comment("Enable vanilla host stone ore variants: stone, deepslate, granite, diorite, andesite, tuff, calcite, and dripstone.").define("enableVanillaStoneOres", true);
        ENABLE_CREATE_STONE_ORES = b.comment("Enable Create host stone ore variants: limestone, scoria, scorchia, crimsite, asurine, veridium, and ochrum. These targets are optional and only generate when Create provides the host blocks.").define("enableCreateStoneOres", true);
        ENABLE_SAND_ORES = b.comment("Enable sand ore variants.").define("enableSandOres", true);
        ENABLE_GRAVEL_ORES = b.comment("Enable gravel ore variants.").define("enableGravelOres", true);
        ENABLE_NETHER_ORES = b.comment("Enable Nether host ore variants: netherrack, blackstone, basalt, and smooth basalt.").define("enableNetherOres", true);
        ENABLE_END_ORES = b.comment("Enable End stone ore variants.").define("enableEndOres", true);
        GLOBAL_RATE_MULTIPLIER = b.defineInRange("globalRateMultiplier", 1.0D, 0.0D, 64.0D);
        VANILLA_STONE_RATE_MULTIPLIER = b.defineInRange("vanillaStoneRateMultiplier", 1.0D, 0.0D, 64.0D);
        CREATE_STONE_RATE_MULTIPLIER = b.defineInRange("createStoneRateMultiplier", 0.60D, 0.0D, 64.0D);
        SEDIMENT_RATE_MULTIPLIER = b.defineInRange("sedimentRateMultiplier", 0.35D, 0.0D, 64.0D);
        NETHER_RATE_MULTIPLIER = b.defineInRange("netherRateMultiplier", 0.50D, 0.0D, 64.0D);
        END_RATE_MULTIPLIER = b.defineInRange("endRateMultiplier", 0.35D, 0.0D, 64.0D);
        COMMON_VEIN_COUNT = b.defineInRange("commonVeinCount", 12, 0, 128);
        RARE_VEIN_COUNT = b.defineInRange("rareVeinCount", 4, 0, 128);
        COMMON_VEIN_SIZE = b.defineInRange("commonVeinSize", 9, 1, 64);
        RARE_VEIN_SIZE = b.defineInRange("rareVeinSize", 5, 1, 64);
        b.pop();
        SPEC = b.build();
    }
    private AllTheOresConfig() {}
}
