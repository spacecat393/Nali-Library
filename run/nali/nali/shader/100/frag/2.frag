#version 100
precision highp float;

uniform sampler2D texture_sampler;

varying vec2 fragment_texcoord;

void main()
{
    float x = 1920.0/4.0;
    float y = 1080.0/4.0;
    vec4 texture_vec4 = vec4(0.0);
    for (int i = -2; i <= 2; ++i)
    {
        for (int l = -2; l <= 2; ++l)
        {
            texture_vec4 += texture2D(texture_sampler, fragment_texcoord + vec2(float(i) / x, float(l) / y));
        }
    }
    texture_vec4 /= 5.0*5.0;
    gl_FragColor = texture_vec4;
}
