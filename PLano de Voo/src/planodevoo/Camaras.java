/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planodevoo;

public class Camaras {
    Constantes cte;
    public  Camaras(){
    
}
    //Intergraph
    public void DMC(){
       Constantes.setCamaraID(1);
        cte=new Constantes(7680,13824,12,120);
    }
    
    public void DMC140(){
        Constantes.setCamaraID(2);
        cte=new Constantes(11200,12096,7.2,92);
    }
    
     public void DMC230(){
         Constantes.setCamaraID(3);
        cte=new Constantes(14144,15542,5.2,92);
    }
    
    public void DMC250(){
        Constantes.setCamaraID(4);
        cte=new Constantes(14656,17216,5.6,112);
    }
    
     public void UltraCamD(){
         Constantes.setCamaraID(5);
        cte=new Constantes(7500,11500,9,100);
    }
    
     //Microsoft
    public void UltraCamX(){
        Constantes.setCamaraID(6);
        cte=new Constantes(9420,14430,7.2,100);
    }
    
     public void UltraCamXp(){
        Constantes.setCamaraID(7);
        cte=new Constantes(11310,17310,6,100);
    }
    
    public void UltraCamXpWA(){
        Constantes.setCamaraID(8);
        cte=new Constantes(11310,17310,6,70);
    }
    
    public void ADS(){
        Constantes.setCamaraID(9);
        cte=new Constantes(12000,12000,6.5,62.5);
    }
    public void outra(double s1,double s2,double pixel,double c){
        Constantes.setCamaraID(10);
        cte=new Constantes(s1,s2,pixel,c);
    }
    
}
