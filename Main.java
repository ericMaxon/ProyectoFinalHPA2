/*    Integrantes:
 * Daniel Downs 3-746-1308
 * Eric Mason 3-747-343
 * Jose Perez 8-961-274 
 * Johann Zimmermann 8-954-1834
*/

import javax.swing.*;
import java.text.*;

/*
    Clase para la lectura de datos, es una clase auxiliar
*/

class Lectura{
    private int inputE;
    private double inputD;
    private boolean error;

    //Metodo modificable para el momento de lectura de Opciones
    public int Opcion(String entrada) {
        do{
            try{
                error=false;
                inputE = Integer.parseInt(entrada);
                while((inputE <= 0)||(inputE>3)){
                    JOptionPane.showMessageDialog(null, "Introduzca un dato correcto", "ERROR",JOptionPane.ERROR_MESSAGE);
                    entrada = JOptionPane.showInputDialog(null, "Introduzca un dato correcto");
                 inputE = Integer.parseInt(entrada);
                }
            
            } catch(NumberFormatException er){
                JOptionPane.showMessageDialog(null, "Conversion erronea de datos, pruebe con valores numericos entero", "ERROR",JOptionPane.ERROR_MESSAGE);
                entrada = JOptionPane.showInputDialog(null, "Introduzca un dato correcto");
                inputE = Integer.parseInt(entrada);
            }
        }while (error);
        
        return inputE;
    }
    //Lectura de Datos double
    public double DatosD(String entrada){//Tenemos el parametro String ya que JOptionPane devuelve String al momento de lectura.
        do{
            try{
                error = false;
                inputD = Double.parseDouble(entrada);//Aqui se hace la conversion a double
             while((inputD <= 0)){//Validamos para numeros negativos o vacios, siendo representados por el cero
                    JOptionPane.showMessageDialog(null, "No se aceptan numero negativos", "ERROR",JOptionPane.ERROR_MESSAGE);
                    entrada = JOptionPane.showInputDialog(null, "Introduzca un dato correcto");
                 inputD = Integer.parseInt(entrada);
                }
            
            } catch(NumberFormatException er){
                JOptionPane.showMessageDialog(null, "Conversion erronea de datos, pruebe con valores numericos entero", "ERROR",JOptionPane.ERROR_MESSAGE);
              entrada = JOptionPane.showInputDialog(null, "Introduzca un dato correcto");
                inputE = Integer.parseInt(entrada);
            }
        }while (error);
        
        return inputD;
    }
}


class Circulo{
    protected double radio;

        Circulo(double radio){
            this.radio = radio;
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

        Cilindro(double altura, double radio){
            super(radio);//Al trabajar con herencia al usar el constructor debemos llamar al constructor base
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
        //Esta clase tiene un constructor largo, ya que hereda muchos atributos, por ende, es necesario llamar su constructor base para asignar los valores heredados
        CilindroHueco(double rInterno, double altura, double radio){
            super(altura, radio);
            this.rInterno = rInterno;
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

class Main{
  public static void main(String[] args) {
    Lectura leer = new Lectura ();
    
    DecimalFormat decimal= new DecimalFormat("####.00");
    Circulo c; 
    Cilindro cl ;
		CilindroHueco ch;
		Icon ima; 
		
		
		String[] opciones = {"Circulo","Cilindro","Cilindro Hueco"};

    double radio;
    double rInterno;
    double altura;
    int seleccion=0;

	do{
    seleccion = JOptionPane.showOptionDialog(null,"Que forma geometrica desea evaluar","Cuerpo Redondos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

    seleccion+=1;
      switch(seleccion){
        case 1:
    			radio = leer.DatosD(JOptionPane.showInputDialog(" Introduzca el radio"));
					c = new Circulo(radio);
   				ima = new ImageIcon("Circulo.png");
   				
		  		JOptionPane.showMessageDialog(null,"Longitud circular: "+decimal.format(c.CalcularLong())+"\nArea Circular: "+decimal.format(c.CalcularArea()),"Circulo",JOptionPane.QUESTION_MESSAGE, ima);
		  		
          
     break;

      case 2:
          radio = leer.DatosD(JOptionPane.showInputDialog(" Introduzca el radio"));
          altura = leer.DatosD(JOptionPane.showInputDialog(" Introduzca la altura"));
					cl = new Cilindro(altura, radio);
					ima = new ImageIcon("Cilindro.png");

        	JOptionPane.showMessageDialog(null,"Area Cilindrica: "+decimal.format(cl.CalcularArea())+"\nVolumen cilindrico: "+decimal.format(cl.CalcularVolumen()),"Cilindro", JOptionPane.QUESTION_MESSAGE,ima);
					

      break;
          
      case 3:
          radio = leer.DatosD(JOptionPane.showInputDialog(" Introduzca el radio"));
          altura = leer.DatosD(JOptionPane.showInputDialog(" Introduzca la altura"));
					rInterno = leer.DatosD(JOptionPane.showInputDialog(" Introduzca el radio interno del cilindro"));
					//Verificar que no se mayor el radio interno
					ch = new CilindroHueco(rInterno,altura,radio);
					ima = new ImageIcon("CilindroHueco.png");
          
					JOptionPane.showMessageDialog(null,"Longitud cilindro hueco: "+decimal.format(ch.CalcularLong())+"\nVolumen Cilindro Hueco: "+decimal.format(ch.CalcularVolumen()),"Cilindro Hueco", JOptionPane.QUESTION_MESSAGE,ima);
      break;
			}
  	}while(seleccion!=0);
	}
}