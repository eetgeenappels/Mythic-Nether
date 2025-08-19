package nl.eetgeenappels.mythic_nether.entity.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.*;
import net.minecraft.world.phys.Vec3;
import nl.eetgeenappels.mythic_nether.entity.SculkyEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Random;

public class RandomFloatAroundGoal extends Goal {
    private final SculkyEntity sculky;
    private final double speed;

    public RandomFloatAroundGoal(SculkyEntity mob, double speed) {
        this.sculky = mob;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return !sculky.getMoveControl().hasWanted() && sculky.getRandom().nextInt(10) == 0;
    }

    @Nullable
    protected Vec3 getPosition() {
        Vec3 vec3 = this.sculky.getViewVector(0.0F);
        int i = 8;
        Vec3 vec32 = HoverRandomPos.getPos(this.sculky, 8, 7, vec3.x, vec3.z, ((float)Math.PI / 2F), 3, 1);
        return vec32 != null ? vec32 : AirAndWaterRandomPos.getPos(this.sculky, 8, 4, -2, vec3.x, vec3.z, (double)((float)Math.PI / 2F));
    }

    @Nullable
    public static Vec3 getPos(SculkyEntity pathfinderMob, int i, int j, double d, double e, float f, int k, int l) {
        boolean bl = GoalUtils.mobRestricted(pathfinderMob, i);
        return RandomPos.generateRandomPos(pathfinderMob, () -> {
            BlockPos blockPos = RandomPos.generateRandomDirectionWithinRadians(pathfinderMob.getRandom(), i, j, 0, d, e, (double)f);
            if (blockPos == null) {
                return null;
            } else {
                BlockPos blockPos2 = LandRandomPos.generateRandomPosTowardDirection(pathfinderMob, i, bl, blockPos);
                if (blockPos2 == null) {
                    return null;
                } else {
                    blockPos2 = RandomPos.moveUpToAboveSolid(blockPos2, pathfinderMob.getRandom().nextInt(k - l + 1) + l, pathfinderMob.level().getMaxBuildHeight(), (blockPosx) -> GoalUtils.isSolid(pathfinderMob, blockPosx));
                    return !GoalUtils.isWater(pathfinderMob, blockPos2) && !GoalUtils.hasMalus(pathfinderMob, blockPos2) ? blockPos2 : null;
                }
            }
        });
    }
}
