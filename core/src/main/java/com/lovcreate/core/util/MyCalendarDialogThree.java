package com.lovcreate.core.util;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.lovcreate.core.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

/**
 * Created by Li on 2017/2/17.
 */

public class MyCalendarDialogThree extends Dialog {
    private static int default_width = 360; // 默认宽度
    private static int default_height = 230;// 默认高度
    private int year, month, day, hour, minute;
    private NumberPicker np_year;
    private NumberPicker np_month, np_day, np_hour, np_minute;
    private View view;
    private Context context;
    private TextView tv_cancel, tv_sure;
    private MyCalendarDialogThree.MyCalendar myCalendar;

    public MyCalendarDialogThree(Context context, int layout, int style) {
        this(context, default_width, default_height, layout, style);
    }

    public MyCalendarDialogThree(Context context, int width, int height, int layout, int style) {
        super(context, style);
        // 设置内容
        view = LayoutInflater.from(context).inflate(layout, null);
        setContentView(view);
        this.context = context;
        // 设置窗口属性
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        // 设置宽度、高度、密度、对齐方式
        float density = getDensity(context);
        params.width = (int) (width * density);
        params.height = (int) (height * density);
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        initData();
        initView();

    }

    private void initData() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void initView() {
        np_year = (NumberPicker) view.findViewById(R.id.np_year);
        np_month = (NumberPicker) view.findViewById(R.id.np_month);
        np_day = (NumberPicker) view.findViewById(R.id.np_day);
        np_year.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np_month.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np_day.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        MyCalendarDialogThree.MyScrollListener listener = new MyCalendarDialogThree.MyScrollListener();

        // 确定取消按钮
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_sure = (TextView) view.findViewById(R.id.tv_sure);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCalendarDialogThree.this.dismiss();
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCalendar.setCalendar(np_year.getValue(), np_month.getValue(), np_day.getValue());
            }
        });

        /**
         * 年份
         */
        MyCalendarDialogThree.MyFormatter formatter = new MyCalendarDialogThree.MyFormatter();
        np_year.setFormatter(formatter);
        np_year.setMinValue(1900);
        np_year.setMaxValue(5000);
        np_year.setValue(year);
        np_year.setWrapSelectorWheel(false);// 当待显示的条目数大于3时，设置是否可以循环滚动，注意该行应该放在上面三行的下面
        np_year.setOnValueChangedListener(listener);
        changeValueByOne(np_year, true);
        /**
         * 月份
         */
        MyCalendarDialogThree.MyFormatterMonth formatterMonth = new MyCalendarDialogThree.MyFormatterMonth();
        np_month.setMinValue(1);

        np_month.setMaxValue(12);
        np_month.setValue(month);
        np_month.setFormatter(formatterMonth);
        np_month.setWrapSelectorWheel(false);
        np_month.setOnValueChangedListener(listener);
        changeValueByOne(np_month, true);
        /**
         * 天数
         */
        MyCalendarDialogThree.MyFormatterDay formatterDay = new MyCalendarDialogThree.MyFormatterDay();
        np_day.setMinValue(1);
        np_day.setMaxValue(31);
        np_day.setValue(day);
        np_day.setFormatter(formatterDay);
        np_day.setWrapSelectorWheel(false);
        np_day.setOnValueChangedListener(listener);
        changeValueByOne(np_day, true);

        setNumberPickerDividerColor(np_year);
        setNumberPickerDividerColor(np_month);
        setNumberPickerDividerColor(np_day);
    }

    public class MyFormatter implements NumberPicker.Formatter {
        @Override
        public String format(int value) {
            String s = value + "年";
            return s;
        }
    }

    public class MyFormatterMonth implements NumberPicker.Formatter {
        @Override
        public String format(int value) {
            String s = value + "月";
            return s;
        }
    }

    public class MyFormatterDay implements NumberPicker.Formatter {
        @Override
        public String format(int value) {
            String s = value + "日";
            return s;
        }
    }

    private void changeValueByOne(final NumberPicker higherPicker, final boolean increment) {

        Method method;
        try {
            // refelction call for
            // higherPicker.changeValueByOne(true);
            method = higherPicker.getClass().getDeclaredMethod("changeValueByOne", boolean.class);
            method.setAccessible(true);
            method.invoke(higherPicker, increment);

        } catch (final NoSuchMethodException e) {
            e.printStackTrace();
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        } catch (final InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连月日，时分秒的联动
     */
    public class MyScrollListener implements NumberPicker.OnValueChangeListener {

        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            int i = picker.getId();
            if (i == R.id.np_year) {
                // np_day.setMaxValue(DateUtils.getMonthDays(np_year.getValue(),
                // np_month.getValue() - 1));
                // np_day.setWrapSelectorWheel(true);
                // Log.i("yuanyg", "12314123151555555555555555");
                // if (newVal >= year) {
                // np_month.setWrapSelectorWheel(true);
                // np_month.setMinValue(1);
                // np_day.setWrapSelectorWheel(true);
                // np_day.setMinValue(1);
                // }
                np_month.setMinValue(1);
                np_month.setMaxValue(12);

            } else if (i == R.id.np_month) {
                // np_day.setMaxValue(DateUtils.getMonthDays(np_year.getValue(),
                // np_month.getValue() - 1));
                // np_day.setWrapSelectorWheel(true);
                // if(np_year.getValue()==year){
                // np_month.setMinValue(month);
                // np_month.setWrapSelectorWheel(false);
                // }
                //
                // if (newVal > month && np_year.getValue() >= year) {
                // np_day.setWrapSelectorWheel(true);
                // np_day.setMinValue(1);
                // }
                int m = newVal;
                if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
                    np_day.setMinValue(1);
                    np_day.setMaxValue(31);
                } else if (m == 4 || m == 6 || m == 9 || m == 11) {
                    np_day.setMinValue(1);
                    np_day.setMaxValue(30);
                } else if (m == 2) {
                    np_day.setMinValue(1);
                    np_day.setMaxValue(29);
                }

            } else if (i == R.id.np_day) {
                // Log.i("yuanye","year="+np_year.getValue()+"---------------->month="+np_month.getMaxValue());
                // if(year==np_year.getValue()&&month==np_month.getValue()){
                // Log.i("yuanye","22222222222222222222");
                // np_day.setMinValue(day);
                // np_day.setWrapSelectorWheel(false);
                // }
            }

        }
    }

    /**
     * 修改分割线的颜色
     * 
     * @param numberPicker
     */
    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        NumberPicker picker = numberPicker;
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    // 设置分割线的颜色值
                    pf.set(picker, new ColorDrawable(ContextCompat.getColor(getContext(), R.color.blue)));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * 写回调数据
     */
    public interface MyCalendar {
        void setCalendar(int year, int month, int day);
    }

    public void setSureClick(MyCalendarDialogThree.MyCalendar myCalendar) {
        this.myCalendar = myCalendar;

    }

    /**
     * 获取显示密度
     *
     * @param context
     * @return
     */
    public float getDensity(Context context) {
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        return dm.density;
    }

}
