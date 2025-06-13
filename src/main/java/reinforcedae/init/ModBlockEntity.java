package reinforcedae.init;

import appeng.block.AEBaseEntityBlock;
import appeng.blockentity.AEBaseBlockEntity;
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

public final class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> DR = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ReinforcedAE.MODID);

    private static <T extends AEBaseBlockEntity> Supplier<BlockEntityType<T>> create(String id, Class<T> entityClass, BlockEntityFactory<T> factory, BlockDefinition<? extends AEBaseEntityBlock<?>>... blockDefinitions) {
        if (blockDefinitions.length == 0) {
            throw new IllegalArgumentException();
        }

        return DR.register(id, () -> {
            var blocks = Arrays.stream(blockDefinitions).map(BlockDefinition::block).toArray(AEBaseEntityBlock[]::new);
            var typeHolder = new AtomicReference<BlockEntityType<T>>();
            var type = BlockEntityType.Builder.of((pos, state) -> factory.create(typeHolder.get(), pos, state), blocks).build(null);
            typeHolder.set(type);
            AEBaseBlockEntity.registerBlockEntityItem(type, blockDefinitions[0].asItem());
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
