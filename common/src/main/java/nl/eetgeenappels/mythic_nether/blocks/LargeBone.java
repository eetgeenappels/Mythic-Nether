package nl.eetgeenappels.mythic_nether.blocks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.eetgeenappels.mythic_nether.MythicNether;
import nl.eetgeenappels.mythic_nether.init.MNBlocks;
import org.jetbrains.annotations.NotNull;

public class LargeBone  extends BushBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LEANING = BooleanProperty.create("leaning");

    public static final RandomSource randomSource = RandomSource.create();

    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MythicNether.MOD_ID, "boney_foliage"));


    private static final VoxelShape SHAPE = Block.box(6.0D, 0.0D, 6.0D,
            10.0D, 32.0D, 10.0D);

    public static final MapCodec<LargeBonePile> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    propertiesCodec()
            ).apply(instance, LargeBonePile::new)
    );


    private final ResourceKey<ConfiguredFeature<?, ?>> feature;

    public LargeBone() {
        super(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.BONE_BLOCK).noOcclusion()
        );
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(LEANING, true)
        );
        this.feature = FEATURE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.getBlock() == MNBlocks.BONE_NYLIUM.get();
    }

    public LargeBone(Properties properties) {
        super(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.BONE_BLOCK).noOcclusion()
        );
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.getRandom(randomSource))
                .setValue(LEANING, randomSource.nextBoolean())
        );
        this.feature = FEATURE;
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEANING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite())
                .setValue(LEANING, Boolean.FALSE);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level,
                                        BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> getFeature() {
        return feature;
    }
}
