package reinforcedae.init;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class ModConfig {
    public static final ModConfig CONFIG;
    public static final ModConfigSpec SPEC;

    static {
        var configured = new ModConfigSpec.Builder().configure(ModConfig::new);
        CONFIG = configured.getKey();
        SPEC = configured.getValue();
    }

    public ModConfig(ModConfigSpec.Builder builder) {

    }
}
