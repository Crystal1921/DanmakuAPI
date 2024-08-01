package dev.xkmc.danmakuapi.content.item;

import dev.xkmc.danmakuapi.api.DanmakuLaser;
import dev.xkmc.danmakuapi.api.DanmakuUseEvent;
import dev.xkmc.danmakuapi.content.entity.ItemLaserEntity;
import dev.xkmc.danmakuapi.init.DanmakuAPI;
import dev.xkmc.danmakuapi.init.data.DanmakuLang;
import dev.xkmc.danmakuapi.init.data.DanmakuConfig;
import dev.xkmc.danmakuapi.init.registrate.DanmakuEntities;
import dev.xkmc.fastprojectileapi.render.DoubleLayerLaserType;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;

import java.util.List;

public class LaserItem extends Item {

	public final DyeColor color;
	public final DanmakuLaser type;
	public final float size;

	public LaserItem(Properties pProperties, DanmakuLaser type, DyeColor color, float size) {
		super(pProperties);
		this.color = color;
		this.type = type;
		this.size = size;
	}

	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		int cooldown = DanmakuConfig.SERVER.playerLaserCooldown.get();
		var event = new DanmakuUseEvent(player, stack, cooldown);
		NeoForge.EVENT_BUS.post(event);
		if (event.isCanceled()) {
			return InteractionResultHolder.fail(stack);
		}
		level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS,
				0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!level.isClientSide) {
			ItemLaserEntity danmaku = new ItemLaserEntity(DanmakuEntities.ITEM_LASER.get(), player, level);
			danmaku.setItem(stack);
			int dur = DanmakuConfig.SERVER.playerLaserDuration.get();
			danmaku.setup(type.damage(), dur, 40, false, player.getYRot(), player.getXRot());
			level.addFreshEntity(danmaku);
		}
		player.awardStat(Stats.ITEM_USED.get(this));
		player.getCooldowns().addCooldown(this, event.getCooldown());
		if (event.consume()) {
			stack.shrink(1);
		}
		return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
	}

	public int getDanmakuColor(ItemStack stack, int i) {
		return i == 0 ? color.getFireworkColor() : 0xffffffff;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext level, List<Component> list, TooltipFlag flag) {
		list.add(DanmakuLang.DANMAKU_DAMAGE.get(type.damage()));
	}

	private DoubleLayerLaserType render;

	public DoubleLayerLaserType getTypeForRender() {
		if (render == null) {
			render = new DoubleLayerLaserType(
					DanmakuAPI.loc("textures/entities/laser_inner.png"),
					DanmakuAPI.loc("textures/entities/laser_outer.png"),
					0xff000000 | color.getFireworkColor());
		}
		return render;
	}

}
