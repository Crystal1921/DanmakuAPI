package dev.xkmc.danmakuapi.content.virtual;

import dev.xkmc.danmakuapi.init.DanmakuAPI;
import dev.xkmc.fastprojectileapi.entity.SimplifiedProjectile;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class DanmakuManager {

	public static void send(LivingEntity user, List<SimplifiedProjectile> proj) {
		DanmakuAPI.HANDLER.toTrackingPlayers(DanmakuToClientPacket.of(user.registryAccess(), proj), user);
	}

	public static void erase(LivingEntity user, SimplifiedProjectile proj, boolean kill) {
		DanmakuAPI.HANDLER.toTrackingPlayers(EraseDanmakuToClient.of(proj, kill), user);
	}

}
