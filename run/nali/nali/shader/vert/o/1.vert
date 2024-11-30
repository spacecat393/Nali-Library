#version 100
precision highp float;

attribute vec2 vertex;

uniform vec2 Pos;

void main()
{
	gl_Position = vec4(vertex + Pos, 0.0, 1.0);
}