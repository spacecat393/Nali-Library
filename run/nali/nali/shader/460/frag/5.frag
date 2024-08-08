#version 460 core

uniform sampler2D main_depth_sampler;
uniform sampler2D own_depth_sampler;

in vec2 fragment_texcoord;

out float fragColor;

void main()
{
    vec4 own_texture_vec4 = vec4(0);
    float x = 1920.0/5.0;
    float y = 1080.0/5.0;

    float own_depth = 1.0;
    for (int i = -2; i <= 2; ++i)
    {
        for (int l = -2; l <= 2; ++l)
        {
            vec2 uv_v2 = fragment_texcoord + vec2(i / x, l / y);
            if (uv_v2.x < 0)
            {
                continue;
            }
            if (uv_v2.y < 0)
            {
                continue;
            }
            if (uv_v2.x > 1.0)
            {
                continue;
            }
            if (uv_v2.y > 1.0)
            {
                continue;
            }
            float new_own_depth = texture(own_depth_sampler, uv_v2).r;
            if (own_depth > new_own_depth)
            {
                own_depth = new_own_depth;
            }
        }
    }

    fragColor = min(texture(main_depth_sampler, fragment_texcoord).r, own_depth);
//    fragColor = own_depth;
}