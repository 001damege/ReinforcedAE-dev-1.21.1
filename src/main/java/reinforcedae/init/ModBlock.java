package reinforcedae.init;

import appeng.core.definitions.BlockDefinition;
import appeng.core.definitions.ItemDefinition;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import reinforcedae.ReinforcedAE;

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

    private static <T extends Block> BlockDefinition<T> block(String englishName, String id, Supplier<T> blockSupplier, BiFunction<Block, Item.Properties, BlockItem> factory) {
        var block = DR.register(id, blockSupplier);
        var item = ModItem.DR.register(id, () -> factory.apply(block.get(), new Item.Properties()));
        var registry = new BlockDefinition<>(englishName, block, new ItemDefinition<>(englishName, item));
        BLOCKS.add(registry);
        return registry;
    }
}
