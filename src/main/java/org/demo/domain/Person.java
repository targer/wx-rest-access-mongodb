package org.demo.domain;

import org.springframework.data.annotation.Id;

import static org.demo.util.PreCondition.isTrue;
import static org.demo.util.PreCondition.notEmpty;
import static org.demo.util.PreCondition.notNull;

/**
 * Created by Administrator on 2014/12/10.
 */
public final class Person {
    public static final int MAX_LENGTH_FIRSTNAME = 100;
    public static final int MAX_LENGTH_LASTNAME = 100;

    @Id
    private String id;
    private String firstName;
    private String lastName;

    public Person() {
    }

    private Person(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void update(String firstName, String lastName) {
        checkNames(firstName, lastName);

        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Person [id=%s, firstName='%s', lastName='%s']", id, firstName, lastName);
    }


    /**
     * We don't have to use the builder pattern here because the constructed
     * class has only two String fields. However, I use the builder pattern
     * in this example because it makes the code a bit easier to read.
     */
    public static class Builder {
        private String firstName;
        private String lastName;

        private Builder() {
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Person build() {
            Person build = new Person(this);
            build.checkNames(build.firstName, build.lastName);
            return build;
        }
    }

    private void checkNames(String firstName, String lastName) {
        notNull(firstName, "firstName cannot be null");
        notEmpty(firstName, "firstName cannot be empty");
        isTrue(firstName.length() <= MAX_LENGTH_FIRSTNAME,
                "firstName cannot be longer than %d characters", MAX_LENGTH_FIRSTNAME);
        if (lastName != null) {
            isTrue(lastName.length() <= MAX_LENGTH_LASTNAME,
                    "lastName cannot be longer than %d characters", MAX_LENGTH_LASTNAME);
        }
    }
}
