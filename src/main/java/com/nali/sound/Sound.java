package com.nali.sound;

import com.nali.NaliAL;
import com.nali.NaliConfig;
import com.nali.system.ClientLoader;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.openal.AL10;

import java.util.LinkedHashSet;
import java.util.Set;

@SideOnly(Side.CLIENT)
public abstract class Sound
{
	public static Set<Sound> SOUND_SET = new LinkedHashSet();
	public int source = -1, id = -1;
	public boolean pause;

	public void play(int id)
	{
		if (id > -1 && id < ClientLoader.SOUND_INTEGER_LIST.size())
		{
			if (this.id != id || this.source == -1)
			{
				if (this.source != -1)
				{
					NaliAL.alDeleteSources(this.source);
				}

				this.gen(id);

//			Nali.warn("S " + NaliAL.alGetSourcei(this.source, AL10.AL_SOURCE_STATE));
				NaliAL.alSourcePlay(this.source);
//			Nali.warn("S " + NaliAL.alGetSourcei(this.source, AL10.AL_SOURCE_STATE));
			}
		}
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
					NaliAL.alSourcePause(this.source);
				}
				else
				{
					NaliAL.alSourcePlay(this.source);
				}
			}
			else if (!this.pause)
			{
				if (NaliAL.alGetSourcei(this.source, AL10.AL_SOURCE_STATE) == AL10.AL_STOPPED)
				{
					NaliAL.alDeleteSources(this.source);
					this.source = -1;
					SOUND_SET.remove(this);
				}
			}
			this.pause = pause;
		}
	}

	public void gen(int id)
	{
//		if (id > -1 && id < ClientLoader.SOUND_INTEGER_LIST.size())
//		{
		this.id = id;
		this.source = NaliAL.alGenSources();
		NaliAL.alSourcei(this.source, AL10.AL_BUFFER, ClientLoader.SOUND_INTEGER_LIST.get(id));
//			NaliAL.alSourcei(this.source, AL10.AL_LOOPING, AL10.AL_FALSE);
//			NaliAL.alSourcef(this.source, AL10.AL_GAIN, NaliConfig.AL_GAIN);
//			NaliAL.alSourcef(this.source, AL10.AL_PITCH, NaliConfig.AL_PITCH);
//			NaliAL.alSource3f(this.source, AL10.AL_POSITION, x, y, z);
		SOUND_SET.add(this);
//		}
	}

	public void set(float x, float y, float z/*, float ry, float rp*/)
	{
		if (this.source != -1)
		{
			NaliAL.alSourcef(this.source, AL10.AL_GAIN, NaliConfig.AL_GAIN);
			NaliAL.alSourcef(this.source, AL10.AL_PITCH, NaliConfig.AL_PITCH);
			NaliAL.alSource3f(this.source, AL10.AL_POSITION, x, y, z);

//			AL10.alSource3f(this.source, AL10.AL_DIRECTION, (float)(Math.cos(ry) * Math.cos(rp)), (float)Math.sin(rp), (float)(Math.sin(ry) * Math.cos(rp)));
//			Nali.warn("X " + x);
//			Nali.warn("Y " + y);
//			Nali.warn("Z " + z);
//			NaliAL.alSource3f(this.source, AL10.AL_POSITION, (float)(entityplayersp.posX - x), (float)(entityplayersp.posY - y), (float)(entityplayersp.posZ - z));
//			NaliAL.alSource3f(this.source, AL10.AL_POSITION, (float)(x - entityplayersp.posX), (float)(y - entityplayersp.posY), (float)(z - entityplayersp.posZ));
		}
	}

	public abstract int getSoundBuffer(byte id);
}
