/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planodevoo;


import java.util.Scanner;

public class Processa {
    
	public static void main(String[] args)  {
            
            
            Scanner s=new Scanner(System.in);
            Camaras camaras=new Camaras();
            Menu menu=new Menu();
            App app=new App();  
            Constantes cte=new Constantes();
            double[] UTMcoord=new double[8];
         
            menu.intialize(s, camaras, cte,app, UTMcoord);
          
            menu.mainMenu();

             
            do{
            System.out.print("\nConfirmar (y/Y): ");
            String c=s.next();
             
            if(c.equals("y")==true || c.equals("Y")==true){
                System.out.print("Programa terminado!\n");
                break;}
            else 
                menu.mainMenu();
                
            
            
            }while(true);
            
                 
            s.close();
        }
        
        
}
	 


