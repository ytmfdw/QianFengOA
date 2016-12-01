package oa.qianfeng.com.oa.model;

import oa.qianfeng.com.oa.QFApp;

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
}
