package nl.eetgeenappels.mythic_nether.fabric;

import nl.eetgeenappels.mythic_nether.MythicNether;
import net.fabricmc.api.ModInitializer;

public final class MythicNetherFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        MythicNether.init();
    }
}
