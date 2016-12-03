package oa.qianfeng.com.oa.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import oa.qianfeng.com.oa.utils.L;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
public abstract class EndlessRecyclerOnScrollListener extends
        RecyclerView.OnScrollListener {
    private int previousTotal = 0;
    private boolean loading = true;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private LinearLayoutManager mLinearLayoutManager;

    public EndlessRecyclerOnScrollListener(
            RecyclerView.LayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = (LinearLayoutManager) linearLayoutManager;
    }

    boolean isAddMore = false;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        L.d("isAddMore=" + isAddMore);
        L.d("newState=" + newState);
        switch (newState) {
            case RecyclerView.SCROLL_STATE_DRAGGING: {
//                isAddMore = false;
            }
            break;
            case RecyclerView.SCROLL_STATE_IDLE: {
                //停止时，判断是否是最后一条
                if (isAddMore) {
                    onLoadMore();
                }
            }
            break;
            case RecyclerView.SCROLL_STATE_SETTLING: {
//                isAddMore = false;
            }
            break;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        if (dy > 0) {
            int lastVisiblePosition = visibleItemCount + firstVisibleItem;
            L.d("lastVisiblePosition=" + lastVisiblePosition);
            L.d("totalItemCount=" + totalItemCount);
            if (lastVisiblePosition == totalItemCount) {
                isAddMore = true;
            } else {
                isAddMore = false;
            }
        }
    }

    public abstract void onLoadMore();
}
