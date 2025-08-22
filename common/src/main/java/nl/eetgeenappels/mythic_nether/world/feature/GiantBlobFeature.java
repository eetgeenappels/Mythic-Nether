package nl.eetgeenappels.mythic_nether.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Random;

public class GiantBlobFeature extends Feature<GiantBlobConfig> {
    public GiantBlobFeature(Codec<GiantBlobConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<GiantBlobConfig> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        GiantBlobConfig config = context.config();

        // Find ground position
        BlockPos ground = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, pos);

        int radius = config.radius();
        for (int x = -radius; x <= radius; x++) {
            for (int y = 0; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    double dist = Math.sqrt(x * x + y * y + z * z);
                    if (dist <= radius) {
                        BlockPos placePos =  new BlockPos(ground.getX(), getLocalMax(level, ground.offset(x, 0,y)), ground.getZ()).offset(x, y, z);
                        level.setBlock(placePos, config.block().getState(random, placePos), 2);
                    }
                }
            }
        }
        return true;
    }
    public static int getLocalMax(WorldGenLevel world, BlockPos pos) {
        int maxY = world.getMaxBuildHeight() - 1;
        int max = maxY;

        for (int y = maxY; y >= 0; y--) {
            BlockPos checkPos = new BlockPos(pos.getX(), y, pos.getZ());
            if (world.getBlockState(checkPos).getBlock() != net.minecraft.world.level.block.Blocks.AIR) {
                if (y < pos.getY()) {
                    return y - 1;
                } else {
                    // Above or at posY: remember it as potential max
                    max = y - 1;
                }
            }
        }
        return max;
    }



}
