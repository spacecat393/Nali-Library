// package com.nali.math;
// package com.nali.objects.math;

// public class V2
// {
//	 public float x, y;

//	 public V2(float x, float y)
//	 {
//		 this.x = x;
//		 this.y = y;
//	 }

//	 public static V2 linesIntersection(V2 v20, V2 v21, V2 v22, V2 v23)
//	 {
//		 float a1 = v21.y - v20.y;
//		 float b1 = v20.x - v21.x;
//		 float c1 = a1 * (v20.x) + b1 * (v20.y);

//		 float a2 = v23.y - v22.y;
//		 float b2 = v22.x - v23.x;
//		 float c2 = a2 * (v22.x) + b2 * (v22.y);

//		 float determinant = a1 * b2 - a2 * b1;

//		 if (determinant == 0)
//		 {
//			 return new V2(Float.MAX_VALUE, Float.MAX_VALUE);
//		 }
//		 else
//		 {
//			 float x = (b2 * c1 - b1 * c2) / determinant;
//			 float y = (a1 * c2 - a2 * c1) / determinant;

//			 return new V2(x, y);
//		 }
//	 }

//	 public float det(float a, float b, float c, float d)
//	 {
//		 return a*d - b*c;
//	 }

//	 public boolean intersect(V3 m, V3 n)
//	 {
//		 float zn = this.det(m.x, m.y, n.x, n.y);

//		 if (Math.abs(zn) < 1e-9)
//		 {
//			 return false;
//		 }

//		 this.x = -this.det(m.z, m.y, n.z, n.y) / zn;
//		 this.y = -this.det(m.x, m.z, n.x, n.z) / zn;

//		 return true;
//	 }
// }
