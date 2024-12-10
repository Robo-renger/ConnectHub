package connecthub;

import connecthub.interfaces.ValidationStrategy;

public class Validator {

    private static Validator instance; // Singleton instance
    private ValidationStrategy strategy; // Validation strategy

    private Validator() {} // Private constructor to prevent instantiation
    
    public static Validator getInstance()
    {
        if(instance == null)
            instance = new Validator();
                    
        return instance;
    }
    
    public void setStrategy(ValidationStrategy strategy)
    {
        this.strategy = strategy;
    }
    
    public boolean validate(String data) throws IllegalArgumentException {
        if(strategy == null)
            throw new IllegalStateException("Validation strategy is not set");
        
        return strategy.validate(data);
    }
}
