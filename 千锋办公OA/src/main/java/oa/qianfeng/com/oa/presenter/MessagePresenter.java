package oa.qianfeng.com.oa.presenter;

import oa.qianfeng.com.oa.model.AttendanceModel;
import oa.qianfeng.com.oa.model.IAttendanceModel;
import oa.qianfeng.com.oa.model.IMessageModel;
import oa.qianfeng.com.oa.model.MessageModel;
import oa.qianfeng.com.oa.view.IAttendanceView;
import oa.qianfeng.com.oa.view.IMessageView;

/**
 * Created by Administrator on 2016/12/1.
 */
public class MessagePresenter {
    IMessageView view;
    IMessageModel model;

    public MessagePresenter(IMessageView view) {
        this.view = view;
        model = new MessageModel();
    }

    public void setTitle() {
        view.setTitle(model.getTitle());
    }
}
