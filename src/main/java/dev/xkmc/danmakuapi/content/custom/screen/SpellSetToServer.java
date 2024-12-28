package dev.xkmc.danmakuapi.content.custom.screen;

import dev.xkmc.l2serial.network.SerialPacketBase;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public record SpellSetToServer(Record data) implements SerialPacketBase<SpellSetToServer> {

	@Override
	public void handle(Player player) {
		if (player instanceof ServerPlayer sp)
			ServerCustomSpellHandler.handle(sp, data);
	}

}
