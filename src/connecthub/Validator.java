package connecthub;

import connecthub.interfaces.ValidationStrategy;

public class Validator {

    private ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String data) {
        return strategy.validate(data);
    }
}
