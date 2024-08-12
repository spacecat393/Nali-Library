#version 460 core

uniform sampler2D main_depth_sampler;
uniform sampler2D own_texture_sampler;
uniform sampler2D own_depth_sampler;

in vec2 fragment_texcoord;

out vec4 fragColor;

void main()
{
//    if (own_texture_vec4.a == 0)
//    {
//        discard;
//    }

//    vec4 own_texture_vec4 = texture(own_texture_sampler, fragment_texcoord);
    float main_depth = texture(main_depth_sampler, fragment_texcoord).r;
    float own_depth = texture(own_depth_sampler, fragment_texcoord).r;
    if (main_depth > own_depth/** && own_depth > 0*//** && length(own_texture_vec4.rgb) != 0 && own_texture_vec4.a != 0*//** && own_depth != 1*//** && main_depth == 1*/)
    {
//        fragColor = own_texture_vec4;
        fragColor = texture(own_texture_sampler, fragment_texcoord);
    }
    else
    {
        fragColor = vec4(0, 0, 0, 0);
    }
//    else
//    {
//        discard;
//    }
}