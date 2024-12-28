package dev.xkmc.danmakuapi.content.custom.screen;

import dev.xkmc.danmakuapi.content.custom.data.ISpellFormData;
import dev.xkmc.danmakuapi.content.custom.editor.SpellOptionInstances;
import dev.xkmc.danmakuapi.init.data.DanmakuConfig;
import dev.xkmc.danmakuapi.init.data.DanmakuLang;
import dev.xkmc.l2serial.util.Wrappers;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class EditorScreen extends Screen {

	private final SpellOptionInstances<?> ins;
	private Button cancel, reset, save;
	private OptionsList list;


	public EditorScreen(ISpellFormData<?> data) {
		super(Component.literal(""));
		ins = SpellOptionInstances.create(Wrappers.cast(data));
	}

	@Override
	protected void init() {
		super.init();
		int w = width / 4;
		int dw = w / 4;
		addRenderableWidget(cancel = new Button.Builder(
				Component.translatable("gui.cancel"),
				e -> onClose()
		).pos(dw, height - 26).size(w, 20).build());
		addRenderableWidget(reset = new Button.Builder(
				DanmakuLang.EDITOR_RESET.get(),
				e -> reset()
		).pos(w + dw * 2, height - 26).size(w, 20).build());
		addRenderableWidget(save = new Button.Builder(
				Component.translatable("gui.done"),
				e -> save()
		).pos(w * 2 + dw * 3, height - 26).size(w, 20).build());

		addOptions();
	}

	private void addOptions() {
		list = new OptionsList(minecraft, width, height, 32, height - 32, 25);
		list.setRenderBackground(false);
		list.setRenderTopAndBottom(false);
		ins.add(list, this::setChanged);
		addRenderableWidget(list);
		reset.active = false;
	}

	private void setChanged() {
		reset.active = true;
		updateSave();
	}

	private void reset() {
		ins.reset();
		removeWidget(list);
		addOptions();
		updateSave();
	}

	private void updateSave() {
		var e = ins.build();
		save.setTooltip(null);
		save.active = false;
		if (e != null) {
			int max = DanmakuConfig.SERVER.customSpellMaxDuration.get();
			int dur = e.getDuration();
			if (dur > max) {
				save.setTooltip(Tooltip.create(DanmakuLang.INVALID_TIME.get(max, dur)));
			} else {
				save.active = true;
			}
		}
	}

	private void save() {
		ins.save();
		onClose();
	}

	@Override
	public void render(GuiGraphics g, int mx, int my, float pTick) {
		renderBackground(g);
		super.render(g, mx, my, pTick);
	}

}
