package nl.eetgeenappels.mythic_nether.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.ai.behavior.InteractWithDoor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TwistingVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import nl.eetgeenappels.mythic_nether.init.MNBlocks;
import org.jetbrains.annotations.NotNull;

public class HeavenlyVine extends TwistingVinesBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public HeavenlyVine() {
        super(Properties.of().instabreak().noCollission().noOcclusion());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite())
                .setValue(AGE, 1);
    }

    @Override
    protected @NotNull Block getBodyBlock() {
        return MNBlocks.HEAVENLY_VINE.get();
    }
}
