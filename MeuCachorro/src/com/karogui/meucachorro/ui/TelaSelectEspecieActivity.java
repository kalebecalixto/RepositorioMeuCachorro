package com.karogui.meucachorro.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.karogui.meucachorro.R;

public class TelaSelectEspecieActivity extends Activity {
	
	
	ImageView cadastroCachorro;
	ImageView cadastroGato;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_select_especie);
		
		
		cadastroCachorro = (ImageView) this.findViewById(R.id.especieCachorro);
		cadastroCachorro.setOnClickListener(onclickListenerCadCachorro);
		
		cadastroGato = (ImageView) this.findViewById(R.id.especieGato);
		cadastroGato.setOnClickListener(onclickListenerCadGato);
		
		
	}
	
	
	private OnClickListener onclickListenerCadCachorro = new OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			//chamar tela de cadastro
			Intent i = new Intent();
			
			Bundle parametro = new Bundle();
			parametro.putString("especie", "cachorro");
			i.putExtras(parametro);
			i.setClass(TelaSelectEspecieActivity.this, ProgressBarActivity.class);
			startActivity(i);
		}
	};
	
	private OnClickListener onclickListenerCadGato = new OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			
			//chamar tela de cadastro
			Intent i = new Intent();
			
			Bundle parametro = new Bundle();
			parametro.putString("especie", "gato");
			i.putExtras(parametro);
			i.setClass(TelaSelectEspecieActivity.this, ProgressBarActivity.class);
			startActivity(i);
	
		}
	};
	
	@Override
	public void onBackPressed() {
		
		//chamar tela de cadastro
		Intent i = new Intent();
		i.setClass(TelaSelectEspecieActivity.this, TelaMainActivity.class);
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
		getMenuInflater().inflate(R.menu.tela_select_especie, menu);
		return true;
	}
	
	

}

