// package com.nali.math;
// package com.nali.objects.math;

// public class V3
// {
//	 public float x, y, z;

//	 public V3(float x, float y, float z)
//	 {
//		 this.x = x;
//		 this.y = y;
//		 this.z = z;
//	 }

//	 public void add(V3 v3)
//	 {
//		 this.x += v3.x;
//		 this.y += v3.y;
//		 this.z += v3.z;
//	 }

//	 public void m(float v)
//	 {
//		 this.x *= v;
//		 this.y *= v;
//		 this.z *= v;
//	 }

//	 public void normalize(float length)
//	 {
//		 this.x /= length;
//		 this.y /= length;
//		 this.z /= length;
//	 }

//	 public void cross(V3 v3)
//	 {
//		 this.x = this.y * v3.z - this.z * v3.y;
//		 this.y = this.z * v3.x - this.x * v3.z;
//		 this.z = this.x * v3.y - this.y * v3.x;
//	 }

//	 public float dot(V3 v3)
//	 {
//		 return this.x * v3.x + this.y * v3.y + this.z * v3.z;
//	 }

//	 public float length()
//	 {
//		 return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
//	 }

//	 public V3 cloneV3()
//	 {
//		 return new V3(this.x, this.y, this.z);
//	 }
// }
