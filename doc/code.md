# Code

<span style="font-size: large; ">

>com/nali/list

```java
	//*da
		//BothDa_modid_name
			//implements IBothDaO / IBothDaS
			public static BothDa_modid_name IDA;
	//*data
		//modid_Data
			public static int
				TEXTURE_STEP,
				SHADER_STEP,
				MODEL_STEP,
				ANIMATION_STEP,
				FRAME_STEP,
				SOUND_STEP,
				MAX_BONE = MAX_BONE_USED * 16;
			public static byte ORDINAL = 2;//0nali 1small 2final_used
	//*render
		//Render_modid_name
			//implements RenderO / RenderS
			@Override
			public int getTextureID(MemoG rg)
			{
				return modid_Data.TEXTURE_STEP + super.getTextureID(rg);
			}

			@Override
			public int getShaderID(MemoG rg)
			{
				return modid_Data.SHADER_STEP + super.getShaderID(rg);
			}
```
## Data
>O-Object S-Skinning

	Object has only model
	Skinning has animation
>Static Field

	Should always include it because need for auto read / auto register
	Follow on Updated
>[Build](../README.md)

</span>