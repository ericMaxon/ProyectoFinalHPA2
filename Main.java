/*    Integrantes:
 * Daniel Downs 3-746-1308
 * Eric Mason 3-747-343
 * Jose Perez 8-961-274 
 * Johann Zimmermann 8-954-1834
 * Michael Xia 8-944-59
*/

import javax.swing.*;
import java.text.*;

/*
    Clase para la lectura de datos, es una clase auxiliar
*/
class Lectura{
    private double inputD;
    private boolean error;

    //Lectura de Datos double
		
    public double DatosD(String entrada, String mensaje){
		/*
			* entrada, representa los datos leidos por el JOptionPane
			* mensaje, representa el mensaje impreso durante la lectura de los datos
		*/
        do{
            try{
                error = false;
                inputD = Double.parseDouble(entrada);//Aqui se hace la conversion a double
             while((inputD <= 0)){//Validamos para numeros negativos o vacios, siendo representados por el cero
                    JOptionPane.showMessageDialog(null, "No se aceptan numero negativos", "ERROR",JOptionPane.ERROR_MESSAGE);
                    entrada = JOptionPane.showInputDialog(null, mensaje);
                 inputD = Integer.parseInt(entrada);
                }
            
            } catch(NumberFormatException er){
                JOptionPane.showMessageDialog(null, "Conversion erronea de datos, pruebe con valores numericos entero", "ERROR",JOptionPane.ERROR_MESSAGE);
              entrada = JOptionPane.showInputDialog(null,mensaje);
                inputD = Integer.parseInt(entrada);
            }
        }while (error);
        return inputD;
    }
}

//Clases de Usuario

class Circulo{
    protected double radio;

    void Asignar(double radio){
        this.radio=radio;
    }

    public double CalcularLong() { // Metodo que calcula la longitud del circulo
        double longitud;
            longitud = 2* Math.PI* radio;
        return longitud;
    }
    public double CalcularArea() { // Metodo que calcula el area del circulo
        double area;
            area = Math.PI* Math.pow (radio,2);
        return area;
    }
}

class Cilindro extends Circulo {
    protected double altura;

    void Asignar(double altura, double radio){
        super.Asignar(radio);//Por ser sobreescritura de un metodo heredado y queremos mantener su comportamiento pero añadirle algo mas usamos "super", para accedar al comportamiento de ese metodo a sobre escribir// y añadirle algo mas.
        this.altura = altura;
    }

    public double CalcularArea(){//Esta funcion sobreescribe a la funcion CalcularArea de la clase base
        double area;
            area= super.CalcularLong()*altura + 2*super.CalcularArea();//Como sobreescribiremos un metodo base usaremos super."MetodoAHeredar"
        return area;
    }
    public double CalcularVolumen(){
        double volumen;
            volumen = super.CalcularArea()*altura;
        return volumen;
    }
}

class CilindroHueco extends Cilindro{
    private double rInterno;//Radio Interno
    
    void Asignar(double rInterno, double altura, double radio){
        super.Asignar(altura,radio);
        this.rInterno=rInterno;
    }

    public double CalcularLong(){//Funcion que calcula la longitud de un cilindro hueco
        double longitud;
            longitud = 2*Math.PI*(Math.pow(radio,2)-Math.pow(rInterno,2)) + super.CalcularLong()*altura+2*Math.PI*rInterno*altura;
        return longitud;
    }
    public double CalcularVolumen(){//Funcion que calcula el volumen de un cilindro hueco
        double volumen;
            volumen = Math.PI * (Math.pow(radio,2)-Math.pow(rInterno,2)) *altura;
        return volumen;
    }
}
//Clase principal
class Main{
  public static void main(String[] args) {
    Lectura leer = new Lectura ();//leer sera el objeto de la clase lectura
    DecimalFormat decimal= new DecimalFormat("####.00");
    //Declaracion de objetos
		Circulo c; 
    Cilindro cl;
		CilindroHueco ch;
		//Variables a usar dentro del programa
		Icon ima;
		String[] opciones = {"Circulo","Cilindro","Cilindro Hueco"};//Para el cuadro de opciones.
    
		double radio;
    double rInterno;
    double altura;

    int seleccion=0;//Variable usada para el momento de la obtencion de opciones.
    String mensaje;//Variable es para el momento de la impresion de los mensajes de lectura.

// Usamos un do el cual controle la seleccion de las figuras, para luego pedir los valores necesarios dependiendo de la formula que se requiera.
	do{
    seleccion = JOptionPane.showOptionDialog(null,"Que forma geometrica desea evaluar","Cuerpos Redondos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

    seleccion+=1;
      switch(seleccion){
				//Para crear el objeto del circulo
        case 1:
          mensaje=" Introduzca el radio";
    			radio = leer.DatosD(JOptionPane.showInputDialog(mensaje),mensaje);
					c = new Circulo();
					c.Asignar(radio);

   				ima = new ImageIcon(Main.class.getResource("Circulo.png"));
   				
		  		JOptionPane.showMessageDialog(null,"Longitud circular: "+decimal.format(c.CalcularLong())+"\nArea Circular: "+decimal.format(c.CalcularArea()),"Circulo",JOptionPane.QUESTION_MESSAGE, ima);
     		break;

    		case 2:
          mensaje= " Introduzca el radio";
          radio = leer.DatosD(JOptionPane.showInputDialog(mensaje), mensaje);
          mensaje=" Introduzca la altura";
          altura = leer.DatosD(JOptionPane.showInputDialog(mensaje), mensaje);
					cl = new Cilindro();
          cl.Asignar(altura, radio);
          
					ima = new ImageIcon(Main.class.getResource("Cilindro.png"));
					
        	JOptionPane.showMessageDialog(null,"Area Cilindrica: "+decimal.format(cl.CalcularArea())+"\nVolumen cilindrico: "+decimal.format(cl.CalcularVolumen()),"Cilindro", JOptionPane.QUESTION_MESSAGE,ima);
      	break;
          
      	case 3:
          mensaje= " Introduzca el radio";
          radio = leer.DatosD(JOptionPane.showInputDialog(mensaje), mensaje);

          mensaje=" Introduzca la altura";
          altura = leer.DatosD(JOptionPane.showInputDialog(mensaje), mensaje);

          mensaje= " Introduzca el radio interno del cilindro";
					rInterno = leer.DatosD(JOptionPane.showInputDialog(mensaje), mensaje);
          
						while(rInterno>radio){
								JOptionPane.showMessageDialog(null,"No existe cilindro con radio interno mayor que el externo","Error",JOptionPane.ERROR_MESSAGE);
								rInterno = leer.DatosD(JOptionPane.showInputDialog(" Vuelva introducir el radio interno del cilindro"),mensaje);
						}
					ch = new CilindroHueco();
					ch.Asignar(rInterno, altura, radio);
					ima = new ImageIcon(Main.class.getResource("CilindroHueco.png"));
          
					JOptionPane.showMessageDialog(null,"Longitud cilindro hueco: "+decimal.format(ch.CalcularLong())+"\nVolumen Cilindro Hueco: "+decimal.format(ch.CalcularVolumen()),"Cilindro Hueco", JOptionPane.QUESTION_MESSAGE,ima);
      	break;
			}
  	}while(seleccion!=0);
	}
}