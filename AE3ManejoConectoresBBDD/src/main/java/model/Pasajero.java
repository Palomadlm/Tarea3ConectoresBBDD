package model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Pasajero {
    //Atributos
    private int id;
    private String nombre;
    private int edad;
    private double peso;
    private int idCoche;

    //Constructor personalizado
    public Pasajero(int anInt, String nombre, int edad, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    //Método propio
    public void mostrarDatos(){
        System.out.println("nombre = " + nombre);
        System.out.println("edad = " + edad);
        System.out.println("idCoche = " + idCoche);

    }}
