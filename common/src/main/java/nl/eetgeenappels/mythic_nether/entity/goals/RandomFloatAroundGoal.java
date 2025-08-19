package nl.eetgeenappels.mythic_nether.entity.goals;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import nl.eetgeenappels.mythic_nether.entity.SculkyEntity;

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

    @Override
    public void start() {
        RandomSource random = sculky.getRandom();
        double x = sculky.getX() + (random.nextDouble() * 16 - 8);
        double y = sculky.getY() + (random.nextDouble() * 8 - 4); // allow vertical wander
        double z = sculky.getZ() + (random.nextDouble() * 16 - 8);
        sculky.getMoveControl().setWantedPosition(x, y, z, speed);
    }
}
