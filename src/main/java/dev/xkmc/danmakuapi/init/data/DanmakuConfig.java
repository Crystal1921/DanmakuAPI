package dev.xkmc.danmakuapi.init.data;

import dev.xkmc.danmakuapi.init.DanmakuAPI;
import dev.xkmc.l2core.util.ConfigInit;
import net.neoforged.neoforge.common.ModConfigSpec;

public class DanmakuConfig {

	public static class Client extends ConfigInit {

		public final ModConfigSpec.BooleanValue laserRenderAdditive;
		public final ModConfigSpec.BooleanValue laserRenderInverted;
		public final ModConfigSpec.DoubleValue laserTransparency;

		Client(Builder builder) {
			markL2();
			laserRenderAdditive = builder.text("Additive laser layer").define("laserRenderAdditive", true);
			laserRenderInverted = builder.text("Inverted layer layer").define("laserRenderInverted", true);
			laserTransparency = builder.text("Laser outer layer transparency").defineInRange("laserTransparency", 0.5, 0, 1);
		}

	}

	public static class Server extends ConfigInit {

		public final ModConfigSpec.IntValue playerDanmakuCooldown;
		public final ModConfigSpec.IntValue playerLaserCooldown;
		public final ModConfigSpec.IntValue playerSpellCooldown;
		public final ModConfigSpec.IntValue playerLaserDuration;

		Server(Builder builder) {
			markL2();
			playerDanmakuCooldown = builder.text("Player item cooldown for using danmaku")
					.defineInRange("playerDanmakuCooldown", 20, 5, 1000);
			playerLaserCooldown = builder.text("Player item cooldown for using laser")
					.defineInRange("playerLaserCooldown", 80, 5, 1000);
			playerSpellCooldown = builder.text("Player item cooldown for using spellcard")
					.defineInRange("playerSpellCooldown", 40, 5, 1000);
			playerLaserDuration = builder.text("Player laser duration")
					.defineInRange("playerLaserDuration", 100, 5, 1000);
		}

	}

	public static final Client CLIENT = DanmakuAPI.REGISTRATE.registerClient(Client::new);
	public static final Server SERVER = DanmakuAPI.REGISTRATE.registerSynced(Server::new);

	public static void init() {
	}


}
