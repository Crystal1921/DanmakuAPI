package dev.xkmc.danmakuapi.content.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dev.xkmc.fastprojectileapi.entity.SimplifiedProjectile;
import dev.xkmc.fastprojectileapi.render.ProjectileRenderHelper;
import dev.xkmc.fastprojectileapi.render.ProjectileRenderer;
import dev.xkmc.fastprojectileapi.render.RenderableProjectileType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public record SimpleProjectileType(ResourceLocation tex, boolean highlight)
		implements RenderableProjectileType<SimpleProjectileType, SimpleProjectileType.Ins> {

	@Override
	public RenderLevelStageEvent.Stage stage() {
		return RenderLevelStageEvent.Stage.AFTER_WEATHER;
	}

	@Override
	public void start(MultiBufferSource buffer, Iterable<Ins> list) {
		VertexConsumer vc;
		vc = buffer.getBuffer(highlight ? DanmakuRenderStates.additive(tex) :
				DanmakuRenderStates.transparent(tex));
		for (var e : list) {
			e.tex(vc, -1);
		}
	}

	@Override
	public void create(ProjectileRenderer r, SimplifiedProjectile e, PoseStack pose, float pTick) {
		pose.mulPose(r.cameraOrientation());
		pose.mulPose(Axis.YP.rotationDegrees(180.0F));
		PoseStack.Pose mat = pose.last();
		Matrix4f m4 = new Matrix4f(mat.pose());
		Matrix3f m3 = new Matrix3f(mat.normal());
		ProjectileRenderHelper.add(this, new Ins(m3, m4));
	}

	public record Ins(Matrix3f m3, Matrix4f m4) {

		public void tex(VertexConsumer vc, int color) {
			vertex(vc, m4, m3, 1, 1, 1, 0, color);
			vertex(vc, m4, m3, 1, 0, 1, 1, color);
			vertex(vc, m4, m3, 0, 0, 0, 1, color);
			vertex(vc, m4, m3, 0, 1, 0, 0, color);
		}

		private static void vertex(VertexConsumer vc, Matrix4f m4, Matrix3f m3, float x, int y, int u, int v, int color) {
			vc.addVertex(m4, x - 0.5F, y - 0.5F, 0.0F).setUv(u, v).setColor(color);
		}

	}
}
