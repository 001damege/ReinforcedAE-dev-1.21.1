package reinforcedae.init;

import appeng.api.parts.IPart;
import appeng.api.parts.IPartItem;
import appeng.api.parts.PartModels;
import appeng.api.upgrades.Upgrades;
import appeng.core.definitions.ItemDefinition;
import appeng.items.materials.EnergyCardItem;
import appeng.items.materials.MaterialItem;
import appeng.items.parts.PartItem;
import appeng.items.parts.PartModelsHelper;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import reinforcedae.ReinforcedAE;
import reinforcedae.common.item.kit.ItemDriveKit;
import reinforcedae.common.item.kit.ItemInterfaceKit;
import reinforcedae.common.item.kit.ItemPatnternProviderKit;
import reinforcedae.common.item.kit.ItemPortKit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public final class ModItem {
    public static final DeferredRegister.Items DR = DeferredRegister.createItems(ReinforcedAE.MODID);

    private static final List<ItemDefinition<?>> ITEMS = new ArrayList<>();

    public static List<ItemDefinition<?>> getItems() {
        return Collections.unmodifiableList(ITEMS);
    }

    public static final ItemDefinition<Item> SPEED_CARD = item("Compressed Acceleration Card", "compressed_acceleration_card", Upgrades::createUpgradeCardItem);
    public static final ItemDefinition<EnergyCardItem> ENERGY_CARD = item("Compressed Energy Card", "compressed_energy_card", p -> new EnergyCardItem(p, 9));
    public static final ItemDefinition<MaterialItem> ITEM_CELL_HOUSING = item("Reinforced Item Cell Housing", "reinforced_item_cell_housing", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> FLUID_CELL_HOUSING = item("Reinforced Fluid Cell Housing", "reinforced_fluid_cell_housing", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> OPTICS_PROCESSOR = item("Optics Processor", "optics_processor", MaterialItem::new);
    public static final ItemDefinition<ItemPatnternProviderKit> PATTERN_PROVIDER_KIT = item("Reinforced Pattern Provider Kit", "pattern_kit", ItemPatnternProviderKit::new);
    public static final ItemDefinition<ItemInterfaceKit> INTERFACE_KIT = item("Reinforced Interface Kit", "interface_kit", ItemInterfaceKit::new);
    public static final ItemDefinition<ItemPortKit> PORT_KIT = item("Reinforced IO Port Kit", "port_kit", ItemPortKit::new);
    public static final ItemDefinition<ItemDriveKit> DRIVE_KIT = item("Reinforced Drive Kit", "drive_kit", ItemDriveKit::new);

    public static final ItemDefinition<MaterialItem> OVER_COMPONENT = item("Reinforced Storage Component", "reinforced_storage_component", MaterialItem::new);

    public static <T extends Item> ItemDefinition<T> item(String englishName, String id, Function<Item.Properties, T> factory) {
        var registry = new ItemDefinition<>(englishName, DR.registerItem(id, factory));
        ITEMS.add(registry);
        return registry;
    }

    private static <T extends IPart> ItemDefinition<PartItem<T>> part(String englishName, String id, Class<T> partClass, Function<IPartItem<T>, T> factory) {
        PartModels.registerModels(PartModelsHelper.createModels(partClass));
        return item(englishName, id, p -> new PartItem<>(p, partClass, factory));
    }
}
