package dev.xkmc.danmakuapi.content.custom.data;

import dev.xkmc.danmakuapi.content.custom.annotation.ArgRange;
import dev.xkmc.danmakuapi.content.custom.forms.HomingSpellForm;
import dev.xkmc.danmakuapi.content.spell.item.ItemSpell;
import dev.xkmc.danmakuapi.init.data.DanmakuConfig;
import net.minecraft.world.item.Item;

public record HomingSpellFormData(
		BaseSpellData base,
		RingFormData form,
		@ArgRange(base = 1, factor = 3, decimal = 1)
		double speed,
		@ArgRange(low = 1, high = 60)
		int turnTime
) implements ISpellFormData

		<HomingSpellFormData> {

	public static final HomingSpellFormData RING = new HomingSpellFormData(
			BaseSpellData.DEF, new RingFormData(4, 4, 5,
			90, 20, 2, 0),
			1, 20);

	public int getDuration() {
		return form.getDuration();
	}

	public int cost() {
		int cost = DanmakuConfig.SERVER.homingSpellDanmakuPerItemCost.get();
		return form().branches() * form().steps() / cost + 1;
	}

	@Override
	public Item getAmmoCost() {
		return base().getAmmoCost();
	}

	@Override
	public ItemSpell createInstance() {
		var spell = new HomingSpellForm();
		spell.init(this);
		return spell;
	}

}
