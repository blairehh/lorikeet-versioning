package lorikeet;

import java.util.regex.Pattern;

public class VersionRegex {
    public static final String TAG_REGEX = "[a-zA-Z0-9][a-zA-Z0-9-]*";
    public static final Pattern TAG_PATTERN = Pattern.compile(TAG_REGEX);

    public static final String VERSION_REGEX = "(m[0-9]+\\.)?c([1-9]+[0-9]*)\\.v([1-9]+[0-9]*)(__" + TAG_REGEX + ")?";
    public static final Pattern VERSION_PATTERN = Pattern.compile(VERSION_REGEX);
}
