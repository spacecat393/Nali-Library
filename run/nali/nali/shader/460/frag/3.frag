#version 460 core

uniform sampler2D texture_sampler;

in vec2 fragment_texcoord;

out vec4 fragColor;

void main()
{
    fragColor = texture(texture_sampler, fragment_texcoord);
}