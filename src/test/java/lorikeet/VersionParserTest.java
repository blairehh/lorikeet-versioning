package lorikeet;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VersionParserTest {

    @Test
    void testJustContractAndVersion() {
        assertThat(new VersionParser().parse("c1.v3"))
            .isEqualTo(new Version(1, 3));
    }

    @Test
    void testWithMilestone() {
        assertThat(new VersionParser().parse("m0.c1.v3"))
            .isEqualTo(new Version(0, 1, 3));
    }

    @Test
    void testWithTag() {
        assertThat(new VersionParser().parse("c1.v3__my-feature"))
            .isEqualTo(new Version(1, 3, "my-feature"));
    }
}