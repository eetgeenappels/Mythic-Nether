package nl.eetgeenappels.mythic_nether.neoforge;

import nl.eetgeenappels.mythic_nether.MythicNether;
import net.neoforged.fml.common.Mod;

@Mod(MythicNether.MOD_ID)
public final class MysticNetherNeoForge {
    public MysticNetherNeoForge() {
        // Run our common setup.
        MythicNether.init();
    }
}
