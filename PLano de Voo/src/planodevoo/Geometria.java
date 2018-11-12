/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planodevoo;

/**
 *
 * @author Joana
 */
public class Geometria extends Processa{
	private double[] coordenadas;
	private String text;
	private String style;
        
	public Geometria(double coords){
		double[] aux=new double[1];
		
		aux[0]=coords;
		
		this.coordenadas=aux;
	}
	
	public Geometria(double[] coords){
		this.coordenadas=coords;
		
	}
	
	public String coordenadas(){
            
		String coords="   <coordinates>\r\n";
		for(int i=0;i<coordenadas.length-1;i++)
			if((i+1)%2==0){
				coords+=(String.valueOf(coordenadas[i]));
				coords+="\r\n";}
			else
				coords+=(String.valueOf(coordenadas[i])+",");
			
		coords+=coordenadas[coordenadas.length-1]+"\r\n   </coordinates>\r\n";
		return coords;
		}
	
	
	public String pontos(){
		text="<Placemark>\r\n"
                        + "<styleUrl>#msn_placemark_circle</styleUrl>"
				+ "<Point>\r\n"
				+ "   <altitudeMode>clampedToGround</altitudeMode>\r\n";
				text+=coordenadas()
				+ "  </Point>\r\n"
				+ " </Placemark>\r\n";
		return text;
	}
	public String linha(){
		text="<Placemark>\r\n"
				+ " <LineString>\r\n"
				+ "   <altitudeMode>clampedToGround</altitudeMode>\r\n";
				text+=coordenadas()
				+ "  </LineString>\r\n"
				+ " </Placemark>\r\n";
		return text;
	}
	public String poligono(){
              style="<Style id=\"yellow\">\n" +
                             "  <LineStyle>\n" +
                                    "    <width>2.5</width>\n" +
                              "  </LineStyle>\n" +
                               "  <PolyStyle>\n" +
                                    "    <color>3214F0DC</color>\n" +
                                "  </PolyStyle>\n" +
                       "</Style>";    
            
		text="<Placemark>\r\n"
                                +"<name>Área de levantamento.kml</name>"
                                +" <styleUrl>#yellow</styleUrl>"
				+ " <Polygon>\r\n"
				+ "   <altitudeMode>clampedToGround</altitudeMode>\r\n"
				+ "    <outerBoundaryIs>\r\n"
				+ "     <LinearRing>\r\n";
				text+=coordenadas()
				+ "    </LinearRing>\r\n"
				+ "   </outerBoundaryIs>\r\n"
				+ "  </Polygon>\r\n"
				+ " </Placemark>\r\n";
		return style+text;
	}
	


}

