package com.lovcreate.core.base;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lovcreate.core.R;
import com.lovcreate.core.app.MainApplication;
import com.lovcreate.core.constant.CoreConstant;
import com.lovcreate.core.util.DimenUtils;
import com.lovcreate.core.util.Logcat;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 作者：yuanYe创建于2016/10/12
 * QQ：962851730
 * <p>
 * Activity基础类
 * 可按照需要添加对应的标题栏, 例如:
 * setToolbar("返回","标题")
 * <p>
 * 标题右侧点击可添加弹出框，需要传入弹出框内容(List<String>)以及弹出框中item点击事件
 * <p>
 * 详情参照方法
 */
public abstract class BaseActivity extends AppCompatActivity {

    private PermissionListener mlistener;

    /**
     * 右侧的菜单list
     */
    private PopupWindow popupWindowMenu;
    private final static String TAG = "BaseActivity";
    protected MainApplication mainApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logcat.i(this.toString() + " - ==> onCreate...");
        // 透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
            window.setNavigationBarColor(Color.BLACK);
        }
        // activity管理
        mainApplication = (MainApplication) getApplication();
        mainApplication.getActivityList().add(this);

        // //设置StatusBar的高度
        // View viewSb = (View) findViewById(R.id.viewV);
        // LinearLayout.LayoutParams layoutParams = new
        // LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getStatusBarHeight());
        // viewSb.setLayoutParams(layoutParams);
    }

    /**
     * 重写setContentView(int)方法, 添加初始化ButterKnife
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    public void setContentViewWithoutButterKnife(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logcat.i(this.toString() + " - ==> onStart...");
    }

    /**
     * 在onStart之后调用，恢复数据
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (null == CoreConstant.loginUser.getToken()) {
            CoreConstant.loginUser.setToken(CoreConstant.getToken(this));
        }
        if (null == CoreConstant.loginUser.getRole()) {
            CoreConstant.loginUser.setRole(CoreConstant.getRole(this));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logcat.i(this.toString() + " - ==> onResume...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logcat.i(this.toString() + " - ==> onPause...");
    }

    /**
     * finish方法添加动画效果
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    /** ------------------------------------左侧toolbar按钮设置---------------------------------------- **/

    /**
     * 左侧文字+左侧图标+自定义点击事件
     *
     * @param text     左侧文字
     * @param icon     左侧图标
     * @param listener 点击事件
     */
    protected void setLeftView(String text, int icon, OnClickListener listener) {
        TextView left = (TextView) findViewById(R.id.tv_left);
        left.setText(text);
        RelativeLayout rl_left_toolbar = (RelativeLayout) findViewById(R.id.rl_left_toolbar);
        if (icon != 0) {
            Drawable drawable = getResources().getDrawable(icon);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); // 设置边界
            left.setCompoundDrawables(drawable, null, null, null);// 画在左边
        }
        rl_left_toolbar.setOnClickListener(listener);
    }

    /**
     * 左侧文字+自定义点击事件
     *
     * @param text     左侧文字
     * @param listener 点击事件
     */
    protected void setLeftView(String text, OnClickListener listener) {
        setLeftView(text, 0, listener);
    }

    /**
     * 左侧图标+自定义点击事件
     *
     * @param icon     左侧图标
     * @param listener 点击事件
     */
    protected void setLeftView(int icon, OnClickListener listener) {
        setLeftView("", icon, listener);
    }

    /**
     * 左侧图标文字+返回事件
     *
     * @param text 左侧文字
     * @param icon 左侧图标
     */
    protected void setLeftView(String text, int icon) {
        setLeftView(text, icon, new OnClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                finish();
            }
        });
    }

    /**
     * 左侧图标+返回事件
     *
     * @param icon 左侧图标
     */
    protected void setLeftView(int icon) {
        setLeftView("", icon);
    }

    /**
     * 左侧文字+返回事件
     *
     * @param text 左侧文字
     */
    protected void setLeftView(String text) {
        setLeftView(text, 0);
    }

    /** ------------------------------------中间toolbar按钮设置---------------------------------------- **/

    /**
     * 仅标题
     *
     * @param text            中间文字
     * @param centerTextColor 文字颜色
     */
    protected void setToolbar(String text, int centerTextColor) {
        TextView tv_center = (TextView) findViewById(R.id.tv_center);
        tv_center.setText(text);
        tv_center.setTextColor(this.getResources().getColor(centerTextColor));
    }

    /**
     * 设置左侧文字+中间文字+返回事件
     *
     * @param textLeft 左侧文字
     * @param text     中间文字
     */
    protected void setToolbar(String textLeft, String text, int centerTextColor) {
        setToolbar(text, centerTextColor);
        setLeftView(textLeft);
    }

    /**
     * 中间文字+左侧图标+返回事件
     *
     * @param icon 左侧图标
     * @param text 中间文字
     */
    protected void setToolbar(int icon, String text, int centerTextColor) {
        setToolbar(text, centerTextColor);
        setLeftView(icon);
    }

    /**
     * 左侧文字图标+中间文字+返回事件
     *
     * @param textLeft 左侧文字
     * @param icon     左侧图标
     * @param text     中间文字
     */
    protected void setToolbar(String textLeft, int icon, String text, int centerTextColor) {
        setToolbar(text, centerTextColor);
        setLeftView(textLeft, icon);
    }

    /**
     * 左侧问题图标+自定义点击事件+中间文字
     *
     * @param textLeft 左侧文字
     * @param icon     左侧图标
     * @param listener 点击事件
     * @param text     中间文字
     */
    protected void setToolbar(String textLeft, int icon, OnClickListener listener, String text, int centerTextColor) {
        setToolbar(text, centerTextColor);
        setLeftView(textLeft, icon, listener);
    }

    /** ------------------------------------右侧toolbar按钮设置---------------------------------------- **/

    /**
     * 右侧文字+右侧图标+自定义点击事件
     *
     * @param rightText 右侧文字
     * @param icon      右侧图标
     * @param listener  点击事件
     */
    protected void setRightView(String rightText, int icon, OnClickListener listener, int rightTextColor) {
        RelativeLayout rl_right_toolbar = (RelativeLayout) findViewById(R.id.rl_right_toolbar);
        rl_right_toolbar.setOnClickListener(listener);
        // 右侧的按钮
        TextView tv_right = (TextView) findViewById(R.id.tv_right);
        tv_right.setText(rightText);
        tv_right.setTextColor(this.getResources().getColor(rightTextColor));
        if (icon != 0) {
            Drawable drawable = getResources().getDrawable(icon);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); // 设置边界
            tv_right.setCompoundDrawables(drawable, null, null, null);// 画在左边
        }
    }

    /**
     * 右侧文字+右侧图标+弹出框内容列表+自定义弹出框item点击事件
     *
     * @param rightText 右侧的文字
     * @param icon      右侧的图标
     * @param popupData popup(弹出框)的内容
     * @param listener  item点击事件
     */
    protected void setRightMenu(String rightText, int icon, final ArrayList<String> popupData,
                                final OnItemClickListener listener, int rightTextColor) {
        final TextView tv_right = (TextView) findViewById(R.id.tv_right);
        setRightView(rightText, icon, new OnClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                showPopMenu(tv_right, popupData, listener);
            }
        }, rightTextColor);
    }

    /**
     * 右侧文字+弹出框内容列表+自定义弹出框item点击事件
     *
     * @param rightText 右侧的文字
     * @param popupData popup的内容
     * @param listener  item点击事件
     */
    protected void setRightMenu(String rightText, final ArrayList<String> popupData,
                                final OnItemClickListener listener, int rightTextColor) {
        setRightMenu(rightText, 0, popupData, listener, rightTextColor);
    }

    /**
     * 右侧图标+弹出框内容列表+自定义弹出框item点击事件
     *
     * @param icon      右侧的图标
     * @param popupData popup的内容
     * @param listener  item点击事件
     */
    protected void setRightMenu(int icon, final ArrayList<String> popupData, final OnItemClickListener listener, int rightTextColor) {
        setRightMenu("", icon, popupData, listener, rightTextColor);
    }

    protected void hideRightMenu() {
        RelativeLayout rl_right_toolbar = (RelativeLayout) findViewById(R.id.rl_right_toolbar);
        rl_right_toolbar.setVisibility(View.GONE);
    }

    /**
     * 重写onStop()方法, 取消Glide加载图片请求
     */
    @Override
    protected void onStop() {
        super.onStop();
        Logcat.i(this.toString() + " - ==> onStop...");
        if (Glide.with(this) != null) {
            Glide.with(this).pauseRequests();
        }
    }

    /**
     * 重写OnDestroy()方法, 销毁时收起软键盘
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logcat.i(this.toString() + " - ==> onDestroy...");
        mainApplication.getActivityList().remove(this);
        hideSoftInput();
    }

    /**
     * 显示标题栏右侧按钮弹框
     */
    public void showPopMenu(View view, ArrayList<String> popupData, OnItemClickListener listener) {
        if (popupWindowMenu != null && popupWindowMenu.isShowing()) {
            // 关闭popupWindow
            popupWindowMenu.dismiss();
        } else {
            final View popupView = getLayoutInflater().inflate(R.layout.base_tool_bar_popupwindow, null);
            popupWindowMenu = new PopupWindow(popupView, ListPopupWindow.WRAP_CONTENT, ListPopupWindow.WRAP_CONTENT,
                    true);

            // 设置弹出的popupWindow不遮挡软键盘
            popupWindowMenu.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            popupWindowMenu.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

            // 设置点击外部让popupWindow消失
            popupWindowMenu.setFocusable(true);// 可以试试设为false的结果
            popupWindowMenu.setOutsideTouchable(true); // 点击外部消失

            // 必须设置的选项
            popupWindowMenu.setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
            popupWindowMenu.setTouchInterceptor(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                    // 这里如果返回true的话，touch事件将被拦截
                    // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                }
            });
            // 将window视图显示在点击按钮下面
            popupWindowMenu.showAsDropDown(view, 0, -DimenUtils.px2dip(this, view.getHeight()));
            ListView listView = (ListView) popupView.findViewById(R.id.pop_listView);
            listView.setAdapter(new MoreBaseAdapter<String>(popupData, R.layout.base_tool_bar_popupwindow_item) {
                @Override
                public void bindView(ViewHolder holder, String obj) {
                    holder.setText(R.id.tv_item_content, obj);
                }
            });
            listView.setOnItemClickListener(listener);
        }
    }

    /**
     * 收起键盘
     */
    public void hideSoftInput() {
        try {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.i(TAG, "----" + e.toString());
        }

    }

    public void setStatusBarHeight() {
        // 设置StatusBar的高度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // View viewSb = (View) view.findViewById(R.id.viewV);
            // LinearLayout.LayoutParams layoutParams = new
            // LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getStatusBarHeight());
            // viewSb.setLayoutParams(layoutParams);
        } else {

        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void hineSearchMenu() {
        if (popupWindowMenu != null && popupWindowMenu.isShowing()) {
            // 关闭popupWindow
            popupWindowMenu.dismiss();
        }
    }

    /**
     * 显示标题栏右侧按钮弹框
     */
    public void showSearchMenu(View view, List<String> popupData, AdapterView.OnItemClickListener listener) {
        if (popupWindowMenu != null && popupWindowMenu.isShowing()) {
            // 关闭popupWindow
            popupWindowMenu.dismiss();
        }
        final View popupView = getLayoutInflater().inflate(R.layout.base_tool_bar_popup_search, null);
        popupWindowMenu = new PopupWindow(popupView, ListPopupWindow.MATCH_PARENT, ListPopupWindow.MATCH_PARENT,
                true);

        // 设置弹出的popupWindow不遮挡软键盘
        popupWindowMenu.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindowMenu.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        // 设置点击外部让popupWindow消失
        popupWindowMenu.setFocusable(false);// 可以试试设为false的结果
        popupWindowMenu.setOutsideTouchable(true); // 点击外部消失

        // 必须设置的选项
        popupWindowMenu.setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        popupWindowMenu.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        // 将window视图显示在点击按钮下面
        popupWindowMenu.showAsDropDown(view, 0, 0);
        ListView listView = (ListView) popupView.findViewById(R.id.pop_listView);
        listView.setAdapter(new MoreBaseAdapter<String>(popupData, R.layout.base_tool_bar_popup_search_item) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_item_content, obj);
            }
        });
        listView.setOnItemClickListener(listener);

    }

    /** ------------------------------------  动态获取权限 http://www.jianshu.com/p/c6cb758cbb43 ---------------------------------------- **/

    /**
     * 权限申请
     *
     * @param permissions 待申请的权限集合
     * @param listener    申请结果监听事件
     */
    protected void requestRunTimePermission(String[] permissions, PermissionListener listener) {
        this.mlistener = listener;

        //用于存放为授权的权限
        List<String> permissionList = new ArrayList<>();
        //遍历传递过来的权限集合
        for (String permission : permissions) {
            //判断是否已经授权
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                //未授权，则加入待授权的权限集合中
                permissionList.add(permission);
            }
        }

        //判断集合
        if (!permissionList.isEmpty()) {  //如果集合不为空，则需要去授权
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {  //为空，则已经全部授权
            listener.onGranted();
        }
    }

    /**
     * 权限申请结果
     *
     * @param requestCode  请求码
     * @param permissions  所有的权限集合
     * @param grantResults 授权结果集合
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    //被用户拒绝的权限集合
                    List<String> deniedPermissions = new ArrayList<>();
                    //用户通过的权限集合
                    List<String> grantedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        //获取授权结果，这是一个int类型的值
                        int grantResult = grantResults[i];

                        if (grantResult != PackageManager.PERMISSION_GRANTED) { //用户拒绝授权的权限
                            String permission = permissions[i];
                            deniedPermissions.add(permission);
                        } else {  //用户同意的权限
                            String permission = permissions[i];
                            grantedPermissions.add(permission);
                        }
                    }

                    if (deniedPermissions.isEmpty()) {  //用户拒绝权限为空
                        mlistener.onGranted();
                    } else {  //不为空
                        //拒绝授权
                        mlistener.onDenied(deniedPermissions);
                        //授权部分
                        mlistener.onGranted(grantedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }

}
