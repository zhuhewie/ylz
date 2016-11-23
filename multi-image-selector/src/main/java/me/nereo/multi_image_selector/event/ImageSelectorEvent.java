package me.nereo.multi_image_selector.event;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-10-10.
 * 候诊界面的添加图片的点击事件
 * 如果resultList != null ,是选着图片的点击回调
 * 如果stringFile != null ,是调用相机的的回调
 */
public class ImageSelectorEvent {
    public ArrayList<String> resultList; //选中的图片
    public String stringFile;  //拍照的图片

    public ImageSelectorEvent(ArrayList<String> resultList) {
        this.resultList = resultList;
    }

//    public ImageSelectorEvent

    public ImageSelectorEvent(String imgFile) {
        this.stringFile = imgFile;
    }
}
