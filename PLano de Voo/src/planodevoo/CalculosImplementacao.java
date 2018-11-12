/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planodevoo;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.coords.UTMCoord;

public class CalculosImplementacao {
	private Constantes cte;
	private CalculoVariaveis cal;
	private double alfa;
	private double beta;
	double ms;
        
        
	
	
	public CalculosImplementacao(Constantes cte, CalculoVariaveis cal){
		this.cte=cte;
		this.cal=cal;
		
	}
	

	public double[] UTMcoordWGS84(double[] UTMcoord){
		
		double[] WGS84=new double[UTMcoord.length];
		
                
		int dim=2;// por causa das cotas
		for(int i=0;i<UTMcoord.length/dim;i++){
				
			WGS84[i*dim]=UTMCoord.fromUTM(Constantes.getZone(), AVKey.NORTH, UTMcoord[i*dim],UTMcoord[i*dim+1]).getLongitude().getDegrees();
			WGS84[i*dim+1]=UTMCoord.fromUTM(Constantes.getZone(), AVKey.NORTH, UTMcoord[i*dim],UTMcoord[i*dim+1]).getLatitude().getDegrees();
			
                
                }
		return WGS84;
	}
	
	
	public double[][] fotos(double[][] novasCoord){
		
		double baseReal=cal.comprimentoBaseArea();
		double nfotos=cal.nFotosFiada()+1;
		double numfiadas = cal.nFiadasBloco();
		
		double[][] fotos=new double[(int)numfiadas][(int)nfotos*2];
		
		int dim=2;
		for(int i=0;i<numfiadas;i++){
			for(int c=0,x=-1;c<nfotos;c++,x++){
				
				fotos[i][c*dim]=novasCoord[i][0]+baseReal*x*Math.sin(degToRad(alfa));
				fotos[i][c*dim+1]=novasCoord[i][1]+baseReal*x*Math.cos(degToRad(alfa));
                                
				}
		}
                cte.setCoordFotos(fotos);
		return fotos;
	}
	
	public double[][] fiadas(double[] coord){
		double distancia=cte.getL();
		double disEixos=cal.distEixosFiadas();
		alfa=calculoRumo(coord);
		beta=180-(90+alfa);
		ms=margemSegurança(cte.gets2());
		
		double numfiadas = cal.nFiadasBloco();
		double[][] fiadas=new double[(int)numfiadas][4];
		

		for(int i=0;i<numfiadas;i++){
			for(int c=0;c<1;c++){
				//PONTO 1
				fiadas[i][0]=coord[0]+ms*Math.sin(degToRad(beta));
				fiadas[i][1]=coord[1]-ms*Math.cos(degToRad(beta));
				//System.out.println("M1 "+fiadas[i][0]+" P1 "+fiadas[i][1]);
				//PONTO 2
				fiadas[i][2]=fiadas[i][0]+distancia*Math.sin(degToRad(alfa));
				fiadas[i][3]=fiadas[i][1]+distancia*Math.cos(degToRad(alfa));
				//System.out.println("M2 "+fiadas[i][2]+" P2 "+fiadas[i][3]);
				ms=disEixos;
				}
			
				coord=fiadas[i];
				//System.out.println("bah, po crl");
			
		}
		
		return fiadas;
				
	}
	
	

	public double[] orcamento(){

		double tFiada= cte.gettFiada()*60;
		double v=cte.getV()/3600*1000;
                
  		double Ts=(cal.comprimentoBaseArea()/v)*(cal.nFotosFiada()-1)*cal.nFiadasBloco()+tFiada*(cal.nFotosFiada()-1);
	
                double custoVoo=Ts/3600*cte.getCustoVoo();
		double custoFoto=cal.nFotosFiada()*cal.nFiadasBloco()*cte.getCustoFoto();
		 
                
               
		double[] aux={custoVoo,custoFoto};
		return aux;
	}
	
	
	public double calculoRumo(double[] coord){

		double MW=coord[0];
		double PW=coord[1];
		
		double ME=coord[2];
		double PE=coord[3];
		
		double numerador=ME-MW;
		double denominador=PE-PW;
		
		if(numerador>0 && denominador<0 || numerador<0 && denominador<0){
			return radToDeg(Math.atan((ME-MW)/(PE-PW)))+180;
		}
		else
			return radToDeg(Math.atan((ME-MW)/(PE-PW)));
		
	}
	
	public double radToDeg(double e){
		
		return e*180/Math.PI;
	}
	
	public double degToRad(double e){
		return e*Math.PI/180;
	}


	public int nFiadas(){	
		return cal.nFiadasBloco();
	}
	
	public double margemSegurança(double S2){
		
		return cal.S2()*(.5-cte.getMargemseguranca());
		
	}
	

	

}

