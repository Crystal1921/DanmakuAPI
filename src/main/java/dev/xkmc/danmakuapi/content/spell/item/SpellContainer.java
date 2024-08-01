package dev.xkmc.danmakuapi.content.spell.item;

import dev.xkmc.danmakuapi.init.DanmakuAPI;
import dev.xkmc.l2core.capability.conditionals.ConditionalToken;
import dev.xkmc.l2core.capability.conditionals.TokenKey;
import dev.xkmc.l2core.init.L2LibReg;
import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Supplier;

@SerialClass
public class SpellContainer extends ConditionalToken {

	private static final TokenKey<SpellContainer> SPELL = TokenKey.of(DanmakuAPI.loc("spellcards"));

	public static void castSpell(ServerPlayer sp, Supplier<? extends ItemSpell> sup, @Nullable LivingEntity target) {
		ItemSpell spell = sup.get();
		spell.start(sp, target);
		L2LibReg.CONDITIONAL.type().getOrCreate(sp).getOrCreateData(SPELL, SpellContainer::new).spells.add(spell);
	}

	@SerialField
	private final ArrayList<ItemSpell> spells = new ArrayList<>();

	@Override
	public boolean tick(Player player) {
		spells.removeIf(e -> e.tick(player));
		return spells.isEmpty();
	}

}
