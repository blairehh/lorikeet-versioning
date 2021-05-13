package lorikeet.exceptions;

import lorikeet.VersionElement;

import java.util.Objects;

public class VersionLessThanOneException extends InvalidVersionException {
    private final String invalidValue;

    public VersionLessThanOneException(long version) {
        this.invalidValue = String.valueOf(version);
    }

    @Override
    public VersionElement element() {
        return VersionElement.VERSION;
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

        VersionLessThanOneException that = (VersionLessThanOneException) o;

        return Objects.equals(this.invalidValue, that.invalidValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invalidValue);
    }
}
