package oa.qianfeng.com.oa.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.presenter.AttendancePresenter;
import oa.qianfeng.com.oa.utils.L;
import oa.qianfeng.com.oa.view.IAttendanceView;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class AttendanceFragment extends BaseNetFragment implements IAttendanceView {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.store_house_ptr_frame)
    PtrClassicFrameLayout storeHousePtrFrame;

    AttendancePresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AttendancePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.d("AttendanceFragment viewCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setTitle();
    }

    @Override
    public void setTitle(String title) {
        getActivity().setTitle(title);
    }
}
