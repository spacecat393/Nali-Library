package com.nali.math;

public class V4
{
//	public float x, y, z, w;
	public static float[] TV4_FLOAT_ARRAY = new float[4];

	public static void q(float[] w_qv4_float_array, float x, float y, float z)
	{
		// // pi / 180 = 0.01745329251
		// float f = x * 0.017453292F;
		// float f1 = y * 0.017453292F;
		// float f2 = z * 0.017453292F;
		// float f3 = (float)Math.sin(0.5F * f);
		// float f4 = (float)Math.cos(0.5F * f);
		// float f5 = (float)Math.sin(0.5F * f1);
		// float f6 = (float)Math.cos(0.5F * f1);
		// float f7 = (float)Math.sin(0.5F * f2);
		// float f8 = (float)Math.cos(0.5F * f2);

		// w_qv4_float_array[0] = f3 * f6 * f8 + f4 * f5 * f7;
		// w_qv4_float_array[1] = f4 * f5 * f8 - f3 * f6 * f7;
		// w_qv4_float_array[2] = f3 * f5 * f8 + f4 * f6 * f7;
		// w_qv4_float_array[3] = f4 * f6 * f8 - f3 * f5 * f7;

		float c1 = (float)Math.cos(x / 2);
		float s1 = (float)Math.sin(x / 2);
		float c2 = (float)Math.cos(y / 2);
		float s2 = (float)Math.sin(y / 2);
		float c3 = (float)Math.cos(z / 2);
		float s3 = (float)Math.sin(z / 2);

		w_qv4_float_array[3] = c1 * c2 * c3 - s1 * s2 * s3;
		w_qv4_float_array[0] = s1 * c2 * c3 + c1 * s2 * s3;
		w_qv4_float_array[1] = c1 * s2 * c3 - s1 * c2 * s3;
		w_qv4_float_array[2] = c1 * c2 * s3 + s1 * s2 * c3;
	}

//	public Quaternion(float x, float y, float z, float w)
//	{
//		v4_float_array[0] = x;
//		v4_float_array[1] = y;
//		v4_float_array[2] = z;
//		v4_float_array[3] = w;
//	}

	// public static float[] by(float[] matrix)
	// {
	//	 float[] quaternion_float_array = new float[(matrix.length / 16) * 4];
	//	 int index = 0;

	//	 for (int i = 0; i < matrix.length; i += 16)
	//	 {
	//		 // Extract the rotation part of the matrix
	//		 float[] rotationMatrix = new float[9];
	//		 rotationMatrix[0] = matrix[i];
	//		 rotationMatrix[1] = matrix[i + 1];
	//		 rotationMatrix[2] = matrix[i + 2];
	//		 rotationMatrix[3] = matrix[i + 4];
	//		 rotationMatrix[4] = matrix[i + 5];
	//		 rotationMatrix[5] = matrix[i + 6];
	//		 rotationMatrix[6] = matrix[i + 8];
	//		 rotationMatrix[7] = matrix[i + 9];
	//		 rotationMatrix[8] = matrix[i + 10];

	//		 // Calculate the trace of the rotation matrix
	//		 float trace = rotationMatrix[0] + rotationMatrix[4] + rotationMatrix[8];

	//		 if (trace > 0.0f)
	//		 {
	//			 float s = 0.5f / (float)Math.sqrt(trace + 1.0f);

	//			 quaternion_float_array[index++] = 0.25f / s;
	//			 quaternion_float_array[index++] = (rotationMatrix[7] - rotationMatrix[5]) * s;
	//			 quaternion_float_array[index++] = (rotationMatrix[2] - rotationMatrix[6]) * s;
	//			 quaternion_float_array[index++] = (rotationMatrix[3] - rotationMatrix[1]) * s;
	//		 }
	//		 else
	//		 {
	//			 if (rotationMatrix[0] > rotationMatrix[4] && rotationMatrix[0] > rotationMatrix[8])
	//			 {
	//				 float s = 2.0f * (float)Math.sqrt(1.0f + rotationMatrix[0] - rotationMatrix[4] - rotationMatrix[8]);

	//				 quaternion_float_array[index++] = (rotationMatrix[7] - rotationMatrix[5]) / s;
	//				 quaternion_float_array[index++] = 0.25f * s;
	//				 quaternion_float_array[index++] = (rotationMatrix[1] + rotationMatrix[3]) / s;
	//				 quaternion_float_array[index++] = (rotationMatrix[2] + rotationMatrix[6]) / s;
	//			 }
	//			 else if (rotationMatrix[4] > rotationMatrix[8])
	//			 {
	//				 float s = 2.0f * (float)Math.sqrt(1.0f + rotationMatrix[4] - rotationMatrix[0] - rotationMatrix[8]);

	//				 quaternion_float_array[index++] = (rotationMatrix[2] - rotationMatrix[6]) / s;
	//				 quaternion_float_array[index++] = (rotationMatrix[1] + rotationMatrix[3]) / s;
	//				 quaternion_float_array[index++] = 0.25f * s;
	//				 quaternion_float_array[index++] = (rotationMatrix[5] + rotationMatrix[7]) / s;
	//			 }
	//			 else
	//			 {
	//				 float s = 2.0f * (float)Math.sqrt(1.0f + rotationMatrix[8] - rotationMatrix[0] - rotationMatrix[4]);

	//				 quaternion_float_array[index++] = (rotationMatrix[3] - rotationMatrix[1]) / s;
	//				 quaternion_float_array[index++] = (rotationMatrix[2] + rotationMatrix[6]) / s;
	//				 quaternion_float_array[index++] = (rotationMatrix[5] + rotationMatrix[7]) / s;
	//				 quaternion_float_array[index++] = 0.25f * s;
	//			 }
	//		 }
	//	 }

	//	 return quaternion_float_array;
	// }

	// public static Quaternion by(float[] matrix)
	// {
	//	 // Extract the rotation part of the matrix
	//	 float[] rotationMatrix = new float[9];
	//	 rotationMatrix[0] = matrix[0];
	//	 rotationMatrix[1] = matrix[1];
	//	 rotationMatrix[2] = matrix[2];
	//	 rotationMatrix[3] = matrix[4];
	//	 rotationMatrix[4] = matrix[5];
	//	 rotationMatrix[5] = matrix[6];
	//	 rotationMatrix[6] = matrix[8];
	//	 rotationMatrix[7] = matrix[9];
	//	 rotationMatrix[8] = matrix[10];

	//	 // Calculate the trace of the rotation matrix
	//	 float trace = rotationMatrix[0] + rotationMatrix[4] + rotationMatrix[8];

	//	 if (trace > 0.0f)
	//	 {
	//		 float s = 0.5f / (float)Math.sqrt(trace + 1.0f);

	//		 return new Quaternion
	//		 (
	//			 0.25f / s,
	//			 (rotationMatrix[7] - rotationMatrix[5]) * s,
	//			 (rotationMatrix[2] - rotationMatrix[6]) * s,
	//			 (rotationMatrix[3] - rotationMatrix[1]) * s
	//		 );
	//	 }
	//	 else
	//	 {
	//		 if (rotationMatrix[0] > rotationMatrix[4] && rotationMatrix[0] > rotationMatrix[8])
	//		 {
	//			 float s = 2.0f * (float)Math.sqrt(1.0f + rotationMatrix[0] - rotationMatrix[4] - rotationMatrix[8]);

	//			 return new Quaternion
	//			 (
	//				 (rotationMatrix[7] - rotationMatrix[5]) / s,
	//				 0.25f * s,
	//				 (rotationMatrix[1] + rotationMatrix[3]) / s,
	//				 (rotationMatrix[2] + rotationMatrix[6]) / s
	//			 );
	//		 }
	//		 else if (rotationMatrix[4] > rotationMatrix[8])
	//		 {
	//			 float s = 2.0f * (float)Math.sqrt(1.0f + rotationMatrix[4] - rotationMatrix[0] - rotationMatrix[8]);

	//			 return new Quaternion
	//			 (
	//				 (rotationMatrix[2] - rotationMatrix[6]) / s,
	//				 (rotationMatrix[1] + rotationMatrix[3]) / s,
	//				 0.25f * s,
	//				 (rotationMatrix[5] + rotationMatrix[7]) / s
	//			 );
	//		 }
	//		 else
	//		 {
	//			 float s = 2.0f * (float)Math.sqrt(1.0f + rotationMatrix[8] - rotationMatrix[0] - rotationMatrix[4]);

	//			 return new Quaternion
	//			 (
	//				 (rotationMatrix[3] - rotationMatrix[1]) / s,
	//				 (rotationMatrix[2] + rotationMatrix[6]) / s,
	//				 (rotationMatrix[5] + rotationMatrix[7]) / s,
	//				 0.25f * s
	//			 );
	//		 }
	//	 }
	// }

	public static void m(float[] r_v4_float_array, float[] w_v4_float_array)
	{
		float x = w_v4_float_array[3] * r_v4_float_array[0] + w_v4_float_array[0] * r_v4_float_array[3] + w_v4_float_array[1] * r_v4_float_array[2] - w_v4_float_array[2] * r_v4_float_array[1];
		float y = w_v4_float_array[3] * r_v4_float_array[1] - w_v4_float_array[0] * r_v4_float_array[2] + w_v4_float_array[1] * r_v4_float_array[3] + w_v4_float_array[2] * r_v4_float_array[0];
		float z = w_v4_float_array[3] * r_v4_float_array[2] + w_v4_float_array[0] * r_v4_float_array[1] - w_v4_float_array[1] * r_v4_float_array[0] + w_v4_float_array[2] * r_v4_float_array[3];
		float w = w_v4_float_array[3] * r_v4_float_array[3] - w_v4_float_array[0] * r_v4_float_array[0] - w_v4_float_array[1] * r_v4_float_array[1] - w_v4_float_array[2] * r_v4_float_array[2];

		w_v4_float_array[3] = w;
		w_v4_float_array[0] = x;
		w_v4_float_array[1] = y;
		w_v4_float_array[2] = z;
	}

//	public M4x4 getM4X4()
//	{
//		final float xy = v4_float_array[0] * v4_float_array[1];
//		final float xz = v4_float_array[0] * v4_float_array[2];
//		final float xw = v4_float_array[0] * v4_float_array[3];
//		final float yz = v4_float_array[1] * v4_float_array[2];
//		final float yw = v4_float_array[1] * v4_float_array[3];
//		final float zw = v4_float_array[2] * v4_float_array[3];
//		final float x_squared = v4_float_array[0] * v4_float_array[0];
//		final float y_squared = v4_float_array[1] * v4_float_array[1];
//		final float z_squared = v4_float_array[2] * v4_float_array[2];
//
//		M4x4 m4x4 = new M4x4();
//		m4x4.m4x4_float_array[0] = 1 - 2 * (y_squared + z_squared);
//		m4x4.m4x4_float_array[1] = 2 * (xy - zw);
//		m4x4.m4x4_float_array[2] = 2 * (xz + yw);
//		// m4x4.m4x4_float_array[3] = 0;
//		m4x4.m4x4_float_array[4] = 2 * (xy + zw);
//		m4x4.m4x4_float_array[5] = 1 - 2 * (x_squared + z_squared);
//		m4x4.m4x4_float_array[6] = 2 * (yz - xw);
//		// m4x4.m4x4_float_array[7] = 0;
//		m4x4.m4x4_float_array[8] = 2 * (xz - yw);
//		m4x4.m4x4_float_array[9] = 2 * (yz + xw);
//		m4x4.m4x4_float_array[10] = 1 - 2 * (x_squared + y_squared);
//		// m4x4.m4x4_float_array[11] = 0;
//		// m4x4.m4x4_float_array[12] = 0;
//		// m4x4.m4x4_float_array[13] = 0;
//		// m4x4.m4x4_float_array[14] = 0;
//		// m4x4.m4x4_float_array[15] = 1;
//
//		return m4x4;
//	}

	public static float[] getM4X4(float[] r_qv4_float_array)
	{
		final float xy = r_qv4_float_array[0] * r_qv4_float_array[1];
		final float xz = r_qv4_float_array[0] * r_qv4_float_array[2];
		final float xw = r_qv4_float_array[0] * r_qv4_float_array[3];
		final float yz = r_qv4_float_array[1] * r_qv4_float_array[2];
		final float yw = r_qv4_float_array[1] * r_qv4_float_array[3];
		final float zw = r_qv4_float_array[2] * r_qv4_float_array[3];
		final float x_squared = r_qv4_float_array[0] * r_qv4_float_array[0];
		final float y_squared = r_qv4_float_array[1] * r_qv4_float_array[1];
		final float z_squared = r_qv4_float_array[2] * r_qv4_float_array[2];

		float[] m4x4_float_array = new float[]
		{
			1 - 2 * (y_squared + z_squared), 2 * (xy - zw), 2 * (xz + yw), 0.0F,
			2 * (xy + zw), 1 - 2 * (x_squared + z_squared), 2 * (yz - xw), 0.0F,
			2 * (xz - yw), 2 * (yz + xw), 1 - 2 * (x_squared + y_squared), 0.0F,
			0.0F, 0.0F, 0.0F, 1.0F
		};

		return m4x4_float_array;
	}

	// public M3x3 getM3x3()
	// {
	//	 final float xy = v4_float_array[0] * v4_float_array[1];
	// 	final float xz = v4_float_array[0] * v4_float_array[2];
	// 	final float xw = v4_float_array[0] * v4_float_array[3];
	// 	final float yz = v4_float_array[1] * v4_float_array[2];
	// 	final float yw = v4_float_array[1] * v4_float_array[3];
	// 	final float zw = v4_float_array[2] * v4_float_array[3];
	// 	final float x_squared = v4_float_array[0] * v4_float_array[0];
	// 	final float y_squared = v4_float_array[1] * v4_float_array[1];
	// 	final float z_squared = v4_float_array[2] * v4_float_array[2];

	//	 M3x3 m3x3 = new M3x3();
	// 	m3x3.m4x4_float_array[0][0] = 1 - 2 * (y_squared + z_squared);
	// 	m3x3.m4x4_float_array[0][1] = 2 * (xy - zw);
	// 	m3x3.m4x4_float_array[0][2] = 2 * (xz + yw);
	// 	m3x3.m4x4_float_array[1][0] = 2 * (xy + zw);
	// 	m3x3.m4x4_float_array[1][1] = 1 - 2 * (x_squared + z_squared);
	// 	m3x3.m4x4_float_array[1][2] = 2 * (yz - xw);
	// 	m3x3.m4x4_float_array[2][0] = 2 * (xz - yw);
	// 	m3x3.m4x4_float_array[2][1] = 2 * (yz + xw);
	// 	m3x3.m4x4_float_array[2][2] = 1 - 2 * (x_squared + y_squared);

	//	 return m3x3;
	// }
}
