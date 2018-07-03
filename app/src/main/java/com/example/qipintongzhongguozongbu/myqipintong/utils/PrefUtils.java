package com.example.qipintongzhongguozongbu.myqipintong.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

/**
 * SharePreference工具类封装
 */
public class PrefUtils {

	public static void putBoolean(Context ctx, String key, boolean value) {
		SharedPreferences sp = ctx.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context ctx, String key,
									 boolean defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		return sp.getBoolean(key, defaultValue);
	}

	public static void putString(Context ctx, String key, String value) {
		SharedPreferences sp = ctx.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();
	}

	public static String getString(Context ctx, String key, String defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		return sp.getString(key, defaultValue);
	}

	public static void putInt(Context ctx, String key, int value) {
		SharedPreferences sp = ctx.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		sp.edit().putInt(key, value).commit();
	}

	public static int getInt(Context ctx, String key, int defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		return sp.getInt(key, defaultValue);
	}

}
