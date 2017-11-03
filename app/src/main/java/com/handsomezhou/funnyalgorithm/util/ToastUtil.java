package com.handsomezhou.funnyalgorithm.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhoujq.
 */

public class ToastUtil {
	public static void toast(Context context,int resId,int duration){
		do {
			if(null==context){
				break;
			}

			Toast.makeText(context, resId, duration).show();
		}while (false);

		return;
	}
	
	public static void toast(Context context,String text,int duration){
		do{
			if(null==context){
				break;
			}

			Toast.makeText(context, text, duration).show();
		}while (false);

		return;
	}
	
	public static void toastLengthshort(Context context,String text){
		do{
			if(null==context){
				break;
			}

			ToastUtil.toast(context, text, Toast.LENGTH_SHORT);
		}while (false);

		return;
	}
	
	public static void toastLengthLong(Context context,String text){
		do{
			if(null==context){
				break;
			}

			ToastUtil.toast(context, text, Toast.LENGTH_LONG);
		}while (false);

		return;
	}
	
	public static void toastLengthshort(Context context,int resId){
		do{
			if(null==context){
				break;
			}

			ToastUtil.toast(context, resId, Toast.LENGTH_SHORT);
		}while (false);

		return;
	}
	
	public static void toastLengthLong(Context context,int resId){
		do{
			if(null==context){
				break;
			}

			ToastUtil.toast(context, resId, Toast.LENGTH_LONG);
		}while (false);

		return;
	}
}
