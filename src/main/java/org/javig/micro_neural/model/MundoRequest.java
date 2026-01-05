package org.javig.micro_neural.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MundoRequest {

    private int[][] data;
    private int turno;

}
