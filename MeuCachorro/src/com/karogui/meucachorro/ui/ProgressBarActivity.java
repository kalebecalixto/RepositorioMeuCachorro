package com.karogui.meucachorro.ui;

import com.karogui.meucachorro.R;
import com.karogui.meucachorro.R.layout;
import com.karogui.meucachorro.R.menu;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;

public class ProgressBarActivity extends Activity implements Runnable{

	
	ProgressBar pb;
	Thread td;
	Handler hd;
	int it;
	String especie = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_bar);
		
		
		Intent i = getIntent();
		if(i!=null)
		{
			Bundle parametro = i.getExtras();
			especie = parametro.getString("especie");
		}

		
		
		pb = (ProgressBar) findViewById(R.id.progressBar1);
		
		hd = new Handler();
		td = new Thread(ProgressBarActivity.this);
		 
		td.start();
		
		
		
		
		
		
	}
	
	public void run()
	{
    	it=1;
    	try{
    		while(it<=50){
    			Thread.sleep(50);
    			hd.post(new Runnable (){
    				
    				public void run(){
    					pb.setProgress(it++);
    				}
    			
    		});
    	}
    		
    	finish();	
		//chamar tela de cadastro
		Intent i = new Intent();
		
		
		Bundle parametro = new Bundle();
		parametro.putString("especie", especie);
		i.putExtras(parametro);

		
		i.setClass(ProgressBarActivity.this, TelaCadastroPetActivity.class);
		startActivity(i);

    		
    	}catch (Exception e){}
    	
    	
    	
    }
	
	@Override
	public void onBackPressed() 
	{
		
	}
}
