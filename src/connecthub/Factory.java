package connecthub;

import connecthub.builders.UserBuilder;
import connecthub.builders.ContentBuilder;
import connecthub.interfaces.Builder;
import connecthub.interfaces.Identifiable;

public class Factory {

    public static Identifiable createEntity(Builder<? extends Identifiable> builder) {
        return builder.build();
    }
}
