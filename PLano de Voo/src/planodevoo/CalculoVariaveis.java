/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planodevoo;

/**
 * 
 * @author joao Rodrigues
 * 
 * Aqui são calculadas todas os parametros necessarios para o plano de  voo
 * 
 * h   -> altura de voo
 * c   -> constante da camara
 * mf  -> modulo da escala
 * Z   -> altura absoluta
 * B   -> base aerea
 * b   -> base fotografica
 * S1  -> lado longitudinal da foto no terreno 
 * S2  -> lado lateral da foto no terreno
 * l   -> sobreposição longitudinal
 * q   -> sobreposição lateral
 * 
 *
 */

public class CalculoVariaveis {
	
	Constantes cte;
	
	public CalculoVariaveis(Constantes cte){
		this.cte=cte;
	}
	
	
	public double moduloEscala(){
		
		return altura()/cte.getC();
	}
	
	public double altura(){
		
		return cte.getMf()*cte.getC();
	}
		
	public double S1(){
		
		return cte.gets1()*microToMetro(cte.getPixel())*cte.getMf();
	}
	
	public double S2(){
		
		return cte.gets2()*microToMetro(cte.getPixel())*cte.getMf();
	}


	public double baseFotografica(){
		
		return altura()*cte.getMf();
	}
	
	
	public double alturaVooAbs(){
		
		return altura()+cte.getZ();
	}
	
	
	public double areaCoberta1Foto(){
		
		return S1()*S2()*Math.pow(cte.getMf(),2);
	}
	
	public double comprimentoBaseArea(){

		return S1()*(1-cte.getLmin()/100);
	}
	
	public double distEixosFiadas(){
		return S2()*(1-cte.getQmin()/100);
	}
	
	public int nModelosFiada(){

		return (int)(cte.getL()/comprimentoBaseArea()+1);
	}
	
	public int nFotosFiada(){
		
		return nModelosFiada()+1;
	}
	public int nFiadasBloco(){
		return (int)(cte.getQ()/distEixosFiadas()+1);
	}
	
	public double areaEstereoModelo(){
		
		return (S1()-2*comprimentoBaseArea())*S2();
	}
	
	public double areaNovaBloco(){
		
		return distEixosFiadas()*distEixosFiadas();
	}
	
	public double tempoExposicaoMax(double mf,double v){
		double a=microToMetro(2); //micro
		return a*cte.getMf()/cte.getV();
	}
	
	public double m2ToKm2(double m){
		
		return m*Math.pow(10,-6);
	}
	
	public double microToMetro(double m){
		
		return m*Math.pow(10,-6);
	}
	

}
