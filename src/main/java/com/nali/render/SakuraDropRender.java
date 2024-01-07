package com.nali.render;

import com.nali.data.BothData;
import com.nali.system.DataLoader;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;
import java.util.WeakHashMap;

import static com.nali.Nali.RANDOM;
import static com.nali.system.Timing.TD;

@SideOnly(Side.CLIENT)
public class SakuraDropRender extends ObjectRender
{
    public static Map<Integer, SakuraDropRender> SAKURADROPGUIDATA_MAP = new WeakHashMap<>();
    public static int INDEX;
    public int id;

    public long time, max_time;
    public float mrx, mry, mrz;
    public float ms;
    public float fy;

    public SakuraDropRender(BothData bothdata, DataLoader dataloader)
    {
        super(bothdata, dataloader);
        this.time = Minecraft.getSystemTime();//System.currentTimeMillis();
        this.ms = 1.0F / (RANDOM.nextInt(3) + 8);
        this.mrx = (45.0F / (RANDOM.nextInt(3) + 5)) - (45.0F / (RANDOM.nextInt(3) + 5));
        this.mry = (45.0F / (RANDOM.nextInt(3) + 5)) - (45.0F / (RANDOM.nextInt(3) + 5));
        this.mrz = (45.0F / (RANDOM.nextInt(3) + 5)) - (45.0F / (RANDOM.nextInt(3) + 5));
        this.max_time = 1000 + RANDOM.nextInt(2500);
        this.fy = -3.0F / (RANDOM.nextInt(3) + 1);
        float s = -25.0F / (RANDOM.nextInt(3) + 1);
        this.sx = s;
        this.sy = s;
        this.sz = s;

        ++INDEX;

        if (INDEX == Integer.MAX_VALUE)
        {
            INDEX = 0;
        }

        this.id = INDEX;
        SakuraDropRender.SAKURADROPGUIDATA_MAP.put(this.id, this);
    }

    public void fastDraw(float new_r, float new_g, float new_b, float new_a)
    {
        this.fy += 0.1F * TD;
        this.y += this.fy * TD;
//        this.s -= this.ms * TD;
        this.rx -= this.mrx * TD;
        this.ry -= this.mry * TD;
        this.rz -= this.mrz * TD;

        if (Minecraft.getSystemTime()/*System.currentTimeMillis()*/ - this.time >= this.max_time)
        {
            SakuraDropRender.SAKURADROPGUIDATA_MAP.remove(this.id);
        }

        this.objectscreendraw.renderScreen(new_r, new_g, new_b, new_a);
    }

    @Override
    public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
    }

    @Override
    public void setLightMapUniform(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
    }
}
