package nl.eetgeenappels.mythic_nether.neoforge;

import net.neoforged.bus.api.SubscribeEvent;
import nl.eetgeenappels.mythic_nether.MythicNether;
import net.neoforged.fml.common.Mod;

@Mod(MythicNether.MOD_ID)
public final class MythicNetherNeoForge {
    public MythicNetherNeoForge() {
        // Run our common setup.
        MythicNether.init();
    }
}
