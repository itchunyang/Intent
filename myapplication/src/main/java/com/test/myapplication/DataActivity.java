package com.test.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * path 用来匹配完整的路径，如：http://example.com/blog/abc.html，这里将 path 设置为 /blog/abc.html 才能够进行匹配；
 * pathPrefix 用来匹配路径的开头部分，拿上面的 Uri 来说，这里将 pathPrefix 设置为 /blog 就能进行匹配了；
 * pathPattern 用表达式来匹配整个路径，这里需要说下匹配符号与转义。
 */

public class DataActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);

        Intent intent = getIntent();
        Uri uri = intent.getData();

        String scheme = uri.getScheme();
        String authority = uri.getAuthority();
        String host = uri.getHost();
        String port = String.valueOf(uri.getPort());
        String path = uri.getPath();

        tv.setText("URI="+uri.toString());
        tv.append("scheme=" + scheme + "\n" + "authority=" + authority + "\n" + "host=" + host + "\n" + "port=" + port + "\n" + "path=" + path);


        if (uri.getScheme().startsWith("file")) {
            Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath());
            if (bitmap != null)
                iv.setImageBitmap(bitmap);
            else
                Toast.makeText(this, "没有找到图片 " + uri.getPath(), Toast.LENGTH_LONG).show();
        } else if (uri.getScheme().startsWith("http")) {
            downImage(uri.toString());
        }
    }

    public void downImage(final String address){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(address);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream is = connection.getInputStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(is);
                    if(bitmap != null)
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iv.setImageBitmap(bitmap);
                            }
                        });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
