package com.karogui.meucachorro.ui;

import com.karogui.meucachorro.R;
import com.karogui.meucachorro.R.layout;
import com.karogui.meucachorro.R.menu;
import com.karogui.meucachorro.dao.gerenciadorbanco.DatabaseHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class TelaMainActivity extends Activity 
{
	
	ImageView listarPet;
	ImageView cadastrarPet;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_main);
		
		Log.i(DatabaseHelper.class.getName(), "Tela Inicial");
		
		cadastrarPet = (ImageView) this.findViewById(R.id.newPet);
		cadastrarPet.setOnClickListener(onClickListenerCadastrarPet);
		
		listarPet = (ImageView) this.findViewById(R.id.listar);
		listarPet.setOnClickListener(onClickListenerListarPet);

		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_main, menu);
		return true;
	}
	
	
	private OnClickListener onClickListenerListarPet = new OnClickListener() {

		@Override
		public void onClick(View v) {
			//chamar tela de cadastro
			Intent i = new Intent();
			i.setClass(TelaMainActivity.this, TelaListarActivity.class);
			startActivity(i);
		}
	};
	
	private OnClickListener onClickListenerCadastrarPet = new OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{

			Intent i = new Intent();
			i.setClass(TelaMainActivity.this, TelaSelectEspecieActivity.class);
			startActivity(i);
		}
	};

	public void onBackPressed() {

		

		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startActivity(startMain);
		finish();
	};

	@Override
	public void onPause() {
		finish();
		super.onPause();
	}
	@Override
	public void onDestroy() {
		finish();
		super.onDestroy();
	}
	

}
