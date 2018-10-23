package test.message.com.messagetestdemo.utils;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;

import test.message.com.messagetestdemo.R;


/**
 * @类名称： ${CLASS_NAME}
 * @创建人： Mr_ladeng (zbl704@yeah.net)
 * @创建时间： ${DATA} 下午3:34
 * @类描述：Glide 工具类
 * Google推荐的图片加载库，专注于流畅的滚动
 * Glide 不光接受Context，还接受Activity 和 Fragment ,图片加载会和Activity/Fragment的生命周期保持一致 在onPause（）暂停加载，onResume（）恢复加载
 * 支持GIF格式图片加载
 *
 *
 * 使用方法：
 * GlideUtils.getInstance().LoadImg( context,  path,  imageView);
 *
 */
public class GlideUtils {
    private static GlideUtils instance;

    public static GlideUtils getInstance() {
        if (instance == null) {
            synchronized (GlideUtils.class) {
                if (instance == null) {
                    instance = new GlideUtils();
                }
            }
        }
        return instance;
    }
    //  with(Context context). 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制。
    //  with(Activity activity).使用Activity作为上下文，Glide的请求会受到Activity生命周期控制。
    //  with(FragmentActivity activity).Glide的请求会受到FragmentActivity生命周期控制。
    //  with(android.app.Fragment fragment).Glide的请求会受到Fragment 生命周期控制。
    //  with(android.support.v4.app.Fragment fragment).Glide的请求会受到Fragment生命周期控制。
    //-----------------------------
    //  Glide基本可以load任何可以拿到的媒体资源，如：
    //  load SD卡资源：load("file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg")
    //  load assets资源：load("file:///android_asset/f003.gif")
    //  load raw资源：load("android.resource://com.frank.glide/raw/raw_1")或load("android.resource://com.frank.glide/raw/"+R.raw.raw_1)
    //  load drawable资源：load("android.resource://com.frank.glide/drawable/news")或load("android.resource://com.frank.glide/drawable/"+R.drawable.news)或load(R.drawable.loading)
    //  load ContentProvider资源：load("content://media/external/images/media/139469")
    //  load http资源：load("http://img.my.csdn.net/uploads/201508/05/1438760757_3588.jpg")
    //  load https资源：load("https://img.alicdn.com/tps/TB1uyhoMpXXXXcLXVXXXXXXXXXX-476-538.jpg_240x5000q50.jpg_.webp")
    //  当然，load不限于String类型，还可以：
    //  load(Uri uri)，load(File file)，load(Integer resourceId)，load(URL url)，load(byte[] model, final String id)，load(byte[] model)，load(T model)。
    //  而且可以使用自己的ModelLoader进行资源加载：
    //  using(ModelLoader<A, T> modelLoader, Class<T> dataClass)，using(final StreamModelLoader<T> modelLoader)，using(StreamByteArrayLoader modelLoader)，using(final FileDescriptorModelLoader<T> modelLoader)。
    //  返回RequestBuilder实例
    //--------------------------------------
    //  * thumbnail(float sizeMultiplier). 请求给定系数的缩略图。如果缩略图比全尺寸图先加载完，
    //        就显示缩略图，否则就不显示。系数sizeMultiplier必须在(0,1)之间，可以递归调用该方法。

    //  * sizeMultiplier(float sizeMultiplier). 在加载资源之前给Target大小设置系数。

    //  * skipMemoryCache(boolean skip). 设置是否跳过内存缓存，但不保证一定不被缓存
    //     （比如请求已经在加载资源且没设置跳过内存缓存，这个资源就会被缓存在内存中）。
    //  *  diskCacheStrategy(DiskCacheStrategy strategy).设置缓存策略。
    //     DiskCacheStrategy.SOURCE：缓存原始数据，
    //     DiskCacheStrategy.RESULT：缓存变换修改后的资源数据，
    //     DiskCacheStrategy.NONE：什么都不缓存，
    //     DiskCacheStrategy.ALL：缓存所有图片  默认
    //     默认采用DiskCacheStrategy.RESULT策略，对于download only操作要使用DiskCacheStrategy.SOURCE。

    //  * priority(Priority priority). 指定加载的优先级，优先级越高越优先加载，但不保证所有图片都按序加载。
    //       枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW。默认为Priority.NORMAL。
    //  * crossFade(5000) //设置淡入淡出效果，默认300ms，可以传参
    //  * dontAnimate(). 移除所有的动画。
    //  * animate(int animationId). 在异步加载资源完成时会执行该动画。
    //  * animate(ViewPropertyAnimation.Animator animator). 在异步加载资源完成时会执行该动画。
    //  * placeholder(int resourceId). 设置资源加载过程中的占位Drawable。
    //  * placeholder(Drawable drawable). 设置资源加载过程中的占位Drawable。

    //  * fallback(int resourceId). 设置model为空时要显示的Drawable。如果没设置fallback，
    //    model为空时将显示error的Drawable，如果error的Drawable也没设置，就显示placeholder的Drawable。
    //  * fallback(Drawable drawable).设置model为空时显示的Drawable。
    //  * error(int resourceId).设置load失败时显示的Drawable。
    //  * error(Drawable drawable).设置load失败时显示的Drawable。

    //  * Glide支持两种图片缩放形式，CenterCrop 和 FitCenter
    //    CenterCrop：等比例缩放图片， 直到图片的宽高都大于等于ImageView的宽度，然后截取中间的显示。
    //    FitCenter：等比例缩放图片，宽或者是高等于ImageView的宽或者是高。

    //  * 当列表在滑动的时候，调用pauseRequests()取消请求，滑动停止时，调用resumeRequests()恢复请求

    //  * listener(RequestListener<? super ModelType, TranscodeType> requestListener).
    //        监听资源加载的请求状态，可以使用两个回调：
    //     onResourceReady(R resource, T model, Target<R> target, boolean isFromMemoryCache, boolean isFirstResource)
    //       和onException(Exception e, T model, Target&lt;R&gt; target, boolean isFirstResource)，
    //       但不要每次请求都使用新的监听器，要避免不必要的内存申请，可以使用单例进行统一的异常监听和处理。
    //   clear() 清除掉所有的图片加载请求
    //   override(int width, int height). 重新设置Target的宽高值（单位为pixel）。
    //   into(Y target).设置资源将被加载到的Target。
    //   into(ImageView view). 设置资源将被加载到的ImageView。取消该ImageView之前所有的加载并释放资源。
    //   into(int width, int height). 后台线程加载时要加载资源的宽高值（单位为pixel）。
    //   preload(int width, int height). 预加载resource到缓存中（单位为pixel）。
    //   asBitmap(). 无论资源是不是gif动画，都作为Bitmap对待。如果是gif动画会停在第一帧。
    //   asGif().把资源作为GifDrawable对待。如果资源不是gif动画将会失败，会回调.error()。
    //-------------------------------------------------------------------------------
    //    显示本地视频
    //    String filePath = "/storage/emulated/0/Pictures/example_video.mp4";
    //    Glide.with( context )
    //         .load( Uri.fromFile( new File( filePath ) ) )
    //         .into( imageViewGifAsBitmap );
    //
    //      new GlideBuilder(context).setDecodeFormat(DecodeFormat.ALWAYS_ARGB_8888);//默认情况下Glide使用RGB_565,因为它只需要两个字节,比使用高质量和系统默认ARGB_8888节省一半的内存。然而RGB_565在某些图像条带会有问题，也不支持透明度。
    /**
     * 加载bitmap，如果是GIF则显示第一帧
     */
    public static String LOAD_BITMAP = "GLIDEUTILS_GLIDE_LOAD_BITMAP";
    /**
     * 加载gif动画
     */
    public static String LOAD_GIF = "GLIDEUTILS_GLIDE_LOAD_GIF";
    /**
     * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
     * 使用activity 会受到Activity生命周期控制
     * 使用FragmentActivity 会受到FragmentActivity生命周期控制
     *
     * @param context
     * @param path
     * @param imageView
     * @param placeid     占位
     * @param errorid     错误
     */
    public void LoadGif(Context context, String path, ImageView imageView, int placeid, int errorid) {
        Glide.with(context).load(path).asGif().placeholder(placeid).error(errorid).crossFade().into(imageView);
    }

    /**
     * 有默认站位loading
     * @param context
     * @param path
     * @param imageView
     */
    public void LoadGif(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).asGif().placeholder(R.drawable.header).error(R.drawable.header).crossFade().into(imageView);
    }

    /**
     * 有默认站位loading
     * @param context
     * @param path
     * @param imageView
     */
    public void LoadGif2(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).asGif().into(imageView);
    }

    /**
     * 加载gif/jpg等图 常用方法，自定义loading
     * @param context
     * @param path
     * @param imageView
     */
    public void Load(Context context, String path, ImageView imageView , int drawable  ) {
        Glide.with(context).load(path)
                .placeholder(drawable)
                .error(drawable)
                .crossFade()
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(imageView));
    }
    /**
     * 加载gif/jpg等图 不加载默认图
     * @param context
     * @param path
     * @param imageView
     */
    public void LoadNoDefault(Context context, String path, ImageView imageView , int drawable ) {
        Glide.with(context).load(path)
                .crossFade()
                .error(drawable)
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(imageView));
    }

    /**
     * 加载gif/jpg等图 常用方法，自定义loading
     * @param context
     * @param path
     * @param imageView
     */
    public void Load(Context context, String path, ImageView imageView ) {
        Glide.with(context).load(path)
                .crossFade()
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(imageView));
    }

    /**
     * 加载gif/jpg等图 常用方法，自定义loading,自定义scaletype
     * @param context
     * @param path
     * @param imageView
     */
    public void LoadDefaltFitXY(Context context, String path, final ImageView imageView , int drawable ) {
        if (imageView == null) {
            return;
        }

        Glide.with(context).load(path)
                .placeholder(drawable)
                .error(drawable)
                .crossFade()
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onStart() {
                        super.onStart();
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                    }

                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        //在这里添加一些图片加载完成的操作
                    }
                });

    }


    /**
     * 加载gif/jpg等图 常用方法，有loading progress
     * @param context
     * @param path
     * @param imageView
     */
    public void loadImg(Context context, String path, final ImageView imageView) {
        if(imageView==null){
            return;
        }
        int drawable = R.drawable.header ;

        GlideDrawableImageViewTarget into = Glide.with(context).load(path)
                .placeholder(drawable)
                .error(drawable)
                .crossFade()
                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .thumbnail(0.5f)
                .into(new GlideDrawableImageViewTarget(imageView){

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    public Bitmap getBitmapFromPath(Context context, String url){
        Bitmap mBitmap = null;
        try {
             mBitmap  = Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
       return  mBitmap;
    }
    /**
     * 加载gif/jpg等图 常用方法，有loading progress
     * @param context
     * @param path
     * @param imageView
     * 加载默认图铺满布局
     */
    public void loadImgFIT_XY(Context context, String path, final ImageView imageView) {
        if (imageView == null) {
            return;
        }
        int drawable = R.drawable.header;

        Glide.with(context).load(path)
                .placeholder(drawable)
                .error(drawable)
                .crossFade()
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onStart() {
                        super.onStart();
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                    }

                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        //在这里添加一些图片加载完成的操作
                    }
                });
    }

    /**
     * 加载gif/jpg等图 常用方法，有loading progress
     *
     * @param context
     * @param path
     * @param imageView 加载没有默认图铺满布局
     */
    public void loadImgFIT_XY_NoDefate(Context context, String path, final ImageView imageView) {
        if (imageView == null) {
            return;
        }

        Glide.with(context).load(path)
                .crossFade()
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onStart() {
                        super.onStart();
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                    }

                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        //在这里添加一些图片加载完成的操作
                    }
                });
    }

    /**
     * 加载gif/jpg等图 常用方法，有loading progress
     * @param context
     * @param path
     * @param imageView
     * 加载默认图 自动义 ScaleType
     * ImageView.ScaleType fitXy = ImageView.ScaleType.FIT_XY;
     */
    public void loadImgScaleType(Context context, String path, final ImageView imageView , final ImageView.ScaleType scaleType) {
        Glide.with(context).load(path)
                .crossFade()
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onStart() {
                        super.onStart();
                        if (imageView.getScaleType() != scaleType) {
                            imageView.setScaleType(scaleType);
                        }
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        if (imageView.getScaleType() != scaleType) {
                            imageView.setScaleType(scaleType);
                        }
                    }

                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        //在这里添加一些图片加载完成的操作
                        if (imageView.getScaleType() != scaleType) {
                            imageView.setScaleType(scaleType);
                        }
                    }
                });
    }



    /**
     * 加载gif/jpg等图 常用方法，有loading progress
     * @param context
     * @param path
     * @param imageView
     */
    public void loadImgListener(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path)
//                .placeholder(R.drawable.pb_bg)
//                .error(R.drawable.pb_bg)
                .crossFade()
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(imageView){
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        //在这里添加一些图片加载完成的操作
                    }
                });
    }



    /**
     * 下载图片到本地
     * @param context
     * @param url
     * @return file
     */
    public File download(Context context, String url) {
        try {
            return Glide.with(context).load(url)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();// needs to be called on background thread
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null ;
        }

    }


    /**
     * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
     * 使用activity 会受到Activity生命周期控制
     * 使用FragmentActivity 会受到FragmentActivity生命周期控制
     *
     * @param context
     * @param path
     * @param imageView
     * @param placeid     占位
     * @param errorid     错误
     * @param bitmapOrgif 加载普通图片 或者GIF图片 ，GIF图片设置bitmap显示第一帧
     */
    public void LoadContextBitmap(Context context, String path, ImageView imageView, int placeid, int errorid, String bitmapOrgif) {
        if (bitmapOrgif == null || bitmapOrgif.equals(LOAD_BITMAP)) {
            Glide.with(context).load(path).placeholder(placeid).error(errorid).crossFade().into(imageView);
        } else if (bitmapOrgif.equals(LOAD_GIF)) {
            Glide.with(context).load(path).asGif().crossFade().into(imageView);
        }
    }

    /**
     * Glide请求图片，会受到Fragment 生命周期控制。
     *
     * @param fragment
     * @param path
     * @param imageView
     * @param placeid
     * @param errorid
     * @param bitmapOrgif 加载普通图片 或者GIF图片 ，GIF图片设置bitmap显示第一帧
     */
    public void LoadFragmentBitmap(android.app.Fragment fragment, String path, ImageView imageView, int placeid, int errorid, String bitmapOrgif) {
        if (bitmapOrgif == null || bitmapOrgif.equals(LOAD_BITMAP)) {
            Glide.with(fragment).load(path).placeholder(placeid).error(errorid).crossFade().into(imageView);
        } else if (bitmapOrgif.equals(LOAD_GIF)) {
            Glide.with(fragment).load(path).asGif().crossFade().into(imageView);
        }
    }

    /**
     * Glide请求图片，会受到support.v4.app.Fragment生命周期控制。
     *
     * @param fragment
     * @param path
     * @param imageView
     * @param placeid
     * @param errorid
     * @param bitmapOrgif 加载普通图片 或者GIF图片 ，GIF图片设置bitmap显示第一帧
     */
    public void LoadSupportv4FragmentBitmap(android.support.v4.app.Fragment fragment, String path, ImageView imageView, int placeid, int errorid, String bitmapOrgif) {
        if (bitmapOrgif == null || bitmapOrgif.equals(LOAD_BITMAP)) {
            Glide.with(fragment).load(path).placeholder(placeid).error(errorid).crossFade().into(imageView);
        } else if (bitmapOrgif.equals(LOAD_GIF)) {
            Glide.with(fragment).load(path).asGif().crossFade().into(imageView);
        }
    }
    //---------------------圆形图片-------------------

    /**
     * 加载设置圆形图片
     * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
     * <BR/>使用activity 会受到Activity生命周期控制
     * <BR/>使用FragmentActivity 会受到FragmentActivity生命周期控制
     *
     * @param context
     * @param path
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadCircleBitmap(Context context, String path,
                                 ImageView imageView ,
                                 int drawable , int roundradius) {
        if (roundradius < 0) {
            Glide.with(context).load(path)
                    .placeholder(drawable).error(drawable)
                    .bitmapTransform(new GlideRoundTransform(context)).into(new ImageViewTarget<GlideDrawable>(imageView) {
                @Override
                protected void setResource(GlideDrawable resource) {

                }
            });
        } else {
            Glide.with(context).load(path)
                    .placeholder(drawable).error(drawable)
                    .bitmapTransform(new GlideRoundTransform(context, roundradius)).into(new ImageViewTarget<GlideDrawable>(imageView) {
                @Override
                protected void setResource(GlideDrawable resource) {

                }
            });
        }
    }


    /**
     * Glide请求图片设置圆形，会受到android.app.Fragment生命周期控制
     *
     * @param fragment
     * @param path
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadfragmentCircleBitmap(android.app.Fragment fragment, String path, ImageView imageView) {
        Glide.with(fragment).load(path).bitmapTransform(new GlideCircleTransform(fragment.getActivity())).into(imageView);
    }

    /**
     * Glide请求图片设置圆形，会受到android.support.v4.app.Fragment生命周期控制
     *
     * @param fragment
     * @param path
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadSupportv4FragmentCircleBitmap(android.support.v4.app.Fragment fragment, String path, ImageView imageView) {
        Glide.with(fragment).load(path).bitmapTransform(new GlideCircleTransform(fragment.getActivity())).into(imageView);
    }
    //-----------------------圆角图片----------------------

    /**
     * 加载设置圆角图片
     * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
     * <BR/>使用activity 会受到Activity生命周期控制
     * <BR/>使用FragmentActivity 会受到FragmentActivity生命周期控制
     *
     * @param context
     * @param path
     * @param imageView
     * @param roundradius 圆角大小（>0）
     */
    @SuppressWarnings("unchecked")
    public void LoadContextRoundBitmap(Context context, String path, ImageView imageView, int roundradius) {
        if (roundradius < 0) {
            Glide.with(context).load(path).bitmapTransform(new GlideRoundTransform(context)).into(imageView);
        } else {
            Glide.with(context).load(path).bitmapTransform(new GlideRoundTransform(context, roundradius)).into(imageView);
        }
    }

    /**
     * Glide请求图片设置圆角，会受到android.app.Fragment生命周期控制
     *
     * @param fragment
     * @param path
     * @param imageView
     * @param roundradius
     */
    @SuppressWarnings("unchecked")
    public void LoadfragmentRoundBitmap(android.app.Fragment fragment, String path, ImageView imageView, int roundradius) {
        if (roundradius < 0) {
            Glide.with(fragment).load(path).bitmapTransform(new GlideRoundTransform(fragment.getActivity())).into(imageView);
        } else {
            Glide.with(fragment).load(path).bitmapTransform(new GlideRoundTransform(fragment.getActivity(), roundradius)).into(imageView);
        }
    }

    /**
     * Glide请求图片设置圆角，会受到android.support.v4.app.Fragment生命周期控制
     *
     * @param fragment
     * @param path
     * @param imageView
     * @param roundradius
     */
    @SuppressWarnings("unchecked")
    public void LoadSupportv4FragmentRoundBitmap(android.support.v4.app.Fragment fragment, String path, ImageView imageView, int roundradius) {
        if (roundradius < 0) {
            Glide.with(fragment).load(path).bitmapTransform(new GlideRoundTransform(fragment.getActivity())).into(imageView);
        } else {
            Glide.with(fragment).load(path).bitmapTransform(new GlideRoundTransform(fragment.getActivity(), roundradius)).into(imageView);
        }
    }
    //-------------------------------------------------

    /**
     * Glide 加载模糊图片
     * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
     * <BR/>使用activity 会受到Activity生命周期控制
     * <BR/>使用FragmentActivity 会受到FragmentActivity生命周期控制
     *
     * @param context
     * @param path
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadContextBlurBitmap(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).bitmapTransform(new BlurTransformation(context)).into(imageView);
    }

    /**
     * Glide 加载模糊图片 会受到Fragment生命周期控制
     *
     * @param fragment
     * @param path
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadFragmentBlurBitmap(android.app.Fragment fragment, String path, ImageView imageView) {
        Glide.with(fragment).load(path).bitmapTransform(new BlurTransformation(fragment.getActivity())).into(imageView);
    }

    /**
     * Glide 加载模糊图片 会受到support.v4.app.Fragment生命周期控制
     *
     * @param fragment
     * @param path
     * @param imageView
     */
    @SuppressWarnings("unchecked")
    public void LoadSupportv4FragmentBlurBitmap(android.support.v4.app.Fragment fragment, String path, ImageView imageView) {
        Glide.with(fragment).load(path).bitmapTransform(new BlurTransformation(fragment.getActivity())).into(imageView);
    }
    //---------------------------------------------------------

    /**
     * 旋转图片
     * 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制
     * <BR/>使用activity 会受到Activity生命周期控制
     * <BR/>使用FragmentActivity 会受到FragmentActivity生命周期控制
     *
     * @param context
     * @param path
     * @param imageView
     * @param rotateRotationAngle 旋转角度
     */
    @SuppressWarnings("unchecked")
    public void LoadContextRotateBitmap(Context context, String path, ImageView imageView, Float rotateRotationAngle) {
        Glide.with(context).load(path).bitmapTransform(new RotateTransformation(context, rotateRotationAngle)).into(imageView);
    }

    /**
     * Glide 加载旋转图片 会受到Fragment生命周期控制
     *
     * @param fragment
     * @param path
     * @param imageView
     * @param rotateRotationAngle
     */
    @SuppressWarnings("unchecked")
    public void LoadFragmentRotateBitmap(android.app.Fragment fragment, String path, ImageView imageView, Float rotateRotationAngle) {
        Glide.with(fragment).load(path).bitmapTransform(new RotateTransformation(fragment.getActivity(), rotateRotationAngle)).into(imageView);
    }

    /**
     * Glide 加载旋转图片 会受到support.v4.app.Fragment生命周期控制
     *
     * @param fragment
     * @param path
     * @param imageView
     * @param rotateRotationAngle
     */
    @SuppressWarnings("unchecked")
    public void LoadSupportv4FragmentRotateBitmap(android.support.v4.app.Fragment fragment, String path, ImageView imageView, Float rotateRotationAngle) {
        Glide.with(fragment).load(path).bitmapTransform(new RotateTransformation(fragment.getActivity(), rotateRotationAngle)).into(imageView);
    }
    //----------------------旋转---------------------------

    /**
     * 旋转
     */
    public class RotateTransformation extends BitmapTransformation {

        private float rotateRotationAngle = 0f;

        public RotateTransformation(Context context, float rotateRotationAngle) {
            super(context);

            this.rotateRotationAngle = rotateRotationAngle;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            Matrix matrix = new Matrix();

            matrix.postRotate(rotateRotationAngle);

            return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
        }

        @Override
        public String getId() {
            return "rotate" + rotateRotationAngle;
        }
    }
    //--------------------------------------------------

    /**
     * 图片转圆形
     */
    public class GlideCircleTransform extends BitmapTransformation {
        public GlideCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_4444);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_4444);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }
    //-----------------------------图片模糊----------------------------------

    /**
     * 图片模糊
     */
    public class BlurTransformation extends BitmapTransformation {

        private RenderScript rs;

        public BlurTransformation(Context context) {
            super(context);

            rs = RenderScript.create(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            Bitmap blurredBitmap = toTransform.copy(Bitmap.Config.ARGB_8888, true);

            // Allocate memory for Renderscript to work with
            Allocation input = Allocation.createFromBitmap(
                    rs,
                    blurredBitmap,
                    Allocation.MipmapControl.MIPMAP_FULL,
                    Allocation.USAGE_SHARED
            );
            Allocation output = Allocation.createTyped(rs, input.getType());

            // Load up an instance of the specific script that we want to use.
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setInput(input);

            // Set the blur radius
            script.setRadius(10);

            // Start the ScriptIntrinisicBlur
            script.forEach(output);

            // Copy the output to the blurred bitmap
            output.copyTo(blurredBitmap);

            toTransform.recycle();

            return blurredBitmap;
        }

        @Override
        public String getId() {
            return "blur";
        }
    }
    //-------------------图片转换圆角图片------------------------------

    /**
     * 图片转换圆角图片
     */
    public class GlideRoundTransform extends BitmapTransformation {

        private float radius = 0f;

        public GlideRoundTransform(Context context) {
            this(context, 4);
        }

        /**
         * 自定义圆角大小
         *
         * @param context
         * @param dp
         */
        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }

    /**
     * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
     */
    public void loadIntoUseFitWidth(Context context, final String imageUrl, final ImageView imageView) {

        int drawable = R.drawable.header ;

        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                        imageView.setLayoutParams(params);
                        return false;
                    }
                })
                .placeholder(drawable)
                .error(drawable)
                .into(imageView);
    }
}