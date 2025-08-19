package nl.eetgeenappels.mythic_nether.client.entity_renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import nl.eetgeenappels.mythic_nether.MythicNether;
import nl.eetgeenappels.mythic_nether.client.entity_models.SculkyModel;
import nl.eetgeenappels.mythic_nether.entity.SculkyEntity;
import org.jetbrains.annotations.NotNull;

public class SculkyRenderer extends MobRenderer<SculkyEntity, SculkyModel<SculkyEntity>> {

    public SculkyRenderer(EntityRendererProvider.Context context) {
        super(context, new SculkyModel<>(context.bakeLayer(SculkyModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(SculkyEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MythicNether.MOD_ID, "textures/entity/sculky/sculky.png");
    }
}