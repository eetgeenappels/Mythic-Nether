package nl.eetgeenappels.mythic_nether.init;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import nl.eetgeenappels.mythic_nether.MythicNether;
import nl.eetgeenappels.mythic_nether.entity.SculkyEntity;

public class MNEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(MythicNether.MOD_ID, Registries.ENTITY_TYPE);
    public static final RegistrySupplier<EntityType<SculkyEntity>> SCULKY = REGISTRY.register("sculky",  () -> EntityType.Builder.of(SculkyEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 0.8F)
            .build("null")
    );

    public static void register() {
        REGISTRY.register();

        EntityAttributeRegistry.register(MNEntities.SCULKY, SculkyEntity::createAttributes);
    }
}