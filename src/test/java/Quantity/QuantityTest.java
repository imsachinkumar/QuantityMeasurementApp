package org.example;

import UtilityClasses.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

private static final double EPS = 1e-6;

// ---------------- LENGTH TESTS ----------------

@Test
void testLengthEquality() {
    Quantity<LengthUnit> f = new Quantity<>(1.0, LengthUnit.FEET);
    Quantity<LengthUnit> i = new Quantity<>(12.0, LengthUnit.INCH);

    assertEquals(f, i);
}

@Test
void testLengthConversion() {
    Quantity<LengthUnit> f = new Quantity<>(1.0, LengthUnit.FEET);
    Quantity<LengthUnit> result = f.convertTo(LengthUnit.INCH);

    assertEquals(12.0, result.getValue(), EPS);
}

@Test
void testLengthAddition() {
    Quantity<LengthUnit> f = new Quantity<>(1.0, LengthUnit.FEET);
    Quantity<LengthUnit> i = new Quantity<>(12.0, LengthUnit.INCH);

    Quantity<LengthUnit> result = f.add(i, LengthUnit.FEET);

    assertEquals(2.0, result.getValue(), EPS);
}

// ---------------- WEIGHT TESTS ----------------

@Test
void testWeightEquality() {
    Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
    Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);

    assertEquals(kg, g);
}

@Test
void testWeightConversion() {
    Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
    Quantity<WeightUnit> result = kg.convertTo(WeightUnit.GRAM);

    assertEquals(1000.0, result.getValue(), EPS);
}

@Test
void testWeightAddition() {
    Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
    Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);

    Quantity<WeightUnit> result = kg.add(g, WeightUnit.KILOGRAM);

    assertEquals(2.0, result.getValue(), EPS);
}

// ---------------- CROSS CATEGORY SAFETY ----------------

@Test
void testCrossCategoryComparison() {
    Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
    Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

    assertNotEquals(length, weight);
}

// ---------------- VALIDATION ----------------

@Test
void testNullUnitConstructor() {
    assertThrows(IllegalArgumentException.class,
            () -> new Quantity<>(1.0, null));
}

@Test
void testInvalidValueConstructor() {
    assertThrows(IllegalArgumentException.class,
            () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
}

// ---------------- VOLUME EQUALITY TESTS ----------------

@Test
void testEquality_LitreToLitre_SameValue() {
    Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
    Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);

    assertEquals(v1, v2);
}

@Test
void testEquality_LitreToMillilitre() {
    Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
    Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

    assertEquals(litre, ml);
}

@Test
void testEquality_LitreToGallon() {
    Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
    Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);

    assertEquals(litre, gallon);
}

// ---------------- CONVERSION TESTS ----------------

@Test
void testConversion_LitreToMillilitre() {
    Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
    Quantity<VolumeUnit> ml = litre.convertTo(VolumeUnit.MILLILITRE);

    assertEquals(1000.0, ml.getValue(), EPS);
    assertEquals(VolumeUnit.MILLILITRE, ml.getUnit());
}

@Test
void testConversion_GallonToLitre() {
    Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
    Quantity<VolumeUnit> litre = gallon.convertTo(VolumeUnit.LITRE);

    assertEquals(3.78541, litre.getValue(), EPS);
}

// ---------------- ADDITION ----------------

@Test
void testAddition_LitrePlusMillilitre() {
    Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
    Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

    Quantity<VolumeUnit> result = litre.add(ml);

    assertEquals(2.0, result.getValue(), EPS);
    assertEquals(VolumeUnit.LITRE, result.getUnit());
}

// ---------------- SUBTRACTION ----------------

@Test
void testSubtraction_SameUnit_Length() {
    Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
    Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

    Quantity<LengthUnit> result = q1.subtract(q2);

    assertEquals(5.0, result.getValue(), EPS);
}

// ---------------- TEMPERATURE TESTS ----------------

@Test
void testTemperatureEquality_CelsiusToFahrenheit() {
    Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
    Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

    assertEquals(c, f);
}

@Test
void testTemperatureConversion() {
    Quantity<TemperatureUnit> result =
            new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                    .convertTo(TemperatureUnit.FAHRENHEIT);

    assertEquals(212.0, result.getValue(), EPS);
}

@Test
void testTemperatureUnsupportedOperation() {
    Quantity<TemperatureUnit> c1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
    Quantity<TemperatureUnit> c2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

    assertThrows(UnsupportedOperationException.class,
            () -> c1.add(c2));
}

}
