package lorikeet.exceptions;

import lorikeet.VersionElement;

import java.util.Objects;

public class ContractLessThanOneException extends InvalidVersionException {
    private final String invalidValue;

    public ContractLessThanOneException(long contract) {
        this.invalidValue = String.valueOf(contract);
    }

    @Override
    public VersionElement element() {
        return VersionElement.CONTRACT;
    }

    @Override
    public String invalidValue() {
        return this.invalidValue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || !this.getClass().equals(o.getClass())) {
            return false;
        }

        ContractLessThanOneException that = (ContractLessThanOneException) o;

        return Objects.equals(this.invalidValue, that.invalidValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invalidValue);
    }
}
