package com.karogui.meucachorro.dao.query;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.karogui.meucachorro.POJO.CorAnimal;
import com.karogui.meucachorro.POJO.RacaAnimal;
import com.karogui.meucachorro.dao.gerenciadorbanco.DatabaseHelper;

public class QueryCorAnimal 
{

	private SQLiteDatabase db;
	private DatabaseHelper helper;
	private Context context;

	public QueryCorAnimal(Context ctx) 
	{
		Log.i(DatabaseHelper.class.getName(), "queryCor");
		this.context = ctx;
	}

	public ArrayList<CorAnimal> buscarCorAnimal(String[] colunas, String where, String[] ordemDosParametros, String groupBy, String orderBy) 
	{
		
		this.helper = new DatabaseHelper(this.context);
	    this.db = helper.getWritableDatabase();
		// 
		// where para dentro do select porem sem colocar o where dentro da
		// string

		Log.i(DatabaseHelper.class.getName(), "cria array");
		
		ArrayList<CorAnimal> lista = new ArrayList<CorAnimal>();
		
		Log.i(DatabaseHelper.class.getName(), "chama db.helper");
		db = this.helper.getReadableDatabase();

		// retorna um tipo parecido com resultset
		Log.i(DatabaseHelper.class.getName(), "executaquery");
		Cursor c = this.db.query("coranimal", colunas, where, ordemDosParametros, groupBy, null, orderBy);
		Log.i(DatabaseHelper.class.getName(), "executou");

		
		Log.i(DatabaseHelper.class.getName(), "preenche lista");
		c.moveToFirst();
		while (!c.isAfterLast()) 
		{

			CorAnimal corAnimal = carregaClasse(c);
			lista.add(corAnimal);
			c.moveToNext();
		}
		
		Log.i(DatabaseHelper.class.getName(), "fechaCursor");
		c.close();
		Log.i(DatabaseHelper.class.getName(), "fechadb");
		db.close();
		Log.i(DatabaseHelper.class.getName(), "retorna lista");
		return lista;

	}

	private CorAnimal carregaClasse(Cursor c) {

		CorAnimal corAnimal = new CorAnimal();

		corAnimal.setSeqCor(c.getLong(0));
		corAnimal.setDescrCor(c.getString(1));
		return corAnimal;
	}


	

}
