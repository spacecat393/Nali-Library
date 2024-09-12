// package com.nali.math;
// package com.nali.objects.math;

// public class V4
// {
//	 public float x, y, z, w;

//	 public V4(float x, float y, float z, float w)
//	 {
//		 this.x = x;
//		 this.y = y;
//		 this.z = z;
//		 this.w = w;
//	 }

//	 public void cross(V4 v40, V4 v41, V4 v43)
//	 {
//		 this.x = v40.y * (v41.z * v43.w - v43.z * v41.w) - v40.z * (v41.y * v43.w - v43.y * v41.w) + v40.w * (v41.y * v43.z - v41.z * v43.y);
//		 this.y = -(v40.x * (v41.z * v43.w - v43.z * v41.w) - v40.z * (v41.x * v43.w - v43.x * v41.w) + v40.w * (v41.x * v43.z - v43.x * v41.z));
//		 this.z = v40.x * (v41.y * v43.w - v43.y * v41.w) - v40.y * (v41.x * v43.w - v43.x * v41.w) + v40.w * (v41.x * v43.y - v43.x * v41.y);
//		 this.w = -(v40.x * (v41.y * v43.z - v43.y * v41.z) - v40.y * (v41.x * v43.z - v43.x * v41.z) + v40.z * (v41.x * v43.y - v43.x * v41.y));
//	 }

//	 public V4 cloneV4()
//	 {
//		 return new V4(this.x, this.y, this.z, this.w);
//	 }
// }
