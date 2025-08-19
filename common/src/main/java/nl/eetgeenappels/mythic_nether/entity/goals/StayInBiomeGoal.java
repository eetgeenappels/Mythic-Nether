package nl.eetgeenappels.mythic_nether.entity.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.biome.Biome;
import nl.eetgeenappels.mythic_nether.entity.SculkyEntity;

public class StayInBiomeGoal extends Goal {
    private final PathfinderMob mob;
    private final ResourceKey<Biome> homeBiome;

    public StayInBiomeGoal(PathfinderMob mob, ResourceKey<Biome> homeBiome) {
        this.mob = mob;
        this.homeBiome = homeBiome;
    }

    @Override
    public boolean canUse() {
        if (!(mob.level() instanceof ServerLevel serverLevel)) return false;

        BlockPos pos = mob.blockPosition();
        ResourceKey<Biome> currentBiome = serverLevel.getBiome(pos).unwrapKey().orElse(null);

        if (currentBiome != null && mob instanceof SculkyEntity) {
            return homeBiome != null && !currentBiome.equals(homeBiome);
        }
        return false;
    }

    @Override
    public void start() {
        // teleport or force back inside biome edge
        if (mob instanceof SculkyEntity && homeBiome != null && mob.level() instanceof ServerLevel serverLevel) {
            BlockPos pos = mob.blockPosition();
            if (!serverLevel.getBiome(pos).is(homeBiome)) {
                // just move them a bit back
                mob.getMoveControl().setWantedPosition(pos.getX(), pos.getY() + 2, pos.getZ(), 1.0);
            }
        }
    }
}
