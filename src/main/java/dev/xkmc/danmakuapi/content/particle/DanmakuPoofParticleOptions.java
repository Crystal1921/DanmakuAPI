package dev.xkmc.danmakuapi.content.particle;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.xkmc.danmakuapi.init.registrate.DanmakuItems;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ScalableParticleOptionsBase;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import org.joml.Vector3f;

public class DanmakuPoofParticleOptions extends ScalableParticleOptionsBase {

	public static final MapCodec<DanmakuPoofParticleOptions> CODEC =
			RecordCodecBuilder.mapCodec(i -> i.group(
					ExtraCodecs.VECTOR3F.fieldOf("color").forGetter((e) -> e.color),
					SCALE.fieldOf("scale").forGetter(DanmakuPoofParticleOptions::getScale)
			).apply(i, DanmakuPoofParticleOptions::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, DanmakuPoofParticleOptions> STREAM_CODEC =
			StreamCodec.composite(
					ByteBufCodecs.VECTOR3F, (e) -> e.color,
					ByteBufCodecs.FLOAT, DanmakuPoofParticleOptions::getScale,
					DanmakuPoofParticleOptions::new);


	private final Vector3f color;

	public DanmakuPoofParticleOptions(Vector3f color, float scale) {
		super(scale);
		this.color = color;
	}

	public Vector3f getColor() {
		return this.color;
	}

	public ParticleType<DanmakuPoofParticleOptions> getType() {
		return DanmakuItems.POOF.get();
	}

}