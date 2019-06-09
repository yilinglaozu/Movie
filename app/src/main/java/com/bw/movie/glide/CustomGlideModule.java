package com.bw.movie.glide;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule
public final class CustomGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
    //        设置缓存大小为20mb
        int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20mb
    //        设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
        Log.i("ljy",context.getCacheDir().getAbsolutePath());
    //        根据SD卡是否可用选择是在内部缓存还是SD卡缓存
        //去文件管理器android/data/报名/自定义的文件名/缓存文件
        ExternalPreferredCacheDiskCacheFactory cacheDiskCacheFactory = new ExternalPreferredCacheDiskCacheFactory(context, "ShopCar", memoryCacheSizeBytes);
        builder.setDiskCache(cacheDiskCacheFactory);
    }
    //    针对V4用户可以提升速度
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}