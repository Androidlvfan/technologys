package com.wd.tech.data.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.mob.MobSDK;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.wd.tech.gen.DaoMaster;
import com.wd.tech.gen.DaoSession;

import java.util.HashSet;
import java.util.Set;

import okhttp3.OkHttpClient;

/**
 * @author Wyg
 * @description DOTO
 * @date :2019/5/10 16:33
 * @fileName:App
 * @packageName:com.wd.tech.dimensionalitytechnology.data.app
 */
public class App extends Application {

    private static App app;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        MobSDK.init(this);


        Set<RequestListener> requestListeners = new HashSet<>();
        requestListeners.add(new RequestLoggingListener());
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(getCacheDir())
                .setMaxCacheSize(20 * 1024 * 1024)
                .build();
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                //开启向下采样 加载图片更强大
                .setDownsampleEnabled(true)
                //如果不是超高清图片  不要8888
                .setBitmapsConfig(Bitmap.Config.ARGB_4444)
                .setRequestListeners(requestListeners)
                .build();
        FLog.setMinimumLoggingLevel(FLog.VERBOSE);
        Fresco.initialize(this,imagePipelineConfig);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).imageDownloader(
                new BaseImageDownloader(this, 60 * 1000, 60 * 1000)) // connectTimeout超时时间
                .build();
        ImageLoader.getInstance().init(config);


        //GreenDao数据库
        //参数1:上下文
        //参数2:数据库名称
        //参数3:游标工厂类
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplicationContext(), "Ls", null);
        //获取DataBase对象
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        //创建DaoMaster对象 所需DataBase对象
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        //创建Daosession对象
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static Context getAppContext(){
        return app;
    }
}
