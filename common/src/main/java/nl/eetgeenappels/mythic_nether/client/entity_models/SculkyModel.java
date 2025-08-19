package nl.eetgeenappels.mythic_nether.client.entity_models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import nl.eetgeenappels.mythic_nether.MythicNether;
import nl.eetgeenappels.mythic_nether.entity.SculkyEntity;

public class SculkyModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MythicNether.MOD_ID, "skulky"), "main");
	private final ModelPart ear1;
	private final ModelPart ear2;
	private final ModelPart head;
	private final ModelPart propellor;
	private final ModelPart bb_main;

	public SculkyModel(ModelPart root) {
		this.ear1 = root.getChild("ear1");
		this.ear2 = root.getChild("ear2");
		this.head = root.getChild("head");
		this.propellor = root.getChild("propellor");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ear1 = partdefinition.addOrReplaceChild("ear1", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -14.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.0F, -15.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 23.0F, -1.0F));

		PartDefinition ear2 = partdefinition.addOrReplaceChild("ear2", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -14.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.0F, -15.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 23.0F, -1.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -12.0F, -4.0F, 10.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 23.0F, -1.0F));

		PartDefinition propeller = partdefinition.addOrReplaceChild("propellor", CubeListBuilder.create().texOffs(0, 18).addBox(-5.0F, -1.0F, -0.5F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -0.5F));

		PartDefinition cube_r1 = propeller.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 18).addBox(-6.0F, -1.0F, 0.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.5F, 0.0F, -2.1817F, 0.0F));

		PartDefinition cube_r2 = propeller.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 18).addBox(-6.0F, -2.0F, 0.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, 0.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 20).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.propellor.yRot = ((SculkyEntity) entity).propellerSpinProgress;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,int k ) {
		ear1.render(poseStack, vertexConsumer, packedLight, packedOverlay, k);
		ear1.render(poseStack, vertexConsumer, packedLight, packedOverlay, k);
		ear2.render(poseStack, vertexConsumer, packedLight, packedOverlay, k);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, k);
		propellor.render(poseStack, vertexConsumer, packedLight, packedOverlay, k);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, k);
	}
}