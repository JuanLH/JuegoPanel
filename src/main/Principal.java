package main;

import java.util.ArrayList;
import java.util.Random;

class Principal {
	
	static String [] bolas = {"blanca","negra","roja","azul","morada"};
	static Participante [] participante= new Participante[50];;
	static Random rand = new Random();
	
	public static void main(String []args) 
	{
            Participante[] jugadores = new Participante[20];

            int elegidos= 0;
            
            for(int x=0 ; x<50 ; x++)
            {
                System.out.println("Participante #"+x);
                tomarBolas(x);
                setCantJugadas(x);
                if(participante[x].jugadas>0) 
                {
                    jugadores[elegidos] = participante[x];
                    System.out.println(elegidos+"- : -"+jugadores[elegidos].jugadas);
                    elegidos++;
                    System.out.println(elegidos+"--------elegidos---------------");
                    if(elegidos == 20)
                        break;
                }
            }
            
		
	}
	
	public static void tomarBolas(int x) 
	{
            //se llena una lista para poder sacarlas cuando el participante las elija
            ArrayList<String> bolas_restantes = new ArrayList<>();
            for(int i = 0 ; i<4 ; i++)
            {
                bolas_restantes.add(bolas[i]);
            }

            //se le dan 3 oportunidades para sacar bolas al participante 
            for(int i=0 ; i<3 ; i++)
            {
                int numero = rand.nextInt((bolas_restantes.size()));
                String color = bolas_restantes.get(numero);
                participante[x].bolas[i] = color;
                bolas_restantes.remove(numero);
            }

            System.out.println("BOLA 1 "+participante[x].bolas[0]+"\n"
                    +"BOLA 2 "+participante[x].bolas[1]+"\n"
                    +"BOLA 3 "+participante[x].bolas[2]);
        }  
	
	public static void setCantJugadas(int x) 
	{

            int cant_jugadas = 0;
            if(busca_bola(participante[x].bolas,"blanca"))
            {
                System.err.println("Murio por blanca");
                participante[x].jugadas = 0;
            }
            else
            {
                boolean sale = false;
                if(busca_bola(participante[x].bolas,"negra") && !busca_bola(participante[x].bolas,"roja"))
                {
                    System.err.println("murio por negra y roja");
                    participante[x].jugadas = 0;
                }
                else if(busca_bola(participante[x].bolas,"negra") && busca_bola(participante[x].bolas,"roja"))
                {
                    //Se le dan 2 oportunidades para sacar 3 bolas en cada una
                    for(int a=0 ; a < 2 ; a++)
                    {   
                        //Se llena la lista de bolas nuevamente
                        ArrayList<String> bolas_restantes = new ArrayList<>();
                        for(int i = 0 ; i<4 ; i++)
                        {
                            bolas_restantes.add(bolas[i]);
                        }

                        //se le dan 3 oportunidades para sacar bolas al participante 
                        for(int i=0 ; i<3 ; i++)
                        {
                            int numero = rand.nextInt(bolas_restantes.size());
                            String color = bolas[numero];
                            participante[x].bolas[i] = color;
                            bolas_restantes.remove(numero);
                        }

                        if(busca_bola(participante[x].bolas,"negra") && !busca_bola(participante[x].bolas,"roja")){
                            sale = true;
                            System.err.println("Murio negra y no roja");
                            continue;
                        }
                        else{
                            cant_jugadas += 1;
                            participante[x].chance_extra = true;
                            System.err.println("Chance extra negra y roja");
                        }
                    }           
                }

                if(sale){
                    participante[x].jugadas = 0;
                }

                if(!busca_bola(participante[x].bolas,"morada"))
                {
                    cant_jugadas += 4;
                }

                if(participante[x].bolas[0].equals("roja") 
                    && participante[x].bolas[1].equals("morada") 
                    && participante[x].bolas[2].equals("azul") )
                {
                    cant_jugadas += 3;
                }

                if(participante[x].bolas[0].equals("morada") 
                    && participante[x].bolas[1].equals("roja") 
                    && participante[x].bolas[2].equals("azul") )
                {
                    cant_jugadas += 3;
                }

                if(participante[x].bolas[0].equals("roja") 
                    && participante[x].bolas[1].equals("morada") 
                    && participante[x].bolas[2].equals("azul") )
                {
                    cant_jugadas += 2;
                }

                if(participante[x].bolas[0].equals("azul") 
                    && participante[x].bolas[1].equals("roja") 
                    && participante[x].bolas[2].equals("morada") )
                {
                    cant_jugadas += 3;
                }
                participante[x].jugadas = cant_jugadas;

            }
		
	}
	
	public static boolean busca_bola(String [] bolas,String color)
	{
	    for(int x=0 ; x<bolas.length ; x++)
	    {
	        if(bolas[x].equals(color))
	        {
	            return true;
	        }
	    }
	    return false;
	}
}


