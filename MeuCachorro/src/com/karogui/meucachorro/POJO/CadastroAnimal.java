package com.karogui.meucachorro.POJO;

import java.sql.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


public class CadastroAnimal implements IEntidade
{
	private int seqAnimal;
	private int seqCor;
	private int seqRaca;
	private String nomeAnimal;
	private String dataNascAnimal;
	private String sexoAnimal;
	private String registro;
	private String observacoes;
	private boolean falecido;
	

	public int getSeqAnimal() {
		return seqAnimal;
	}

	public void setSeqAnimal(int seqAnimal) {
		this.seqAnimal = seqAnimal;
	}

	public int getSeqCor() {
		return seqCor;
	}

	public void setSeqCor(int seqCor) {
		this.seqCor = seqCor;
	}

	public int getSeqRaca() {
		return seqRaca;
	}

	public void setSeqRaca(int seqRaca) {
		this.seqRaca = seqRaca;
	}

	public String getNomeAnimal() {
		return nomeAnimal;
	}

	public void setNomeAnimal(String nomeAnimal) {
		this.nomeAnimal = nomeAnimal;
	}

	public String getDataNascAnimal() {
		return dataNascAnimal;
	}

	public void setDataNascAnimal(String string) {
		this.dataNascAnimal = string;
	}

	public String getSexoAnimal() {
		return sexoAnimal;
	}

	public void setSexoAnimal(String sexoAnimal) {
		this.sexoAnimal = sexoAnimal;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public boolean isFalecido() {
		return falecido;
	}

	public void setFalecido(boolean falecido) {
		this.falecido = falecido;
	}

}
