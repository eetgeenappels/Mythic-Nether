package nl.eetgeenappels.mythic_nether.blocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.material.MapColor;

public class SculkLeaves extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 1, 7);
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;

    public static final TagKey<Block> woodTag = TagKey.create(Registries.BLOCK, ResourceLocation.parse("true_end:wood"));


    public SculkLeaves() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.SCULK_VEIN).strength(0.2f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false).randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(DISTANCE, 7));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED, DISTANCE, PERSISTENT);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return super.getStateForPlacement(context).setValue(WATERLOGGED, flag).setValue(DISTANCE, 7);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos blockPos, RandomSource randomSource) {
        if (state.getValue(PERSISTENT)) {
            return;
        }
        if (state.getValue(DISTANCE) == 7) {
            if (randomSource.nextDouble() < 0.2) {
                level.removeBlock(blockPos, true);
            }
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        int minDistance = 7;
        for (Direction direction : Direction.values()) {
            BlockState neighbor = world.getBlockState(currentPos.relative(direction));
            if (neighbor.is(woodTag)) {
                minDistance = 1;
                break;
            } else if (neighbor.getBlock() instanceof SculkLeaves) {
                minDistance = Math.min(minDistance, neighbor.getValue(DISTANCE) + 1);
            }
        }
        world.scheduleTick(currentPos, this, 20);
        return state.setValue(DISTANCE, minDistance);
    }
}
