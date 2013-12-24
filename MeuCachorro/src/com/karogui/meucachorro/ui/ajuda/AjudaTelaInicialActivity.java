package com.karogui.meucachorro.ui.ajuda;

import com.karogui.meucachorro.R;
import com.karogui.meucachorro.R.layout;
import com.karogui.meucachorro.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

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
