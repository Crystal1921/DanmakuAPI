package dev.xkmc.danmakuapi.init;

import dev.xkmc.danmakuapi.content.particle.DanmakuPoofParticle;
import dev.xkmc.danmakuapi.content.item.DanmakuItemDeco;
import dev.xkmc.danmakuapi.content.item.SpellItem;
import dev.xkmc.danmakuapi.init.registrate.DanmakuItems;
import dev.xkmc.fastprojectileapi.render.ProjectileRenderHelper;
import net.minecraft.world.item.DyeColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(value = Dist.CLIENT, modid = DanmakuAPI.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DanmakuClient {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			for (var e : DanmakuItems.Bullet.values())
				for (var d : DyeColor.values())
					e.get(d).get().getTypeForRender();
			for (var e : DanmakuItems.Laser.values())
				for (var d : DyeColor.values())
					e.get(d).get().getTypeForRender();
			ProjectileRenderHelper.setup();
		});
	}

	@SubscribeEvent
	public static void registerItemDeco(RegisterItemDecorationsEvent event) {
		var deco = new DanmakuItemDeco();
		for (var col : DyeColor.values()) {
			for (var e : DanmakuItems.Bullet.values()) {
				event.register(e.get(col), deco);
			}
			for (var e : DanmakuItems.Laser.values()) {
				event.register(e.get(col), deco);
			}
		}
		event.register(DanmakuItems.CUSTOM_SPELL_RING.get(), deco);
		event.register(DanmakuItems.CUSTOM_SPELL_HOMING.get(), deco);
		for (var e : SpellItem.LIST) {
			event.register(e, deco);
		}
	}

	@SubscribeEvent
	public static void registerParticle(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(DanmakuItems.POOF.get(), DanmakuPoofParticle.Provider::new);
	}

}
