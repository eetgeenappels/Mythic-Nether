package nl.eetgeenappels.mythic_nether.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import nl.eetgeenappels.mythic_nether.MythicNether;

public class MNBiomes {

    public static final ResourceKey<Biome> UNDEEP_DARK = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(MythicNether.MOD_ID, "undeep_dark"));

}
