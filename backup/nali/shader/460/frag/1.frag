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
//    vec4 own_texture_vec4 = texture(own_texture_sampler, fragment_texcoord);
//    if (own_texture_vec4.a == 0)
//    {
//        discard;
//    }

    float main_depth = texture(main_depth_sampler, fragment_texcoord).r;
    float own_depth = texture(own_depth_sampler, fragment_texcoord).r;
//    float mct_depth = texture(mct_depth_sampler, fragment_texcoord).r;
//    if (gl_FragCoord.z / 2.0F > own_depth)
//    if (0.0001 > own_depth)
//    if (0.1 > gl_FragCoord.z)
//    if ((gl_FragCoord.z * 1.9) < own_depth)
//    if ((gl_FragCoord.z * 1.98) < own_depth)
//    if ((gl_FragCoord.z * 1.98) > own_depth)
//    if (0 == own_depth)
//    if (gl_FragCoord.z > own_depth)
    if (main_depth > own_depth/** && main_depth > mct_depth && mct_depth > own_depth*/)
    {
//        vec4 own_texture_vec4 = texture(own_texture_sampler, fragment_texcoord);
        fragColor = texture(own_texture_sampler, fragment_texcoord);
//        if (own_texture_vec4.a < 1)
//        {
//            float mct_depth = texture(mct_depth_sampler, fragment_texcoord).r;
//            if (main_depth > mct_depth)
//            {
//                vec4 mct_texture_vec4 = texture(mct_texture_sampler, fragment_texcoord);
//                if (mct_texture_vec4.a < 1)
//                {
//                    if (mct_depth > own_depth)
//                    {
////                        fragColor = (mix(own_texture_vec4, mct_texture_vec4, own_texture_vec4.a) + mct_texture_vec4) / 2.0;
//                        fragColor = mix(own_texture_vec4, mct_texture_vec4, own_texture_vec4.a);
//                    }
//                    else if (own_depth > mct_depth)
//                    {
//                        fragColor = mix(mct_texture_vec4, own_texture_vec4, mct_texture_vec4.a);
////                        fragColor = (mix(mct_texture_vec4, own_texture_vec4, mct_texture_vec4.a) + mct_texture_vec4) / 2.0;
////                        fragColor = vec4((mct_texture_vec4.rgb + own_texture_vec4.rgb) / 2.0, own_texture_vec4.a);
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
////        vec4 main_texture_vec4 = texture(main_texture_sampler, fragment_texcoord);
////        own_texture_vec4 = mix(own_texture_vec4, main_texture_vec4, own_texture_vec4.a);
//        if (own_texture_vec4.a != 1)
//        {
//////        vec4 main_texture_vec4 = texture(main_texture_sampler, fragment_texcoord);
//////
//////        float r_s = own_texture_vec4.r;
//////        float g_s = own_texture_vec4.g;
//////        float b_s = own_texture_vec4.b;
//////        float a_s = own_texture_vec4.a;
//////
//////        float r_d = main_texture_vec4.r;
//////        float g_d = main_texture_vec4.g;
//////        float b_d = main_texture_vec4.b;
//////        float a_d = main_texture_vec4.a;
//////
//////        float r_f = a_s * r_s + (1 - a_s) * r_d;
//////        float g_f = a_s * g_s + (1 - a_s) * g_d;
//////        float b_f = a_s * b_s + (1 - a_s) * b_d;
//////        float a_f = a_s + (1 - a_s) * a_d;
//////
//////        own_texture_vec4 = vec4(r_f, g_f, b_f, a_f);
//            own_texture_vec4.rgb *= own_texture_vec4.a;
//        }
////        vec4 main_texture_vec4 = texture(main_texture_sampler, fragment_texcoord);
////        own_texture_vec4 = mix(own_texture_vec4, main_texture_vec4, own_texture_vec4.a);
    }
    else
    {
        fragColor = vec4(0, 0, 0, 0);
//        discard;
    }

//    fragColor = own_texture_vec4;
////    float z = gl_FragCoord.w * 500;
//    float z = gl_FragCoord.z / gl_FragCoord.w;
//    fragColor = vec4(z, z, z, 1.0);
////    fragColor = vec4(gl_FragCoord.x, gl_FragCoord.y, gl_FragCoord.z, 1.0);
}