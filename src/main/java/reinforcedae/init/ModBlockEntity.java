package reinforcedae.init;

import appeng.block.AEBaseEntityBlock;
import appeng.blockentity.AEBaseBlockEntity;
import appeng.blockentity.crafting.CraftingBlockEntity;
import appeng.core.definitions.BlockDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredRegister;
import reinforcedae.ReinforcedAE;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

@SuppressWarnings({"unused", "unchecked"})
public final class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> DR = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ReinforcedAE.MODID);

    public static final Supplier<BlockEntityType<CraftingBlockEntity>> CRAFTING_UNIT = create("reinforced_crafting_unit", CraftingBlockEntity.class, CraftingBlockEntity::new, ModBlock.CRAFTING_UNIT, ModBlock.CRAFTING_ACCELERATOR);
    public static final Supplier<BlockEntityType<CraftingBlockEntity>> CRAFTING_STORAGE = create("reinforced_crafting_storage", CraftingBlockEntity.class, CraftingBlockEntity::new, ModBlock.STORAGE_1024M, ModBlock.STORAGE_2048M, ModBlock.STORAGE_8192M, ModBlock.STORAGE_32768M, ModBlock.STORAGE_131072M, ModBlock.STORAGE_CREATIVE);

    @SuppressWarnings("DataFlowIssue")
    @SafeVarargs
    private static <T extends AEBaseBlockEntity> Supplier<BlockEntityType<T>> create(String id, Class<T> entityClass, BlockEntityFactory<T> factory, BlockDefinition<? extends AEBaseEntityBlock<?>>... blockDefs) {
        if (blockDefs.length == 0) {
            throw new IllegalArgumentException();
        }
        return DR.register(id, () -> {
            var blocks = Arrays.stream(blockDefs).map(BlockDefinition::block).toArray(AEBaseEntityBlock[]::new);
            var typeHolder = new AtomicReference<BlockEntityType<T>>();
            var type = BlockEntityType.Builder.of((pos, state) -> factory.create(typeHolder.get(), pos, state), blocks).build(null);
            typeHolder.set(type);
            AEBaseBlockEntity.registerBlockEntityItem(type, blockDefs[0].asItem());
            for (var block : blocks) {
                block.setBlockEntity(entityClass, type, null, null);
            }
            return type;
        });
    }

    private interface BlockEntityFactory<T extends AEBaseBlockEntity> {
        T create(BlockEntityType<T> type, BlockPos pos, BlockState state);
    }
}
