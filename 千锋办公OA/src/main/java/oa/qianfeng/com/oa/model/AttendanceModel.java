package oa.qianfeng.com.oa.model;

import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.entity.KaoQinAllBean;

/**
 * Created by Administrator on 2016/12/1.
 */
public class AttendanceModel implements IAttendanceModel {
    @Override
    public String getTitle() {
        String name = QFApp.user.name;
        String id = QFApp.user.id;
        return "姓名：" + name + "    " + "工号：" + id;
    }

    @Override
    public String getDetail(KaoQinAllBean all) {
        if (all != null) {
            StringBuilder sb = new StringBuilder();
            int day = all.allDays - all.realDays;
            if (day > 0) {
                sb.append("缺勤").append(day).append("天;");
            }
            int late = all.late20 + all.late30 + all.late60 + all.late120;
            if (late > 0) {
                sb.append("迟到").append(late).append("次;");
            }
            if (all.unsignCount > 0) {
                sb.append("未打卡").append(all.unsignCount).append("次;");
            }

            if (sb.length() == 0) {
                sb.append("没有不良记录");
            }

            return sb.toString();
        }
        return "没有不良记录";
    }

    KaoQinAllBean allBean;

    @Override
    public KaoQinAllBean getAllBean() {
        return allBean;
    }

    @Override
    public void setAllBean(KaoQinAllBean all) {
        this.allBean = all;
    }
}
