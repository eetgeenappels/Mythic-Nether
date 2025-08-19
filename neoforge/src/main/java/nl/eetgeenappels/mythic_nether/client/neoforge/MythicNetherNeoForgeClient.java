package nl.eetgeenappels.mythic_nether.client.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import nl.eetgeenappels.mythic_nether.MythicNether;
import nl.eetgeenappels.mythic_nether.client.MythicNetherCommonClient;
import nl.eetgeenappels.mythic_nether.client.entity_models.SculkyModel;
import nl.eetgeenappels.mythic_nether.client.entity_renderer.SculkyRenderer;
import nl.eetgeenappels.mythic_nether.init.MNEntities;

@EventBusSubscriber(value = Dist.CLIENT)
public class MythicNetherNeoForgeClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MythicNether.LOGGER.info("Mythic Nether Client has been initialized");
        MythicNetherCommonClient.init();
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SculkyModel.LAYER_LOCATION, SculkyModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MNEntities.SCULKY.get(), SculkyRenderer::new);
    }
}
