package com.ztstech.vgmate.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


public class ToastUtil {

	@SuppressLint("ServiceCast")
	public static void toastCenter(Context context, String text) {
		if (ContextUtils.isContextFinishing(context)) {
			return;
		}
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		WindowManager manager = (WindowManager) context
				.getSystemService(context.WINDOW_SERVICE);
		int height = manager.getDefaultDisplay().getHeight();
		toast.setGravity(Gravity.TOP, 0, (int) (0.4 * height));
		toast.show();
	}

//    /**
//     * 无法登录提示
//     * @param context
//     */
//	public static void loadFialTip(Context context, String hint){
//		if (ContextUtils.isContextFinishing(context)) {
//			return;
//		}
//		View view = LayoutInflater.from(context).inflate(R.layout.toast_login_hint,null);
//		TextView tvHint = (TextView) view.findViewById(R.id.tv_hint);
//		if (hint != null && !hint.isEmpty()){
//			tvHint.setText(hint);
//		}
//		Toast toast = new Toast(context);
//		toast.setView(view);
//		toast.setDuration(Toast.LENGTH_LONG);
//		toast.setGravity(Gravity.TOP,0,ViewUtils.dp2px(232));
//		toast.showPickDialog();
//	}
//	public static void sendGroupMessageSuccess(Context context, String hint){
//		if (ContextUtils.isContextFinishing(context)) {
//			return;
//		}
//		View view = LayoutInflater.from(context).inflate(R.layout.toast_send_group_message_hint,null);
//		TextView tvHint = (TextView) view.findViewById(R.id.tv_hint);
//		if (hint != null && !hint.isEmpty()){
//			tvHint.setText(hint);
//		}
//		Toast toast = new Toast(context);
//		toast.setView(view);
//		toast.setDuration(Toast.LENGTH_LONG);
//		toast.setGravity(Gravity.TOP,0,ViewUtils.dp2px(232));
//		toast.showPickDialog();
//	}

}