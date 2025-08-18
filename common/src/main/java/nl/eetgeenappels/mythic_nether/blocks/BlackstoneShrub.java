package nl.eetgeenappels.mythic_nether.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class BlackstoneShrub extends Block {
    public BlackstoneShrub() {
        super(BlockBehaviour.Properties.of().sound(SoundType.STONE).dynamicShape().noOcclusion().noCollission());
    }

}
