package dev.xkmc.danmakuapi.content.spell.spellcard;

import dev.xkmc.l2serial.serialization.marker.SerialClass;
import dev.xkmc.l2serial.serialization.marker.SerialField;

import javax.annotation.OverridingMethodsMustInvokeSuper;

@SerialClass
public class Ticker<T> {

	@SerialField
	public int tick;

	@OverridingMethodsMustInvokeSuper
	public boolean tick(CardHolder holder, T card) {
		tick++;
		return true;
	}

}
