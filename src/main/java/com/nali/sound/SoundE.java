package com.nali.sound;

import com.nali.NaliAL;
import com.nali.NaliConfig;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.openal.AL10;

@SideOnly(Side.CLIENT)
public class SoundE extends Sound
{
	@Override
	public void set()
	{
		NaliAL.alSourcef(source, AL10.AL_GAIN, NaliConfig.FLOAT_ARRAY[2] * NaliConfig.FLOAT_ARRAY[0]);
		NaliAL.alSourcef(source, AL10.AL_PITCH, NaliConfig.FLOAT_ARRAY[7] * NaliConfig.FLOAT_ARRAY[0]);
	}
}
