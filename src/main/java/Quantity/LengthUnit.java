package Quantity;
public enum LengthUnit {
    FEET {
        public double toFeet(double value) {
            return value;
        }
    },
    INCH {
        public double toFeet(double value) {
            return value / 12.0;
        }
    },
    YARDS {
        public double toFeet(double value) {
            return value * 3.0;
        }
    },
    CENTIMETERS{
        public double toFeet(double value) {
            return (value * 0.393701) / 12.0;
        }
    };

    public abstract double toFeet(double value);
}
