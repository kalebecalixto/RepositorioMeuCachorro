package com.karogui.meucachorro.POJO;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

public class RacaAnimal implements IEntidade
{

	private Long seqRaca = null;
	private String descrRaca = null;
	private int seqEspecie = 0;
	
	
	public Long getSeqRaca() {
		return seqRaca;
	}
	public void setSeqRaca(Long seqRaca) {
		this.seqRaca = seqRaca;
	}
	public String getDescrRaca() {
		return descrRaca;
	}
	public void setDescrRaca(String descrRaca) {
		this.descrRaca = descrRaca;
	}
	
	//Aqui esta a mágica do spinner, sem este método você terá uma exceção, passe para ele oq deseja retonar para spinner  
	public String toString() 
	{  
		//return (this.getSeqRaca() + " " + this.getDescrRaca());
		return (this.getDescrRaca());
	}
	public int getSeqEspecie() {
		return seqEspecie;
	}
	public void setSeqEspecie(int seqEspecie) {
		this.seqEspecie = seqEspecie;
	}  
	
}
