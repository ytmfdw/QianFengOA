package oa.qianfeng.com.oa.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatSpinner;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

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
import oa.qianfeng.com.oa.utils.StrUtil;
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
    AppCompatAutoCompleteTextView acsp;
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

    @BindView(R.id.btn_submmit)
    Button btnSubmmit;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.sp_reason)
    AppCompatSpinner spReason;
    TimePickerDialog startTpd;

    TimePickerDialog endTpd;
    long startTime = 0;

    long endTime = 0;
    AskPresenter presenter;

    @Override
    public void initViews() {
        super.initViews();
        bean = getIntent().getParcelableExtra(IntentUtils.INTENT_KEY_LEAVEBEAN);
        type = getIntent().getIntExtra(IntentUtils.INTENT_KEY_TYPE, Constant.TYPE_LEFAVE);
        adapter_boss = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, boess);
        acsp.setAdapter(adapter_boss);
        adapter_reason = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, reason);
        spReason.setAdapter(adapter_reason);

        presenter = new AskPresenter(this);
        showLoading();
        presenter.loadData(bean, type, this);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_ask);
        ButterKnife.bind(this);
    }

    @Override
    public void showLoading() {
        getShowDialog(false, "正在加载...").show();
    }

    @Override
    public void dismissLoading() {
        dismiss();
    }

    @Override
    public void setupViews(LeaveBean bean) {
        title.setText(type == Constant.TYPE_LEFAVE ? "请假申请" : type == Constant.TYPE_OVERTIME ? "加班申请" : "补签申请");
        if (bean != null) {
            tvName.setText("姓名：\t" + bean.name);
            tvDepartment.setText("部门：\t" + bean.department);


            try {
                title.setText(StrUtil.isNull(bean.strType));
                type = bean.leaveType;
                acsp.setText(bean.boss);
                if (!boess.contains(StrUtil.isNull(bean.boss))) {
//                    acsp.setText(bean.boss);
//                } else {
                    boess.add(StrUtil.isNull(bean.boss));
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
                    btnStartTime.setText(StrUtil.isNull(bean.time));
                    btnEndTime.setVisibility(View.GONE);
                    etAllTime.setVisibility(View.GONE);
                } else {
                    if (bean.time != null && bean.time.contains("至")) {
                        String[] times = bean.time.split("至");
                        if (times != null && times.length == 2) {
                            btnStartTime.setText(times[0]);
                            L.d(btnStartTime.getText().toString());
                            btnEndTime.setText(times[1]);
                            L.d(btnEndTime.getText().toString());
                        }
                    }
                }

                etAllTime.setText(StrUtil.isNull(bean.duration));

                etMark.setText(StrUtil.isNull(bean.mark));

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

    @Override
    public void showSuccess(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        setResult(Constant.SUBMMIT_SUCCESS);
        finish();
    }

    @Override
    public void showFaild() {
        Toast.makeText(this, "请求失败", Toast.LENGTH_LONG).show();
    }


    @OnClick({R.id.btn_startTime, R.id.btn_endTime, R.id.btn_submmit, R.id.btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_startTime: {
                if (startTpd == null) {
                    long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
                    startTpd = new TimePickerDialog.Builder()
                            .setCallBack(new OnDateSetListener() {

                                @Override
                                public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                                    startTime = millseconds;
                                    btnStartTime.setText(StrUtil.getStringByTime(startTime));
                                }
                            })
                            .setCancelStringId("取消")
                            .setSureStringId("确定")
                            .setTitleStringId("开始时间")
                            .setYearText("年")
                            .setMonthText("月")
                            .setDayText("日")
                            .setHourText("时")
                            .setMinuteText("分")
                            .setCyclic(false)
                            .setMinMillseconds(System.currentTimeMillis())
                            .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                            .setCurrentMillseconds(System.currentTimeMillis())
                            .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                            .setType(Type.ALL)
                            .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                            .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                            .setWheelItemTextSize(12)
                            .build();
                }
                startTpd.show(getSupportFragmentManager(), "start");
            }
            break;
            case R.id.btn_endTime: {
                if (endTpd == null) {
                    long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
                    endTpd = new TimePickerDialog.Builder()
                            .setCallBack(new OnDateSetListener() {

                                @Override
                                public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                                    endTime = millseconds;
                                    if (endTime < startTime) {
                                        Toast.makeText(AskActivity.this, "结束时间不能小于开始时间", Toast.LENGTH_LONG).show();
                                        endTime = 0;
                                        return;
                                    }
                                    btnEndTime.setText(StrUtil.getStringByTime(endTime));
                                    etAllTime.setText(StrUtil.getHourByDuration(type, startTime, endTime) + "");
                                }
                            })
                            .setCancelStringId("取消")
                            .setSureStringId("确定")
                            .setTitleStringId("结束时间")
                            .setYearText("年")
                            .setMonthText("月")
                            .setDayText("日")
                            .setHourText("时")
                            .setMinuteText("分")
                            .setCyclic(false)
                            .setMinMillseconds(System.currentTimeMillis())
                            .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                            .setCurrentMillseconds(System.currentTimeMillis())
                            .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                            .setType(Type.ALL)
                            .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                            .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                            .setWheelItemTextSize(12)
                            .build();
                }
                endTpd.show(getSupportFragmentManager(), "end");
            }
            break;
            case R.id.btn_submmit: {
                bean.boss = acsp.getText().toString();
                bean.strType = (String) spReason.getSelectedItem();
                if (type == Constant.TYPE_SIGN) {
                    bean.duration = btnStartTime.getText().toString();
                } else {
                    bean.duration = btnStartTime.getText().toString() + "至" + btnEndTime.getText().toString();
                }

                bean.hours = etAllTime.getText().toString();

                bean.mark = etMark.getText().toString();

                presenter.postAsk(bean);


            }
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

        dismissLoading();
    }

    @Override
    public void onGetDataFaild() {
        Toast.makeText(this, "获取数据失败", Toast.LENGTH_LONG).show();
    }
}
