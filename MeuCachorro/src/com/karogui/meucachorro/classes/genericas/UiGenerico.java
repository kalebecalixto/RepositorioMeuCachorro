package com.karogui.meucachorro.classes.genericas;

import com.karogui.meucachorro.dao.gerenciadorbanco.DatabaseHelper;
import com.karogui.meucachorro.ui.TelaCadastroPetActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ViewFlipper;

public class UiGenerico extends Activity
{
	
	Context ctx;
	private ViewFlipper vfTroca;
	
	public UiGenerico (Context ctx)
	{
		Log.i(DatabaseHelper.class.getName(), "UIGENERICO");
		this.ctx = ctx;
	}
	
	public void CarregaProximaTela (Class<?> ctxFinal, int tipoTrans)
	{
	
		Log.i(DatabaseHelper.class.getName(), "Carrega proxima tela");
		Intent i = new Intent();
		Log.i(DatabaseHelper.class.getName(), "Carrega proxima tela2");
		i.setClass(ctx, ctxFinal);
		Log.i(DatabaseHelper.class.getName(), "Carrega proxima tela3");
		startActivity(i);
		Log.i(DatabaseHelper.class.getName(), "Carrega proxima tela4");
		trocarView(tipoTrans);
		Log.i(DatabaseHelper.class.getName(), "Carrega proxima tela5");
	}
	
	
	private void trocarView(int pPagina) 
	{
		vfTroca.setDisplayedChild(pPagina);
	}
	
	
	public void FecharAplicacao ()
	{
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
	}

}
