package reinforcedae.common.block;

import appeng.block.crafting.ICraftingUnitType;
import appeng.core.definitions.BlockDefinition;
import net.minecraft.world.item.Item;
import reinforcedae.init.ModBlock;

import java.util.Objects;

public enum ReinforceCraftingUnitType implements ICraftingUnitType {
    STORAGE_1024M(1024, "1024m_storage", 0),
    STORAGE_2048M(2048, "2048m_storage", 0),
    STORAGE_8192M(8192, "8192m_storage", 0),
    STORAGE_32768M(32768, "32768m_storage", 0),
    STORAGE_131072M(131072, "131072m_storage", 0),
    STORAGE_CREATIVE(1048576, "creative_storage", 0),
    UNIT(0, "unit", 0),
    ACCELERATOR(0, "accelerator", 131072);

    private final int storageMb;
    private final String affix;
    private final int acceleratorThread;

    ReinforceCraftingUnitType(int storageMb, String affix, int acceleratorThread) {
        this.storageMb = storageMb;
        this.affix = affix;
        this.acceleratorThread = acceleratorThread;
    }

    @Override
    public long getStorageBytes() {
        return 1024L * 1024 * storageMb;
    }

    @Override
    public int getAcceleratorThreads() {
        return this.acceleratorThread;
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
