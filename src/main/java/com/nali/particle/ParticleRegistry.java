package com.nali.particle;

import com.nali.mixin.IMixinEnumParticleTypes;
import com.nali.system.Reflect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;

import static com.nali.Nali.error;

public class ParticleRegistry
{
    public static EnumParticleTypes[] ENUMPARTICLETYPES_ARRAY;

    static
    {
        List<Class> particle_class_list = Reflect.getClasses("com.nali.list.particle");
        particle_class_list.sort(Comparator.comparing(Class::getName));
        List<Class> particlefactory_class_list = Reflect.getClasses("com.nali.list.particlefactory");
        particlefactory_class_list.sort(Comparator.comparing(Class::getName));

        Class<?>[] particles_params =
        {
            String.class, int.class, boolean.class
        };

        ENUMPARTICLETYPES_ARRAY = new EnumParticleTypes[particlefactory_class_list.size()];

        for (int i = 0; i < particlefactory_class_list.size(); ++i)
        {
            try
            {
                Class particlefactory_class = particlefactory_class_list.get(i);
                Class particle_class = particle_class_list.get(i);
                ENUMPARTICLETYPES_ARRAY[i] = EnumHelper.addEnum(EnumParticleTypes.class, (String)particlefactory_class.getField("enumName").get(null), particles_params, particlefactory_class.getField("particleNameIn").get(null), EnumParticleTypes.values().length, false);
                particle_class.getField("ENUMPARTICLETYPES").set(null, ENUMPARTICLETYPES_ARRAY[i]);
                IMixinEnumParticleTypes.PARTICLES().put(EnumParticleTypes.values().length, ENUMPARTICLETYPES_ARRAY[i]);
                IMixinEnumParticleTypes.BY_NAME().put(ENUMPARTICLETYPES_ARRAY[i].getParticleName(), ENUMPARTICLETYPES_ARRAY[i]);
            }
            catch (IllegalAccessException | NoSuchFieldException e)
            {
                error(e);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void register()
    {
        try
        {
            List<Class> particlefactory_class_list = Reflect.getClasses("com.nali.list.particlefactory");
            particlefactory_class_list.sort(Comparator.comparing(Class::getName));

            for (int i = 0; i < particlefactory_class_list.size(); ++i)
            {
                Class particlefactory_class = particlefactory_class_list.get(i);
                Minecraft.getMinecraft().effectRenderer.registerParticle(ENUMPARTICLETYPES_ARRAY[i].getParticleID(), (IParticleFactory)particlefactory_class.getConstructor().newInstance());
            }
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
        {
            error(e);
        }
    }
}
