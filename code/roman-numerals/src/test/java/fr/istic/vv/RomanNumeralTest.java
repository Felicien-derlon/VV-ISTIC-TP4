package fr.istic.vv;
import net.jqwik.api.*;
import static org.assertj.core.api.Assertions.*;

public class RomanNumeralTest {

    @Property
    void intergerToRoman(@ForAll("integers") int integer) {
        String roman = RomanNumeraUtils.toRomanNumeral(integer);
        if(RomanNumeraUtils.isValidRomanNumeral(roman)) {
            assertThat(RomanNumeraUtils.parseRomanNumeral(roman)).isEqualTo(integer);
        }
    }

    @Property
    void romanToInteger(@ForAll("romans") String roman) {
        if(RomanNumeraUtils.isValidRomanNumeral(roman)) {
            int integer = RomanNumeraUtils.parseRomanNumeral(roman);
            String intToRoman = RomanNumeraUtils.toRomanNumeral(integer);
            assertThat(intToRoman).isEqualTo(roman);
        }
    }

    @Provide
    Arbitrary<Integer> integers() {
        return Arbitraries.integers().between(1, 3999);
    }

    @Provide
    Arbitrary<String> romans() {
        return Arbitraries.strings().withChars(new char[] {'M','D','C','L','X','V','I'}).ofMaxLength(9);
    }
}
