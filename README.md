# AllTheOres

**AllTheOres** is the ore and world generation companion mod for Minecraft 1.21.1 (NeoForge), forming one half of the RichStuff + AllTheOres pair.

- **Mod ID:** `alltheores`
- **Version:** 1.0.0
- **Minecraft:** 1.21.1
- **NeoForge:** 21.1.235
- **Group:** `com.richetoku.alltheores`

AllTheOres intentionally contains **only ores and ore generation**. Dusts, nuggets, tiny dusts, tiny nuggets, plates, gears, coins, molds, and processing recipes stay in RichStuff.

## What AllTheOres adds

This version removes the old dense and poor ore variants. Every RichStuff metal, gem, and crystal now gets ore variants for real host stones instead:

- stone
- deepslate
- granite
- diorite
- andesite
- tuff
- calcite
- dripstone
- Create limestone
- Create scoria
- Create scorchia
- Create crimsite
- Create asurine
- Create veridium
- Create ochrum
- sand
- gravel
- netherrack / nether
- blackstone
- basalt
- smooth basalt
- end stone

Worldgen uses one configured feature per material/host pair so the ore that generates in a host stone uses the matching visual variant. Create host blocks are added through optional tag entries, so the data remains loadable without Create, but those variants only generate when Create is installed and its terrain blocks exist.

The config class keeps worldgen toggles and rate defaults. Since Minecraft/NeoForge worldgen is data-driven, pack makers should override or regenerate the included JSON feature/biome modifier layer when changing existing worldgen rates.

AllTheOres contains only ore blocks, ore tags, and ore world generation. Nugget/dust/tiny-dust processing and metal slimes live in the RichStuff mod because those systems use RichStuff processing items.

## Building

Requires Java 21 and Gradle with NeoForge dependency access.

```bash
cd AllTheOres
gradle build
```

Output jars are produced in `build/libs`. A `data` gradle task generates/refreshes data resources into `src/generated/resources`.

## Project layout

```
AllTheOres/
├── src/main/                 # Mod source & resources
├── src/generated/resources/  # Generated data (features, biome modifiers, loot, tags)
├── docs/                     # Supplemental documentation
├── gradle.properties         # Mod metadata & versions
├── build.gradle
└── settings.gradle