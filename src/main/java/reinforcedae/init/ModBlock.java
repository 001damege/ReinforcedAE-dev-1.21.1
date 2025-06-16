package reinforcedae.init;

import appeng.block.AEBaseBlockItem;
import appeng.block.crafting.CraftingBlockItem;
import appeng.block.crafting.CraftingUnitBlock;
import appeng.core.definitions.BlockDefinition;
import appeng.core.definitions.ItemDefinition;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import reinforcedae.ReinforcedAE;
import reinforcedae.common.block.ReinforceCraftingUnitType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class ModBlock {
    public static final DeferredRegister.Blocks DR = DeferredRegister.createBlocks(ReinforcedAE.MODID);

    private static final List<BlockDefinition<?>> BLOCKS = new ArrayList<>();

    public static List<BlockDefinition<?>> getBlocks() {
        return Collections.unmodifiableList(BLOCKS);
    }

    public static final BlockDefinition<CraftingUnitBlock> CRAFTING_UNIT = block("Reinforced Crafting Unit", "reinforced_crafting_unit", () ->
            new CraftingUnitBlock(ReinforceCraftingUnitType.UNIT), AEBaseBlockItem::new);
    public static final BlockDefinition<CraftingUnitBlock> CRAFTING_ACCELERATOR = block("Reinforced Crafting Co-Processing Unit", "reinforced_crafting_accelerator", () ->
            new CraftingUnitBlock(ReinforceCraftingUnitType.ACCELERATOR), (b, p) -> new CraftingBlockItem(b, p) {
        @Override
        public void addCheckedInformation(ItemStack stack, TooltipContext context, List<Component> toolTip, TooltipFlag advancedTooltips) {
            toolTip.add(ModTranlation.AcceleratorThreads.text());
        }
    });

    public static final BlockDefinition<CraftingUnitBlock> STORAGE_1024M = block("1024M Crafting Storage", "1024m_crafting_storage", () -> new CraftingUnitBlock(ReinforceCraftingUnitType.STORAGE_1024M), CraftingBlockItem::new);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_2048M = block("2048M Crafting Storage", "2048m_crafting_storage", () -> new CraftingUnitBlock(ReinforceCraftingUnitType.STORAGE_2048M), CraftingBlockItem::new);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_8192M = block("8192M Crafting Storage", "8192m_crafting_storage", () -> new CraftingUnitBlock(ReinforceCraftingUnitType.STORAGE_8192M), CraftingBlockItem::new);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_32768M = block("32768M Crafting Storage", "32768m_crafting_storage", () -> new CraftingUnitBlock(ReinforceCraftingUnitType.STORAGE_32768M), CraftingBlockItem::new);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_131072M = block("131072M Crafting Storage", "131072m_crafting_storage", () -> new CraftingUnitBlock(ReinforceCraftingUnitType.STORAGE_131072M), CraftingBlockItem::new);
    public static final BlockDefinition<CraftingUnitBlock> STORAGE_CREATIVE = block("Creative Crafting Storage", "creative_crafting_storage", () -> new CraftingUnitBlock(ReinforceCraftingUnitType.STORAGE_CREATIVE), CraftingBlockItem::new);

    private static <T extends Block> BlockDefinition<T> block(String englishName, String id, Supplier<T> blockSupplier, BiFunction<Block, Item.Properties, BlockItem> factory) {
        var block = DR.register(id, blockSupplier);
        var item = ModItem.DR.register(id, () -> factory.apply(block.get(), new Item.Properties()));
        var registry = new BlockDefinition<>(englishName, block, new ItemDefinition<>(englishName, item));
        BLOCKS.add(registry);
        return registry;
    }
}
