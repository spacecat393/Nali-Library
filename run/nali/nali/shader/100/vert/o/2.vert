#version 100
precision highp float;

attribute vec4 vertex;

varying vec2 fragment_texcoord;

void main()
{
    gl_Position = vec4(vertex.xy, 0.0, 1.0);
    fragment_texcoord = vertex.zw;
}
