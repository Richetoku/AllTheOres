package com.richetoku.alltheores;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class UnificationManager {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Gson GSON = new Gson();
    
    public static final Map<String, UnifiedMaterial> UNIFIED_MATERIALS = new HashMap<>();
    public static final Map<String, String> UNIFIED_GEMS = new HashMap<>();
    
    public static void loadUnificationData() {
        try (InputStream stream = UnificationManager.class.getResourceAsStream("/data/alltheores/unification.json")) {
            if (stream == null) {
                LOGGER.warn("Could not find unification.json");
                return;
            }
            
            JsonObject root = JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
            
            // Load unified materials
            JsonObject materials = root.getAsJsonObject("unified_items");
            for (Map.Entry<String, JsonElement> entry : materials.entrySet()) {
                JsonObject material = entry.getValue().getAsJsonObject();
                UnifiedMaterial unified = new UnifiedMaterial();
                unified.ingot = material.has("ingot") ? material.get("ingot").getAsString() : null;
                unified.nugget = material.has("nugget") ? material.get("nugget").getAsString() : null;
                unified.raw = material.has("raw") ? material.get("raw").getAsString() : null;
                unified.block = material.has("block") ? material.get("block").getAsString() : null;
                unified.ore = material.has("ore") ? material.get("ore").getAsString() : null;
                unified.deepslateOre = material.has("deepslate_ore") ? material.get("deepslate_ore").getAsString() : null;
                unified.dust = material.has("dust") ? material.get("dust").getAsString() : null;
                UNIFIED_MATERIALS.put(entry.getKey(), unified);
            }
            
            // Load unified gems
            JsonObject gems = root.getAsJsonObject("unified_gems");
            for (Map.Entry<String, JsonElement> entry : gems.entrySet()) {
                UNIFIED_GEMS.put(entry.getKey(), entry.getValue().getAsString());
            }
            
            LOGGER.info("Loaded {} unified materials and {} unified gems", UNIFIED_MATERIALS.size(), UNIFIED_GEMS.size());
        } catch (Exception e) {
            LOGGER.error("Failed to load unification data", e);
        }
    }
    
    public static boolean hasUnifiedMaterial(String material) {
        return UNIFIED_MATERIALS.containsKey(material);
    }
    
    public static String getUnifiedIngot(String material) {
        UnifiedMaterial mat = UNIFIED_MATERIALS.get(material);
        return mat != null ? mat.ingot : null;
    }
    
    public static String getUnifiedNugget(String material) {
        UnifiedMaterial mat = UNIFIED_MATERIALS.get(material);
        return mat != null ? mat.nugget : null;
    }
    
    public static String getUnifiedRaw(String material) {
        UnifiedMaterial mat = UNIFIED_MATERIALS.get(material);
        return mat != null ? mat.raw : null;
    }
    
    public static String getUnifiedBlock(String material) {
        UnifiedMaterial mat = UNIFIED_MATERIALS.get(material);
        return mat != null ? mat.block : null;
    }
    
    public static String getUnifiedOre(String material) {
        UnifiedMaterial mat = UNIFIED_MATERIALS.get(material);
        return mat != null ? mat.ore : null;
    }
    
    public static String getUnifiedDeepslateOre(String material) {
        UnifiedMaterial mat = UNIFIED_MATERIALS.get(material);
        return mat != null ? mat.deepslateOre : null;
    }
    
    public static String getUnifiedDust(String material) {
        UnifiedMaterial mat = UNIFIED_MATERIALS.get(material);
        return mat != null ? mat.dust : null;
    }
    
    public static boolean hasUnifiedGem(String gem) {
        return UNIFIED_GEMS.containsKey(gem);
    }
    
    public static String getUnifiedGem(String gem) {
        return UNIFIED_GEMS.get(gem);
    }
    
    public static class UnifiedMaterial {
        public String ingot;
        public String nugget;
        public String raw;
        public String block;
        public String ore;
        public String deepslateOre;
        public String dust;
    }
}