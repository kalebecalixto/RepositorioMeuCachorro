package com.karogui.meucachorro.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import android.widget.ViewFlipper;

import com.karogui.meucachorro.R;
import com.karogui.meucachorro.POJO.CorAnimal;
import com.karogui.meucachorro.POJO.RacaAnimal;
import com.karogui.meucachorro.classes.genericas.Mask;
import com.karogui.meucachorro.dao.gerenciadorbanco.DatabaseHelper;
import com.karogui.meucachorro.dao.query.QueryCadastroAnimal;
import com.karogui.meucachorro.dao.query.QueryCorAnimal;
import com.karogui.meucachorro.dao.query.QueryRacaAnimal;

public class TelaCadastroPetActivity extends Activity {

	private EditText txtNomePet;
	private EditText txtDtNascPet;
	private Spinner spnRaca;
	private Spinner spnCor;
	private Button btSalvar;
	private RacaAnimal seqRacaSelected;
	private CorAnimal seqCorSelected;
	private RadioGroup grupoSexo;
	private String especie;

	RadioGroup rg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.i(DatabaseHelper.class.getName(), "cadastroPet");

		setContentView(R.layout.activity_tela_cadastro_pet);
		
		Log.i(DatabaseHelper.class.getName(), "cadastroPet2");

		txtNomePet = (EditText) this.findViewById(R.id.nomePet);
		txtDtNascPet = (EditText) this.findViewById(R.id.dtNascPet);
		spnRaca = (Spinner) findViewById(R.id.spnRaca);
		spnCor = (Spinner) findViewById(R.id.spnCor);
		grupoSexo = (RadioGroup) findViewById(R.id.rdoSexo);
		
		btSalvar = (Button) findViewById(R.id.btnSalvar);
		btSalvar.setOnClickListener(onClickListenerSalvar);
		
		Intent i = getIntent();
		if(i!=null)
		{
			Bundle parametro = i.getExtras();
			especie = parametro.getString("especie");
		}
		

		// colocando mascara em data
		txtDtNascPet.addTextChangedListener(Mask.insert("##/##/####", txtDtNascPet));

		SpnRacaAnimal(especie);
		SpnCorAnimal();

		//rg = (RadioGroup) findViewById(R.id.rdoSexo);

		//int op = rg.getCheckedRadioButtonId();

	}

	private void SpnCorAnimal() {

		// preechendo o spn de Raca
		// passar colunas a serem buscadas
		String[] colunas = { "seqcor, descrcor" };
		ArrayList arrayDeCor = new QueryCorAnimal(this).buscarCorAnimal(colunas, null, null, null, "descrcor");

		// Cria um ArrayAdapter usando um padrão de layout da classe R do
		// android, passando o ArrayList raca para preencher
		ArrayAdapter<CorAnimal> arrayAdapterCor = new ArrayAdapter<CorAnimal>(this, android.R.layout.simple_spinner_item, arrayDeCor);
		ArrayAdapter<CorAnimal> spinnerArrayAdapter = arrayAdapterCor;
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		Log.i(DatabaseHelper.class.getName(), "joga adapter dentro de spn");
		spnCor.setAdapter(spinnerArrayAdapter);
		Log.i(DatabaseHelper.class.getName(), "jogou");

		// Método do Spinner para capturar o item selecionado
		spnCor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() 
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,int posicao, long id) 
			{
				//pega objeto selecionado e passa para um novo objeto onde da pra trabalhar 
				seqCorSelected = (CorAnimal) parent.getSelectedItem(); 
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

	}

	private void SpnRacaAnimal(String especie) {

		String where = "";
		if (especie.equals("cachorro"))
		{
			where = " seqEspecie =1 ";
		}
		else if(especie.equals("gato"))
		{
			where = " seqEspecie =2 ";
		}
		
		// preechendo o spn de Raca
		// passar colunas a serem buscadas
		String[] colunas = { "seqraca, descrraca, seqespecie" };
		ArrayList arrayDeRaca = new QueryRacaAnimal(this).buscarRacaAnimal(
				colunas, where, null, null, "descrraca");

		Log.i(DatabaseHelper.class.getName(), "carregouArray");

		// Cria um ArrayAdapter usando um padrão de layout da classe R do
		// android, passando o ArrayList raca
		Log.i(DatabaseHelper.class.getName(), "cria adper com padrao de layout");
		ArrayAdapter<RacaAnimal> arrayAdapterRaca = new ArrayAdapter<RacaAnimal>(this, android.R.layout.simple_spinner_item, arrayDeRaca);

		Log.i(DatabaseHelper.class.getName(), "atribui array ao adapter");
		ArrayAdapter<RacaAnimal> spinnerArrayAdapter = arrayAdapterRaca;

		Log.i(DatabaseHelper.class.getName(), "atribuiu array ao spn");

		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		Log.i(DatabaseHelper.class.getName(), "joga adapter dentro de spn");
		spnRaca.setAdapter(spinnerArrayAdapter);
		Log.i(DatabaseHelper.class.getName(), "jogou");

		// Método do Spinner para capturar o item selecionado
		spnRaca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id)
			{
				//pega objeto selecionado e passa para um novo objeto onde da pra trabalhar 
				seqRacaSelected = (RacaAnimal) parent.getSelectedItem(); 
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

	}
	
	
	private OnClickListener onClickListenerSalvar = new OnClickListener() {

		@Override
		public void onClick(View v) 
		{
			 
			String nomeAnimal = txtNomePet.getText().toString();
			String dataNascAnimal = txtDtNascPet.getText().toString();
			
			String sexo = "";
			long racaAnimal = seqRacaSelected.getSeqRaca();
			long corAnimal = seqCorSelected.getSeqCor();
			ContentValues valores = new ContentValues();
			
			switch(grupoSexo.getCheckedRadioButtonId())
			{
				case R.id.F :
					sexo = "F";
					break;
				case R.id.M :	
					sexo = "M";
					break;
					
			}
			

			//Log.i(DatabaseHelper.class.getName(), "sexo :::"+sexo);
			
			//return;
			
			
			if (nomeAnimal.trim().equals("") || dataNascAnimal.trim().equals("")) 
			{
				
				Toast toast = Toast.makeText(TelaCadastroPetActivity.this, "Favor adicionar nome e data de nascimento do animal!", Toast.LENGTH_LONG);
		        toast.show();
				return;
			}
			
			
			String sql = "insert into cadastroanimal (seqAnimal, seqCor, seqRaca, nomeAnimal, dtNascAnimal, sexo, falecido) values (null, "+corAnimal+", "+racaAnimal+", '"+nomeAnimal+"', '"+dataNascAnimal.substring(6, 10)+"-"+dataNascAnimal.substring(3, 5)+"-"+dataNascAnimal.substring(0, 2)+"', '"+sexo+"', 0)";
			
			
			/*valores.put("seqAnimal", "(select Max(coalesce(seqAnimal,1))+1 from cadastroanimal)");
			valores.put("seqCor", corAnimal);
			valores.put("seqRaca", racaAnimal);
			valores.put("nomeAnimal", "'"+nomeAnimal+"'");
			valores.put("dtnascanimal", "'"+dataNascAnimal.substring(6, 10)+"-"+dataNascAnimal.substring(3, 5)+"-"+dataNascAnimal.substring(0, 2)+"'");
			valores.put("sexo", "'"+sexo+"'");
			
			new QueryCadastroAnimal(TelaCadastroPetActivity.this).salvarCadastroAnimal(valores);
			
			*/
			
			
			
			new QueryCadastroAnimal(TelaCadastroPetActivity.this).salvarCadastroAnimal(sql);
			
			Log.i(DatabaseHelper.class.getName(), sql);
			
			

			//chamar tela de cadastro
			Intent i = new Intent();
			i.setClass(TelaCadastroPetActivity.this, TelaMainActivity.class);
			startActivity(i);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_cadastro_pet, menu);
		return true;
	}
	

	public void onBackPressed() {
		
		finish();
		
		//chamar tela de cadastro
		Intent i = new Intent();
		i.setClass(TelaCadastroPetActivity.this, TelaSelectEspecieActivity.class);
		startActivity(i);
	};
	

}
