package oa.qianfeng.com.oa.entity;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
    public String type;
    public String time;
    public String duration;
    public String creatTime;
    public String mark;
    public String boss;
    public String strState;

    public int state;


    public LeaveBean(Element e) {
        Elements tds = e.select("td");
        
    }
}
