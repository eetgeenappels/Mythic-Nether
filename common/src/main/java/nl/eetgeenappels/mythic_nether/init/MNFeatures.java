package nl.eetgeenappels.mythic_nether.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import nl.eetgeenappels.mythic_nether.MythicNether;
import nl.eetgeenappels.mythic_nether.world.feature.GiantBlobConfig;
import nl.eetgeenappels.mythic_nether.world.feature.GiantBlobFeature;

public class MNFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(MythicNether.MOD_ID, Registries.FEATURE);

    public static final RegistrySupplier<Feature<?>> GIANT_BLOB =
            FEATURES.register("giant_blob", () -> new GiantBlobFeature(GiantBlobConfig.CODEC));

    public static void init() {
        FEATURES.register();
    }
}
