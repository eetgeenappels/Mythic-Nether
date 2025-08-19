package nl.eetgeenappels.mythic_nether.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import nl.eetgeenappels.mythic_nether.MythicNether;
import nl.eetgeenappels.mythic_nether.blocks.*;

public class MNBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(MythicNether.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> SCULK_STEM = REGISTRY.register("sculk_stem", SculkStem::new);
    public static final RegistrySupplier<Block> SCULK_LEAVES = REGISTRY.register("sculk_leaves", SculkLeaves::new);
    public static final RegistrySupplier<Block> BLACKSTONE_SHRUB = REGISTRY.register("blackstone_shrub", BlackstoneShrub::new);
    public static final RegistrySupplier<Block> BONE_NYLIUM = REGISTRY.register("bone_nylium", BoneNylium::new);
    public static final RegistrySupplier<Block> SMALL_BONE_PILE = REGISTRY.register("small_bone_pile", SmallBonePile::new);
    public static final RegistrySupplier<Block> LARGE_BONE_PILE = REGISTRY.register("large_bone_pile", LargeBonePile::new);
    public static final RegistrySupplier<Block> LARGE_BONE = REGISTRY.register("large_bone", LargeBone::new);

    public static void register() {
        MythicNether.LOGGER.info("Registering Blocks");
        REGISTRY.register();
    }
}
