package oa.qianfeng.com.oa.entity;

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
public class LeaveBean {
    public String name;
    public String department;
    public String strType;
    public String time;
    public String duration;
    public String creatTime;
    public String mark;
    public String boss;
    public String strState;

    /**
     * 当前状态整型值
     */
    public int state;
    /**
     * 类型整型值
     */
    public int leaveType;


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
}
