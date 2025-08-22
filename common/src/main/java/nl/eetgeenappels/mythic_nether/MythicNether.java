package nl.eetgeenappels.mythic_nether;

import nl.eetgeenappels.mythic_nether.init.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MythicNether {
    public static final Logger LOGGER = LoggerFactory.getLogger(MythicNether.class);
    public static final String MOD_ID = "mythic_nether";

    public static void init() {
        MNBlocks.register();
        MNItems.register();
        MNCreativeTab.register();
        MNEntities.register();
        MNFeatures.init();
    }
}
