package emt.proekt.bicycleshop.sharedkernel.domain.financial;

import emt.proekt.bicycleshop.sharedkernel.domain.base.ValueObject;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
public class Money implements ValueObject {

    @Enumerated(value = EnumType.STRING)
    private final Currency currency;

    private final int amount;

    public Money(@NonNull Currency currency, @NonNull int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public static Money valueOf(Currency currency, int amount) {
        return new Money(currency, amount);
    }

    public Money add(Money money) {
        if(!currency.equals(money.currency)) {
            throw new IllegalArgumentException("different currency");
        }
        return new Money(currency, amount+money.amount);
    }

    public Money substract(Money money) {
        if(!currency.equals(money.currency)) {
            throw new IllegalArgumentException("different currency");
        }
        return new Money(currency, amount-money.amount);
    }

    public Money multiply(int q) {
        return new Money(currency, amount*q);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount &&
                currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    @Override
    public String toString() {
        return String.format("%s %d", currency, amount);
    }
}
