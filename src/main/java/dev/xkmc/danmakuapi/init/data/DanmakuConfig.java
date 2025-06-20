package dev.xkmc.danmakuapi.init.data;

import dev.xkmc.danmakuapi.init.DanmakuAPI;
import dev.xkmc.l2core.util.ConfigInit;
import net.neoforged.neoforge.common.ModConfigSpec;

public class DanmakuConfig {

	public static class Client extends ConfigInit {

		public final ModConfigSpec.BooleanValue laserRenderAdditive;
		public final ModConfigSpec.BooleanValue laserRenderInverted;
		public final ModConfigSpec.DoubleValue laserTransparency;
		public final ModConfigSpec.DoubleValue farDanmakuFading;
		public final ModConfigSpec.DoubleValue selfDanmakuFading;
		public final ModConfigSpec.DoubleValue fadingStart;
		public final ModConfigSpec.DoubleValue fadingEnd;
		public final ModConfigSpec.IntValue powerInfoXAnchor;
		public final ModConfigSpec.IntValue powerInfoXOffset;
		public final ModConfigSpec.IntValue powerInfoYAnchor;
		public final ModConfigSpec.IntValue powerInfoYOffset;


		Client(Builder builder) {
			markL2();
			laserRenderAdditive = builder.text("Additive laser layer").define("laserRenderAdditive", true);
			laserRenderInverted = builder.text("Inverted layer layer").define("laserRenderInverted", true);
			laserTransparency = builder.text("Laser outer layer transparency").defineInRange("laserTransparency", 0.5, 0, 1);
			farDanmakuFading = builder.defineInRange("farDanmakuFading", 0.5d, 0, 1);
			selfDanmakuFading = builder.defineInRange("selfDanmakuFading", 0.5d, 0, 1);
			fadingStart = builder.defineInRange("fadingStart", 8d, 0, 128);
			fadingEnd = builder.defineInRange("fadingEnd", 64d, 0, 128);
			powerInfoXAnchor = builder.defineInRange("powerInfoXAnchor", 1, -1, 1);
			powerInfoXOffset = builder.defineInRange("powerInfoXOffset", -8, -1000, 1000);
			powerInfoYAnchor = builder.defineInRange("powerInfoYAnchor", 0, -1, 1);
			powerInfoYOffset = builder.defineInRange("powerInfoYOffset", 0, -1000, 1000);
		}

	}

	public static class Server extends ConfigInit {

		public final ModConfigSpec.IntValue playerDanmakuCooldown;
		public final ModConfigSpec.IntValue playerLaserCooldown;
		public final ModConfigSpec.IntValue playerSpellCooldown;
		public final ModConfigSpec.IntValue playerLaserDuration;

		public final ModConfigSpec.IntValue customSpellMaxDuration;
		public final ModConfigSpec.IntValue ringSpellDanmakuPerItemCost;
		public final ModConfigSpec.IntValue homingSpellDanmakuPerItemCost;

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

			customSpellMaxDuration = builder.text("Max duration of custom spell allowed")
					.defineInRange("customSpellMaxDuration", 60, 1, 1000);
			ringSpellDanmakuPerItemCost = builder.text("Ring Spell: Max number of bullet allowed per item cost")
					.defineInRange("ringSpellDanmakuPerItemCost", 32, 1, 1024);
			homingSpellDanmakuPerItemCost = builder.text("Homing Spell: Max number of bullet allowed per item cost")
					.defineInRange("homingSpellDanmakuPerItemCost", 8, 1, 1024);

		}

	}

	public static final Client CLIENT = DanmakuAPI.REGISTRATE.registerClient(Client::new);
	public static final Server SERVER = DanmakuAPI.REGISTRATE.registerSynced(Server::new);

	public static void init() {
	}


}
