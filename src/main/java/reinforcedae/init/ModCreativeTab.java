package reinforcedae.init;

import appeng.block.AEBaseBlock;
import appeng.block.AEBaseBlockItem;
import appeng.core.definitions.BlockDefinition;
import appeng.core.definitions.ItemDefinition;
import appeng.items.AEBaseItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;
import reinforcedae.ReinforcedAE;

import java.util.ArrayList;

public final class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> DR = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ReinforcedAE.MODID);

    static {
        DR.register("tab", () -> CreativeModeTab.builder()
                .title(ModTranlation.ModName.text())
                .icon(ModItem.OPTICS_PROCESSOR::stack)
                .displayItems(ModCreativeTab::insertTab)
                .build());
    }

    private static void insertTab(CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output output) {
        var registries = new ArrayList<ItemDefinition<?>>();
        registries.addAll(ModItem.getItems());
        registries.addAll(ModBlock.getBlocks().stream().map(BlockDefinition::item).toList());

        for (var itemDef : registries) {
            var item = itemDef.asItem();

            if (item instanceof AEBaseBlockItem baseItem && baseItem.getBlock() instanceof AEBaseBlock baseBlock) {
                baseBlock.addToMainCreativeTab(params, output);
            } else if (item instanceof AEBaseItem baseItem) {
                baseItem.addToMainCreativeTab(params, output);
            } else {
                output.accept(itemDef);
            }
        }
    }
}
