package dev.xkmc.danmakuapi.init.data;

import dev.xkmc.danmakuapi.init.DanmakuAPI;
import dev.xkmc.l2core.util.ConfigInit;
import net.neoforged.neoforge.common.ModConfigSpec;

public class DanmakuConfig {

	public static class Server extends ConfigInit {

		public final ModConfigSpec.IntValue playerDanmakuCooldown;
		public final ModConfigSpec.IntValue playerLaserCooldown;
		public final ModConfigSpec.IntValue playerSpellCooldown;
		public final ModConfigSpec.IntValue playerLaserDuration;

		Server(ModConfigSpec.Builder builder) {
			markL2();
			playerDanmakuCooldown = builder.comment("Player item cooldown for using danmaku")
					.defineInRange("playerDanmakuCooldown", 20, 5, 1000);
			playerLaserCooldown = builder.comment("Player item cooldown for using laser")
					.defineInRange("playerLaserCooldown", 80, 5, 1000);
			playerSpellCooldown = builder.comment("Player item cooldown for using spellcard")
					.defineInRange("playerSpellCooldown", 40, 5, 1000);
			playerLaserDuration = builder.comment("Player laser duration")
					.defineInRange("playerLaserDuration", 100, 5, 1000);
		}

	}

	public static final Server SERVER = DanmakuAPI.REGISTRATE.registerSynced(Server::new);

	public static void init() {
	}


}
