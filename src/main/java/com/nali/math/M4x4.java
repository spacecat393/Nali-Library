package com.nali.math;

public class M4x4
{
	public float[] mat = new float[]
	{
		1.0F, 0.0F, 0.0F, 0.0F,
		0.0F, 1.0F, 0.0F, 0.0F,
		0.0F, 0.0F, 1.0F, 0.0F,
		0.0F, 0.0F, 0.0F, 1.0F
	};

	public final static float[] IDENTITY = new float[]
	{
		1.0F, 0.0F, 0.0F, 0.0F,
		0.0F, 1.0F, 0.0F, 0.0F,
		0.0F, 0.0F, 1.0F, 0.0F,
		0.0F, 0.0F, 0.0F, 1.0F
	};

	public static void inverse(float[] mat, int index)
	{
		int i, j, k;
		M4x4 s = new M4x4();
		M4x4 t = new M4x4();

		System.arraycopy(mat, index, t.mat, 0, 16);

		// Forward elimination
		for (i = 0; i < 3; i++)
		{
			int pivot = i;

			float pivotsize = t.mat[i * 4 + i];

			if (pivotsize < 0)
				pivotsize = -pivotsize;

			for (j = i + 1; j < 4; j++)
			{
				float tmp = t.mat[j * 4 + i];

				if (tmp < 0)
					tmp = -tmp;

				if (tmp > pivotsize)
				{
					pivot = j;
					pivotsize = tmp;
				}
			}

			if (pivotsize == 0)
			{
				// Cannot invert singular matrix
				t.mat = new float[]
				{
					1.0F, 0.0F, 0.0F, 0.0F,
					0.0F, 1.0F, 0.0F, 0.0F,
					0.0F, 0.0F, 1.0F, 0.0F,
					0.0F, 0.0F, 0.0F, 1.0F
				};

				System.arraycopy(t.mat, 0, mat, index, 16);

				return;
			}

			if (pivot != i)
			{
				for (j = 0; j < 4; j++)
				{
					int i4j = i * 4 + j;
					int p4j = pivot * 4 + j;
					float tmp;

					tmp = t.mat[i4j];
					t.mat[i4j] = t.mat[p4j];
					t.mat[p4j] = tmp;

					tmp = s.mat[i4j];
					s.mat[i4j] = s.mat[p4j];
					s.mat[p4j] = tmp;
				}
			}

			for (j = i + 1; j < 4; j++)
			{
				float f = t.mat[j * 4 + i] / t.mat[i * 4 + i];

				for (k = 0; k < 4; k++)
				{
					int j4k = j * 4 + k;
					int i4k = i * 4 + k;
					t.mat[j4k] -= f * t.mat[i4k];
					s.mat[j4k] -= f * s.mat[i4k];
				}
			}
		}

		// Backward substitution
		for (i = 3; i >= 0; --i)
		{
			float f;

			if ((f = t.mat[i * 4 + i]) == 0)
			{
				// Cannot invert singular matrix
				t.mat = new float[]
				{
					1.0F, 0.0F, 0.0F, 0.0F,
					0.0F, 1.0F, 0.0F, 0.0F,
					0.0F, 0.0F, 1.0F, 0.0F,
					0.0F, 0.0F, 0.0F, 1.0F
				};

				System.arraycopy(t.mat, 0, mat, index, 16);

				return;
			}

			for (j = 0; j < 4; j++)
			{
				int i4j = i * 4 + j;

				t.mat[i4j] /= f;
				s.mat[i4j] /= f;
			}

			for (j = 0; j < i; j++)
			{
				f = t.mat[j * 4 + i];

				for (k = 0; k < 4; k++)
				{
					int j4k = j * 4 + k;
					int i4k = i * 4 + k;
					t.mat[j4k] -= f * t.mat[i4k];
					s.mat[j4k] -= f * s.mat[i4k];
				}
			}
		}

		System.arraycopy(s.mat, 0, mat, index, 16);
	}

//	public void inverse()
//	{
//		int i, j, k;
//		M4x4 s = new M4x4();
//		M4x4 t = this;
//
//		// Forward elimination
//		for (i = 0; i < 3; i++)
//		{
//			int pivot = i;
//
//			float pivotsize = t.mat[i * 4 + i];
//
//			if (pivotsize < 0)
//				pivotsize = -pivotsize;
//
//			for (j = i + 1; j < 4; j++)
//			{
//				float tmp = t.mat[j * 4 + i];
//
//				if (tmp < 0)
//					tmp = -tmp;
//
//				if (tmp > pivotsize)
//				{
//					pivot = j;
//					pivotsize = tmp;
//				}
//			}
//
//			if (pivotsize == 0)
//			{
//				// Cannot invert singular matrix
//				this.mat = new float[]
//				{
//					1.0F, 0.0F, 0.0F, 0.0F,
//					0.0F, 1.0F, 0.0F, 0.0F,
//					0.0F, 0.0F, 1.0F, 0.0F,
//					0.0F, 0.0F, 0.0F, 1.0F
//				};
//
//				return;
//			}
//
//			if (pivot != i)
//			{
//				for (j = 0; j < 4; j++)
//				{
//					int i4j = i * 4 + j;
//					int p4j = pivot * 4 + j;
//					float tmp;
//
//					tmp = t.mat[i4j];
//					t.mat[i4j] = t.mat[p4j];
//					t.mat[p4j] = tmp;
//
//					tmp = s.mat[i4j];
//					s.mat[i4j] = s.mat[p4j];
//					s.mat[p4j] = tmp;
//				}
//			}
//
//			for (j = i + 1; j < 4; j++)
//			{
//				float f = t.mat[j * 4 + i] / t.mat[i * 4 + i];
//
//				for (k = 0; k < 4; k++)
//				{
//					int j4k = j * 4 + k;
//					int i4k = i * 4 + k;
//					t.mat[j4k] -= f * t.mat[i4k];
//					s.mat[j4k] -= f * s.mat[i4k];
//				}
//			}
//		}
//
//		// Backward substitution
//		for (i = 3; i >= 0; --i)
//		{
//			float f;
//
//			if ((f = t.mat[i * 4 + i]) == 0)
//			{
//				// Cannot invert singular matrix
//				this.mat = new float[]
//				{
//					1.0F, 0.0F, 0.0F, 0.0F,
//					0.0F, 1.0F, 0.0F, 0.0F,
//					0.0F, 0.0F, 1.0F, 0.0F,
//					0.0F, 0.0F, 0.0F, 1.0F
//				};
//
//				return;
//			}
//
//			for (j = 0; j < 4; j++)
//			{
//				int i4j = i * 4 + j;
//
//				t.mat[i4j] /= f;
//				s.mat[i4j] /= f;
//			}
//
//			for (j = 0; j < i; j++)
//			{
//				f = t.mat[j * 4 + i];
//
//				for (k = 0; k < 4; k++)
//				{
//					int j4k = j * 4 + k;
//					int i4k = i * 4 + k;
//					t.mat[j4k] -= f * t.mat[i4k];
//					s.mat[j4k] -= f * s.mat[i4k];
//				}
//			}
//		}
//
//		this.mat = s.mat;
//	}

//	public void rotateX(float x)
//	{
//		this.mat[5] = (float)Math.cos(x);
//		this.mat[6] = (float)Math.sin(x);
//		this.mat[9] = (float)-Math.sin(x);
//		this.mat[10] = (float)Math.cos(x);
//	}
//
//	public void rotateY(float y)
//	{
//		this.mat[0] = (float)Math.cos(y);
//		this.mat[2] = (float)-Math.sin(y);
//		this.mat[8] = (float)Math.sin(y);
//		this.mat[10] = (float)Math.cos(y);
//	}
//
//	public void rotateZ(float z)
//	{
//		this.mat[0] = (float)Math.cos(z);
//		this.mat[1] = (float)Math.sin(z);
//		this.mat[4] = (float)-Math.sin(z);
//		this.mat[5] = (float)Math.cos(z);
//	}

//	public void translateUV(float x, float y/*, float z*/)
//	{
//		this.mat[12] = x;
//		this.mat[13] = y;
////		this.mat[14] = z;
//	}
//
//	public void translateXYZ(float x, float y, float z)
//	{
//		this.mat[3] = x;
//		this.mat[7] = y;
//		this.mat[11] = z;
//	}
//
//	public void translatePlus(float x, float y, float z)
//	{
//		this.mat[3] += x;
//		this.mat[7] += y;
//		this.mat[11] += z;
//	}

//	public void scale(float x, float y, float z)
//	{
//		this.mat[0] *= x;
//		this.mat[5] *= y;
//		this.mat[10] *= z;
//	}

	// public void setProjectionMatrix(float angle_of_view, float near, float far)
	// {
	//	 float scale = (float)(1 / Math.tan(angle_of_view * 0.5 * 3.141 / 180));
	//	 this.mat[0] = scale;
	//	 this.mat[5] = scale;
	//	 this.mat[10] = -far / (far - near);
	//	 this.mat[14] = -far * near / (far - near);
	//	 this.mat[11] = -1;
	//	 this.mat[15] = 0;
	// }

//	public static M4x4 getOrthographic(float b, float t, float l, float r, float n, float f)
//	{
//		M4x4 m4x4 = new M4x4();
//		// m4x4.mat[0] = 2.0F / (r - l);
//		// m4x4.mat[1] = 0.0F;
//		// m4x4.mat[2] = 0.0F;
//		// m4x4.mat[3] = 0.0F;
//
//		// m4x4.mat[4] = 0.0F;
//		// m4x4.mat[5] = 2.0F / (t - b);
//		// m4x4.mat[6] = 0.0F;
//		// m4x4.mat[7] = 0.0F;
//
//		// m4x4.mat[8] = 0.0F;
//		// m4x4.mat[9] = 0.0F;
//		// m4x4.mat[10] = -2.0F / (f - n);
//		// m4x4.mat[11] = 0.0F;
//
//		// m4x4.mat[12] = -(r + l) / (r - l);
//		// m4x4.mat[13] = -(t + b) / (t - b);
//		// m4x4.mat[14] = -(f + n) / (f - n);
//		// m4x4.mat[15] = 1.0F;
//
//		m4x4.mat[0] = 2.0F / (r - l);
//
//		m4x4.mat[5] = 2.0F / (t - b);
//
//		m4x4.mat[10] = -2.0F / (f - n);
//
//		m4x4.mat[3] = -(r + l) / (r - l);
//		m4x4.mat[7] = -(t + b) / (t - b);
//		m4x4.mat[11] = -(f + n) / (f - n);
//		m4x4.mat[15] = 1.0F;
//		return m4x4;
//	}

	// public void transformV4(V4 v40, V4 v41)
	// {
	//	 v41.x = v40.x * this.mat[0] + v40.y * this.mat[4] + v40.z * this.mat[8] + v40.w * this.mat[12];
	//	 v41.y = v40.x * this.mat[1] + v40.y * this.mat[5] + v40.z * this.mat[9] + v40.w * this.mat[13];
	//	 v41.z = v40.x * this.mat[2] + v40.y * this.mat[6] + v40.z * this.mat[10] + v40.w * this.mat[14];
	//	 v41.w = v40.x * this.mat[3] + v40.y * this.mat[7] + v40.z * this.mat[11] + v40.w * this.mat[15];
	// }

//	public void invert(M4x4 m4x4)
//	{
//		float b00 = m4x4.mat[0] * m4x4.mat[5] - m4x4.mat[1] * m4x4.mat[4],
//		b01 = m4x4.mat[0] * m4x4.mat[6] - m4x4.mat[2] * m4x4.mat[4],
//		b02 = m4x4.mat[0] * m4x4.mat[7] - m4x4.mat[3] * m4x4.mat[4],
//		b03 = m4x4.mat[1] * m4x4.mat[6] - m4x4.mat[2] * m4x4.mat[5],
//		b04 = m4x4.mat[1] * m4x4.mat[7] - m4x4.mat[3] * m4x4.mat[5],
//		b05 = m4x4.mat[2] * m4x4.mat[7] - m4x4.mat[3] * m4x4.mat[6],
//		b06 = m4x4.mat[8] * m4x4.mat[13] - m4x4.mat[9] * m4x4.mat[12],
//		b07 = m4x4.mat[8] * m4x4.mat[14] - m4x4.mat[10] * m4x4.mat[12],
//		b08 = m4x4.mat[8] * m4x4.mat[15] - m4x4.mat[11] * m4x4.mat[12],
//		b09 = m4x4.mat[9] * m4x4.mat[14] - m4x4.mat[10] * m4x4.mat[13],
//		b10 = m4x4.mat[9] * m4x4.mat[15] - m4x4.mat[11] * m4x4.mat[13],
//		b11 = m4x4.mat[10] * m4x4.mat[15] - m4x4.mat[11] * m4x4.mat[14],
//
//		// Calculate the determinant
//		det = b00 * b11 - b01 * b10 + b02 * b09 + b03 * b08 - b04 * b07 + b05 * b06;
//
//		if (det == 0.0F)
//		{
//			return;
//		}
//
//		det = 1.0F / det;
//
//		this.mat[0] = (m4x4.mat[5] * b11 - m4x4.mat[6] * b10 + m4x4.mat[7] * b09) * det;
//		this.mat[1] = (m4x4.mat[2] * b10 - m4x4.mat[1] * b11 - m4x4.mat[3] * b09) * det;
//		this.mat[2] = (m4x4.mat[13] * b05 - m4x4.mat[14] * b04 + m4x4.mat[15] * b03) * det;
//		this.mat[3] = (m4x4.mat[10] * b04 - m4x4.mat[9] * b05 - m4x4.mat[11] * b03) * det;
//		this.mat[4] = (m4x4.mat[6] * b08 - m4x4.mat[4] * b11 - m4x4.mat[7] * b07) * det;
//		this.mat[5] = (m4x4.mat[0] * b11 - m4x4.mat[2] * b08 + m4x4.mat[3] * b07) * det;
//		this.mat[6] = (m4x4.mat[14] * b02 - m4x4.mat[12] * b05 - m4x4.mat[15] * b01) * det;
//		this.mat[7] = (m4x4.mat[8] * b05 - m4x4.mat[10] * b02 + m4x4.mat[11] * b01) * det;
//		this.mat[8] = (m4x4.mat[4] * b10 - m4x4.mat[5] * b08 + m4x4.mat[7] * b06) * det;
//		this.mat[9] = (m4x4.mat[1] * b08 - m4x4.mat[0] * b10 - m4x4.mat[3] * b06) * det;
//		this.mat[10] = (m4x4.mat[12] * b04 - m4x4.mat[13] * b02 + m4x4.mat[15] * b00) * det;
//		this.mat[11] = (m4x4.mat[9] * b02 - m4x4.mat[8] * b04 - m4x4.mat[11] * b00) * det;
//		this.mat[12] = (m4x4.mat[5] * b07 - m4x4.mat[4] * b09 - m4x4.mat[6] * b06) * det;
//		this.mat[13] = (m4x4.mat[0] * b09 - m4x4.mat[1] * b07 + m4x4.mat[2] * b06) * det;
//		this.mat[14] = (m4x4.mat[13] * b01 - m4x4.mat[12] * b03 - m4x4.mat[14] * b00) * det;
//		this.mat[15] = (m4x4.mat[8] * b03 - m4x4.mat[9] * b01 + m4x4.mat[10] * b00) * det;
//	}

//	public void multiply(float[] mat3, float w)
//	{
//		float x = mat3[0];
//		float y = mat3[1];
//		float z = mat3[2];
//
//		mat3[0] = this.mat[0] * x + this.mat[1] * y + this.mat[2] * z + this.mat[3] * w;
//		mat3[1] = this.mat[4] * x + this.mat[5] * y + this.mat[6] * z + this.mat[7] * w;
//		mat3[2] = this.mat[8] * x + this.mat[9] * y + this.mat[10] * z + this.mat[11] * w;
//
//		// mat3[0].setX(m[0][0] * x + m[0][1] * y + m[0][2] * z + m[0][3] * w);
//		// mat3[1].setY(m[1][0] * x + m[1][1] * y + m[1][2] * z + m[1][3] * w);
//		// mat3[2].setZ(m[2][0] * x + m[2][1] * y + m[2][2] * z + m[2][3] * w);
//		// mat3[3].setW(m[3][0] * x + m[3][1] * y + m[3][2] * z + m[3][3] * w);
//	}

//	public void add(float[] mat)
//	{
//		for (int i = 0; i < 16; i++)
//		{
//			mat[i] += this.mat[i];
//		}
//	}
//
//	public static void add(float[] mat_0, float[] mat_1, int index_0, int index_1)
//	{
//		for (int i = 0; i < 16; i++)
//		{
//			mat_1[index_1 + i] += mat_0[index_0 + i];
//		}
//	}

//	public void multiply(float[] mat)
//	{
//		float[] float_array = new float[16];
//
//		for (int i = 0; i < 4; i++)
//		{
//			for (int j = 0; j < 4; j++)
//			{
//				float_array[i * 4 + j] = this.mat[i * 4] * mat[j] + this.mat[i * 4 + 1] * mat[4 + j] + this.mat[i * 4 + 2] * mat[8 + j] + this.mat[i * 4 + 3] * mat[12 + j];
//			}
//		}
//
//		System.arraycopy(float_array, 0, this.mat, 0, 16);
//	}

	public void multiply(float[] mat, int index)
	{
		float[] float_array = new float[16];

		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				float_array[i * 4 + j] = this.mat[i * 4] * mat[j + index] + this.mat[i * 4 + 1] * mat[4 + j + index] + this.mat[i * 4 + 2] * mat[8 + j + index] + this.mat[i * 4 + 3] * mat[12 + j + index];
			}
		}

		System.arraycopy(float_array, 0, mat, index, 16);
	}

	public static void multiply(float[] mat_0, float[] mat_1, int index_0, int index_1)
	{
		float[] float_array = new float[16];

		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				float_array[i * 4 + j] = mat_0[index_0 + i * 4] * mat_1[j + index_1] + mat_0[index_0 + i * 4 + 1] * mat_1[4 + j + index_1] + mat_0[index_0 + i * 4 + 2] * mat_1[8 + j + index_1] + mat_0[index_0 + i * 4 + 3] * mat_1[12 + j + index_1];
			}
		}

		System.arraycopy(float_array, 0, mat_1, index_1, 16);
	}

//	public void cloneMat(float[] mat, int index)
//	{
//		System.arraycopy(this.mat, 0, mat, index, 16);
//	}

//	public static float[] interpolate(float[] mat4_0, float[] mat4_1, float time)
//	{
//		float[] float_array = new float[16];
//
//		for (int i = 0; i < 16; i++)
//		{
//			float_array[i] = (1 - time) * mat4_0[i] + time * mat4_1[i];
//		}
//
//		return float_array;
//	}

//	public static M4x4 rotate(float angle, float x, float y, float z)
//	{
//		M4x4 m4x4 = new M4x4();
//		float radians = (float)Math.toRadians(angle);
//
//		float length = (float)Math.sqrt(x * x + y * y + z * z);
//		x /= length;
//		y /= length;
//		z /= length;
//
//		float c = (float)Math.cos(radians);
//		float s = (float)Math.sin(radians);
//		float omc = 1.0F - c;
//
//		m4x4.mat = new float[]
//		{
//			x * x * omc + c, x * y * omc - z * s, x * z * omc + y * s, 0,
//			y * x * omc + z * s, y * y * omc + c, y * z * omc - x * s, 0,
//			x * z * omc - y * s, y * z * omc + x * s, z * z * omc + c, 0,
//			0, 0, 0, 1
//		};
//
//		return m4x4;
//	}

//	public static void lerp(float[] s_o_mat4, float[] e_mat4, int s_index, int e_index, float blend_factor)
//	{
//		for (int i = 0; i < 16; i++)
//		{
//			float s = s_o_mat4[i + s_index];
//			s_o_mat4[i + s_index] = s + (e_mat4[i + e_index] - s) * blend_factor;
//		}
//	}

	public static float[] multiplyVec4Mat4(float[] vec4, float[] mat4)
	{
		float[] result = new float[4];
		for (int i = 0; i < 4; i++)
		{
			float sum = 0.0F;
			for (int j = 0; j < 4; j++)
			{
				sum += vec4[j] * mat4[i * 4 + j];
			}
			result[i] = sum;
		}
		return result;
	}
}
