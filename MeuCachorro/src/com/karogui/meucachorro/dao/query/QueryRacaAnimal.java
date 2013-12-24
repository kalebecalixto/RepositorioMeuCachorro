package com.karogui.meucachorro.dao.query;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.karogui.meucachorro.POJO.RacaAnimal;
import com.karogui.meucachorro.dao.gerenciadorbanco.DatabaseHelper;

public class QueryRacaAnimal {

	private SQLiteDatabase db;
	private DatabaseHelper helper;
	private Context context;

	public QueryRacaAnimal(Context ctx) 
	{
		Log.i(DatabaseHelper.class.getName(), "queryRaca");
		this.context = ctx;
	}

	public ArrayList<RacaAnimal> buscarRacaAnimal(String[] colunas, String where, String[] ordemDosParametros, String groupBy, String orderBy) 
	{
		
		this.helper = new DatabaseHelper(this.context);
	    this.db = helper.getWritableDatabase();
		// 
		// where para dentro do select porem sem colocar o where dentro da
		// string

		Log.i(DatabaseHelper.class.getName(), "cria array Raça");
		
		ArrayList<RacaAnimal> lista = new ArrayList<RacaAnimal>();
		
		
		Log.i(DatabaseHelper.class.getName(), "string Colunas Raça");
		

		Log.i(DatabaseHelper.class.getName(), "chama db.helper da QUERYRACA");
		db = this.helper.getReadableDatabase();

		// retorna um tipo parecido com resultset
		Log.i(DatabaseHelper.class.getName(), "executaquery RAÇA");
		Cursor c = this.db.query("racaanimal", colunas, where, ordemDosParametros, groupBy, null, orderBy);
		Log.i(DatabaseHelper.class.getName(), "executou");

		c.moveToFirst();

		Log.i(DatabaseHelper.class.getName(), "preenche lista RAÇA");
		while (!c.isAfterLast()) 
		{
			RacaAnimal racaAnimal = carregaClasse(c);
			lista.add(racaAnimal);
			c.moveToNext();
		}
		
		Log.i(DatabaseHelper.class.getName(), "fechaCursor");
		c.close();
		Log.i(DatabaseHelper.class.getName(), "fechadb");
		db.close();
		Log.i(DatabaseHelper.class.getName(), "retorna lista");
		return lista;

	}

	private RacaAnimal carregaClasse(Cursor c) {

		RacaAnimal racaAnimal = new RacaAnimal();

		racaAnimal.setSeqRaca(c.getLong(0));
		racaAnimal.setDescrRaca(c.getString(1));
		racaAnimal.setSeqEspecie(c.getInt(2));
		return racaAnimal;
	}

}
