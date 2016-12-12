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

        //
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Dialog_Fullscreen);
        LoadingView loadingView = new LoadingView(this);
        loadingView.setText(msg);
        builder.setView(loadingView);
        //不可取消
        builder.setCancelable(cancelable);
        dialog = builder.create();
        //设置对话框 大小
        //1.得到对话框的窗体对象
        Window dialogWindow = dialog.getWindow();
        //2.得到对话框窗体对象的布局属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置对话框重心
        dialogWindow.setGravity(Gravity.CENTER);
        //3.测量控件大小，得到控件的测量大小
        loadingView.measure(0, 0);
        //4.设置对话框布局属性的宽高
        lp.width = loadingView.getMeasuredWidth();
        lp.height = loadingView.getMeasuredHeight();
        //5.重新设置对话框窗体的布局属性
        dialogWindow.setAttributes(lp);
        return dialog;
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
