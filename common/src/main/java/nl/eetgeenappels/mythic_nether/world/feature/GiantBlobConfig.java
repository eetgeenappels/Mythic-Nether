package nl.eetgeenappels.mythic_nether.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record GiantBlobConfig(BlockStateProvider block, int radius) implements FeatureConfiguration {
    public static final Codec<GiantBlobConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("block").forGetter(GiantBlobConfig::block),
            Codec.INT.fieldOf("radius").forGetter(GiantBlobConfig::radius)
    ).apply(instance, GiantBlobConfig::new));
}
