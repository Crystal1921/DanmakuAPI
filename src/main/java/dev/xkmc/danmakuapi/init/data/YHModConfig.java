package dev.xkmc.danmakuapi.init.data;

import dev.xkmc.danmakuapi.init.DanmakuAPI;
import dev.xkmc.l2core.util.ConfigInit;
import net.neoforged.neoforge.common.ModConfigSpec;

public class YHModConfig {

	public static class Client extends ConfigInit {

		public final ModConfigSpec.BooleanValue laserRenderAdditive;
		public final ModConfigSpec.BooleanValue laserRenderInverted;
		public final ModConfigSpec.DoubleValue laserTransparency;

		Client(ModConfigSpec.Builder builder) {
			laserRenderAdditive = builder.define("laserRenderAdditive", true);
			laserRenderInverted = builder.define("laserRenderInverted", true);
			laserTransparency = builder.defineInRange("laserTransparency", 0.5, 0, 1);
		}

	}

	public static class Server extends ConfigInit {

		public final ModConfigSpec.DoubleValue danmakuMinPHPDamage;
		public final ModConfigSpec.DoubleValue danmakuPlayerPHPDamage;
		public final ModConfigSpec.DoubleValue danmakuHealOnHitTarget;
		public final ModConfigSpec.IntValue playerDanmakuCooldown;
		public final ModConfigSpec.IntValue playerLaserCooldown;
		public final ModConfigSpec.IntValue playerSpellCooldown;
		public final ModConfigSpec.IntValue playerLaserDuration;

		Server(ModConfigSpec.Builder builder) {

			builder.push("danmaku_battle");
			{
				danmakuMinPHPDamage = builder.comment("Minimum damage youkai danmaku will deal against non-player")
						.defineInRange("danmakuMinPHPDamage", 0.02, 0, 1);
				danmakuPlayerPHPDamage = builder.comment("Minimum damage youkai danmaku will deal against player")
						.defineInRange("danmakuPlayerPHPDamage", 0.1, 0, 1);
				danmakuHealOnHitTarget = builder.comment("When danmaku hits target, heal youkai health by percentage of max health")
						.defineInRange("danmakuHealOnHitTarget", 0.2, 0, 1);
				playerDanmakuCooldown = builder.comment("Player item cooldown for using danmaku")
						.defineInRange("playerDanmakuCooldown", 20, 5, 1000);
				playerLaserCooldown = builder.comment("Player item cooldown for using laser")
						.defineInRange("playerLaserCooldown", 80, 5, 1000);
				playerSpellCooldown = builder.comment("Player item cooldown for using spellcard")
						.defineInRange("playerSpellCooldown", 40, 5, 1000);
				playerLaserDuration = builder.comment("Player laser duration")
						.defineInRange("playerLaserDuration", 100, 5, 1000);
			}
			builder.pop();

		}

	}

	public static final Client CLIENT = DanmakuAPI.REGISTRATE.registerClient(Client::new);
	public static final Server SERVER = DanmakuAPI.REGISTRATE.registerSynced(Server::new);

	public static void init() {
	}


}
