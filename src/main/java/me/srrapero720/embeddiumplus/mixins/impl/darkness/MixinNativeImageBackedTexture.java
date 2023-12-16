package me.srrapero720.embeddiumplus.mixins.impl.darkness;

import net.minecraft.client.renderer.texture.DynamicTexture;
import com.mojang.blaze3d.platform.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srrapero720.embeddiumplus.features.true_darkness.Darkness;
import me.srrapero720.embeddiumplus.features.true_darkness.TextureAccess;

@Mixin(DynamicTexture.class)
public class MixinNativeImageBackedTexture implements TextureAccess {
	@Shadow
	private NativeImage pixels;

	@Unique
	private boolean embeddiumPlus$enableHook = false;

	@Inject(method = "upload", at = @At(value = "HEAD"))
	private void onRenderWorld(CallbackInfo ci) {
		if (embeddiumPlus$enableHook && Darkness.enabled) {
			final NativeImage img = pixels;
			for (int b = 0; b < 16; b++) {
				for (int s = 0; s < 16; s++) {
					final int color = Darkness.darken(img.getPixelRGBA(b, s), b, s);
					img.setPixelRGBA(b, s, color);
				}
			}
		}
	}

	@Override
	public void darkness_enableUploadHook() {
		embeddiumPlus$enableHook = true;
	}
}