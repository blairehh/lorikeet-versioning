package lorikeet.exceptions;

import java.util.Objects;

public class UnparseableVersionException extends RuntimeException {
    private final String version;

    public UnparseableVersionException(String version) {
        this.version = version;
    }

    public String version() {
        return this.version;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || !this.getClass().equals(o.getClass())) {
            return false;
        }

        UnparseableVersionException that = (UnparseableVersionException) o;

        return Objects.equals(this.version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version);
    }
}
