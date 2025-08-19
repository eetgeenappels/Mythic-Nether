package nl.eetgeenappels.mythic_nether.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import nl.eetgeenappels.mythic_nether.client.entity_models.SculkyModel;
import nl.eetgeenappels.mythic_nether.client.entity_renderer.SculkyRenderer;
import nl.eetgeenappels.mythic_nether.init.MNEntities;

public class MythicNetherCommonClient {

    public static void init() {
        EntityModelLayerRegistry.register(SculkyModel.LAYER_LOCATION, SculkyModel::createBodyLayer);
        EntityRendererRegistry.register(MNEntities.SCULKY, SculkyRenderer::new);
    }

}
