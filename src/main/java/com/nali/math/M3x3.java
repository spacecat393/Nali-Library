// package com.nali.math;
// package com.nali.objects.math;

// public class M3x3
// {
//     public float[][] mat = new float[][]
//     {
//         { 1.0F, 0.0F, 0.0F},
//         { 0.0F, 1.0F, 0.0F},
//         { 0.0F, 0.0F, 1.0F},
//     };

//     public void rotateX(float x)
//     {
//         this.mat[1][1] = (float)Math.cos(x);
//         this.mat[1][2] = (float)Math.sin(x);
//         this.mat[2][1] = (float)-Math.sin(x);
//         this.mat[2][2] = (float)Math.cos(x);
//     }

//     public void rotateY(float y)
//     {
//         this.mat[0][0] = (float)Math.cos(y);
//         this.mat[0][2] = (float)-Math.sin(y);
//         this.mat[2][0] = (float)Math.sin(y);
//         this.mat[2][2] = (float)Math.cos(y);
//     }

//     public void rotateZ(float z)
//     {
//         this.mat[0][0] = (float)Math.cos(z);
//         this.mat[0][1] = (float)Math.sin(z);
//         this.mat[1][0] = (float)-Math.sin(z);
//         this.mat[1][1] = (float)Math.cos(z);
//     }

//     public void multiply(M3x3 m3x3)
//     {
//         M3x3 m3x30 = new M3x3();

//         for (int i = 0; i < 3; i++)
//         {
//             for (int j = 0; j < 3; j++)
//             {
//                 m3x30.mat[i][j] = this.mat[i][0] * m3x3.mat[0][j] + this.mat[i][1] * m3x3.mat[1][j] + this.mat[i][2] * m3x3.mat[2][j];
//             }
//         }

//         this.mat = m3x30.mat;
//     }

//     public void V3M4x4(V3 v3)
//     {
//         V3 v30 = new V3(0, 0, 0);

//         v30.x = v3.x * this.mat[0][0] + v3.y * this.mat[0][1] + v3.z * this.mat[0][2];
//         v30.y = v3.x * this.mat[1][0] + v3.y * this.mat[1][1] + v3.z * this.mat[1][2];
//         v30.z = v3.x * this.mat[2][0] + v3.y * this.mat[2][1] + v3.z * this.mat[2][2];

//         v3.x = v30.x;
//         v3.y = v30.y;
//         v3.z = v30.z;
//     }
// }
