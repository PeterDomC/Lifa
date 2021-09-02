package automata;

import java.util.Objects;

/**
 * Letter class. 
 * Combination of a symbol and an index
 */
public class Letter{
    
    private final String symb;
    private int index;

    /*
     * Constructor
     */
    public Letter(String symb){
        this.symb = symb;
        this.index = 0;
    }
    
    /*
     * Getter: Symbol
     */
    public String getSymb(){
        return symb;
    }

    /*
     * Getter: Index
     */
    public int getIndex(){
        return index;
    }
    
    /*
     * Setter: Index
     */
    public void setIndex(int index){
        this.index = index;
    }
    
    /*
     * Override of equals
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Letter)) return false;

        Letter C = (Letter) o;
        return symb.equals(C.getSymb());
    }

    /*
     * Override of hashCode
     */
    @Override
    public int hashCode(){
        return Objects.hash(symb) + 13*index;
    }
}