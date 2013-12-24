package com.karogui.meucachorro.dao.gerenciadorbanco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.karogui.meucachorro.POJO.IEntidade;

public class DatabaseHelper extends SQLiteOpenHelper {
	// nome do arquivo do database
	private static final String DATABASE_FILE_NAME = "meucachorro.db";
	// sempre que voce mudar objetos em seu database incremente a versao
	private static final int DATABASE_VERSION = 1;

	private String[] scriptCreate;
	private String[] scriptUpdate;
	private String[] scriptCargaInicial;

	Context context;
	/**
	 * criar banco DatabaseHelper(Context ctx, String nomeBd, int versaoBanco)
	 * 
	 * @author kalebe
	 * 
	 * @param <T>
	 */
	public DatabaseHelper(Context ctx) 
	{

		
		super(ctx, DATABASE_FILE_NAME, null, DATABASE_VERSION);
		Log.i(DatabaseHelper.class.getName(), "dentro do help");
		this.context = ctx;
		
	}

	public void onCreate(SQLiteDatabase db) {

		try 
		{
			
			Log.i(DatabaseHelper.class.getName(), "Mandar ler Create banco");
			
			scriptCreate = lerArquivos(db, "createBanco.sql");
			
			Log.i(DatabaseHelper.class.getName(), "Leu Arq Create");
			
		} 
		catch (IOException e) 
		{
			Log.i(DatabaseHelper.class.getName(), "Falha ao ler arq Create Banco");
			e.printStackTrace();
		}

		try
		{
			Log.i(DatabaseHelper.class.getName(), "Criando Banco");
			for (String sql : scriptCreate) 
			{
				db.execSQL(sql);
			}
			Log.i(DatabaseHelper.class.getName(), "Banco Criado ");
		}
		catch (Exception e) 
		{
			Log.i(DatabaseHelper.class.getName(), "Falha ao criar Banco");
			e.printStackTrace();
		}
		

		try 
		{

			Log.i(DatabaseHelper.class.getName(), "ler arq Carga Banco");
			
			scriptCargaInicial = lerArquivos(db, "cargaInicial.sql");
			
			Log.i(DatabaseHelper.class.getName(), "leu arq Carga Banco");
		} 
		catch (IOException e) 
		{
			Log.i(DatabaseHelper.class.getName(), "falha ao ler arq Carga Banco");
			e.printStackTrace();
		}
		
		try
		{
			Log.i(DatabaseHelper.class.getName(), "executando Carga Banco");
			for (String sql : scriptCargaInicial) 
			{
				db.execSQL(sql);
			}
			Log.i(DatabaseHelper.class.getName(), "Executada Carga Banco");

		}
		catch (Exception e) 
		{
			Log.i(DatabaseHelper.class.getName(), "falha ao executar arq Carga Banco");
			e.printStackTrace();
		}

		
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		Log.i(DatabaseHelper.class.getName(), "atualizado banco");

	}

	private String[] lerArquivos(SQLiteDatabase db, String nomeArquivo)
			throws IOException {
		AssetManager manager = context.getAssets();

		InputStream inputStream = null;
		BufferedReader reader = null;

		try {
			inputStream = manager.open(nomeArquivo);

			reader = new BufferedReader(new InputStreamReader(inputStream));

			StringBuilder stringBuilderSql = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuilderSql.append(line);
			}

			String[] sqls = stringBuilderSql.toString().split(";");

			return sqls;

		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}

				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
	}
/*
	public <T> Dao<T, Object> getDAO(Class<T> entidadeClass) {
		Dao<IEntidade, Object> dao = null;
		if (daos.get(entidadeClass) == null) {
			try {

				dao = getDao(entidadeClass);
			} catch (SQLException e) {
				Log.e(DatabaseHelper.class.getName(),
						"exception during getDAO", e);
				throw new RuntimeException(e);
			}
			daos.put(entidadeClass, dao);
		}

		return (Dao<T, Object>) daos.get(entidadeClass);
	}
*/
}