package com.karogui.meucachorro.ui.ajuda;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.karogui.meucachorro.R;

public class AjudaTelaInicialActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajuda_tela_inicial);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajuda_tela_inicial, menu);
		return true;
	}

}
