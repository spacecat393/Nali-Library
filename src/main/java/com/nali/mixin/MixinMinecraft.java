package com.nali.mixin;

import com.nali.Nali;
import com.nali.key.KeyHelper;
import com.nali.render.SoundRender;
import com.nali.system.Timing;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;

import static com.nali.key.KeyHelper.DETECT_METHOD_ARRAY;
import static com.nali.render.SoundRender.SOUNDRENDER_SET;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft
{
//    private byte state;

    @Inject(method = "runGameLoop", at = @At(value = "HEAD"))
    @Mutable
    private void nali_runGameLoop(CallbackInfo callbackinfo)
    {
        Timing.count();
        for (SoundRender soundrender : new HashSet<>(SOUNDRENDER_SET))
        {
            soundrender.loop();
        }
//    }
//
//    @Inject(method = "runTick", at = @At(value = "TAIL"))
//    @Mutable
//    private void nali_runTick(CallbackInfo ci)
//    {
////        boolean keyboard = (this.state & 1) == 0;
////        boolean mouse = (this.state & 2) == 0;
//
////        if (keyboard)
////        {
////            do
////            {
//        int k0 = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
////        int k0 = Keyboard.getEventKey();
//        if (k0 != 0)
//        {
////            KEY_SET.add(k0);
//            addKKey(k0);
//        }
////            }
////            while (Keyboard.next());
////        }
////        if (mouse)
////        {
////            do
////            {
//        int m0 = Mouse.getEventButton() - 100;
//        if (m0 != 0)
//        {
////            KEY_SET.add(m0);
//            addMKey(m0);
//        }
////            }
////            while (Mouse.next());
////        }

        for (int i = 0; i < DETECT_METHOD_ARRAY.length; ++i)
        {
            try
            {
                Method method = DETECT_METHOD_ARRAY[i];
                int key = KeyHelper.KEYBINDING_ARRAY[i].getKeyCodeDefault();
//                if (key != 0)
//                {
//                LOGGER.info("K " + key + " " + Arrays.toString(MKEY_SET.toArray()));
//                if (KKEY_SET.contains(key) || MKEY_SET.contains(key))
//                if (KEY_SET.contains(key))
                if ((key < 0 && Mouse.isButtonDown(key + 100)) || (key > 0 && Keyboard.isKeyDown(key)))
                {
                    method.invoke(null);
                }
//                    else
//                    {
//                        for (int k : KeyHelper.CURRENT_KEY_INTEGER_LIST)
//                        {
//                            if (k == key)
//                            {
//                                method.invoke(null);
//                                break;
//                            }
//                        }
//
//                        for (int m : KeyHelper.CURRENT_MOUSE_KEY_INTEGER_LIST)
//                        {
//                            if (m == key)
//                            {
//                                method.invoke(null);
//                                break;
//                            }
//                        }
//                    }
//                }
            }
            catch (IllegalAccessException | InvocationTargetException e)
            {
                Nali.error(e);
            }
        }

//        KEY_SET.clear();
//        if (!keyboard)
//        {
//        KKEY_SET.clear();
//        }
//        if (!mouse)
//        {
//        MKEY_SET.clear();
//        }
//        CURRENT_KEY_INTEGER_LIST.clear();
//        CURRENT_MOUSE_KEY_INTEGER_LIST.clear();
//        this.state = 0;
    }

//    @Inject(method = "runTickKeyboard", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
//    @Mutable
//    private void nali_runTickKeyboard(CallbackInfo ci, int i)
//    {
////        this.state |= 1;
////        int k = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
////        int m = Mouse.getEventButton() - 100;
//        if (i != 0)
//        {
//            KEY_SET.add(i);
////            KKEY_SET.add(i);
////            Nali.LOGGER.info("K " + i);
////            CURRENT_KEY_INTEGER_LIST.add(i);
//        }
//           /* for (Method method : WORLD_METHOD_LIST)
//            {
//                try
//                {
//                    method.invoke(null, i);
//                }
//                catch (IllegalAccessException | InvocationTargetException e)
//                {
//                    Nali.error(e);
//                }
//            }*/
//    }

//    @Inject(method = "dispatchKeypresses", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isRepeatEvent()Z", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
//    @Mutable
//    private void dispatchKeypresses(CallbackInfo ci, int i)
//    {
//        if (i != 0)
//        {
//            CURRENT_KEY_INTEGER_LIST.add(i);
//            CURRENT_MOUSE_KEY_INTEGER_LIST.add(i - 100);
//        }
//    }

//    @Inject(method = "runTickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;setKeyBindState(IZ)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
//    @Mutable
//    private void nali_runTickMouse(CallbackInfo ci, int i)
//    {
////        this.state |= 2;
//        if (i != 0)
//        {
//            KEY_SET.add(i);
////            MKEY_SET.add(i);
////            Nali.LOGGER.info("M " + i);
////            CURRENT_MOUSE_KEY_INTEGER_LIST.add(i - 100);
////            for (Method method : GUI_METHOD_LIST)
////            {
////                try
////                {
////                    method.invoke(null, i);
////                }
////                catch (IllegalAccessException | InvocationTargetException e)
////                {
////                    Nali.error(e);
////                }
////            }
//        }
//    }
}
