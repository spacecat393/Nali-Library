# Resource

<span style="font-size: large; ">

- Create Raw Data / Library [SmallPointer](https://github.com/spacecat393/SmallPointer)

>run/nali/modid

	*model
	Name(String)
	Raw Data

	*shader
		**gl_part - Name(String)
		attrib.dat
		uniform.dat

		**frag
		0.frag 1.frag ...(Signed Int)

		**vert
			***o
			0.vert 1.vert ...(Signed Int)

			***s
			00.vert 01.vert 02.vert 10.vert 11.vert 12.vert ...(Signed Int)

	*texture
	0.png 1.png ...(Signed Int)
	ImageIO.read(file)

	*sound
	0.ogg 1.mp3 ...(Signed Int)
	FFmpeg.getSounds(file)
>run/nali/modid/model.dat

	model_Name(String) texture(Signed Int) modid(String) shader_line(Signed Int) texture_state(2Bit/Byte) culling(1Bit/Byte) transparent(1Bit/Byte) glow(1Bit/Byte)
>run/nali/modid/shader.dat

	modid(String) gl_part(String) modid(String) o/s(String)vert_id(Signed Int) modid(String) frag_id(Signed Int) ==> shader_line(Signed Int)
>run/nali/modid/texture.dat

	texture(Signed Int) mipmap(1Bit/Byte)

>run/nali/modid/frame.dat

	model-animation_Name(String)
## Data
>texture_state(2Bit/Byte)

	0 GL_NEAREST
	1 GL_LINEAR
	2 GL_LINEAR_MIPMAP_LINEAR
>Raw Data

	*model-animation_Name(String)
	Has only one
		**bone
			***0.bin 1.bin ...(Unsigned Byte ==> Signed Byte[java] -> Signed Short[java]) ==> max_bone
		bindpose.bin(M4x4*max_bone Float)
		transform.bin(M4x4*max_bone*max_key Float)

	*model_Name(String)
	Can has more than one
		index.bin i(Signed Int)
		joint.bin j*i(Unsigned Byte ==> Signed Byte[java] -> Unsigned Byte[buffer])
		normal.bin 3*i(Float)
		texcoord.bin 2*i(Float)
		vertex.bin 3*i(Float)
		weight.bin j*i(Float)

	*model-animation_Name_Doc(String)
		bone.dat
		joint.dat
>j*i

	j or max_joint is around 1-4 when bone has children can be found in model-animation_Name_Doc
	i is max_bytes from index.bin / 4
## Extra
>run/nali/modid/shader/gl_part/attrib.dat

	Object
		[line]attribute_name(String) size(Byte)
		vertex 3
		texcoord 2
		normal 3

	Skinning
		[line]attribute_name(String) size(Byte)
		...
		[line]attribute_name(String)
		joint
		weight
>run/nali/modid/shader/gl_part/uniform.dat

	[line]uniform_name(String)
	... ==> uniform_id
>run/nali/nali

	*c
	libNaliAL.so libNaliGL.so(Library)

	*tmp
	config bgm ...
	Created by program
>[Build](../README.md)

</span>