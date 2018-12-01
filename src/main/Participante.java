package main;

public class Participante {
	
    Participante()
    {
        bolas = new String[3];
        int jugadas = 0;
        chance_extra = false;
        money = 0;
        chances = 4;
    }
    
    public void perdedor(){
        money = 0;
        perdio = true;
    }
    public  String [] bolas ;
    public  int jugadas;
    public  boolean chance_extra,perdio;
    public  int money;
    public int chances;
    
}
