package lorikeet;


import lorikeet.exceptions.BadVersionTag;
import lorikeet.exceptions.ContractLessThanOneException;
import lorikeet.exceptions.MilestoneLessThanZeroException;
import lorikeet.exceptions.VersionLessThanOneException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VersionTest {

    @Test
    void testPrettyStringWithAllElements() {
        assertThat(new Version(1, 2, 3, "default-role-to-participant").pretty())
            .isEqualTo("m1.c2.v3__default-role-to-participant");
    }

    @Test
    void testPrettyStringWithJustContractAndVersion() {
        assertThat(new Version(2, 3).pretty())
            .isEqualTo("c2.v3");
    }

    @Test
    void testPrettyStringWithMilestone() {
        assertThat(new Version(1, 2, 3).pretty())
            .isEqualTo("m1.c2.v3");
    }

    @Test
    void testPrettyStringWithTag() {
        assertThat(new Version(2, 3, "add-user").pretty())
            .isEqualTo("c2.v3__add-user");
    }

    @Test
    void testContractCanNotBeLessThan1() {
        assertThatThrownBy(() -> new Version(0, 1)).isInstanceOf(ContractLessThanOneException.class);
    }

    @Test
    void testVersionCanNotBeLessThan1() {
        assertThatThrownBy(() -> new Version(1, 0)).isInstanceOf(VersionLessThanOneException.class);
    }

    @Test
    void testMilestoneCanNotBeLessThan0() {
        assertThatThrownBy(() -> new Version(-1, 1, 1)).isInstanceOf(MilestoneLessThanZeroException.class);
    }

    @Test
    void testTagCanNotStartWithHyphen() {
        assertThatThrownBy(() -> new Version(1, 1, 1, "-1")).isInstanceOf(BadVersionTag.class);
    }

    @Test
    void testTagCanNotContainUnderscore() {
        assertThatThrownBy(() -> new Version(1, 1, 1, "a_b")).isInstanceOf(BadVersionTag.class);
    }

    @Test
    void testElementGetter() {
        Version version = new Version(1, 2, 3, "abc");
        assertThat(version.get(VersionElement.MILESTONE)).isEqualTo("1");
        assertThat(version.get(VersionElement.CONTRACT)).isEqualTo("2");
        assertThat(version.get(VersionElement.VERSION)).isEqualTo("3");
        assertThat(version.get(VersionElement.TAG)).isEqualTo("abc");
    }

    @Test
    void testElementGetterWithOptionals() {
        Version version = new Version(2, 3);
        assertThat(version.get(VersionElement.MILESTONE)).isEqualTo("");
        assertThat(version.get(VersionElement.TAG)).isEqualTo("");
    }
}