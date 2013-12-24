package com.karogui.meucachorro.dao.query;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.karogui.meucachorro.POJO.CadastroAnimal;
import com.karogui.meucachorro.dao.gerenciadorbanco.DatabaseHelper;

public class QueryCadastroAnimal 
{
	

	private SQLiteDatabase db;
	private DatabaseHelper helper;
	private Context context;

	public QueryCadastroAnimal(Context ctx) 
	{
		Log.i(DatabaseHelper.class.getName(), "queryCadastro");
		this.context = ctx;
		this.helper = new DatabaseHelper(this.context);
	    this.db = helper.getWritableDatabase();
	}

	
	
	public ArrayList<CadastroAnimal> buscarListaAnimais(String[] colunas, String where, String[] ordemDosParametros, String groupBy, String orderBy) 
	{
		
		// 
		// where para dentro do select porem sem colocar o where dentro da
		// string

		Log.i(DatabaseHelper.class.getName(), "cria array Animal");
		
		ArrayList<CadastroAnimal> listaAnimal = new ArrayList<CadastroAnimal>();
		
		Log.i(DatabaseHelper.class.getName(), "chama db.helper");
		
		// retorna um tipo parecido com resultset
		Log.i(DatabaseHelper.class.getName(), "executaquery");
		Cursor c = this.db.query("cadastroanimal", colunas, where, ordemDosParametros, groupBy, null, orderBy);
		Log.i(DatabaseHelper.class.getName(), "executou");

		c.moveToFirst();

		Log.i(DatabaseHelper.class.getName(), "preenche lista");
		while (!c.isAfterLast()) 
		{
			CadastroAnimal animal = carregaClasse(c);
			listaAnimal.add(animal);
			c.moveToNext();
		}
		
		Log.i(DatabaseHelper.class.getName(), "fechaCursor");
		c.close();
		Log.i(DatabaseHelper.class.getName(), "fechadb");
		db.close();
		Log.i(DatabaseHelper.class.getName(), "retorna lista");
		return listaAnimal;

	}

	
	public CadastroAnimal buscarAnimal(String[] colunas, String where, String[] ordemDosParametros, String groupBy, String orderBy) 
	{
		
		// 
		// where para dentro do select porem sem colocar o where dentro da
		// string

			
		CadastroAnimal animal = new CadastroAnimal();
		
		Log.i(DatabaseHelper.class.getName(), "chama db.helper");
		
		// retorna um tipo parecido com resultset
		Log.i(DatabaseHelper.class.getName(), "executaquery");
		Cursor c = this.db.query("cadastroanimal", colunas, where, ordemDosParametros, groupBy, null, orderBy);
		Log.i(DatabaseHelper.class.getName(), "executou");

		c.moveToFirst();

		Log.i(DatabaseHelper.class.getName(), "preenche Objeto Animal");
		while (!c.isAfterLast()) 
		{
			animal = carregaClasse(c);
			c.moveToNext();
		}
		
		Log.i(DatabaseHelper.class.getName(), "fechaCursor");
		c.close();
		Log.i(DatabaseHelper.class.getName(), "fechadb");
		db.close();
		Log.i(DatabaseHelper.class.getName(), "retorna Animal");
		return animal;

	}
	
	
	
	
	private CadastroAnimal carregaClasse(Cursor c) {

		CadastroAnimal animal = new CadastroAnimal();
		

		
		animal.setSeqAnimal(c.getInt(0));
		animal.setNomeAnimal(c.getString(3));
		animal.setDataNascAnimal(c.getString(4));
		animal.setSeqCor(c.getInt(1));
		animal.setSeqRaca(c.getInt(2));
		animal.setSexoAnimal(c.getString(5));
		animal.setRegistro(c.getString(6));
		animal.setObservacoes(c.getString(7));
		animal.setFalecido((String.valueOf(c.getInt(8)).equals("1") ? true : false));
		
		Log.i(DatabaseHelper.class.getName(), String.valueOf(c.getInt(8)));
		
		return animal;
	}

	
	
	public void salvarCadastroAnimal (ContentValues valoresColunas)
	{
		
		Log.i(DatabaseHelper.class.getName(), "Salvando");
		this.db.insert("cadastroanimal", null, valoresColunas);
		db.close();
		Toast toast = Toast.makeText(context, "Dados Salvos Com Sucesso!", Toast.LENGTH_LONG);
        toast.show();
        Log.i(DatabaseHelper.class.getName(), "Salvou");
	}
	/*
	@metodo q recebe a string do sql pronto
	*/
	public void salvarCadastroAnimal (String sql)
	{
		
		Log.i(DatabaseHelper.class.getName(), "Salvando");
		this.db.execSQL(sql);
		db.close();
		Toast toast = Toast.makeText(context, "Dados Salvos Com Sucesso!", Toast.LENGTH_LONG);
        toast.show();
        Log.i(DatabaseHelper.class.getName(), "Salvou");
	}
}
