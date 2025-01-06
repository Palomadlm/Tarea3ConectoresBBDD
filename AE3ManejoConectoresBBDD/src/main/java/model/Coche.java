package model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Coche {

    //Atributos
    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;

    //Constructor personalizado
    public Coche(String marca, String matricula, String modelo, String color) {
        this.marca = marca;
        this.matricula = matricula;
        this.modelo = modelo;
        this.color = color;
    }
    //Método propio
    public void mostrarDatos(){
        System.out.println("id = " + id);
        System.out.println("matricula = " + matricula);
        System.out.println("marca = " + marca);
        System.out.println("modelo= " + modelo);
        System.out.println("color = " + color);
    }
}



