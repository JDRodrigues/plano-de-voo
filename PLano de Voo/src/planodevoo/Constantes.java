/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planodevoo;


public class Constantes {
        static int camaraID;
	private static int ZONE=29;
	private static double margemSeguranca=0.2;
	private static double s1;
	private static double s2;
	private static double mf;
	private static double pixel; //micrometros
	private static double c;//mm
	private static double lmin;
	private static double qmin;
	private static double L;
	private static double Q;
	private static double b;
	private static double Z;
	private static double v;
	private static double tVoo;
	private static double custoFoto;
	private static double custoVoo;
	private static double tFiada;
        private static double[][] coordFotos;
        private static double[] cotas;
	
        public Constantes(){
            
        }
        
        
	public Constantes(double s1,double s2,double pixel,double c){
		Constantes.s1=s1;
		Constantes.s2=s2;
		Constantes.pixel=pixel;
		Constantes.c=c;
  
	}
        
        public void setCotas(double[] cotas){
            this.cotas=cotas;
        }
        
        public double[] getCotas(){
            return cotas;
        }
        
        
        public void setCoordFotos(double[][] coordFotos){
            this.coordFotos=coordFotos;
        }
        
        public double[][] getCoordFotos(){
            return coordFotos;
        }
        
        public static void setCamaraID(int camaraID){
            Constantes.camaraID=camaraID;
        }
        public static int getCamaraID(){
            return camaraID;
        }

	public static int getZone() {
		return ZONE;
	}

	public static double getMargemseguranca() {
		return margemSeguranca;
	}

	public void sets1(double s1) {
		Constantes.s1=s1;
	}
        
	public double gets1() {
		return s1;
	}	
	
        public void sets2(double s2) {
		Constantes.s2=s2;
	}
        
	public double gets2() {
		return s2;
	}

    /**
     *
     * @param mf
     */
    public void setMf(double mf) {
		Constantes.mf=mf;
	}
        
	public double getMf() {
		return mf;
	}


        public void setPixel(double pixel) {
		Constantes.pixel=pixel;
	}
        
	public double getPixel() {
		return pixel;
	}

        public void setC(double c) {
		Constantes.c=c;
	}
        
	public double getC() {
		return c;
	}


	public double getL() {
		return L;
	}
	
	public void setL(double[] coords) {

		double distancia=Math.sqrt(Math.pow((coords[2]-coords[0]),2)+Math.pow((coords[1]-coords[3]),2));
		Constantes.L=distancia;
	}

	public double getQ() {
		
		return Q;
	}
	
	public void setQ(double[] coords) {
		double distancia=Math.sqrt(Math.pow((coords[6]-coords[0]),2)+Math.pow((coords[1]-coords[7]),2));
		
		Constantes.Q=distancia;
	}

        public void setZ(double Z) {
		Constantes.Z=Z;
	}
        
	public double getZ() {
		return Z;
	}
        
	public void setB(double b) {
		Constantes.b=b;
	}
        
	public double getB() {
		return b;
	}


        public void setV(double v) {
		Constantes.v=v;
	}
        
	public double getV() {
		return v;
	}

        public void setQmin(double qmin) {
            Constantes.qmin=qmin;
	}
        
	public double getQmin() {
		return qmin;
	}

        public void setLmin(double lmin) {
            Constantes.lmin=lmin;
	}
        
	public double getLmin() {
		return lmin;
	}

	public double gettVoo() {
		return tVoo;
	}

	public void settVoo(double tVoo) {
		Constantes.tVoo = tVoo;
	}

	public double getCustoFoto() {
		return custoFoto;
	}

	public void setCustoFoto(double custoFoto) {
		Constantes.custoFoto = custoFoto;
	}

	public double gettFiada() {
		return tFiada;
	}

	public void settFiada(double tFiada) {
		Constantes.tFiada = tFiada;
	}

	public double getCustoVoo() {
		return custoVoo;
	}

	public void setCustoVoo(double custoVoo) {
		Constantes.custoVoo = custoVoo;
	}






	


	
	
	
}
