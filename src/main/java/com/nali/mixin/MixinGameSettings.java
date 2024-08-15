package com.nali.mixin;

import com.nali.key.MixKeyBinding;
import com.nali.system.Reflect;
import com.nali.system.StringReader;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.nali.Nali.error;
import static com.nali.key.KeyHelper.DETECT_METHOD_ARRAY;
import static com.nali.key.KeyHelper.KEYBINDING_ARRAY;


@Mixin(GameSettings.class)
public abstract class MixinGameSettings
{
//    @Shadow
//    public KeyBinding[] keyBindings;
    private List<Class> key_class_list;
//    private int[] int_array;
    private String[] string_array;
    private Object[] string_array_object;
    private byte load_time;

    @Inject(method = "loadOptions", at = @At(value = "HEAD"))
    private void nali_firstLoadOptions(CallbackInfo ci)
    {
        if (++this.load_time == 2)
        {
            this.key_class_list = Reflect.getClasses("com.nali.list.key");
//        this.key_class_list.sort(Comparator.comparing(Class::getName));
            int size = this.key_class_list.size();
            this.string_array = new String[size];
            this.string_array_object = new Object[size];

            for (int i = 0; i < size; ++i)
            {
                Class clasz = this.key_class_list.get(i);
                this.string_array_object[i] = StringReader.get(clasz);
                this.string_array[i] = "key_" + MixKeyBinding.getDescription((String[])this.string_array_object[i]);
            }
        }
    }

    @Inject(method = "loadOptions", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/GameSettings;dataFix(Lnet/minecraft/nbt/NBTTagCompound;)Lnet/minecraft/nbt/NBTTagCompound;", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void nali_midLoadOptions(CallbackInfo ci, FileInputStream fileInputStream, List<String> list, NBTTagCompound nbttagcompound)
    {
        if (this.load_time == 2)
//        if (Minecraft.getMinecraft().gameSettings != null)
        {
            int size = this.key_class_list.size();
            DETECT_METHOD_ARRAY = new Method[size];
            KEYBINDING_ARRAY = new KeyBinding[size];
            Integer[] integer_array = new Integer[size];

//        Arrays.fill(int_array, -1);

            for (String s1 : nbttagcompound.getKeySet())
            {
                for (int i = 0; i < size; ++i)
                {
                    if (s1.equals(this.string_array[i]))
                    {
                        integer_array[i] = Integer.parseInt(nbttagcompound.getString(s1));
                    }
                }
            }

            for (int i = 0; i < size; ++i)
            {
                Class clasz = this.key_class_list.get(i);
                try
                {
                    KeyBinding keybinding = (KeyBinding)clasz.getConstructor(String[].class, Integer.class).newInstance(this.string_array_object[i], integer_array[i]);
                    clasz.getField("ID").set(null, i);
                    ClientRegistry.registerKeyBinding(keybinding);
//                this.keyBindings = ArrayUtils.add(this.keyBindings, keybinding);
                    DETECT_METHOD_ARRAY[i] = clasz.getMethod("detect");
                    KEYBINDING_ARRAY[i] = keybinding;
                }
                catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e)
                {
                    error(e);
                }
            }

            this.key_class_list = null;
            this.string_array = null;
            this.string_array_object = null;
        }
    }

    @Inject(method = "loadOptions", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;resetKeyBindingArrayAndHash()V", shift = At.Shift.AFTER))
    private void nali_lastLoadOptions(CallbackInfo ci)
    {
        if (this.load_time == 2)
        {
            for (KeyBinding keybinding : KEYBINDING_ARRAY)
            {
                keybinding.setKeyModifierAndCode(keybinding.getKeyModifierDefault(), 0);
            }
        }
    }

    @Inject(method = "saveOptions", at = @At(value = "HEAD"))
    private void nali_firstSaveOptions(CallbackInfo ci)
    {
//        int size = KEYBINDING_ARRAY.length;
//        this.int_array = new int[size];
        for (KeyBinding keybinding : KEYBINDING_ARRAY)
        {
            //            this.int_array[i] = keybinding.getKeyCode();
            keybinding.setKeyModifierAndCode(keybinding.getKeyModifierDefault(), keybinding.getKeyCodeDefault());
        }
    }

    @Inject(method = "saveOptions", at = @At(value = "TAIL"))
    private void nali_lastSaveOptions(CallbackInfo ci)
    {
//        int size = KEYBINDING_ARRAY.length;
//        this.int_array = new int[size];
        for (KeyBinding keybinding : KEYBINDING_ARRAY)
        {
            keybinding.setKeyModifierAndCode(keybinding.getKeyModifierDefault(), 0);
//            keybinding.setKeyModifierAndCode(keybinding.getKeyModifierDefault(), this.int_array[i]);
        }
//        this.int_array = null;
    }

    @Inject(method = "setOptionKeyBinding", at = @At(value = "HEAD"), cancellable = true)
    private void setOptionKeyBinding(KeyBinding key, int keyCode, CallbackInfo ci)
    {
        for (KeyBinding keybinding : KEYBINDING_ARRAY)
        {
            if (keybinding.equals(key))
            {
                key.setKeyCode(keyCode);
                ci.cancel();
            }
        }
    }
}
