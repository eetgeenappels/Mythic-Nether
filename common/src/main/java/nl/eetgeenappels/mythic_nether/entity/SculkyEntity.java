package nl.eetgeenappels.mythic_nether.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import nl.eetgeenappels.mythic_nether.entity.goals.RandomFloatAroundGoal;
import org.jetbrains.annotations.NotNull;

public class SculkyEntity extends PathfinderMob {

    public float propellerSpinProgress = 0f;

    public SculkyEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.setLevel(level);

        moveControl = new FlyingMoveControl(this, 1, false);
    }

    public void tick() {
        super.tick();
        moveControl.tick();
        propellerSpinProgress += 5f;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RandomFloatAroundGoal(this, 1.0));
    }

    @Override
    protected @NotNull PathNavigation createNavigation(Level level) {
        FlyingPathNavigation nav = new FlyingPathNavigation(this, level);
        nav.setCanOpenDoors(false);
        nav.setCanFloat(true);
        nav.setCanPassDoors(true);
        return nav;
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH,  8)
                .add(Attributes.FLYING_SPEED, 4);
    }
}