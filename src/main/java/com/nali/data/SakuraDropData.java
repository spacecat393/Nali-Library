package com.nali.data;

import com.nali.system.DataLoader;
import net.minecraft.client.Minecraft;

import java.util.Map;
import java.util.WeakHashMap;

import static com.nali.Nali.RANDOM;

public class SakuraDropData extends ObjectData
{
    public static Map<Integer, SakuraDropData> SAKURADROPGUIDATA_MAP = new WeakHashMap<>();
    public static int INDEX;
    public int id;

    public long time;
    public long max_time;
    public float mrx;
    public float mry;
    public float mrz;
    public float ms;
    public float fy;

    public SakuraDropData(BothData bothdata, DataLoader dataloader)
    {
        super(bothdata, dataloader);
        this.time = Minecraft.getSystemTime();//System.currentTimeMillis();
        this.ms = 1.0F / (RANDOM.nextInt(3) + 8);
        this.mrx = (1.0F / (RANDOM.nextInt(3) + 5)) - (1.0F / (RANDOM.nextInt(3) + 5));
        this.mry = (1.0F / (RANDOM.nextInt(3) + 5)) - (1.0F / (RANDOM.nextInt(3) + 5));
        this.mrz = (1.0F / (RANDOM.nextInt(3) + 5)) - (1.0F / (RANDOM.nextInt(3) + 5));
        this.max_time = 1000 + RANDOM.nextInt(2500);
        this.fy = -3.0F / (RANDOM.nextInt(3) + 1);
        float s = 0.5F / (RANDOM.nextInt(3) + 1);
        this.screen_float_array[4] = -1.0F;
        this.screen_float_array[8] = s;
        this.screen_float_array[9] = s;
        this.screen_float_array[10] = s;

        ++INDEX;

        if (INDEX == Integer.MAX_VALUE)
        {
            INDEX = 0;
        }

        this.id = INDEX;
        SakuraDropData.SAKURADROPGUIDATA_MAP.put(this.id, this);
    }

    @Override
    public void render()
    {
        this.fy += 0.1F * DataLoader.TD;
        this.screen_float_array[3] += this.fy * DataLoader.TD;
//        this.s -= this.ms * DataLoader.TD;
        this.screen_float_array[5] -= this.mrx * DataLoader.TD;
        this.screen_float_array[6] -= this.mry * DataLoader.TD;
        this.screen_float_array[7] -= this.mrz * DataLoader.TD;

        if (Minecraft.getSystemTime()/*System.currentTimeMillis()*/ - this.time >= this.max_time)
        {
            SakuraDropData.SAKURADROPGUIDATA_MAP.remove(this.id);
        }

        super.render();
    }
}
