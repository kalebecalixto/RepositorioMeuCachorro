package com.karogui.meucachorro.POJO;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

public class CorAnimal implements IEntidade
{
	
	private Long seqCor = null;
	private String descrCor = null;
	
	
	public Long getSeqCor() {
		return seqCor;
	}
	public void setSeqCor(Long seqCor) {
		this.seqCor = seqCor;
	}
	public String getDescrCor() {
		return descrCor;
	}
	public void setDescrCor(String descrCor) {
		this.descrCor = descrCor;
	}

	
	//Aqui esta a m�gica do spinner, sem este m�todo voc� ter� uma exce��o, passe para ele oq deseja retonar para spinner  
	public String toString() 
	{  
		//return (this.getSeqCor() + " " + this.getDescrCor());
		return (this.getDescrCor());
	}  
	

}
