package com.example.kevin.multipledialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kevin.multipledialog.view.CircleImageDrawable;
import com.example.kevin.multipledialog.view.MatchDialogFragment;
import com.example.kevin.multipledialog.view.MyDialogFragment;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Dialog
 * -AlertDialog    警告对话框
 * -ProgressDialog     进度条对话框
 * -DatePickerDialog   日期对话框
 * -TimePickerDialog   时间对话框
 * AlterDialog直接继承自Dialog，其他的几个类均继承自AlterDialog.
 *
 * interface DialogInterFace.OnClickListener：监听dialog的按钮的点击事件。
 * interface DialogInterface.OnCancelListener：当对话框调用cancel()方法的时候触发。
 * interface DialogInterface.OnDismissListener：当对话框调用dismiss()方法的时候触发。
 * interface DialogInterface.OnShowListener：当对话框调用show()方法的时候触发。
 * interface DialogInterface.OnMultiChoiceListener：当对话框使用多选列表，并且选中的时候触发。
 *
 * dialog.dismiss();    取消对话框
 * dialog.cancel();     取消对话框，会触发OnCancelListener，内部会调用dialog.dismiss();
 *
 * DialogFragment
 * 传统的new AlertDialog在屏幕旋转时，第一不会保存用户输入的值，第二还会报异常，
 * 因为Activity销毁前不允许对话框未关闭。而通过DialogFragment实现的对话框则可以完全不必考虑旋转的问题。
 */
public class DialogActivity extends AppCompatActivity implements MyDialogFragment.DialogFragmentListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);


        initView();
    }

    private void initView() {

    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6,R.id.btn_7,R.id.btn_8,
            R.id.btn_9,R.id.btn_10,R.id.btn_11})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                showDialog1();  // 普通提示对话框
                break;
            case R.id.btn_2:    // 确认取消对话框
                showDialog2();
                break;
            case R.id.btn_3:    // 列表对话框
                showDialog3();
                break;
            case R.id.btn_4:    // 列表单选对话框
                showDialog4();
                break;
            case R.id.btn_5:    // 列表多选对话框
                showDialog5();
                break;
            case R.id.btn_6:    // 自定义对话框
                showDialog6();
                break;
            case R.id.btn_7:    // 进度条对话框
                showDialog7();
                break;
            case R.id.btn_8:    // 日期选择对话框
                showDialog8();
                break;
            case R.id.btn_9:    // 时间选择对话框
                showDialog9();
                break;
            /* =====     DialogFragment     ===== */
            case R.id.btn_10:    // 自定义对话框
                showDialog10();
                break;
            case R.id.btn_11:    // 屏幕适配
                showDialog11();
                break;
        }
    }

    /**
     * 普通提示对话框
     */
    private void showDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("我是标题");
        builder.setIcon(android.R.drawable.btn_star_big_on);
        builder.setMessage("我是内容");
        builder.setCancelable(false);// 设置该dialog是否可以按返回键取消
        builder.setPositiveButton("知道了！", new DialogInterface.OnClickListener() {
            /**
             * 为按钮实现的点击响应监听器
             * @param dialog - 接收点击事件的dialog
             * @param which - 点击的按钮 - 或者 点击的item的位置
             *              Positive（-1）、Negative(-2)、 Neutral(-3)
             */
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();// 内部先创建再显示
//      builder.create().show();
    }

    /**
     * 确认取消对话框
     */
    private void showDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("我是标题");
        builder.setIcon(android.R.drawable.btn_star_big_on);
        builder.setMessage("我是内容：确认取消对话框");
        builder.setCancelable(false);// 设置该dialog是否可以按返回键取消
        // 确认，OK，继续
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this, "确认", Toast.LENGTH_SHORT).show();
            }
        });
        // 取消
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
        // 忽略，以后提醒我
        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this, "忽略", Toast.LENGTH_SHORT).show();
                // 该方法内部也会调用dialog.dismiss()方法，区别就是会触发DialogInterface.OnCancelListener
                dialog.cancel();
            }
        });
        builder.show();
    }

    /**
     * 列表对话框
     */
    private void showDialog3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择城市");
        builder.setIcon(android.R.drawable.btn_star_big_on);
        builder.setCancelable(false);// 设置该dialog是否可以按返回键取消
        final String[] items = {"北京", "上海", "广州", "聊城", "济南"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this, items[which] + which, Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    /**
     * 列表单选对话框
     */
    private void showDialog4() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择城市");
        builder.setIcon(android.R.drawable.btn_star_big_on);
        builder.setCancelable(false);// 设置该dialog是否可以按返回键取消
        final String[] items = {"北京", "上海", "广州", "聊城", "济南"};
        final int[] flag = new int[1];
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                flag[0] = which;
                Toast.makeText(DialogActivity.this, "您选择了：" + items[which] + which, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this, "最终选择：" + flag[0], Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    /**
     * 列表多选对话框
     */
    private void showDialog5() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择城市");
        builder.setIcon(android.R.drawable.btn_star_big_on);
        builder.setCancelable(false);// 设置该dialog是否可以按返回键取消
        final String[] items = {"北京", "上海", "广州", "聊城", "济南"};
        final boolean[] checkedItems = new boolean[5];  // 复选框选择的结果，默认初始为全不选择(false)，如果默认第i个选项选择，就将第i个置为true即可
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this,""+Arrays.toString(checkedItems),Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    /**
     * 自定义对话框
     */
    private void showDialog6() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_login, null);// 加载自定义布局
        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        Button login = (Button) view.findViewById(R.id.login);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.longin);// 将图片解析成Bitmap
        CircleImageDrawable circleImageDrawable = new CircleImageDrawable(bitmap);// 圆形图片
        iv.setImageDrawable(circleImageDrawable);   // 将登陆头像设置成圆形图片

        builder.setView(view);// 将自定义view设置成dialog的内容
        builder.setCancelable(false);

        AlertDialog alertDialog = builder.show();
        final AlertDialog finalAlertDialog = alertDialog;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
                finalAlertDialog.dismiss();
            }
        });
    }

    /**
     * 进度条对话框
     */
    private void showDialog7() {

        final ProgressDialog pd = new ProgressDialog(DialogActivity.this);
        pd.setIcon(android.R.drawable.star_big_on);
        pd.setTitle("下载");
        pd.setMessage("别催了,人家已经很努力的啦~");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//水平进度条-显示进度
        pd.setCancelable(false);
//        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);//环形进度条-不显示进度
        pd.setMax(100);
        /**
         * AsyncTask<Params,Progress,Result>的子类
         * 参数：
         * -Params：参数(初始)，子线程在开始的时候传递的参数，即doInBackground()方法的参数
         * -Progress：进度(中间)，进度单元的类型，在doInBackground()计算出来用于更新进度，即onProgressUpdate()的参数
         * -Result：结果(最终)，子线程返回的数值类型，即doInBackground()方法的返回值类型，也是onPostExecute()的参数类型
         */
        new AsyncTask<Void,Integer,String>() {

            @Override
            protected void onPreExecute() {
                pd.show();
            }

            @Override
            protected String doInBackground(Void...params) {
                for (int i = 0; i < 100; i++) {
                    SystemClock.sleep(100);
                    publishProgress(i);
                }
                return "下载完毕";
            }

            @Override
            protected void onProgressUpdate(Integer...values) {
                pd.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(String result) {
                pd.dismiss();
                Toast.makeText(DialogActivity.this,result,Toast.LENGTH_SHORT).show();
            }

        }.execute();
    }

    /**
     * 日期选择对话框
     * DatePickerDialog
     * public DatePickerDialog (Context context, DatePickerDialog.OnDateSetListener callBack,
     * int year, int monthOfYear, int dayOfMonth)
     * 参数：
     * context-The context the dialog is to run in.
     * callBack-How the parent is notified that the date is set.
     * year-初始年
     * monthOfYear-初始月，注意，月是从0开始的，表示1月
     * dayOfMonth-初始日
     *
     */
    private void showDialog8() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(DialogActivity.this,year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日",Toast.LENGTH_SHORT).show();
            }
        },2000,0,1);
        datePickerDialog.show();
    }

    /**
     * 时间选择对话框
     * DatePickerDialog
     *
     */
    private void showDialog9() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(DialogActivity.this,hourOfDay+"时"+minute+"分",Toast.LENGTH_SHORT).show();
            }
        },14,15,true);
        timePickerDialog.show();
    }

    /**================================  MyDialogFragment  ====================================*/

    /** 自定义对话框 */
    private void showDialog10() {

        // 创建并显示对话框
        MyDialogFragment dialog = new MyDialogFragment();
        dialog.setCancelable(false);//禁止按BACK键
        dialog.show(getFragmentManager(),"MyDialogFragment");
    }

    /**
     * 在大屏幕上，对话框嵌入显示，小屏幕上，对话框弹出显示。（也可以反着来，本例仅仅给出方法）
     */
    private void showDialog11() {

        MatchDialogFragment dialogfragment = new MatchDialogFragment();
        FragmentManager fm = getFragmentManager();

        boolean large_layout = getResources().getBoolean(R.bool.large_layout);
        if (large_layout) {
            // 大屏幕 - 嵌入显示
            FragmentTransaction ft = fm.beginTransaction();
            //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(R.id.fl,dialogfragment).commit();
        }else {
            // 小屏幕 - 弹出对话框
            dialogfragment.setCancelable(false);
            dialogfragment.show(fm,"MyDialogFragment");
        }
    }

    /**
     * 实现MyDialogFragment.DialogFragmentListener接口，
     * 就可以实现从MyDialogFragment向Activity传递数据。
     */
    @Override
    public void onLogin(String username, String password) {
        Toast.makeText(this,"登陆成功 username："+username+"  password："+password,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}