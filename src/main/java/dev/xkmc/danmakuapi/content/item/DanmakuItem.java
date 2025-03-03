package dev.xkmc.danmakuapi.content.item;

import dev.xkmc.danmakuapi.api.DanmakuBullet;
import dev.xkmc.danmakuapi.api.DanmakuUseEvent;
import dev.xkmc.danmakuapi.content.entity.ItemBulletEntity;
import dev.xkmc.danmakuapi.content.render.ButterflyProjectileType;
import dev.xkmc.danmakuapi.content.render.RotatingProjectileType;
import dev.xkmc.danmakuapi.content.render.SimpleProjectileType;
import dev.xkmc.danmakuapi.init.DanmakuAPI;
import dev.xkmc.danmakuapi.init.data.DanmakuConfig;
import dev.xkmc.danmakuapi.init.data.DanmakuLang;
import dev.xkmc.danmakuapi.init.registrate.DanmakuEntities;
import dev.xkmc.danmakuapi.init.registrate.DanmakuItems;
import dev.xkmc.fastprojectileapi.render.RenderableProjectileType;
import dev.xkmc.l2library.content.raytrace.RayTraceUtil;
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
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;

import java.util.List;

public class DanmakuItem extends Item {

	public final DanmakuBullet type;
	public final DyeColor color;
	public final float size;

	public DanmakuItem(Properties pProperties, DanmakuBullet type, DyeColor color, float size) {
		super(pProperties);
		this.type = type;
		this.color = color;
		this.size = size;
	}

	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		int cooldown = DanmakuConfig.SERVER.playerDanmakuCooldown.get();
		var event = new DanmakuUseEvent(player, stack, cooldown);
		NeoForge.EVENT_BUS.post(event);
		if (event.isCanceled()) {
			return InteractionResultHolder.fail(stack);
		}
		level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS,
				0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
		if (!level.isClientSide) {
			ItemBulletEntity danmaku = new ItemBulletEntity(DanmakuEntities.ITEM_DANMAKU.get(), player, level);
			danmaku.setItem(stack);
			danmaku.setup(type.damage(), 40, false, type.bypass(),
					RayTraceUtil.getRayTerm(Vec3.ZERO, player.getXRot(), player.getYRot(), 2));
			danmaku.moveTo(RayTraceUtil.getRayTerm(player.getEyePosition(), player.getXRot(), player.getYRot(), 2));
			level.addFreshEntity(danmaku);
		}
		player.awardStat(Stats.ITEM_USED.get(this));
		player.getCooldowns().addCooldown(this, event.getCooldown());
		if (event.consume()) {
			stack.shrink(1);
		}
		return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext level, List<Component> list, TooltipFlag flag) {
		list.add(DanmakuLang.DANMAKU_DAMAGE.get(type.damage()));
		if (type.bypass())
			list.add(DanmakuLang.DANMAKU_BYPASS.get());
	}

	private RenderableProjectileType<?, ?> render;

	public RenderableProjectileType<?, ?> getTypeForRender() {
		if (render == null) {
			var loc = DanmakuAPI.loc("textures/entity/bullet/" + type.getName() + "/" + color.getName() + ".png");
			render = switch (type) {
				case DanmakuItems.Bullet.BUTTERFLY -> new ButterflyProjectileType(loc, type.display(), 20);
				case DanmakuItems.Bullet.SPARK -> new RotatingProjectileType(loc, type.display(), 20);
				case DanmakuItems.Bullet.STAR -> new RotatingProjectileType(loc, type.display(), 40);
				default -> new SimpleProjectileType(loc, type.display());
			};
		}
		return render;
	}

}
