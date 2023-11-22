// package com.nali.math;
// package com.nali.objects.math;

// public class BoundingBox
// {
//     public float min_x;
//     public float min_y;
//     public float min_z;
//     public float max_x;
//     public float max_y;
//     public float max_z;

//     public BoundingBox(float x1, float y1, float z1, float x2, float y2, float z2)
//     {
//         this.min_x = Math.min(x1, x2);
//         this.min_y = Math.min(y1, y2);
//         this.min_z = Math.min(z1, z2);
//         this.max_x = Math.max(x1, x2);
//         this.max_y = Math.max(y1, y2);
//         this.max_z = Math.max(z1, z2);
//     }

//     public boolean intersects(BoundingBox box)
//     {
//         return this.intersects(box.min_x, box.min_y, box.min_z, box.max_x, box.max_y, box.max_z);
//     }

//     public boolean intersects(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z)
//     {
//         return this.min_x < max_x && this.max_x > min_x && this.min_y < max_y && this.max_y > min_y && this.min_z < max_z && this.max_z > min_z;
//     }
// }
