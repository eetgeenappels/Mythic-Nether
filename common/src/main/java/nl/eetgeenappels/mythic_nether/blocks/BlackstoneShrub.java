package nl.eetgeenappels.mythic_nether.blocks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

public class BlackstoneShrub extends BushBlock {
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MythicNether.MOD_ID, "boney_foliage"));

    private static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D,
            12.0D, 8.0D, 12.0D);

    public static final MapCodec<LargeBonePile> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    propertiesCodec()
            ).apply(instance, LargeBonePile::new)
    );

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level,
                                        BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    private final ResourceKey<ConfiguredFeature<?, ?>> feature;

    public BlackstoneShrub(Properties properties) {
        super(properties);
        this.feature = FEATURE;
    }

    public BlackstoneShrub() {
        this(BlockBehaviour.Properties.of()
                .sound(SoundType.STONE)
                .dynamicShape()
                .noOcclusion()
                .noCollission());
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.getBlock() == Blocks.BLACKSTONE;
    }

    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> getFeature() {
        return feature;
    }
}
