package com.linbochuan.whackamole;

import android.widget.ImageView;

public class mole {
    private int index;
    private ImageView imageView;
    private boolean isVisible;

    public mole(int index, ImageView imageView) {
        this.index = index;
        this.imageView = imageView;
        this.isVisible = false;
    }

    // 获取地鼠索引
    public int getIndex() {
        return index;
    }

    // 获取地鼠图像视图
    public ImageView getImageView() {
        return imageView;
    }

    // 检查地鼠是否可见
    public boolean isVisible() {
        return isVisible;
    }

    // 设置地鼠可见性
    public void setVisible(boolean visible) {
        isVisible = visible;
        if (visible) {
            // 显示地鼠 - 使用有地鼠的图片
            imageView.setImageResource(R.drawable.img_with_mole);
        } else {
            // 隐藏地鼠 - 使用空的地鼠洞图片
            imageView.setImageResource(R.drawable.img_without_mole);
        }
    }
}