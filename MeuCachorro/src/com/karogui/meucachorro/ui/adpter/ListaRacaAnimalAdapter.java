package com.karogui.meucachorro.ui.adpter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;

import com.karogui.meucachorro.POJO.CadastroAnimal;
import com.karogui.meucachorro.POJO.RacaAnimal;

public class ListaRacaAnimalAdapter 
{
	
	private LayoutInflater mInflater;

	public final static int TYPE_SECTION_HEADER = 0;
	public final static int TYPE_NORMAL = 1;

	private List<CadastroAnimal> listaAnimal;
	private List<RacaAnimal> listaRaca;
	private Context context;


}
