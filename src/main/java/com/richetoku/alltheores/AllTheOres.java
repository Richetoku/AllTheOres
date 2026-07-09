package com.richetoku.alltheores;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(AllTheOres.MODID)
public class AllTheOres {
    public static final String MODID = "alltheores";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCK_REGISTRY = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEM_REGISTRY = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final Map<String, DeferredBlock<Block>> BLOCKS = new LinkedHashMap<>();
    public static final Map<String, DeferredItem<BlockItem>> ITEMS = new LinkedHashMap<>();

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_TABS.register("alltheores", () -> CreativeModeTab.builder()
        .title(Component.translatable("itemGroup.alltheores"))
        .withTabsBefore(CreativeModeTabs.NATURAL_BLOCKS)
        .icon(() -> ITEMS.values().iterator().next().get().getDefaultInstance())
        .displayItems((params, out) -> ITEMS.values().forEach(h -> out.accept(h.get())))
        .build());

    public AllTheOres(IEventBus bus, ModContainer container) {
        registerOres();
        BLOCK_REGISTRY.register(bus);
        ITEM_REGISTRY.register(bus);
        CREATIVE_TABS.register(bus);
        
        // Load unification data
        UnificationManager.loadUnificationData();
        
        container.registerConfig(ModConfig.Type.COMMON, AllTheOresConfig.SPEC);
        LOGGER.info("AllTheOres registered {} ore blocks.", BLOCKS.size());
    }

    private static void registerOres() {
        for (String id : AllTheOresCatalog.ORE_BLOCK_IDS) {
            DeferredBlock<Block> block = BLOCK_REGISTRY.register(id, () -> new Block(propertiesFor(id)));
            BLOCKS.put(id, block);
            ITEMS.put(id, ITEM_REGISTRY.register(id, () -> new BlockItem(block.get(), new Item.Properties())));
        }
    }

    private static BlockBehaviour.Properties propertiesFor(String id) {
        boolean sand = id.startsWith("sand_");
        boolean gravel = id.startsWith("gravel_");
        boolean deep = id.startsWith("deepslate_");
        boolean netherLike = id.startsWith("nether_") || id.startsWith("blackstone_") || id.startsWith("basalt_") || id.startsWith("smooth_basalt_");
        boolean end = id.startsWith("end_");
        float hardness = deep ? 4.5F : 3.0F;
        if (sand || gravel) hardness = 0.8F;
        if (netherLike) hardness = 3.0F;
        if (end) hardness = 4.0F;
        MapColor color = sand ? MapColor.SAND : (gravel ? MapColor.STONE : (netherLike ? MapColor.NETHER : (end ? MapColor.SAND : (deep ? MapColor.DEEPSLATE : MapColor.STONE))));
        SoundType sound = sand ? SoundType.SAND : (gravel ? SoundType.GRAVEL : (deep ? SoundType.DEEPSLATE : SoundType.STONE));
        return BlockBehaviour.Properties.of().mapColor(color).strength(hardness, 3.0F).sound(sound).requiresCorrectToolForDrops();
    }
}
