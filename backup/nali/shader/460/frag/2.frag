#version 460 core

//uniform sampler2D main_texture_sampler;
uniform sampler2D main_depth_sampler;
uniform sampler2D own_texture_sampler;
uniform sampler2D own_depth_sampler;
//uniform sampler2D mct_texture_sampler;
//uniform sampler2D mct_depth_sampler;

in vec2 fragment_texcoord;

out vec4 fragColor;

void main()
{
//    vec4 main_texture_vec4 = texture(main_texture_sampler, fragment_texcoord);
//    vec4 own_texture_vec4 = texture(own_texture_sampler, fragment_texcoord);
//    if (/**length(own_texture_vec4.rgb) == 0 || */own_texture_vec4.a == 0)
//    {
//        discard;
//    }
//    main_texture_vec4 = mix(own_texture_vec4, main_texture_vec4, 0.5);
////    main_texture_vec4.a = 0.5;
//    fragColor = main_texture_vec4;


//    vec4 main_texture_vec4 = texture(main_texture_sampler, fragment_texcoord);
//    float Pi = 6.28318530718; // Pi*2
//
//    // GAUSSIAN BLUR SETTINGS {{{
//    float Directions = 1.0; // BLUR DIRECTIONS (Default 16.0 - More is better but slower)
//    float Quality = 1.0; // BLUR QUALITY (Default 4.0 - More is better but slower)
//    float Size = 800.0; // BLUR SIZE (Radius)
//    // GAUSSIAN BLUR SETTINGS }}}
//
//    vec2 Radius = Size / vec2(1920, 1080);// / iResolution.xy
//
//    // Normalized pixel coordinates (from 0 to 1)
//    vec2 uv = fragment_texcoord / vec2(1920, 1080);//fragCoord/iResolution.xy
//    // Pixel colour
//    vec4 own_texture_vec4 = texture(own_texture_sampler, uv);
//
//    // Blur calculations
//    for (float d=0.0; d<Pi; d+=Pi/Directions)
//    {
//        for (float i=1.0/Quality; i<=1.0; i+=1.0/Quality)
//        {
//            own_texture_vec4 += texture(own_texture_sampler, uv+vec2(cos(d),sin(d))*Radius*i);
//        }
//    }
//
//    // Output to screen
//    own_texture_vec4 /= Quality * Directions - 15.0;

//    vec4 own_texture_vec4 = texture(own_texture_sampler, fragment_texcoord);
    float own_depth = 1.0;
    vec4 own_texture_vec4 = vec4(0);
//    vec4 main_color_vec4 = vec4(0);
    float x = 1920.0/5.0;
    float y = 1080.0/5.0;
//    float ic = 1.0;
    for (int i = -2; i <= 2; ++i)
    {
//        float lc = 1.0;
        for (int l = -2; l <= 2; ++l)
        {
            vec2 uv_v2 = fragment_texcoord + vec2(i / x, l / y);
            if (uv_v2.x < 0)
            {
                continue;
//                discard;
//                uv_v2.x = 0;
            }
            if (uv_v2.y < 0)
            {
                continue;
//                discard;
//                uv_v2.y = 0;
            }
//            if (uv_v2.x > 0.9)
            if (uv_v2.x > 1.0)
            {
                continue;
//                discard;
//                uv_v2.x = 0.9;
            }
//            if (uv_v2.y > 0.9)
            if (uv_v2.y > 1.0)
            {
                continue;
//                discard;
//                uv_v2.y = 0.9;
            }
            own_texture_vec4 += texture(own_texture_sampler, uv_v2)/** * ic * lc*/;
//            vec4 v4 = texture(own_texture_sampler, uv_v2);
////            if (main_color_vec4.a == 0 && v4.a != 0)
////            {
////                main_color_vec4 = v4;
////            }
//            own_texture_vec4 += v4;
//            vec4 v4 = texture(own_texture_sampler, uv_v2);
//            own_texture_vec4 += v4/** * ic * lc*/;
//            if (v4.a == 0.0)
//            {
//                own_texture_vec4.a /= 2.0;
//            }
//            lc /= 1.01;
            float new_own_depth = texture(own_depth_sampler, uv_v2).r;
            if (own_depth > new_own_depth)
            {
                own_depth = new_own_depth;
            }
//            own_depth += new_own_depth;
        }
//        ic /= 1.01;
    }
//    float x1 = 1.0 / x;
//    float y1 = 1.0 / y;
//    float x_1 = -1.0 / x;
//    float y_1 = -1.0 / y;
//    vec2 uv_v20 = fragment_texcoord + vec2(x1, y1);
//    vec2 uv_v21 = fragment_texcoord + vec2(x_1, y1);
//    vec2 uv_v22 = fragment_texcoord + vec2(x1, y_1);
//    vec2 uv_v23 = fragment_texcoord + vec2(x_1, y_1);
//    vec4 own_texture_vec4 = texture(own_texture_sampler, uv_v20)
//    + texture(own_texture_sampler, uv_v21)
//    + texture(own_texture_sampler, uv_v22)
//    + texture(own_texture_sampler, uv_v23);
//own_texture_vec4 /= 4.0*4.0;
    own_texture_vec4 /= 5.0*5.0;
//    own_depth /= 5.0*5.0;
//    own_texture_vec4.rgb *= 2.0;
//    own_texture_vec4 /= 5.0*5.0;
//    own_texture_vec4.a = 0.1;
//    if (/**own_texture_vec4.a > 0*/own_texture_vec4.a == 0/** || own_texture_vec4.a == 1 || own_texture_vec4.a < 1.0*//** || length(own_texture_vec4.rgb) == 0*/)
//    {
////        fragColor = main_texture_vec4;
//        discard;
//    }
//    own_texture_vec4.a = 0.5;
//    float own_depth = 1.0;
//    for (int i = -2; i <= 2; ++i)
//    {
//        for (int l = -2; l <= 2; ++l)
//        {
//            vec2 uv_v2 = fragment_texcoord + vec2(i / x, l / y);
//            if (uv_v2.x < 0)
//            {
//                continue;
////                discard;
////                uv_v2.x = 0;
//            }
//            if (uv_v2.y < 0)
//            {
//                continue;
////                discard;
////                uv_v2.y = 0;
//            }
////            if (uv_v2.x > 0.9)
//            if (uv_v2.x > 1.0)
//            {
//                continue;
////                discard;
////                uv_v2.x = 0.9;
//            }
////            if (uv_v2.y > 0.9)
//            if (uv_v2.y > 1.0)
//            {
//                continue;
////                discard;
////                uv_v2.y = 0.9;
//            }
//            float new_own_depth = texture(own_depth_sampler, uv_v2).r;
//            if (own_depth > new_own_depth)
//            {
//                own_depth = new_own_depth;
//            }
//        }
//    }
////    own_texture_vec4 = vec4(0.0);
//    float own_depth = texture(own_depth_sampler, uv_v20).r;
//    float own_depth_1 = texture(own_depth_sampler, uv_v21).r;
//    float own_depth_2 = texture(own_depth_sampler, uv_v22).r;
//    float own_depth_3 = texture(own_depth_sampler, uv_v23).r;
////    own_depth /= 2.0;
//    if (own_depth > own_depth_1)
//    {
//        own_depth = own_depth_1;
//    }
//    if (own_depth > own_depth_2)
//    {
//        own_depth = own_depth_2;
//    }
//    if (own_depth > own_depth_3)
//    {
//        own_depth = own_depth_3;
//    }

//    own_texture_vec4 /= 25.0;
//    own_depth /= 25.0;
//    for (int i = -2; i <= 2; ++i)
//    {
//        for (int j = -2; j <= 2; ++j)
//        {
//            vec2 uv_v2 = fragment_texcoord + vec2(i, j) / vec2(600, 400);
//            own_texture_vec4 += texture(own_texture_sampler, uv_v2);
//            own_depth += texture(own_depth_sampler, uv_v2).r;
//        }
//    }
//
//    own_texture_vec4 /= 25.0;
//    own_depth /= 25.0;

//    if (own_texture_vec4.a == 0)
//    {
//        discard;
//    }

    float main_depth = texture(main_depth_sampler, fragment_texcoord).r;
//    float own_depth = texture(own_depth_sampler, fragment_texcoord).r;
    if (main_depth > own_depth)
    {
        fragColor = own_texture_vec4;
//        if (own_texture_vec4.a < 1)
//        {
//            float mct_depth = texture(mct_depth_sampler, fragment_texcoord).r;
//            if (main_depth > mct_depth)
//            {
//                vec4 mct_texture_vec4 = texture(mct_texture_sampler, fragment_texcoord);
//                if (mct_texture_vec4.a < 1)
//                {
//                    //                vec4 main_texture_vec4 = texture(main_texture_sampler, fragment_texcoord);
//                    if (mct_depth > own_depth)
//                    {
//                        fragColor = mix(own_texture_vec4, mct_texture_vec4, own_texture_vec4.a);
//                    }
//                    else if (own_depth > mct_depth)
//                    {
//                        fragColor = mix(mct_texture_vec4, own_texture_vec4, mct_texture_vec4.a);
//                    }
//                    else
//                    {
//                        fragColor = vec4(0, 0, 0, 0);
//                    }
//                }
//                else
//                {
//                    fragColor = own_texture_vec4;
//                }
//            }
//            else
//            {
//                fragColor = own_texture_vec4;
//            }
//        }
//        else
//        {
//            fragColor = own_texture_vec4;
//        }
//        vec4 main_texture_vec4 = texture(main_texture_sampler, fragment_texcoord);
//        own_texture_vec4.r = main_texture_vec4.r;
//        own_texture_vec4.g = main_texture_vec4.g;
//        own_texture_vec4.b = main_texture_vec4.b;
//        vec4 mix_texture_vec4 = mix(own_texture_vec4, main_texture_vec4, own_texture_vec4.a);
//        main_texture_vec4.rgb *= length(own_texture_vec4.rgb);
//        main_texture_vec4.r += own_texture_vec4.a / (1.0 + own_texture_vec4.r);
//        main_texture_vec4.g += own_texture_vec4.a / (1.0 + own_texture_vec4.g);
//        main_texture_vec4.b += own_texture_vec4.a / (1.0 + own_texture_vec4.b);

//        main_texture_vec4.r += own_texture_vec4.a * (1.0 - own_texture_vec4.r);
//        main_texture_vec4.g += own_texture_vec4.a * (1.0 - own_texture_vec4.g);
//        main_texture_vec4.b += own_texture_vec4.a * (1.0 - own_texture_vec4.b);
//        main_texture_vec4.rgb += own_texture_vec4.rgb;
//        main_texture_vec4.rgb /= 2.0;

//        main_texture_vec4.rgb += own_texture_vec4.rgb;
//        main_texture_vec4.rgb += own_texture_vec4.a;

//        main_texture_vec4.rgb += vec3(own_texture_vec4.r, 0.0, 0.0);
//        main_texture_vec4.rgb += vec3(0.0, own_texture_vec4.g, 0.0);
//        main_texture_vec4.rgb += vec3(own_texture_vec4.r, own_texture_vec4.g, own_texture_vec4.b);
//        main_texture_vec4.rgb += own_texture_vec4.rgb;
//        main_texture_vec4.rgb /= 2.0;
//        main_texture_vec4.rgb *= texture(main_texture_sampler, fragment_texcoord).rgb * own_texture_vec4.a;
//        float alpha = 1.0 - own_texture_vec4.a;
////        own_texture_vec4.rgb = own_texture_vec4.rgb / alpha + main_texture_vec4.rgb * alpha;
//        own_texture_vec4.rgb = own_texture_vec4.rgb + main_texture_vec4.rgb * alpha;
//        own_texture_vec4.a = 1;
//        own_texture_vec4.rgb += vec3(1.0, 0.0, 0.0);
//        own_texture_vec4 = mix_texture_vec4;

//        own_texture_vec4.rgb = main_texture_vec4.rgb + (own_texture_vec4.rgb / own_texture_vec4.a);
//        own_texture_vec4.rgb = main_texture_vec4.rgb + own_texture_vec4.a;
//        own_texture_vec4.rgb = main_texture_vec4.rgb + own_texture_vec4.rgb * own_texture_vec4.a;

//        own_texture_vec4.rgb = main_texture_vec4.rgb + main_color_vec4.rgb * own_texture_vec4.a;
//        own_texture_vec4.rgb = main_texture_vec4.rgb + own_texture_vec4.rgb * own_texture_vec4.a;
//        own_texture_vec4.rgb = own_texture_vec4.rgb * own_texture_vec4.a / 2.0;
//        own_texture_vec4.a = 1.0;
//        own_texture_vec4.rgb = main_color_vec4.rgb * own_texture_vec4.a;

//        own_texture_vec4.r = main_texture_vec4.r + own_texture_vec4.r * own_texture_vec4.a;
//        own_texture_vec4.g = main_texture_vec4.g + own_texture_vec4.g * own_texture_vec4.a;
//        own_texture_vec4.b = main_texture_vec4.b + own_texture_vec4.b * own_texture_vec4.a;

//        own_texture_vec4 *= 2;

//        own_texture_vec4.a = 1.0;
//        if (own_texture_vec4.a != 1)
//        {
//////////    ////        vec4 own_texture_vec4 = vec4(0.0);
//////////    ////        for (int i = -2; i <= 2; ++i)
//////////    ////        {
//////////    ////            for (int j = -2; j <= 2; ++j)
//////////    ////            {
//////////    ////                own_texture_vec4 += texture(main_texture_sampler, fragment_texcoord + vec2(i, j) / vec2(600, 400));
//////////    ////            }
//////////    ////        }
//////////    ////
//////////    ////        own_texture_vec4 /= 25.0;
//////////    ////            main_texture_vec4 = vec4(main_texture_vec4.xyz, 1);
//////////    //
//            vec4 main_texture_vec4 = texture(main_texture_sampler, fragment_texcoord);
////////////            float r_s = own_texture_vec4.r;
////////////            float g_s = own_texture_vec4.g;
////////////            float b_s = own_texture_vec4.b;
////////////            float a_s = own_texture_vec4.a;
////////////
////////////            float r_d = main_texture_vec4.r;
////////////            float g_d = main_texture_vec4.g;
////////////            float b_d = main_texture_vec4.b;
////////////            float a_d = main_texture_vec4.a;
////////////
////////////            float r_f = a_s * r_s + (1 - a_s) * r_d;
////////////            float g_f = a_s * g_s + (1 - a_s) * g_d;
////////////            float b_f = a_s * b_s + (1 - a_s) * b_d;
////////////            float a_f = a_s + (1 - a_s) * a_d;
////////////
////////////            own_texture_vec4 = vec4(1.0 - r_f, 1.0 - g_f, 1.0 - b_f, a_f);
////////////            own_texture_vec4 = mix(main_texture_vec4, own_texture_vec4, 1.0);
////////////            own_texture_vec4 = mix(own_texture_vec4, main_texture_vec4, 0.5);
//////////            vec4 new_color = mix(vec4(0.0, 0.0, 0.0, 1.0), main_texture_vec4, 1.0);
//////////            own_texture_vec4 = mix(own_texture_vec4, new_color, own_texture_vec4.a);
////////////            float length_rgb = length(own_texture_vec4.rgb);
////////////            own_texture_vec4 = mix(own_texture_vec4, main_texture_vec4, length_rgb);
////////////            own_texture_vec4 = vec4(length_rgb, length_rgb, length_rgb, 1.0);
////////////            if (length_rgb < 0.5)
////////////            if (length(main_texture_vec4.rgb) < 0.5)
////////////            {
////////////                own_texture_vec4 = mix(main_texture_vec4, own_texture_vec4, own_texture_vec4.a);
//////////////                own_texture_vec4.rgb *= 2.0 - length_rgb;
////////////////                own_texture_vec4.rgb /= 1.0 - length_rgb;
////////////////                float light = 1.0 - length_rgb;
////////////////                own_texture_vec4.rgb += vec3(light, light, light);
////////////            }
////////////            else
////////////            {
////////////                own_texture_vec4 = mix(own_texture_vec4, main_texture_vec4, own_texture_vec4.a);
////////////            }
////////            own_texture_vec4 = mix(main_texture_vec4, own_texture_vec4, own_texture_vec4.a);
//////////            vec4 new_own_texture_vec4 = mix(main_texture_vec4, own_texture_vec4, own_texture_vec4.a);
////////////            own_texture_vec4 = mix(own_texture_vec4, main_texture_vec4, main_texture_vec4.a);
////////////            own_texture_vec4 = mix(own_texture_vec4, main_texture_vec4, own_texture_vec4.a);
//////////            float length_rgb = length(new_own_texture_vec4.rgb);
//////////            if (length_rgb < 0.5)
//////////            {
//////////                new_own_texture_vec4 += own_texture_vec4;
//////////            }
//////////
//////////            own_texture_vec4 = new_own_texture_vec4;
//////////            own_texture_vec4.rgb += own_texture_vec4.rgb * main_texture_vec4.rgb;
//////            own_texture_vec4 *= 50.0;
//////            own_texture_vec4 += main_texture_vec4 * 50.0;
//////            own_texture_vec4 /= 1.0+50.0+50.0;
//////            own_texture_vec4.a = 1.0;
////////            own_texture_vec4 /= 2.0;
//            own_texture_vec4 = mix(own_texture_vec4, main_texture_vec4, own_texture_vec4.a);
//            own_texture_vec4.a = 0.1;
////            own_texture_vec4.r = main_texture_vec4.r;
//////            own_texture_vec4.g = main_texture_vec4.g;
//////            own_texture_vec4.b = main_texture_vec4.b;
////            own_texture_vec4 += main_texture_vec4;
////            own_texture_vec4 /= 2.0;
////            own_texture_vec4.rgb *= main_texture_vec4.rgb / 2.0;
////        }
////////        fragColor = own_texture_vec4;
//        if (own_texture_vec4.a < 0.75)
//        {
//            discard;
//        }
    }
    else
    {
//        fragColor = main_texture_vec4;
//        discard;
        fragColor = vec4(0, 0, 0, 0);
    }
//    fragColor = own_texture_vec4;
}