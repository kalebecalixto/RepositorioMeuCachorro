package com.karogui.meucachorro.ui;


//classe tela inicial do sistema

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;

import com.karogui.meucachorro.R;
import com.karogui.meucachorro.dao.gerenciadorbanco.DatabaseHelper;
import com.karogui.meucachorro.dao.gerenciadorbanco.DatabaseManager;
import com.karogui.meucachorro.dao.query.QueryCadastroAnimal;
import com.karogui.meucachorro.ui.adpter.ListaAnimalAdapter;
import com.karogui.meucachorro.ui.ajuda.AjudaTelaInicialActivity;

public class TelaInicialActivity extends Activity {
	
	
	private ViewFlipper vfTroca;
	private ListView listViewPet;
	
	ImageButton addPet;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_inicial);
		
		Log.i(DatabaseHelper.class.getName(), "Chamar Banco");
		
		//iniciado o banco de dados
		DatabaseManager.init(this);
		
		Log.i(DatabaseHelper.class.getName(), "Chamou Banco");
		
		vfTroca = (ViewFlipper) this.findViewById(R.id.vfTroca);
		
		//addPet = (ImageButton) this.findViewById(R.id.addPet);
		//addPet.setOnClickListener(onClickListenerAddPet);
		
		listViewPet = (ListView) findViewById(R.id.listViewPet);
		
		listaAnimal();
		
		// Atribui evento de click no elemento
		listViewPet.setOnItemClickListener(new OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) 
			{
				
			}
		});
		
	}
	
	
	
	
	
	private void listaAnimal() 
	{
		
		String[] colunas = { "seqAnimal, seqCor, seqRaca, nomeAnimal, dtNascAnimal, sexo" };
		ArrayList arrayAnimal = new QueryCadastroAnimal(this).buscarListaAnimais(colunas, null, null, null, "nomeAnimal");
		
		ListaAnimalAdapter listAdpterAnimal = new ListaAnimalAdapter(this, arrayAnimal); 
		
		listViewPet.setAdapter(listAdpterAnimal);
		


		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_inicial, menu);
		return true;
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i = new Intent();
		switch (item.getItemId()) {
		case R.id.newPet:
			i.setClass(TelaInicialActivity.this, TelaCadastroPetActivity.class);
			startActivity(i);
			trocarView(2);
			return true;
		case R.id.abriAjuda:
			i.setClass(TelaInicialActivity.this, AjudaTelaInicialActivity.class);
			startActivity(i);
			trocarView(2);
			return true;

			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	

	public void onBackPressed() {

		finish();

		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
	};
	
	private void trocarView(int pPagina) 
	{
		vfTroca.setDisplayedChild(pPagina);
	}
	
}
