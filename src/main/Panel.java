/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Component;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author JLHiciano
 */
public final class Panel extends javax.swing.JFrame {

    String [] bolas = {"blanca","negra","roja","azul","morada"};
    char [] casillas = {'1','2','3','4','5','6','7','8','9','X','X','X','R','D','O','/'};
    ArrayList<Participante> participantes;
    ArrayList<Casilla> cDispobibles;
    ArrayList<Casilla> cElegidas;
    Random rand = new Random();
    Participante[] jugadores = new Participante[20];
    
    ArrayList<String> elementos;
    public Panel()  {
        initComponents();
        
        participantes = new ArrayList<>();
        //Instanciando los vectores
        for(int x=0 ; x<50 ; x++)
        {
            participantes.add(new Participante());
            
            if(x<20)
                jugadores[x] = new Participante();
        }
        
        int elegidos= 0;//Cantidad de elegidos
        //Seleccionando los participantes
        while(elegidos<20)
        {
            for(int x=0 ; x<participantes.size() ; x++)
            {
                System.out.println("Participante #"+x);
                tomarBolas(x);
                setCantJugadas(x);
                if(participantes.get(x).jugadas>0) 
                {
                    jugadores[elegidos] = participantes.get(x);
                    elegidos++;
                    if(elegidos == 20)
                        break;
                }
            }
        }
        
        resetPanel();  
    } 
    public void pulsaciones(){
        for(int x=0;x<jugadores.length;x++)
        {
            int pulsaciones = jugadores[x].jugadas * 10;
            for(int i = 0; i< pulsaciones;i++)
            {
                pulsacion();
            }
        }
    }
    public void pulsacion(){
        int casilla = rand.nextInt(cDispobibles.size());
        char element = cDispobibles.get(casilla).valor;
        cDispobibles.remove(casilla);//se saca el valor para que no siga apareciendo
        int boton = rand.nextInt(16)+1;
        while(isPlayedPosition(boton)){
            boton = rand.nextInt(16)+1;
        }
        Casilla c = new Casilla(element, boton);
        cElegidas.add(c);
        pintarBoton(element,boton); 
        System.out.println("pulso");
    }
    public boolean isPlayedPosition(int pos){
        for(Casilla cas : cElegidas){
            if(cas.pos == pos)
                return true;
        }
        return false;
    }
    public void pintarBoton(char element,int boton){
        
        
        if(boton <= 8)
        {
            if(boton == 1)
            {
                jButton1.setText(Character.toString(element));
            }
            
            if(boton == 2)
            {
                jButton2.setText(Character.toString(element));
            }
            
            if(boton == 3)
            {
                jButton3.setText(Character.toString(element));
            }
            
            if(boton == 4)
            {
                jButton4.setText(Character.toString(element));
            }
            
            if(boton == 5)
            {
                jButton5.setText(Character.toString(element));
            }
            
            if(boton == 6)
            {
                jButton6.setText(Character.toString(element));
            }
            
            if(boton == 7)
            {
                jButton7.setText(Character.toString(element));
            }
            
            if(boton == 8)
            {
                jButton8.setText(Character.toString(element));
            }
        }
        else
        {
            if(boton == 9)
            {
                jButton9.setText(Character.toString(element));
            }
            
            if(boton == 10)
            {
                jButton10.setText(Character.toString(element));
            }
            
            if(boton == 11)
            {
                jButton11.setText(Character.toString(element));
            }
            
            if(boton == 12)
            {
                jButton12.setText(Character.toString(element));
            }
            
            if(boton == 13)
            {
                jButton13.setText(Character.toString(element));
            }
            
            if(boton == 14)
            {
                jButton14.setText(Character.toString(element));
            }
            
            if(boton == 15)
            {
                jButton15.setText(Character.toString(element));
            }
            
            if(boton == 16)
            {
                jButton16.setText(Character.toString(element));
            }
        }
        
        
        
    }
    public void resetPanel(){
        cDispobibles = new ArrayList<>();
        cElegidas = new ArrayList<>();
        
        for(char c : casillas)
        {
            cDispobibles.add(new Casilla(c));
        }
        
        Component[] components = this.getContentPane().getComponents();  
        for(Component component : components)
        {
            if(component instanceof JButton){
      
                JButton button = (JButton) component;
                button.setText("?");
                    
                
            }
        }
    }
    public  void tomarBolas(int x) {
        //se llena una lista para poder sacarlas cuando el participante las elija
        ArrayList<String> bolas_restantes = new ArrayList<>();
        for(int i = 0 ; i<4 ; i++)
        {
            bolas_restantes.add(bolas[i]);
        }

        //se le dan 3 oportunidades para sacar bolas al participante 
        for(int i=0 ; i<3 ; i++)
        {
            System.out.println(participantes.get(x));
            int numero = rand.nextInt((bolas_restantes.size()));
            String color = bolas_restantes.get(numero);
            participantes.get(x).bolas[i] = color;
            bolas_restantes.remove(numero);
        }

        System.out.println("BOLA 1 "+participantes.get(x).bolas[0]+"\n"
                +"BOLA 2 "+participantes.get(x).bolas[1]+"\n"
                +"BOLA 3 "+participantes.get(x).bolas[2]);
    }
    public  void setCantJugadas(int x) {

        int cant_jugadas = 0;
        if(busca_bola(participantes.get(x).bolas,"blanca"))
        {
            System.err.println("Murio por blanca");
            participantes.get(x).jugadas = 0;
        }
        else
        {
            boolean sale = false;
            if(busca_bola(participantes.get(x).bolas,"negra") && !busca_bola(participantes.get(x).bolas,"roja"))
            {
                System.err.println("murio por negra y roja");
                participantes.get(x).jugadas = 0;
            }
            else if(busca_bola(participantes.get(x).bolas,"negra") && busca_bola(participantes.get(x).bolas,"roja"))
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
                        participantes.get(x).bolas[i] = color;
                        bolas_restantes.remove(numero);
                    }

                    if(busca_bola(participantes.get(x).bolas,"negra") && !busca_bola(participantes.get(x).bolas,"roja")){
                        sale = true;
                        System.err.println("Murio negra y no roja");
                        continue;
                    }
                    else{
                        cant_jugadas += 1;
                        participantes.get(x).chance_extra = true;
                        System.err.println("Chance extra negra y roja");
                    }
                }           
            }

            if(sale){
                participantes.get(x).jugadas = 0;
            }

            if(!busca_bola(participantes.get(x).bolas,"morada"))
            {
                cant_jugadas += 4;
            }

            if(participantes.get(x).bolas[0].equals("roja") 
                && participantes.get(x).bolas[1].equals("morada") 
                && participantes.get(x).bolas[2].equals("azul") )
            {
                cant_jugadas += 3;
            }

            if(participantes.get(x).bolas[0].equals("morada") 
                && participantes.get(x).bolas[1].equals("roja") 
                && participantes.get(x).bolas[2].equals("azul") )
            {
                cant_jugadas += 3;
            }

            if(participantes.get(x).bolas[0].equals("roja") 
                && participantes.get(x).bolas[1].equals("morada") 
                && participantes.get(x).bolas[2].equals("azul") )
            {
                cant_jugadas += 2;
            }

            if(participantes.get(x).bolas[0].equals("azul") 
                && participantes.get(x).bolas[1].equals("roja") 
                && participantes.get(x).bolas[2].equals("morada") )
            {
                cant_jugadas += 3;
            }
            participantes.get(x).jugadas = cant_jugadas;

        }

    }
    public  boolean busca_bola(String [] bolas,String color){
        for(int x=0 ; x<bolas.length ; x++)
        {
            if(bolas[x].equals(color))
            {
                return true;
            }
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnSimular = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton1.setText("?");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton2.setText("?");

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton3.setText("?");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton4.setText("?");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton5.setText("?");

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton6.setText("?");

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton7.setText("?");

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton8.setText("?");

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton9.setText("?");

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton10.setText("?");

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton11.setText("?");

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton12.setText("?");

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton13.setText("?");

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton14.setText("?");

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton15.setText("?");

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jButton16.setText("?");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        btnSimular.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        btnSimular.setText(">");
        btnSimular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(btnSimular, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(btnSimular, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 903, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimularActionPerformed
        Thread hilo = new Thread( new PintaPantalla());
        hilo.setDaemon(true);
        hilo.start();
    }//GEN-LAST:event_btnSimularActionPerformed
    
    private class Casilla {
        Casilla(char valor){
            this.valor= valor;
        }
        Casilla(char valor,int pos){
            this.valor= valor;
            this.pos = pos;
        }
        public char valor;
        public int pos;

        public char getValor() {
            return valor;
        }

        public void setValor(char valor) {
            this.valor = valor;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }
        
        
    }
    
    private class PintaPantalla implements Runnable { 
  
        public void run() 
        { 
            try {
                //System.out.println(Thread.currentThread().getName()
                //                 + ", executing run() method!");
                for(int x=0;x<jugadores.length;x++)
                {
                     
                    for(int i = 0; i< jugadores[x].jugadas;i++)
                    {
                        int pulsaciones = 10;
                        while(pulsaciones>=1)
                        {
                            sleep(1000);
                            pulsacion();
                            
                            pulsaciones--;
                        }
                        resetPanel();
                    }
                }
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    } 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Panel().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSimular;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
