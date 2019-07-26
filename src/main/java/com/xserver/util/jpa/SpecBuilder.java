package com.xserver.util.jpa;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public class SpecBuilder<S extends SpecBuilder<S, T>, T> {
    private Specification<T> spec = null;

    public S AND(Specification<T> other) {
        if (other == null) {
            return (S) this;
        }

        if (spec == null) {
            this.spec = other;
            return (S) this;
        }

        this.spec = Specifications.where(spec).and(other);
        return (S) this;
    }

    public S OR(Specification<T> other) {
        if (other == null) {
            return (S) this;
        }

        if (spec == null) {
            this.spec = other;
            return (S) this;
        }

        this.spec = Specifications.where(spec).or(other);
        return (S) this;
    }

    public Specification<T> build() {
        return spec;
    }

    public SpecBuilder() {

    }

}