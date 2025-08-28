package nl.eetgeenappels.mythic_nether.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import nl.eetgeenappels.mythic_nether.MythicNether;

public class MNItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(MythicNether.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> SCULK_STEM = block(MNBlocks.SCULK_STEM);
    public static final RegistrySupplier<Item> SCULK_LEAVES = block(MNBlocks.SCULK_LEAVES);
    public static final RegistrySupplier<Item> BLACKSTONE_SHRUB = block(MNBlocks.BLACKSTONE_SHRUB);
    public static final RegistrySupplier<Item> BONE_NYLIUM = block(MNBlocks.BONE_NYLIUM);
    public static final RegistrySupplier<Item> SMALL_BONE_PILE = block(MNBlocks.SMALL_BONE_PILE);
    public static final RegistrySupplier<Item> LARGE_BONE_PILE = block(MNBlocks.LARGE_BONE_PILE);
    public static final RegistrySupplier<Item> LARGE_BONE = block(MNBlocks.LARGE_BONE);
    public static final RegistrySupplier<Item> BONE_MARROW = block(MNBlocks.BONE_MARROW);
    public static final RegistrySupplier<Item> HEAVENLY_VINE = block(MNBlocks.HEAVENLY_VINE);
    public static final RegistrySupplier<Item> PINK_DREAM_FLOWERS = block(MNBlocks.PINK_DREAM_FLOWER);
    public static final RegistrySupplier<Item> CYAN_DREAM_FLOWERS = block(MNBlocks.CYAN_DREAM_FLOWER);
    public static final RegistrySupplier<Item> DREAMY_NYLIUM = block(MNBlocks.DREAMY_NYLIUM);
    public static final RegistrySupplier<Item> BLUE_HEAVENLY_NYLIUM = block(MNBlocks.BLUE_HEAVENLY_NYLIUM);
    public static final RegistrySupplier<Item> PINK_HEAVENLY_NYLIUM = block(MNBlocks.PINK_HEAVENLY_NYLIUM);

    private static RegistrySupplier<Item> block(RegistrySupplier<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register() {
        REGISTRY.register();
    }

}
