package com.nali.mixin;

import net.minecraft.client.renderer.ActiveRenderInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.nio.FloatBuffer;

@Mixin(ActiveRenderInfo.class)
public interface MixinActiveRenderInfo
{
    @Accessor("MODELVIEW")
    @Final
    public static FloatBuffer MODELVIEW()
    {
        throw new AssertionError();
    };

    @Accessor("PROJECTION")
    @Final
    public static FloatBuffer PROJECTION()
    {
        throw new AssertionError();
    };
}
