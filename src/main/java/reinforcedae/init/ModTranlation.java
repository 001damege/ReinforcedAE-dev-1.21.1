package reinforcedae.init;

import guideme.internal.data.LocalizationEnum;
import reinforcedae.ReinforcedAE;

public enum ModTranlation implements LocalizationEnum {
    ModName("ReinforcedAE", "gui"),
    AcceleratorThreads("Provides 262144 co-processing threads per block."),
    ;

    private final String englishText;
    private final String root;

    ModTranlation(String englishText, String root) {
        this.englishText = englishText;
        this.root = root;
    }

    ModTranlation(String englishText) {
        this(englishText, "gui.tooltips");
    }

    @Override
    public String getEnglishText() {
        return englishText;
    }

    @Override
    public String getTranslationKey() {
        return String.format("%s.%s.%s", root, ReinforcedAE.MODID, name());
    }
}
