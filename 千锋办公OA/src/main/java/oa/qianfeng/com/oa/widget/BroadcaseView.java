package oa.qianfeng.com.oa.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.entity.BroadcastBean;
import oa.qianfeng.com.oa.utils.Constant;
import uk.co.senab.photoview.PhotoView;


/**
 * 自定义View,用来显示详情界面
 * <p/>
 * 标题
 * <p/>
 * 来源+时间
 * <p/>
 * --------------------------------
 * <p/>
 * 内容
 */
public class BroadcaseView extends LinearLayout {
    public BroadcaseView(Context context) {
        super(context);
        init();
    }

    public BroadcaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //固定方向
        setOrientation(VERTICAL);
    }


    /**
     * 设置数据，绘制界面
     *
     * @param bean
     */
    public void setData(BroadcastBean bean) {
        if (bean == null) {
            return;
        }

        //==============标题=============
        LayoutParams textParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        textParams.setMargins(0, 20, 0, 20);

/*        TextView tv1 = new TextView(getContext());
        //把R.dimen中的尺寸转换成-->px
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_PX, getSize(R.dimen.text_size_title));
        tv1.setText(bean.title);
        addView(tv1, textParams);*/
        //===============来源 时间=============
        TextView tv2 = new TextView(getContext());
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, getSize(R.dimen.text_size_content));
        tv2.setText(bean.getSubTitle());
        addView(tv2, textParams);
        //==============分隔线================
        View view = new View(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
        view.setBackgroundColor(Color.parseColor("#FF333333"));
        addView(view, params);
        //=========内容 ===========

        List<BroadcastBean.ContentEntity> content = bean.getContent();
        for (BroadcastBean.ContentEntity entity : content) {
            //取出数据，创建控件，设置数据
            //根据数据类型，决定创建什么控件
            int type = entity.type;
            //取出内容
            String value = entity.value;
            switch (type) {
                case Constant.TYPE_TEXT: {
                    //文本内容
                    TextView tmp = new TextView(getContext());
                    tmp.setTextSize(TypedValue.COMPLEX_UNIT_PX, getSize(R.dimen.text_size_content));
                    //设置文本行间距
                    tmp.setLineSpacing(1.5f, 1.2f);
                    //段落之间多空一行
                    value = value.replaceAll("\n", "\n\n");
                    tmp.setText(value);
                    addView(tmp, textParams);
                }
                break;
                case Constant.TYPE_IMG: {
                    //图片
                    PhotoView img = new PhotoView(getContext());
                    //加载图片
                    Glide.with(getContext()).load(value).placeholder(R.drawable.default_img).error(R.drawable.error_img).into(img);
                    addView(img, textParams);
                }
                break;
            }
        }

    }

    private float getSize(int id) {
        //返回的是px  18
        return getResources().getDimensionPixelSize(id);
    }
}
