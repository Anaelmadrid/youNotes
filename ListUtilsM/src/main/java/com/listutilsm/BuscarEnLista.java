package com.listutilsm;

import org.jetbrains.annotations.Contract;

public class BuscarEnLista {
    //Buscamos una palabra en el arreglo de datos y retornamos la posicion de la palabra
    public static void main(String[] args) {
        String paises[] = {"Ecuador", "Colombia", "Uruguay", "Chile", "Bolivia", "Argentina", "Paraguay", "Peru", "Brasil", "Venezuela"};
        ordenarArregloString(paises);
        for (int i = 0; i < paises.length; i++) {
            System.out.println(paises[i]);
        }
    }

    @Contract(pure = true)
    public static int buscar(String DEdatos[], String datoAbuscar) {
        for (int i = 0; i < DEdatos.length; i++) {
            if (DEdatos[i].contains(datoAbuscar) || DEdatos[i] == datoAbuscar) {
                return i;
            }
        }
        return -1;
    }

    //Buscamos una palabra en el segun la posicion y retornamos la palabra
    @Contract(pure = true)
    public static String buscarDataOfposicion(String DEdatos[], int posicion) {
        String resultado = DEdatos[posicion];
        return resultado;
    }

    public static void ordenarArregloINT(int datosiInt[]) {
        for (int i = 0; i < datosiInt.length; i++) {
            for (int j = i + 1; j < datosiInt.length; j++) {
                System.out.println(datosiInt[i] + " y " + datosiInt[j] + "\n");
                if (datosiInt[i] > datosiInt[j]) {
                    int temp = datosiInt[i];
                    System.out.println("Temp" + temp);
                    datosiInt[i] = datosiInt[j];
                    datosiInt[j] = temp;
                }
            }
        }
    }

    public static void ordenarArregloString(String datosiString[]) {
        for (int i = 0; i < datosiString.length; i++) {
            for (int j = i + 1; j < datosiString.length; j++) {
                if (datosiString[i].compareTo(datosiString[j]) > 0) {
                    String temp = datosiString[i];
                    datosiString[i] = datosiString[j];
                    datosiString[j] = temp;
                }

            }
        }

    }
}
