package oa.qianfeng.com.oa.ui.activity;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.widget.LoadingView;

/**
 * Created by Administrator on 2016/11/30.
 */
public class BaseNetActivity extends BaseActivity {

    AlertDialog dialog;

    /**
     * 获取显示的对话框
     *
     * @param cancelable 是否可取消
     * @param msg        显示的文字
     * @return
     */
    public Dialog getShowDialog(boolean cancelable, String msg) {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Dialog_Fullscreen);
        LoadingView loadingView = new LoadingView(this);
        loadingView.setText(msg);
        builder.setView(loadingView);
        //不可取消
        builder.setCancelable(cancelable);
        dialog = builder.create();

        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        loadingView.measure(0, 0);
        lp.width = loadingView.getMeasuredWidth();
        lp.height = loadingView.getMeasuredHeight();
        dialogWindow.setAttributes(lp);
        return dialog;
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
