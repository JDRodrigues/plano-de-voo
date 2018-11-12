/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planodevoo;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WriteToFile {
	PrintWriter writer,w,w2;
	String out;
        CalculoVariaveis cal;
        CalculosImplementacao a;
        Constantes cte;

        
	
	public WriteToFile(String out){

		this.out=out;
	}
        public void iniciate(CalculoVariaveis cal,CalculosImplementacao a,Constantes cte){
            this.cal=cal;
            this.a=a;
            this.cte=cte;
        }
        
        
	
	
	public void open(){
		try {
			writer = new PrintWriter(out, "UTF-8");
			writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			writer.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
                       
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
	}
	
	public void openFolder(){
            
		writer.println("<Folder>");
                String style="<Style id=\"sn_placemark_circle\">\n" +
"		<IconStyle>\n" +
"			<scale>1.2</scale>\n" +
"			<Icon>\n" +
"				<href>http://maps.google.com/mapfiles/kml/shapes/placemark_circle.png</href>\n" +
"			</Icon>\n" +
"		</IconStyle>\n" +
"		<ListStyle>\n" +
"		</ListStyle>\n" +
"	</Style>"
                        + "<StyleMap id=\"msn_placemark_circle\">\n" +
"		<Pair>\n" +
"			<key>normal</key>\n" +
"			<styleUrl>#sn_placemark_circle</styleUrl>\n" +
"		</Pair>\n" +
"		<Pair>\n" +
"			<key>highlight</key>\n" +
"			<styleUrl>#sh_placemark_circle_highlight</styleUrl>\n" +
"		</Pair>\n" +
"	</StyleMap>";
                writer.println(style);
	}
	
	
	public void writeToFile(String texto){
            
   		writer.println(texto); 
	}

        public void closeFolder(){
		writer.println("</Folder>");	
		writer.println("</kml>");	
		writer.close();
	}
        
        public void writeReport() {
            try {
                w=new PrintWriter("relatorio.txt", "UTF-8");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(WriteToFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Ficheiro não encontrado");
            }
            
            String[] cam={"Intergraph - DMC","Intergraph - DMC II 140","Intergraph - DMC II 230","Intergraph - DMC II 250",
            "Microsoft - UltraCamD","Microsoft - UltraCamX","Microsoft - UltraCamXp","Microsoft -UltraCamXp WA",
           "Leica - ADS" };
            
            w.write("                   RELATÓRIO \r\n");
            w.write("                  Plano de Voo \r\n\r\n");
            w.write(Calendar.getInstance().getTime().toString());
            w.write("\r\n\r\nDados introduzidos pelo utilizador:\r\n");
            w.write("\r\nCâmara utilizada: "+cam[Constantes.getCamaraID()-1]);
            w.write("\r\nCusto de cada fotografia: "+cte.getCustoFoto()+" €");
            w.write("\r\nCusto de voo : "+cte.getCustoVoo()+" €/h");
            w.write("\r\nÁrea a levantar: "+cte.getL()+" m x "+cte.getQ()+" m");
            w.write("\r\nCota média do terreno: "+cte.getZ()+" m");
            w.write("\r\nMódulo da escala das fotografia: "+cte.getMf()+" m");
            w.write("\r\nSobreposição longitudinal: "+cte.getLmin()+"%");
            w.write("\r\nSobreposição lateral: "+cte.getQmin()+"%");
            w.write("\r\nTempo de voo na troca de fiada: "+cte.gettFiada()+" min\r\n");
            
            
            w.write("\r\n\r\nResultados:\r\n");
            w.write("\r\nVelocidade de voo: "+cte.getV()+" km\\h");
            w.write("\r\nComprimetno da base aérea: "+cal.comprimentoBaseArea()+" m");
            w.write("\r\nDistancia entre eixos de fiadas: "+cal.distEixosFiadas()+" m");
            w.write("\r\nNúmero de Fiadas: "+cal.nFiadasBloco());
            w.write("\r\nNúmero de modelos por fiada´: "+cal.nModelosFiada());
            w.write("\r\nNúmero de fotografias por fiada: "+cal.nFotosFiada());
            w.write("\r\nTempo de voo: "+a.orcamento()[0]/cte.getCustoVoo());
            
            w.write("\r\n\r\nOrçamento:\r\n");
            w.write("Preço das fotografias: "+a.orcamento()[1]+" €");
            w.write("\r\nPreço do voo: "+a.orcamento()[0]+" €");
            w.write("\r\nPreço total: "+(a.orcamento()[0]+a.orcamento()[1])+" €");
            w.close();
            
            
            
        }
        
 
        
        public void writePontos(){
            try {
                w2=new PrintWriter("pontos.txt", "UTF-8");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(WriteToFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Ficheiro não encontrado");
            }
            double[][] coord=cte.getCoordFotos();
            w2.write("Lista das coordenadas de todos os pontos de tomada de fotos\r\n\r\n ");
            w2.write("Cordenadas dos pontos\r\n");
            int id=1000;
            w2.write(" ID            X(m)                Y(m)               Z(m)\r\n");
            for(int i=0;i<cal.nFiadasBloco();i++){
                for(int c=0;c<cal.nFotosFiada();c++){
                    w2.write(id+"   "+coord[i][c*2]+"    "+coord[i][c*2+1]+"     "+cal.alturaVooAbs()+"\r\n");
                    id++;
                }
            }
            
            w2.close();
            
        }
}

