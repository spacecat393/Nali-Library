package com.nali.math;

public class M4x4
{
//	public float[] m4x4_float_array = new float[]
//	{
//		1.0F, 0.0F, 0.0F, 0.0F,
//		0.0F, 1.0F, 0.0F, 0.0F,
//		0.0F, 0.0F, 1.0F, 0.0F,
//		0.0F, 0.0F, 0.0F, 1.0F
//	};

	public final static float[] DM4X4_FLOAT_ARRAY = new float[]
	{
		1.0F, 0.0F, 0.0F, 0.0F,
		0.0F, 1.0F, 0.0F, 0.0F,
		0.0F, 0.0F, 1.0F, 0.0F,
		0.0F, 0.0F, 0.0F, 1.0F
	};

	public static void i(float[] w_m4x4_float_array, int w_index)
	{
		int i, j, k;
//		M4x4 s = new M4x4();
//		M4x4 t = new M4x4();
		float[] s = new float[]
		{
			1.0F, 0.0F, 0.0F, 0.0F,
			0.0F, 1.0F, 0.0F, 0.0F,
			0.0F, 0.0F, 1.0F, 0.0F,
			0.0F, 0.0F, 0.0F, 1.0F
		};
		float[] t = new float[]
		{
			1.0F, 0.0F, 0.0F, 0.0F,
			0.0F, 1.0F, 0.0F, 0.0F,
			0.0F, 0.0F, 1.0F, 0.0F,
			0.0F, 0.0F, 0.0F, 1.0F
		};

		System.arraycopy(w_m4x4_float_array, w_index, t, 0, 16);

		// Forward elimination
		for (i = 0; i < 3; i++)
		{
			int pivot = i;

			float pivotsize = t[i * 4 + i];

			if (pivotsize < 0)
				pivotsize = -pivotsize;

			for (j = i + 1; j < 4; j++)
			{
				float tmp = t[j * 4 + i];

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
				t = new float[]
				{
					1.0F, 0.0F, 0.0F, 0.0F,
					0.0F, 1.0F, 0.0F, 0.0F,
					0.0F, 0.0F, 1.0F, 0.0F,
					0.0F, 0.0F, 0.0F, 1.0F
				};

				System.arraycopy(t, 0, w_m4x4_float_array, w_index, 16);

				return;
			}

			if (pivot != i)
			{
				for (j = 0; j < 4; j++)
				{
					int i4j = i * 4 + j;
					int p4j = pivot * 4 + j;
					float tmp;

					tmp = t[i4j];
					t[i4j] = t[p4j];
					t[p4j] = tmp;

					tmp = s[i4j];
					s[i4j] = s[p4j];
					s[p4j] = tmp;
				}
			}

			for (j = i + 1; j < 4; j++)
			{
				float f = t[j * 4 + i] / t[i * 4 + i];

				for (k = 0; k < 4; k++)
				{
					int j4k = j * 4 + k;
					int i4k = i * 4 + k;
					t[j4k] -= f * t[i4k];
					s[j4k] -= f * s[i4k];
				}
			}
		}

		// Backward substitution
		for (i = 3; i >= 0; --i)
		{
			float f;

			if ((f = t[i * 4 + i]) == 0)
			{
				// Cannot invert singular matrix
				t = new float[]
				{
					1.0F, 0.0F, 0.0F, 0.0F,
					0.0F, 1.0F, 0.0F, 0.0F,
					0.0F, 0.0F, 1.0F, 0.0F,
					0.0F, 0.0F, 0.0F, 1.0F
				};

				System.arraycopy(t, 0, w_m4x4_float_array, w_index, 16);

				return;
			}

			for (j = 0; j < 4; j++)
			{
				int i4j = i * 4 + j;

				t[i4j] /= f;
				s[i4j] /= f;
			}

			for (j = 0; j < i; j++)
			{
				f = t[j * 4 + i];

				for (k = 0; k < 4; k++)
				{
					int j4k = j * 4 + k;
					int i4k = i * 4 + k;
					t[j4k] -= f * t[i4k];
					s[j4k] -= f * s[i4k];
				}
			}
		}

		System.arraycopy(s, 0, w_m4x4_float_array, w_index, 16);
	}

//	public void i()
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
//			float pivotsize = t.m4x4_float_array[i * 4 + i];
//
//			if (pivotsize < 0)
//				pivotsize = -pivotsize;
//
//			for (j = i + 1; j < 4; j++)
//			{
//				float tmp = t.m4x4_float_array[j * 4 + i];
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
//				this.m4x4_float_array = new float[]
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
//					tmp = t.m4x4_float_array[i4j];
//					t.m4x4_float_array[i4j] = t.m4x4_float_array[p4j];
//					t.m4x4_float_array[p4j] = tmp;
//
//					tmp = s.m4x4_float_array[i4j];
//					s.m4x4_float_array[i4j] = s.m4x4_float_array[p4j];
//					s.m4x4_float_array[p4j] = tmp;
//				}
//			}
//
//			for (j = i + 1; j < 4; j++)
//			{
//				float f = t.m4x4_float_array[j * 4 + i] / t.m4x4_float_array[i * 4 + i];
//
//				for (k = 0; k < 4; k++)
//				{
//					int j4k = j * 4 + k;
//					int i4k = i * 4 + k;
//					t.m4x4_float_array[j4k] -= f * t.m4x4_float_array[i4k];
//					s.m4x4_float_array[j4k] -= f * s.m4x4_float_array[i4k];
//				}
//			}
//		}
//
//		// Backward substitution
//		for (i = 3; i >= 0; --i)
//		{
//			float f;
//
//			if ((f = t.m4x4_float_array[i * 4 + i]) == 0)
//			{
//				// Cannot invert singular matrix
//				this.m4x4_float_array = new float[]
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
//				t.m4x4_float_array[i4j] /= f;
//				s.m4x4_float_array[i4j] /= f;
//			}
//
//			for (j = 0; j < i; j++)
//			{
//				f = t.m4x4_float_array[j * 4 + i];
//
//				for (k = 0; k < 4; k++)
//				{
//					int j4k = j * 4 + k;
//					int i4k = i * 4 + k;
//					t.m4x4_float_array[j4k] -= f * t.m4x4_float_array[i4k];
//					s.m4x4_float_array[j4k] -= f * s.m4x4_float_array[i4k];
//				}
//			}
//		}
//
//		this.m4x4_float_array = s.m4x4_float_array;
//	}

//	public void rotateX(float x)
//	{
//		this.m4x4_float_array[5] = (float)Math.cos(x);
//		this.m4x4_float_array[6] = (float)Math.sin(x);
//		this.m4x4_float_array[9] = (float)-Math.sin(x);
//		this.m4x4_float_array[10] = (float)Math.cos(x);
//	}
//
//	public void rotateY(float y)
//	{
//		this.m4x4_float_array[0] = (float)Math.cos(y);
//		this.m4x4_float_array[2] = (float)-Math.sin(y);
//		this.m4x4_float_array[8] = (float)Math.sin(y);
//		this.m4x4_float_array[10] = (float)Math.cos(y);
//	}
//
//	public void rotateZ(float z)
//	{
//		this.m4x4_float_array[0] = (float)Math.cos(z);
//		this.m4x4_float_array[1] = (float)Math.sin(z);
//		this.m4x4_float_array[4] = (float)-Math.sin(z);
//		this.m4x4_float_array[5] = (float)Math.cos(z);
//	}

//	public void translateUV(float x, float y/*, float z*/)
//	{
//		this.m4x4_float_array[12] = x;
//		this.m4x4_float_array[13] = y;
////		this.m4x4_float_array[14] = z;
//	}
//
//	public void translateXYZ(float x, float y, float z)
//	{
//		this.m4x4_float_array[3] = x;
//		this.m4x4_float_array[7] = y;
//		this.m4x4_float_array[11] = z;
//	}
//
//	public void translatePlus(float x, float y, float z)
//	{
//		this.m4x4_float_array[3] += x;
//		this.m4x4_float_array[7] += y;
//		this.m4x4_float_array[11] += z;
//	}

//	public void scale(float x, float y, float z)
//	{
//		this.m4x4_float_array[0] *= x;
//		this.m4x4_float_array[5] *= y;
//		this.m4x4_float_array[10] *= z;
//	}

	// public void setProjectionMatrix(float angle_of_view, float near, float far)
	// {
	//	 float scale = (float)(1 / Math.tan(angle_of_view * 0.5 * 3.141 / 180));
	//	 this.m4x4_float_array[0] = scale;
	//	 this.m4x4_float_array[5] = scale;
	//	 this.m4x4_float_array[10] = -far / (far - near);
	//	 this.m4x4_float_array[14] = -far * near / (far - near);
	//	 this.m4x4_float_array[11] = -1;
	//	 this.m4x4_float_array[15] = 0;
	// }

//	public static M4x4 getOrthographic(float b, float t, float l, float r, float n, float f)
//	{
//		M4x4 m4x4 = new M4x4();
//		// m4x4.m4x4_float_array[0] = 2.0F / (r - l);
//		// m4x4.m4x4_float_array[1] = 0.0F;
//		// m4x4.m4x4_float_array[2] = 0.0F;
//		// m4x4.m4x4_float_array[3] = 0.0F;
//
//		// m4x4.m4x4_float_array[4] = 0.0F;
//		// m4x4.m4x4_float_array[5] = 2.0F / (t - b);
//		// m4x4.m4x4_float_array[6] = 0.0F;
//		// m4x4.m4x4_float_array[7] = 0.0F;
//
//		// m4x4.m4x4_float_array[8] = 0.0F;
//		// m4x4.m4x4_float_array[9] = 0.0F;
//		// m4x4.m4x4_float_array[10] = -2.0F / (f - n);
//		// m4x4.m4x4_float_array[11] = 0.0F;
//
//		// m4x4.m4x4_float_array[12] = -(r + l) / (r - l);
//		// m4x4.m4x4_float_array[13] = -(t + b) / (t - b);
//		// m4x4.m4x4_float_array[14] = -(f + n) / (f - n);
//		// m4x4.m4x4_float_array[15] = 1.0F;
//
//		m4x4.m4x4_float_array[0] = 2.0F / (r - l);
//
//		m4x4.m4x4_float_array[5] = 2.0F / (t - b);
//
//		m4x4.m4x4_float_array[10] = -2.0F / (f - n);
//
//		m4x4.m4x4_float_array[3] = -(r + l) / (r - l);
//		m4x4.m4x4_float_array[7] = -(t + b) / (t - b);
//		m4x4.m4x4_float_array[11] = -(f + n) / (f - n);
//		m4x4.m4x4_float_array[15] = 1.0F;
//		return m4x4;
//	}

	// public void transformV4(V4 v40, V4 v41)
	// {
	//	 v41.x = v40.x * this.m4x4_float_array[0] + v40.y * this.m4x4_float_array[4] + v40.z * this.m4x4_float_array[8] + v40.w * this.m4x4_float_array[12];
	//	 v41.y = v40.x * this.m4x4_float_array[1] + v40.y * this.m4x4_float_array[5] + v40.z * this.m4x4_float_array[9] + v40.w * this.m4x4_float_array[13];
	//	 v41.z = v40.x * this.m4x4_float_array[2] + v40.y * this.m4x4_float_array[6] + v40.z * this.m4x4_float_array[10] + v40.w * this.m4x4_float_array[14];
	//	 v41.w = v40.x * this.m4x4_float_array[3] + v40.y * this.m4x4_float_array[7] + v40.z * this.m4x4_float_array[11] + v40.w * this.m4x4_float_array[15];
	// }

//	public void invert(M4x4 m4x4)
//	{
//		float b00 = m4x4.m4x4_float_array[0] * m4x4.m4x4_float_array[5] - m4x4.m4x4_float_array[1] * m4x4.m4x4_float_array[4],
//		b01 = m4x4.m4x4_float_array[0] * m4x4.m4x4_float_array[6] - m4x4.m4x4_float_array[2] * m4x4.m4x4_float_array[4],
//		b02 = m4x4.m4x4_float_array[0] * m4x4.m4x4_float_array[7] - m4x4.m4x4_float_array[3] * m4x4.m4x4_float_array[4],
//		b03 = m4x4.m4x4_float_array[1] * m4x4.m4x4_float_array[6] - m4x4.m4x4_float_array[2] * m4x4.m4x4_float_array[5],
//		b04 = m4x4.m4x4_float_array[1] * m4x4.m4x4_float_array[7] - m4x4.m4x4_float_array[3] * m4x4.m4x4_float_array[5],
//		b05 = m4x4.m4x4_float_array[2] * m4x4.m4x4_float_array[7] - m4x4.m4x4_float_array[3] * m4x4.m4x4_float_array[6],
//		b06 = m4x4.m4x4_float_array[8] * m4x4.m4x4_float_array[13] - m4x4.m4x4_float_array[9] * m4x4.m4x4_float_array[12],
//		b07 = m4x4.m4x4_float_array[8] * m4x4.m4x4_float_array[14] - m4x4.m4x4_float_array[10] * m4x4.m4x4_float_array[12],
//		b08 = m4x4.m4x4_float_array[8] * m4x4.m4x4_float_array[15] - m4x4.m4x4_float_array[11] * m4x4.m4x4_float_array[12],
//		b09 = m4x4.m4x4_float_array[9] * m4x4.m4x4_float_array[14] - m4x4.m4x4_float_array[10] * m4x4.m4x4_float_array[13],
//		b10 = m4x4.m4x4_float_array[9] * m4x4.m4x4_float_array[15] - m4x4.m4x4_float_array[11] * m4x4.m4x4_float_array[13],
//		b11 = m4x4.m4x4_float_array[10] * m4x4.m4x4_float_array[15] - m4x4.m4x4_float_array[11] * m4x4.m4x4_float_array[14],
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
//		this.m4x4_float_array[0] = (m4x4.m4x4_float_array[5] * b11 - m4x4.m4x4_float_array[6] * b10 + m4x4.m4x4_float_array[7] * b09) * det;
//		this.m4x4_float_array[1] = (m4x4.m4x4_float_array[2] * b10 - m4x4.m4x4_float_array[1] * b11 - m4x4.m4x4_float_array[3] * b09) * det;
//		this.m4x4_float_array[2] = (m4x4.m4x4_float_array[13] * b05 - m4x4.m4x4_float_array[14] * b04 + m4x4.m4x4_float_array[15] * b03) * det;
//		this.m4x4_float_array[3] = (m4x4.m4x4_float_array[10] * b04 - m4x4.m4x4_float_array[9] * b05 - m4x4.m4x4_float_array[11] * b03) * det;
//		this.m4x4_float_array[4] = (m4x4.m4x4_float_array[6] * b08 - m4x4.m4x4_float_array[4] * b11 - m4x4.m4x4_float_array[7] * b07) * det;
//		this.m4x4_float_array[5] = (m4x4.m4x4_float_array[0] * b11 - m4x4.m4x4_float_array[2] * b08 + m4x4.m4x4_float_array[3] * b07) * det;
//		this.m4x4_float_array[6] = (m4x4.m4x4_float_array[14] * b02 - m4x4.m4x4_float_array[12] * b05 - m4x4.m4x4_float_array[15] * b01) * det;
//		this.m4x4_float_array[7] = (m4x4.m4x4_float_array[8] * b05 - m4x4.m4x4_float_array[10] * b02 + m4x4.m4x4_float_array[11] * b01) * det;
//		this.m4x4_float_array[8] = (m4x4.m4x4_float_array[4] * b10 - m4x4.m4x4_float_array[5] * b08 + m4x4.m4x4_float_array[7] * b06) * det;
//		this.m4x4_float_array[9] = (m4x4.m4x4_float_array[1] * b08 - m4x4.m4x4_float_array[0] * b10 - m4x4.m4x4_float_array[3] * b06) * det;
//		this.m4x4_float_array[10] = (m4x4.m4x4_float_array[12] * b04 - m4x4.m4x4_float_array[13] * b02 + m4x4.m4x4_float_array[15] * b00) * det;
//		this.m4x4_float_array[11] = (m4x4.m4x4_float_array[9] * b02 - m4x4.m4x4_float_array[8] * b04 - m4x4.m4x4_float_array[11] * b00) * det;
//		this.m4x4_float_array[12] = (m4x4.m4x4_float_array[5] * b07 - m4x4.m4x4_float_array[4] * b09 - m4x4.m4x4_float_array[6] * b06) * det;
//		this.m4x4_float_array[13] = (m4x4.m4x4_float_array[0] * b09 - m4x4.m4x4_float_array[1] * b07 + m4x4.m4x4_float_array[2] * b06) * det;
//		this.m4x4_float_array[14] = (m4x4.m4x4_float_array[13] * b01 - m4x4.m4x4_float_array[12] * b03 - m4x4.m4x4_float_array[14] * b00) * det;
//		this.m4x4_float_array[15] = (m4x4.m4x4_float_array[8] * b03 - m4x4.m4x4_float_array[9] * b01 + m4x4.m4x4_float_array[10] * b00) * det;
//	}

//	public void m(float[] mat3, float w)
//	{
//		float x = mat3[0];
//		float y = mat3[1];
//		float z = mat3[2];
//
//		mat3[0] = this.m4x4_float_array[0] * x + this.m4x4_float_array[1] * y + this.m4x4_float_array[2] * z + this.m4x4_float_array[3] * w;
//		mat3[1] = this.m4x4_float_array[4] * x + this.m4x4_float_array[5] * y + this.m4x4_float_array[6] * z + this.m4x4_float_array[7] * w;
//		mat3[2] = this.m4x4_float_array[8] * x + this.m4x4_float_array[9] * y + this.m4x4_float_array[10] * z + this.m4x4_float_array[11] * w;
//
//		// mat3[0].setX(m[0][0] * x + m[0][1] * y + m[0][2] * z + m[0][3] * w);
//		// mat3[1].setY(m[1][0] * x + m[1][1] * y + m[1][2] * z + m[1][3] * w);
//		// mat3[2].setZ(m[2][0] * x + m[2][1] * y + m[2][2] * z + m[2][3] * w);
//		// mat3[3].setW(m[3][0] * x + m[3][1] * y + m[3][2] * z + m[3][3] * w);
//	}

//	public void add(float[] m4x4_float_array)
//	{
//		for (int i = 0; i < 16; i++)
//		{
//			m4x4_float_array[i] += this.m4x4_float_array[i];
//		}
//	}
//
//	public static void add(float[] r_m4x4_float_array, float[] w_m4x4_float_array, int r_index, int w_index)
//	{
//		for (int i = 0; i < 16; i++)
//		{
//			w_m4x4_float_array[w_index + i] += r_m4x4_float_array[r_index + i];
//		}
//	}

//	public void m(float[] m4x4_float_array)
//	{
//		float[] float_array = new float[16];
//
//		for (int i = 0; i < 4; i++)
//		{
//			for (int j = 0; j < 4; j++)
//			{
//				float_array[i * 4 + j] = this.m4x4_float_array[i * 4] * m4x4_float_array[j] + this.m4x4_float_array[i * 4 + 1] * m4x4_float_array[4 + j] + this.m4x4_float_array[i * 4 + 2] * m4x4_float_array[8 + j] + this.m4x4_float_array[i * 4 + 3] * m4x4_float_array[12 + j];
//			}
//		}
//
//		System.arraycopy(float_array, 0, this.m4x4_float_array, 0, 16);
//	}

//	public void m(float[] mat, int index)
//	{
//		float[] float_array = new float[16];
//
//		for (int i = 0; i < 4; i++)
//		{
//			for (int j = 0; j < 4; j++)
//			{
//				float_array[i * 4 + j] = this.m4x4_float_array[i * 4] * mat[j + index] + this.m4x4_float_array[i * 4 + 1] * mat[4 + j + index] + this.m4x4_float_array[i * 4 + 2] * mat[8 + j + index] + this.m4x4_float_array[i * 4 + 3] * mat[12 + j + index];
//			}
//		}
//
//		System.arraycopy(float_array, 0, mat, index, 16);
//	}

	public static void m(float[] r_m4x4_float_array, float[] w_m4x4_float_array, int r_index, int w_index)
	{
		float[] float_array = new float[16];

		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				float_array[i * 4 + j] = r_m4x4_float_array[r_index + i * 4] * w_m4x4_float_array[j + w_index] + r_m4x4_float_array[r_index + i * 4 + 1] * w_m4x4_float_array[4 + j + w_index] + r_m4x4_float_array[r_index + i * 4 + 2] * w_m4x4_float_array[8 + j + w_index] + r_m4x4_float_array[r_index + i * 4 + 3] * w_m4x4_float_array[12 + j + w_index];
			}
		}

		System.arraycopy(float_array, 0, w_m4x4_float_array, w_index, 16);
	}

//	public void cloneMat(float[] m4x4_float_array, int index)
//	{
//		System.arraycopy(this.m4x4_float_array, 0, m4x4_float_array, index, 16);
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
//		m4x4.m4x4_float_array = new float[]
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

	public static float[] mV4M4x4(float[] v4_float_array, float[] m4x4_float_array, int m4x4_index)
	{
		float[] result = new float[4];
		for (int i = 0; i < 4; i++)
		{
			float sum = 0.0F;
			for (int j = 0; j < 4; j++)
			{
				sum += v4_float_array[j] * m4x4_float_array[m4x4_index + i * 4 + j];
			}
			result[i] = sum;
		}
		return result;
	}
}
