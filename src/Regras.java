
public class Regras {
	public String Id;
	public String regra;
	public double peso;
	public double confianca;
	public int itens;
	
	
	public void insereRegras(String I, String R, double P, double C, int T){
		this.Id = I;
		this.regra = R;
		this.peso = P;
		this.confianca = C;
		this.itens = T;
	}

}
