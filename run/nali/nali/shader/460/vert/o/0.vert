#version 460 core

in vec4 vertex;

out vec2 fragment_texcoord;

void main()
{
    gl_Position = vec4(vertex.xy, 0.0, 1.0);
    fragment_texcoord = vertex.zw;
}
