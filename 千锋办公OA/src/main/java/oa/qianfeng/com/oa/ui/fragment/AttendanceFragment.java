package oa.qianfeng.com.oa.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.adapter.EndlessRecyclerOnScrollListener;
import oa.qianfeng.com.oa.adapter.RBaseAdapter;
import oa.qianfeng.com.oa.entity.KaoQinAllBean;
import oa.qianfeng.com.oa.entity.KaoQinBean;
import oa.qianfeng.com.oa.impl.OnLoadDataListener;
import oa.qianfeng.com.oa.presenter.AttendancePresenter;
import oa.qianfeng.com.oa.utils.L;
import oa.qianfeng.com.oa.view.IAttendanceView;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class AttendanceFragment extends BaseNetFragment implements IAttendanceView, OnLoadDataListener {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.store_house_ptr_frame)
    PtrClassicFrameLayout refresh;

    AttendancePresenter presenter;

    RBaseAdapter<KaoQinBean> adapter;

    int page = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AttendancePresenter(this);
        showLoading();
        presenter.loadData(1, this);
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
        presenter.initViews();
    }

    @Override
    public void onResume() {
        super.onResume();
//        presenter.setTitle();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {
        refresh.refreshComplete();
//        adapter.setFootetViewVisiable(false);
    }

    @Override
    public void setTitle() {
        getActivity().setTitle(presenter.getTitle());
    }

    @Override
    public void initViews() {
        adapter = new RBaseAdapter<KaoQinBean>(getActivity(), android.R.layout.simple_expandable_list_item_2) {
            @Override
            public void bindData(RViewHolder holder, int position) {
                KaoQinBean bean = adapter.getData(position);
                if (bean != null) {
                    TextView tv1 = (TextView) holder.findViewById(android.R.id.text1);
                    TextView tv2 = (TextView) holder.findViewById(android.R.id.text2);
                    tv1.setText(bean.kq_date);
                    tv2.setText(bean.kq_time + bean.kq_address);
                }
            }
        };
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        //加载更多
        createLoadMoreView();

        rv.addOnScrollListener(new EndlessRecyclerOnScrollListener(rv.getLayoutManager()) {
            @Override
            public void onLoadMore() {
                adapter.setFootetViewVisiable(true);
                page++;
                L.d("page=" + page);
                presenter.loadData(page, AttendanceFragment.this);
            }
        });

        //刷新控件
        refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //先清空
                adapter.setDatas(null);
                page = 1;
                presenter.loadData(page, AttendanceFragment.this);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, rv, header);
            }
        });
    }

    @Override
    public void setData(List<KaoQinBean> data) {
        adapter.addData(data);
    }

    @Override
    public void getDataSuccess(KaoQinAllBean all, List<KaoQinBean> bean) {
        dismissLoading();
    }

    @Override
    public void getDataFaild() {

        dismissLoading();
    }

    private void createLoadMoreView() {
        View loadMoreView = LayoutInflater
                .from(getActivity())
                .inflate(R.layout.view_load_more, rv, false);
        adapter.addFooterView(loadMoreView);
    }
}
