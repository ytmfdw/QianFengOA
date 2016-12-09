package oa.qianfeng.com.oa.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import oa.qianfeng.com.oa.utils.StrUtil;

/**
 * <th>编号</th>
 * <th>标题</th>
 * <th>发布人</th>
 * <th>发布时间</th>
 * <th>类型</th>
 * <th>阅读量</th>
 * <th>状态</th>
 * href
 */
public class MsgBroadcastBean implements Parcelable {
    public String codeNum;
    public String title;
    public String publisher;
    public String publishTime;
    public String type;
    public String readNum;
    public String state;
    public String href;

    public MsgBroadcastBean(Element e) {
        Elements tds = e.select("td");
        if (tds.size() == 7) {
            codeNum = tds.get(0).text();
            title = tds.get(1).text();
            publisher = tds.get(2).text();
            publishTime = tds.get(3).text();
            type = tds.get(4).text();
            readNum = tds.get(5).text();
            state = tds.get(6).text();
        }
    }

    protected MsgBroadcastBean(Parcel in) {
        codeNum = in.readString();
        title = in.readString();
        publisher = in.readString();
        publishTime = in.readString();
        type = in.readString();
        readNum = in.readString();
        state = in.readString();
    }

    public static final Creator<MsgBroadcastBean> CREATOR = new Creator<MsgBroadcastBean>() {
        @Override
        public MsgBroadcastBean createFromParcel(Parcel in) {
            return new MsgBroadcastBean(in);
        }

        @Override
        public MsgBroadcastBean[] newArray(int size) {
            return new MsgBroadcastBean[size];
        }
    };

    @Override
    public String toString() {
        return title + "\n" + publishTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(StrUtil.isNull(codeNum));
        dest.writeString(StrUtil.isNull(title));
        dest.writeString(StrUtil.isNull(publisher));
        dest.writeString(StrUtil.isNull(publishTime));
        dest.writeString(StrUtil.isNull(type));
        dest.writeString(StrUtil.isNull(readNum));
        dest.writeString(StrUtil.isNull(state));
    }


}
