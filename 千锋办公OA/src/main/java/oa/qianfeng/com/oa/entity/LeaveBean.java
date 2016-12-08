package oa.qianfeng.com.oa.entity;


import android.os.Parcel;
import android.os.Parcelable;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import oa.qianfeng.com.oa.utils.Constant;

/**
 * <th width="40px" style="text-align: center">姓名</th>
 * <th width="58px" style="text-align: center">部门</th>
 * <th width="40px" style="text-align: center">请假类型</th>
 * <th width="130px" style="text-align: center">请假时间</th>
 * <th width="20px" style="text-align: center">时长</th>
 * <th width="50px" style="text-align: center">创建时间</th>
 * <th width="80px" style="text-align: center">备注</th>
 * <th width="10px" style="text-align: center">审批人</th>
 * <th width="50px" style="text-align: center">状态</th>
 * <th width="50px" style="text-align: center">操作</th>
 * <p/>
 * int state
 */
public class LeaveBean implements Parcelable {
    public String name;
    public String department;
    public String strType;
    public String time;
    public String duration;
    public String creatTime;
    public String mark;
    public String boss;
    public String strState;
    public String id;
    public String user_id;

    public String group_id;

    /**
     * 当前状态整型值
     */
    public int state=-1;
    /**
     * 类型整型值
     */
    public int leaveType;

    public LeaveBean() {

    }

    public LeaveBean(Parcel in) {
        name = in.readString();
        department = in.readString();
        strType = in.readString();
        time = in.readString();
        duration = in.readString();
        creatTime = in.readString();
        mark = in.readString();
        boss = in.readString();
        strState = in.readString();
        state = in.readInt();
        leaveType = in.readInt();
        id = in.readString();
        user_id = in.readString();
        group_id = in.readString();
    }

    public LeaveBean(Element e) {
        Elements tds = e.select("td");
        if (tds.size() == 10) {
            name = tds.get(0).text();
            department = tds.get(1).text();
            strType = tds.get(2).text();
            //设置leaveType
            leaveType = getType(strType);
            time = tds.get(3).text();
            duration = tds.get(4).text();
            creatTime = tds.get(5).text();
            mark = tds.get(6).text();
            boss = tds.get(7).text();
            strState = tds.get(8).text();

            //获取id
            String tmp = tds.get(9).text().trim();
            if (tmp != null && tmp.length() >= 0) {
                Elements es = tds.get(9).select("a");
                for (Element t : es) {
                    if (t.hasAttr("href")) {
                        String href = t.attr("href");
                        if (href.contains("user_id")) {
                            String[] arr = href.split("\\?");
                            if (arr.length == 2) {
                                String[] arr2 = arr[1].split("&");
                                for (int i = 0; i < arr2.length; i++) {
                                    if (arr2[i].contains("user_id")) {
                                        user_id = arr2[i].replaceAll("user_id", "");
                                        user_id = user_id.replaceAll("=", "");
                                        user_id = user_id.trim();
                                    }
                                    if (arr2[i].contains("ID")) {
                                        id = arr2[i].replaceAll("ID", "");
                                        id = id.replaceAll("=", "");
                                        id = id.trim();
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //状态整型值
            state = getState(strState);
        }

    }

    private static int getType(String str) {
        if (str.contains("加班")) {
            return Constant.TYPE_OVERTIME;
        } else if (str.contains("补签")) {
            return Constant.TYPE_SIGN;
        }
        return Constant.TYPE_LEFAVE;
    }

    private static int getState(String str) {
        if (str.contains("已批准")) {
            return Constant.STATE_ALLOWED;
        }
        return Constant.STATE_WAIT;
    }

    @Override
    public String toString() {
        return strType + "\t" + creatTime + "\t" + strState;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(isNull(name));
        dest.writeString(isNull(department));
        dest.writeString(isNull(strType));
        dest.writeString(isNull(time));
        dest.writeString(isNull(duration));
        dest.writeString(isNull(creatTime));
        dest.writeString(isNull(mark));
        dest.writeString(isNull(boss));
        dest.writeString(isNull(strState));
        dest.writeInt(state);
        dest.writeInt(leaveType);
        dest.writeString(isNull(id));
        dest.writeString(isNull(user_id));
        dest.writeString(isNull(group_id));
    }

    public String isNull(String str) {
        return str == null ? "" : str;
    }

    public static final Parcelable.Creator<LeaveBean> CREATOR = new Creator<LeaveBean>() {
        @Override
        public LeaveBean createFromParcel(Parcel source) {
            return new LeaveBean(source);
        }

        @Override
        public LeaveBean[] newArray(int size) {
            return new LeaveBean[0];
        }
    };
}
