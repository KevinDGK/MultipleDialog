package com.example.kevin.multipledialog.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.kevin.multipledialog.R;

/**
 * Created by Kevin on 2016/4/29.
 * DialogFragment在android 3.0时被引入,是一种特殊的Fragment,用于在Activity的内容之上展示一个模态的对话框。
 * 典型的用于：展示警告框，输入框，确认框等等。
 * 使用DialogFragment至少需要实现onCreateView或者onCreateDialog方法。
 */
public class MyDialogFragment extends DialogFragment {

    /**
     * 第一种方法：重写onCreateView，即使用自定义的xml布局文件展示Dialog，返回的是View
     */
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//        // 创建自定义view，并将其作为Fragment的根布局返回，即dialog的布局
//        View view = inflater.inflate(R.layout.dialogfragment_login,container);
//        return view;    //返回的view就是dialog要展示的内容
//    }

    /**
     * 第二种方法：重写onCreateDialog，即利用AlertDialog或者Dialog创建出Dialog，返回的是Dialog
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // 下面几行代码跟dialog无关，仅仅是为了显示圆形头像
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialogfragment_login, null);// 加载自定义布局
        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.longin);// 将图片解析成Bitmap
        CircleImageDrawable circleImageDrawable = new CircleImageDrawable(bitmap);// 圆形图片
        iv.setImageDrawable(circleImageDrawable);   // 将登陆头像设置成圆形图片

        final EditText et_usename = (EditText) view.findViewById(R.id.et_usename);
        final EditText et_password = (EditText) view.findViewById(R.id.et_password);

        builder.setView(view);// 将自定义view设置成dialog的内容

        builder.setPositiveButton("登陆", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 向Activity传递数据
                DialogFragmentListener listener = (DialogFragmentListener) getActivity();
                String name = et_usename.getText().toString().trim();
                String pwd = et_password.getText().toString().trim();
                listener.onLogin(name, pwd);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 向Activity传递数据
                DialogFragmentListener listener = (DialogFragmentListener) getActivity();
                listener.onCancel("Baby,你取消了登陆操作~");
            }
        });
        return builder.create();
    }

    /**
     * 对话框和Activity进行交互的接口：让对话框所在的Activity实现
     */
    public interface DialogFragmentListener {
        void onLogin(String username, String password);  //登陆
        void onCancel(String msg);
    }
}
