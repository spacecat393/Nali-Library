#version 460 core

uniform sampler2D main_depth_sampler;
uniform sampler2D own_depth_sampler;

in vec2 fragment_texcoord;

out float fragColor;
//out vec4 fragColor;

void main()
{
    fragColor = min(texture(main_depth_sampler, fragment_texcoord).r, texture(own_depth_sampler, fragment_texcoord).r);
//    fragColor = texture(main_depth_sampler, fragment_texcoord);
//    fragColor = texture(own_depth_sampler, fragment_texcoord).r;
//    vec4 main_depth = texture(main_depth_sampler, fragment_texcoord);
//    vec4 own_depth = texture(own_depth_sampler, fragment_texcoord);
//    if (main_depth.r > own_depth.r)
//    {
//        main_depth = own_depth;
//    }
//    fragColor = main_depth;
//    fragColor = own_depth;
}