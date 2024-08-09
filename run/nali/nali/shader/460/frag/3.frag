#version 460 core

uniform sampler2D texture_sampler;

in vec2 fragment_texcoord;

out vec4 fragColor;

void main()
{
    vec4 texture_vec4 = texture(texture_sampler, fragment_texcoord);
    if (texture_vec4.a == 0)
    {
        discard;
    }
    fragColor = texture_vec4;
}