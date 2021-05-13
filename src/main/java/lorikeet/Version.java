package lorikeet;

import lorikeet.exceptions.BadVersionTag;
import lorikeet.exceptions.ContractLessThanOneException;
import lorikeet.exceptions.MilestoneLessThanZeroException;
import lorikeet.exceptions.VersionLessThanOneException;

import java.util.Optional;

import static lorikeet.VersionRegex.TAG_PATTERN;

public record Version(Optional<Long> milestone, long contract, long version, Optional<String> tag) {

    public Version {
        if (contract < 1) {
            throw new ContractLessThanOneException(contract);
        }

        if (version < 1) {
            throw new VersionLessThanOneException(version);
        }

        if (milestone.isPresent() && milestone.orElseThrow() < 0) {
            throw new MilestoneLessThanZeroException(milestone.orElseThrow());
        }

        if (tag.isPresent()) {
            final boolean matches = TAG_PATTERN.matcher(tag.orElseThrow()).matches();
            if (!matches) {
                throw new BadVersionTag(tag.orElseThrow());
            }
        }
    }

    public Version(long contract, long version) {
        this(Optional.empty(), contract, version, Optional.empty());
    }

    public Version(long milestone, long contract, long version, String tag) {
        this(Optional.of(milestone), contract, version, Optional.ofNullable(tag));
    }

    public Version(long milestone, long contract, long version) {
        this(Optional.of(milestone), contract, version, Optional.empty());
    }

    public Version(long contract, long version, String tag) {
        this(Optional.empty(), contract, version, Optional.ofNullable(tag));
    }

    public String pretty() {
        final StringBuilder builder = new StringBuilder();
        this.milestone.ifPresent((value) -> {
            builder.append("m");
            builder.append(value);
            builder.append(".");
        });
        builder.append("c");
        builder.append(this.contract);
        builder.append(".v");
        builder.append(this.version);
        this.tag.ifPresent((value) -> {
            builder.append("__");
            builder.append(value);
        });
        return builder.toString();
    }

    public String get(VersionElement element) {
        return switch (element) {
            case TAG -> this.tag.orElse("");
            case VERSION -> String.valueOf(this.version);
            case CONTRACT -> String.valueOf(this.contract);
            case MILESTONE -> this.milestone.map(String::valueOf).orElse("");
        };
    }
}
