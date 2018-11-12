/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planodevoo;


import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 *
 * @author Joana
 */
public class Menu {
    Scanner s;
    Camaras camaras;
    Constantes cte;
    double[] UTMcoord;
    App app;
    String nome="";
    
    public void intialize(Scanner s,Camaras camaras,Constantes cte,App app,double[] UTMcoord){
        this.s=s;
        this.camaras=camaras;
        this.cte=cte;
        this.UTMcoord=UTMcoord;
        this.app=app;
    }
            
    
    public Menu(){
    
}
    public void mainMenu(){
        boolean end = false;
        
        do{
        System.out.println("       PLANO DE VOO\n\n");
        
        System.out.println("Novo projecto...............1");
        System.out.println("Abrir projecto..............2");
        System.out.println("Guardar projecto............3");
        System.out.println("Sair........................0\n");
        System.out.print("Opção-> ");
        int opcao=s.nextInt();
        switch(opcao){
            case(1):{
                 menuCommands();
                    }continue;
           case(2):{
                System.out.print("Nome: ");
                String file2=s.next();
                load(file2);
                menuCommands();
           }continue;
           case(3):{
               System.out.print("Nome: ");
               String file1=s.next();
               save(file1);
           }continue;
           case(0):{
               end=true;
               
           }
    }
        
        }while(!end);
    }
    
    public void save(String name){
           try{
    	    PrintWriter writer = new PrintWriter("saved files"+"/"+name+".dat", "UTF-8");
    	    
            writer.write(String.valueOf(UTMcoord[0]));
            for(int i=1;i<UTMcoord.length;i++){
                
                writer.write(","+UTMcoord[i]);
  
            }
            writer.write(String.valueOf("\n"+cte.getMf()+"\n"));
            writer.write(String.valueOf(cte.getLmin()+"\n"));
            writer.write(String.valueOf(cte.getQmin()+"\n"));
            writer.write(String.valueOf(cte.getV()+"\n"));
            writer.write(String.valueOf(cte.gettFiada()+"\n"));
            writer.write(String.valueOf(cte.getCustoVoo()+"\n"));
            writer.write(String.valueOf(cte.getCustoFoto()+"\n"));
            writer.write(String.valueOf(cte.getZ()+"\n"));
            
            switch(Constantes.getCamaraID()){
            case 1:
                writer.write("DMC");break;
            case 2:
                writer.write("DMC140");break;
            case 3:
                writer.write("DMC230");break;
            case 4:
                writer.write("DMC250");break;
            case 5:
               writer.write("UltraCamD");break;
            case 6:
                writer.write("UltraCamX");break;
            case 7:
                writer.write("UltraCamXp");break;
            case 8:
                writer.write("UltraCamXpWA");break;
            case 9:
                writer.write("ADS");break;
            case 10:{
                 writer.write("Outra\n");
                 writer.write(String.valueOf(cte.gets1())+"\n");
                 writer.write(String.valueOf(cte.gets2())+"\n");
                 writer.write(String.valueOf(cte.getPixel())+"\n");
                 writer.write(String.valueOf(cte.getC())+"\n");
            } break;
              
            }
            
    	    writer.close();
            
    	} catch (Exception e) {
    		System.out.println("Ficheiro não encontrado");
        
    	}
    }
    public void load(String nome){
              String aux="";
       try {
            for (String line : Files.readAllLines(Paths.get("saved files"+"/"+nome+".dat"))) {            
                aux+=line+"/";
                 
            }
            
            
        String[] aux2=aux.split("/");
        String[] auxCoords=aux2[0].split(",");
     
        for(int i=0;i<auxCoords.length;i++){
            UTMcoord[i]=Double.parseDouble(auxCoords[i]);
     
        }
        cte.setMf(Double.parseDouble(aux2[1]));
        cte.setLmin(Double.parseDouble(aux2[2]));
        cte.setQmin(Double.parseDouble(aux2[3]));
        cte.setV(Double.parseDouble(aux2[4]));
        cte.settFiada(Double.parseDouble(aux2[5]));
        cte.setCustoVoo(Double.parseDouble(aux2[6]));
        cte.setCustoFoto(Double.parseDouble(aux2[7]));
        
        cte.setZ(Double.parseDouble(aux2[8]));
        String camara=aux2[9];
        
        switch(camara){
            case "DMC":
                camaras.DMC();break;
            case "DMC140":
                camaras.DMC140();break;
            case "DMC230":
                camaras.DMC230();break;
            case "DMC250":
                camaras.DMC250();break;
            case "UltraCamD":
                camaras.UltraCamD();break;
            case "UltraCamX":
                camaras.UltraCamX();break;
            case "UltraCamXp":
                camaras.UltraCamXp();break;
            case "UltraCamXpWA()":
                camaras.UltraCamXpWA();break;
            case "ADS":
                camaras.ADS();break;
            case "Outra":{
               cte.sets1(Double.parseDouble(aux2[10]));
               cte.sets2(Double.parseDouble(aux2[11]));
               cte.setPixel(Double.parseDouble(aux2[12]));
               cte.setC(Double.parseDouble(aux2[13]));
                
                  camaras.outra(cte.gets1(), cte.gets2(), cte.getPixel(), cte.getC());

            }break;
        }
            
            
        } catch (IOException ex) {
           // Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("Ficheiro não encontrado");
        }
        
    }
    
  
    
    public void menuCommands(){
        
            
         boolean end = false;
         menu: do{
         System.out.println("\nDefinir Coordenadas.........1");
         System.out.println("Definir constantes de voo...2");
         System.out.println("Definir Câmara fotográfica..3");
         System.out.println("Sair........................0\n");
         System.out.print("Opção-> ");
         
         int opc=s.nextInt();
         
         switch(opc){
             case 1:
                 coordenadas();continue;
             case 2:
                 menuAviao();continue;
             case 3:
                 camara();continue;
             case 0:{
                   app.aplicaçao(UTMcoord); 
                   System.out.println("aki menu");
             }break menu; 
         }
    
        }while(!end);
   
    } 

    public void coordenadas(){
       

       for(int i=0;i<4;i++){
                 switch (i) {
                     case 0:
                         System.out.print("\nCanto superior esquerdo: ");
                         break;
                     case 1:
                         System.out.print("\nCanto superior direito: ");
                         break;
                     case 2:
                         System.out.print("\nCanto inferior direito: ");
                         break;
                     default:
                         System.out.print("\nCanto inferior esquerdo: ");
                         break;
                 }
             System.out.print("\nE: ");
             UTMcoord[i*2]=s.nextDouble();
             System.out.print("N: ");
             UTMcoord[i*2+1]=s.nextDouble();
             }
       
    }
    
    public void menuAviao(){
        boolean end = false;
        do{
             System.out.print("\nEscala: ");
             double mf=s.nextDouble();
             
             System.out.print("\nSobreposição longitudinal: ");
             double l=s.nextDouble();
            
             System.out.print("\nSobreposição lateral: ");
             double q=s.nextDouble();
            
             System.out.print("\nVelocidade de voo (km/h): ");
             double v=s.nextDouble();
             
             System.out.print("\nTempo na mudança de fiada (min): ");
             double tfiada=s.nextDouble();
             
             System.out.print("\nCusto de voo por hora: ");
             double custoVoo=s.nextDouble();
             
             System.out.print("\nCusto por cada foto: ");
             double custoFoto=s.nextDouble();
             
             System.out.print("\nCota média (m): ");
             double cota=s.nextDouble();
             
             
             System.out.println("\nConcluir...........1");
             System.out.println("Corrigir...........2");
             int opc=s.nextInt();
             if(opc==1){
                cte.setMf(mf);
                cte.setLmin(l);
                cte.setQmin(q);
                cte.setV(v);
                cte.settFiada(tfiada);
                cte.setCustoVoo(custoVoo);
                cte.setCustoFoto(custoFoto);
                cte.setZ(cota);
                
                 break;
             }
             else if(opc==0)
                 continue;
        }while(!end);
    }


    public void camara(){
        
    
        boolean end = false;
        camara: do{
        System.out.println("Marcas\n");
        System.out.println("Intergraph..........1");
        System.out.println("Microsoft...........2");
        System.out.println("Leica...............3");
        System.out.println("Outra...............4");
        System.out.println("Concluir............0\n");
        System.out.print("Opção-> ");
        int opc=s.nextInt();
        
      
        switch(opc){
            case(1):{
                System.out.println("Intergraph\n");
                System.out.println("DMC..............1");
                System.out.println("DMC II 140.......2");
                System.out.println("DMC II 230.......3");
                System.out.println("DMC II 250.......4\n");
                System.out.print("Opção-> ");
                int opc2=s.nextInt();
                
                switch(opc2){
                    case(1):
                        camaras.DMC();continue;                                       
                    case(2):
                        camaras.DMC140();continue;
                    case(3):
                        camaras.DMC230();continue;
                    case(4):
                        camaras.DMC250();break;
                        
                }
                
                
            }continue;
            case(2):{
                System.out.println("Microsoft\n");
                System.out.println("UltraCamD..............1");
                System.out.println("UltraCamX..............2");
                System.out.println("UltraCamXp.............3");
                System.out.println("UltraCamXp WA..........4\n");
                System.out.print("Opção-> ");
                int opc3=s.nextInt();
                
                switch(opc3){
                    case(1):
                        camaras.UltraCamD();continue;                                       
                    case(2):
                        camaras.UltraCamX();continue;
                    case(3):
                        camaras.UltraCamXp();continue;
                    case(4):
                        camaras.UltraCamXpWA();break;
                }
            }continue;
            case(3):{
               System.out.println("Leica\n");
               System.out.println("ADS..............1");
               int opc4=s.nextInt();
               if(opc4==1)
                   camaras.ADS();break;
                
            } 
            case(4):{
                System.out.print("s1: ");
                double s1=s.nextDouble();
                System.out.print("s2: ");
                double s2=s.nextDouble();
                System.out.print("pixel: ");
                double pixel=s.nextDouble();
                System.out.print("c: ");
                double c=s.nextDouble();
                camaras.outra(s1, s2, pixel, c);
            }continue;

            case(0):
                break camara;
                
            }
                

        }while(!end);

    }

    public void clear(){
        for(int i=0;i<15;i++){
            System.out.print("\n");
        }
    }
   
}