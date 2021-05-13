package lorikeet.exceptions;

import lorikeet.VersionElement;

import java.util.Objects;

public class MilestoneLessThanZeroException extends InvalidVersionException {
    private final String invalidValue;

    public MilestoneLessThanZeroException(long milestone) {
        this.invalidValue = String.valueOf(milestone);
    }

    @Override
    public VersionElement element() {
        return VersionElement.MILESTONE;
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

        MilestoneLessThanZeroException that = (MilestoneLessThanZeroException) o;

        return Objects.equals(this.invalidValue, that.invalidValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invalidValue);
    }
}
