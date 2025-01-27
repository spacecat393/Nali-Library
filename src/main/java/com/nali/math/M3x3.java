// package com.nali.math;
// package com.nali.objects.math;

// public class M3x3
// {
//	 public float[][] m4x4_float_array = new float[][]
//	 {
//		 { 1.0F, 0.0F, 0.0F},
//		 { 0.0F, 1.0F, 0.0F},
//		 { 0.0F, 0.0F, 1.0F},
//	 };

//	 public void rotateX(float x)
//	 {
//		 this.m4x4_float_array[1][1] = (float)Math.cos(x);
//		 this.m4x4_float_array[1][2] = (float)Math.sin(x);
//		 this.m4x4_float_array[2][1] = (float)-Math.sin(x);
//		 this.m4x4_float_array[2][2] = (float)Math.cos(x);
//	 }

//	 public void rotateY(float y)
//	 {
//		 this.m4x4_float_array[0][0] = (float)Math.cos(y);
//		 this.m4x4_float_array[0][2] = (float)-Math.sin(y);
//		 this.m4x4_float_array[2][0] = (float)Math.sin(y);
//		 this.m4x4_float_array[2][2] = (float)Math.cos(y);
//	 }

//	 public void rotateZ(float z)
//	 {
//		 this.m4x4_float_array[0][0] = (float)Math.cos(z);
//		 this.m4x4_float_array[0][1] = (float)Math.sin(z);
//		 this.m4x4_float_array[1][0] = (float)-Math.sin(z);
//		 this.m4x4_float_array[1][1] = (float)Math.cos(z);
//	 }

//	 public void m(M3x3 m3x3)
//	 {
//		 M3x3 m3x30 = new M3x3();

//		 for (int i = 0; i < 3; i++)
//		 {
//			 for (int j = 0; j < 3; j++)
//			 {
//				 m3x30.m4x4_float_array[i][j] = this.m4x4_float_array[i][0] * m3x3.m4x4_float_array[0][j] + this.m4x4_float_array[i][1] * m3x3.m4x4_float_array[1][j] + this.m4x4_float_array[i][2] * m3x3.m4x4_float_array[2][j];
//			 }
//		 }

//		 this.m4x4_float_array = m3x30.m4x4_float_array;
//	 }

//	 public void V3M4x4(V3 v3)
//	 {
//		 V3 v30 = new V3(0, 0, 0);

//		 v30.x = v3.x * this.m4x4_float_array[0][0] + v3.y * this.m4x4_float_array[0][1] + v3.z * this.m4x4_float_array[0][2];
//		 v30.y = v3.x * this.m4x4_float_array[1][0] + v3.y * this.m4x4_float_array[1][1] + v3.z * this.m4x4_float_array[1][2];
//		 v30.z = v3.x * this.m4x4_float_array[2][0] + v3.y * this.m4x4_float_array[2][1] + v3.z * this.m4x4_float_array[2][2];

//		 v3.x = v30.x;
//		 v3.y = v30.y;
//		 v3.z = v30.z;
//	 }
// }
