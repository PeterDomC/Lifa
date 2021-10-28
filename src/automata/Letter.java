package automata;

import java.util.Objects;

/*
 * Letter class.
 * This is just a wrapped String.
 * @Immutable
 */
public class Letter{
    
    private final String symb;

    /**
     * Constructor
     */
    public Letter(String symb){
        this.symb = symb;
    }
    
    /**
     * Getter: Symbol
     */
    public String getSymb(){
        return symb;
    }
    
    /**
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

    /**
     * Override of hashCode
     */
    @Override
    public int hashCode(){
        return Objects.hash(symb);
    }
}