package dev.xkmc.danmakuapi.content.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.xkmc.danmakuapi.content.item.DanmakuItem;
import dev.xkmc.fastprojectileapi.render.ProjectileRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.joml.Quaternionf;

public class ItemBulletRenderer<T extends ItemBulletEntity> extends EntityRenderer<T> implements ProjectileRenderer {

	private static final float MIN_CAMERA_DISTANCE_SQUARED = 12.25F;

	public ItemBulletRenderer(EntityRendererProvider.Context pContext) {
		super(pContext);
	}

	protected int getBlockLightLevel(T e, BlockPos pPos) {
		return e.fullBright() ? 15 : super.getBlockLightLevel(e, pPos);
	}

	@Override
	public boolean shouldRender(T pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
		return true;
	}

	@Override
	public Quaternionf cameraOrientation() {
		return entityRenderDispatcher.cameraOrientation();
	}

	public void render(T e, float yaw, float pTick, PoseStack pose, MultiBufferSource buffer, int light) {
		if (!(e.getItem().getItem() instanceof DanmakuItem danmaku)) return;
		if (e.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(e) < MIN_CAMERA_DISTANCE_SQUARED)) {
			pose.pushPose();
			float scale = e.scale();
			pose.translate(0, e.getBbHeight() / 2, 0);
			pose.scale(scale, scale, scale);
			danmaku.getTypeForRender().create(this, e, pose, pTick);
			pose.popPose();
			super.render(e, yaw, pTick, pose, buffer, light);
		}
	}

	public ResourceLocation getTextureLocation(T pEntity) {
		return TextureAtlas.LOCATION_BLOCKS;
	}

}