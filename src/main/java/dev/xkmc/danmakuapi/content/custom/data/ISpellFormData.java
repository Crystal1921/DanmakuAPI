package dev.xkmc.danmakuapi.content.custom.data;

import dev.xkmc.danmakuapi.content.spell.item.ItemSpell;
import dev.xkmc.l2serial.util.Wrappers;
import net.minecraft.world.item.Item;

public interface ISpellFormData<T extends Record & ISpellFormData<T>> {

	int getDuration();

	int cost();

	ItemSpell createInstance();

	default T cast() {
		return Wrappers.cast(this);
	}

	Item getAmmoCost();

}
