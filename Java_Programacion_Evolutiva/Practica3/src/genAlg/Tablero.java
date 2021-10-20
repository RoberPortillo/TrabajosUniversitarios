package genAlg;

import java.awt.Color;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import funciones.Arbol;

public class Tablero extends JFrame{
	private char tablero[][] = new char[32][32];
	private int contadorComida;
	private int posXHormiga;
	private int posYHormiga;
	private int numPasos;
	private String horientacion;
	
	public Tablero() {
		for(int i = 0; i < 32; i++) {
			for(int j = 0;j < 32; j++) {
				this.tablero[i][j] = '0';
			}
		}
	}
	
	
	public void initTablero() {
		this.numPasos = 0;
		this.contadorComida = 0;
		this.posXHormiga = 0;
		this.posYHormiga = 0;
		this.horientacion = "este";
		this.cargarTablero();
		
	}
	private Tablero(Tablero src) {
		this.contadorComida = src.contadorComida;
		this.posXHormiga = src.posXHormiga;
		this.posYHormiga = src.posYHormiga;
		this.horientacion = src.horientacion;
		this.numPasos = src.numPasos;
		for(int i = 0; i < 32; i++) {
			for(int j = 0; j < 32;j++) {
				this.tablero[i][j] = src.tablero[i][j];
			}
		}
	}
	
	public Tablero clone() {
		return new Tablero(this);
	}
	
	public void avanza() {
		this.numPasos++;
		int nuevaPosX = this.posXHormiga;
		int nuevaPosY = this.posYHormiga;
		if(this.horientacion.equals("norte")) nuevaPosY--; //Nos movemos al norte
		else if(this.horientacion.equals("sur")) nuevaPosY++; //Nos movemos al sur
		else if(this.horientacion.equals("este")) nuevaPosX++; //Nos movemos al este
		else if(this.horientacion.equals("oeste")) nuevaPosX--; //Nos movemos al oeste
		
		if(nuevaPosY < 32 && nuevaPosY >= 0 && nuevaPosX < 32 && nuevaPosX >= 0) {
			this.tablero[this.posYHormiga][this.posXHormiga] = '*'; //Ponemos la casilla en la que estaria a vacio
			this.posXHormiga = nuevaPosX;
			this.posYHormiga = nuevaPosY;
			if(this.tablero[this.posYHormiga][this.posXHormiga] == '#') this.contadorComida++;
			this.tablero[this.posYHormiga][this.posXHormiga] = '@';
		}
	
		
	}
	
	public void giraDerecha() {
		this.numPasos++;
		if(this.horientacion.equals("norte")) this.horientacion = "este";
		else if(this.horientacion.equals("este")) this.horientacion = "sur";
		else if(this.horientacion.equals("sur")) this.horientacion = "oeste";
		else if(this.horientacion.equals("oeste"))this.horientacion = "norte";
	}
	
	public void giraIzquierda() {
		this.numPasos++;
		if(this.horientacion.equals("norte")) this.horientacion = "oeste";
		else if(this.horientacion.equals("oeste")) this.horientacion = "sur";
		else if(this.horientacion.equals("sur")) this.horientacion = "este";
		else if(this.horientacion.equals("este")) this.horientacion = "norte";
	}
	
	
	//Carga el tablero
	public void cargarTablero() {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("src\\caminoDeSantaFe.txt"));
			int w = 0;
			int i = 0;
			String bfRead;
			while((bfRead = bf.readLine()) != null) {
				w = 0;
				for(int j = 0; j < bfRead.length();j = j + 2) {
					if(bfRead.charAt(j) == '#') {
						this.tablero[i][w] = bfRead.charAt(j);	
					}else if(bfRead.charAt(j) == '@') {
						this.tablero[i][w] = bfRead.charAt(j);
						this.posXHormiga = w;
						this.posYHormiga = i;
					}
					w++;
				}
				i++;
				
			}
		}catch(Exception e) {
			System.err.println("No se encontro archivo");
		}
	}
	
	public void imprimirTablero() {
		String texto = "";
		for(int i = 0; i < 32 ; i++) {
			for(int j = 0; j < 32; j++) {
				texto += "|"+this.tablero[i][j];
			}
			texto += "|\n";
			texto+="-----------------------------------------------------------------------------------------------\n";
		}
		
		System.out.println(texto);
		System.out.println(this.contadorComida);
		System.out.println(this.posXHormiga);
		System.out.println(this.posYHormiga);
	}
	
	
	//Pinta el tablero
	public JPanel pintarTablero() {
		JPanel panel = new JPanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(324, 11, 460, 439);
		setContentPane(panel);
		panel.setLayout(null);
		
		
		for(int i = 0; i < 32; i++) {
			for(int j = 0; j < 32; j++) {
				JLabel etiqueta = new JLabel();
				etiqueta.setBounds(j * 10,i * 10, 9,9);
				etiqueta.setOpaque(true);
				if(tablero[i][j] == '0') etiqueta.setBackground(Color.CYAN);
				else if(tablero[i][j] == '#') etiqueta.setBackground(Color.RED);
				else if(tablero[i][j] == '*') etiqueta.setBackground(Color.BLACK);
				else if(tablero[i][j] == '@') etiqueta.setBackground(Color.YELLOW);
				
				panel.add(etiqueta);
			}
		}
		
		return panel;
		
		
	}
	
	public boolean hayComida() {
		int nuevaPosX = this.posXHormiga;
		int nuevaPosY = this.posYHormiga;
		if(this.horientacion.equals("norte")) nuevaPosY--; //Nos movemos al norte
		else if(this.horientacion.equals("sur")) nuevaPosY++; //Nos movemos al sur
		else if(this.horientacion.equals("este")) nuevaPosX++; //Nos movemos al este
		else if(this.horientacion.equals("oeste"))nuevaPosX--; //Nos movemos al oeste
		
		if(nuevaPosY < 32 && nuevaPosY >= 0 && nuevaPosX < 32 && nuevaPosX >= 0) {
			
			if(this.tablero[nuevaPosY][nuevaPosX] == '#') return true;
			else return false;
		}else return false;
	}
	
	//Mirar en que momento descienden los pasos TODO
	public void ejecutaArbol(Arbol ab){
		 //mientras no se haya acabado el tiempo ni la comida
		 if (ab != null  && this.contadorComida < 89) {
			 
		 //acciones a realizar en función del nodo en el que estemos
			 if(ab.getNodo().equals("P3")){
				 ejecutaArbol(ab.getHi());
				 ejecutaArbol(ab.getHc());
				 ejecutaArbol(ab.getHd());
			 }
			 else if(ab.getNodo().equals("P2")){
				 ejecutaArbol(ab.getHi());
				 ejecutaArbol(ab.getHd());
			 }
			 else if(ab.getNodo().equals("SIC")){
				 if(this.hayComida()) 
					 ejecutaArbol(ab.getHi());
				 else 
					 ejecutaArbol(ab.getHd());
			 }
			 else if(ab.getNodo().equals("A")) this.avanza();
			 else if(ab.getNodo().equals("GD")) this.giraDerecha();
			 else if(ab.getNodo().equals("GI")) this.giraIzquierda();
				
			 
		 }	       
	}
	public int getNumPasos() {
		return this.numPasos;
	}
	
	public int getContComida() {
		return this.contadorComida;
	}
	
	
	@Override
	public String toString() {
		return "Comida: " + this.contadorComida + " Pasos: " + this.numPasos + " Posx: " + this.posXHormiga
				+ " Posy: " + this.posYHormiga;
	}
}
