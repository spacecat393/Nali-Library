//package com.nali.system;
//
//import com.nali.system.file.FileDataReader;
//import com.nali.system.opengl.memo.Memo3DS;
//import com.nali.system.opengl.memo.client.MemoAnimation;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static com.nali.Nali.I;
//import static com.nali.Nali.ID;
//
//public class BothLoader
//{
//    public Map<String, Class> data_class_map = new HashMap();
////    public List<Class> data_class_list;
//
//    public List<Memo3DS> memo3d_list = new ArrayList();
//    public List<MemoAnimation> memoanimation_list = new ArrayList();
//
//    public void pairModel()
//    {
////        this.data_class_list = Reflect.getClasses("com.nali.list.data");
//        List<Class> data_class_list = Reflect.getClasses("com.nali.list.data");
//
//        List<String> data_string_list = new ArrayList();
////        for (Class data_class : this.data_class_list)
//        for (Class data_class : data_class_list)
//        {
////            data_string_list.add(data_class.getSimpleName().toLowerCase());
//            this.data_class_map.put(data_class.getSimpleName().toLowerCase(), data_class);
//        }
//
//        File[] file_array = new File(ID).listFiles();
//
//        for (File file : file_array)
//        {
//            File model_file = new File(file.getPath() + "/ModelSList");
//            if (model_file.isFile())
//            {
//                String[][] string_2d_array = FileDataReader.getMixXStringArray(model_file.toPath());
//                try
//                {
//                    this.data_class_map.get(file.getName()).getField("MODEL_S_STEP").set(null, this.memo3d_list.size());
//                }
//                catch (IllegalAccessException | NoSuchFieldException e)
//                {
//                    I.error(e);
//                }
//
//                for (String[] string_array : string_2d_array)
//                {
//                    this.memo3d_list.add(new Memo3DS(string_array, file.getPath()));
//                }
//            }
//        }
//
//        for (File file : file_array)
//        {
//            File model_file = new File(file.getPath() + "/AnimationList");
//            if (model_file.isFile())
//            {
////                String name_file = file.getName();
////                for (int i = 0; i < this.data_string_list.size(); ++i)
////                {
////                    if (this.data_string_list.get(i).contains(name_file))
////                    {
//                String[][] string_2d_array = FileDataReader.getMixXStringArray(model_file.toPath());
//                try
//                {
////                    this.data_class_list.get(i).getField("ANIMATION_STEP").set(null, this.openglskinningmemo_list.size());
//                    data_class_map.get(file.getName()).getField("ANIMATION_STEP").set(null, this.stores.memoanimation_list.size());
//                }
//                catch (IllegalAccessException | NoSuchFieldException e)
//                {
//                    I.error(e);
//                }
//
//                for (String[] string_array : string_2d_array)
//                {
//                    this.stores.memoanimation_list.add(new MemoAnimation(string_array, file.getPath()));
//                }
////                        break;
////                    }
////                }
//            }
//        }
//    }
//
//    public void clear()
//    {
////        this.data_class_list = null;
//        this.data_class_map = null;
//    }
//}
