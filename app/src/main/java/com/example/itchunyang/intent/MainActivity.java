package com.example.itchunyang.intent;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void component(View view) {

        //第一个参数是包名，也是主配置文件Manifest里设置好的包名
        //第二个是类名，要带上包名

        //启动系统app,没有任何问题
        //ComponentName componentName = new ComponentName("com.android.gallery3d","com.android.gallery3d.app.Gallery");

        //启动自己写的app,会报错java.lang.SecurityException: Permission Denial.
        //解决方式有2个  1.任意声明个action在里面。2.添加android:exported="true"标签
        ComponentName componentName = new ComponentName("com.test.myapplication","com.test.myapplication.FindActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
    }


    //总结: 目标的Action Category Data 要同时完全包含发送方的,每一项都可以多,但是不能少
    public void action(View view) {
        //目标Activity没有声明category会报错,也可以通过Action启动另外app的Activity
        Intent intent = new Intent("android.intent.action.LOGIN");
        startActivity(intent);
    }

    //Category属性用于指定当前动作（Action）被执行的环境
    public void category(View view) {
        //默认的Intent发送时会加android.intent.category.DEFAULT
        //目标Activity声明的Category必须大于等于intent里面的Category
        Intent intent = new Intent("android.intent.action.SET");
        intent.addCategory("android.intent.category.CUSTOM_CATEGORY");
        startActivity(intent);
    }

    //Data通常是URI格式定义的操作数据
    /**
     * URI包含
     * scheme://host:port/path (host + port = authority)
     * data属性解析：android:scheme、android:host、android:port、android:path、android:mimeType前四个属性构成了URI的组成部分,mimeType设置了数据的类型
     *
     *  setType 会把mimeType置为null；
     *  setData 会把data置为null；
     *  setDataAndType
     *
     */
    public void data(View view) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("http://www.hao123.com"));
//        startActivity(intent);

//        Uri uri = Uri.parse("file:///sdcard/Pictures/1017.jpg");
//        Intent intent = new Intent("android.intent.action.VIEW_PICTURE");
//        intent.setData(uri);
//        startActivity(intent);

        Uri uri = Uri.parse("http://img.ycwb.com/news/attachement/jpg/site2/20131117/002197acf03913f271043c.jpg");
        Intent intent = new Intent("android.intent.action.VIEW_PICTURE");
        intent.setDataAndType(uri,"text/plain");
        startActivity(intent);
    }

    public void extras(View view) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.test.myapplication","com.test.myapplication.ExtrasActivity"));
        intent.putExtra("age",100);
        intent.putExtra("name","小李飞刀");

        Bundle bundle = new Bundle();
        bundle.putChar("char",'D');
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //期望这个意图的运行模式
    public void flags(View view) {
        Intent intent = new Intent("android.intent.action.FLAG");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /*************************************************************/

    /**
     * android.intent.category.ALTERNATIVE #
     android.intent.category.APP_BROWSER #启动浏览器
     android.intent.category.APP_CALCULATOR #计算器
     android.intent.category.APP_CALENDAR #日历
     android.intent.category.APP_CONTACTS #联系人
     android.intent.category.APP_EMAIL  #email
     android.intent.category.APP_GALLERY #相册
     android.intent.category.APP_MAPS #google maps
     android.intent.category.APP_MARKET #google play
     android.intent.category.APP_MESSAGING #消息
     android.intent.category.APP_MUSIC #音乐
     android.intent.category.BROWSABLE #在浏览器中可以调用该app
     android.intent.category.CAR_DOCK
     android.intent.category.CAR_MODE
     android.intent.category.DEFAULT #如果没有设置其他category，定义为DEFAULT，允许在没有任何其他intent filter匹配的情况下可以调用该程序
     android.intent.category.DESK_DOCK
     android.intent.category.DEVELOPMENT_PREFERENCE
     android.intent.category.EMBED
     android.intent.category.HE_DESK_DOCK
     android.intent.category.HOME #将作为Launcher的选择项启动
     android.intent.category.INFO
     android.intent.category.LAUNCHER #作为主菜单启动，即在主菜单中会出现
     android.intent.category.LE_DESK_DOCK
     android.intent.category.MONKEY
     android.intent.category.OPENABLE
     android.intent.category.PREFERENCE
     android.intent.category.SELECTED_ALTERNATIVE
     android.intent.category.TAB #作为TabActivity的一个Tab使用
     */

    public void calc(View view) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_CALCULATOR");
        startActivity(intent);
    }

    public void web(View view) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_BROWSER");
        startActivity(intent);
    }

    public void calendar(View view) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_CALENDAR");
        startActivity(intent);
    }

    public void gallery(View view) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_GALLERY");
        startActivity(intent);
    }

}
