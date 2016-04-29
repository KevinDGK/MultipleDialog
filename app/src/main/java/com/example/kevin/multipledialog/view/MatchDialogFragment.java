package com.example.kevin.multipledialog.view;

import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kevin.multipledialog.R;

/**
 * Created by Kevin on 2016/4/29.
 * DialogFragment在android 3.0时被引入,是一种特殊的Fragment,用于在Activity的内容之上展示一个模态的对话框。
 * 典型的用于：展示警告框，输入框，确认框等等。
 * 使用DialogFragment至少需要实现onCreateView或者onCreateDialog方法。
 */
public class MatchDialogFragment extends DialogFragment {

    /**
     * 重写onCreateView，即使用自定义的xml布局文件展示Dialog，返回的是View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (!getResources().getBoolean(R.bool.large_layout))
        {
            // 如果是小屏幕，需要弹出窗口，所以需要将标题栏的空白给去掉
            Log.i("MatchDialogFragment", "onCreateView: 去掉空白标题栏！");
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        // 创建自定义view，并将其作为Fragment的根布局返回，即dialog的布局
        View view = inflater.inflate(R.layout.dialog_login_match,container,false);

        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        Button login = (Button) view.findViewById(R.id.login);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.longin);// 将图片解析成Bitmap
        CircleImageDrawable circleImageDrawable = new CircleImageDrawable(bitmap);// 圆形图片
        iv.setImageDrawable(circleImageDrawable);   // 将登陆头像设置成圆形图片

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                Toast.makeText(getActivity(), "登陆成功！", Toast.LENGTH_SHORT).show();
            }
        });
        return view;    //返回的view就是dialog要展示的内容
    }

}
