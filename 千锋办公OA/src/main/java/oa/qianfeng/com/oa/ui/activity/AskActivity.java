package oa.qianfeng.com.oa.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.entity.LeaveBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.presenter.AskPresenter;
import oa.qianfeng.com.oa.utils.Constant;
import oa.qianfeng.com.oa.utils.IntentUtils;
import oa.qianfeng.com.oa.utils.L;
import oa.qianfeng.com.oa.view.IAskView;

/**
 * Created by Administrator on 2016/12/7.
 */
public class AskActivity extends BaseNetActivity implements IAskView, OnGetDataListener<Map<String, Object>> {

    LeaveBean bean;
    int type;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.acsp)
    AppCompatSpinner acsp;
    @BindView(R.id.btn_startTime)
    Button btnStartTime;
    @BindView(R.id.btn_endTime)
    Button btnEndTime;
    @BindView(R.id.et_allTime)
    TextInputEditText etAllTime;
    @BindView(R.id.et_mark)
    TextInputEditText etMark;

    List<String> boess = new ArrayList<>();
    ArrayAdapter<String> adapter_boss;
    List<String> reason = new ArrayList<>();
    ArrayAdapter<String> adapter_reason;

    AskPresenter presenter;
    @BindView(R.id.btn_submmit)
    Button btnSubmmit;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.sp_reason)
    AppCompatSpinner spReason;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        ButterKnife.bind(this);
        bean = getIntent().getParcelableExtra(IntentUtils.INTENT_KEY_LEAVEBEAN);
        type = getIntent().getIntExtra(IntentUtils.INTENT_KEY_TYPE, Constant.TYPE_LEFAVE);
        adapter_boss = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, boess);
        acsp.setAdapter(adapter_boss);
        adapter_reason = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, reason);
        spReason.setAdapter(adapter_reason);

        presenter = new AskPresenter(this);

        if (bean == null) {
            presenter.loadData(this);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dissmissLoading() {

    }

    @Override
    public void setupViews(LeaveBean bean) {
        title.setText(type == Constant.TYPE_LEFAVE ? "请假申请" : type == Constant.TYPE_OVERTIME ? "加班申请" : "补签申请");
        if (bean != null) {
            tvName.setText("姓名：\t" + bean.name);
            tvDepartment.setText("部门：\t" + bean.department);


            try {
                title.setText(bean.strType);
                type = bean.leaveType;
                if (boess.contains(bean.boss)) {
                    acsp.setSelection(boess.indexOf(bean.boss));
                } else {
                    boess.add(bean.boss);
                    adapter_boss.notifyDataSetChanged();
                }
                if (reason.contains(bean.strType)) {
                    spReason.setSelection(reason.indexOf(bean.strType));
                } else {
                    reason.add(bean.strType);
                    adapter_reason.notifyDataSetChanged();
                }
//            acsp.setSelection(0);
                if (type == Constant.TYPE_SIGN) {
                    //补签，只显示一个时间
                    btnStartTime.setText(bean.time);
                    btnEndTime.setVisibility(View.GONE);
                    etAllTime.setVisibility(View.GONE);
                } else {
                    String[] times = bean.time.split("至");
                    if (times != null && times.length == 2) {
                        btnStartTime.setText(times[0]);
                        L.d(btnStartTime.getText().toString());
                        btnEndTime.setText(times[1]);
                        L.d(btnEndTime.getText().toString());
                    }
                }

                etAllTime.setText(bean.duration);

                etMark.setText(bean.mark);

                if (bean.state == Constant.STATE_ALLOWED) {
                    //已批复的，无需修改
                    btnStartTime.setEnabled(false);
                    btnEndTime.setEnabled(false);
                    acsp.setEnabled(false);
                    spReason.setEnabled(false);
                    etAllTime.setEnabled(false);
                    etMark.setEnabled(false);
                    btnSubmmit.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public LeaveBean getLeaveBean() {
        return bean;
    }


    @OnClick({R.id.btn_startTime, R.id.btn_endTime, R.id.btn_submmit, R.id.btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_startTime:
                break;
            case R.id.btn_endTime:
                break;
            case R.id.btn_submmit:
                break;
            case R.id.btn_cancel:
                setResult(Constant.SUBMMIT_CANCEL);
                finish();
                break;
        }
    }

    @Override
    public void onGetDataSuccess(Map<String, Object> value) {
        //加载界面
        bean = (LeaveBean) value.get(Constant.MAP_KEY_BEAN);
        List<String> data = (List<String>) value.get(Constant.MAP_KEY_LIST_BOSS);
        boess.clear();
        boess.addAll(data);
        adapter_boss.notifyDataSetChanged();
        List<String> myreason = (List<String>) value.get(Constant.MAP_KEY_LIST_REASON);
        reason.clear();
        reason.addAll(myreason);
        adapter_reason.notifyDataSetChanged();
        setupViews(bean);
    }

    @Override
    public void onGetDataFaild() {

    }
}
