package com.qf.oom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * 图片的二次采样
 *
 * BitmapFactory
 *
 */
public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
    }


    //图片的所占大小 图片的宽度 * 图片的高度 * 一个像素所占的字节数
    public void btnclick(View v){
        String path = Environment.getExternalStorageDirectory() + "/Pictures/oom.jpg";
        Bitmap bitmap1 = BitmapFactory.decodeFile(path);
//        iv.setImageBitmap(bitmap);
        Bitmap bitmap2 = cyBitmap(path, 400, 300);

        Log.d("print", "未采样图片：" + bitmap1.getByteCount() + "   采样图片：" + bitmap2.getWidth() * bitmap2.getHeight() * 4);


        iv.setImageBitmap(bitmap2);

    }

    /**
     * 二次采样的工具方法
     *
     * Bitmap.Config.ARGB_8888 一个像素占用4个字节（默认情况）
     * Bitmap.Config.RGB_565 一个像素占用2个字节
     * Bitmap.Config.ARGB_4444 一个像素占用2个字节
     * Bitmap.Config.ALPHA_8 一个像素占用1个字节，当设置这个属性时系统默认采用ARGB_8888
     *
     *
     * @param path
     * @return
     */
    public Bitmap cyBitmap(String path, int newWidth, int newHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//将该属性设置为true以后，加载图片则只会加载图片的边缘部分，这样图片就不会占用内存而且我们还能获得图片的宽高
        BitmapFactory.decodeFile(path, options);
        //获得图片的宽高
        int oldWidth = options.outWidth;
        int oldHeight = options.outHeight;
        Log.d("print", "获得图片的宽高：" + oldHeight + "  " + oldWidth);

        //计算二次采样的压缩比率
        //宽度的比率
        int perW = oldWidth/newWidth;
        //高度的比率
        int perH = oldHeight/newHeight;

        int per = perW > perH ? perW : perH;

        //设置二次采样的压缩比率
        options.inSampleSize = per;
        //设置像素质量
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;//减少图片的内存一半的开销
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

}
