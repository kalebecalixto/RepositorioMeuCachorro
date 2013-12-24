package com.karogui.meucachorro.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.karogui.meucachorro.R;
import com.karogui.meucachorro.POJO.CadastroAnimal;
import com.karogui.meucachorro.dao.gerenciadorbanco.DatabaseHelper;
import com.karogui.meucachorro.dao.query.QueryCadastroAnimal;
import com.karogui.meucachorro.ui.adpter.ListaAnimalAdapter;

public class TelaListarActivity extends Activity {
	
	private ListView listViewPet;
	private Context ctx = TelaListarActivity.this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_listar);
		
		
		listViewPet = (ListView) findViewById(R.id.listViewPet);
		
		listaAnimal();
		
		// Atribui evento de click no elemento
		listViewPet.setOnItemClickListener(new OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) 
			{
				Object o =  adapter.getItemAtPosition(position);
				ChamarTela(o);
			}
		});


		
	}
	
	private void ChamarTela (Object o)
	{
		CadastroAnimal animalSelect = new CadastroAnimal();
		
		animalSelect = (CadastroAnimal) o;
		
		Intent i = new Intent();
		Bundle parametro = new Bundle();
		parametro.putInt("seqAnimal", animalSelect.getSeqAnimal());
		i.putExtras(parametro);
		i.setClass(TelaListarActivity.this, TelaManutPetActivity.class);
		startActivity(i);

		
	}
	
	private void listaAnimal() 
	{
		
		String[] colunas = { "seqAnimal, seqCor, seqRaca, nomeAnimal, dtNascAnimal, sexo, registro, observacoes, falecido" };
		ArrayList arrayAnimal = new QueryCadastroAnimal(this).buscarListaAnimais(colunas, null, null, null, "nomeAnimal");
		ListaAnimalAdapter listAdpterAnimal = new ListaAnimalAdapter(this, arrayAnimal); 
		listViewPet.setAdapter(listAdpterAnimal);
		


		
	}
	
	@Override
	public void onBackPressed() {
		
		//chamar tela de cadastro
		Intent i = new Intent();
		i.setClass(TelaListarActivity.this, TelaMainActivity.class);
		startActivity(i);
		finish();
	};
	

	@Override
	public void onPause() {
		finish();
		super.onPause();
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_listar, menu);
		return true;
	}

}
