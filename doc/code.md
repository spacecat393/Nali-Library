# Code

<span style="font-size: large; ">

>com/nali/list

```java
//*da
	//BothDaModidName
		//implements IBothDaO / IBothDaS
		public static BothDaModidName IDA;
//*data
	//ModidData
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
	//RenderModidName
		@SideOnly(Side.CLIENT)
		//implements RenderO / RenderS
		@Override
		public int getTextureID(MemoG rg)
		{
			return ModidData.TEXTURE_STEP + super.getTextureID(rg);
		}

		@Override
		public int getShaderID(MemoG rg)
		{
			return ModidData.SHADER_STEP + super.getShaderID(rg);
		}

		@Override
		public byte getExtraBit(MemoG rg)
		{
			return /*...*/;
		}
```
## Data
>O-Object S-Skinning

	Object has only model
	Skinning has animation
>Static Field

	Should always include it because need for auto read / auto register
	Follow on Updated
>getExtraBit

	1 Transparent
	2 Skinning
	4 Color/NoTexture
	8 EntityGlow
	16 TileEntity
>[Build](../README.md)

</span>