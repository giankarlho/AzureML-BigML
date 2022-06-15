/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.Data;


@Data
public class PrediccionInputData {

    double confidence;
    String category;
    double credits;
    double probability;
    String result;
    int edad, insulina, presion, pliegue, embarazos, glucosa;
    double imc, pedigri;
    String diabetes;

    @Override
    public String toString() {
        return "PrediccionInputData{"
                + "Embarazos=" + embarazos
                + ", Glucosa=" + glucosa
                + ", Presion_sanguinea=" + presion
                + ", Pliegue_cutaneo=" + pliegue
                + ", Insulina=" + insulina
                + ", Indice_de_masa_corporal=" + imc
                + ", Pedigri_diabetes=" + pedigri
                + ", Edad=" + edad                
                + '}';
    }
}
