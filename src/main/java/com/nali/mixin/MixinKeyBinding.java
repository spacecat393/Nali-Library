//package com.nali.mixin;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.settings.KeyBinding;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Mutable;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import static com.nali.key.KeyHelper.IKEY_ARRAY;
//
//@Mixin(KeyBinding.class)
//public abstract class MixinKeyBinding
//{
//	@Inject(method = "resetKeyBindingArrayAndHash", at = @At(value = "TAIL"))
//	private static void nali_resetKeyBindingArrayAndHash(CallbackInfo callbackinfo)
//	{
//		for (KeyBinding keybinding : IKEY_ARRAY)
//		{
//			keybinding.setKeyModifierAndCode(keybinding.getKeyModifierDefault(), 0);
//			Minecraft.getMinecraft().gameSettings.setOptionKeyBinding(keybinding, keybinding.getKeyCode());
//		}
//	}
//}
