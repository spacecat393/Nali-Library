#version 100
precision highp float;

uniform sampler2D texture_sampler;

//uniform vec4 Color;

varying vec2 fragment_texcoord;

void main()
{
//	vec4 texture_color = texture2D(texture_sampler, fragment_texcoord);
//	gl_FragColor = texture_color * Color;
	gl_FragColor = texture2D(texture_sampler, fragment_texcoord);
}
