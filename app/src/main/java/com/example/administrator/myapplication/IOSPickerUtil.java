package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.lib.MessageHandler;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.view.WheelTime;
import com.lovcreate.core.base.BaseActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 统一 防IOS选择器:包括,日期,时间,条件,日期范围
 * Created by Albert.Ma on 2017/7/25 0025.
 */

public class IOSPickerUtil {

    /**
     * 收起键盘
     * @param context
     */
    private static void  hideSoftInput(Context context){
        ((BaseActivity)context).hideSoftInput();
    }

    /**
     * 获取日期选择器Builder
     *
     * @param context              上下文
     * @param onTimeSelectListener 点击时间监听器
     * @param title                标题
     * @return
     */
    public static TimePickerView.Builder getDatePickerBuilder(Context context, TimePickerView.OnTimeSelectListener onTimeSelectListener, String title) {
        hideSoftInput(context);
        TimePickerView.Builder builder = new TimePickerView.Builder(context, onTimeSelectListener);
        builder.setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("完成")//确认按钮文字
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText(title)//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.RED)//确定按钮文字颜色
                .setCancelColor(Color.GRAY)//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false);//是否显示为对话框样式
        return builder;
    }

    /**
     * 获取时间选择器Builder
     *
     * @param context              上下文
     * @param onTimeSelectListener 点击时间监听器
     * @param title                标题
     * @return
     */
    public static TimePickerView.Builder getTimePickerBuilder(Context context, TimePickerView.OnTimeSelectListener onTimeSelectListener, String title) {
        hideSoftInput(context);
        TimePickerView.Builder builder = new TimePickerView.Builder(context, onTimeSelectListener);
        builder.setType(new boolean[]{false, false, false, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("完成")//确认按钮文字
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText(title)//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.RED)//确定按钮文字颜色
                .setCancelColor(Color.GRAY)//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false);//是否显示为对话框样式
        return builder;
    }

    /**
     * 单项条件选择器
     *
     * @param context                 上下文
     * @param onOptionsSelectListener 点击提交监听事件
     * @param title                   标题
     */
    public static OptionsPickerView.Builder getOptionsPickerBuilder(Context context, OptionsPickerView.OnOptionsSelectListener onOptionsSelectListener, String title) {
        hideSoftInput(context);
        OptionsPickerView.Builder builder = new OptionsPickerView.Builder(context, onOptionsSelectListener);
        builder.setTitleText(title)//设置标题内容
                .setTitleColor(Color.parseColor("#393845"))// 设置标题文字颜色
                .setContentTextSize(24)// 设置滚轮文字大小
                .setTextColorCenter(Color.parseColor("#393845"))//设置滚轮文字颜色
                .setDividerColor(Color.parseColor("#E6E6E6"))// 设置分割线的颜色
                .setBgColor(Color.WHITE)// 设置背景颜色
                .setTitleBgColor(Color.WHITE)// 设置标题背景颜色
                .setCancelColor(Color.parseColor("#9B96A3"))// 设置左侧取消文字颜色
                .setSubmitText("完成")//设置确定的文字内容
                .setSubmitColor(Color.parseColor("#E60012"))//设置确定的文字颜色
                .setBackgroundId(0x66000000) // 设置外部遮罩颜色
                .setLineSpacingMultiplier(0.5f)//滚轮间距设置（1.2-2.0倍，此为文字高度的间距倍数）
                .isCenterLabel(false);// 是否只显示中间选中项的label文字，false则每项item全部都带有label。
        return builder;
    }


    /////////////////////////////////////////////////////////////////////    日期段选择器    /////////////////////////////////////////////////////////////////////

    /**
     * 获取日期段选择器
     */
    private static Context CONTEXT;//上下文
    private static TimePickerView dateRangePicker;
    private static TextView TARGET;//选择日期的目标

    /**
     * 获取日期段选择器 返回 2017.07.28-2017.07.29
     *
     * @param context        上下文
     * @param targetTextView 选择日期的目标
     * @return
     */
    public static TimePickerView getDateRangePickerView(Context context, TextView targetTextView) {
        hideSoftInput(context);
        return getDateRangePickerView(context, targetTextView, null);
    }

    /**
     * 获取日期段选择器 回调: 开始日期和结束日期
     *
     * @param context        上下文
     * @param targetTextView 选择日期的目标
     * @param callback       回调
     * @return
     */
    public static TimePickerView getDateRangePickerView(Context context, TextView targetTextView, DateRangeCustomListener.Callback callback) {
        hideSoftInput(context);

        //设置相关属性
        CONTEXT = context;
        TARGET = targetTextView;

        //新建自定义布局
        IOSPickerUtil.DateRangeCustomListener dateRangeCustomListener = new IOSPickerUtil.DateRangeCustomListener();
        if (callback != null) {
            dateRangeCustomListener.setCallback(callback);
        }

        //创建自定义布局的Builder
        TimePickerView.Builder builder = new TimePickerView.Builder(CONTEXT, null).setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .setLayoutRes(R.layout.pickerview_date_range, dateRangeCustomListener);

        dateRangePicker = builder.build();

        //设置日期选择器开始的日期
        String datesStr = "";
        if (callback != null) {
            datesStr = callback.setInitStartDateEndDateListener();
        } else {
            datesStr = String.valueOf(TARGET.getText());
        }
        Calendar startDate = Calendar.getInstance();
        if (datesStr.contains("-")) {
            String[] dates = datesStr.split("-");
            String[] str = dates[0].split("\\.");
            startDate.set(Integer.parseInt(str[0]), Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]));
        }
        dateRangePicker.setDate(startDate);//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。

        return dateRangePicker;
    }

    ///仿IOS自定义布局监听器的实现类
    public static class DateRangeCustomListener implements CustomListener {

        private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        private int FLAG = 0;//0:未选择;1:开始时间;2:结束时间

        private Callback callback;

        @Override
        public void customLayout(View v) {
            /**
             * 获取组件
             */
            final TextView startDateTextView = (TextView) v.findViewById(R.id.startDateTextView);
            final TextView endDateTextView = (TextView) v.findViewById(R.id.endDateTextView);
            TextView btnSubmit = (TextView) v.findViewById(R.id.btnSubmit);
            TextView tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            TextView btnCancel = (TextView) v.findViewById(R.id.btnCancel);
            WheelView year = (WheelView) v.findViewById(R.id.year);
            WheelView month = (WheelView) v.findViewById(R.id.month);
            WheelView day = (WheelView) v.findViewById(R.id.day);

            /**
             * 初始化
             */
            String datesStr = "";
            if (callback != null) {
                datesStr = callback.setInitStartDateEndDateListener();
            } else {
                datesStr = String.valueOf(TARGET.getText());
            }
            if (datesStr.contains("-")) {
                String[] dates = datesStr.split("-");
                startDateTextView.setText(dates[0]);
                endDateTextView.setText(dates[1]);
                FLAG = 1;
                startDateTextView.setBackgroundResource(R.drawable.pickerview_date_range_selected);
                startDateTextView.setTextColor(ContextCompat.getColor(CONTEXT, R.color.white));
            } else {
                startDateTextView.setText(sdf.format(new Date()));
                endDateTextView.setText(sdf.format(new Date()));
            }
            btnSubmit.setText("完成");
            tvTitle.setText("不限");
            btnCancel.setText("取消");

            /**
             * 事件
             */
            //开始时间 点击事件
            startDateTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FLAG = 1;
                    startDateTextView.setBackgroundResource(R.drawable.pickerview_date_range_selected);
                    startDateTextView.setTextColor(ContextCompat.getColor(CONTEXT, R.color.white));
                    startDateTextView.setText(parseDate(dateRangePicker.wheelTime.getTime()));
                    endDateTextView.setBackgroundResource(R.drawable.pickerview_date_range_unselected);
                    endDateTextView.setTextColor(ContextCompat.getColor(CONTEXT, R.color.black));
                }
            });
            //结束时间 点击事件
            endDateTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FLAG = 2;
                    endDateTextView.setBackgroundResource(R.drawable.pickerview_date_range_selected);
                    endDateTextView.setTextColor(ContextCompat.getColor(CONTEXT, R.color.white));
                    endDateTextView.setText(parseDate(dateRangePicker.wheelTime.getTime()));
                    startDateTextView.setBackgroundResource(R.drawable.pickerview_date_range_unselected);
                    startDateTextView.setTextColor(ContextCompat.getColor(CONTEXT, R.color.black));
                }
            });
            //完成 点击事件
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null) {
                        callback.setOnClickDoneListener(String.valueOf(startDateTextView.getText()), String.valueOf(endDateTextView.getText()));
                    } else {
                        TARGET.setText(startDateTextView.getText() + "-" + endDateTextView.getText());
                    }
                    dateRangePicker.dismiss();
                }
            });
            //取消 点击事件
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dateRangePicker.dismiss();
                }
            });
            //不限 点击事件
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null) {
                        callback.setOnClickDoneListener("", "");
                    } else {
                        TARGET.setText("");
                    }
                    dateRangePicker.dismiss();
                }
            });
            //年 滚动事件
            year.handler.setCallback(new MessageHandler.Callback() {
                @Override
                public void afterRoll(String year) {
                    if (FLAG == 1) {
                        String date = String.valueOf(startDateTextView.getText());
                        startDateTextView.setText(year + date.substring(4, date.length()));
                    } else if (FLAG == 2) {
                        String date = String.valueOf(endDateTextView.getText());
                        endDateTextView.setText(year + date.substring(4, date.length()));
                    }
                }
            });
            //月 滚动事件
            month.handler.setCallback(new MessageHandler.Callback() {
                @Override
                public void afterRoll(String month) {
                    month = setStringWith0(month);
                    if (FLAG == 1) {
                        String date = String.valueOf(startDateTextView.getText());
                        startDateTextView.setText(date.substring(0, 5) + month + date.substring(7, date.length()));
                    } else if (FLAG == 2) {
                        String date = String.valueOf(endDateTextView.getText());
                        endDateTextView.setText(date.substring(0, 5) + month + date.substring(7, date.length()));
                    }
                }
            });
            //日 滚动事件
            day.handler.setCallback(new MessageHandler.Callback() {
                @Override
                public void afterRoll(String day) {
                    day = setStringWith0(day);
                    if (FLAG == 1) {
                        String date = String.valueOf(startDateTextView.getText());
                        startDateTextView.setText(date.substring(0, 8) + day);
                    } else if (FLAG == 2) {
                        String date = String.valueOf(endDateTextView.getText());
                        endDateTextView.setText(date.substring(0, 8) + day);
                    }
                }
            });
        }

        public void setCallback(Callback callback) {
            this.callback = callback;
        }

        /**
         * 处理月日前面加0
         *
         * @param string 月或日
         * @return
         */
        private String setStringWith0(String string) {
            string = string.length() == 1 ? "0" + string : string;
            return string;
        }

        /**
         * 转化日期格式
         */
        private String parseDate(String timeStr) {
            Date date = null;
            try {
                date = WheelTime.dateFormat.parse(timeStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return sdf.format(date);
        }

        public interface Callback {
            /**
             * 返回初始化开始时间与滚轮显示的时间,和结束时间,-分隔:返回例如:2017.08.01-2017.09.01
             */
            String setInitStartDateEndDateListener();

            /**
             * 点击完成后的监听器,用于获取开始时间和结束时间
             */
            void setOnClickDoneListener(String startDate, String endDate);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 任务获取日期选择器Builder
     *
     * @param context              上下文
     * @param onTimeSelectListener 点击时间监听器
     * @param title                标题
     */
    public static TimePickerView.Builder getTaskDatePickerBuilder(Context context, TimePickerView.OnTimeSelectListener onTimeSelectListener, String title) {
        hideSoftInput(context);
        TimePickerView.Builder builder = new TimePickerView.Builder(context, onTimeSelectListener);
        builder.setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("完成")//确认按钮文字
                .setContentSize(20)//滚轮文字大小
                .setTitleSize(18)//标题文字大小
                .setTitleText(title)//标题文字
                .setTitleBgColor(Color.WHITE)//标题背景颜色
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setTitleColor(Color.parseColor("#393845"))//标题文字颜色
                .setSubmitColor(Color.parseColor("#E60012"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#9B96A3"))//取消按钮文字颜色
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false);//是否显示为对话框样式
        return builder;
    }

    /**
     * 获取时间选择器Builder
     *
     * @param context              上下文
     * @param onTimeSelectListener 点击时间监听器
     * @param title                标题
     * @return
     */
    public static TimePickerView.Builder getDefaultTime(Context context, TimePickerView.OnTimeSelectListener onTimeSelectListener, String title, Calendar currentDate) {
        hideSoftInput(context);
        TimePickerView.Builder builder = new TimePickerView.Builder(context, onTimeSelectListener);
        builder.setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("完成")//确认按钮文字
                .setContentSize(20)//滚轮文字大小
                .setTitleSize(18)//标题文字大小
                .setTitleText(title)//标题文字
                .setTitleBgColor(Color.WHITE)//标题背景颜色
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.parseColor("#393845"))//标题文字颜色
                .setSubmitColor(Color.parseColor("#E60012"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#9B96A3"))//取消按钮文字颜色
                .setLabel("年", "月", "日", "时", "分", "")//默认设置为年月日时分秒
                .setDate(currentDate)// 如果不设置的话，默认是系统时间
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false);//是否显示为对话框样式
        return builder;
    }

    /**
     * 任务获取时间选择器Builder
     *
     * @param context              上下文
     * @param onTimeSelectListener 点击时间监听器
     * @param title                标题
     */
    public static TimePickerView.Builder getTaskTimePickerBuilder(Context context, TimePickerView.OnTimeSelectListener onTimeSelectListener, String title) {
        hideSoftInput(context);
        TimePickerView.Builder builder = new TimePickerView.Builder(context, onTimeSelectListener);
        builder.setType(new boolean[]{false, false, false, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("完成")//确认按钮文字
                .setContentSize(20)//滚轮文字大小
                .setTitleSize(18)//标题文字大小
                .setTitleText(title)//标题文字
                .setTitleBgColor(Color.WHITE)//标题背景颜色
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.parseColor("#393845"))//标题文字颜色
                .setSubmitColor(Color.parseColor("#E60012"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#9B96A3"))//取消按钮文字颜色
                .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false);//是否显示为对话框样式
        return builder;
    }


    /**
     * 客户报备获取时间选择器Builder
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @param context              上下文
     * @param onTimeSelectListener 点击时间监听器
     * @param title                标题
     */
    public static TimePickerView.Builder getCustomerTimePickerBuilder(Context context, TimePickerView.OnTimeSelectListener onTimeSelectListener, String title) {
        hideSoftInput(context);
        TimePickerView.Builder builder = new TimePickerView.Builder(context, onTimeSelectListener);
        builder.setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("完成")//确认按钮文字
                .setContentSize(20)//滚轮文字大小
                .setTitleSize(18)//标题文字大小
                .setTitleText(title)//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.parseColor("#393845"))//标题文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色
                .setSubmitColor(Color.parseColor("#E60012"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#9B96A3"))//取消按钮文字颜色
                .setLabel("年", "月", "日", "时", "分", "")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false);//是否显示为对话框样式
        return builder;
    }

}
