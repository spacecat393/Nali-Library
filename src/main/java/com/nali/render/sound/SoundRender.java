package com.nali.render.sound;

import com.nali.NaliConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.openal.AL10;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.nali.Nali.I;

@SideOnly(Side.CLIENT)
public class SoundRender
{
    public static Set<SoundRender> SOUNDRENDER_SET = new LinkedHashSet();
//    public DataLoader dataloader;
    public int source = -1, id = -1;
    public boolean pause;

//    public static SoundRender getSoundRender(/*DataLoader dataloader, */int id)
//    {
////        if (dataloader.openalmemory == null)
//        if (id == -1)
//        {
//            return new NoSoundRender();
//        }
//        else
//        {
//            SoundRender soundrender = new SoundRender();
////            soundrender.dataloader = dataloader;
////        this.gen(id);
//            return soundrender;
//        }
//    }

//    public void rePlay(int id)
//    {
//        if (id != -1)
//        {
//            if (this.id == id && this.source != -1)
//            {
//                AL10.alSourceRewind(this.source);
//
//                AL10.alSourcePlay(this.source);
//            }
//            else
//            {
//                AL10.alDeleteSources(this.source);
//
//                this.gen(id);
//
//                AL10.alSourcePlay(this.source);
//            }
//        }
//    }

    public void play(int id)
    {
//        if (id != -1)
//        {
        if (this.id != id || this.source == -1)
        {
//                Minecraft.getMinecraft().addScheduledTask(() ->
//                {
            if (this.source != -1)
            {
                AL10.alDeleteSources(this.source);
//                    AL10.alDeleteBuffers(this.buffer);
            }

            this.gen(id);

            AL10.alSourcePlay(this.source);
//                });
        }
//        }
    }

    public void loop()
    {
        if (this.source != -1)
        {
            boolean pause = Minecraft.getMinecraft().isGamePaused();
            if (pause != this.pause)
            {
                if (pause)
                {
                    AL10.alSourcePause(this.source);
                }
                else
                {
                    AL10.alSourcePlay(this.source);
                }
            }
            else if (!this.pause)
            {
                if (AL10.alGetSourcei(this.source, AL10.AL_SOURCE_STATE) == AL10.AL_STOPPED)
                {
                    AL10.alDeleteSources(this.source);
                    this.source = -1;
                    SOUNDRENDER_SET.remove(this);
                }
            }
            this.pause = pause;
        }
    }

    public void gen(int id)
    {
//        Minecraft.getMinecraft().addScheduledTask(() ->
//        {
        this.id = id;
        this.source = AL10.alGenSources();
//        this.buffer = AL10.alGenBuffers();
//        AL10.alBufferData(this.buffer, AL10.AL_FORMAT_MONO16, this.dataloader.openalmemory.bytebuffer_array[id], this.dataloader.openalmemory.sample_rate_int_array[id]);
//        AL10.alSourcei(this.source, AL10.AL_BUFFER, this.dataloader.openalmemory.sound_buffer_int_array[id]);
        AL10.alSourcei(this.source, AL10.AL_BUFFER, I.clientloader.openalmemo_list.get(id).sound_buffer);
        SOUNDRENDER_SET.add(this);
//        });
    }

    public void set(float x, float y, float z)
    {
//        Minecraft.getMinecraft().addScheduledTask(() ->
//        {
        if (this.source != -1)
        {
            AL10.alSourcef(this.source, AL10.AL_GAIN, NaliConfig.SOUND.al_gain);
            AL10.alSourcef(this.source, AL10.AL_PITCH, NaliConfig.SOUND.al_pitch);
            AL10.alSource3f(this.source, AL10.AL_POSITION, x, y, z);
        }
//        });
    }
}
