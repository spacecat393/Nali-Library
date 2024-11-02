#version 100
precision highp float;

attribute vec4 vertex;

uniform vec4 Pos;

varying vec2 fragment_texcoord;

//mat4 createOrthographicProjectionMatrix(float left, float right, float bottom, float top, float near, float far)
//{
//    mat4 ortho = mat4(1.0);
//    ortho[0][0] = 2.0 / (right - left);
//    ortho[1][1] = 2.0 / (top - bottom);
//    ortho[2][2] = -2.0 / (far - near);
//
//    ortho[0][3] = -(right + left) / (right - left);
//    ortho[1][3] = -(top + bottom) / (top - bottom);
//    ortho[2][3] = -(far + near) / (far - near);
////    ortho[3][0] = -(right + left) / (right - left);
////    ortho[3][1] = -(top + bottom) / (top - bottom);
////    ortho[3][2] = -(far + near) / (far - near);
//
////    ortho[3][3] = 1.0;
//    return ortho;
//}

//mat4 createScaleMatrix(float x, float y, float z)
//{
//    return mat4(
//        x, 0.0, 0.0, 0.0,
//        0.0, y, 0.0, 0.0,
//        0.0, 0.0, z, 0.0,
//        0.0, 0.0, 0.0, 1.0
//    );
//}

//void main()
//{
////    float left = -1.0, right = 1.0, bottom = -1.0, top = 1.0, near = 0.1, far = 1.0;
////    float left = -1.77777777778, right = 1.77777777778, bottom = -1.77777777778, top = 1.77777777778, near = -1.0, far = 1.0;//w/h
////    float left = -0.51, right = 0.51, bottom = -0.51, top = 0.51, near = -1.0, far = 1.0;
//    float left = -0.11, right = 0.11, bottom = -0.11, top = 0.11, near = -1.0, far = 1.0;
//
//    mat4 projectionMatrix = createOrthographicProjectionMatrix(left, right, bottom, top, near, far);
//    mat4 scaleMatrix = createScaleMatrix(0.3, 0.3, 0.3);
//
//    gl_Position = projectionMatrix * scaleMatrix * vec4(vertex.xy + Pos.xy, 0.0, 1.0);
//    fragment_texcoord = vertex.zw + Pos.zw;
//}

void main()
{
	//tab?
	gl_Position = vec4(vertex.xy/** + Pos.xy*/, 0.0, 1.0);
	fragment_texcoord = vertex.zw/** + Pos.zw*/;
}