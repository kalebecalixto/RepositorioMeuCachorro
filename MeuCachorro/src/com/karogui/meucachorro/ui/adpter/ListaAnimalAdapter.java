package com.karogui.meucachorro.ui.adpter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.karogui.meucachorro.R;
import com.karogui.meucachorro.R.drawable;
import com.karogui.meucachorro.POJO.CadastroAnimal;
import com.karogui.meucachorro.POJO.RacaAnimal;
import com.karogui.meucachorro.dao.query.QueryRacaAnimal;

public class ListaAnimalAdapter extends ArrayAdapter<CadastroAnimal> 
{
	

	private LayoutInflater mInflater;

	public final static int TYPE_SECTION_HEADER = 0;
	public final static int TYPE_NORMAL = 1;

	private List<CadastroAnimal> listaAnimal;
	private List<RacaAnimal> listaRaca;
	private Context context;

	public ListaAnimalAdapter(Context context, ArrayList<CadastroAnimal> objects) 
	{
		super(context, R.layout.row_animal, objects);
		mInflater = LayoutInflater.from(context);
		this.listaAnimal = objects;
		this.context = context;
	}

	public int getCount() {
		return super.getCount() + 1;
	}

	public int getViewTypeCount() {
		return 2;
	}

	public int getItemViewType(int position) {

		if (position == 0)
			return TYPE_SECTION_HEADER;
		else
			return TYPE_NORMAL;
	}

	public boolean areAllItemsSelectable() {
		return false;
	}

	public boolean isEnabled(int position) {
		return (getItemViewType(position) != TYPE_SECTION_HEADER);
	}

	public CadastroAnimal getItem(int position) {
		return listaAnimal.get(position - 1);
	}

	private String FormataCabecalho() {

		if (listaAnimal.isEmpty())
			return "Nenhum Animal cadastrado";
		else {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(" Selecione um Animal");

			return strBuilder.toString();

		}// else
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		if (position == 0) {

			view = mInflater.inflate(R.layout.listheader, null);


			((TextView) view).setText(FormataCabecalho());

			return view;
		}

		if (view == null) {
			view = mInflater.inflate(R.layout.row_animal, null);
		}

		CadastroAnimal animal = getItem(position);

		if (animal != null) {

			// Obtem os elementos de tela
			
			ImageView imagemFalecido;
			imagemFalecido = (ImageView) view.findViewById(R.id.imageList2);
			TextView txtNomeAnimal = (TextView) view.findViewById(R.id.txtNomeAnimal);
			
			
			

			
			TextView txtRaca = (TextView) view.findViewById(R.id.txtRacaAnimal);
			
			if(animal.isFalecido())
			{
				imagemFalecido.setImageResource(R.drawable.falecido);
			}

			// Popula os elementos de tela
			txtNomeAnimal.setText(animal.getNomeAnimal());
			
			String[] colunas = {"seqRaca, descrRaca, seqEspecie"};
			String where = " seqRaca ="+animal.getSeqRaca();
			ArrayList <RacaAnimal> arrayRaca  = new QueryRacaAnimal(context).buscarRacaAnimal(colunas, where, null, null, "descrRaca");
			txtRaca.setText(arrayRaca.get(0).getDescrRaca());
		}

		return view;
	}



}
