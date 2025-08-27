package nl.eetgeenappels.mythic_nether.client.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import nl.eetgeenappels.mythic_nether.client.MythicNetherCommonClient;
import nl.eetgeenappels.mythic_nether.init.MNBlocks;

public class MythicNetherFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(MNBlocks.SCULK_LEAVES.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MNBlocks.BLACKSTONE_SHRUB.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MNBlocks.SMALL_BONE_PILE.get(), RenderType.cutout());

        MythicNetherCommonClient.init();
    }
}
