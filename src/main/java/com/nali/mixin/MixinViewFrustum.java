//package com.nali.mixin;
//
//import net.minecraft.client.renderer.ViewFrustum;
//import net.minecraft.client.renderer.chunk.IRenderChunkFactory;
//import net.minecraft.client.renderer.chunk.RenderChunk;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.Redirect;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
////*extra
//@Mixin(ViewFrustum.class)
//public abstract class MixinViewFrustum
//{
//	@Shadow
//	public RenderChunk[] renderChunks;
//
//	@Shadow protected int countChunksX;
//
//	@Shadow protected int countChunksY;
//
//	@Shadow protected int countChunksZ;
//
//	@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ViewFrustum;setCountChunksXYZ(I)V"))
//	private void nali_createRenderChunks(ViewFrustum instance, int renderDistanceChunks)
//	{
////        WorldClient.refreshVisibleChunks
//		this.countChunksX = .CHUNK.count.x;
//		this.countChunksY = .CHUNK.count.y;
//		this.countChunksZ = .CHUNK.count.z;
//	}
//
//	@Inject(method = "createRenderChunks", at = @At(value = "HEAD"), cancellable = true)
//	private void nali_createRenderChunks(IRenderChunkFactory renderChunkFactory, CallbackInfo ci)
//	{
//	}
//}
