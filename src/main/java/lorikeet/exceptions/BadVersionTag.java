package lorikeet.exceptions;

import lorikeet.VersionElement;

import java.util.Objects;

public class BadVersionTag extends InvalidVersionException {

    private final String invalidValue;

    public BadVersionTag(String tag) {
        this.invalidValue = tag;
    }

    @Override
    public VersionElement element() {
        return VersionElement.TAG;
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

        BadVersionTag that = (BadVersionTag) o;

        return Objects.equals(this.invalidValue, that.invalidValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invalidValue);
    }
}
