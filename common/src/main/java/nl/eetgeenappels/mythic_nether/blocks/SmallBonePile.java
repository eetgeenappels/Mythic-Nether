package nl.eetgeenappels.mythic_nether.blocks;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.eetgeenappels.mythic_nether.MythicNether;
import nl.eetgeenappels.mythic_nether.init.MNBlocks;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.function.Function;

public class SmallBonePile extends BushBlock {
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MythicNether.MOD_ID, "boney_foliage"));

    public static final MapCodec<LargeBonePile> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    propertiesCodec()
            ).apply(instance, LargeBonePile::new)
    );

    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D,
            16.0D, 5.0D, 16.0D);

    private final ResourceKey<ConfiguredFeature<?, ?>> feature;

    public SmallBonePile(Properties properties) {
        super(properties);
        this.feature = FEATURE;
    }

    public SmallBonePile() {
        this(BlockBehaviour.Properties.of()
                .sound(SoundType.BONE_BLOCK)
                .dynamicShape()
                .noOcclusion()
                .noCollission());
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.getBlock() == MNBlocks.BONE_NYLIUM.get();
    }

    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> getFeature() {
        return feature;
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level,
                                        BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
