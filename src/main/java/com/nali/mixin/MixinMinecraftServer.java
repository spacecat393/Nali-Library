//package com.nali.mixin;
//
//import com.nali.system.ServerLoader;
//import net.minecraft.server.MinecraftServer;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(MinecraftServer.class)
//public class MixinMinecraftServer
//{
//	@Inject(method = "tick", at = @At("TAIL"))
//	private void nali_runGameLoop(CallbackInfo ci)
//	{
////		for (Runnable runnable : ServerLoader.RUNNABLE_LIST)
//		for (int i = 0; i < ServerLoader.RUNNABLE_LIST.size(); ++i)
//		{
//			Runnable runnable = ServerLoader.RUNNABLE_LIST.get(i);
//			runnable.run();
//		}
//		ServerLoader.RUNNABLE_LIST.clear();
//	}
//}
