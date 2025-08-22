package nl.eetgeenappels.mythic_nether.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import nl.eetgeenappels.mythic_nether.MythicNether;

public class MNCreativeTab {

    public static final DeferredRegister<CreativeModeTab> REGISTRY =
            DeferredRegister.create(MythicNether.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final DeferredSupplier<CreativeModeTab> MYTHIC_NETHER = REGISTRY.register("true_end", () ->
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.literal("Mythic Nether")).icon(() -> new ItemStack(MNBlocks.SCULK_STEM.get())).displayItems((parameters, tabData) -> {
                tabData.accept(MNItems.SCULK_STEM.get());
                tabData.accept(MNItems.SCULK_LEAVES.get());
                tabData.accept(MNItems.BLACKSTONE_SHRUB.get());
                tabData.accept(MNItems.BONE_NYLIUM.get());
                tabData.accept(MNItems.SMALL_BONE_PILE.get());
                tabData.accept(MNItems.LARGE_BONE_PILE.get());
                tabData.accept(MNItems.LARGE_BONE.get());
                tabData.accept(MNItems.BONE_MARROW.get());
            }).build());

    public static void register() {
        // Register the tab itself first
        REGISTRY.register();
    }
}
