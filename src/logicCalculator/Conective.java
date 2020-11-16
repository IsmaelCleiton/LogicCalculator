package logicCalculator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fuion
 * 
 */
public class Conective extends Symbol {
    private boolean binary;

    public Conective(boolean binary) {
        super();
        this.binary = binary;
    }

    public boolean isBinary() {
        return binary;
    }

    public void setIsBinary(boolean isBinary) {
        this.binary = isBinary;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
