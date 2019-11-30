# largeview
**这是一个图片放大缩小以及展示gif的展示库**

集成方式  :  implementation 'com.github.wukuiqing49:Large:1.0.91'

实例展示: 
    1: CustomPreviewImageView 支持展示gif格式和一般图片链接格式
    2:LargeImageView   支持bitmap和file文件的放大缩小展示方法是(setImage)

LargeImageView :

```
    /**
     * Sets a Bitmap as the content of this ImageView.
     *
     * @param bm The bitmap to initFitImageScale
     */
    @Override
    public void setImage(Bitmap bm) {
        setImageDrawable(new BitmapDrawable(getResources(), bm));
    }

    @Override
    public void setImage(Drawable drawable) {
        setImageDrawable(drawable);
    }

    @Override
    public void setImage(@DrawableRes int resId) {
        setImageDrawable(ContextCompat.getDrawable(getContext(), resId));
    }

```


CustomPreviewImageView:
```
     

CustomPreviewImageView largeImageView = findViewById(R.id.largeView);
//   largeImageView.setImageView("http://img0.imgtn.bdimg.com/it/u=3577340855,977795531&fm=26&gp=0.jpg", ""); 
        largeImageView.setImageView("http://img.sccnn.com/bimg/326/203.jpg", "");
        largeImageView.setClickLisetner(new CustomPreviewClickListener() {
            @Override
            public void longClick(String file) {
            //长按事件
                Toast.makeText(MainActivity.this, file, Toast.LENGTH_LONG).show();
            }

            @Override
            public void click() {
                Toast.makeText(MainActivity.this, "单击", Toast.LENGTH_LONG).show();
            }
        });


```







