/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planodevoo;


import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.coords.UTMCoord;


public class App {
	
	public double[] UTMcoord;
	//double[] cotas=new double[12];
        CalculosImplementacao a;
        Constantes cte=new Constantes();
        
	public void aplicaçao(double[] UTMcoord){
		 WriteToFile w=new WriteToFile("planoVoo.kml");
		System.out.println("---1");
		 CalculoVariaveis cal=new CalculoVariaveis(cte);
		 CalculosImplementacao a= new CalculosImplementacao(cte,cal);

                System.out.println("---2");
		cte.setQ(UTMcoord);	
		cte.setL(UTMcoord);
                
               System.out.println("---3");
	
		double[][] fiadas=a.fiadas(UTMcoord);
		double[][] fotos=a.fotos(fiadas);

		w.iniciate(cal, a, cte);
                w.writeReport();
                w.writePontos();
                System.out.println("---4");
		/**
		 * Escreve o ficheiro .kml com os pontos, fiadas e poligono	
		 */
		w.open();
		
		w.openFolder();
		
                System.out.println("---5");
		Geometria g=new Geometria(a.UTMcoordWGS84(UTMcoord));
		w.writeToFile(g.poligono());
		writeFotos(fotos,w,fiadas.length,cal.nFotosFiada(),a);
		writeFiadas(fiadas,w,fiadas.length,cte);
		w.closeFolder();
		
		System.out.println("---6");
	}



	public  void writeFiadas(double[][] coord,WriteToFile w,int nfiadas,Constantes cte){
			Geometria g;
		
			double[][] fiadasWGS84=new double[nfiadas][4];
			for(int i=0;i<fiadasWGS84.length;i++){
				for(int c=0;c<1;c++){
   					fiadasWGS84[i][c*2]=UTMCoord.fromUTM(cte.getZone(), AVKey.NORTH, coord[i][c*2],coord[i][c*2+1]).getLongitude().getDegrees();
					fiadasWGS84[i][c*2+1]=UTMCoord.fromUTM(cte.getZone(), AVKey.NORTH, coord[i][c*2],coord[i][c*2+1]).getLatitude().getDegrees();
                                   
					fiadasWGS84[i][c*2+2]=UTMCoord.fromUTM(cte.getZone(), AVKey.NORTH, coord[i][c*2+2],coord[i][c*2+3]).getLongitude().getDegrees();
					fiadasWGS84[i][c*2+3]=UTMCoord.fromUTM(cte.getZone(), AVKey.NORTH, coord[i][c*2+2],coord[i][c*2+3]).getLatitude().getDegrees();
					
					double[] aux={fiadasWGS84[i][c*2],fiadasWGS84[i][c*2+1],fiadasWGS84[i][c*2+2],fiadasWGS84[i][c*2+3]};
					
                                    g=new Geometria(aux);
				w.writeToFile(g.linha());    
				}
                                
                                
                               
   				
				
			}
	}

	public  void writeFotos(double[][] coord,WriteToFile w, int nfiadas, int nfotos,CalculosImplementacao cal){
		
			Geometria g;
			double[][] fiadasWGS84=new double[nfiadas][nfotos*2];
			
			for(int i=0;i<fiadasWGS84.length;i++){
				for(int c=0;c<nfotos+1;c++){
					fiadasWGS84[i]=cal.UTMcoordWGS84(coord[i]);
					
					double[] aux={fiadasWGS84[i][c*2],fiadasWGS84[i][c*2+1]};
					g=new Geometria(aux);
					w.writeToFile(g.pontos());	
				}	
			}
			
	}
}


