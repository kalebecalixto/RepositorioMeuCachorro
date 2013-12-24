package com.karogui.meucachorro.ui;

import java.util.ArrayList;

import android.R.color;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.karogui.meucachorro.R;
import com.karogui.meucachorro.POJO.CadastroAnimal;
import com.karogui.meucachorro.POJO.CorAnimal;
import com.karogui.meucachorro.POJO.RacaAnimal;
import com.karogui.meucachorro.classes.genericas.Mask;
import com.karogui.meucachorro.dao.gerenciadorbanco.DatabaseHelper;
import com.karogui.meucachorro.dao.query.QueryCadastroAnimal;
import com.karogui.meucachorro.dao.query.QueryCorAnimal;
import com.karogui.meucachorro.dao.query.QueryRacaAnimal;

public class TelaManutPetActivity extends Activity {
	
	private CadastroAnimal animal;
	RacaAnimal raca = new RacaAnimal();
	private int seqAnimal;
	private EditText nome;
	private EditText dtNascAnimal;
	private EditText observacoes;
	private EditText registro;
	private Spinner spnRaca;
	private Spinner spnCor;
	private Button btnAlterar;
	private RacaAnimal seqRacaSelected;
	private CorAnimal seqCorSelected;
	private RadioGroup grupoSexo;
	private RadioGroup grupoFalecido;
	private RadioButton M;
	private RadioButton F;
	
	private RadioButton vivo;
	private RadioButton morto;
	
	private int posixRacaArray;
	private int posixCorArray;
	private String dataFormatada; 


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_manut_pet);
		
		nome = (EditText) findViewById(R.id.nomePet);
		dtNascAnimal = (EditText) findViewById(R.id.dtNascPet);
		observacoes = (EditText) findViewById(R.id.observPet);
		registro = (EditText) findViewById(R.id.pedigree);
		spnRaca = (Spinner) findViewById(R.id.spnRaca);
		spnCor = (Spinner) findViewById(R.id.spnCor);
		grupoSexo = (RadioGroup) findViewById(R.id.rdoSexo);
		grupoFalecido = (RadioGroup) findViewById(R.id.rdoFalecido);
		M = (RadioButton) findViewById(R.id.M);
		F = (RadioButton) findViewById(R.id.F);
		vivo = (RadioButton) findViewById(R.id.vivo);
		morto = (RadioButton) findViewById(R.id.morto);
		btnAlterar = (Button) findViewById(R.id.btnAlterar);
		btnAlterar.setOnClickListener(onClickListenerAlterar);

		
		
		
		// colocando mascara em data
		dtNascAnimal.addTextChangedListener(Mask.insert("##/##/####", dtNascAnimal));
		
		
		
		Intent i = getIntent();
		if(i!=null)
		{
			Bundle parametro = i.getExtras();
			seqAnimal = parametro.getInt("seqAnimal");
		}

		
		
		carregaAnimal(seqAnimal);
		SpnRacaAnimal(String.valueOf(raca.getSeqEspecie()));
		SpnCorAnimal();
		
		
		dataFormatada =  animal.getDataNascAnimal().substring(8, animal.getDataNascAnimal().length());
		dataFormatada += "/"+ animal.getDataNascAnimal().substring(5, 7);
		dataFormatada += "/"+ animal.getDataNascAnimal().substring(0, 4);
		
		

		spnRaca.setSelection(posixRacaArray);
		spnCor.setSelection(posixCorArray);
		nome.setText(animal.getNomeAnimal());
		if(animal.getSexoAnimal().equals("M"))
		{
			M.setChecked(true);
			F.setChecked(false);
		}
		else
		{
			M.setChecked(false);
			F.setChecked(true);
		}
		if(animal.isFalecido())
		{
			morto.setChecked(true);
			vivo.setChecked(false);
		}
		else
		{
			morto.setChecked(false);
			vivo.setChecked(true);
		}
		registro.setText(animal.getRegistro());
		observacoes.setText(animal.getObservacoes());
		dtNascAnimal.setText(dataFormatada);
		
		
	}
	
	
	private void SpnCorAnimal() {

		// preechendo o spn de Raca
		// passar colunas a serem buscadas
		String[] colunas = { "seqcor, descrcor" };
		ArrayList <CorAnimal> arrayDeCor = new QueryCorAnimal(this).buscarCorAnimal(colunas, null, null, null, "descrcor");
		
		Log.i(DatabaseHelper.class.getName(), "coranimal"+animal.getSeqCor());
		
		for(int i = 0; i < arrayDeCor.size(); i++)
		{
			
			if(Integer.parseInt(String.valueOf(arrayDeCor.get(i).getSeqCor())) != Integer.parseInt((String.valueOf(animal.getSeqCor()))))
			{
				
			}else
			{
				posixCorArray = i;
			}
			
			
		}

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
		String[] colunas = { "seqraca, descrraca, seqEspecie" };
		ArrayList <RacaAnimal> arrayDeRaca = new QueryRacaAnimal(this).buscarRacaAnimal(
				colunas, where, null, null, "descrraca");

		Log.i(DatabaseHelper.class.getName(), "carregouArray");

		// Cria um ArrayAdapter usando um padrão de layout da classe R do
		// android, passando o ArrayList raca
		ArrayAdapter<RacaAnimal> arrayAdapterRaca = new ArrayAdapter<RacaAnimal>(this, android.R.layout.simple_spinner_item, arrayDeRaca);

			
		for(int i = 0; i < arrayDeRaca.size(); i++)
		{
			if(arrayDeRaca.get(i).getSeqRaca() == raca.getSeqRaca())
			{
				posixRacaArray = i;
			}
		}
		
				
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


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_manut_pet, menu);
		return true;
	}
	
	
	@Override
	public void onBackPressed() {
		
		
		//chamar tela de cadastro
		Intent i = new Intent();
		i.setClass(TelaManutPetActivity.this, TelaListarActivity.class);
		startActivity(i);
		finish();
	};
	
	@Override
	public void onPause() {
		finish();
		super.onPause();
	}

	
	private OnClickListener onClickListenerAlterar = new OnClickListener() {

		@Override
		public void onClick(View v) 
		{
			 
			String nomeAnimal = nome.getText().toString();
			String dataNascAnimal = dtNascAnimal.getText().toString();
			String observacao = observacoes.getText().toString();
			String pedgre = registro.getText().toString();
			
			String sexo = "";
			int falecido = 0;
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
			
			switch(grupoFalecido.getCheckedRadioButtonId())
			{
				case R.id.morto:
					falecido = 1;
					break;
				case R.id.vivo:
					falecido = 0;
					break;
				
			}
			

			if (nomeAnimal.trim().equals("") || dataNascAnimal.trim().equals("")) 
			{
				
				Toast toast = Toast.makeText(TelaManutPetActivity.this, "Favor adicionar nome e data de nascimento do animal!", Toast.LENGTH_LONG);
		        toast.show();
				return;
			}
			
			
			String update = "";
			
			update += " UPDATE cadastroanimal SET ";
			update += " nomeAnimal =  '"+nomeAnimal+"',";
			update += " dtNascAnimal =  '"+dataNascAnimal.substring(6, 10)+"-"+dataNascAnimal.substring(3, 5)+"-"+dataNascAnimal.substring(0, 2)+"',";
			update += " sexo =  '"+sexo+"',";
			update += " seqRaca =  "+racaAnimal+",";
			update += " seqCor =  "+corAnimal+",";
			update += " falecido =  '"+falecido+"',";
			update += " registro =  '"+pedgre+"',";
			update += " observacoes =  '"+observacao+"'";
			update += " WHERE seqAnimal =  "+animal.getSeqAnimal();
			
			
			
			new QueryCadastroAnimal(TelaManutPetActivity.this).salvarCadastroAnimal(update);
			
			Log.i(DatabaseHelper.class.getName(), update);
			
			

			//chamar tela de cadastro
			Intent i = new Intent();
			i.setClass(TelaManutPetActivity.this, TelaListarActivity.class);
			startActivity(i);
		}
	};
	
	
	public void carregaAnimal(int seqAnimal)
	{
		
		String[] colunas = { "seqAnimal, seqCor, seqRaca, nomeAnimal, dtNascAnimal, sexo, registro, observacoes, falecido" };
		animal = new QueryCadastroAnimal(this).buscarAnimal(colunas, "seqanimal="+seqAnimal, null, null, null);
		
		Log.i(DatabaseHelper.class.getName(), "nomeAnimal"+animal.getNomeAnimal());
		Log.i(DatabaseHelper.class.getName(), "sexo"+animal.getSexoAnimal());
		Log.i(DatabaseHelper.class.getName(), "raca"+animal.getSeqRaca());
		
		ArrayList<RacaAnimal> arrayRaca = new ArrayList<RacaAnimal>();
		String[] colunas2 = { " seqRaca, descrRaca, seqEspecie " };
		arrayRaca = new QueryRacaAnimal(this).buscarRacaAnimal(colunas2, "seqRaca = "+animal.getSeqRaca(), null, null, null);
		
		raca = arrayRaca.get(0);
		
		
		
		
		
	}

}
