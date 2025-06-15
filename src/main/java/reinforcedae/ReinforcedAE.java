package reinforcedae;

import appeng.api.AECapabilities;
import appeng.api.networking.IInWorldGridNodeHost;
import appeng.api.upgrades.Upgrades;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEParts;
import appeng.core.localization.GuiText;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reinforcedae.init.*;

import java.util.List;

import static appeng.core.definitions.AEItems.*;

@Mod(ReinforcedAE.MODID)
public final class ReinforcedAE {
    public static final String MODID = "reinforcedae";
    public static final String MOD_NAME = "ReinforcedAE";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public ReinforcedAE(IEventBus eventBus, ModContainer mod) {
        eventBus.addListener(ReinforcedAE::initUpgrades);
        eventBus.addListener(ReinforcedAE::initCapabilities);
        ModItem.DR.register(eventBus);
        ModCreativeTab.DR.register(eventBus);
        ModBlock.DR.register(eventBus);
        ModBlockEntity.DR.register(eventBus);
        ModComponent.DR.register(eventBus);
    }

    public static ResourceLocation makeId(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    private static void initUpgrades(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            var storageCellGroup = GuiText.StorageCells.getTranslationKey();
            var portableCellGroup = GuiText.PortableCells.getTranslationKey();
            var interfaceGroup = GuiText.Interface.getTranslationKey();
            var wirelessTerminalGroup = GuiText.WirelessTerminals.getTranslationKey();
            var itemIoBusGroup = GuiText.IOBuses.getTranslationKey();


            Upgrades.add(ModItem.SPEED_CARD, AEBlocks.IO_PORT, 3);
            Upgrades.add(ModItem.SPEED_CARD, AEParts.IMPORT_BUS, 4, itemIoBusGroup);
            Upgrades.add(ModItem.SPEED_CARD, AEParts.EXPORT_BUS, 4, interfaceGroup);
            Upgrades.add(ModItem.ENERGY_CARD, WIRELESS_TERMINAL, 2, wirelessTerminalGroup);
            Upgrades.add(ModItem.ENERGY_CARD, WIRELESS_CRAFTING_TERMINAL, 2, wirelessTerminalGroup);
            Upgrades.add(ModItem.ENERGY_CARD, COLOR_APPLICATOR, 2);
            Upgrades.add(ModItem.ENERGY_CARD, MATTER_CANNON, 2);
            Upgrades.add(ModItem.SPEED_CARD, MATTER_CANNON, 4);
            Upgrades.add(ModItem.SPEED_CARD, AEBlocks.MOLECULAR_ASSEMBLER, 4);
            Upgrades.add(ModItem.SPEED_CARD, AEBlocks.VIBRATION_CHAMBER, 3);
            Upgrades.add(ModItem.ENERGY_CARD, AEBlocks.VIBRATION_CHAMBER, 3);
            Upgrades.add(ModItem.SPEED_CARD, AEBlocks.INSCRIBER, 4);

            var portableCell = List.of(PORTABLE_ITEM_CELL1K, PORTABLE_ITEM_CELL4K, PORTABLE_ITEM_CELL16K, PORTABLE_ITEM_CELL64K, PORTABLE_ITEM_CELL256K,
                    PORTABLE_FLUID_CELL1K, PORTABLE_FLUID_CELL4K, PORTABLE_FLUID_CELL16K, PORTABLE_FLUID_CELL64K, PORTABLE_FLUID_CELL256K);

            for (var list : portableCell) {
                Upgrades.add(ModItem.ENERGY_CARD, list, 2, portableCellGroup);
            }
        });
    }

    @SuppressWarnings("UnstableApiUsage")
    private static void initCapabilities(RegisterCapabilitiesEvent event) {
        for (var type : ModBlockEntity.DR.getEntries()) {
            event.registerBlockEntity(AECapabilities.IN_WORLD_GRID_NODE_HOST, type.get(), (be, context) -> (IInWorldGridNodeHost) be);
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    private static void initPartCapabilities(RegisterCapabilitiesEvent event) {

    }
}
