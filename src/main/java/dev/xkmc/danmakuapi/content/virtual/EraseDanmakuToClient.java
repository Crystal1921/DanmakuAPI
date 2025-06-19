package dev.xkmc.danmakuapi.content.virtual;

import dev.xkmc.fastprojectileapi.entity.SimplifiedProjectile;
import dev.xkmc.l2serial.network.SerialPacketBase;
import net.minecraft.world.entity.player.Player;

public record EraseDanmakuToClient(int id, boolean kill) implements SerialPacketBase<EraseDanmakuToClient> {


	public static EraseDanmakuToClient of(SimplifiedProjectile e, boolean kill) {
		return new EraseDanmakuToClient(e.getId(), kill);
	}

	@Override
	public void handle(Player player) {
		DanmakuClientHandler.erase(id, kill);
	}

}
