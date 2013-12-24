package com.karogui.meucachorro.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;

import com.karogui.meucachorro.R;

public class SplashKaroguiActivityRunable extends Activity

{

	private static final String TAG = "MeuPETSplash";

	private final static int tempoEspera = 3000;

	private Thread tempoExibicao;
	private boolean blnClicou = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_karogui);

		tempoExibicao = new Thread() {
			public void run() {
				try {
					synchronized (this) {
						// Espera por 5 segundos or sai quando
						// o usuário tocar na tela
						wait(tempoEspera);
						blnClicou = true;
					}
				} catch (InterruptedException ex) {
					blnClicou = false;
					finish();
				}

				if (blnClicou) {
					// fechar a tela de Splash
					finish();

					// Carrega a Activity Principal
					Intent i = new Intent();
					i.setClass(SplashKaroguiActivityRunable.this,TelaMainActivity.class);
					startActivity(i);

				}
			}
		};

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_karogui, menu);
		return true;
	}

	@Override
	public void onPause() {
		// garante que quando o usuário clicar no botão
		// "Voltar" o sistema deve finalizar a thread
		tempoExibicao.interrupt();
		Log.i(TAG, "OnPause");
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		Log.i(TAG, "onBackPressed");
		fecharSplash();
	}
	
	
	//click em tela
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			fecharSplash();
		}

		return true;
	}

	private void fecharSplash() {
		// o método abaixo está relacionado a thread de splash
		synchronized (tempoExibicao) {

			if (blnClicou)
				return;

			blnClicou = true;

			// o método abaixo finaliza o comando wait
			// mesmo que ele não tenha terminado sua espera
			tempoExibicao.notifyAll();
		}
	}

	@Override
	protected void onResume() {
		Log.i(TAG, "onResume");
		tempoExibicao.start();
		super.onResume();
	}

}
