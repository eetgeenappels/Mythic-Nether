package nl.eetgeenappels.mythic_nether.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class GiantBlobFeature extends Feature<GiantBlobConfig> {
    public GiantBlobFeature(Codec<GiantBlobConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<GiantBlobConfig> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        GiantBlobConfig config = context.config();

        // Find ground surface
        BlockPos ground = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, origin);
        int radius = config.radius();

        BlockPos.MutableBlockPos placePos = new BlockPos.MutableBlockPos();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                int localMaxY = getLocalMax(level, ground.offset(x, 0, z));
                for (int y = -radius; y <= radius; y++) {
                    double dist = Math.sqrt(x * x + y * y + z * z);
                    if (dist <= radius) {
                        placePos.set(
                                ground.getX() + x,
                                localMaxY + 1 + y,
                                ground.getZ() + z
                        );

                        // Optional: only place if we aren't replacing air/liquids
                        if (level.getBlockState(placePos).isAir()) {
                            level.setBlock(placePos, config.block().getState(random, placePos), 2);
                        }
                    }
                }
            }
        }

        return true;
    }

    public static int getLocalMax(WorldGenLevel world, BlockPos pos) {
        int maxY = 180;
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
