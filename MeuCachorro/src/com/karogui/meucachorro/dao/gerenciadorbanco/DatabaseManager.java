package com.karogui.meucachorro.dao.gerenciadorbanco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseManager 
{

	private static DatabaseManager instance;
	private DatabaseHelper helper;
	private SQLiteDatabase db;

	public static void init(Context ctx) 
	{
		Log.i(DatabaseHelper.class.getName(), "Init");
		if (null == instance) {
			instance = new DatabaseManager(ctx);
		}
	}

	public static DatabaseManager getInstance() 
	{
		return instance;
	}

	private DatabaseManager(Context ctx) 
	{
		Log.i(DatabaseHelper.class.getName(), "chama help");
		helper = new DatabaseHelper(ctx);
		db = helper.getWritableDatabase(); 
	}

	public DatabaseHelper getHelper() 
	{
		return helper;
	}


	
}
