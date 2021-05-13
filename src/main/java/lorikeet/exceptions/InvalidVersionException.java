package lorikeet.exceptions;

import lorikeet.VersionElement;

public abstract class InvalidVersionException extends RuntimeException {
    abstract public VersionElement element();
    abstract public String invalidValue();
}
