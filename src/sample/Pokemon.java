package sample;

public class Pokemon {

    int id;
    String nombre;
    int hp;
    int ataque;
    int defensa;
    String peso;
    String imagen;

    public Pokemon(){

    }

    public Pokemon(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Pokemon(int id, String nombre, int hp, int ataque, int defensa, String peso) {
        this.id = id;
        this.nombre = nombre;
        this.hp = hp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return id + "\t\t" + nombre;
    }
}
