package lorikeet;

import lorikeet.exceptions.UnparseableVersionException;

import java.util.Optional;
import java.util.regex.Matcher;

import static lorikeet.VersionRegex.VERSION_PATTERN;

public class VersionParser {


    public Version parse(String version) {
        final Matcher matcher = VERSION_PATTERN.matcher(version);
        if (!matcher.matches()) {
            throw new UnparseableVersionException(version);
        }

        final Optional<Long> milestone = Optional.ofNullable(matcher.group(1))
            .map((value) -> value.substring(1, value.length() - 1))
            .map(Long::parseLong);

        final Optional<String> tag = Optional.ofNullable(matcher.group(4))
            .map((value) -> value.substring(2));

        return new Version(
            milestone,
            Long.parseLong(matcher.group(2)),
            Long.parseLong(matcher.group(3)),
            tag
        );
    }
}
