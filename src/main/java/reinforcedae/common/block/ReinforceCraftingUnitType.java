package reinforcedae.common.block;

import appeng.block.crafting.ICraftingUnitType;
import appeng.core.definitions.BlockDefinition;
import net.minecraft.world.item.Item;
import reinforcedae.init.ModBlock;

import java.util.Objects;

public enum ReinforceCraftingUnitType implements ICraftingUnitType {
    STORAGE_1024M(1024, "1024m_storage"),
    STORAGE_2048M(2048, "2048m_storage"),
    STORAGE_8192M(8192, "8192m_storage"),
    STORAGE_32768M(32768, "32768m_storage"),
    STORAGE_131072M(131072, "131072m_storage"),
    STORAGE_CREATIVE(134217728, "creative_storage"),
    UNIT(0, "unit"),
    ACCELERATOR(0, "accelerator");

    private final int storageMb;
    private final String affix;

    ReinforceCraftingUnitType(int storageMb, String affix) {
        this.storageMb = storageMb;
        this.affix = affix;
    }

    @Override
    public long getStorageBytes() {
        return 1024L * 1024 * storageMb;
    }

    @Override
    public int getAcceleratorThreads() {
        return this == ACCELERATOR ? 16 : 0;
    }

    public String getAffix() {
        return affix;
    }

    @Override
    public Item getItemFromType() {
        return Objects.requireNonNull(getDefinition()).asItem();
    }

    public BlockDefinition<?> getDefinition() {
        return switch (this) {
            case STORAGE_1024M -> ModBlock.STORAGE_1024M;
            case STORAGE_2048M -> ModBlock.STORAGE_2048M;
            case STORAGE_8192M -> ModBlock.STORAGE_8192M;
            case STORAGE_32768M -> ModBlock.STORAGE_32768M;
            case STORAGE_131072M -> ModBlock.STORAGE_131072M;
            case STORAGE_CREATIVE -> ModBlock.STORAGE_CREATIVE;
            case UNIT -> ModBlock.CRAFTING_UNIT;
            case ACCELERATOR -> ModBlock.CRAFTING_ACCELERATOR;
        };
    }
}
