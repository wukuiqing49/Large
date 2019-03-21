package com.cnlive.largeimage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cnlive.largeimage.factory.FileBitmapDecoderFactory;
import com.cnlive.largeimage.loading.LVCircularSmile;

import java.io.File;


/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/1/24
 * <p>
 * 简介:
 */
public class CustomPreviewImageView extends LinearLayout {
    Context mContext;
    private LVCircularSmile loading;
    private ImageView gif;
    private ImageView preview;
    private ImageView errImg;
    private LargeImageView largeImageView;
    private RelativeLayout root;
    CustomPreviewClickListener clickLisetner;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String filePath = (String) msg.obj;
                if (!TextUtils.isEmpty(filePath)) showView(filePath);
            } else if (msg.what == 2) {
                String errMessage = (String) msg.obj;
                if (mContext != null && !TextUtils.isEmpty(errMessage)) {
                    showLoadFail();
                    Toast.makeText(mContext, errMessage, Toast.LENGTH_LONG).show();
                }

            }
        }
    };

    public CustomPreviewImageView(Context context) {
        this(context, null);
    }

    public CustomPreviewImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomPreviewImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    public void setClickLisetner(CustomPreviewClickListener clickLisetner) {
        this.clickLisetner = clickLisetner;
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_custom_preview, this, true);
        loading = view.findViewById(R.id.loading);
        loading.startAnim();
        gif = view.findViewById(R.id.gif);
        preview = view.findViewById(R.id.preview);
        errImg = view.findViewById(R.id.errImg);
        largeImageView = view.findViewById(R.id.large);
        root = view.findViewById(R.id.root);

    }

    public void setImageView(final String imgurl, String previewUrl) {
        if (TextUtils.isEmpty(imgurl)) preview.setVisibility(GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = handler.obtainMessage();
                try {
                    File imgfile = Glide.with(mContext).asFile().load(imgurl).submit().get();
                    if (imgfile != null && imgfile.exists()) {
                        msg.what = 1;
                        msg.obj = imgfile.getAbsolutePath();
                    } else {
                        msg.what = 2;
                        msg.obj = "图片读取异常";
                    }
                } catch (Exception e) {
                    msg.what = 2;
                    msg.obj = "图片下载异常";
                } finally {
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void showView(final String file) {
        loading.stopAnim();
        loading.setVisibility(GONE);
        if ("gif".equals(FileTypeUtil.getFileType(file))) {
            gif.setVisibility(View.VISIBLE);
            preview.setVisibility(GONE);
            largeImageView.setVisibility(GONE);
            RequestOptions options = new RequestOptions();
            options.diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(mContext).asGif().load(file).apply(options).into(gif);
            gif.setOnTouchListener(new OnTouchListener() {
                private long downTime;
                private boolean isDouble;

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if (DoublePressed.onDoublePressed()) {
                                isDouble = true;
                            }
                            downTime = System.currentTimeMillis();
                            break;
                        case MotionEvent.ACTION_UP:
                            long differenceTime = System.currentTimeMillis() - downTime;
                            if (!isDouble) {
                                if (differenceTime > 500) {
                                    clickLisetner.longClick(file);
                                } else {
                                    clickLisetner.click();
                                }
                            }
                            downTime = 0;
                            isDouble = false;
                            break;
                    }
                    return true;
                }
            });
        } else {
            gif.setVisibility(View.GONE);
            preview.setVisibility(GONE);
            errImg.setVisibility(GONE);
            largeImageView.setVisibility(View.VISIBLE);
            largeImageView.setImage(new FileBitmapDecoderFactory(file));

            largeImageView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    clickLisetner.longClick(file);
                    return false;
                }
            });
            largeImageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (DoublePressed.onDoublePressed()) return;
                    clickLisetner.click();
                }
            });

            largeImageView.setOnImageLoadListener(new BlockImageLoader.OnImageLoadListener() {
                @Override
                public void onBlockImageLoadFinished() {

                }

                @Override
                public void onLoadImageSize(int imageWidth, int imageHeight) {

                }

                @Override
                public void onLoadFail(Exception e) {

                }
            });

        }
    }

    private void showLoadFail() {
        loading.stopAnim();
        gif.setVisibility(GONE);
        loading.setVisibility(GONE);
        largeImageView.setVisibility(GONE);
        preview.setVisibility(GONE);
        errImg.setVisibility(VISIBLE);
        errImg.setImageResource(R.drawable.preview_zwtp);
        root.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLisetner.click();
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (handler != null) handler.removeCallbacksAndMessages(null);
    }
}
